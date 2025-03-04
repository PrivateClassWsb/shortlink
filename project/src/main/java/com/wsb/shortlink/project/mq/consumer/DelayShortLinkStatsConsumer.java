package com.wsb.shortlink.project.mq.consumer;

import com.wsb.shortlink.project.common.convention.exception.ServiceException;
import com.wsb.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.wsb.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import com.wsb.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import static com.wsb.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;

/**
 * 延迟记录短链接统计组件
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsConsumer implements InitializingBean {

    private final RedissonClient redissonClient;
    private final ShortLinkService shortLinkService;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    public void onMessage() {
        Executors.newSingleThreadExecutor(  // 创建单线程线程池
            runnable -> {   // 自定义线程工厂
                Thread thread = new Thread(runnable);
                thread.setName("delay_short-link_stats_consumer");  // 线程命名
                thread.setDaemon(Boolean.TRUE); // 设置为守护线程
                return thread;
            }).execute(() -> {  // 提交任务到线程池
                RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY); // 获取阻塞队列
                RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);    // 获取延迟队列
                for (; ; ) {    // 无限循环消费消息
                    try {
                        ShortLinkStatsRecordDTO statsRecord = delayedQueue.poll();
                        if (statsRecord != null) {
                            if (!messageQueueIdempotentHandler.isMessageProcessed(statsRecord.getKeys())) {
                                if (messageQueueIdempotentHandler.isAccomplish(statsRecord.getKeys())) {
                                    return;
                                }
                                throw new ServiceException("消息未完成流程，需要消息队列重试");
                            }
                            try {
                                shortLinkService.shortLinkStats(null, null, statsRecord);
                            }catch (Throwable ex){
                                messageQueueIdempotentHandler.delMessageProcessed(statsRecord.getKeys());
                                log.error("延迟记录短链接监控消费异常", ex);
                            }
                            messageQueueIdempotentHandler.setAccomplish(statsRecord.getKeys());
                            continue;
                        }
                        LockSupport.parkUntil(500);
                    } catch (Throwable ignored) {
                    }
                }
            });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        onMessage();    // Spring Bean初始化完成后启动消费者
    }
}
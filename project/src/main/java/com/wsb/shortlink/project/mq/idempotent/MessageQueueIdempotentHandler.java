package com.wsb.shortlink.project.mq.idempotent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 消息队列幂等处理器
 */
@Component
@RequiredArgsConstructor
public class MessageQueueIdempotentHandler {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String IDEMPOTENT_KEY_PREFIX = "short-link:idempotent:";

    /**
     * 判断当前消息是否消费过
     *
     * @param messageId 消息唯一标识
     * @return 消息是否消费过
     */
    public boolean isMessageBeingConsumed(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        // 仅当 Redis 中 不存在 指定的 key 时，才会设置它的值为 "0",并返回 true。如果 key 已存在，则不会修改它的值，并返回 false。
        return Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, "0", 2, TimeUnit.MINUTES));
    }

    /**
     * 判断消息消费流程是否执行完成
     *
     * @param messageId 消息唯一标识
     * @return 消息是否执行完成
     */
    public boolean isAccomplish(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        return Objects.equals(stringRedisTemplate.opsForValue().get(key), "1");
    }

    /**
     * 设置消息流程执行完成
     *
     * @param messageId 消息唯一标识
     */
    public void setAccomplish(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        stringRedisTemplate.opsForValue().set(key, "1", 2, TimeUnit.MINUTES);
    }

    /**
     * 如果消息处理遇到异常情况，删除幂等标识
     *
     * @param messageId 消息唯一标识
     */
    public void delMessageProcessed(String messageId) {
        String key = IDEMPOTENT_KEY_PREFIX + messageId;
        stringRedisTemplate.delete(key);
    }
}
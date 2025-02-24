package com.wsb.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsb.shortlink.project.dao.entity.LinkLocaleStatsDO;
import com.wsb.shortlink.project.dao.entity.LinkOsStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 操作系统统计访问持久层
 */
public interface LinkOsStatsMapper extends BaseMapper<LinkOsStatsDO> {

    /**
     * 记录地区访问监控数据
     */
    @Insert("INSERT INTO t_link_os_stats (gid, full_short_url, date, cnt, os, create_time, update_time, del_flag)" +
            "VALUES(#{linkOsStats.gid}, #{linkOsStats.fullShortUrl}, #{linkOsStats.date}, #{linkOsStats.cnt}, #{linkOsStats.os}, NOW(), NOW(),0) ON DUPLICATE KEY UPDATE " +
            "cnt = cnt + #{linkOsStats.cnt};")
    void shortLinkOstats(@Param("linkOsStats") LinkOsStatsDO linkOsStatsDO);
}

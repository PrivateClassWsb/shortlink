package com.wsb.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsb.shortlink.project.dao.entity.LinkAccessStatsDO;
import com.wsb.shortlink.project.dao.entity.LinkLocaleStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 地区统计访问持久层
 */
public interface LinkLocaleStatsMapper extends BaseMapper<LinkLocaleStatsDO> {

    /**
     * 记录地区访问监控数据
     */
    @Insert("INSERT INTO t_link_locale_stats (gid, full_short_url, date, cnt, country, province, city, adcode, create_time, update_time, del_flag)" +
            "VALUES(#{linkLocaleStats.gid}, #{linkLocaleStats.fullShortUrl}, #{linkLocaleStats.date}, #{linkLocaleStats.cnt}, #{linkLocaleStats.country}, #{linkLocaleStats.province}, #{linkLocaleStats.city}, #{linkLocaleStats.adcode}, NOW(), NOW(),0) ON DUPLICATE KEY UPDATE " +
            "cnt = cnt + #{linkLocaleStats.cnt};")
    void shortLinkLocaleStats(@Param("linkLocaleStats") LinkLocaleStatsDO linkLocaleStatsDO);
}

package com.wsb.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsb.shortlink.project.dao.entity.ShortLinkDO;
import com.wsb.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.wsb.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}

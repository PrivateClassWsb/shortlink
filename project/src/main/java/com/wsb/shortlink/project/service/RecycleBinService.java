package com.wsb.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsb.shortlink.project.dao.entity.ShortLinkDO;
import com.wsb.shortlink.project.dto.req.*;
import com.wsb.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * 回收站管理接口层
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 保存回收站请求参数
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询短链接
     *
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回解雇
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * 恢复短链接
     * @param requestParam 恢复短链接请求参数
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);

    /**
     * 移除短链接
     * @param requestParam 移除短链接请求参数
     */
    void removeRecycleBin(RecycleBinRemoveReqDTO requestParam);
}

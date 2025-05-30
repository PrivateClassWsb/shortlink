package com.wsb.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 短链接分组更新参数
 */
@Data
public class ShortLinkGroupUpdateReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名
     */
    private String name;
}

package com.wsb.shortlink.project.test;

public class LinkTableShardingTest {

    public static final String SQL = "create table t_link_%d\n" +
            "(\n" +
            "    id              bigint auto_increment comment 'ID'\n" +
            "        primary key,\n" +
            "    domain          varchar(128) collate utf8mb4_general_ci  null comment '域名',\n" +
            "    short_uri       varchar(8) collate utf8mb4_bin           null comment '短链接',\n" +
            "    full_short_url  varchar(128) collate utf8mb4_general_ci  null comment '完整短链接',\n" +
            "    origin_url      varchar(1024) collate utf8mb4_general_ci null comment '原始链接',\n" +
            "    click_num       int default 0                            null comment '点击量',\n" +
            "    gid             varchar(32) collate utf8mb4_general_ci   null comment '分组id',\n" +
            "    favicon         varchar(256) collate utf8mb4_general_ci  null comment '网站图标',\n" +
            "    enable_status   tinyint(1)                               null comment '启用标识 0：启用 1：未启用',\n" +
            "    created_type    tinyint(1)                               null comment '创建类型 0：接口创建 1：控制台创建',\n" +
            "    valid_date_type tinyint(1)                               null comment '有效期类型 0：永久有效 1：自定义',\n" +
            "    valid_date      datetime                                 null comment '有效期',\n" +
            "    `describe`      varchar(1024) collate utf8mb4_general_ci null comment '描述',\n" +
            "    create_time     datetime                                 null comment '创建时间',\n" +
            "    update_time     datetime                                 null comment '修改时间',\n" +
            "    del_flag        tinyint(1)                               null comment '删除标识 0：未删除 1：已删除',\n" +
            "    constraint idx_unique_full_short_url\n" +
            "        unique (full_short_url)\n" +
            ")\n" +
            "    comment '短链接表';\n" +
            "\n";

    public static void main(String[] args) {
        for (int i = 0; i <16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }

}

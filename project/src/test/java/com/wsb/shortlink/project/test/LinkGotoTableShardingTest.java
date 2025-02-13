package com.wsb.shortlink.project.test;

public class LinkGotoTableShardingTest {

    public static final String SQL = "create table t_link_goto_%d\n" +
            "(\n" +
            "    id             bigint auto_increment comment 'ID'\n" +
            "        primary key,\n" +
            "    full_short_url varchar(128) collate utf8mb4_general_ci null comment '完整短链接',\n" +
            "    gid            varchar(32) collate utf8mb4_general_ci  null comment '分组标识'\n" +
            ")\n" +
            "    comment '路由表';";

    public static void main(String[] args) {
        for (int i = 0; i <16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }

}

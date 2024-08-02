create database sm_db;
use sm_db;

create table news_headline
(
    hid         int auto_increment comment '头条id'
        primary key,
    title       varchar(50)   not null comment '头条标题',
    article     varchar(5000) not null comment '头条新闻内容',
    type        int           not null comment '头条类型id',
    publisher   int           not null comment '头条发布用户id',
    page_views  int           not null comment '头条浏览量',
    create_time datetime      null comment '头条发布时间',
    update_time datetime      null comment '头条最后的修改时间',
    version     int default 1 null comment '乐观锁',
    is_deleted  int default 0 null comment '头条是否被删除 1 删除  0 未删除'
)
    row_format = DYNAMIC;

create table news_type
(
    tid        int auto_increment comment '新闻类型id'
        primary key,
    tname      varchar(10)   not null comment '新闻类型描述',
    version    int default 1 null comment '乐观锁',
    is_deleted int default 0 null comment '头条是否被删除 1 删除  0 未删除'
)
    row_format = DYNAMIC;

create table news_user
(
    uid        int auto_increment comment '用户id'
        primary key,
    username   varchar(20)   not null comment '用户登录名',
    user_pwd   varchar(50)   not null comment '用户登录密码密文',
    nick_name  varchar(20)   not null comment '用户昵称',
    version    int default 1 null comment '乐观锁',
    is_deleted int default 0 null comment '头条是否被删除 1 删除  0 未删除',
    constraint username_unique
        unique (username)
)
    row_format = DYNAMIC;


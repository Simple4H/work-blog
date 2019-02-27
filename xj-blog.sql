create Database xj_blog;
use xj_blog;

-- 用户表
create table `xj_user`
(
  `id`          int(11)     not null auto_increment
    comment '用户表id',
  `username`    varchar(50) not null
    comment '账号',
  `password`    varchar(50) not null
    comment '密码',
  `email`       varchar(50) not null
    comment '邮箱',
  `create_time` datetime    not null
    comment '创建时间',
  `update_time` datetime    not null
    comment '最后一次登录时间',
  primary key (`id`),
  unique key `user_name_unique` (`username`) using btree
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 用户详情表
create table `xj_user_item`
(
  `id`         int(11) not null auto_increment
    comment '用户详情表ID',
  `user_id`    int(11) not null
    comment '用户表ID',
  `article_id` int(11) not null
    comment '文章表ID',
  primary key (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 文章表
create table `xj_article`
(
  `id`           int(11)       not null auto_increment
    comment '文章表ID',
  `user_id`      int(11)       not null
    comment '用户ID',
  `title`        varchar(30)   not null
    comment '标题',
  `content`      varchar(1000) not null
    comment '内容',
  `tags`         varchar(10)   not null
    comment '类型',
  `like_article` int(10)       not null
    comment '点赞数',
  `create_time`  datetime      not null
    comment '创建时间',
  `update_time`  datetime      not null
    comment '更新时间',
  primary key (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 评论表
create table `xj_comment`
(
  `id`          int(11)      not null auto_increment
    comment '评论表id',
  `user_id`     int(11)      not null
    comment '用户id',
  `article_id`  int(11)      not null
    comment '博客id',
  `comment`     varchar(140) not null
    comment '评论',
  `create_time` datetime     not null
    comment '创建时间',
  primary key (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
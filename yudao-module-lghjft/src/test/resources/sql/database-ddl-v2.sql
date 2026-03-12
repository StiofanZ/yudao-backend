CREATE TABLE `system_dept`
(
    `id`             bigint                                 NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `name`           varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
    `parent_id`      bigint                                 NOT NULL DEFAULT '0' COMMENT '父部门id',
    `sort`           int                                    NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `leader_user_id` bigint                                          DEFAULT NULL COMMENT '负责人',
    `phone`          varchar(11) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '联系电话',
    `email`          varchar(50) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '邮箱',
    `zt` tinyint NOT NULL COMMENT '部门状态（0正常 1停用）',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint                                 NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='部门表'

CREATE TABLE `system_dict_data`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `sort`        int                                     NOT NULL DEFAULT '0' COMMENT '字典排序',
    `label`       varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典标签',
    `value`       varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `color_type`  varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '颜色类型',
    `css_class`   varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT 'css 样式',
    `remark`      varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='字典数据表'

CREATE TABLE `system_dict_type`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `name`         varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典名称',
    `lx` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
    `zt` tinyint                                 NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `remark`       varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `deleted_time` datetime                                         DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='字典类型表'

CREATE TABLE `system_login_log`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `log_type`    bigint                                  NOT NULL COMMENT '日志类型',
    `trace_id`    varchar(64) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `yh_id` bigint NOT NULL DEFAULT '0' COMMENT '用户编号',
    `user_type`   tinyint                                 NOT NULL DEFAULT '0' COMMENT '用户类型',
    `username`    varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '用户账号',
    `result`      tinyint                                 NOT NULL COMMENT '登陆结果',
    `user_ip`     varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户 IP',
    `user_agent`  varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '浏览器 UA',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统访问记录'

CREATE TABLE `system_mail_account`
(
    `id`              bigint                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `mail`            varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
    `username`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `password`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `host`            varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'SMTP 服务器域名',
    `port`            int                                     NOT NULL COMMENT 'SMTP 服务器端口',
    `ssl_enable`      bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否开启 SSL',
    `starttls_enable` bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否开启 STARTTLS',
    `creator`         varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`         varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`         bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='邮箱账号表'

CREATE TABLE `system_mail_log`
(
    `id`                bigint                                   NOT NULL AUTO_INCREMENT COMMENT '编号',
    `yh_id` bigint DEFAULT NULL COMMENT '用户编号',
    `user_type`         tinyint                                           DEFAULT NULL COMMENT '用户类型',
    `to_mails`          varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接收邮箱地址',
    `cc_mails`          varchar(1024) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '抄送邮箱地址',
    `bcc_mails`         varchar(1024) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '密送邮箱地址',
    `account_id`        bigint                                   NOT NULL COMMENT '邮箱账号编号',
    `from_mail`         varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '发送邮箱地址',
    `template_id`       bigint                                   NOT NULL COMMENT '模板编号',
    `template_code`     varchar(63) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模板编码',
    `template_nickname` varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '模版发送人名称',
    `template_title`    varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '邮件标题',
    `template_content`  text COLLATE utf8mb4_unicode_ci          NOT NULL COMMENT '邮件内容',
    `template_params`   varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '邮件参数',
    `send_status`       tinyint                                  NOT NULL DEFAULT '0' COMMENT '发送状态',
    `send_time`         datetime                                          DEFAULT NULL COMMENT '发送时间',
    `send_message_id`   varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '发送返回的消息 ID',
    `send_exception`    varchar(4096) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '发送异常',
    `creator`           varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`       datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`           varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`       datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`           bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='邮件日志表'

CREATE TABLE `system_mail_template`
(
    `id`          bigint                                    NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(63) COLLATE utf8mb4_unicode_ci    NOT NULL COMMENT '模板名称',
    `code`        varchar(63) COLLATE utf8mb4_unicode_ci    NOT NULL COMMENT '模板编码',
    `account_id`  bigint                                    NOT NULL COMMENT '发送的邮箱账号编号',
    `nickname`    varchar(255) COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT '发送人名称',
    `title`       varchar(255) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模板标题',
    `nr` varchar(10240) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板内容',
    `params`      varchar(255) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '参数数组',
    `zt` tinyint                                   NOT NULL COMMENT '开启状态',
    `remark`      varchar(255) COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci             DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci             DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                    NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='邮件模版表'

CREATE TABLE `system_menu`
(
    `id`             bigint                                  NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`           varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '菜单名称',
    `permission`     varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `lx` tinyint NOT NULL COMMENT '菜单类型',
    `sort`           int                                     NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `parent_id`      bigint                                  NOT NULL DEFAULT '0' COMMENT '父菜单ID',
    `path`           varchar(200) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '路由地址',
    `icon`           varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT '#' COMMENT '菜单图标',
    `component`      varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '组件路径',
    `component_name` varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '组件名',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '菜单状态',
    `visible`        bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `keep_alive`     bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否缓存',
    `always_show`    bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否总是显示',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 250001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表'

CREATE TABLE `system_notice`
(
    `id`          bigint                                 NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `title`       varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
    `nr` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容',
    `lx` tinyint                         NOT NULL COMMENT '公告类型（1通知 2公告）',
    `zt` tinyint                         NOT NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                 NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='通知公告表'

CREATE TABLE `system_notify_message`
(
    `id`                bigint                                   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `yh_id` bigint NOT NULL COMMENT '用户id',
    `user_type`         tinyint                                  NOT NULL COMMENT '用户类型',
    `template_id`       bigint                                   NOT NULL COMMENT '模版编号',
    `template_code`     varchar(64) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模板编码',
    `template_nickname` varchar(63) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模版发送人名称',
    `template_content`  varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模版内容',
    `template_type`     int                                      NOT NULL COMMENT '模版类型',
    `template_params`   varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模版参数',
    `read_status`       bit(1)                                   NOT NULL COMMENT '是否已读',
    `read_time`         datetime                                          DEFAULT NULL COMMENT '阅读时间',
    `creator`           varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`       datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`           varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`       datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`           bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`         bigint                                   NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='站内信消息表'

CREATE TABLE `system_notify_template`
(
    `id`          bigint                                   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(63) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模板名称',
    `code`        varchar(64) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模版编码',
    `nickname`    varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '发送人名称',
    `nr` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模版内容',
    `lx` tinyint                                  NOT NULL COMMENT '类型',
    `params`      varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '参数数组',
    `zt` tinyint                                  NOT NULL COMMENT '状态',
    `remark`      varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='站内信模板表'

CREATE TABLE `system_oauth2_access_token`
(
    `id`            bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `yh_id` bigint NOT NULL COMMENT '用户编号',
    `user_type`     tinyint                                 NOT NULL COMMENT '用户类型',
    `user_info`     varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户信息',
    `access_token`  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
    `refresh_token` varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '刷新令牌',
    `client_id`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes`        varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '授权范围',
    `expires_time`  datetime                                NOT NULL COMMENT '过期时间',
    `creator`       varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`     bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`),
    KEY `idx_access_token` (`access_token`),
    KEY `idx_refresh_token` (`refresh_token`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 访问令牌'

CREATE TABLE `system_oauth2_approve`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `yh_id` bigint NOT NULL COMMENT '用户编号',
    `user_type`    tinyint                                 NOT NULL COMMENT '用户类型',
    `client_id`    varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scope`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '授权范围',
    `approved`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否接受',
    `expires_time` datetime                                NOT NULL COMMENT '过期时间',
    `creator`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`    bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 批准表'

CREATE TABLE `system_oauth2_client`
(
    `id`                             bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `client_id`                      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `secret`                         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
    `name`                           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
    `logo`                           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用图标',
    `description`                    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '应用描述',
    `zt` tinyint NOT NULL COMMENT '状态',
    `access_token_validity_seconds`  int                                     NOT NULL COMMENT '访问令牌的有效期',
    `refresh_token_validity_seconds` int                                     NOT NULL COMMENT '刷新令牌的有效期',
    `redirect_uris`                  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
    `authorized_grant_types`         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
    `scopes`                         varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '授权范围',
    `auto_approve_scopes`            varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '自动通过的授权范围',
    `authorities`                    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '权限',
    `resource_ids`                   varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '资源',
    `additional_information`         varchar(4096) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '附加信息',
    `creator`                        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`                    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`                    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                        bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 客户端表'

CREATE TABLE `system_oauth2_code`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `yh_id` bigint NOT NULL COMMENT '用户编号',
    `user_type`    tinyint                                 NOT NULL COMMENT '用户类型',
    `code`         varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '授权码',
    `client_id`    varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes`       varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '授权范围',
    `expires_time` datetime                                NOT NULL COMMENT '过期时间',
    `redirect_uri` varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '可重定向的 URI 地址',
    `state`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '状态',
    `creator`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`    bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 授权码表'

CREATE TABLE `system_oauth2_refresh_token`
(
    `id`            bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `yh_id` bigint DEFAULT NULL COMMENT '用户编号',
    `refresh_token` varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '刷新令牌',
    `user_type`     tinyint                                 NOT NULL COMMENT '用户类型',
    `client_id`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes`        varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '授权范围',
    `expires_time`  datetime                                NOT NULL COMMENT '过期时间',
    `creator`       varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`     bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 刷新令牌'

CREATE TABLE `system_operate_log`
(
    `id`             bigint                                   NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `trace_id`       varchar(64) COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `yh_id` bigint                                 NOT NULL COMMENT '用户编号',
    `user_type`      tinyint                                  NOT NULL DEFAULT '0' COMMENT '用户类型',
    `lx`    varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作模块类型',
    `sub_type`       varchar(50) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '操作名',
    `biz_id`         bigint                                   NOT NULL COMMENT '操作数据模块编号',
    `action`         varchar(2000) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作内容',
    `success`        bit(1)                                   NOT NULL DEFAULT b'1' COMMENT '操作结果',
    `extra`          varchar(2000) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '拓展字段',
    `request_method` varchar(16) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '请求方法名',
    `request_url`    varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '请求地址',
    `user_ip`        varchar(50) COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT '用户 IP',
    `user_agent`     varchar(512) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '浏览器 UA',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint                                   NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='操作日志记录 V2 版本'

CREATE TABLE `system_post`
(
    `id`          bigint                                 NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `code`        varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
    `name`        varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
    `sort`        int                                    NOT NULL COMMENT '显示顺序',
    `zt` tinyint NOT NULL COMMENT '状态（0正常 1停用）',
    `remark`      varchar(500) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                 NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='岗位信息表'

CREATE TABLE `system_role`
(
    `id`                  bigint                                  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`                varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '角色名称',
    `code`                varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
    `sort`                int                                     NOT NULL COMMENT '显示顺序',
    `data_scope`          tinyint                                 NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `data_scope_dept_ids` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `zt` tinyint NOT NULL COMMENT '角色状态（0正常 1停用）',
    `lx` tinyint NOT NULL COMMENT '角色类型',
    `remark`              varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`             varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`             varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`             bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`           bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色信息表'

CREATE TABLE `system_role_menu`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `role_id`     bigint   NOT NULL COMMENT '角色ID',
    `menu_id`     bigint   NOT NULL COMMENT '菜单ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)   NOT NULL                      DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint   NOT NULL                      DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色和菜单关联表'

CREATE TABLE `system_sms_channel`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `signature`    varchar(12) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信签名',
    `code`         varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '渠道编码',
    `status`  tinyint NOT NULL COMMENT '开启状态',
    `remark`       varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `api_key` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '短信 API 的账号',
    `api_secret`   varchar(128) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '短信 API 的秘钥',
    `callback_url` varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '短信发送回调 URL',
    `kzcs`    json                                    DEFAULT NULL COMMENT '扩展参数',
    `creator`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信渠道'

CREATE TABLE `system_sms_code`
(
    `id`          bigint                                 NOT NULL AUTO_INCREMENT COMMENT '编号',
    `mobile`      varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
    `code`        varchar(6) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '验证码',
    `create_ip`   varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建 IP',
    `scene`       tinyint                                NOT NULL COMMENT '发送场景',
    `today_index` tinyint                                NOT NULL COMMENT '今日发送的第几条',
    `used`        tinyint                                NOT NULL COMMENT '是否使用',
    `used_time`   datetime                                        DEFAULT NULL COMMENT '使用时间',
    `used_ip`     varchar(255) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '使用 IP',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                 NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`),
    KEY `idx_mobile` (`mobile`) COMMENT '手机号'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='手机验证码'

CREATE TABLE `system_sms_log`
(
    `id`               bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `channel_id`       bigint                                  NOT NULL COMMENT '短信渠道编号',
    `channel_code`     varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信渠道编码',
    `template_id`      bigint                                  NOT NULL COMMENT '模板编号',
    `template_code`    varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板编码',
    `template_type`    tinyint                                 NOT NULL COMMENT '短信类型',
    `template_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短信内容',
    `template_params`  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短信参数',
    `api_template_id`  varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信 API 的模板编号',
    `mobile`           varchar(11) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '手机号',
    `yh_id` bigint DEFAULT NULL COMMENT '用户编号',
    `user_type`        tinyint                                          DEFAULT NULL COMMENT '用户类型',
    `send_status`      tinyint                                 NOT NULL DEFAULT '0' COMMENT '发送状态',
    `send_time`        datetime                                         DEFAULT NULL COMMENT '发送时间',
    `api_send_code`    varchar(63) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '短信 API 发送结果的编码',
    `api_send_msg`     varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '短信 API 发送失败的提示',
    `api_request_id`   varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '短信 API 发送返回的唯一请求 ID',
    `api_serial_no`    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '短信 API 发送返回的序号',
    `receive_status`   tinyint                                 NOT NULL DEFAULT '0' COMMENT '接收状态',
    `receive_time`     datetime                                         DEFAULT NULL COMMENT '接收时间',
    `api_receive_code` varchar(63) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT 'API 接收结果的编码',
    `api_receive_msg`  varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'API 接收结果的说明',
    `creator`          varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`      datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`      datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信日志'

CREATE TABLE `system_sms_template`
(
    `id`              bigint                                  NOT NULL AUTO_INCREMENT COMMENT '编号',
    `lx` tinyint                                 NOT NULL COMMENT '模板类型',
    `zt` tinyint                                 NOT NULL COMMENT '开启状态',
    `code`            varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板编码',
    `name`            varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板名称',
    `nr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板内容',
    `params`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数数组',
    `remark`          varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `api_template_id` varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信 API 的模板编号',
    `channel_id`      bigint                                  NOT NULL COMMENT '短信渠道编号',
    `channel_code`    varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信渠道编码',
    `creator`         varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`         varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`         bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信模板'

CREATE TABLE `system_social_client`
(
    `id`            bigint                                   NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`          varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '应用名',
    `social_type`   tinyint                                  NOT NULL COMMENT '社交平台的类型',
    `user_type`     tinyint                                  NOT NULL COMMENT '用户类型',
    `client_id`     varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '客户端编号',
    `client_secret` varchar(2048) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
    `public_key`    varchar(2048) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'publicKey公钥',
    `agent_id`      varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '代理编号',
    `zt` tinyint NOT NULL COMMENT '状态',
    `creator`       varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`   datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`   datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`     bigint                                   NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='社交客户端表'

CREATE TABLE `system_social_user`
(
    `id`             bigint unsigned                          NOT NULL AUTO_INCREMENT COMMENT '主键(自增策略)',
    `lx` tinyint NOT NULL COMMENT '社交平台的类型',
    `openid`         varchar(32) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '社交 openid',
    `token`          varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '社交 token',
    `raw_token_info` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始 Token 数据，一般是 JSON 格式',
    `nickname`       varchar(32) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '用户昵称',
    `avatar`         varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '用户头像',
    `raw_user_info`  varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始用户数据，一般是 JSON 格式',
    `code`           varchar(256) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '最后一次的认证 code',
    `state`          varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '最后一次的认证 state',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint                                   NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='社交用户表'

CREATE TABLE `system_social_user_bind`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键(自增策略)',
    `yh_id` bigint NOT NULL COMMENT '用户编号',
    `user_type`      tinyint         NOT NULL COMMENT '用户类型',
    `social_type`    tinyint         NOT NULL COMMENT '社交平台的类型',
    `social_user_id` bigint          NOT NULL COMMENT '社交用户的编号',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time`    datetime        NOT NULL               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time`    datetime        NOT NULL               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)          NOT NULL               DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint          NOT NULL               DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='社交绑定表'

CREATE TABLE `system_tenant`
(
    `id`              bigint                                 NOT NULL AUTO_INCREMENT COMMENT '租户编号',
    `name`            varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '租户名',
    `contact_user_id` bigint                                          DEFAULT NULL COMMENT '联系人的用户编号',
    `contact_name`    varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系人',
    `contact_mobile`  varchar(500) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '联系手机',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '租户状态',
    `websites`        varchar(1024) COLLATE utf8mb4_unicode_ci        DEFAULT '' COMMENT '绑定域名数组',
    `package_id`      bigint                                 NOT NULL COMMENT '租户套餐编号',
    `expire_time`     datetime                               NOT NULL COMMENT '过期时间',
    `account_count`   int                                    NOT NULL COMMENT '账号数量',
    `creator`         varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`     datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`         varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time`     datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`         bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户表'

CREATE TABLE `system_tenant_package`
(
    `id`          bigint                                   NOT NULL AUTO_INCREMENT COMMENT '套餐编号',
    `name`        varchar(30) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '套餐名',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
    `remark`      varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '备注',
    `menu_ids`    varchar(4096) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关联的菜单编号',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='租户套餐表'

CREATE TABLE `system_user_post`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `yh_id` bigint NOT NULL DEFAULT '0' COMMENT '用户ID',
    `post_id`     bigint   NOT NULL                      DEFAULT '0' COMMENT '岗位ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)   NOT NULL                      DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint   NOT NULL                      DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户岗位表'

CREATE TABLE `system_user_role`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `yh_id` bigint NOT NULL COMMENT '用户ID',
    `role_id`     bigint NOT NULL COMMENT '角色ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint NOT NULL                        DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户和角色关联表'

CREATE TABLE `system_users`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户昵称',
    `remark`      varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `dept_id`     bigint                                           DEFAULT NULL COMMENT '部门ID',
    `post_ids`    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '岗位编号数组',
    `email`       varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '用户邮箱',
    `mobile`      varchar(11) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '手机号码',
    `sex`         tinyint                                          DEFAULT '0' COMMENT '用户性别',
    `avatar`      varchar(512) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '头像地址',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `login_ip`    varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime                                         DEFAULT NULL COMMENT '最后登录时间',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户信息表'

INSERT INTO `system_tenant` (id, name, contact_user_id, contact_name, contact_mobile, status, websites, package_id, expire_time, account_count, creator, create_time, updater,
                             update_time, deleted)
VALUES (1, '陇工惠经费通', null, '陇工惠经费通', '00000000000', 0, 'localhost,127.0.0.1', 0, '2099-02-19 17:14:16', 9999, '1', '2021-01-05 17:03:47', '1', '2025-12-16 15:02:30',
        false);

CREATE TABLE `infra_api_access_log`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `trace_id`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `yh_id` bigint NOT NULL DEFAULT '0' COMMENT '用户编号',
    `user_type`        tinyint                                                       NOT NULL DEFAULT '0' COMMENT '用户类型',
    `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '应用名',
    `request_method`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '请求方法名',
    `request_url`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求地址',
    `request_params`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
    `response_body`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '响应结果',
    `user_ip`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户 IP',
    `user_agent`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '浏览器 UA',
    `operate_module`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '操作模块',
    `operate_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '操作名',
    `operate_type`     tinyint                                                                DEFAULT '0' COMMENT '操作分类',
    `begin_time`       datetime                                                      NOT NULL COMMENT '开始请求时间',
    `end_time`         datetime                                                      NOT NULL COMMENT '结束请求时间',
    `duration`         int                                                           NOT NULL COMMENT '执行时长',
    `result_code`      int                                                           NOT NULL DEFAULT '0' COMMENT '结果码',
    `result_msg`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '结果提示',
    `creator`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`      datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`      datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`        bigint                                                        NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='API 访问日志表'

CREATE TABLE `infra_api_error_log`
(
    `id`                           bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `trace_id`                     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '链路追踪编号',
    `yh_id` bigint NOT NULL DEFAULT '0' COMMENT '用户编号',
    `user_type`                    tinyint                                                        NOT NULL DEFAULT '0' COMMENT '用户类型',
    `application_name`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '应用名',
    `request_method`               varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '请求方法名',
    `request_url`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '请求地址',
    `request_params`               varchar(8000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求参数',
    `user_ip`                      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '用户 IP',
    `user_agent`                   varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '浏览器 UA',
    `exception_time`               datetime                                                       NOT NULL COMMENT '异常发生时间',
    `exception_name`               varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '异常名',
    `exception_message`            text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NOT NULL COMMENT '异常导致的消息',
    `exception_root_cause_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NOT NULL COMMENT '异常导致的根消息',
    `exception_stack_trace`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NOT NULL COMMENT '异常的栈轨迹',
    `exception_class_name`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '异常发生的类全名',
    `exception_file_name`          varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '异常发生的类文件',
    `exception_method_name`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '异常发生的方法名',
    `exception_line_number`        int                                                            NOT NULL COMMENT '异常发生的方法所在行',
    `process_status`               tinyint                                                        NOT NULL COMMENT '处理状态',
    `clsj`  datetime        DEFAULT NULL COMMENT '处理时间',
    `process_user_id`              int                                                                     DEFAULT '0' COMMENT '处理用户编号',
    `creator`                      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`                  datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`                  datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                      bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`                    bigint                                                         NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统异常日志'

CREATE TABLE `infra_codegen_column`
(
    `id`                       bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`                 bigint                                                        NOT NULL COMMENT '表编号',
    `column_name`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字段名',
    `data_type`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字段类型',
    `column_comment`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字段描述',
    `nullable`                 bit(1)                                                        NOT NULL COMMENT '是否允许为空',
    `primary_key`              bit(1)                                                        NOT NULL COMMENT '是否主键',
    `ordinal_position`         int                                                           NOT NULL COMMENT '排序',
    `java_type`                varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'Java 属性类型',
    `java_field`               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT 'Java 属性名',
    `dict_type`                varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '字典类型',
    `example`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '数据示例',
    `create_operation`         bit(1)                                                        NOT NULL COMMENT '是否为 Create 创建操作的字段',
    `update_operation`         bit(1)                                                        NOT NULL COMMENT '是否为 Update 更新操作的字段',
    `list_operation`           bit(1)                                                        NOT NULL COMMENT '是否为 List 查询操作的字段',
    `list_operation_condition` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '=' COMMENT 'List 查询操作的条件类型',
    `list_operation_result`    bit(1)                                                        NOT NULL COMMENT '是否为 List 查询操作的返回字段',
    `html_type`                varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '显示类型',
    `creator`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`              datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`              datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                  bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='代码生成表字段定义'

CREATE TABLE `infra_codegen_table`
(
    `id`                    bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `data_source_config_id` bigint                                                        NOT NULL COMMENT '数据源配置的编号',
    `scene`                 tinyint                                                       NOT NULL DEFAULT '1' COMMENT '生成场景',
    `table_name`            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '表名称',
    `table_comment`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '表描述',
    `remark`                varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `module_name`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模块名',
    `business_name`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '业务名',
    `class_name`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '类名称',
    `class_comment`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '类描述',
    `author`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '作者',
    `template_type`         tinyint                                                       NOT NULL DEFAULT '1' COMMENT '模板类型',
    `front_type`            tinyint                                                       NOT NULL COMMENT '前端类型',
    `parent_menu_id`        bigint                                                                 DEFAULT NULL COMMENT '父菜单编号',
    `master_table_id`       bigint                                                                 DEFAULT NULL COMMENT '主表的编号',
    `sub_join_column_id`    bigint                                                                 DEFAULT NULL COMMENT '子表关联主表的字段编号',
    `sub_join_many`         bit(1)                                                                 DEFAULT NULL COMMENT '主表与子表是否一对多',
    `tree_parent_column_id` bigint                                                                 DEFAULT NULL COMMENT '树表的父字段编号',
    `tree_name_column_id`   bigint                                                                 DEFAULT NULL COMMENT '树表的名字字段编号',
    `creator`               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`           datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`           datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`               bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='代码生成表定义'

CREATE TABLE `infra_config`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `category`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '参数分组',
    `lx` tinyint NOT NULL COMMENT '参数类型',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数名称',
    `config_key`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数键名',
    `value`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数键值',
    `visible`     bit(1)                                                        NOT NULL COMMENT '是否可见',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='参数配置表'

CREATE TABLE `infra_data_source_config`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '主键编号',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '参数名称',
    `url`         varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '数据源连接',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '密码',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='数据源配置表'

CREATE TABLE `infra_file`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '文件编号',
    `config_id`   bigint                                                                  DEFAULT NULL COMMENT '配置编号',
    `name`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '文件名',
    `path`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '文件路径',
    `url`         varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件 URL',
    `lx` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
    `size`        int                                                            NOT NULL COMMENT '文件大小',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文件表'

CREATE TABLE `infra_file_config`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '配置名',
    `storage`     tinyint                                                        NOT NULL COMMENT '存储器',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '备注',
    `master`      bit(1)                                                         NOT NULL COMMENT '是否为主配置',
    `config`      varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储配置',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文件配置表'

CREATE TABLE `infra_file_content`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `config_id`   bigint                                                        NOT NULL COMMENT '配置编号',
    `path`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
    `nr` mediumblob NOT NULL COMMENT '文件内容',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文件表'

CREATE TABLE `infra_job`
(
    `id`              bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '任务编号',
    `name`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
    `zt` tinyint NOT NULL COMMENT '任务状态',
    `handler_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '处理器的名字',
    `handler_param`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '处理器的参数',
    `cron_expression` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'CRON 表达式',
    `retry_count`     int                                                          NOT NULL DEFAULT '0' COMMENT '重试次数',
    `retry_interval`  int                                                          NOT NULL DEFAULT '0' COMMENT '重试间隔',
    `monitor_timeout` int                                                          NOT NULL DEFAULT '0' COMMENT '监控超时时间',
    `creator`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time`     datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time`     datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`         bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='定时任务表'

CREATE TABLE `infra_job_log`
(
    `id`            bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '日志编号',
    `job_id`        bigint                                                       NOT NULL COMMENT '任务编号',
    `handler_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '处理器的名字',
    `handler_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '处理器的参数',
    `execute_index` tinyint                                                      NOT NULL DEFAULT '1' COMMENT '第几次执行',
    `begin_time`    datetime                                                     NOT NULL COMMENT '开始执行时间',
    `end_time`      datetime                                                              DEFAULT NULL COMMENT '结束执行时间',
    `duration`      int                                                                   DEFAULT NULL COMMENT '执行时长',
    `zt` tinyint NOT NULL COMMENT '任务状态',
    `result`        varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci        DEFAULT '' COMMENT '结果数据',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='定时任务日志表'

CREATE TABLE `gh_qx_dlzh`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT,
    `lxdh`        varchar(11) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '联系电话',
    `yhzh`        varchar(30) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '用户账号',
    `yhyx`        varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '用户邮箱',
    `shxydm`      varchar(20) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '社会信用代码',
    `password`    varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `yhxm`        varchar(30) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '用户姓名',
    `yhbz`        varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户备注',
    `yhxb`        tinyint                                          DEFAULT NULL COMMENT '用户性别',
    `txdz`        varchar(200) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '头像地址',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `login_ip`    varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '最后登录IP',
    `login_date`  datetime                                         DEFAULT NULL COMMENT '最后登录时间',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint                                  NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `gh_qx_dlzh_lxdh_uindex` (`lxdh`),
    UNIQUE KEY `gh_qx_dlzh_shxydm_uindex` (`shxydm`),
    UNIQUE KEY `gh_qx_dlzh_yhyx_uindex` (`yhyx`),
    UNIQUE KEY `gh_qx_dlzh_yhzh_uindex` (`yhzh`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1001
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='登录账号'

CREATE TABLE `gh_qx_zhwh`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `apply_no`      varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '申请编号',
    `dlzh_id`       bigint                                                        NOT NULL COMMENT '登录账号 ID',
    `dept_id`       bigint                                                                 DEFAULT NULL COMMENT '主管工会部门 ID',
    `djxh`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '登记序号',
    `shxydm`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '社会信用代码',
    `dwmc`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '单位名称',
    `old_jcghzh`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '原基层工会账号',
    `old_jcghhm`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '原基层工会户名',
    `old_jcghhh`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '原基层工会行号',
    `old_jcghyh`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '原基层工会开户行',
    `new_jcghzh`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '新基层工会账号',
    `new_jcghhm`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '新基层工会户名',
    `new_jcghhh`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '新基层工会行号',
    `new_jcghyh`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '新基层工会开户行',
    `apply_reason`  varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '申请原因',
    `materials`     json                                                                   DEFAULT NULL COMMENT '材料附件',
    `zt`            tinyint                                                       NOT NULL DEFAULT '0' COMMENT '状态 0待审 1通过 2驳回 3撤回',
    `audit_remark`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '审核意见',
    `audit_user_id` bigint                                                                 DEFAULT NULL COMMENT '审核人',
    `audit_time`    datetime                                                               DEFAULT NULL COMMENT '审核时间',
    `sync_status`   tinyint                                                       NOT NULL DEFAULT '0' COMMENT '同步状态 0未触发 1已触发',
    `sync_message`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '同步说明',
    `sync_time`     datetime                                                               DEFAULT NULL COMMENT '同步时间',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_gh_qx_zhwh_status` (`zt`) USING BTREE,
    KEY `idx_gh_qx_zhwh_dlzh` (`dlzh_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='账户维护申请'

CREATE TABLE `gh_qx_sfxx`
(
    `id`          bigint                                 NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `dlzh_id`     bigint                                 NOT NULL COMMENT '登录账号ID',
    `djxh`        varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登记序号',
    `sflx`        varchar(2) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '身份类型（01:法定代表人,02:财务负责人）',
    `ghlx`        varchar(2) COLLATE utf8mb4_unicode_ci                        DEFAULT NULL COMMENT '工会类型（01:基层工会,02:缴费单位,03:联合工会,04:集团工会,05:产业系统工会,06:县区总工会,07:市总工会,08:省总工会）',
    `qxlx`        varchar(2) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '权限类型（01:管理员,02:一般人）',
    `sqyy`        varchar(50) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '授权原因',
    `jjyy`        varchar(50) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '拒绝原因',
    `jbyy`        varchar(50) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '解绑原因',
    `dept_id`     bigint                                                       DEFAULT NULL COMMENT '部门编号',
    `zt` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0:待审核 1:已审核',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 NOT NULL              DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='身份信息'

CREATE TABLE `gh_nrgl_bbfb`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `version`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '版本号',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `nr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0:草稿 1:已发布',
    `dept_id`     bigint                                                                 DEFAULT NULL COMMENT '发布部门编号',
    `fbsj`        date                                                                   DEFAULT NULL COMMENT '发布时间',
    `read_count`  int                                                           NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='版本发布'

CREATE TABLE `gh_nrgl_bszn`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `parent_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '父编号',
    `sxmc`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '事项名称',
    `nr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
    `sort`        int                                                           NOT NULL DEFAULT '0' COMMENT '排序',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '状态 (0:未审核, 1:已审核, 2:已发布, 3:已过期, 4:已下架)',
    `dept_id`     bigint                                                                 DEFAULT NULL COMMENT '发布部门编号',
    `kjfw`        tinyint                                                       NOT NULL DEFAULT '1' COMMENT '可见范围 (1:完全可见, 2:下级可见, 3:本级可见)',
    `blbm`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '办理部门',
    `zxdh`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '咨询电话',
    `fdsx`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '法定时限',
    `cnsx`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '承诺时限',
    `sfbz`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '收费标准',
    `ywfl`        tinyint                                                                DEFAULT NULL COMMENT '业务分类 (1:缴费管理, 2:返拨管理, 3:退费管理, 4:缓交管理)',
    `blzt`        tinyint                                                                DEFAULT NULL COMMENT '办理主体 (1:全总工会, 2:省总工会, 3:基层工会, 4:缴费单位)',
    `xjyy`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci               DEFAULT NULL COMMENT '下架原因 (1:已失效政策, 2:新政策替代)',
    `fbsj`        date                                                                   DEFAULT NULL COMMENT '发布时间',
    `read_count`  int                                                           NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='办事指南'

CREATE TABLE `gh_nrgl_cjwt`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `parent_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '父编号',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `nr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
    `sort`        int                                                           NOT NULL DEFAULT '0' COMMENT '排序',
    `zt` tinyint NOT NULL DEFAULT '0' COMMENT '状态 (0:未审核, 1:已审核, 2:已发布, 3:已过期, 4:已下架)',
    `dept_id`     bigint                                                                 DEFAULT NULL COMMENT '发布部门编号',
    `kjfw`        tinyint                                                       NOT NULL DEFAULT '1' COMMENT '可见范围 (1:完全可见, 2:下级可见, 3:本级可见)',
    `wtfl`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '问题分类',
    `xjyy`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci               DEFAULT NULL COMMENT '下架原因 (1:已失效政策, 2:新政策替代)',
    `read_count`  int                                                           NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='常见问题'

CREATE TABLE `gh_nrgl_zcjd`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `parent_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '父编号',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `nr`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
    `sort`        int                                                           NOT NULL DEFAULT '0' COMMENT '排序',
    `zt`   tinyint NOT NULL DEFAULT '0' COMMENT '状态 (0:未审核, 1:已审核, 2:已发布, 3:已过期, 4:已下架)',
    `dept_id`     bigint                                                                 DEFAULT NULL COMMENT '发布部门编号',
    `kjfw`        tinyint                                                       NOT NULL DEFAULT '1' COMMENT '可见范围 (1:完全可见, 2:下级可见, 3:本级可见)',
    `fjlj` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '附件路径，逗号分隔',
    `fbbm`        tinyint                                                                DEFAULT NULL COMMENT '发布部门 (0:全总, 1:省总, 2:市州)',
    `xjyy`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci               DEFAULT NULL COMMENT '下架原因 (1:已失效政策, 2:新政策替代)',
    `fbrq`        date                                                                   DEFAULT NULL COMMENT '原文件发布日期',
    `glzc_id`     bigint                                                                 DEFAULT NULL COMMENT '关联政策ID',
    `read_count`  int                                                           NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='政策解读'

CREATE TABLE `gh_nrgl_zcwj`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `title`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `summary`          varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '摘要',
    `nr`               longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '文件内容',
    `sort`             int                                                           NOT NULL DEFAULT '0' COMMENT '排序',
    `zt`               tinyint                                                       NOT NULL DEFAULT '0' COMMENT '状态(0:草稿,2:已发布,4:已下架)',
    `dept_id`          bigint                                                                 DEFAULT NULL COMMENT '发布部门编号',
    `kjfw`             tinyint                                                       NOT NULL DEFAULT '1' COMMENT '可见范围(1:完全可见,2:下级可见,3:本级可见)',
    `version_no`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '版本号',
    `tags`             varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '标签',
    `search_keywords`  varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '搜索关键词',
    `attachment_urls`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '附件地址，逗号分隔',
    `fbbm`             tinyint                                                                DEFAULT NULL COMMENT '发布部门(0:全总,1:省总,2:市州)',
    `fbrq`             date                                                                   DEFAULT NULL COMMENT '发布日期',
    `off_shelf_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '下架原因',
    `read_count`       int                                                           NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`          bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`      datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`      datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_gh_nrgl_zcwj_status` (`zt`) USING BTREE,
    KEY `idx_gh_nrgl_zcwj_dept` (`dept_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='政策文件'

CREATE TABLE `gh_xxzx_tzgg`
(
    `id`          bigint                                 NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `title`       varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
    `nr` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容',
    `lx` tinyint                         NOT NULL COMMENT '公告类型（1通知 2公告）',
    `zt` tinyint                         NOT NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `dept_id`     bigint                                          DEFAULT NULL,
    `read_count`  int                                    NOT NULL DEFAULT '0' COMMENT '阅读量',
    `deleted`     bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `tenant_id`   bigint                                 NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='通知公告表'

CREATE TABLE `gh_xxzx_xxtx_message`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '消息主键',
    `title`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息标题',
    `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '消息内容',
    `sfznx`   tinyint NOT NULL                                              DEFAULT '1' COMMENT '是否发送站内信',
    `sfdx`    tinyint NOT NULL                                              DEFAULT '0' COMMENT '是否发送短信',
    `dxnr`    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '短信内容',
    `message_type` tinyint                                                       NOT NULL DEFAULT '0' COMMENT '消息类型（0：系统消息，1：业务消息）',
    `sender_id`    bigint                                                        NOT NULL COMMENT '发送者ID',
    `sender_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '发送者姓名',
    `dept_ids`     json                                                                   DEFAULT NULL COMMENT '部门ID列表',
    `user_ids`     json                                                                   DEFAULT NULL COMMENT '用户ID列表',
    `send_time`    datetime                                                               DEFAULT NULL COMMENT '发送时间',
    `status`  tinyint NOT NULL                                              DEFAULT '0' COMMENT '消息状态（0：草稿，1：已发送，2：已撤回）',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`    bigint                                                        NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_send_time` (`send_time`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息表'

CREATE TABLE `gh_xxzx_xxtx_message_receiver`
(
    `id`            bigint   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `message_id`    bigint   NOT NULL COMMENT '消息ID',
    `receiver_type` tinyint  NOT NULL COMMENT '接收者类型（0：部门，1：用户）',
    `receiver_id`   bigint   NOT NULL COMMENT '接收者ID（部门ID或用户ID）',
    `read_status`   tinyint  NOT NULL                                            DEFAULT '0' COMMENT '阅读状态（0：未读，1：已读）',
    `read_time`     datetime                                                     DEFAULT NULL COMMENT '阅读时间',
    `dxrzid` bigint                                                        DEFAULT NULL COMMENT '短信日志ID',
    `dxzt`   tinyint                                                       DEFAULT NULL COMMENT '短信状态',
    `dxbz`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '短信备注',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time`   datetime NOT NULL                                            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time`   datetime NOT NULL                                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)   NOT NULL                                            DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`     bigint   NOT NULL                                            DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_message_id` (`message_id`) USING BTREE,
    KEY `idx_receiver` (`receiver_type`, `receiver_id`) USING BTREE,
    KEY `idx_read_status` (`read_status`) USING BTREE,
    CONSTRAINT `fk_gh_xxzx_xxtx_message_receiver_message` FOREIGN KEY (`message_id`) REFERENCES `gh_xxzx_xxtx_message` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息接收表'

CREATE TABLE `gh_marker_info`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `xzqh_dm`     bigint unsigned                                               NOT NULL COMMENT '行政区划代码',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工会名称',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '联系电话',
    `address`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '详细地址',
    `lng`         decimal(10, 6)                                                NOT NULL COMMENT '经度（保留6位小数，满足高德地图精度）',
    `lat`         decimal(10, 6)                                                NOT NULL COMMENT '纬度（保留6位小数，满足高德地图精度）',
    `remark`      varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT '' COMMENT '备注信息',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `jobtime`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '工作时间',
    `grade`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '级别',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 102
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment ='高德地图标注点信息表'

CREATE TABLE `gh_dm_hj_bq`
(
    `id`          varchar(20) NOT NULL COMMENT '归类管理代码',
    `bq_mc`       varchar(20)          DEFAULT NULL COMMENT '标签归类名称',
    `dept_id`     bigint               DEFAULT NULL COMMENT '部门ID',
    `deleted`     bit(1)               DEFAULT b'0',
    `creator`     varchar(64)          DEFAULT NULL COMMENT '创建者',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)          DEFAULT NULL COMMENT '更新者',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='标签代码'

CREATE TABLE `gh_hj_bqxx`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '归类管理代码',
    `bq_id`       varchar(20)       DEFAULT NULL COMMENT '标签ID',
    `djxh`        varchar(20)       DEFAULT NULL COMMENT '登记序号',
    `yxqq`        date              DEFAULT NULL,
    `yxqz`        date              DEFAULT NULL,
    `deleted`     bit(1)            DEFAULT b'0',
    `creator`     varchar(64)       DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)       DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 66
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='户籍标签'



CREATE TABLE `gh_hj_jcxx`
(
    `dept_id`     char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      NOT NULL COMMENT '工会机构代码',
    `hyghbz`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '行业工会标志',
    `djxh`        char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     NOT NULL COMMENT '登记序号',
    `shxydm`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '社会信用代码',
    `nsrmc`       varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '纳税人名称',
    `nsrjc`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '纳税人简称',
    `zgswj_dm`    char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '主管税务机关代码',
    `zgswjmc`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主管税务机关名称',
    `zgswskfj_dm` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '科所分局代码',
    `zgswskfjmc`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科所分局名称',
    `ssgly_dm`    char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '税管员代码',
    `ssglyxm`     varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '税管员姓名',
    `zzjglx_dm`   char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '组织机构类型代码',
    `zzjglxmc`    varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '组织机构类型名称',
    `hy_dm`       char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '行业代码',
    `hymc`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '行业名称',
    `djzclx_dm`   char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '登记注册类型代码',
    `djzclxmc`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '登记注册类型名称',
    `dwlsgx_dm`   char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '单位隶属关系代码',
    `dwlsgxmc`    varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '单位隶属关系名称',
    `zgrs`        decimal(16, 4)                                                DEFAULT NULL COMMENT '总共人数',
    `nsrzt_dm`    char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '纳税人状态代码',
    `nsrztmc`     varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '纳税人状态名称',
    `fzcrq`       datetime(6)                                                   DEFAULT NULL COMMENT '日期',
    `zxrq`        datetime(6)                                                   DEFAULT NULL COMMENT '日期',
    `zcdz`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册地址',
    `yzbm`        char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '邮政编码',
    `lxr`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '联系人',
    `lxdh`        char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '联系电话',
    `ghlb_dm`     char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '工会类别代码',
    `xtlb_dm`     char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '系统类别代码',
    `hjfl1_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '户籍分类',
    `hjfl2_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '2',
    `hjfl3_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '3',
    `hjfl4_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '4',
    `hjfl5_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '5',
    `hjfl6_dm`    varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '2023小额缴费工会组织标志',
    `hjfl7_dm`    datetime                                                      DEFAULT NULL COMMENT '小微上报时间',
    `hjfl8_dm`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT 'N' COMMENT '是否已建会缴纳筹备金',
    `hjfl9_dm`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT 'N' COMMENT '小微企业标志',
    `hjfl10_dm`   varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '9',
    `sdghjg_dm`   char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      DEFAULT NULL COMMENT '属地工会机构代码',
    `clghbj`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci      NOT NULL COMMENT '成立工会标志',
    `clghrq`      datetime(6)                                                   DEFAULT NULL COMMENT '成立工会日期',
    `jcghdm`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '基层工会代码',
    `jcghmc`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '基层工会名称',
    `jcghzh`      varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '基层工会账户',
    `jcghhm`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '基层工会户名',
    `jcghhh`      char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL COMMENT '基层工会行号',
    `jcghyh`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '基层工会银行',
    `bz`          text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '备注',
    `jym`         char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci    DEFAULT NULL COMMENT '校验码',
    `nsrsbh`      varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '纳税人识别号',
    `creator`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `updater`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '修改人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `wjlj` varchar(500) DEFAULT NULL COMMENT '文件地址',
    `jdxz_dm`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '街道乡镇代码',
    `sjtb_sj`     datetime                                                      DEFAULT NULL COMMENT '税务数据同步时间',
    `deleted`     bit(1)                                                        DEFAULT b'0',
    PRIMARY KEY (`DJXH`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='基层账户空需维护对象'

CREATE TABLE `gh_wf_jfhzjnsq`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `shxydm`      varchar(18)  NOT NULL COMMENT '社会信用代码',
    `dwmc`        varchar(255) NOT NULL COMMENT '单位名称',
    `zgsbm`       varchar(255)          DEFAULT NULL COMMENT '主管税务部门',
    `dwdz`        varchar(500)          DEFAULT NULL COMMENT '单位地址',
    `ghfrdjzh`    varchar(30)           DEFAULT NULL COMMENT '工会法人登记证号',
    `ghmc`        varchar(255)          DEFAULT NULL COMMENT '工会名称',
    `zgzs`        int                   DEFAULT '0' COMMENT '职工总数',
    `ghyhs`       int                   DEFAULT '0' COMMENT '工会会员数',
    `ghfzr`       varchar(50)           DEFAULT NULL COMMENT '工会负责人',
    `lxdh`        varchar(20)           DEFAULT NULL COMMENT '联系电话',
    `ghzh`        varchar(50)           DEFAULT NULL COMMENT '工会账户',
    `khyhmc`      varchar(100)          DEFAULT NULL COMMENT '开户银行名称',
    `ghhm`        varchar(255)          DEFAULT NULL COMMENT '工会账户户名',
    `khyhwdm`     varchar(20)           DEFAULT NULL COMMENT '开户银行网点代码',
    `hzjnyy`      text COMMENT '汇总缴纳原因',
    `dwfzr`       varchar(50)           DEFAULT NULL COMMENT '单位负责人',
    `jbr`         varchar(50)           DEFAULT NULL COMMENT '经办人',
    `jbrdh`       varchar(20)           DEFAULT NULL COMMENT '经办人电话',
    `sqrq`        date                  DEFAULT NULL COMMENT '申请日期',
    `fzjgzs`      int                   DEFAULT '0' COMMENT '分支机构总数',
    `zgghspyj`    text COMMENT '主管工会审核意见',
    `zgghfzr`     varchar(50)           DEFAULT NULL COMMENT '主管工会负责人',
    `zgghjbr`     varchar(50)           DEFAULT NULL COMMENT '主管工会经办人',
    `zgghjbrdh`   varchar(20)           DEFAULT NULL COMMENT '主管工会经办人电话',
    `zgghsprq`    date                  DEFAULT NULL COMMENT '主管工会审核日期',
    `sghspyj`     text COMMENT '省总工会审核意见',
    `sghfzr`      varchar(50)           DEFAULT NULL COMMENT '省总工会负责人',
    `sghsprq`     date                  DEFAULT NULL COMMENT '省总工会审核日期',
    `lcsl_id`     varchar(64)           DEFAULT NULL COMMENT '流程实例ID',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_wf_jfhzjnsq_shxydm` (`shxydm`),
    KEY `idx_gh_wf_jfhzjnsq_lcsl_id` (`lcsl_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工会经费汇总缴纳申请';

CREATE TABLE `gh_wf_jfhzjnsqmx`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `jfhzjnsq_id` bigint       NOT NULL COMMENT '汇总缴纳申请ID',
    `shxydm`      varchar(18)  NOT NULL COMMENT '社会信用代码',
    `dwmc`        varchar(255) NOT NULL COMMENT '单位名称',
    `zgsbm`       varchar(255)          DEFAULT NULL COMMENT '主管税务部门',
    `zgzs`        int                   DEFAULT '0' COMMENT '职工总数',
    `ygzze`       decimal(15, 2)        DEFAULT '0.00' COMMENT '月工资总额',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_wf_jfhzjnsqmx_jfhzjnsq_id` (`jfhzjnsq_id`),
    KEY `idx_gh_wf_jfhzjnsqmx_shxydm` (`shxydm`),
    CONSTRAINT `fk_gh_wf_jfhzjnsqmx_jfhzjnsq_id`
        FOREIGN KEY (`jfhzjnsq_id`) REFERENCES `gh_wf_jfhzjnsq` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工会经费汇总缴纳申请明细';

CREATE TABLE `gh_wf_jfhjsq`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `shxydm`      varchar(32)  NOT NULL COMMENT '社会信用代码',
    `dwmc`        varchar(255) NOT NULL COMMENT '单位名称',
    `lxr`         varchar(255)          DEFAULT NULL COMMENT '联系人',
    `lxdh`        varchar(100)          DEFAULT NULL COMMENT '联系电话',
    `syfl`        decimal(5, 2)         DEFAULT NULL COMMENT '适用费率',
    `zgzs`        int                   DEFAULT NULL COMMENT '职工总数',
    `ygzze`       decimal(15, 2)        DEFAULT NULL COMMENT '月工资总额',
    `ybjje`       decimal(15, 2)        DEFAULT NULL COMMENT '月拨缴金额',
    `hjkssj`      varchar(20)           DEFAULT NULL COMMENT '缓缴开始年月',
    `hjjssj`      varchar(20)           DEFAULT NULL COMMENT '缓缴结束年月',
    `hjzys`       int                   DEFAULT NULL COMMENT '缓缴月数',
    `ljhjje`      decimal(15, 2)        DEFAULT NULL COMMENT '累计缓缴金额',
    `hjqksm`      text COMMENT '缓缴情况说明',
    `dwfzr`       varchar(50)           DEFAULT NULL COMMENT '单位负责人',
    `jbr`         varchar(50)           DEFAULT NULL COMMENT '经办人',
    `sqrq`        date                  DEFAULT NULL COMMENT '申请日期',
    `jcghyj`      text COMMENT '基层工会意见',
    `jcghfzr`     varchar(50)           DEFAULT NULL COMMENT '基层工会负责人',
    `jcghjbr`     varchar(50)           DEFAULT NULL COMMENT '基层工会经办人',
    `jcghgzrq`    date                  DEFAULT NULL COMMENT '基层工会盖章日期',
    `zgghspyj`    text COMMENT '主管工会审核意见',
    `zgghfzr`     varchar(50)           DEFAULT NULL COMMENT '主管工会负责人',
    `zgghjbr`     varchar(50)           DEFAULT NULL COMMENT '主管工会经办人',
    `zgghsprq`    date                  DEFAULT NULL COMMENT '主管工会审核日期',
    `zgghcwfzr`   varchar(50)           DEFAULT NULL COMMENT '主管工会财务负责人',
    `lcsl_id`     varchar(64)           DEFAULT NULL COMMENT '流程实例ID',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_gh_wf_jfhjsq_lcsl_id` (`lcsl_id`),
    KEY `idx_gh_wf_jfhjsq_shxydm` (`shxydm`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工会经费缓缴申请';

CREATE TABLE `gh_wf_tdfsq`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `shxydm`      varchar(50)  NOT NULL COMMENT '社会信用代码',
    `dwmc`        varchar(100) NOT NULL COMMENT '单位名称',
    `qksm`        text         NOT NULL COMMENT '情况说明',
    `dwfzr`       varchar(50)           DEFAULT NULL COMMENT '单位负责人',
    `jbr`         varchar(50)  NOT NULL COMMENT '经办人',
    `lxdh`        varchar(20)           DEFAULT NULL COMMENT '联系电话',
    `hm`          varchar(100) NOT NULL COMMENT '户名',
    `khyhmc`      varchar(100) NOT NULL COMMENT '开户行名称',
    `zh`          varchar(50)  NOT NULL COMMENT '账号',
    `khyhhh`      varchar(20)           DEFAULT NULL COMMENT '开户行行号',
    `sqrq`        date         NOT NULL COMMENT '申请日期',
    `zgghspyj`    varchar(500)          DEFAULT NULL COMMENT '主管工会审批意见',
    `zgghfzr`     varchar(50)           DEFAULT NULL COMMENT '主管工会负责人',
    `zgghjbr`     varchar(50)           DEFAULT NULL COMMENT '主管工会经办人',
    `zgghsprq`    date                  DEFAULT NULL COMMENT '主管工会审批日期',
    `sghspyj`     varchar(500)          DEFAULT NULL COMMENT '省总工会审批意见',
    `sghfzr`      varchar(50)           DEFAULT NULL COMMENT '省总工会负责人',
    `sghjbr`      varchar(50)           DEFAULT NULL COMMENT '省总工会经办人',
    `sghsprq`     date                  DEFAULT NULL COMMENT '省总工会审批日期',
    `thfs`        tinyint               DEFAULT NULL COMMENT '退还方式',
    `lcsl_id`     varchar(64)           DEFAULT NULL COMMENT '流程实例ID',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_wf_tdfsq_shxydm` (`shxydm`),
    KEY `idx_gh_wf_tdfsq_lcsl_id` (`lcsl_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工会经费退抵费申请';

CREATE TABLE `gh_wf_tdfsq_fj`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tdfsq_id`    bigint       NOT NULL COMMENT '退抵费申请ID',
    `fjlx`        varchar(20)  NOT NULL COMMENT '附件类型',
    `wjlj`        varchar(500) NOT NULL COMMENT '文件路径',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_wf_tdfsq_fj_tdfsq_id` (`tdfsq_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工会经费退抵费申请附件';

CREATE TABLE `gh_nrgl_wtfk`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `fkbh`        varchar(64)          DEFAULT NULL COMMENT '反馈编号',
    `yh_id`       bigint               DEFAULT NULL COMMENT '用户ID',
    `yhmc`        varchar(64) NOT NULL COMMENT '用户名',
    `lx`          varchar(32)          DEFAULT NULL COMMENT '反馈类型',
    `ptmc`        varchar(32)          DEFAULT NULL COMMENT '平台名称',
    `nr`          text        NOT NULL COMMENT '反馈内容',
    `zt`          tinyint              DEFAULT '1' COMMENT '处理状态',
    `clr_id`      bigint               DEFAULT NULL COMMENT '处理人ID',
    `lxdh`        varchar(20)          DEFAULT NULL COMMENT '联系电话',
    `lxyx`        varchar(50)          DEFAULT NULL COMMENT '联系邮箱',
    `clsj`        datetime             DEFAULT NULL COMMENT '处理时间',
    `clsm`        varchar(512)         DEFAULT NULL COMMENT '处理说明',
    `fj_list`     json                 DEFAULT NULL COMMENT '附件列表',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)          DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)          DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)      NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_nrgl_wtfk_fkbh` (`fkbh`),
    KEY `idx_gh_nrgl_wtfk_yh_id` (`yh_id`),
    KEY `idx_gh_nrgl_wtfk_zt` (`zt`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='问题反馈';

CREATE TABLE `gh_nrgl_wtfk_clmx`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `wtfk_id`     bigint   NOT NULL COMMENT '问题反馈ID',
    `clr_id`      bigint   NOT NULL COMMENT '处理人ID',
    `clrmc`       varchar(64)       DEFAULT NULL COMMENT '处理人名称',
    `clsm`        text     NOT NULL COMMENT '处理说明',
    `zt`          tinyint  NOT NULL COMMENT '处理后状态',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     varchar(64)       DEFAULT '' COMMENT '创建者',
    `updater`     varchar(64)       DEFAULT '' COMMENT '更新者',
    `deleted`     bit(1)   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_gh_nrgl_wtfk_clmx_wtfk_id` (`wtfk_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='问题反馈处理明细';



CREATE VIEW `v_system_dept` AS
with recursive `dept` as (select `system_dept`.`id`             AS `id`,
                                 `system_dept`.`name`           AS `name`,
                                 `system_dept`.`parent_id`      AS `parent_id`,
                                 `system_dept`.`sort`           AS `sort`,
                                 `system_dept`.`leader_user_id` AS `leader_user_id`,
                                 `system_dept`.`phone`          AS `phone`,
                                 `system_dept`.`email`          AS `email`,
                                 `system_dept`.`status`         AS `status`,
                                 `system_dept`.`creator`        AS `creator`,
                                 `system_dept`.`create_time`    AS `create_time`,
                                 `system_dept`.`updater`        AS `updater`,
                                 `system_dept`.`update_time`    AS `update_time`,
                                 `system_dept`.`deleted`        AS `deleted`,
                                 `system_dept`.`tenant_id`      AS `tenant_id`,
                                 `system_dept`.`id`             AS `root_id`,
                                 1                              AS `level`,
                                 0                              AS `is_leaf`
                          from `system_dept`
                          union all
                          select `d`.`id`             AS `id`,
                                 `d`.`name`           AS `name`,
                                 `d`.`parent_id`      AS `parent_id`,
                                 `d`.`sort`           AS `sort`,
                                 `d`.`leader_user_id` AS `leader_user_id`,
                                 `d`.`phone`          AS `phone`,
                                 `d`.`email`          AS `email`,
                                 `d`.`status`         AS `status`,
                                 `d`.`creator`        AS `creator`,
                                 `d`.`create_time`    AS `create_time`,
                                 `d`.`updater`        AS `updater`,
                                 `d`.`update_time`    AS `update_time`,
                                 `d`.`deleted`        AS `deleted`,
                                 `d`.`tenant_id`      AS `tenant_id`,
                                 `t`.`root_id`        AS `root_id`,
                                 (`t`.`level` + 1)    AS `t.level + 1`,
                                 1                    AS `is_leaf`
                          from (`system_dept` `d` join `dept` `t` on ((`d`.`parent_id` = `t`.`id`))))
select `dept`.`id`             AS `id`,
       `dept`.`name`           AS `name`,
       `dept`.`parent_id`      AS `parent_id`,
       `dept`.`sort`           AS `sort`,
       `dept`.`leader_user_id` AS `leader_user_id`,
       `dept`.`phone`          AS `phone`,
       `dept`.`email`          AS `email`,
       `dept`.`status`         AS `status`,
       `dept`.`creator`        AS `creator`,
       `dept`.`create_time`    AS `create_time`,
       `dept`.`updater`        AS `updater`,
       `dept`.`update_time`    AS `update_time`,
       `dept`.`deleted`        AS `deleted`,
       `dept`.`tenant_id`      AS `tenant_id`,
       `dept`.`root_id`        AS `root_id`,
       `dept`.`level`          AS `level`,
       `dept`.`is_leaf`        AS `is_leaf`
from `dept`

CREATE VIEW `v_system_dept_reverse` AS
with recursive `dept` as (select `system_dept`.`id`             AS `id`,
                                 `system_dept`.`name`           AS `name`,
                                 `system_dept`.`parent_id`      AS `parent_id`,
                                 `system_dept`.`sort`           AS `sort`,
                                 `system_dept`.`leader_user_id` AS `leader_user_id`,
                                 `system_dept`.`phone`          AS `phone`,
                                 `system_dept`.`email`          AS `email`,
                                 `system_dept`.`zt` AS `zt`,
                                 `system_dept`.`creator`        AS `creator`,
                                 `system_dept`.`create_time`    AS `create_time`,
                                 `system_dept`.`updater`        AS `updater`,
                                 `system_dept`.`update_time`    AS `update_time`,
                                 `system_dept`.`deleted`        AS `deleted`,
                                 `system_dept`.`tenant_id`      AS `tenant_id`,
                                 `system_dept`.`id`             AS `root_id`,
                                 1                              AS `level`,
                                 0                              AS `is_leaf`
                          from `system_dept`
                          union all
                          select `d`.`id`             AS `id`,
                                 `d`.`name`           AS `name`,
                                 `d`.`parent_id`      AS `parent_id`,
                                 `d`.`sort`           AS `sort`,
                                 `d`.`leader_user_id` AS `leader_user_id`,
                                 `d`.`phone`          AS `phone`,
                                 `d`.`email`          AS `email`,
                                 `d`.`zt` AS `zt`,
                                 `d`.`creator`        AS `creator`,
                                 `d`.`create_time`    AS `create_time`,
                                 `d`.`updater`        AS `updater`,
                                 `d`.`update_time`    AS `update_time`,
                                 `d`.`deleted`        AS `deleted`,
                                 `d`.`tenant_id`      AS `tenant_id`,
                                 `t`.`root_id`        AS `root_id`,
                                 (`t`.`level` + 1)    AS `t.level + 1`,
                                 1                    AS `is_leaf`
                          from (`system_dept` `d` join `dept` `t` on ((`d`.`id` = `t`.`parent_id`))))
select `dept`.`id`             AS `id`,
       `dept`.`name`           AS `name`,
       `dept`.`parent_id`      AS `parent_id`,
       `dept`.`sort`           AS `sort`,
       `dept`.`leader_user_id` AS `leader_user_id`,
       `dept`.`phone`          AS `phone`,
       `dept`.`email`          AS `email`,
       `dept`.`zt` AS `zt`,
       `dept`.`creator`        AS `creator`,
       `dept`.`create_time`    AS `create_time`,
       `dept`.`updater`        AS `updater`,
       `dept`.`update_time`    AS `update_time`,
       `dept`.`deleted`        AS `deleted`,
       `dept`.`tenant_id`      AS `tenant_id`,
       `dept`.`root_id`        AS `root_id`,
       `dept`.`level`          AS `level`,
       `dept`.`is_leaf`        AS `is_leaf`
from `dept`

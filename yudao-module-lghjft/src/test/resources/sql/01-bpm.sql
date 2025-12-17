CREATE TABLE `gh_wf_sq_tfsq`
(
    `id`                  bigint   NOT NULL AUTO_INCREMENT COMMENT '退费申请ID',
    `tfsqmx_id`           bigint   NOT NULL COMMENT '退费申请明细ID',
    `sqtflx_dm`           tinyint  NOT NULL COMMENT '申请退费类型代码',
    `process_instance_id` varchar(64)       DEFAULT NULL COMMENT '流程实例的编号',
    `status`              tinyint  NOT NULL COMMENT '审批结果',
    `creator`             varchar(64) COMMENT '创建者',
    `create_time`         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`             varchar(64) COMMENT '更新者',
    `update_time`         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) comment ='申请-退费申请';
CREATE TABLE `gh_wf_sq_tfsqmx`
(
    `id`          bigint         NOT NULL COMMENT '退费申请明细ID',
    `xh`          bigint         not null comment '退费申请明细序号',
    `spuuid`      varchar(32)    not null comment '税票UUID',
    `rkje`        decimal(38, 2) not null comment '入库金额',
    `tfsq_je`     decimal(38, 2) not null comment '退费审批金额',
    `xybz`        tinyint        NOT NULL COMMENT '有效标志',
    `creator`     varchar(64) COMMENT '创建者',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COMMENT '更新者',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`, `xh`) USING BTREE
) comment ='申请-退费申请明细';
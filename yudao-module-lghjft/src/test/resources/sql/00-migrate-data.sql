# migrate data from gsghds.sys_dept to system_dept
select * from system_dept;
delete from system_dept where true;
insert into system_dept
select dept_id,
       dept_name,
       parent_id,
       order_num,
       (select r.user_id from gsghds.sys_user r where r.nick_name = leader and r.dept_id = t.dept_id),
       (case when length(t.phone) = 7 then t.phone
           when length(t.phone) = 11 then t.phone
           when substring_index(t.phone,'-',0)>0 then replace(t.phone,'-','')
           end) as phone,
       t.email,
       t.status,
       'admin'                                                          as creator,
       current_timestamp()                                              as create_time,
       null                                                             as updater,
       current_timestamp()                                              as update_time,
       cast(t.del_flag as UNSIGNED)                                     as deleted,
       1                                                                as tenant_id
from gsghds.sys_dept t;
# migrate data from gsghds.sys_post to system_posts
select * from system_post;
delete from system_post where true;
insert into system_post
select post_id,
       post_code,
       post_name,
       post_sort,
       status,
       remark,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       b'0',
       1
from gsghds.sys_post;
# migrate data from gsghds.sys_role to system_role
select * from system_role;
delete from system_role where true;
insert into system_role
select role_id,
       role_name,
       role_key,
       role_sort,
       data_scope,
       (select ifnull(cast(json_arrayagg(dept_id) as char),'') from gsghds.sys_role_dept t where t.role_id = r.role_id),
       status,
       1,
       remark,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       cast(if(del_flag = '2', '1', '0') as unsigned),
       1
from gsghds.sys_role r;
# migrate data from gsghds.sys_user to system_users
select * from system_users;
delete from system_users where true;
insert into system_users
select user_id,
       user_name,
       password,
       nick_name,
       remark,
       dept_id,
       (select cast(json_arrayagg(post_id) as char)
        from (select user_id, post_id
              from gsghds.sys_user_post
              order by user_id, post_id) t
        where t.user_id = r.user_id),
       r.email,
       r.phonenumber,
       r.sex,
       r.avatar,
       r.status,
       r.login_ip,
       r.login_date,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       cast(if(r.del_flag='2','1','0') as unsigned ),
       1
from gsghds.sys_user r;
# migrate date from gsghds.sys_user_role to system_user_role
select * from system_user_role;
delete from system_user_role where true;
insert into system_user_role
select row_number() over (),
       user_id,
       role_id,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       cast('0' as unsigned),
       1
from gsghds.sys_user_role;
# migrate data from gsghds.sys_role_menu to system_role_menu
select * from system_role_menu;
delete from system_role_menu where true;
insert into system_role_menu
select row_number() over (),
       1,
       id,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       b'0',
       1
from system_menu;
# migrate data from gsghds.sys_dept to gh_gy_dept_kzxx
drop table if exists gh_gy_dept_kzxx;
create table gh_gy_dept_kzxx
(
    dept_id     bigint not null comment '部门ID',
    hyghbz char(1) not null comment '行业工会标志，N（一般工会）Y（行业工会）S（产业系统）T（特殊工会）',
    xzjb char(1) not null comment '行政级别',
    xzqhfw varchar(114) null comment '行政区划范围',
    jym char(100) null comment '校验码',
    yhzh     varchar(40) null comment '银行账号',
    yhhm varchar(60) null comment '银行户名',
    yhhh char(12) null comment '银行行号',
    yhmc varchar(60) null comment '银行名称',
    creator varchar(64) null comment '创建者',
    create_time datetime default CURRENT_TIMESTAMP,
    updater varchar(64) null comment '更新者',
    update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) comment '公用-部门扩展信息';
delete from gh_gy_dept_kzxx where true;
insert into gh_gy_dept_kzxx
select dept_id,
       hyghbz,
       xzjb,
       xzqhfw,
       jym,
       yhzh,
       hm,
       hh,
       yhmc,
       'admin',
       current_timestamp(),
       null,
       current_timestamp()
from gsghds.sys_dept;
# migrate data from gsghds.sys_dept to gh_ls_yhxx
drop table if exists gh_ls_yhxx;
create table gh_ls_yhxx
(
    id          bigint primary key auto_increment,
    dept_id     bigint,
    yhzh     varchar(40) not null comment '银行账号',
    yhhm varchar(60) not null comment '银行户名',
    yhhh char(12) not null comment '银行行号',
    yhmc varchar(60) not null comment '银行名称',
    creator varchar(64) null comment '创建者',
    create_time datetime default CURRENT_TIMESTAMP,
    updater varchar(64) null comment '更新者',
    update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) comment '历史-银行信息';
delete from gh_ls_yhxx where true;
insert into gh_ls_yhxx(dept_id, yhzh, yhhm, yhhh, yhmc, creator, create_time, updater, update_time)
select dept_id,
       yhzh1,
       hm,
       hh,
       yhmc,
       'admin',
       current_timestamp(),
       null,
       current_timestamp()
from gsghds.sys_dept
where yhzh1 is not null
  and yhzh1 != '1'
union
select dept_id,
       yhzh2,
       hm,
       hh,
       yhmc,
       'admin',
       current_timestamp(),
       null,
       current_timestamp()
from gsghds.sys_dept
where yhzh2 is not null
  and yhzh2 != '1'
union
select dept_id,
       yhzh3,
       hm,
       hh,
       yhmc,
       'admin',
       current_timestamp(),
       null,
       current_timestamp()
from gsghds.sys_dept
where yhzh3 is not null
  and yhzh3 != '1';





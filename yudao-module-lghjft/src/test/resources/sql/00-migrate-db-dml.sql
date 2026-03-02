# migrate data from sys_post to system_posts
select *
from system_post;
delete
from system_post
where true;
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
from sys_post;


# migrate data from sys_dept to system_dept
select *
from system_dept;
delete
from system_dept
where true;
insert into system_dept
select dept_id,
       dept_name,
       parent_id,
       order_num,
       (select r.user_id from sys_user r where r.nick_name = leader and r.dept_id = t.dept_id),
       (case
            when length(t.phone) = 7 then t.phone
            when length(t.phone) = 11 then t.phone
            when substring_index(t.phone, '-', 0) > 0 then replace(t.phone, '-', '')
           end)                     as phone,
       t.email,
       t.status,
       'admin'                      as creator,
       current_timestamp()          as create_time,
       null                         as updater,
       current_timestamp()          as update_time,
       cast(t.del_flag as UNSIGNED) as deleted,
       1                            as tenant_id
from sys_dept t;


# migrate data from sys_role to system_role
select *
from system_role;
delete
from system_role
where true;
insert into system_role
select role_id,
       role_name,
       role_key,
       role_sort,
       data_scope,
       (select ifnull(cast(json_arrayagg(dept_id) as char), '') from sys_role_dept t where t.role_id = r.role_id),
       status,
       1,
       remark,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       cast(if(del_flag = '2', '1', '0') as unsigned),
       1
from sys_role r;

# migrate data from sys_user to system_users
select *
from system_users;
delete
from system_users
where true;
insert into system_users
select user_id,
       user_name,
       password,
       nick_name,
       remark,
       dept_id,
       (select cast(json_arrayagg(post_id) as char)
        from (select user_id, post_id
              from sys_user_post
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
       cast(if(r.del_flag = '2', '1', '0') as unsigned),
       1
from sys_user r;

# migrate data from sys_role_dept to sys
select *
from sys_role_dept;

# migrate date from sys_user_role to system_user_role
select *
from system_user_role;
delete
from system_user_role
where true;
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
from sys_user_role;

# migrate data from sys_menu to system_menu
select *
from system_menu;
delete
from system_menu
where true;
insert into system_menu
select 100000                 as id,
       '经费代收'             as name,
       ''                     as permission,
       1                      as type,
       1                      as sort,
       0                      as parent_id,
       '/jfds'                as path,
       null                   as icon,
       null                   as component,
       null                   as component_name,
       cast(b'1' as unsigned) as status,
       cast(b'1' as unsigned) as visible,
       cast(b'1' as unsigned) as keep_alive,
       cast(b'1' as unsigned) as always_show,
       'admin'                as creator,
       current_timestamp()    as create_time,
       'admin'                as updater,
       current_timestamp()    as update_time,
       cast(b'0' as unsigned) as deleted
union all
select 100000 + menu_id                                                                               as id,
       menu_name                                                                                      as name,
       if(length(perms) = 0 or perms is null, '', perms)                                              as permission,
       (case when menu_type = 'F' then 3 when menu_type = 'C' then 2 when menu_type = 'M' then 1 end) as type,
       order_num                                                                                      as sort,
       100000 + parent_id                                                                             as parent_id,
       if(path = '#' or length(path) = 0, null, path)                                                 as path,
       if(icon = '#', null, icon)                                                                     as icon,
       if(length(component) = 0, null, concat('http://lghjft.gsgh.org.cn:8899/jfds/', component))     as component,
       null                                                                                           as component_name,
       if(status = '0', cast(b'1' as unsigned), cast(b'0' as unsigned))                               as status,
       if(visible = '0', cast(b'1' as unsigned), cast(b'0' as unsigned))                              as visible,
       cast(b'1' as unsigned)                                                                         as keep_alive,
       cast(b'1' as unsigned)                                                                         as always_show,
       'admin'                                                                                        as creator,
       current_timestamp()                                                                            as create_time,
       'admin'                                                                                        as updater,
       current_timestamp()                                                                            as update_time,
       cast(b'0' as unsigned)                                                                         as deleted
from sys_menu;

INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200001, '系统管理', '', 1, 11, 0, '/system', 'ep:tools', null, null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2026-01-13 13:29:49', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200100, '用户管理', 'system:user:list', 2, 1, 200001, 'user', 'ep:avatar', 'system/user/index', 'SystemUser', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2025-03-15 21:30:41', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200101, '角色管理', '', 2, 2, 200001, 'role', 'ep:user', 'system/role/index', 'SystemRole', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2024-05-01 18:35:29',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200102, '菜单管理', '', 2, 3, 200001, 'menu', 'ep:menu', 'system/menu/index', 'SystemMenu', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2024-02-29 01:03:50',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200103, '部门管理', '', 2, 4, 200001, 'dept', 'fa:address-card', 'system/dept/index', 'SystemDept', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:06:28', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200104, '岗位管理', '', 2, 5, 200001, 'post', 'fa:address-book-o', 'system/post/index', 'SystemPost', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:06:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200105, '字典管理', '', 2, 6, 200001, 'dict', 'ep:collection', 'system/dict/index', 'SystemDictType', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:07:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200108, '审计日志', '', 1, 9, 200001, 'log', 'ep:document-copy', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2024-02-29 01:08:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201224, '租户管理', '', 2, 0, 200001, 'tenant', 'fa-solid:house-user', null, null, 0, true, true, true, '1', '2022-02-20 01:41:13', '1', '2024-02-29 00:59:29', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201261, 'OAuth 2.0', '', 2, 10, 200001, 'oauth2', 'fa:dashcube', null, null, 0, true, true, true, '1', '2022-05-09 23:38:17', '1', '2024-02-29 01:12:08', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202083, '地区管理', '', 2, 14, 200001, 'area', 'fa:map-marker', 'system/area/index', 'SystemArea', 0, true, true, true, '1', '2022-12-23 17:35:05', '1',
        '2024-02-29 08:50:28', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202447, '三方登录', '', 1, 10, 200001, 'social', 'fa:rocket', '', '', 0, true, true, true, '1', '2023-11-04 12:12:01', '1', '2024-02-29 01:14:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202739, '消息中心', '', 1, 7, 200001, 'messages', 'ep:chat-dot-round', '', '', 0, true, true, true, '1', '2024-04-22 23:54:30', '1', '2024-04-23 09:36:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213567, '登录管理', '', 2, 1, 200001, 'dlzh', 'fa:key', '/lghjft/qx/dlzh/index.vue', 'DlzhController', 0, true, true, true, '1', '2026-01-16 10:47:17', '1',
        '2026-01-30 20:16:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213568, '身份管理', '', 2, 1, 200001, 'sfxx', 'fa:child', '/lghjft/qx/sfxx/index.vue', 'SfxxController', 0, true, true, true, '1', '2026-01-16 10:47:17', '1',
        '2026-01-30 18:43:58', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200107, '通知公告', '', 2, 4, 202739, 'notice', 'ep:takeaway-box', 'system/notice/index', 'SystemNotice', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-04-22 23:56:17', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200109, '令牌管理', '', 2, 2, 201261, 'token', 'fa:key', 'system/oauth2/token/index', 'SystemTokenClient', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:13:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200500, '操作日志', '', 2, 1, 200108, 'operate-log', 'ep:position', 'system/operatelog/index', 'SystemOperateLog', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:09:59', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200501, '登录日志', '', 2, 2, 200108, 'login-log', 'ep:promotion', 'system/loginlog/index', 'SystemLoginLog', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 01:10:29', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201001, '用户查询', 'system:user:query', 3, 1, 200100, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201002, '用户新增', 'system:user:create', 3, 2, 200100, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201003, '用户修改', 'system:user:update', 3, 3, 200100, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201004, '用户删除', 'system:user:delete', 3, 4, 200100, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201005, '用户导出', 'system:user:export', 3, 5, 200100, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201006, '用户导入', 'system:user:import', 3, 6, 200100, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201007, '重置密码', 'system:user:update-password', 3, 7, 200100, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201008, '角色查询', 'system:role:query', 3, 1, 200101, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201009, '角色新增', 'system:role:create', 3, 2, 200101, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201010, '角色修改', 'system:role:update', 3, 3, 200101, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201011, '角色删除', 'system:role:delete', 3, 4, 200101, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201012, '角色导出', 'system:role:export', 3, 5, 200101, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201013, '菜单查询', 'system:menu:query', 3, 1, 200102, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201014, '菜单新增', 'system:menu:create', 3, 2, 200102, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201015, '菜单修改', 'system:menu:update', 3, 3, 200102, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201016, '菜单删除', 'system:menu:delete', 3, 4, 200102, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201017, '部门查询', 'system:dept:query', 3, 1, 200103, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201018, '部门新增', 'system:dept:create', 3, 2, 200103, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201019, '部门修改', 'system:dept:update', 3, 3, 200103, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201020, '部门删除', 'system:dept:delete', 3, 4, 200103, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201021, '岗位查询', 'system:post:query', 3, 1, 200104, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201022, '岗位新增', 'system:post:create', 3, 2, 200104, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201023, '岗位修改', 'system:post:update', 3, 3, 200104, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201024, '岗位删除', 'system:post:delete', 3, 4, 200104, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201025, '岗位导出', 'system:post:export', 3, 5, 200104, '', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201026, '字典查询', 'system:dict:query', 3, 1, 200105, '#', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201027, '字典新增', 'system:dict:create', 3, 2, 200105, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201028, '字典修改', 'system:dict:update', 3, 3, 200105, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201029, '字典删除', 'system:dict:delete', 3, 4, 200105, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201030, '字典导出', 'system:dict:export', 3, 5, 200105, '#', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201063, '设置角色菜单权限', 'system:permission:assign-role-menu', 3, 6, 200101, '', '', '', null, 0, true, true, true, '', '2021-01-06 17:53:44', '', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201064, '设置角色数据权限', 'system:permission:assign-role-data-scope', 3, 7, 200101, '', '', '', null, 0, true, true, true, '', '2021-01-06 17:56:31', '',
        '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201065, '设置用户角色', 'system:permission:assign-user-role', 3, 8, 200101, '', '', '', null, 0, true, true, true, '', '2021-01-07 10:23:28', '', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201093, '短信管理', '', 1, 1, 202739, 'sms', 'ep:message', null, null, 0, true, true, true, '1', '2021-04-05 01:10:16', '1', '2024-04-22 23:56:03', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201138, '租户列表', '', 2, 0, 201224, 'list', 'ep:house', 'system/tenant/index', 'SystemTenant', 0, true, true, true, '', '2021-12-14 12:31:43', '1', '2024-02-29 01:01:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201225, '租户套餐', '', 2, 0, 201224, 'package', 'fa:bars', 'system/tenantPackage/index', 'SystemTenantPackage', 0, true, true, true, '', '2022-02-19 17:44:06', '1',
        '2024-02-29 01:01:43', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201263, '应用管理', '', 2, 0, 201261, 'oauth2/application', 'fa:hdd-o', 'system/oauth2/client/index', 'SystemOAuth2Client', 0, true, true, true, '', '2022-05-10 16:26:33',
        '1', '2024-02-29 01:13:14', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202130, '邮箱管理', '', 2, 2, 202739, 'mail', 'fa-solid:mail-bulk', null, null, 0, true, true, true, '1', '2023-01-25 17:27:44', '1', '2024-04-22 23:56:08', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202144, '站内信管理', '', 1, 3, 202739, 'notify', 'ep:message-box', null, null, 0, true, true, true, '1', '2023-01-28 10:25:18', '1', '2024-04-22 23:56:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202448, '三方应用', '', 2, 1, 202447, 'client', 'ep:set-up', 'system/social/client/index.vue', 'SocialClient', 0, true, true, true, '1', '2023-11-04 12:17:19', '1',
        '2024-05-04 19:09:54', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202453, '三方用户', 'system:social-user:query', 2, 2, 202447, 'user', 'ep:avatar', 'system/social/user/index.vue', 'SocialUser', 0, true, true, true, '1',
        '2023-11-04 14:01:05', '1', '2023-11-04 14:01:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213569, '创建', 'lghjft:qx-dlzh:create', 3, 1, 213567, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213570, '更新', 'lghjft:qx-dlzh:update', 3, 2, 213567, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213571, '删除', 'lghjft:qx-dlzh:delete', 3, 3, 213567, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213572, '查询', 'lghjft:qx-dlzh:query', 3, 4, 213567, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213573, '重置密码', 'lghjft:qx-dlzh:reset-password', 3, 4, 213567, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213586, '创建', 'lghjft:qx-sfxx:create', 3, 1, 213568, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213587, '更新', 'lghjft:qx-sfxx:update', 3, 2, 213568, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213588, '删除', 'lghjft:qx-sfxx:delete', 3, 3, 213568, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213589, '查询', 'lghjft:qx-sfxx:query', 3, 4, 213568, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213590, '审核', 'lghjft:qx-sfxx:audit', 3, 4, 213568, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-07 15:58:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201036, '公告查询', 'system:notice:query', 3, 1, 200107, '#', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201037, '公告新增', 'system:notice:create', 3, 2, 200107, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201038, '公告修改', 'system:notice:update', 3, 3, 200107, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201039, '公告删除', 'system:notice:delete', 3, 4, 200107, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201040, '操作查询', 'system:operate-log:query', 3, 1, 200500, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201042, '日志导出', 'system:operate-log:export', 3, 2, 200500, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201043, '登录查询', 'system:login-log:query', 3, 1, 200501, '#', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201045, '日志导出', 'system:login-log:export', 3, 3, 200501, '#', '#', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201046, '令牌列表', 'system:oauth2-token:page', 3, 1, 200109, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-05-09 23:54:42', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201048, '令牌删除', 'system:oauth2-token:delete', 3, 2, 200109, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-05-09 23:54:53', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201094, '短信渠道', '', 2, 0, 201093, 'sms-channel', 'fa:stack-exchange', 'system/sms/channel/index', 'SystemSmsChannel', 0, true, true, true, '', '2021-04-01 11:07:15',
        '1', '2024-02-29 01:15:54', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201100, '短信模板', '', 2, 1, 201093, 'sms-template', 'ep:connection', 'system/sms/template/index', 'SystemSmsTemplate', 0, true, true, true, '', '2021-04-01 17:35:17',
        '1', '2024-02-29 01:16:18', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201107, '短信日志', '', 2, 2, 201093, 'sms-log', 'fa:edit', 'system/sms/log/index', 'SystemSmsLog', 0, true, true, true, '', '2021-04-11 08:37:05', '1',
        '2024-02-29 08:49:02', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201139, '租户查询', 'system:tenant:query', 3, 1, 201138, '', '', '', null, 0, true, true, true, '', '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201140, '租户创建', 'system:tenant:create', 3, 2, 201138, '', '', '', null, 0, true, true, true, '', '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201141, '租户更新', 'system:tenant:update', 3, 3, 201138, '', '', '', null, 0, true, true, true, '', '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201142, '租户删除', 'system:tenant:delete', 3, 4, 201138, '', '', '', null, 0, true, true, true, '', '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201143, '租户导出', 'system:tenant:export', 3, 5, 201138, '', '', '', null, 0, true, true, true, '', '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201226, '租户套餐查询', 'system:tenant-package:query', 3, 1, 201225, '', '', '', null, 0, true, true, true, '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201227, '租户套餐创建', 'system:tenant-package:create', 3, 2, 201225, '', '', '', null, 0, true, true, true, '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201228, '租户套餐更新', 'system:tenant-package:update', 3, 3, 201225, '', '', '', null, 0, true, true, true, '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201229, '租户套餐删除', 'system:tenant-package:delete', 3, 4, 201225, '', '', '', null, 0, true, true, true, '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201264, '客户端查询', 'system:oauth2-client:query', 3, 1, 201263, '', '', '', null, 0, true, true, true, '', '2022-05-10 16:26:33', '1', '2022-05-11 00:31:06', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201265, '客户端创建', 'system:oauth2-client:create', 3, 2, 201263, '', '', '', null, 0, true, true, true, '', '2022-05-10 16:26:33', '1', '2022-05-11 00:31:23', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201266, '客户端更新', 'system:oauth2-client:update', 3, 3, 201263, '', '', '', null, 0, true, true, true, '', '2022-05-10 16:26:33', '1', '2022-05-11 00:31:28', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201267, '客户端删除', 'system:oauth2-client:delete', 3, 4, 201263, '', '', '', null, 0, true, true, true, '', '2022-05-10 16:26:33', '1', '2022-05-11 00:31:33', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202131, '邮箱账号', '', 2, 0, 202130, 'mail-account', 'fa:universal-access', 'system/mail/account/index', 'SystemMailAccount', 0, true, true, true, '',
        '2023-01-25 09:33:48', '1', '2024-02-29 08:48:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202136, '邮件模版', '', 2, 0, 202130, 'mail-template', 'fa:tag', 'system/mail/template/index', 'SystemMailTemplate', 0, true, true, true, '', '2023-01-25 12:05:31', '1',
        '2024-02-29 08:48:41', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202141, '邮件记录', '', 2, 0, 202130, 'mail-log', 'fa:edit', 'system/mail/log/index', 'SystemMailLog', 0, true, true, true, '', '2023-01-26 02:16:50', '1',
        '2024-02-29 08:48:51', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202145, '模板管理', '', 2, 0, 202144, 'notify-template', 'fa:archive', 'system/notify/template/index', 'SystemNotifyTemplate', 0, true, true, true, '',
        '2023-01-28 02:26:42', '1', '2024-02-29 08:49:14', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202151, '消息记录', '', 2, 0, 202144, 'notify-message', 'fa:edit', 'system/notify/message/index', 'SystemNotifyMessage', 0, true, true, true, '', '2023-01-28 04:28:22',
        '1', '2024-02-29 08:49:22', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202449, '三方应用查询', 'system:social-client:query', 3, 1, 202448, '', '', '', '', 0, true, true, true, '1', '2023-11-04 12:43:12', '1', '2023-11-04 12:43:33', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202450, '三方应用创建', 'system:social-client:create', 3, 2, 202448, '', '', '', '', 0, true, true, true, '1', '2023-11-04 12:43:58', '1', '2023-11-04 12:43:58', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202451, '三方应用更新', 'system:social-client:update', 3, 3, 202448, '', '', '', '', 0, true, true, true, '1', '2023-11-04 12:44:27', '1', '2023-11-04 12:44:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202452, '三方应用删除', 'system:social-client:delete', 3, 4, 202448, '', '', '', '', 0, true, true, true, '1', '2023-11-04 12:44:43', '1', '2023-11-04 12:44:43', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (205010, '租户切换', 'system:tenant:visit', 3, 999, 201138, '', '', '', '', 0, true, true, true, '1', '2025-05-05 15:25:32', '1', '2025-05-05 15:25:32', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201095, '短信渠道查询', 'system:sms-channel:query', 3, 1, 201094, '', '', '', null, 0, true, true, true, '', '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201096, '短信渠道创建', 'system:sms-channel:create', 3, 2, 201094, '', '', '', null, 0, true, true, true, '', '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201097, '短信渠道更新', 'system:sms-channel:update', 3, 3, 201094, '', '', '', null, 0, true, true, true, '', '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201098, '短信渠道删除', 'system:sms-channel:delete', 3, 4, 201094, '', '', '', null, 0, true, true, true, '', '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201101, '短信模板查询', 'system:sms-template:query', 3, 1, 201100, '', '', '', null, 0, true, true, true, '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201102, '短信模板创建', 'system:sms-template:create', 3, 2, 201100, '', '', '', null, 0, true, true, true, '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201103, '短信模板更新', 'system:sms-template:update', 3, 3, 201100, '', '', '', null, 0, true, true, true, '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201104, '短信模板删除', 'system:sms-template:delete', 3, 4, 201100, '', '', '', null, 0, true, true, true, '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201105, '短信模板导出', 'system:sms-template:export', 3, 5, 201100, '', '', '', null, 0, true, true, true, '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201106, '发送测试短信', 'system:sms-template:send-sms', 3, 6, 201100, '', '', '', null, 0, true, true, true, '1', '2021-04-11 00:26:40', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201108, '短信日志查询', 'system:sms-log:query', 3, 1, 201107, '', '', '', null, 0, true, true, true, '', '2021-04-11 08:37:05', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201109, '短信日志导出', 'system:sms-log:export', 3, 5, 201107, '', '', '', null, 0, true, true, true, '', '2021-04-11 08:37:05', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202132, '账号查询', 'system:mail-account:query', 3, 1, 202131, '', '', '', null, 0, true, true, true, '', '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202133, '账号创建', 'system:mail-account:create', 3, 2, 202131, '', '', '', null, 0, true, true, true, '', '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202134, '账号更新', 'system:mail-account:update', 3, 3, 202131, '', '', '', null, 0, true, true, true, '', '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202135, '账号删除', 'system:mail-account:delete', 3, 4, 202131, '', '', '', null, 0, true, true, true, '', '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202137, '模版查询', 'system:mail-template:query', 3, 1, 202136, '', '', '', null, 0, true, true, true, '', '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202138, '模版创建', 'system:mail-template:create', 3, 2, 202136, '', '', '', null, 0, true, true, true, '', '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202139, '模版更新', 'system:mail-template:update', 3, 3, 202136, '', '', '', null, 0, true, true, true, '', '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202140, '模版删除', 'system:mail-template:delete', 3, 4, 202136, '', '', '', null, 0, true, true, true, '', '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202142, '日志查询', 'system:mail-log:query', 3, 1, 202141, '', '', '', null, 0, true, true, true, '', '2023-01-26 02:16:50', '', '2023-01-26 02:16:50', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202143, '发送测试邮件', 'system:mail-template:send-mail', 3, 5, 202136, '', '', '', null, 0, true, true, true, '1', '2023-01-26 23:29:15', '1', '2023-01-26 23:29:15',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202146, '站内信模板查询', 'system:notify-template:query', 3, 1, 202145, '', '', '', null, 0, true, true, true, '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202147, '站内信模板创建', 'system:notify-template:create', 3, 2, 202145, '', '', '', null, 0, true, true, true, '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202148, '站内信模板更新', 'system:notify-template:update', 3, 3, 202145, '', '', '', null, 0, true, true, true, '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202149, '站内信模板删除', 'system:notify-template:delete', 3, 4, 202145, '', '', '', null, 0, true, true, true, '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202150, '发送测试站内信', 'system:notify-template:send-notify', 3, 5, 202145, '', '', '', null, 0, true, true, true, '1', '2023-01-28 10:54:43', '1', '2023-01-28 10:54:43',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202152, '站内信消息查询', 'system:notify-message:query', 3, 1, 202151, '', '', '', null, 0, true, true, true, '', '2023-01-28 04:28:22', '', '2023-01-28 04:28:22', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200002, '基础设施', '', 1, 20, 0, '/infra', 'ep:monitor', null, null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2024-03-01 08:28:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200106, '配置管理', '', 2, 8, 200002, 'config', 'fa:connectdevelop', 'infra/config/index', 'InfraConfig', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-04-23 00:02:45', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200110, '定时任务', '', 2, 7, 200002, 'job', 'fa-solid:tasks', 'infra/job/index', 'InfraJob', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 08:57:36', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200114, '表单构建', 'infra:build:list', 2, 2, 200002, 'build', 'fa:wpforms', 'infra/build/index', 'InfraBuild', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-02-29 08:51:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200115, '代码生成', 'infra:codegen:query', 2, 1, 200002, 'codegen', 'ep:document-copy', 'infra/codegen/index', 'InfraCodegen', 0, true, true, true, 'admin',
        '2021-01-05 17:03:48', '1', '2024-02-29 08:51:06', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200116, 'API 接口', 'infra:swagger:list', 2, 3, 200002, 'swagger', 'fa:fighter-jet', 'infra/swagger/index', 'InfraSwagger', 0, true, true, true, 'admin',
        '2021-01-05 17:03:48', '1', '2024-04-23 00:01:24', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201070, '代码生成案例', '', 1, 1, 200002, 'demo', 'ep:aim', 'infra/testDemo/index', null, 0, true, true, true, '', '2021-02-06 12:42:49', '1', '2023-11-15 23:45:53',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201083, 'API 日志', '', 2, 4, 200002, 'log', 'fa:tasks', null, null, 0, true, true, true, '', '2021-02-26 02:18:24', '1', '2024-04-22 23:58:36', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201243, '文件管理', '', 2, 6, 200002, 'file', 'ep:files', null, '', 0, true, true, true, '1', '2022-03-16 23:47:40', '1', '2024-04-23 00:02:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201255, '数据源配置', '', 2, 1, 200002, 'data-source-config', 'ep:data-analysis', 'infra/dataSourceConfig/index', 'InfraDataSourceConfig', 0, true, true, true, '',
        '2022-04-27 14:37:32', '1', '2024-02-29 08:51:25', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202525, 'WebSocket', '', 2, 5, 200002, 'websocket', 'ep:connection', 'infra/webSocket/index', 'InfraWebSocket', 0, true, true, true, '1', '2023-11-23 19:41:55', '1',
        '2024-04-23 00:02:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202740, '监控中心', '', 1, 10, 200002, 'monitors', 'ep:monitor', '', '', 0, true, true, true, '1', '2024-04-23 00:04:44', '1', '2024-04-23 00:04:44', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200111, 'MySQL 监控', '', 2, 1, 202740, 'druid', 'fa-solid:box', 'infra/druid/index', 'InfraDruid', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-04-23 00:05:58', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200112, 'Java 监控', '', 2, 3, 202740, 'admin-server', 'ep:coffee-cup', 'infra/server/index', 'InfraAdminServer', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-04-23 00:06:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200113, 'Redis 监控', '', 2, 2, 202740, 'redis', 'fa:reddit-square', 'infra/redis/index', 'InfraRedis', 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1',
        '2024-04-23 00:06:09', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201031, '配置查询', 'infra:config:query', 3, 1, 200106, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201032, '配置新增', 'infra:config:create', 3, 2, 200106, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201033, '配置修改', 'infra:config:update', 3, 3, 200106, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201034, '配置删除', 'infra:config:delete', 3, 4, 200106, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201035, '配置导出', 'infra:config:export', 3, 5, 200106, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201050, '任务新增', 'infra:job:create', 3, 2, 200110, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201051, '任务修改', 'infra:job:update', 3, 3, 200110, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201052, '任务删除', 'infra:job:delete', 3, 4, 200110, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201053, '状态修改', 'infra:job:update', 3, 5, 200110, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201054, '任务导出', 'infra:job:export', 3, 7, 200110, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201056, '生成修改', 'infra:codegen:update', 3, 2, 200115, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201057, '生成删除', 'infra:codegen:delete', 3, 3, 200115, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201058, '导入代码', 'infra:codegen:create', 3, 2, 200115, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201059, '预览代码', 'infra:codegen:preview', 3, 4, 200115, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201060, '生成代码', 'infra:codegen:download', 3, 5, 200115, '', '', '', null, 0, true, true, true, 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201075, '任务触发', 'infra:job:trigger', 3, 8, 200110, '', '', '', null, 0, true, true, true, '', '2021-02-07 13:03:10', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201077, '链路追踪', '', 2, 4, 202740, 'skywalking', 'fa:eye', 'infra/skywalking/index', 'InfraSkyWalking', 0, true, true, true, '', '2021-02-08 20:41:31', '1',
        '2024-04-23 00:07:15', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201078, '访问日志', '', 2, 1, 201083, 'api-access-log', 'ep:place', 'infra/apiAccessLog/index', 'InfraApiAccessLog', 0, true, true, true, '', '2021-02-26 01:32:59', '1',
        '2024-02-29 08:54:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201084, '错误日志', 'infra:api-error-log:query', 2, 2, 201083, 'api-error-log', 'ep:warning-filled', 'infra/apiErrorLog/index', 'InfraApiErrorLog', 0, true, true, true, '',
        '2021-02-26 07:53:20', '1', '2024-02-29 08:55:17', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201087, '任务查询', 'infra:job:query', 3, 1, 200110, '', '', '', null, 0, true, true, true, '1', '2021-03-10 01:26:19', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201090, '文件列表', '', 2, 5, 201243, 'file', 'ep:upload-filled', 'infra/file/index', 'InfraFile', 0, true, true, true, '', '2021-03-12 20:16:20', '1',
        '2024-02-29 08:53:02', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201237, '文件配置', '', 2, 0, 201243, 'file-config', 'fa-solid:file-signature', 'infra/fileConfig/index', 'InfraFileConfig', 0, true, true, true, '', '2022-03-15 14:35:28',
        '1', '2024-02-29 08:52:54', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201256, '数据源配置查询', 'infra:data-source-config:query', 3, 1, 201255, '', '', '', null, 0, true, true, true, '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201257, '数据源配置创建', 'infra:data-source-config:create', 3, 2, 201255, '', '', '', null, 0, true, true, true, '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201258, '数据源配置更新', 'infra:data-source-config:update', 3, 3, 201255, '', '', '', null, 0, true, true, true, '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201259, '数据源配置删除', 'infra:data-source-config:delete', 3, 4, 201255, '', '', '', null, 0, true, true, true, '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201260, '数据源配置导出', 'infra:data-source-config:export', 3, 5, 201255, '', '', '', null, 0, true, true, true, '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202472, '主子表（内嵌）', '', 2, 12, 201070, 'demo03-inner', 'fa:power-off', 'infra/demo/demo03/inner/index', 'Demo03StudentInner', 0, true, true, true, '',
        '2023-11-13 04:39:51', '1', '2023-11-16 23:53:46', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202478, '单表（增删改查）', '', 2, 1, 201070, 'demo01-contact', 'ep:bicycle', 'infra/demo/demo01/index', 'Demo01Contact', 0, true, true, true, '', '2023-11-15 14:42:30', '1',
        '2023-11-16 20:34:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202484, '树表（增删改查）', '', 2, 2, 201070, 'demo02-category', 'fa:tree', 'infra/demo/demo02/index', 'Demo02Category', 0, true, true, true, '', '2023-11-16 12:18:27', '1',
        '2023-11-16 20:35:01', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202490, '主子表（标准）', '', 2, 10, 201070, 'demo03-normal', 'fa:battery-3', 'infra/demo/demo03/normal/index', 'Demo03StudentNormal', 0, true, true, true, '',
        '2023-11-16 12:53:37', '1', '2023-11-16 23:10:03', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202497, '主子表（ERP）', '', 2, 11, 201070, 'demo03-erp', 'ep:calendar', 'infra/demo/demo03/erp/index', 'Demo03StudentERP', 0, true, true, true, '', '2023-11-16 15:50:59',
        '1', '2023-11-17 13:19:56', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201066, '获得 Redis 监控信息', 'infra:redis:get-monitor-info', 3, 1, 200113, '', '', '', null, 0, true, true, true, '', '2021-01-26 01:02:31', '', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201067, '获得 Redis Key 列表', 'infra:redis:get-key-list', 3, 2, 200113, '', '', '', null, 0, true, true, true, '', '2021-01-26 01:02:52', '', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201082, '日志导出', 'infra:api-access-log:export', 3, 2, 201078, '', '', '', null, 0, true, true, true, '', '2021-02-26 01:32:59', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201085, '日志处理', 'infra:api-error-log:update-status', 3, 2, 201084, '', '', '', null, 0, true, true, true, '', '2021-02-26 07:53:20', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201086, '日志导出', 'infra:api-error-log:export', 3, 3, 201084, '', '', '', null, 0, true, true, true, '', '2021-02-26 07:53:20', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201088, '日志查询', 'infra:api-access-log:query', 3, 1, 201078, '', '', '', null, 0, true, true, true, '1', '2021-03-10 01:28:04', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201089, '日志查询', 'infra:api-error-log:query', 3, 1, 201084, '', '', '', null, 0, true, true, true, '1', '2021-03-10 01:29:09', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201091, '文件查询', 'infra:file:query', 3, 1, 201090, '', '', '', null, 0, true, true, true, '', '2021-03-12 20:16:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201092, '文件删除', 'infra:file:delete', 3, 4, 201090, '', '', '', null, 0, true, true, true, '', '2021-03-12 20:16:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201238, '文件配置查询', 'infra:file-config:query', 3, 1, 201237, '', '', '', null, 0, true, true, true, '', '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201239, '文件配置创建', 'infra:file-config:create', 3, 2, 201237, '', '', '', null, 0, true, true, true, '', '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201240, '文件配置更新', 'infra:file-config:update', 3, 3, 201237, '', '', '', null, 0, true, true, true, '', '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201241, '文件配置删除', 'infra:file-config:delete', 3, 4, 201237, '', '', '', null, 0, true, true, true, '', '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201242, '文件配置导出', 'infra:file-config:export', 3, 5, 201237, '', '', '', null, 0, true, true, true, '', '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202479, '示例联系人查询', 'infra:demo01-contact:query', 3, 1, 202478, '', '', '', null, 0, true, true, true, '', '2023-11-15 14:42:30', '', '2023-11-15 14:42:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202480, '示例联系人创建', 'infra:demo01-contact:create', 3, 2, 202478, '', '', '', null, 0, true, true, true, '', '2023-11-15 14:42:30', '', '2023-11-15 14:42:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202481, '示例联系人更新', 'infra:demo01-contact:update', 3, 3, 202478, '', '', '', null, 0, true, true, true, '', '2023-11-15 14:42:30', '', '2023-11-15 14:42:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202482, '示例联系人删除', 'infra:demo01-contact:delete', 3, 4, 202478, '', '', '', null, 0, true, true, true, '', '2023-11-15 14:42:30', '', '2023-11-15 14:42:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202483, '示例联系人导出', 'infra:demo01-contact:export', 3, 5, 202478, '', '', '', null, 0, true, true, true, '', '2023-11-15 14:42:30', '', '2023-11-15 14:42:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202485, '示例分类查询', 'infra:demo02-category:query', 3, 1, 202484, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:18:27', '', '2023-11-16 12:18:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202486, '示例分类创建', 'infra:demo02-category:create', 3, 2, 202484, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:18:27', '', '2023-11-16 12:18:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202487, '示例分类更新', 'infra:demo02-category:update', 3, 3, 202484, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:18:27', '', '2023-11-16 12:18:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202488, '示例分类删除', 'infra:demo02-category:delete', 3, 4, 202484, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:18:27', '', '2023-11-16 12:18:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202489, '示例分类导出', 'infra:demo02-category:export', 3, 5, 202484, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:18:27', '', '2023-11-16 12:18:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202491, '学生查询', 'infra:demo03-student:query', 3, 1, 202490, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:53:37', '', '2023-11-16 12:53:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202492, '学生创建', 'infra:demo03-student:create', 3, 2, 202490, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:53:37', '', '2023-11-16 12:53:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202493, '学生更新', 'infra:demo03-student:update', 3, 3, 202490, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:53:37', '', '2023-11-16 12:53:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202494, '学生删除', 'infra:demo03-student:delete', 3, 4, 202490, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:53:37', '', '2023-11-16 12:53:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202495, '学生导出', 'infra:demo03-student:export', 3, 5, 202490, '', '', '', null, 0, true, true, true, '', '2023-11-16 12:53:37', '', '2023-11-16 12:53:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201200, '审批中心', '', 1, 1, 0, '/task', 'fa:tasks', null, null, 0, true, true, true, '1', '2022-01-07 23:51:48', '1', '2026-01-09 12:31:17', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201201, '我的流程', '', 2, 1, 201200, 'my', 'fa-solid:book', 'bpm/processInstance/index', 'BpmProcessInstanceMy', 0, true, true, true, '', '2022-01-07 15:53:44', '1',
        '2024-03-21 23:52:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201207, '待办任务', '', 2, 10, 201200, 'todo', 'fa:slack', 'bpm/task/todo/index', 'BpmTodoTask', 0, true, true, true, '1', '2022-01-08 10:33:37', '1',
        '2024-02-29 12:37:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201208, '已办任务', '', 2, 20, 201200, 'done', 'fa:delicious', 'bpm/task/done/index', 'BpmDoneTask', 0, true, true, true, '1', '2022-01-08 10:34:13', '1',
        '2024-02-29 12:37:54', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202713, '抄送我的', 'bpm:process-instance-cc:query', 2, 30, 201200, 'copy', 'ep:copy-document', 'bpm/task/copy/index', 'BpmProcessInstanceCopy', 0, true, true, true, '1',
        '2024-03-17 21:50:23', '1', '2024-04-24 19:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202720, '发起流程', '', 2, 0, 201200, 'create', 'fa-solid:grin-stars', 'bpm/processInstance/create/index', 'BpmProcessInstanceCreate', 0, true, false, true, '1',
        '2024-03-19 19:46:05', '1', '2024-03-23 19:03:42', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201202, '流程实例的查询', 'bpm:process-instance:query', 3, 1, 201201, '', '', '', null, 0, true, true, true, '', '2022-01-07 15:53:44', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201219, '流程实例的创建', 'bpm:process-instance:create', 3, 2, 201201, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:36:15', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201220, '流程实例的取消', 'bpm:process-instance:cancel', 3, 3, 201201, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:36:33', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201221, '流程任务的查询', 'bpm:task:query', 3, 1, 201207, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:38:52', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201222, '流程任务的更新', 'bpm:task:update', 3, 2, 201207, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:39:24', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201281, '报表管理', '', 2, 40, 0, '/report', 'ep:pie-chart', null, null, 0, true, true, true, '1', '2022-07-10 20:22:15', '1', '2024-02-29 12:33:03', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201282, '报表设计器', '', 2, 1, 201281, 'jimu-report', 'ep:trend-charts', 'report/jmreport/index', 'JimuReport', 0, true, true, true, '1', '2022-07-10 20:26:36', '1',
        '2025-05-03 09:57:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202153, '大屏设计器', '', 2, 2, 201281, 'go-view', 'fa:area-chart', 'report/goview/index', 'GoView', 0, true, true, true, '1', '2023-02-07 00:03:19', '1',
        '2025-05-03 09:57:03', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (205009, '仪表盘设计器', '', 2, 1, 201281, 'jimu-bi', 'fa:y-combinator', 'report/jmreport/bi', 'JimuBI', 0, true, true, true, '1', '2025-05-03 09:57:15', '1',
        '2025-05-03 10:02:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202154, '创建项目', 'report:go-view-project:create', 3, 1, 202153, '', '', '', null, 0, true, true, true, '1', '2023-02-07 19:25:14', '1', '2023-02-07 19:25:14', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202155, '更新项目', 'report:go-view-project:update', 3, 2, 202153, '', '', '', '', 0, true, true, true, '1', '2023-02-07 19:25:34', '1', '2024-04-24 20:01:18', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202156, '查询项目', 'report:go-view-project:query', 3, 0, 202153, '', '', '', null, 0, true, true, true, '1', '2023-02-07 19:25:53', '1', '2023-02-07 19:25:53', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202157, '使用 SQL 查询数据', 'report:go-view-data:get-by-sql', 3, 3, 202153, '', '', '', null, 0, true, true, true, '1', '2023-02-07 19:26:15', '1', '2023-02-07 19:26:15',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202158, '使用 HTTP 查询数据', 'report:go-view-data:get-by-http', 3, 4, 202153, '', '', '', null, 0, true, true, true, '1', '2023-02-07 19:26:35', '1',
        '2023-02-07 19:26:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202755, '删除项目', 'report:go-view-project:delete', 3, 2, 202153, '', '', '', '', 0, true, true, true, '1', '2024-04-24 20:01:37', '1', '2024-04-24 20:01:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (205047, '数据维护', '', 1, 1, 0, '/sjwh', 'fa:table', null, null, 0, true, true, true, '', '2026-01-13 11:15:51', '1', '2026-01-13 14:51:22', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (205075, '代码维护', '', 1, 0, 205047, 'sjwh/dmwh', 'ep:pointer', null, null, 0, true, true, true, '', '2026-01-13 11:15:51', '1', '2026-02-25 11:35:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (206365, '拨付信息排除解除', '', 1, 4, 205047, 'sjwh/bfzhpc', 'ep:check', null, null, 0, true, true, true, '', '2026-01-13 11:15:51', '1', '2026-01-13 16:11:36', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213602, '年度任务', '', 2, 0, 205047, 'rws', 'ep:notebook', 'lghjft/rws/index', 'Rws', 0, true, true, true, '', '2026-02-25 10:55:57', '1', '2026-02-25 10:59:52', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213603, '年度任务查询', 'lghjft:rws:query', 3, 1, 213602, '', '', '', null, 0, true, true, true, '', '2026-02-25 10:55:57', '', '2026-02-25 10:55:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213604, '年度任务创建', 'lghjft:rws:create', 3, 2, 213602, '', '', '', null, 0, true, true, true, '', '2026-02-25 10:55:57', '', '2026-02-25 10:55:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213605, '年度任务更新', 'lghjft:rws:update', 3, 3, 213602, '', '', '', null, 0, true, true, true, '', '2026-02-25 10:55:57', '', '2026-02-25 10:55:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213606, '年度任务删除', 'lghjft:rws:delete', 3, 4, 213602, '', '', '', null, 0, true, true, true, '', '2026-02-25 10:55:57', '', '2026-02-25 10:55:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213607, '年度任务导出', 'lghjft:rws:export', 3, 5, 213602, '', '', '', null, 0, true, true, true, '', '2026-02-25 10:55:57', '', '2026-02-25 10:55:57', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213608, '行政区划', '', 2, 0, 205075, 'xzqh', 'ep:chat-line-round', 'dm/xzqh/index', 'Xzqh', 0, true, true, true, '', '2026-02-25 11:04:07', '1', '2026-02-25 11:06:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213614, '银行网点', '', 2, 0, 205075, 'yhwd', 'fa:cny', 'dm/yhwd/index', 'Yhwd', 0, true, true, true, '', '2026-02-25 11:37:21', '1', '2026-02-25 11:40:56', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213620, '税务机关', '', 2, 0, 205075, 'swjg', 'fa-solid:network-wired', 'dm/swjg/index', 'Swjg', 0, true, true, true, '', '2026-02-25 11:55:12', '1', '2026-02-25 12:00:48',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213626, '户籍分类', '', 2, 0, 205075, 'hjfl', 'ep:user', 'dm/hjfl/index', 'Hjfl', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:30:01', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213632, '分配比例', '', 2, 0, 205075, 'fpbl-copy', 'ep:pie-chart', 'dm/fpblcopy/index', 'FpblCopy', 0, true, true, true, '', '2026-02-25 15:37:11', '1',
        '2026-02-25 15:39:23', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213638, '收款国库', '', 2, 0, 205075, 'skgk', 'fa-solid:book-open', 'dm/skgk/index', 'Skgk', 0, true, true, true, '', '2026-02-25 15:41:35', '1', '2026-02-25 15:43:04',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213609, '行政区划查询', 'dm:xzqh:query', 3, 1, 213608, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:04:07', '', '2026-02-25 11:04:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213610, '行政区划创建', 'dm:xzqh:create', 3, 2, 213608, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:04:07', '', '2026-02-25 11:04:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213611, '行政区划更新', 'dm:xzqh:update', 3, 3, 213608, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:04:07', '', '2026-02-25 11:04:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213612, '行政区划删除', 'dm:xzqh:delete', 3, 4, 213608, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:04:07', '', '2026-02-25 11:04:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213613, '行政区划导出', 'dm:xzqh:export', 3, 5, 213608, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:04:07', '', '2026-02-25 11:04:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213615, '银行网点查询', 'dm:yhwd:query', 3, 1, 213614, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:37:21', '', '2026-02-25 11:37:21', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213616, '银行网点创建', 'dm:yhwd:create', 3, 2, 213614, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:37:21', '', '2026-02-25 11:37:21', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213617, '银行网点更新', 'dm:yhwd:update', 3, 3, 213614, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:37:21', '', '2026-02-25 11:37:21', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213618, '银行网点删除', 'dm:yhwd:delete', 3, 4, 213614, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:37:21', '', '2026-02-25 11:37:21', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213619, '银行网点导出', 'dm:yhwd:export', 3, 5, 213614, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:37:21', '', '2026-02-25 11:37:21', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213621, '税务机关查询', 'dm:swjg:query', 3, 1, 213620, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:55:12', '', '2026-02-25 11:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213622, '税务机关创建', 'dm:swjg:create', 3, 2, 213620, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:55:12', '', '2026-02-25 11:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213623, '税务机关更新', 'dm:swjg:update', 3, 3, 213620, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:55:12', '', '2026-02-25 11:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213624, '税务机关删除', 'dm:swjg:delete', 3, 4, 213620, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:55:12', '', '2026-02-25 11:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213625, '税务机关导出', 'dm:swjg:export', 3, 5, 213620, '', '', '', null, 0, true, true, true, '', '2026-02-25 11:55:12', '', '2026-02-25 11:55:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213627, '户籍分类查询', 'dm:hjfl:query', 3, 1, 213626, '', '', '', '', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:32:49', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213628, '户籍分类创建', 'dm:hjfl:create', 3, 2, 213626, '', '', '', '', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:30:54', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213629, '户籍分类更新', 'dm:hjfl:update', 3, 3, 213626, '', '', '', '', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:31:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213630, '户籍分类删除', 'dm:hjfl:delete', 3, 4, 213626, '', '', '', '', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:31:04', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213631, '户籍分类导出', 'dm:hjfl:export', 3, 5, 213626, '', '', '', '', 0, true, true, true, '', '2026-02-25 14:34:40', '1', '2026-02-25 15:31:09', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213633, '分配比例查询', 'dm:fpbl-copy:query', 3, 1, 213632, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:37:11', '', '2026-02-25 15:37:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213634, '分配比例创建', 'dm:fpbl-copy:create', 3, 2, 213632, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:37:11', '', '2026-02-25 15:37:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213635, '分配比例更新', 'dm:fpbl-copy:update', 3, 3, 213632, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:37:11', '', '2026-02-25 15:37:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213636, '分配比例删除', 'dm:fpbl-copy:delete', 3, 4, 213632, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:37:11', '', '2026-02-25 15:37:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213637, '分配比例导出', 'dm:fpbl-copy:export', 3, 5, 213632, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:37:11', '', '2026-02-25 15:37:11', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213639, '收款国库查询', 'dm:skgk:query', 3, 1, 213638, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:41:35', '', '2026-02-25 15:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213640, '收款国库创建', 'dm:skgk:create', 3, 2, 213638, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:41:35', '', '2026-02-25 15:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213641, '收款国库更新', 'dm:skgk:update', 3, 3, 213638, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:41:35', '', '2026-02-25 15:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213642, '收款国库删除', 'dm:skgk:delete', 3, 4, 213638, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:41:35', '', '2026-02-25 15:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213643, '收款国库导出', 'dm:skgk:export', 3, 5, 213638, '', '', '', null, 0, true, true, true, '', '2026-02-25 15:41:35', '', '2026-02-25 15:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213468, '流程事项', '', 1, 1, 0, '/workflow', 'ep:baseball', '', '', 0, true, true, true, '1', '2026-01-14 09:43:34', '1', '2026-01-30 12:47:56', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213469, '退费申请', '', 2, 0, 213468, 'wfsqtfsq', '', 'lghjft/wfsqtfsq/index', 'WfSqTfsq', 0, true, true, true, '', '2026-01-14 09:45:45', '1', '2026-01-14 13:41:27',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213538, '汇总缴费申请', '', 2, 0, 213468, 'lghjft/wfsqhzjf', '', 'lghjft/workflow/wfsqhzjf/index', 'WfSqHzjf', 0, true, true, true, '', '2026-01-27 11:44:46', '1',
        '2026-02-11 17:21:04', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213544, '分支机构明细管理', '', 2, 0, 213468, 'wf-sq-hzjfmx', '', 'lghjft/wfsqhzjfmx/index', 'WfSqHzjfmx', 1, true, true, true, '', '2026-01-27 11:45:08', '1',
        '2026-02-03 10:24:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213550, '退抵费申请', '', 2, 0, 213468, 'lghjft/wftdfsq', '', 'lghjft/workflow/wftdfsq/index', 'WfTdfsq', 0, true, true, true, '', '2026-01-27 17:39:58', '1',
        '2026-02-11 17:23:55', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213580, '经费缓缴申请', '', 2, 0, 213468, 'lghjft/wfjfhjsq', '', 'lghjft/workflow/wfjfhjsq/index', 'WfJfhjSq', 0, true, true, true, '', '2026-02-05 11:10:36', '1',
        '2026-02-11 17:22:01', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213470, '退费申请查询', 'lghjft:workflow-wfsqtfsq:query', 3, 1, 213469, '', '', '', '', 0, true, true, true, '', '2026-01-14 09:45:45', '1', '2026-01-14 13:41:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213471, '退费申请创建', 'lghjft:workflow-wfsqtfsq:create', 3, 2, 213469, '', '', '', '', 0, true, true, true, '', '2026-01-14 09:45:45', '1', '2026-01-14 13:41:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213539, '工会经费汇总申报查询', 'lghjft:wf-sq-hzjf:query', 3, 1, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '', '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213540, '工会经费汇总申报创建', 'lghjft:wf-sq-hzjf:create', 3, 2, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '', '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213541, '工会经费汇总申报更新', 'lghjft:wf-sq-hzjf:update', 3, 3, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '', '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213545, '分支机构明细查询', 'lghjft:wf-sq-hzjfmx:query', 3, 1, 213544, '', '', '', '', 1, true, true, true, '', '2026-01-27 11:45:08', '1', '2026-02-05 11:11:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213546, '分支机构明细创建', 'lghjft:wf-sq-hzjfmx:create', 3, 2, 213544, '', '', '', '', 1, true, true, true, '', '2026-01-27 11:45:08', '1', '2026-02-05 11:11:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213551, '退抵费申请查询', 'lghjft:wftdfsq:query', 3, 1, 213550, '', '', '', '', 0, true, true, true, '', '2026-01-27 17:39:58', '1', '2026-02-03 10:38:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213552, '退抵费申请创建', 'lghjft:workflow-wftdfsq:create', 3, 2, 213550, '', '', '', '', 0, true, true, true, '', '2026-01-27 17:39:58', '1', '2026-01-28 17:51:15',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213553, '退抵费申请更新', 'lghjft:wf-tdfsq:update', 3, 3, 213550, '', '', '', null, 0, true, true, true, '', '2026-01-27 17:39:58', '', '2026-01-27 17:39:58', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213581, '工会经费缓缴申请查询', 'lghjft:wf-jfhj-sq:query', 3, 1, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213582, '工会经费缓缴申请创建', 'lghjft:wf-jfhj-sq:create', 3, 2, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213583, '工会经费缓缴申请更新', 'lghjft:wf-jfhj-sq:update', 3, 3, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213584, '工会经费缓缴申请删除', 'lghjft:wf-jfhj-sq:delete', 3, 4, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213585, '工会经费缓缴申请导出', 'lghjft:wf-jfhj-sq:export', 3, 5, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213481, '消息中心', '', 1, 0, 0, '/xxzx', 'ep:bell', '', '', 0, true, true, true, '1', '2026-01-16 04:04:38', '1', '2026-01-30 12:45:51', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213482, '通知公告', '', 2, 0, 213481, 'tzgg', '', '/lghjft/xxzx/tzgg/index.vue', 'TzggController', 0, true, true, true, '1', '2026-01-16 05:05:02', '1',
        '2026-01-18 16:07:48', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213489, '消息提醒', '', 2, 1, 213481, 'xxtx', '', '/lghjft/xxzx/xxtx/index.vue', 'XxtxController', 0, true, true, true, '1', '2026-01-16 11:23:13', '1',
        '2026-01-18 16:07:04', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213483, '公告查询', 'lghjft:xxzx-tzgg:query', 3, 0, 213482, '', '', '', '', 0, true, true, true, '1', '2026-01-16 05:07:47', '1', '2026-01-16 08:42:28', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213484, '公告创建', 'lghjft:xxzx-tzgg:create', 3, 1, 213482, '', '', '', '', 0, true, true, true, '1', '2026-01-16 05:26:23', '1', '2026-01-16 08:42:32', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213485, '公告修改', 'lghjft:xxzx-tzgg:update', 3, 2, 213482, '', '', '', '', 0, true, true, true, '1', '2026-01-16 05:27:37', '1', '2026-01-16 08:42:37', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213486, '公告删除', 'lghjft:xxzx-tzgg:delete', 3, 4, 213482, '', '', '', '', 0, true, true, true, '1', '2026-01-16 06:38:04', '1', '2026-01-16 08:42:41', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213504, '消息查询', 'lghjft:xxzx-xxtx:query', 3, 0, 213489, '', '', '', '', 0, true, true, true, '1', '2026-01-18 16:08:52', '1', '2026-01-18 16:08:52', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213505, '消息创建', 'lghjft:xxzx-xxtx:create', 3, 1, 213489, '', '', '', '', 0, true, true, true, '1', '2026-01-18 16:09:07', '1', '2026-01-18 16:09:07', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213506, '消息修改', 'lghjft:xxzx-xxtx:update', 3, 2, 213489, '', '', '', '', 0, true, true, true, '1', '2026-01-18 16:09:27', '1', '2026-01-18 17:05:23', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213507, '消息删除', 'lghjft:xxzx-xxtx:delete', 3, 3, 213489, '', '', '', '', 0, true, true, true, '1', '2026-01-18 16:09:41', '1', '2026-01-18 16:09:41', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213508, '消息发送', 'lghjft:xxzx-xxtx:send', 3, 4, 213489, '', '', '', '', 0, true, true, true, '1', '2026-01-18 17:02:34', '1', '2026-01-18 17:02:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213487, '内容管理', '', 1, 15, 0, '/nrgl', 'fa:align-justify', '', '', 0, true, true, true, '1', '2026-01-16 10:47:17', '1', '2026-01-16 11:01:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213498, '问题反馈', '', 2, 1, 213487, 'wtfk', 'fa:question-circle', 'lghjft/wtfk/index', 'Wtfk', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:12:23',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213515, '问题处理', '', 2, 2, 213487, 'wtcl', 'ep:avatar', 'lghjft/wtfk/process/index', '', 0, true, true, true, '1', '2026-01-19 16:19:02', '1', '2026-01-19 17:31:04',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213516, '政策解读', '', 2, 10, 213487, 'zcjd', 'ep:document', 'lghjft/nrgl/zcjd/index', null, 0, true, true, true, '', '2026-01-20 10:18:28', '', '2026-01-20 10:18:28',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213517, '办事指南', '', 2, 20, 213487, 'bszn', 'ep:guide', 'lghjft/nrgl/bszn/index', null, 0, true, true, true, '', '2026-01-20 10:18:28', '', '2026-01-20 10:18:28',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213518, '常见问题', '', 2, 30, 213487, 'cjwt', 'ep:question-filled', 'lghjft/nrgl/cjwt/index', null, 0, true, true, true, '', '2026-01-20 10:18:28', '',
        '2026-01-20 10:18:28', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213562, '版本发布', '', 2, 40, 213487, 'bbfb', 'ep:notebook', 'lghjft/nrgl/bbfb/index', 'BbfbController', 0, true, true, true, '1', '2026-01-29 22:26:31', '1',
        '2026-01-29 22:34:06', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213499, '问题反馈查询', 'lghjft:wtfk:query', 3, 1, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213500, '问题反馈创建', 'lghjft:wtfk:create', 3, 2, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:22', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213501, '问题反馈更新', 'lghjft:wtfk:update', 3, 3, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:36', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213502, '问题反馈删除', 'lghjft:wtfk:delete', 3, 4, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:45', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213503, '问题反馈导出', 'lghjft:wtfk:export', 3, 5, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:53', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213519, '创建', 'lghjft:nrgl-zcjd:create', 3, 1, 213516, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213520, '更新', 'lghjft:nrgl-zcjd:update', 3, 2, 213516, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213521, '删除', 'lghjft:nrgl-zcjd:delete', 3, 3, 213516, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213522, '查询', 'lghjft:nrgl-zcjd:query', 3, 4, 213516, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213523, '创建', 'lghjft:nrgl-bszn:create', 3, 1, 213517, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213524, '更新', 'lghjft:nrgl-bszn:update', 3, 2, 213517, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213525, '删除', 'lghjft:nrgl-bszn:delete', 3, 3, 213517, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213526, '查询', 'lghjft:nrgl-bszn:query', 3, 4, 213517, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213527, '创建', 'lghjft:nrgl-cjwt:create', 3, 1, 213518, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213528, '更新', 'lghjft:nrgl-cjwt:update', 3, 2, 213518, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213529, '删除', 'lghjft:nrgl-cjwt:delete', 3, 3, 213518, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213530, '查询', 'lghjft:nrgl-cjwt:query', 3, 4, 213518, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213563, '创建', 'lghjft:nrgl-bbfb:create', 3, 1, 213562, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:21:16', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213564, '更新', 'lghjft:nrgl-bbfb:update', 3, 2, 213562, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213565, '删除', 'lghjft:nrgl-bbfb:delete', 3, 3, 213562, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213566, '查询', 'lghjft:nrgl-bbfb:query', 3, 4, 213562, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-01-20 10:24:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213531, '办事地图', '', 2, 0, 0, '/map', 'ep:basketball', 'lghjft/map/index', 'MarkerInfo', 0, true, true, true, '', '2026-01-20 14:32:55', '1', '2026-01-20 15:11:04',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213532, '高德地图标注点信息查询', 'lghjft:marker-info:query', 3, 1, 213531, '', '', '', null, 0, true, true, true, '', '2026-01-20 14:32:55', '', '2026-01-20 14:32:55',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213533, '高德地图标注点信息创建', 'lghjft:marker-info:create', 3, 2, 213531, '', '', '', '', 0, true, true, true, '', '2026-01-20 14:32:55', '1', '2026-01-20 15:11:30',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213534, '高德地图标注点信息更新', 'lghjft:marker-info:update', 3, 3, 213531, '', '', '', null, 0, true, true, true, '', '2026-01-20 14:32:55', '', '2026-01-20 14:32:55',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213535, '高德地图标注点信息删除', 'lghjft:marker-info:delete', 3, 4, 213531, '', '', '', null, 0, true, true, true, '', '2026-01-20 14:32:55', '', '2026-01-20 14:32:55',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213536, '高德地图标注点信息导出', 'lghjft:marker-info:export', 3, 5, 213531, '', '', '', null, 0, true, true, true, '', '2026-01-20 14:32:55', '', '2026-01-20 14:32:55',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213591, '户籍管理', '', 1, 15, 0, '/hjgl', 'ep:avatar', '', '', 0, true, true, true, '1', '2026-01-16 10:47:17', '1', '2026-02-14 00:32:59', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213592, '基础信息', '', 2, 0, 213591, 'jcxx', 'ep:edit-pen', '/lghjft/hjgl/jcxx/index', 'GhHjController', 0, true, true, true, '1', '2026-01-16 10:47:17', '1',
        '2026-02-14 00:36:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213593, '标签管理', '', 2, 1, 213591, 'bqgl', 'ep:collection-tag', '/lghjft/hjgl/bqgl/index', 'BqglController', 0, true, true, true, '1', '2026-01-16 10:47:17', '1',
        '2026-02-15 00:43:12', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213594, '创建', 'lghjft:hjgl-jcxx:create', 3, 1, 213592, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213595, '更新', 'lghjft:hjgl-jcxx:update', 3, 2, 213592, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213596, '删除', 'lghjft:hjgl-jcxx:delete', 3, 3, 213592, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213597, '查询', 'lghjft:hjgl-jcxx:query', 3, 4, 213592, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213598, '创建', 'lghjft:hjgl-bqgl:create', 3, 1, 213593, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213599, '更新', 'lghjft:hjgl-bqgl:update', 3, 2, 213593, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213600, '删除', 'lghjft:hjgl-bqgl:delete', 3, 3, 213593, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213601, '查询', 'lghjft:hjgl-bqgl:query', 3, 4, 213593, '', '', '', '', 0, true, true, true, '1', '2026-01-20 10:21:16', '1', '2026-02-14 21:24:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201185, '工作流程', '', 1, 50, 0, '/bpm', 'fa:medium', null, null, 0, true, true, true, '1', '2021-12-30 20:26:36', '1', '2024-02-29 12:43:43', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (200005, 'OA 示例', '', 1, 40, 201185, 'oa', 'fa:road', null, null, 0, true, true, true, 'admin', '2021-09-20 16:26:19', '1', '2024-02-29 12:38:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201186, '流程管理', '', 1, 10, 201185, 'manager', 'fa:dedent', null, null, 0, true, true, true, '1', '2021-12-30 20:28:30', '1', '2024-02-29 12:36:02', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201118, '请假查询', '', 2, 0, 200005, 'leave', 'fa:leanpub', 'bpm/oa/leave/index', 'BpmOALeave', 0, true, true, true, '', '2021-09-20 08:51:03', '1', '2024-02-29 12:38:21',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201187, '流程表单', '', 2, 2, 201186, 'form', 'fa:hdd-o', 'bpm/form/index', 'BpmForm', 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2024-03-19 12:25:25', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201193, '流程模型', '', 2, 1, 201186, 'model', 'fa-solid:project-diagram', 'bpm/model/index', 'BpmModel', 0, true, true, true, '1', '2021-12-31 23:24:58', '1',
        '2024-03-19 12:25:19', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201209, '用户分组', '', 2, 4, 201186, 'user-group', 'fa:user-secret', 'bpm/group/index', 'BpmUserGroup', 0, true, true, true, '', '2022-01-14 02:14:20', '1',
        '2024-03-21 23:55:29', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202714, '流程分类', '', 2, 3, 201186, 'category', 'fa:object-ungroup', 'bpm/category/index', 'BpmCategory', 0, true, true, true, '', '2024-03-08 02:00:51', '1',
        '2024-03-21 23:51:18', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202721, '流程实例', '', 2, 10, 201186, 'process-instance/manager', 'fa:square', 'bpm/processInstance/manager/index', 'BpmProcessInstanceManager', 0, true, true, true, '1',
        '2024-03-21 23:57:30', '1', '2024-03-21 23:57:30', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202724, '流程任务', '', 2, 11, 201186, 'process-tasnk', 'ep:collection-tag', 'bpm/task/manager/index', 'BpmManagerTask', 0, true, true, true, '1', '2024-03-22 08:43:22',
        '1', '2024-03-22 08:43:27', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202726, '流程监听器', '', 2, 5, 201186, 'process-listener', 'fa:assistive-listening-systems', 'bpm/processListener/index', 'BpmProcessListener', 0, true, true, true, '',
        '2024-03-09 16:05:34', '1', '2024-03-23 13:13:38', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202731, '流程表达式', '', 2, 6, 201186, 'process-expression', 'fa:wpexplorer', 'bpm/processExpression/index', 'BpmProcessExpression', 0, true, true, true, '',
        '2024-03-09 22:35:08', '1', '2024-03-23 19:43:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201119, '请假申请查询', 'bpm:oa-leave:query', 3, 1, 201118, '', '', '', null, 0, true, true, true, '', '2021-09-20 08:51:03', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201120, '请假申请创建', 'bpm:oa-leave:create', 3, 2, 201118, '', '', '', null, 0, true, true, true, '', '2021-09-20 08:51:03', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201188, '表单查询', 'bpm:form:query', 3, 1, 201187, '', '', '', null, 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201189, '表单创建', 'bpm:form:create', 3, 2, 201187, '', '', '', null, 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201190, '表单更新', 'bpm:form:update', 3, 3, 201187, '', '', '', null, 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201191, '表单删除', 'bpm:form:delete', 3, 4, 201187, '', '', '', null, 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201192, '表单导出', 'bpm:form:export', 3, 5, 201187, '', '', '', null, 0, true, true, true, '', '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201194, '模型查询', 'bpm:model:query', 3, 1, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-03 19:01:10', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201195, '模型创建', 'bpm:model:create', 3, 2, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-03 19:01:24', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201197, '模型更新', 'bpm:model:update', 3, 4, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-03 19:02:28', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201198, '模型删除', 'bpm:model:delete', 3, 5, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-03 19:02:43', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201199, '模型发布', 'bpm:model:deploy', 3, 6, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-03 19:03:24', '1', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201210, '用户组查询', 'bpm:user-group:query', 3, 1, 201209, '', '', '', null, 0, true, true, true, '', '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201211, '用户组创建', 'bpm:user-group:create', 3, 2, 201209, '', '', '', null, 0, true, true, true, '', '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201212, '用户组更新', 'bpm:user-group:update', 3, 3, 201209, '', '', '', null, 0, true, true, true, '', '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201213, '用户组删除', 'bpm:user-group:delete', 3, 4, 201209, '', '', '', null, 0, true, true, true, '', '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201215, '流程定义查询', 'bpm:process-definition:query', 3, 10, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:21:43', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201216, '流程任务分配规则查询', 'bpm:task-assign-rule:query', 3, 20, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:26:53', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201217, '流程任务分配规则创建', 'bpm:task-assign-rule:create', 3, 21, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:28:15', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (201218, '流程任务分配规则更新', 'bpm:task-assign-rule:update', 3, 22, 201193, '', '', '', null, 0, true, true, true, '1', '2022-01-23 00:28:41', '1', '2022-04-20 17:03:10',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202715, '分类查询', 'bpm:category:query', 3, 1, 202714, '', '', '', '', 0, true, true, true, '', '2024-03-08 02:00:51', '1', '2024-03-19 14:36:25', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202716, '分类创建', 'bpm:category:create', 3, 2, 202714, '', '', '', '', 0, true, true, true, '', '2024-03-08 02:00:51', '1', '2024-03-19 14:36:31', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202717, '分类更新', 'bpm:category:update', 3, 3, 202714, '', '', '', '', 0, true, true, true, '', '2024-03-08 02:00:51', '1', '2024-03-19 14:36:35', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202718, '分类删除', 'bpm:category:delete', 3, 4, 202714, '', '', '', '', 0, true, true, true, '', '2024-03-08 02:00:51', '1', '2024-03-19 14:36:41', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202722, '流程实例的查询（管理员）', 'bpm:process-instance:manager-query', 3, 1, 202721, '', '', '', '', 0, true, true, true, '1', '2024-03-22 08:18:27', '1',
        '2024-03-22 08:19:05', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202723, '流程实例的取消（管理员）', 'bpm:process-instance:cancel-by-admin', 3, 2, 202721, '', '', '', '', 0, true, true, true, '1', '2024-03-22 08:19:25', '1',
        '2024-03-22 08:19:25', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202725, '流程任务的查询（管理员）', 'bpm:task:mananger-query', 3, 1, 202724, '', '', '', '', 0, true, true, true, '1', '2024-03-22 08:43:49', '1', '2024-03-22 08:43:49',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202727, '流程监听器查询', 'bpm:process-listener:query', 3, 1, 202726, '', '', '', null, 0, true, true, true, '', '2024-03-09 16:05:34', '', '2024-03-09 16:05:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202728, '流程监听器创建', 'bpm:process-listener:create', 3, 2, 202726, '', '', '', null, 0, true, true, true, '', '2024-03-09 16:05:34', '', '2024-03-09 16:05:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202729, '流程监听器更新', 'bpm:process-listener:update', 3, 3, 202726, '', '', '', null, 0, true, true, true, '', '2024-03-09 16:05:34', '', '2024-03-09 16:05:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202730, '流程监听器删除', 'bpm:process-listener:delete', 3, 4, 202726, '', '', '', null, 0, true, true, true, '', '2024-03-09 16:05:34', '', '2024-03-09 16:05:34', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202732, '流程表达式查询', 'bpm:process-expression:query', 3, 1, 202731, '', '', '', null, 0, true, true, true, '', '2024-03-09 22:35:08', '', '2024-03-09 22:35:08', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202733, '流程表达式创建', 'bpm:process-expression:create', 3, 2, 202731, '', '', '', null, 0, true, true, true, '', '2024-03-09 22:35:08', '', '2024-03-09 22:35:08',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202734, '流程表达式更新', 'bpm:process-expression:update', 3, 3, 202731, '', '', '', null, 0, true, true, true, '', '2024-03-09 22:35:08', '', '2024-03-09 22:35:08',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202735, '流程表达式删除', 'bpm:process-expression:delete', 3, 4, 202731, '', '', '', null, 0, true, true, true, '', '2024-03-09 22:35:08', '', '2024-03-09 22:35:08',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (202913, '流程清理', 'bpm:model:clean', 3, 7, 201193, '', '', '', '', 0, true, true, true, '1', '2025-01-17 19:32:06', '1', '2025-01-17 19:32:06', false);


# migrate data from sys_role_menu to system_role_menu
select *
from system_role_menu;
delete
from system_role_menu
where true;
insert into system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
select role_id,
       100000 + menu_id,
       'admin',
       current_timestamp(),
       'admin',
       current_timestamp(),
       cast(b'0' as unsigned) as deleted,
       1
from sys_role_menu
union all
select 1,
       id,
       'admin',
       current_timestamp(),
       null,
       current_timestamp(),
       cast(b'0' as unsigned) as deleted,
       1
from system_menu
where id like '2%'
   or id = '100000';

INSERT INTO system_oauth2_client (id, client_id, secret, name, logo, description, status, access_token_validity_seconds, refresh_token_validity_seconds, redirect_uris,
                                  authorized_grant_types, scopes, auto_approve_scopes, authorities, resource_ids, additional_information, creator, create_time, updater,
                                  update_time, deleted)
VALUES (1, 'default', 'admin123', '芋道源码', 'http://test.yudao.iocoder.cn/20250502/sort2_1746189740718.png', '我是描述', 0, 1800, 2592000,
        '["https://www.iocoder.cn","https://doc.iocoder.cn"]', '["password","authorization_code","implicit","refresh_token","client_credentials"]', '["user.read","user.write"]',
        '[]', '["user.read","user.write"]', '[]', '{}', '1', '2022-05-11 21:47:12', '1', '2025-08-21 10:04:50', false);


INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1, '用户性别', 'system_user_sex', 0, null, 'admin', '2021-01-05 17:03:48', '1', '2022-05-16 20:29:32', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (6, '参数类型', 'infra_config_type', 0, null, 'admin', '2021-01-05 17:03:48', '', '2022-02-01 16:36:54', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (7, '通知类型', 'system_notice_type', 0, null, 'admin', '2021-01-05 17:03:48', '', '2022-02-01 16:35:26', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (9, '操作类型', 'infra_operate_type', 0, null, 'admin', '2021-01-05 17:03:48', '1', '2024-03-14 12:44:01', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (10, '系统状态', 'common_status', 0, null, 'admin', '2021-01-05 17:03:48', '', '2022-02-01 16:21:28', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (11, 'Boolean 是否类型', 'infra_boolean_string', 0, 'boolean 转是否', '', '2021-01-19 03:20:08', '', '2022-02-01 16:37:10', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (104, '登陆结果', 'system_login_result', 0, '登陆结果', '', '2021-01-18 06:17:11', '', '2022-02-01 16:36:00', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (106, '代码生成模板类型', 'infra_codegen_template_type', 0, null, '', '2021-02-05 07:08:06', '1', '2022-05-16 20:26:50', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (107, '定时任务状态', 'infra_job_status', 0, null, '', '2021-02-07 07:44:16', '', '2022-02-01 16:51:11', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (108, '定时任务日志状态', 'infra_job_log_status', 0, null, '', '2021-02-08 10:03:51', '', '2022-02-01 16:50:43', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (109, '用户类型', 'user_type', 0, null, '', '2021-02-26 00:15:51', '', '2021-02-26 00:15:51', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (110, 'API 异常数据的处理状态', 'infra_api_error_log_process_status', 0, null, '', '2021-02-26 07:07:01', '', '2022-02-01 16:50:53', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (111, '短信渠道编码', 'system_sms_channel_code', 0, null, '1', '2021-04-05 01:04:50', '1', '2022-02-16 02:09:08', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (112, '短信模板的类型', 'system_sms_template_type', 0, null, '1', '2021-04-05 21:50:43', '1', '2022-02-01 16:35:06', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (113, '短信发送状态', 'system_sms_send_status', 0, null, '1', '2021-04-11 20:18:03', '1', '2022-02-01 16:35:09', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (114, '短信接收状态', 'system_sms_receive_status', 0, null, '1', '2021-04-11 20:27:14', '1', '2022-02-01 16:35:14', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (116, '登陆日志的类型', 'system_login_type', 0, '登陆日志的类型', '1', '2021-10-06 00:50:46', '1', '2022-02-01 16:35:56', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (117, 'OA 请假类型', 'bpm_oa_leave_type', 0, null, '1', '2021-09-21 22:34:33', '1', '2022-01-22 10:41:37', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (130, '支付渠道编码类型', 'pay_channel_code', 0, '支付渠道的编码', '1', '2021-12-03 10:35:08', '1', '2023-07-10 10:11:39', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (131, '支付回调状态', 'pay_notify_status', 0, '支付回调状态（包括退款回调）', '1', '2021-12-03 10:53:29', '1', '2023-07-19 18:09:43', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (132, '支付订单状态', 'pay_order_status', 0, '支付订单状态', '1', '2021-12-03 11:17:50', '1', '2021-12-03 11:17:50', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (134, '退款订单状态', 'pay_refund_status', 0, '退款订单状态', '1', '2021-12-10 16:42:50', '1', '2023-07-19 10:13:17', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (139, '流程实例的状态', 'bpm_process_instance_status', 0, '流程实例的状态', '1', '2022-01-07 23:46:42', '1', '2022-01-07 23:46:42', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (140, '流程实例的结果', 'bpm_task_status', 0, '流程实例的结果', '1', '2022-01-07 23:48:10', '1', '2024-03-08 22:42:03', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (141, '流程的表单类型', 'bpm_model_form_type', 0, '流程的表单类型', '103', '2022-01-11 23:50:45', '103', '2022-01-11 23:50:45', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (142, '任务分配规则的类型', 'bpm_task_candidate_strategy', 0, 'BPM 任务的候选人的策略', '103', '2022-01-12 23:21:04', '103', '2024-03-06 02:53:59', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (144, '代码生成的场景枚举', 'infra_codegen_scene', 0, '代码生成的场景枚举', '1', '2022-02-02 13:14:45', '1', '2022-03-10 16:33:46', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (145, '角色类型', 'system_role_type', 0, '角色类型', '1', '2022-02-16 13:01:46', '1', '2022-02-16 13:01:46', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (146, '文件存储器', 'infra_file_storage', 0, '文件存储器', '1', '2022-03-15 00:24:38', '1', '2022-03-15 00:24:38', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (147, 'OAuth 2.0 授权类型', 'system_oauth2_grant_type', 0, 'OAuth 2.0 授权类型（模式）', '1', '2022-05-12 00:20:52', '1', '2022-05-11 16:25:49', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (149, '商品 SPU 状态', 'product_spu_status', 0, '商品 SPU 状态', '1', '2022-10-24 21:19:04', '1', '2022-10-24 21:19:08', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (150, '优惠类型', 'promotion_discount_type', 0, '优惠类型', '1', '2022-11-01 12:46:06', '1', '2022-11-01 12:46:06', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (151, '优惠劵模板的有限期类型', 'promotion_coupon_template_validity_type', 0, '优惠劵模板的有限期类型', '1', '2022-11-02 00:06:20', '1', '2022-11-04 00:08:26', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (152, '营销的商品范围', 'promotion_product_scope', 0, '营销的商品范围', '1', '2022-11-02 00:28:01', '1', '2022-11-02 00:28:01', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (153, '优惠劵的状态', 'promotion_coupon_status', 0, '优惠劵的状态', '1', '2022-11-04 00:14:49', '1', '2022-11-04 00:14:49', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (154, '优惠劵的领取方式', 'promotion_coupon_take_type', 0, '优惠劵的领取方式', '1', '2022-11-04 19:12:27', '1', '2022-11-04 19:12:27', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (155, '促销活动的状态', 'promotion_activity_status', 0, '促销活动的状态', '1', '2022-11-04 22:54:23', '1', '2022-11-04 22:54:23', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (156, '营销的条件类型', 'promotion_condition_type', 0, '营销的条件类型', '1', '2022-11-04 22:59:23', '1', '2022-11-04 22:59:23', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (157, '交易售后状态', 'trade_after_sale_status', 0, '交易售后状态', '1', '2022-11-19 20:52:56', '1', '2022-11-19 20:52:56', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (158, '交易售后的类型', 'trade_after_sale_type', 0, '交易售后的类型', '1', '2022-11-19 21:04:09', '1', '2022-11-19 21:04:09', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (159, '交易售后的方式', 'trade_after_sale_way', 0, '交易售后的方式', '1', '2022-11-19 21:39:04', '1', '2022-11-19 21:39:04', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (160, '终端', 'terminal', 0, '终端', '1', '2022-12-10 10:50:50', '1', '2022-12-10 10:53:11', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (161, '交易订单的类型', 'trade_order_type', 0, '交易订单的类型', '1', '2022-12-10 16:33:54', '1', '2022-12-10 16:33:54', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (162, '交易订单的状态', 'trade_order_status', 0, '交易订单的状态', '1', '2022-12-10 16:48:44', '1', '2022-12-10 16:48:44', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (163, '交易订单项的售后状态', 'trade_order_item_after_sale_status', 0, '交易订单项的售后状态', '1', '2022-12-10 20:58:08', '1', '2022-12-10 20:58:08', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (164, '公众号自动回复的请求关键字匹配模式', 'mp_auto_reply_request_match', 0, '公众号自动回复的请求关键字匹配模式', '1', '2023-01-16 23:29:56', '1', '2023-01-16 23:29:56',
        false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (165, '公众号的消息类型', 'mp_message_type', 0, '公众号的消息类型', '1', '2023-01-17 22:17:09', '1', '2023-01-17 22:17:09', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (166, '邮件发送状态', 'system_mail_send_status', 0, '邮件发送状态', '1', '2023-01-26 09:53:13', '1', '2023-01-26 09:53:13', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (167, '站内信模版的类型', 'system_notify_template_type', 0, '站内信模版的类型', '1', '2023-01-28 10:35:10', '1', '2023-01-28 10:35:10', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (168, '代码生成的前端类型', 'infra_codegen_front_type', 0, '', '1', '2023-04-12 23:57:52', '1', '2023-04-12 23:57:52', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (170, '快递计费方式', 'trade_delivery_express_charge_mode', 0, '用于商城交易模块配送管理', '1', '2023-05-21 22:45:03', '1', '2023-05-21 22:45:03', false,
        '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (171, '积分业务类型', 'member_point_biz_type', 0, '', '1', '2023-06-10 12:15:00', '1', '2023-06-28 13:48:20', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (173, '支付通知类型', 'pay_notify_type', 0, null, '1', '2023-07-20 12:23:03', '1', '2023-07-20 12:23:03', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (174, '会员经验业务类型', 'member_experience_biz_type', 0, null, '', '2023-08-22 12:41:01', '', '2023-08-22 12:41:01', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (175, '交易配送类型', 'trade_delivery_type', 0, '', '1', '2023-08-23 00:03:14', '1', '2023-08-23 00:03:14', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (176, '分佣模式', 'brokerage_enabled_condition', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (177, '分销关系绑定模式', 'brokerage_bind_mode', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (178, '佣金提现类型', 'brokerage_withdraw_type', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (179, '佣金记录业务类型', 'brokerage_record_biz_type', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (180, '佣金记录状态', 'brokerage_record_status', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (181, '佣金提现状态', 'brokerage_withdraw_status', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (182, '佣金提现银行', 'brokerage_bank_name', 0, null, '', '2023-09-28 02:46:05', '', '2023-09-28 02:46:05', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (183, '砍价记录的状态', 'promotion_bargain_record_status', 0, '', '1', '2023-10-05 10:41:08', '1', '2023-10-05 10:41:08', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (184, '拼团记录的状态', 'promotion_combination_record_status', 0, '', '1', '2023-10-08 07:24:25', '1', '2023-10-08 07:24:25', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (185, '回款-回款方式', 'crm_receivable_return_type', 0, '回款-回款方式', '1', '2023-10-18 21:54:10', '1', '2023-10-18 21:54:10', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (186, 'CRM 客户行业', 'crm_customer_industry', 0, 'CRM 客户所属行业', '1', '2023-10-28 22:57:07', '1', '2024-02-18 23:30:22', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (187, '客户等级', 'crm_customer_level', 0, 'CRM 客户等级', '1', '2023-10-28 22:59:12', '1', '2023-10-28 15:11:16', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (188, '客户来源', 'crm_customer_source', 0, 'CRM 客户来源', '1', '2023-10-28 23:00:34', '1', '2023-10-28 15:11:16', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (600, 'Banner 位置', 'promotion_banner_position', 0, '', '1', '2023-10-08 07:24:25', '1', '2023-11-04 13:04:02', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (601, '社交类型', 'system_social_type', 0, '', '1', '2023-11-04 13:03:54', '1', '2023-11-04 13:03:54', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (604, '产品状态', 'crm_product_status', 0, '', '1', '2023-10-30 21:47:59', '1', '2023-10-30 21:48:45', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (605, 'CRM 数据权限的级别', 'crm_permission_level', 0, '', '1', '2023-11-30 09:51:59', '1', '2023-11-30 09:51:59', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (606, 'CRM 审批状态', 'crm_audit_status', 0, '', '1', '2023-11-30 18:56:23', '1', '2023-11-30 18:56:23', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (607, 'CRM 产品单位', 'crm_product_unit', 0, '', '1', '2023-12-05 23:01:51', '1', '2023-12-05 23:01:51', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (608, 'CRM 跟进方式', 'crm_follow_up_type', 0, '', '1', '2024-01-15 20:48:05', '1', '2024-01-15 20:48:05', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (610, '转账订单状态', 'pay_transfer_status', 0, '', '1', '2023-10-28 16:18:32', '1', '2023-10-28 16:18:32', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (611, 'ERP 库存明细的业务类型', 'erp_stock_record_biz_type', 0, 'ERP 库存明细的业务类型', '1', '2024-02-05 18:07:02', '1', '2024-02-05 18:07:02', false,
        '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (612, 'ERP 审批状态', 'erp_audit_status', 0, '', '1', '2024-02-06 00:00:07', '1', '2024-02-06 00:00:07', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (613, 'BPM 监听器类型', 'bpm_process_listener_type', 0, '', '1', '2024-03-23 12:52:24', '1', '2024-03-09 15:54:28', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (615, 'BPM 监听器值类型', 'bpm_process_listener_value_type', 0, '', '1', '2024-03-23 13:00:31', '1', '2024-03-23 13:00:31', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (616, '时间间隔', 'date_interval', 0, '', '1', '2024-03-29 22:50:09', '1', '2024-03-29 22:50:09', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (619, 'CRM 商机结束状态类型', 'crm_business_end_status_type', 0, '', '1', '2024-04-13 23:23:00', '1', '2024-04-13 23:23:00', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (620, 'AI 模型平台', 'ai_platform', 0, '', '1', '2024-05-09 22:27:38', '1', '2024-05-09 22:27:38', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (621, 'AI 绘画状态', 'ai_image_status', 0, '', '1', '2024-06-26 20:51:23', '1', '2024-06-26 20:51:23', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (622, 'AI 音乐状态', 'ai_music_status', 0, '', '1', '2024-06-27 22:45:07', '1', '2024-06-28 00:56:27', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (623, 'AI 音乐生成模式', 'ai_generate_mode', 0, '', '1', '2024-06-27 22:46:21', '1', '2024-06-28 01:22:29', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (624, '写作语气', 'ai_write_tone', 0, '', '1', '2024-07-07 15:19:02', '1', '2024-07-07 15:19:02', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (625, '写作语言', 'ai_write_language', 0, '', '1', '2024-07-07 15:18:52', '1', '2024-07-07 15:18:52', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (626, '写作长度', 'ai_write_length', 0, '', '1', '2024-07-07 15:18:41', '1', '2024-07-07 15:18:41', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (627, '写作格式', 'ai_write_format', 0, '', '1', '2024-07-07 15:14:34', '1', '2024-07-07 15:14:34', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (628, 'AI 写作类型', 'ai_write_type', 0, '', '1', '2024-07-10 21:25:29', '1', '2024-07-10 21:25:29', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (629, 'BPM 流程模型类型', 'bpm_model_type', 0, '', '1', '2024-08-26 15:21:43', '1', '2024-08-26 15:21:43', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (640, 'AI 模型类型', 'ai_model_type', 0, '', '1', '2025-03-03 12:24:07', '1', '2025-03-03 12:24:07', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1001, 'IoT 产品设备类型', 'iot_product_device_type', 0, '', '1', '2024-08-10 11:54:30', '1', '2025-03-17 09:25:08', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1002, 'IoT 产品状态', 'iot_product_status', 0, '', '1', '2024-08-10 12:06:09', '1', '2025-03-17 09:25:10', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1004, 'IoT 联网方式', 'iot_net_type', 0, '', '1', '2024-09-06 22:04:13', '1', '2025-03-17 09:25:14', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1006, 'IoT 设备状态', 'iot_device_state', 0, '', '1', '2024-09-21 08:12:55', '1', '2025-03-17 09:25:19', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1007, 'IoT 物模型功能类型', 'iot_thing_model_type', 0, '', '1', '2024-09-29 20:02:36', '1', '2025-03-17 09:25:24', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1011, 'IoT 物模型单位', 'iot_thing_model_unit', 0, '', '1', '2024-12-25 17:36:46', '1', '2025-03-17 09:25:35', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1013, 'IoT 数据流转目的的类型枚举', 'iot_data_sink_type_enum', 0, '', '1', '2025-03-09 12:39:36', '1', '2025-06-24 12:45:24', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1014, 'IoT 场景流转的触发类型枚举', 'iot_rule_scene_trigger_type_enum', 0, '', '1', '2025-03-20 14:59:44', '1', '2025-03-20 14:59:44', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1015, 'IoT 设备消息类型枚举', 'iot_device_message_type_enum', 0, '', '1', '2025-03-20 15:01:15', '1', '2025-03-20 15:01:15', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (1016, 'IoT 规则场景的触发类型枚举', 'iot_rule_scene_action_type_enum', 0, '', '1', '2025-03-28 15:26:54', '1', '2025-03-28 15:29:13', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2000, 'IoT 数据格式', 'iot_codec_type', 0, 'IoT 编解码器类型', '1', '2025-06-12 22:55:46', '1', '2025-06-12 22:55:46', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2001, 'IoT 告警级别', 'iot_alert_level', 0, '', '1', '2025-06-27 20:30:57', '1', '2025-06-27 20:30:57', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2002, 'IoT 告警', 'iot_alert_receive_type', 0, '', '1', '2025-06-27 22:49:19', '1', '2025-06-27 22:49:19', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2003, 'IoT 固件设备范围', 'iot_ota_task_device_scope', 0, '', '1', '2025-07-02 09:42:49', '1', '2025-07-02 09:42:49', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2004, 'IoT 固件升级任务状态', 'iot_ota_task_status', 0, '', '1', '2025-07-02 09:43:43', '1', '2025-07-02 09:43:43', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2005, 'IoT 固件升级记录状态', 'iot_ota_task_record_status', 0, '', '1', '2025-07-02 09:45:02', '1', '2025-07-02 09:45:02', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2006, 'IoT 定位类型', 'iot_location_type', 0, '', '1', '2025-07-05 09:56:25', '1', '2025-07-05 09:56:25', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2007, 'AI MCP 客户端名字', 'ai_mcp_client_name', 0, '', '1', '2025-08-28 13:57:40', '1', '2025-08-28 13:57:40', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2008, 'is_leaf', '是否节点', 0, '', '1', '2025-12-31 12:52:31', '1', '2025-12-31 12:56:38', true, '2025-12-31 12:56:21');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2009, '是否节点', 'is_leaf', 0, '', '1', '2025-12-31 12:56:38', '1', '2025-12-31 13:13:02', true, '2025-12-31 13:12:45');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2010, '比例划分', 'sys_blhf_gh', 0, '', '1', '2026-01-07 08:52:36', '1', '2026-01-07 08:52:36', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2011, '有效标志', 'sys_fpbl_gh', 0, '', '1', '2026-01-07 09:28:24', '1', '2026-01-07 09:28:24', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2012, '大类标识', 'sys_hjfl_gh', 0, '', '1', '2026-01-08 09:46:59', '1', '2026-01-08 09:46:59', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2013, '年度', 'sys_nd', 0, '', '1', '2026-01-08 15:06:19', '1', '2026-01-08 15:06:19', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2014, '任务类型', 'sys_rwlx', 0, '', '1', '2026-01-08 15:06:34', '1', '2026-01-08 15:06:34', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2015, '纳税人状态	', 'sys_nsrzt', 0, '', '1', '2026-01-09 11:08:42', '1', '2026-01-09 14:54:23', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2016, '街道乡镇', 'sys_jdxz', 0, null, '1', '2026-01-09 11:14:54', '', '2026-01-09 11:14:54', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2017, '工会组织状态	', 'sys_ghzt', 0, null, '', '2026-01-09 12:01:14', '', '2026-01-09 12:01:14', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2018, '工会组织类型', 'sys_ghlx', 0, null, '', '2026-01-09 12:57:29', '', '2026-01-09 12:57:29', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2019, '成立工会标志', 'sys_jhbz', 0, null, '', '2026-01-09 12:57:48', '', '2026-01-09 12:57:48', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2020, '税务机关', 'sys_swjg_type', 0, null, '', '2026-01-09 16:19:00', '', '2026-01-09 16:19:00', false, null);
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2021, '问题处理', 'lg_wtfk_status', 0, '1-未处理, 3-已处理, 2-跟进中', '1', '2026-01-20 08:52:46', '1', '2026-01-20 16:16:28', false, '1970-01-01 00:00:00');
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2022, '工会级别', 'gh_marker_type', 0, '工会级别', '1', '2026-01-20 15:15:29', '1', '2026-01-20 15:15:29', false, '1970-01-01 00:00:00');

insert into gh_hj_jcxx(dept_id, hyghbz, djxh, shxydm, nsrmc, nsrjc, zgswj_dm, zgswjmc, zgswskfj_dm, zgswskfjmc, ssgly_dm, ssglyxm, zzjglx_dm, zzjglxmc, hy_dm, hymc, djzclx_dm,
                       djzclxmc, dwlsgx_dm, dwlsgxmc, zgrs, nsrzt_dm, nsrztmc, fzcrq, zxrq, zcdz, yzbm, lxr, lxdh, ghlb_dm, xtlb_dm, hjfl1_dm, hjfl2_dm, hjfl3_dm, hjfl4_dm,
                       hjfl5_dm, hjfl6_dm, hjfl7_dm, hjfl8_dm, hjfl9_dm, hjfl10_dm, sdghjg_dm, clghbj, clghrq, jcghdm, jcghmc, jcghzh, jcghhm, jcghhh, jcghyh, bz, jym, nsrsbh,
                       creator, create_time, updater, update_time, file_url, jdxz_dm, sjtb_sj, deleted)
select dept_id,
       hyghbz,
       djxh,
       shxydm,
       nsrmc,
       nsrjc,
       zgswj_dm,
       zgswjmc,
       zgswskfj_dm,
       zgswskfjmc,
       ssgly_dm,
       ssglyxm,
       zzjglx_dm,
       zzjglxmc,
       hy_dm,
       hymc,
       djzclx_dm,
       djzclxmc,
       dwlsgx_dm,
       dwlsgxmc,
       zgrs,
       nsrzt_dm,
       nsrztmc,
       fzcrq,
       zxrq,
       zcdz,
       yzbm,
       lxr,
       lxdh,
       ghlb_dm,
       xtlb_dm,
       hjfl1_dm,
       hjfl2_dm,
       hjfl3_dm,
       hjfl4_dm,
       hjfl5_dm,
       hjfl6_dm,
       hjfl7_dm,
       hjfl8_dm,
       hjfl9_dm,
       hjfl10_dm,
       sdghjg_dm,
       clghbj,
       clghrq,
       jcghdm,
       jcghmc,
       jcghzh,
       jcghhm,
       jcghhh,
       jcghyh,
       bz,
       jym,
       nsrsbh,
       create_by,
       create_time,
       update_by,
       update_time,
       fileurl,
       jdxz_dm,
       sjtb_sj,
       cast(b'0' as unsigned)
from gh_hj;
# migrate data from gsghds.sys_dept to gh_gy_dept_kzxx
drop table if exists gh_gy_dept_kzxx;
create table gh_gy_dept_kzxx
(
    dept_id     bigint       not null comment '部门ID',
    hyghbz      char(1)      not null comment '行业工会标志，N（一般工会）Y（行业工会）S（产业系统）T（特殊工会）',
    xzjb        char(1)      not null comment '行政级别',
    xzqhfw      varchar(114) null comment '行政区划范围',
    jym         char(100)    null comment '校验码',
    yhzh        varchar(40)  null comment '银行账号',
    yhhm        varchar(60)  null comment '银行户名',
    yhhh        char(12)     null comment '银行行号',
    yhmc        varchar(60)  null comment '银行名称',
    creator     varchar(64)  null comment '创建者',
    create_time datetime default CURRENT_TIMESTAMP,
    updater     varchar(64)  null comment '更新者',
    update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) comment '公用-部门扩展信息';
delete
from gh_gy_dept_kzxx
where true;
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
    yhzh        varchar(40) not null comment '银行账号',
    yhhm        varchar(60) not null comment '银行户名',
    yhhh        char(12)    not null comment '银行行号',
    yhmc        varchar(60) not null comment '银行名称',
    creator     varchar(64) null comment '创建者',
    create_time datetime default CURRENT_TIMESTAMP,
    updater     varchar(64) null comment '更新者',
    update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) comment '历史-银行信息';
delete
from gh_ls_yhxx
where true;
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


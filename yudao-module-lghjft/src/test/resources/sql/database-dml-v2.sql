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
       'ep:cherry'            as icon,
       null                   as component,
       null                   as component_name,
       cast(b'0' as unsigned) as status,
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
       if(status = '0', cast(b'0' as unsigned), cast(b'1' as unsigned)) as status,
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
VALUES (214690, '报表快照', '', 2, 3, 201281, 'bbhc', 'ep:files', 'report/bbhc/index', 'ReportBbhc', 0, true, true, true, '1', '2026-03-24 08:00:00', '1',
        '2026-03-24 08:00:00', false);
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
VALUES (214691, '报表快照查询', 'report:bbhc:query', 3, 1, 214690, '', '', '', null, 0, true, true, true, '1', '2026-03-24 08:00:00', '1', '2026-03-24 08:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (214692, '报表快照重生成', 'report:bbhc:update', 3, 2, 214690, '', '', '', null, 0, true, true, true, '1', '2026-03-24 08:00:00', '1', '2026-03-24 08:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (214693, '报表快照清理', 'report:bbhc:delete', 3, 3, 214690, '', '', '', null, 0, true, true, true, '1', '2026-03-24 08:00:00', '1', '2026-03-24 08:00:00', false);
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
VALUES (213644, '汇拨征收', '', 1, 2, 100000, 'hbzz', 'ep:coin', null, null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213645, '单位缴费台账', '', 2, 1, 213644, 'jfmx', 'ep:document', '/lghjft/hbzz/jfmx/index', 'LghjftHbzzJfmx', 0, true, true, true, '1', '2026-03-06 09:00:00', '1',
        '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213646, '单位缴费台账查询', 'lghjft:jf:query', 3, 1, 213645, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213647, '单位缴费台账导出', 'lghjft:jf:export', 3, 2, 213645, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213648, '单位返拨查询', '', 2, 2, 213644, 'hkxx', 'ep:wallet-filled', '/lghjft/hbzz/hkxx/index', 'LghjftHbzzHkxx', 0, true, true, true, '1', '2026-03-06 09:00:00', '1',
        '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213649, '单位返拨查询查看', 'lghjft:hkxx:query', 3, 1, 213648, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213650, '单位返拨查询处理', 'lghjft:hkxx:update', 3, 2, 213648, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213651, '单位返拨查询导出', 'lghjft:hkxx:export', 3, 3, 213648, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213652, '应代收单位', '', 2, 2, 205047, 'jhdwyds', 'ep:office-building', '/lghjft/sjwh/jhdwyds/index', 'LghjftSjwhJhdwyds', 0, true, true, true, '1', '2026-03-06 09:00:00',
        '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213653, '应代收单位查询', 'lghjft:jhdwyds:query', 3, 1, 213652, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213654, '应代收单位创建', 'lghjft:jhdwyds:create', 3, 2, 213652, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213655, '应代收单位更新', 'lghjft:jhdwyds:update', 3, 3, 213652, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213656, '应代收单位删除', 'lghjft:jhdwyds:delete', 3, 4, 213652, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213657, '应代收单位导出', 'lghjft:jhdwyds:export', 3, 5, 213652, '', '', '', null, 0, true, true, true, '1', '2026-03-06 09:00:00', '1', '2026-03-06 09:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213468, '流程事项', '', 1, 1, 0, '/workflow', 'ep:baseball', '', '', 0, true, true, true, '1', '2026-01-14 09:43:34', '1', '2026-01-30 12:47:56', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213538, '汇总缴费申请', '', 2, 0, 213468, 'jfhzjnsq', '', 'lghjft/workflow/jfhzjnsq/index', 'LghjftWorkflowJfhzjnsq', 0, true, true, true, '', '2026-01-27 11:44:46', '1',
        '2026-02-11 17:21:04', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213544, '分支机构明细管理', '', 2, 0, 213468, 'jfhzjnsqmx', '', 'lghjft/workflow/jfhzjnsq/fzjgxxDetail', 'LghjftWorkflowJfhzjnsqFzjgxxDetail', 1, true, true, true, '',
        '2026-01-27 11:45:08', '1',
        '2026-02-03 10:24:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213550, '退抵费申请', '', 2, 0, 213468, 'tdfsq', '', 'lghjft/workflow/tdfsq/index', 'LghjftWorkflowTdfsq', 0, true, true, true, '', '2026-01-27 17:39:58', '1',
        '2026-02-11 17:23:55', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213580, '经费缓缴申请', '', 2, 0, 213468, 'jfhjsq', '', 'lghjft/workflow/jfhjsq/index', 'LghjftWorkflowJfhjsq', 0, true, true, true, '', '2026-02-05 11:10:36', '1',
        '2026-02-11 17:22:01', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213539, '工会经费汇总申报查询', 'lghjft:workflow-jfhzjnsq:query', 3, 1, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '', '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213540, '工会经费汇总申报创建', 'lghjft:workflow-jfhzjnsq:create', 3, 2, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '',
        '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213541, '工会经费汇总申报更新', 'lghjft:workflow-jfhzjnsq:update', 3, 3, 213538, '', '', '', null, 0, true, true, true, '', '2026-01-27 11:44:46', '',
        '2026-01-27 11:44:46',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213545, '分支机构明细查询', 'lghjft:wf-sq-hzjfmx:query', 3, 1, 213544, '', '', '', '', 1, true, true, true, '', '2026-01-27 11:45:08', '1', '2026-02-05 11:11:39', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213546, '分支机构明细创建', 'lghjft:wf-sq-hzjfmx:create', 3, 2, 213544, '', '', '', '', 1, true, true, true, '', '2026-01-27 11:45:08', '1', '2026-02-05 11:11:40', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213551, '退抵费申请查询', 'lghjft:workflow-tdfsq:query', 3, 1, 213550, '', '', '', '', 0, true, true, true, '', '2026-01-27 17:39:58', '1', '2026-02-03 10:38:13', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213552, '退抵费申请创建', 'lghjft:workflow-tdfsq:create', 3, 2, 213550, '', '', '', '', 0, true, true, true, '', '2026-01-27 17:39:58', '1', '2026-01-28 17:51:15',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213553, '退抵费申请更新', 'lghjft:workflow-tdfsq:update', 3, 3, 213550, '', '', '', null, 0, true, true, true, '', '2026-01-27 17:39:58', '', '2026-01-27 17:39:58', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213581, '工会经费缓缴申请查询', 'lghjft:workflow-jfhjsq:query', 3, 1, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213582, '工会经费缓缴申请创建', 'lghjft:workflow-jfhjsq:create', 3, 2, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213583, '工会经费缓缴申请更新', 'lghjft:workflow-jfhjsq:update', 3, 3, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213584, '工会经费缓缴申请删除', 'lghjft:workflow-jfhjsq:delete', 3, 4, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213585, '工会经费缓缴申请导出', 'lghjft:workflow-jfhjsq:export', 3, 5, 213580, '', '', '', null, 0, true, true, true, '', '2026-02-05 11:10:36', '', '2026-02-05 11:10:36',
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
VALUES (213498, '问题反馈', '', 2, 1, 213487, 'wtfk', 'fa:question-circle', 'lghjft/nrgl/wtfk/index', 'LghjftNrglWtfk', 0, true, true, true, '', '2026-01-16 15:07:20', '1',
        '2026-01-16 15:12:23',
        false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213515, '问题处理', '', 2, 2, 213487, 'wtcl', 'ep:avatar', 'lghjft/nrgl/wtfk/process/index', 'LghjftNrglWtfkProcess', 0, true, true, true, '1', '2026-01-19 16:19:02', '1',
        '2026-01-19 17:31:04',
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
VALUES (213658, '政策文件', '', 2, 15, 213487, 'zcwj', 'ep:files', 'lghjft/nrgl/zcwj/index', 'LghjftNrglZcwj', 0, true, true, true, '1', '2026-03-06 12:00:00', '1',
        '2026-03-06 12:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213663, '在线咨询', '', 2, 16, 213487, 'zxzx', 'ep:chat-round', 'lghjft/nrgl/zxzx/index', 'LghjftNrglZxzxIndex', 0, true, true, true, '1', '2026-03-06 12:10:00', '1',
        '2026-03-06 12:10:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213499, '问题反馈查询', 'lghjft:nrgl-wtfk:query', 3, 1, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:10', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213500, '问题反馈创建', 'lghjft:nrgl-wtfk:create', 3, 2, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:22', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213501, '问题反馈更新', 'lghjft:nrgl-wtfk:update', 3, 3, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:36', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213502, '问题反馈删除', 'lghjft:nrgl-wtfk:delete', 3, 4, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:45', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213503, '问题反馈导出', 'lghjft:nrgl-wtfk:export', 3, 5, 213498, '', '', '', '', 0, true, true, true, '', '2026-01-16 15:07:20', '1', '2026-01-16 15:13:53', false);
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
VALUES (213659, '政策文件查询', 'lghjft:nrgl-zcwj:query', 3, 1, 213658, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:00:00', '1', '2026-03-06 12:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213660, '政策文件创建', 'lghjft:nrgl-zcwj:create', 3, 2, 213658, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:00:00', '1', '2026-03-06 12:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213661, '政策文件更新', 'lghjft:nrgl-zcwj:update', 3, 3, 213658, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:00:00', '1', '2026-03-06 12:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213662, '政策文件删除', 'lghjft:nrgl-zcwj:delete', 3, 4, 213658, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:00:00', '1', '2026-03-06 12:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213664, '在线咨询查询', 'lghjft:nrgl-zxzx:query', 3, 1, 213663, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:10:00', '1', '2026-03-06 12:10:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213665, '在线咨询处理', 'lghjft:nrgl-zxzx:update', 3, 2, 213663, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:10:00', '1', '2026-03-06 12:10:00', false);
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
VALUES (213666, '小微小额', '', 2, 2, 213591, 'xwxe', 'ep:data-analysis', '/lghjft/hjgl/xwxe/index', 'LghjftHjglXwxe', 0, true, true, true, '1', '2026-03-06 12:20:00', '1',
        '2026-03-06 12:20:00', false);
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
VALUES (213667, '小微小额查询', 'lghjft:hjgl-xwxe:query', 3, 1, 213666, '', '', '', '', 0, true, true, true, '1', '2026-03-06 12:20:00', '1', '2026-03-06 12:20:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213668, '安全中心', '', 2, 2, 200001, 'aqzx', 'ep:shield', 'lghjft/qx/aqzx/index', 'LghjftQxAqzx', 0, true, true, true, '1', '2026-03-06 18:00:00', '1',
        '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213669, '安全中心查询', 'lghjft:qx-aqzx:query', 3, 1, 213668, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213670, '安全中心更新', 'lghjft:qx-aqzx:update', 3, 2, 213668, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213671, '安全中心发送验证码', 'lghjft:qx-aqzx:send-sms', 3, 3, 213668, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1',
        '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213672, '账户维护', '', 2, 3, 200001, 'zhwh', 'ep:wallet', 'lghjft/qx/zhwh/index', 'LghjftQxZhwh', 0, true, true, true, '1', '2026-03-06 18:00:00', '1',
        '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213673, '账户维护查询', 'lghjft:qx-zhwh:query', 3, 1, 213672, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213674, '账户维护创建', 'lghjft:qx-zhwh:create', 3, 2, 213672, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213675, '账户维护更新', 'lghjft:qx-zhwh:update', 3, 3, 213672, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213676, '账户维护删除', 'lghjft:qx-zhwh:delete', 3, 4, 213672, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213677, '账户维护审核', 'lghjft:qx-zhwh:audit', 3, 5, 213672, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213678, '单位信息审批', '', 2, 4, 200001, 'dwxxsp', 'ep:document-checked', 'lghjft/qx/dwxxsp/index', 'LghjftQxDwxxsp', 0, true, true, true, '1',
        '2026-03-06 18:00:00', '1', '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213679, '单位信息审批查询', 'lghjft:qx-dwxxsp:query', 3, 1, 213678, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1',
        '2026-03-06 18:00:00', false);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater,
                        update_time, deleted)
VALUES (213680, '单位信息审批处理', 'lghjft:qx-dwxxsp:audit', 3, 2, 213678, '', '', '', '', 0, true, true, true, '1', '2026-03-06 18:00:00', '1',
        '2026-03-06 18:00:00', false);
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

update system_menu
set visible=1,
    status=0


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
INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (92001, 99, '陇工惠', 'LONGGONGHUI', 'system_sms_channel_code', 0, 'success', '', '陇工惠短信渠道', '1', '2026-03-12 08:30:00', '1', '2026-03-12 08:30:00', false);
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

INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (2, 'biz', 1, '用户管理-账号初始密码', 'system.user.init-password', '123456', false, '初始化密码 123456', 'admin', '2021-01-05 17:03:48', '1', '2024-07-20 17:22:47', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (7, 'url', 2, 'MySQL 监控的地址', 'url.druid', '', true, '', '1', '2023-04-07 13:41:16', '1', '2023-04-07 14:33:38', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (8, 'url', 2, 'SkyWalking 监控的地址', 'url.skywalking', '', true, '', '1', '2023-04-07 13:41:16', '1', '2023-04-07 14:57:03', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (9, 'url', 2, 'Spring Boot Admin 监控的地址', 'url.spring-boot-admin', '', true, '', '1', '2023-04-07 13:41:16', '1', '2023-04-07 14:52:07', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (10, 'url', 2, 'Swagger 接口文档的地址', 'url.swagger', '', true, '', '1', '2023-04-07 13:41:16', '1', '2023-04-07 14:59:00', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (11, 'ui', 2, '腾讯地图 key', 'tencent.lbs.key', 'TVDBZ-TDILD-4ON4B-PFDZA-RNLKH-VVF6E', true, '腾讯地图 key', '1', '2023-06-03 19:16:27', '1', '2023-06-03 19:16:27', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (12, 'test2', 2, 'test3', 'test4', 'test5', true, 'test6', '1', '2023-12-03 09:55:16', '1', '2025-04-06 21:00:09', false);
INSERT INTO infra_config (id, category, type, name, config_key, value, visible, remark, creator, create_time, updater, update_time, deleted)
VALUES (13, '用户管理-账号初始密码', 2, '用户管理-注册开关', 'system.user.register-enabled', 'true', false, '', '1', '2025-04-26 17:23:41', '1', '2025-12-16 17:21:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2142, 22, 'blob.png', '20251216/blob_1765819816638.png', 'http://test.yudao.iocoder.cn/20251216/blob_1765819816638.png', 'image/png', 4385, '1', '2025-12-16 01:30:18', '1',
        '2025-12-16 01:30:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2143, 22, 'blob.png', '20251216/blob_1765819846641.png', 'http://test.yudao.iocoder.cn/20251216/blob_1765819846641.png', 'image/png', 27021, '1', '2025-12-16 01:30:47',
        '1', '2025-12-16 01:30:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2144, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112359230.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112359230.png',
        'image/png', 10948, '1', '2025-12-31 00:32:42', '1', '2025-12-31 00:32:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2145, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112371419.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112371419.png',
        'image/png', 10948, '1', '2025-12-31 00:32:52', '1', '2025-12-31 00:32:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2146, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112401508.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112401508.png',
        'image/png', 10948, '1', '2025-12-31 00:33:22', '1', '2025-12-31 00:33:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2147, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112431634.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112431634.png',
        'image/png', 10948, '1', '2025-12-31 00:33:52', '1', '2025-12-31 00:33:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2148, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112462007.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112462007.png',
        'image/png', 10948, '1', '2025-12-31 00:34:22', '1', '2025-12-31 00:34:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2149, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112491852.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112491852.png',
        'image/png', 22500, '1', '2025-12-31 00:34:52', '1', '2025-12-31 00:34:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2150, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112515464.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112515464.png',
        'image/png', 22213, '1', '2025-12-31 00:35:16', '1', '2025-12-31 00:35:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2151, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112518480.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112518480.png',
        'image/png', 22213, '1', '2025-12-31 00:35:19', '1', '2025-12-31 00:35:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2152, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112522267.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112522267.png',
        'image/png', 22213, '1', '2025-12-31 00:35:22', '1', '2025-12-31 00:35:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2153, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112552237.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112552237.png',
        'image/png', 22213, '1', '2025-12-31 00:35:52', '1', '2025-12-31 00:35:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2154, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112582250.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112582250.png',
        'image/png', 22213, '1', '2025-12-31 00:36:22', '1', '2025-12-31 00:36:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2155, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112603242.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112603242.png',
        'image/png', 22004, '1', '2025-12-31 00:36:43', '1', '2025-12-31 00:36:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2156, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112633224.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112633224.png',
        'image/png', 22004, '1', '2025-12-31 00:37:13', '1', '2025-12-31 00:37:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2157, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112641579.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112641579.png',
        'image/png', 31637, '1', '2025-12-31 00:37:22', '1', '2025-12-31 00:37:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2158, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112647233.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112647233.png',
        'image/png', 22213, '1', '2025-12-31 00:37:28', '1', '2025-12-31 00:37:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2159, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112663228.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112663228.png',
        'image/png', 22004, '1', '2025-12-31 00:37:43', '1', '2025-12-31 00:37:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2160, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112713246.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112713246.png',
        'image/png', 22213, '1', '2025-12-31 00:38:33', '1', '2025-12-31 00:38:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2161, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112740085.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112740085.png',
        'image/png', 22004, '1', '2025-12-31 00:39:00', '1', '2025-12-31 00:39:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2162, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112799059.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112799059.png',
        'image/png', 22213, '1', '2025-12-31 00:39:59', '1', '2025-12-31 00:39:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2163, 22, 'go-view/9_index_preview.png', '20251231/9_index_preview_1767112807747.png', 'http://test.yudao.iocoder.cn/20251231/9_index_preview_1767112807747.png',
        'image/png', 22004, '1', '2025-12-31 00:40:08', '1', '2025-12-31 00:40:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2164, 22, 'image.png', 'editor-default-image/20260119/image_1768784521914.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260119/image_1768784521914.png',
        'image/png', 186769, '1', '2026-01-19 09:02:04', '1', '2026-01-19 09:02:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2165, 22, 'image.png', 'editor-default-image/20260119/image_1768785224852.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260119/image_1768785224852.png',
        'image/png', 190663, '1', '2026-01-19 09:13:46', '1', '2026-01-19 09:13:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2166, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768808388480.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768808388480.png',
        'image/png', 7116, '1', '2026-01-19 15:39:51', '1', '2026-01-19 15:39:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2167, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768808458279.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768808458279.png',
        'image/png', 67113, '1', '2026-01-19 15:40:59', '1', '2026-01-19 15:40:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2168, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768808487227.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768808487227.png',
        'image/png', 90432, '1', '2026-01-19 15:41:27', '1', '2026-01-19 15:41:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2169, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768808516482.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768808516482.png',
        'image/png', 90397, '1', '2026-01-19 15:41:57', '1', '2026-01-19 15:41:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2170, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809103449.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809103449.png',
        'image/png', 90618, '1', '2026-01-19 15:51:44', '1', '2026-01-19 15:51:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2171, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809133280.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809133280.png',
        'image/png', 95082, '1', '2026-01-19 15:52:14', '1', '2026-01-19 15:52:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2172, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809164181.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809164181.png',
        'image/png', 98138, '1', '2026-01-19 15:52:44', '1', '2026-01-19 15:52:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2173, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809193176.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809193176.png',
        'image/png', 98201, '1', '2026-01-19 15:53:13', '1', '2026-01-19 15:53:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2174, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809222776.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809222776.png',
        'image/png', 61148, '1', '2026-01-19 15:53:43', '1', '2026-01-19 15:53:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2175, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809253048.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809253048.png',
        'image/png', 98138, '1', '2026-01-19 15:54:13', '1', '2026-01-19 15:54:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2176, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809282921.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809282921.png',
        'image/png', 98062, '1', '2026-01-19 15:54:44', '1', '2026-01-19 15:54:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2177, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809313054.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809313054.png',
        'image/png', 98163, '1', '2026-01-19 15:55:13', '1', '2026-01-19 15:55:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2178, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809342862.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809342862.png',
        'image/png', 98051, '1', '2026-01-19 15:55:43', '1', '2026-01-19 15:55:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2179, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809387104.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809387104.png',
        'image/png', 12553, '1', '2026-01-19 15:56:27', '1', '2026-01-19 15:56:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2180, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809418011.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809418011.png',
        'image/png', 12489, '1', '2026-01-19 15:56:58', '1', '2026-01-19 15:56:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2181, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809528883.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809528883.png',
        'image/png', 12499, '1', '2026-01-19 15:58:49', '1', '2026-01-19 15:58:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2182, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809636324.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809636324.png',
        'image/png', 12499, '1', '2026-01-19 16:00:37', '1', '2026-01-19 16:00:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2183, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809651384.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809651384.png',
        'image/png', 12499, '1', '2026-01-19 16:00:52', '1', '2026-01-19 16:00:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2184, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809656133.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809656133.png',
        'image/png', 12541, '1', '2026-01-19 16:00:56', '1', '2026-01-19 16:00:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2185, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809716515.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809716515.png',
        'image/png', 70168, '1', '2026-01-19 16:01:57', '1', '2026-01-19 16:01:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2186, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809747080.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809747080.png',
        'image/png', 303606, '1', '2026-01-19 16:02:28', '1', '2026-01-19 16:02:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2187, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809777240.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809777240.png',
        'image/png', 302618, '1', '2026-01-19 16:02:58', '1', '2026-01-19 16:02:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2188, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768809948769.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768809948769.png',
        'image/png', 303194, '1', '2026-01-19 16:05:49', '1', '2026-01-19 16:05:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2189, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810027114.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810027114.png',
        'image/png', 303194, '1', '2026-01-19 16:07:08', '1', '2026-01-19 16:07:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2190, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810027541.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810027541.png',
        'image/png', 301416, '1', '2026-01-19 16:07:08', '1', '2026-01-19 16:07:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2191, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810081574.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810081574.png',
        'image/png', 33389, '1', '2026-01-19 16:08:02', '1', '2026-01-19 16:08:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2192, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810169413.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810169413.png',
        'image/png', 33389, '1', '2026-01-19 16:09:30', '1', '2026-01-19 16:09:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2193, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810208064.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810208064.png',
        'image/png', 33389, '1', '2026-01-19 16:10:09', '1', '2026-01-19 16:10:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2194, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810227734.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810227734.png',
        'image/png', 13564, '1', '2026-01-19 16:10:28', '1', '2026-01-19 16:10:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2195, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810282729.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810282729.png',
        'image/png', 69140, '1', '2026-01-19 16:11:23', '1', '2026-01-19 16:11:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2196, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810686948.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810686948.png',
        'image/png', 72281, '1', '2026-01-19 16:18:09', '1', '2026-01-19 16:18:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2197, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810716578.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810716578.png',
        'image/png', 62399, '1', '2026-01-19 16:18:37', '1', '2026-01-19 16:18:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2198, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810749585.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810749585.png',
        'image/png', 62413, '1', '2026-01-19 16:19:12', '1', '2026-01-19 16:19:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2199, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810776093.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810776093.png',
        'image/png', 62373, '1', '2026-01-19 16:19:36', '1', '2026-01-19 16:19:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2200, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810806356.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810806356.png',
        'image/png', 61840, '1', '2026-01-19 16:20:07', '1', '2026-01-19 16:20:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2201, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810836354.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810836354.png',
        'image/png', 164098, '1', '2026-01-19 16:20:37', '1', '2026-01-19 16:20:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2202, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810866225.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810866225.png',
        'image/png', 164098, '1', '2026-01-19 16:21:07', '1', '2026-01-19 16:21:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2203, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810903538.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810903538.png',
        'image/png', 164098, '1', '2026-01-19 16:21:44', '1', '2026-01-19 16:21:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2204, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768810990787.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768810990787.png',
        'image/png', 164098, '1', '2026-01-19 16:23:11', '1', '2026-01-19 16:23:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2205, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811002486.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811002486.png',
        'image/png', 164098, '1', '2026-01-19 16:23:23', '1', '2026-01-19 16:23:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2206, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811021231.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811021231.png',
        'image/png', 164253, '1', '2026-01-19 16:23:41', '1', '2026-01-19 16:23:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2207, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811117137.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811117137.png',
        'image/png', 165353, '1', '2026-01-19 16:25:18', '1', '2026-01-19 16:25:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2208, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811136158.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811136158.png',
        'image/png', 169552, '1', '2026-01-19 16:25:36', '1', '2026-01-19 16:25:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2209, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811177230.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811177230.png',
        'image/png', 263282, '1', '2026-01-19 16:26:17', '1', '2026-01-19 16:26:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2210, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811206654.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811206654.png',
        'image/png', 263175, '1', '2026-01-19 16:26:47', '1', '2026-01-19 16:26:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2211, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811236028.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811236028.png',
        'image/png', 263117, '1', '2026-01-19 16:27:16', '1', '2026-01-19 16:27:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2212, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811288856.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811288856.png',
        'image/png', 263220, '1', '2026-01-19 16:28:09', '1', '2026-01-19 16:28:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2213, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811296528.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811296528.png',
        'image/png', 171047, '1', '2026-01-19 16:28:17', '1', '2026-01-19 16:28:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2214, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811327129.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811327129.png',
        'image/png', 171071, '1', '2026-01-19 16:28:47', '1', '2026-01-19 16:28:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2215, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811355536.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811355536.png',
        'image/png', 24321, '1', '2026-01-19 16:29:16', '1', '2026-01-19 16:29:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2216, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811388358.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811388358.png',
        'image/png', 186046, '1', '2026-01-19 16:29:49', '1', '2026-01-19 16:29:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2217, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811415965.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811415965.png',
        'image/png', 64692, '1', '2026-01-19 16:30:16', '1', '2026-01-19 16:30:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2218, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811585488.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811585488.png',
        'image/png', 66982, '1', '2026-01-19 16:33:06', '1', '2026-01-19 16:33:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2219, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811647071.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811647071.png',
        'image/png', 66982, '1', '2026-01-19 16:34:07', '1', '2026-01-19 16:34:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2220, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811703286.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811703286.png',
        'image/png', 66982, '1', '2026-01-19 16:35:03', '1', '2026-01-19 16:35:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2221, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811793282.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811793282.png',
        'image/png', 66982, '1', '2026-01-19 16:36:34', '1', '2026-01-19 16:36:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2222, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811857307.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811857307.png',
        'image/png', 66982, '1', '2026-01-19 16:37:38', '1', '2026-01-19 16:37:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2223, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811927682.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811927682.png',
        'image/png', 66982, '1', '2026-01-19 16:38:48', '1', '2026-01-19 16:38:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2224, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811981861.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811981861.png',
        'image/png', 66982, '1', '2026-01-19 16:39:42', '1', '2026-01-19 16:39:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2225, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768811986081.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768811986081.png',
        'image/png', 66993, '1', '2026-01-19 16:39:46', '1', '2026-01-19 16:39:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2226, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812025767.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812025767.png',
        'image/png', 29136, '1', '2026-01-19 16:40:26', '1', '2026-01-19 16:40:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2227, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812055986.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812055986.png',
        'image/png', 31058, '1', '2026-01-19 16:40:56', '1', '2026-01-19 16:40:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2228, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812086156.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812086156.png',
        'image/png', 31058, '1', '2026-01-19 16:41:26', '1', '2026-01-19 16:41:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2229, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812087099.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812087099.png',
        'image/png', 66993, '1', '2026-01-19 16:41:27', '1', '2026-01-19 16:41:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2230, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812115985.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812115985.png',
        'image/png', 31058, '1', '2026-01-19 16:41:56', '1', '2026-01-19 16:41:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2231, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812156050.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812156050.png',
        'image/png', 66993, '1', '2026-01-19 16:42:36', '1', '2026-01-19 16:42:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2232, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812163994.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812163994.png',
        'image/png', 31058, '1', '2026-01-19 16:42:44', '1', '2026-01-19 16:42:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2233, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812214226.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812214226.png',
        'image/png', 66993, '1', '2026-01-19 16:43:34', '1', '2026-01-19 16:43:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2234, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812240991.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812240991.png',
        'image/png', 31058, '1', '2026-01-19 16:44:01', '1', '2026-01-19 16:44:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2235, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812275098.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812275098.png',
        'image/png', 66993, '1', '2026-01-19 16:44:35', '1', '2026-01-19 16:44:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2236, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812300996.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812300996.png',
        'image/png', 31058, '1', '2026-01-19 16:45:01', '1', '2026-01-19 16:45:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2237, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812350552.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812350552.png',
        'image/png', 66993, '1', '2026-01-19 16:45:51', '1', '2026-01-19 16:45:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2238, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812381016.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812381016.png',
        'image/png', 31058, '1', '2026-01-19 16:46:21', '1', '2026-01-19 16:46:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2239, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812386997.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812386997.png',
        'image/png', 31058, '1', '2026-01-19 16:46:27', '1', '2026-01-19 16:46:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2240, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812411188.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812411188.png',
        'image/png', 66993, '1', '2026-01-19 16:46:51', '1', '2026-01-19 16:46:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2241, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812491790.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812491790.png',
        'image/png', 66993, '1', '2026-01-19 16:48:12', '1', '2026-01-19 16:48:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2242, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812519108.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812519108.png',
        'image/png', 31058, '1', '2026-01-19 16:48:39', '1', '2026-01-19 16:48:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2243, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812565076.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812565076.png',
        'image/png', 66993, '1', '2026-01-19 16:49:25', '1', '2026-01-19 16:49:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2244, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812587640.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812587640.png',
        'image/png', 31058, '1', '2026-01-19 16:49:48', '1', '2026-01-19 16:49:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2245, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812630090.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812630090.png',
        'image/png', 66993, '1', '2026-01-19 16:50:30', '1', '2026-01-19 16:50:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2246, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812645358.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812645358.png',
        'image/png', 66978, '1', '2026-01-19 16:50:46', '1', '2026-01-19 16:50:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2247, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812651223.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812651223.png',
        'image/png', 31058, '1', '2026-01-19 16:50:51', '1', '2026-01-19 16:50:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2248, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812675530.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812675530.png',
        'image/png', 66977, '1', '2026-01-19 16:51:16', '1', '2026-01-19 16:51:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2249, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812706016.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812706016.png',
        'image/png', 83128, '1', '2026-01-19 16:51:46', '1', '2026-01-19 16:51:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2250, 22, 'go-view/8_index_preview.png', '20260119/8_index_preview_1768812706666.png', 'http://test.yudao.iocoder.cn/20260119/8_index_preview_1768812706666.png',
        'image/png', 31058, '1', '2026-01-19 16:51:47', '1', '2026-01-19 16:51:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2251, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813021398.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813021398.png',
        'image/png', 90365, '1', '2026-01-19 16:57:02', '1', '2026-01-19 16:57:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2252, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813051389.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813051389.png',
        'image/png', 90365, '1', '2026-01-19 16:57:32', '1', '2026-01-19 16:57:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2253, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813080184.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813080184.png',
        'image/png', 95830, '1', '2026-01-19 16:58:01', '1', '2026-01-19 16:58:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2254, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813110855.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813110855.png',
        'image/png', 95812, '1', '2026-01-19 16:58:32', '1', '2026-01-19 16:58:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2255, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813141139.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813141139.png',
        'image/png', 95839, '1', '2026-01-19 16:59:01', '1', '2026-01-19 16:59:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2256, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813170784.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813170784.png',
        'image/png', 95795, '1', '2026-01-19 16:59:31', '1', '2026-01-19 16:59:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2257, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813276305.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813276305.png',
        'image/png', 95813, '1', '2026-01-19 17:01:17', '1', '2026-01-19 17:01:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2258, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813320943.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813320943.png',
        'image/png', 95743, '1', '2026-01-19 17:02:01', '1', '2026-01-19 17:02:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2259, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813348274.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813348274.png',
        'image/png', 95743, '1', '2026-01-19 17:02:28', '1', '2026-01-19 17:02:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2260, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813365881.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813365881.png',
        'image/png', 95854, '1', '2026-01-19 17:02:46', '1', '2026-01-19 17:02:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2261, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813580093.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813580093.png',
        'image/png', 95826, '1', '2026-01-19 17:06:21', '1', '2026-01-19 17:06:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2262, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813592707.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813592707.png',
        'image/png', 95826, '1', '2026-01-19 17:06:33', '1', '2026-01-19 17:06:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2263, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813605925.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813605925.png',
        'image/png', 95848, '1', '2026-01-19 17:06:46', '1', '2026-01-19 17:06:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2264, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813637392.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813637392.png',
        'image/png', 95811, '1', '2026-01-19 17:07:18', '1', '2026-01-19 17:07:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2265, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813765666.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813765666.png',
        'image/png', 95811, '1', '2026-01-19 17:09:26', '1', '2026-01-19 17:09:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2266, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813807256.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813807256.png',
        'image/png', 95811, '1', '2026-01-19 17:10:07', '1', '2026-01-19 17:10:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2267, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813816085.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813816085.png',
        'image/png', 95821, '1', '2026-01-19 17:10:16', '1', '2026-01-19 17:10:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2268, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813846690.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813846690.png',
        'image/png', 209083, '1', '2026-01-19 17:10:47', '1', '2026-01-19 17:10:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2269, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813876439.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813876439.png',
        'image/png', 265676, '1', '2026-01-19 17:11:17', '1', '2026-01-19 17:11:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2270, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813905469.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813905469.png',
        'image/png', 265682, '1', '2026-01-19 17:11:46', '1', '2026-01-19 17:11:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2271, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768813936194.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768813936194.png',
        'image/png', 265730, '1', '2026-01-19 17:12:16', '1', '2026-01-19 17:12:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2272, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814129777.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814129777.png',
        'image/png', 265730, '1', '2026-01-19 17:15:30', '1', '2026-01-19 17:15:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2273, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814130075.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814130075.png',
        'image/png', 265730, '1', '2026-01-19 17:15:31', '1', '2026-01-19 17:15:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2274, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814145388.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814145388.png',
        'image/png', 98144, '1', '2026-01-19 17:15:46', '1', '2026-01-19 17:15:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2275, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814262598.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814262598.png',
        'image/png', 98136, '1', '2026-01-19 17:17:43', '1', '2026-01-19 17:17:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2276, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814352601.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814352601.png',
        'image/png', 98136, '1', '2026-01-19 17:19:13', '1', '2026-01-19 17:19:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2277, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814426389.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814426389.png',
        'image/png', 98136, '1', '2026-01-19 17:20:27', '1', '2026-01-19 17:20:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2278, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814501341.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814501341.png',
        'image/png', 98136, '1', '2026-01-19 17:21:42', '1', '2026-01-19 17:21:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2279, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814510190.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814510190.png',
        'image/png', 98136, '1', '2026-01-19 17:21:50', '1', '2026-01-19 17:21:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2280, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814535840.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814535840.png',
        'image/png', 98115, '1', '2026-01-19 17:22:17', '1', '2026-01-19 17:22:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2281, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768814581087.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768814581087.png',
        'image/png', 96548, '1', '2026-01-19 17:23:01', '1', '2026-01-19 17:23:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2282, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768814623845.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768814623845.png',
        'image/png', 28145, '1', '2026-01-19 17:23:44', '1', '2026-01-19 17:23:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2283, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768814683984.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768814683984.png',
        'image/png', 7116, '1', '2026-01-19 17:24:44', '1', '2026-01-19 17:24:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2284, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768814721311.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768814721311.png',
        'image/png', 7116, '1', '2026-01-19 17:25:22', '1', '2026-01-19 17:25:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2285, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815279120.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815279120.png',
        'image/png', 96551, '1', '2026-01-19 17:34:40', '1', '2026-01-19 17:34:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2286, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815308640.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815308640.png',
        'image/png', 96525, '1', '2026-01-19 17:35:09', '1', '2026-01-19 17:35:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2287, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815353572.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815353572.png',
        'image/png', 10290, '1', '2026-01-19 17:35:54', '1', '2026-01-19 17:35:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2288, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815380176.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815380176.png',
        'image/png', 7116, '1', '2026-01-19 17:36:20', '1', '2026-01-19 17:36:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2289, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815447227.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815447227.png',
        'image/png', 7116, '1', '2026-01-19 17:37:28', '1', '2026-01-19 17:37:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2290, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815447426.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815447426.png',
        'image/png', 7116, '1', '2026-01-19 17:37:28', '1', '2026-01-19 17:37:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2291, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815470185.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815470185.png',
        'image/png', 7116, '1', '2026-01-19 17:37:50', '1', '2026-01-19 17:37:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2292, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815502202.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815502202.png',
        'image/png', 67624, '1', '2026-01-19 17:38:22', '1', '2026-01-19 17:38:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2293, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815533883.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815533883.png',
        'image/png', 67653, '1', '2026-01-19 17:38:54', '1', '2026-01-19 17:38:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2294, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815562262.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815562262.png',
        'image/png', 67611, '1', '2026-01-19 17:39:22', '1', '2026-01-19 17:39:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2295, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815590933.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815590933.png',
        'image/png', 67601, '1', '2026-01-19 17:39:51', '1', '2026-01-19 17:39:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2296, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815620497.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815620497.png',
        'image/png', 67634, '1', '2026-01-19 17:40:21', '1', '2026-01-19 17:40:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2297, 22, 'go-view/10_index_preview.png', '20260119/10_index_preview_1768815651165.png', 'http://test.yudao.iocoder.cn/20260119/10_index_preview_1768815651165.png',
        'image/png', 67666, '1', '2026-01-19 17:40:51', '1', '2026-01-19 17:40:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2298, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768815919129.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768815919129.png',
        'image/png', 66252, '1', '2026-01-19 17:45:20', '1', '2026-01-19 17:45:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2299, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768815949008.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768815949008.png',
        'image/png', 66194, '1', '2026-01-19 17:45:49', '1', '2026-01-19 17:45:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2300, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768815978498.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768815978498.png',
        'image/png', 66250, '1', '2026-01-19 17:46:19', '1', '2026-01-19 17:46:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2301, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816023144.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816023144.png',
        'image/png', 10411, '1', '2026-01-19 17:47:03', '1', '2026-01-19 17:47:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2302, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816053121.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816053121.png',
        'image/png', 10411, '1', '2026-01-19 17:47:33', '1', '2026-01-19 17:47:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2303, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816082788.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816082788.png',
        'image/png', 10411, '1', '2026-01-19 17:48:03', '1', '2026-01-19 17:48:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2304, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816112689.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816112689.png',
        'image/png', 66703, '1', '2026-01-19 17:48:33', '1', '2026-01-19 17:48:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2305, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816144166.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816144166.png',
        'image/png', 66669, '1', '2026-01-19 17:49:05', '1', '2026-01-19 17:49:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2306, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816208691.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816208691.png',
        'image/png', 66669, '1', '2026-01-19 17:50:09', '1', '2026-01-19 17:50:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2307, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816208744.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816208744.png',
        'image/png', 66530, '1', '2026-01-19 17:50:09', '1', '2026-01-19 17:50:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2308, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816233069.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816233069.png',
        'image/png', 52799, '1', '2026-01-19 17:50:33', '1', '2026-01-19 17:50:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2309, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816263098.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816263098.png',
        'image/png', 203189, '1', '2026-01-19 17:51:04', '1', '2026-01-19 17:51:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2310, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816429935.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816429935.png',
        'image/png', 197337, '1', '2026-01-19 17:54:03', '1', '2026-01-19 17:54:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2311, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816481187.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816481187.png',
        'image/png', 197337, '1', '2026-01-19 17:54:47', '1', '2026-01-19 17:54:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2312, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816481495.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816481495.png',
        'image/png', 197337, '1', '2026-01-19 17:54:47', '1', '2026-01-19 17:54:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2313, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816525587.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816525587.png',
        'image/png', 50127, '1', '2026-01-19 17:55:26', '1', '2026-01-19 17:55:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2314, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816553371.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816553371.png',
        'image/png', 48757, '1', '2026-01-19 17:55:54', '1', '2026-01-19 17:55:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2315, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816584212.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816584212.png',
        'image/png', 50125, '1', '2026-01-19 17:56:24', '1', '2026-01-19 17:56:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2316, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816591138.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816591138.png',
        'image/png', 196988, '1', '2026-01-19 17:56:32', '1', '2026-01-19 17:56:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2317, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816646198.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816646198.png',
        'image/png', 50125, '1', '2026-01-19 17:57:27', '1', '2026-01-19 17:57:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2318, 22, 'go-view/11_index_preview.png', '20260119/11_index_preview_1768816656422.png', 'http://test.yudao.iocoder.cn/20260119/11_index_preview_1768816656422.png',
        'image/png', 196988, '1', '2026-01-19 17:57:37', '1', '2026-01-19 17:57:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2319, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870318042.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870318042.png',
        'image/png', 50062, '1', '2026-01-20 08:52:00', '1', '2026-01-20 08:52:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2320, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870384788.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870384788.png',
        'image/png', 50062, '1', '2026-01-20 08:53:05', '1', '2026-01-20 08:53:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2321, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870474618.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870474618.png',
        'image/png', 50062, '1', '2026-01-20 08:54:35', '1', '2026-01-20 08:54:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2322, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870498150.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870498150.png',
        'image/png', 50062, '1', '2026-01-20 08:54:58', '1', '2026-01-20 08:54:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2323, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870498353.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870498353.png',
        'image/png', 50135, '1', '2026-01-20 08:54:58', '1', '2026-01-20 08:54:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2324, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870650050.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870650050.png',
        'image/png', 50084, '1', '2026-01-20 08:57:30', '1', '2026-01-20 08:57:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2325, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870725977.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870725977.png',
        'image/png', 50084, '1', '2026-01-20 08:58:46', '1', '2026-01-20 08:58:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2326, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870809034.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870809034.png',
        'image/png', 50084, '1', '2026-01-20 09:00:09', '1', '2026-01-20 09:00:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2327, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870876000.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870876000.png',
        'image/png', 50084, '1', '2026-01-20 09:01:16', '1', '2026-01-20 09:01:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2328, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768870970998.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768870970998.png',
        'image/png', 50084, '1', '2026-01-20 09:02:51', '1', '2026-01-20 09:02:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2329, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871066874.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871066874.png',
        'image/png', 50084, '1', '2026-01-20 09:04:27', '1', '2026-01-20 09:04:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2330, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871147883.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871147883.png',
        'image/png', 50084, '1', '2026-01-20 09:05:48', '1', '2026-01-20 09:05:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2331, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871228893.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871228893.png',
        'image/png', 50084, '1', '2026-01-20 09:07:09', '1', '2026-01-20 09:07:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2332, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871309145.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871309145.png',
        'image/png', 50084, '1', '2026-01-20 09:08:30', '1', '2026-01-20 09:08:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2333, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871402031.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871402031.png',
        'image/png', 613879, '1', '2026-01-20 09:10:03', '1', '2026-01-20 09:10:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2334, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871412970.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871412970.png',
        'image/png', 50070, '1', '2026-01-20 09:10:13', '1', '2026-01-20 09:10:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2335, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871432249.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871432249.png',
        'image/png', 611621, '1', '2026-01-20 09:10:35', '1', '2026-01-20 09:10:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2336, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871461437.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871461437.png',
        'image/png', 610289, '1', '2026-01-20 09:11:02', '1', '2026-01-20 09:11:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2337, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871490950.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871490950.png',
        'image/png', 50070, '1', '2026-01-20 09:11:31', '1', '2026-01-20 09:11:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2338, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871493146.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871493146.png',
        'image/png', 609842, '1', '2026-01-20 09:11:33', '1', '2026-01-20 09:11:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2339, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871522623.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871522623.png',
        'image/png', 625130, '1', '2026-01-20 09:12:03', '1', '2026-01-20 09:12:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2340, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871551946.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871551946.png',
        'image/png', 625078, '1', '2026-01-20 09:12:32', '1', '2026-01-20 09:12:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2341, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871573927.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871573927.png',
        'image/png', 50070, '1', '2026-01-20 09:12:54', '1', '2026-01-20 09:12:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2342, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871581952.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871581952.png',
        'image/png', 1033386, '1', '2026-01-20 09:13:03', '1', '2026-01-20 09:13:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2343, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871611769.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871611769.png',
        'image/png', 1027166, '1', '2026-01-20 09:13:32', '1', '2026-01-20 09:13:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2344, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871642684.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871642684.png',
        'image/png', 1032403, '1', '2026-01-20 09:14:04', '1', '2026-01-20 09:14:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2345, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871643953.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871643953.png',
        'image/png', 50070, '1', '2026-01-20 09:14:04', '1', '2026-01-20 09:14:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2346, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871647215.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871647215.png',
        'image/png', 50070, '1', '2026-01-20 09:14:07', '1', '2026-01-20 09:14:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2347, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871703644.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871703644.png',
        'image/png', 1030972, '1', '2026-01-20 09:15:04', '1', '2026-01-20 09:15:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2348, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871706328.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871706328.png',
        'image/png', 1033989, '1', '2026-01-20 09:15:08', '1', '2026-01-20 09:15:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2349, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871732804.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871732804.png',
        'image/png', 1036795, '1', '2026-01-20 09:15:34', '1', '2026-01-20 09:15:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2350, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871762518.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871762518.png',
        'image/png', 1031713, '1', '2026-01-20 09:16:04', '1', '2026-01-20 09:16:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2351, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871764970.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871764970.png',
        'image/png', 50045, '1', '2026-01-20 09:16:05', '1', '2026-01-20 09:16:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2352, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871794274.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871794274.png',
        'image/png', 1025201, '1', '2026-01-20 09:16:36', '1', '2026-01-20 09:16:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2353, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871800038.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871800038.png',
        'image/png', 50045, '1', '2026-01-20 09:16:40', '1', '2026-01-20 09:16:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2354, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768871800993.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768871800993.png',
        'image/png', 49923, '1', '2026-01-20 09:16:41', '1', '2026-01-20 09:16:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2355, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871822142.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871822142.png',
        'image/png', 1024836, '1', '2026-01-20 09:17:03', '1', '2026-01-20 09:17:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2356, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871876119.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871876119.png',
        'image/png', 1034829, '1', '2026-01-20 09:17:57', '1', '2026-01-20 09:17:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2357, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871883487.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871883487.png',
        'image/png', 1036610, '1', '2026-01-20 09:18:04', '1', '2026-01-20 09:18:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2358, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871913494.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871913494.png',
        'image/png', 1039254, '1', '2026-01-20 09:18:34', '1', '2026-01-20 09:18:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2359, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871946291.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871946291.png',
        'image/png', 1041783, '1', '2026-01-20 09:19:07', '1', '2026-01-20 09:19:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2360, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768871972325.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768871972325.png',
        'image/png', 1036490, '1', '2026-01-20 09:19:33', '1', '2026-01-20 09:19:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2361, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872012010.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872012010.png',
        'image/png', 66932, '1', '2026-01-20 09:20:12', '1', '2026-01-20 09:20:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2362, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872025277.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872025277.png',
        'image/png', 50143, '1', '2026-01-20 09:20:25', '1', '2026-01-20 09:20:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2363, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872043071.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872043071.png',
        'image/png', 66909, '1', '2026-01-20 09:20:43', '1', '2026-01-20 09:20:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2364, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872075003.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872075003.png',
        'image/png', 575625, '1', '2026-01-20 09:21:15', '1', '2026-01-20 09:21:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2365, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872105865.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872105865.png',
        'image/png', 608582, '1', '2026-01-20 09:21:46', '1', '2026-01-20 09:21:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2366, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872135050.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872135050.png',
        'image/png', 618646, '1', '2026-01-20 09:22:16', '1', '2026-01-20 09:22:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2367, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872135564.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872135564.png',
        'image/png', 50143, '1', '2026-01-20 09:22:16', '1', '2026-01-20 09:22:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2368, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872164827.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872164827.png',
        'image/png', 612381, '1', '2026-01-20 09:22:50', '1', '2026-01-20 09:22:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2369, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872232892.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872232892.png',
        'image/png', 50143, '1', '2026-01-20 09:23:53', '1', '2026-01-20 09:23:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2370, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872251909.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872251909.png',
        'image/png', 50084, '1', '2026-01-20 09:24:12', '1', '2026-01-20 09:24:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2371, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872259535.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872259535.png',
        'image/png', 1036293, '1', '2026-01-20 09:24:21', '1', '2026-01-20 09:24:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2372, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872266573.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872266573.png',
        'image/png', 638065, '1', '2026-01-20 09:24:27', '1', '2026-01-20 09:24:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2373, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872271588.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872271588.png',
        'image/png', 641090, '1', '2026-01-20 09:24:32', '1', '2026-01-20 09:24:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2374, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872284607.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872284607.png',
        'image/png', 1459419, '1', '2026-01-20 09:24:47', '1', '2026-01-20 09:24:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2375, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872315335.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872315335.png',
        'image/png', 50093, '1', '2026-01-20 09:25:15', '1', '2026-01-20 09:25:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2376, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872318103.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872318103.png',
        'image/png', 2496689, '1', '2026-01-20 09:25:21', '1', '2026-01-20 09:25:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2377, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872345267.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872345267.png',
        'image/png', 2503061, '1', '2026-01-20 09:25:46', '1', '2026-01-20 09:25:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2378, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872434952.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872434952.png',
        'image/png', 50093, '1', '2026-01-20 09:27:16', '1', '2026-01-20 09:27:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2379, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872482001.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872482001.png',
        'image/png', 648742, '1', '2026-01-20 09:28:03', '1', '2026-01-20 09:28:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2380, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872483876.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872483876.png',
        'image/png', 50093, '1', '2026-01-20 09:28:04', '1', '2026-01-20 09:28:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2381, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872488884.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872488884.png',
        'image/png', 651333, '1', '2026-01-20 09:28:10', '1', '2026-01-20 09:28:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2382, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872489672.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872489672.png',
        'image/png', 2486305, '1', '2026-01-20 09:28:15', '1', '2026-01-20 09:28:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2383, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872495359.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872495359.png',
        'image/png', 2490725, '1', '2026-01-20 09:28:24', '1', '2026-01-20 09:28:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2384, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872524873.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872524873.png',
        'image/png', 2476500, '1', '2026-01-20 09:28:48', '1', '2026-01-20 09:28:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2385, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872554787.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872554787.png',
        'image/png', 2484145, '1', '2026-01-20 09:29:18', '1', '2026-01-20 09:29:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2386, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872584754.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872584754.png',
        'image/png', 2549372, '1', '2026-01-20 09:29:47', '1', '2026-01-20 09:29:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2387, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872614215.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872614215.png',
        'image/png', 2492603, '1', '2026-01-20 09:30:17', '1', '2026-01-20 09:30:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2388, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872644305.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872644305.png',
        'image/png', 2468636, '1', '2026-01-20 09:30:47', '1', '2026-01-20 09:30:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2389, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872648960.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872648960.png',
        'image/png', 50093, '1', '2026-01-20 09:30:49', '1', '2026-01-20 09:30:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2390, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872674473.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872674473.png',
        'image/png', 2510478, '1', '2026-01-20 09:31:17', '1', '2026-01-20 09:31:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2391, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872706729.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872706729.png',
        'image/png', 2506217, '1', '2026-01-20 09:31:51', '1', '2026-01-20 09:31:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2392, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872720069.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872720069.png',
        'image/png', 50093, '1', '2026-01-20 09:32:00', '1', '2026-01-20 09:32:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2393, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872751821.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872751821.png',
        'image/png', 7116, '1', '2026-01-20 09:32:32', '1', '2026-01-20 09:32:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2394, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872781831.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872781831.png',
        'image/png', 7116, '1', '2026-01-20 09:33:02', '1', '2026-01-20 09:33:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2395, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872810887.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872810887.png',
        'image/png', 16837, '1', '2026-01-20 09:33:31', '1', '2026-01-20 09:33:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2396, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872820875.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872820875.png',
        'image/png', 50093, '1', '2026-01-20 09:33:41', '1', '2026-01-20 09:33:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2397, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872841161.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872841161.png',
        'image/png', 15164, '1', '2026-01-20 09:34:01', '1', '2026-01-20 09:34:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2398, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872871170.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872871170.png',
        'image/png', 154366, '1', '2026-01-20 09:34:32', '1', '2026-01-20 09:34:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2399, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872895001.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872895001.png',
        'image/png', 49923, '1', '2026-01-20 09:34:55', '1', '2026-01-20 09:34:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2400, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872901364.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872901364.png',
        'image/png', 161885, '1', '2026-01-20 09:35:02', '1', '2026-01-20 09:35:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2401, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872937253.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872937253.png',
        'image/png', 623312, '1', '2026-01-20 09:35:38', '1', '2026-01-20 09:35:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2402, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872938230.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872938230.png',
        'image/png', 49923, '1', '2026-01-20 09:35:38', '1', '2026-01-20 09:35:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2403, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872939852.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872939852.png',
        'image/png', 49923, '1', '2026-01-20 09:35:40', '1', '2026-01-20 09:35:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2404, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872941108.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872941108.png',
        'image/png', 522835, '1', '2026-01-20 09:35:43', '1', '2026-01-20 09:35:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2405, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872965929.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872965929.png',
        'image/png', 621679, '1', '2026-01-20 09:36:07', '1', '2026-01-20 09:36:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2406, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872965667.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872965667.png',
        'image/png', 624543, '1', '2026-01-20 09:36:07', '1', '2026-01-20 09:36:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2407, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872969673.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872969673.png',
        'image/png', 522835, '1', '2026-01-20 09:36:10', '1', '2026-01-20 09:36:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2408, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768872970934.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768872970934.png',
        'image/png', 50157, '1', '2026-01-20 09:36:11', '1', '2026-01-20 09:36:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2409, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768872968399.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768872968399.png',
        'image/png', 2505322, '1', '2026-01-20 09:36:12', '1', '2026-01-20 09:36:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2410, 22, 'go-view/21_index_preview.png', '20260120/21_index_preview_1768872973518.png', 'http://test.yudao.iocoder.cn/20260120/21_index_preview_1768872973518.png',
        'image/png', 650046, '1', '2026-01-20 09:36:14', '1', '2026-01-20 09:36:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2411, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768872991751.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768872991751.png',
        'image/png', 497385, '1', '2026-01-20 09:36:32', '1', '2026-01-20 09:36:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2412, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873037518.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873037518.png',
        'image/png', 473581, '1', '2026-01-20 09:37:19', '1', '2026-01-20 09:37:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2413, 22, 'go-view/11_index_preview.png', '20260120/11_index_preview_1768873038976.png', 'http://test.yudao.iocoder.cn/20260120/11_index_preview_1768873038976.png',
        'image/png', 50069, '1', '2026-01-20 09:37:23', '1', '2026-01-20 09:37:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2414, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873046544.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873046544.png',
        'image/png', 2491962, '1', '2026-01-20 09:37:29', '1', '2026-01-20 09:37:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2415, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873046135.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873046135.png',
        'image/png', 2505172, '1', '2026-01-20 09:37:31', '1', '2026-01-20 09:37:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2416, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873052431.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873052431.png',
        'image/png', 2504221, '1', '2026-01-20 09:37:37', '1', '2026-01-20 09:37:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2417, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873058881.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873058881.png',
        'image/png', 472509, '1', '2026-01-20 09:37:51', '1', '2026-01-20 09:37:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2418, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873109989.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873109989.png',
        'image/png', 49743, '1', '2026-01-20 09:38:30', '1', '2026-01-20 09:38:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2419, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873141301.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873141301.png',
        'image/png', 48777, '1', '2026-01-20 09:39:02', '1', '2026-01-20 09:39:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2420, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873204771.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873204771.png',
        'image/png', 67012, '1', '2026-01-20 09:40:05', '1', '2026-01-20 09:40:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2421, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873215511.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873215511.png',
        'image/png', 160145, '1', '2026-01-20 09:40:16', '1', '2026-01-20 09:40:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2422, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873230933.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873230933.png',
        'image/png', 67271, '1', '2026-01-20 09:40:31', '1', '2026-01-20 09:40:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2423, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873245287.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873245287.png',
        'image/png', 156002, '1', '2026-01-20 09:40:46', '1', '2026-01-20 09:40:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2424, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873277081.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873277081.png',
        'image/png', 166166, '1', '2026-01-20 09:41:18', '1', '2026-01-20 09:41:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2425, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873305461.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873305461.png',
        'image/png', 771105, '1', '2026-01-20 09:41:47', '1', '2026-01-20 09:41:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2426, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873335676.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873335676.png',
        'image/png', 774778, '1', '2026-01-20 09:42:17', '1', '2026-01-20 09:42:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2427, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873335971.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873335971.png',
        'image/png', 67208, '1', '2026-01-20 09:42:18', '1', '2026-01-20 09:42:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2428, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873366052.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873366052.png',
        'image/png', 798209, '1', '2026-01-20 09:42:48', '1', '2026-01-20 09:42:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2429, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873395442.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873395442.png',
        'image/png', 820005, '1', '2026-01-20 09:43:17', '1', '2026-01-20 09:43:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2430, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873417927.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873417927.png',
        'image/png', 67229, '1', '2026-01-20 09:43:38', '1', '2026-01-20 09:43:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2431, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873425728.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873425728.png',
        'image/png', 794985, '1', '2026-01-20 09:43:47', '1', '2026-01-20 09:43:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2432, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873437529.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873437529.png',
        'image/png', 67340, '1', '2026-01-20 09:43:58', '1', '2026-01-20 09:43:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2433, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873441907.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873441907.png',
        'image/png', 67217, '1', '2026-01-20 09:44:02', '1', '2026-01-20 09:44:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2434, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873455393.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873455393.png',
        'image/png', 806193, '1', '2026-01-20 09:44:17', '1', '2026-01-20 09:44:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2435, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873500135.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873500135.png',
        'image/png', 765529, '1', '2026-01-20 09:45:01', '1', '2026-01-20 09:45:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2436, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873516343.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873516343.png',
        'image/png', 765312, '1', '2026-01-20 09:45:18', '1', '2026-01-20 09:45:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2437, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873518885.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873518885.png',
        'image/png', 67058, '1', '2026-01-20 09:45:19', '1', '2026-01-20 09:45:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2438, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873531920.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873531920.png',
        'image/png', 67271, '1', '2026-01-20 09:45:32', '1', '2026-01-20 09:45:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2439, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873565995.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873565995.png',
        'image/png', 67271, '1', '2026-01-20 09:46:06', '1', '2026-01-20 09:46:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2440, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873567666.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873567666.png',
        'image/png', 745321, '1', '2026-01-20 09:46:09', '1', '2026-01-20 09:46:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2441, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873575499.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873575499.png',
        'image/png', 746247, '1', '2026-01-20 09:46:17', '1', '2026-01-20 09:46:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2442, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873605547.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873605547.png',
        'image/png', 791247, '1', '2026-01-20 09:46:47', '1', '2026-01-20 09:46:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2443, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873636268.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873636268.png',
        'image/png', 745374, '1', '2026-01-20 09:47:18', '1', '2026-01-20 09:47:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2444, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873777670.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873777670.png',
        'image/png', 752420, '1', '2026-01-20 09:49:39', '1', '2026-01-20 09:49:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2445, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873778231.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873778231.png',
        'image/png', 752693, '1', '2026-01-20 09:49:40', '1', '2026-01-20 09:49:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2446, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873785346.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873785346.png',
        'image/png', 770970, '1', '2026-01-20 09:49:46', '1', '2026-01-20 09:49:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2447, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873788006.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873788006.png',
        'image/png', 67196, '1', '2026-01-20 09:49:48', '1', '2026-01-20 09:49:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2448, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873851366.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873851366.png',
        'image/png', 150021, '1', '2026-01-20 09:50:52', '1', '2026-01-20 09:50:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2449, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873876332.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873876332.png',
        'image/png', 67196, '1', '2026-01-20 09:51:17', '1', '2026-01-20 09:51:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2450, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873881006.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873881006.png',
        'image/png', 159387, '1', '2026-01-20 09:51:21', '1', '2026-01-20 09:51:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2451, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873940840.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873940840.png',
        'image/png', 155776, '1', '2026-01-20 09:52:23', '1', '2026-01-20 09:52:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2452, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873970322.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873970322.png',
        'image/png', 152193, '1', '2026-01-20 09:52:51', '1', '2026-01-20 09:52:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2453, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768873984005.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768873984005.png',
        'image/png', 67196, '1', '2026-01-20 09:53:04', '1', '2026-01-20 09:53:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2454, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768873999912.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768873999912.png',
        'image/png', 159672, '1', '2026-01-20 09:53:20', '1', '2026-01-20 09:53:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2455, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874032291.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874032291.png',
        'image/png', 163741, '1', '2026-01-20 09:53:53', '1', '2026-01-20 09:53:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2456, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874060026.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874060026.png',
        'image/png', 152804, '1', '2026-01-20 09:54:21', '1', '2026-01-20 09:54:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2457, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874081218.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874081218.png',
        'image/png', 67196, '1', '2026-01-20 09:54:41', '1', '2026-01-20 09:54:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2458, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874090741.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874090741.png',
        'image/png', 161004, '1', '2026-01-20 09:54:51', '1', '2026-01-20 09:54:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2459, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874120562.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874120562.png',
        'image/png', 159053, '1', '2026-01-20 09:55:21', '1', '2026-01-20 09:55:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2460, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874150132.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874150132.png',
        'image/png', 152986, '1', '2026-01-20 09:55:50', '1', '2026-01-20 09:55:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2461, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874180342.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874180342.png',
        'image/png', 154342, '1', '2026-01-20 09:56:21', '1', '2026-01-20 09:56:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2462, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874196877.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874196877.png',
        'image/png', 67196, '1', '2026-01-20 09:56:37', '1', '2026-01-20 09:56:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2463, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874212425.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874212425.png',
        'image/png', 163893, '1', '2026-01-20 09:56:53', '1', '2026-01-20 09:56:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2464, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874240684.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874240684.png',
        'image/png', 165840, '1', '2026-01-20 09:57:21', '1', '2026-01-20 09:57:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2465, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874272935.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874272935.png',
        'image/png', 155887, '1', '2026-01-20 09:57:55', '1', '2026-01-20 09:57:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2466, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874305557.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874305557.png',
        'image/png', 155688, '1', '2026-01-20 09:58:26', '1', '2026-01-20 09:58:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2467, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874324979.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874324979.png',
        'image/png', 67196, '1', '2026-01-20 09:58:45', '1', '2026-01-20 09:58:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2468, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874332053.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874332053.png',
        'image/png', 160826, '1', '2026-01-20 09:58:53', '1', '2026-01-20 09:58:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2469, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874366557.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874366557.png',
        'image/png', 160937, '1', '2026-01-20 09:59:27', '1', '2026-01-20 09:59:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2470, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874536395.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874536395.png',
        'image/png', 161288, '1', '2026-01-20 10:02:17', '1', '2026-01-20 10:02:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2471, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874537005.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874537005.png',
        'image/png', 161297, '1', '2026-01-20 10:02:18', '1', '2026-01-20 10:02:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2472, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874540065.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874540065.png',
        'image/png', 161054, '1', '2026-01-20 10:02:20', '1', '2026-01-20 10:02:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2473, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874612916.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874612916.png',
        'image/png', 67196, '1', '2026-01-20 10:03:33', '1', '2026-01-20 10:03:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2474, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874661441.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874661441.png',
        'image/png', 162088, '1', '2026-01-20 10:04:22', '1', '2026-01-20 10:04:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2475, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874661952.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874661952.png',
        'image/png', 165188, '1', '2026-01-20 10:04:22', '1', '2026-01-20 10:04:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2476, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874701544.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874701544.png',
        'image/png', 161873, '1', '2026-01-20 10:05:02', '1', '2026-01-20 10:05:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2477, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874720230.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874720230.png',
        'image/png', 162941, '1', '2026-01-20 10:05:20', '1', '2026-01-20 10:05:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2478, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874734063.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874734063.png',
        'image/png', 67196, '1', '2026-01-20 10:05:34', '1', '2026-01-20 10:05:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2479, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874752137.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874752137.png',
        'image/png', 152617, '1', '2026-01-20 10:05:53', '1', '2026-01-20 10:05:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2480, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874780183.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874780183.png',
        'image/png', 161592, '1', '2026-01-20 10:06:20', '1', '2026-01-20 10:06:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2481, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874811347.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874811347.png',
        'image/png', 166094, '1', '2026-01-20 10:06:55', '1', '2026-01-20 10:06:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2482, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874842013.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874842013.png',
        'image/png', 159674, '1', '2026-01-20 10:07:24', '1', '2026-01-20 10:07:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2483, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874880764.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874880764.png',
        'image/png', 67196, '1', '2026-01-20 10:08:03', '1', '2026-01-20 10:08:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2484, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874894359.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874894359.png',
        'image/png', 157512, '1', '2026-01-20 10:08:15', '1', '2026-01-20 10:08:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2485, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874899876.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874899876.png',
        'image/png', 153953, '1', '2026-01-20 10:08:20', '1', '2026-01-20 10:08:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2486, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874930905.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874930905.png',
        'image/png', 161037, '1', '2026-01-20 10:08:51', '1', '2026-01-20 10:08:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2487, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874959671.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874959671.png',
        'image/png', 157903, '1', '2026-01-20 10:09:20', '1', '2026-01-20 10:09:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2488, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768874963895.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768874963895.png',
        'image/png', 67196, '1', '2026-01-20 10:09:24', '1', '2026-01-20 10:09:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2489, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768874990049.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768874990049.png',
        'image/png', 161911, '1', '2026-01-20 10:09:51', '1', '2026-01-20 10:09:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2490, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875019748.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875019748.png',
        'image/png', 153815, '1', '2026-01-20 10:10:20', '1', '2026-01-20 10:10:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2491, 22, 'go-view/22_index_preview.png', '20260120/22_index_preview_1768875043053.png', 'http://test.yudao.iocoder.cn/20260120/22_index_preview_1768875043053.png',
        'image/png', 67196, '1', '2026-01-20 10:10:43', '1', '2026-01-20 10:10:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2492, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875050543.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875050543.png',
        'image/png', 163869, '1', '2026-01-20 10:10:51', '1', '2026-01-20 10:10:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2493, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875313008.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875313008.png',
        'image/png', 161763, '1', '2026-01-20 10:15:14', '1', '2026-01-20 10:15:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2494, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875341890.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875341890.png',
        'image/png', 154972, '1', '2026-01-20 10:15:42', '1', '2026-01-20 10:15:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2495, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875372053.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875372053.png',
        'image/png', 160392, '1', '2026-01-20 10:16:12', '1', '2026-01-20 10:16:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2496, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875430264.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875430264.png',
        'image/png', 164437, '1', '2026-01-20 10:17:11', '1', '2026-01-20 10:17:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2497, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875467276.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875467276.png',
        'image/png', 164360, '1', '2026-01-20 10:17:48', '1', '2026-01-20 10:17:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2498, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875467280.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875467280.png',
        'image/png', 164360, '1', '2026-01-20 10:17:49', '1', '2026-01-20 10:17:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2499, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875497814.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875497814.png',
        'image/png', 158615, '1', '2026-01-20 10:18:18', '1', '2026-01-20 10:18:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2500, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875522917.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875522917.png',
        'image/png', 157944, '1', '2026-01-20 10:18:46', '1', '2026-01-20 10:18:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2501, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875649264.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875649264.png',
        'image/png', 157934, '1', '2026-01-20 10:20:50', '1', '2026-01-20 10:20:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2502, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875683823.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875683823.png',
        'image/png', 157912, '1', '2026-01-20 10:21:24', '1', '2026-01-20 10:21:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2503, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875684089.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875684089.png',
        'image/png', 157867, '1', '2026-01-20 10:21:25', '1', '2026-01-20 10:21:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2504, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875826500.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875826500.png',
        'image/png', 157341, '1', '2026-01-20 10:23:47', '1', '2026-01-20 10:23:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2505, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875826717.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875826717.png',
        'image/png', 157198, '1', '2026-01-20 10:24:39', '1', '2026-01-20 10:24:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2506, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875865407.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875865407.png',
        'image/png', 160775, '1', '2026-01-20 10:24:40', '1', '2026-01-20 10:24:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2507, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875882210.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875882210.png',
        'image/png', 157214, '1', '2026-01-20 10:24:46', '1', '2026-01-20 10:24:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2508, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875912196.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875912196.png',
        'image/png', 162295, '1', '2026-01-20 10:25:13', '1', '2026-01-20 10:25:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2509, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875942272.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875942272.png',
        'image/png', 152430, '1', '2026-01-20 10:25:43', '1', '2026-01-20 10:25:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2510, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768875972897.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768875972897.png',
        'image/png', 165814, '1', '2026-01-20 10:26:14', '1', '2026-01-20 10:26:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2511, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876021209.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876021209.png',
        'image/png', 159919, '1', '2026-01-20 10:27:02', '1', '2026-01-20 10:27:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2512, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876031978.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876031978.png',
        'image/png', 154657, '1', '2026-01-20 10:27:13', '1', '2026-01-20 10:27:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2513, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876065388.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876065388.png',
        'image/png', 152624, '1', '2026-01-20 10:27:46', '1', '2026-01-20 10:27:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2514, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876104203.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876104203.png',
        'image/png', 151795, '1', '2026-01-20 10:28:27', '1', '2026-01-20 10:28:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2515, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876164085.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876164085.png',
        'image/png', 152330, '1', '2026-01-20 10:29:25', '1', '2026-01-20 10:29:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2516, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876181682.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876181682.png',
        'image/png', 160303, '1', '2026-01-20 10:29:42', '1', '2026-01-20 10:29:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2517, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876211782.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876211782.png',
        'image/png', 153436, '1', '2026-01-20 10:30:12', '1', '2026-01-20 10:30:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2518, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876242379.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876242379.png',
        'image/png', 165907, '1', '2026-01-20 10:30:43', '1', '2026-01-20 10:30:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2519, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876273293.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876273293.png',
        'image/png', 151553, '1', '2026-01-20 10:31:14', '1', '2026-01-20 10:31:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2520, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876337781.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876337781.png',
        'image/png', 152189, '1', '2026-01-20 10:32:18', '1', '2026-01-20 10:32:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2521, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876362480.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876362480.png',
        'image/png', 167630, '1', '2026-01-20 10:32:43', '1', '2026-01-20 10:32:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2522, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876394370.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876394370.png',
        'image/png', 162720, '1', '2026-01-20 10:33:15', '1', '2026-01-20 10:33:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2523, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876422535.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876422535.png',
        'image/png', 152815, '1', '2026-01-20 10:33:43', '1', '2026-01-20 10:33:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2524, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876452196.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876452196.png',
        'image/png', 166324, '1', '2026-01-20 10:34:12', '1', '2026-01-20 10:34:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2525, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876482487.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876482487.png',
        'image/png', 152286, '1', '2026-01-20 10:34:43', '1', '2026-01-20 10:34:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2526, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876511918.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876511918.png',
        'image/png', 164835, '1', '2026-01-20 10:35:12', '1', '2026-01-20 10:35:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2527, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876648032.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876648032.png',
        'image/png', 161130, '1', '2026-01-20 10:37:28', '1', '2026-01-20 10:37:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2528, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876725029.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876725029.png',
        'image/png', 161106, '1', '2026-01-20 10:38:45', '1', '2026-01-20 10:38:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2529, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876741078.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876741078.png',
        'image/png', 161106, '1', '2026-01-20 10:39:01', '1', '2026-01-20 10:39:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2530, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876751740.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876751740.png',
        'image/png', 156348, '1', '2026-01-20 10:39:12', '1', '2026-01-20 10:39:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2531, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768876873048.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768876873048.png',
        'image/png', 153401, '1', '2026-01-20 10:41:13', '1', '2026-01-20 10:41:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2532, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877071750.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877071750.png',
        'image/png', 153591, '1', '2026-01-20 10:44:35', '1', '2026-01-20 10:44:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2533, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877082916.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877082916.png',
        'image/png', 160256, '1', '2026-01-20 10:44:43', '1', '2026-01-20 10:44:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2534, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877128180.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877128180.png',
        'image/png', 160160, '1', '2026-01-20 10:45:28', '1', '2026-01-20 10:45:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2535, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877142976.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877142976.png',
        'image/png', 160895, '1', '2026-01-20 10:45:43', '1', '2026-01-20 10:45:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2536, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877244735.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877244735.png',
        'image/png', 160964, '1', '2026-01-20 10:47:32', '1', '2026-01-20 10:47:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2537, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877573418.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877573418.png',
        'image/png', 161577, '1', '2026-01-20 10:52:54', '1', '2026-01-20 10:52:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2538, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877591764.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877591764.png',
        'image/png', 160924, '1', '2026-01-20 10:53:12', '1', '2026-01-20 10:53:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2539, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877750134.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877750134.png',
        'image/png', 157957, '1', '2026-01-20 10:55:51', '1', '2026-01-20 10:55:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2540, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877772459.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877772459.png',
        'image/png', 108646, '1', '2026-01-20 10:56:13', '1', '2026-01-20 10:56:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2541, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877818448.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877818448.png',
        'image/png', 168223, '1', '2026-01-20 10:56:59', '1', '2026-01-20 10:56:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2542, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877944585.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877944585.png',
        'image/png', 149884, '1', '2026-01-20 10:59:05', '1', '2026-01-20 10:59:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2543, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768877999897.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768877999897.png',
        'image/png', 156935, '1', '2026-01-20 11:00:00', '1', '2026-01-20 11:00:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2544, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878029838.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878029838.png',
        'image/png', 160029, '1', '2026-01-20 11:00:30', '1', '2026-01-20 11:00:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2545, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878071461.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878071461.png',
        'image/png', 151014, '1', '2026-01-20 11:01:12', '1', '2026-01-20 11:01:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2546, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878101514.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878101514.png',
        'image/png', 158916, '1', '2026-01-20 11:01:42', '1', '2026-01-20 11:01:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2547, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878131413.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878131413.png',
        'image/png', 147033, '1', '2026-01-20 11:02:13', '1', '2026-01-20 11:02:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2548, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878162278.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878162278.png',
        'image/png', 160799, '1', '2026-01-20 11:02:46', '1', '2026-01-20 11:02:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2549, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878191458.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878191458.png',
        'image/png', 151196, '1', '2026-01-20 11:03:12', '1', '2026-01-20 11:03:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2550, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878221297.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878221297.png',
        'image/png', 156754, '1', '2026-01-20 11:03:42', '1', '2026-01-20 11:03:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2551, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878251494.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878251494.png',
        'image/png', 155278, '1', '2026-01-20 11:04:12', '1', '2026-01-20 11:04:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2552, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878282221.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878282221.png',
        'image/png', 153804, '1', '2026-01-20 11:04:43', '1', '2026-01-20 11:04:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2553, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878311561.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878311561.png',
        'image/png', 156125, '1', '2026-01-20 11:05:13', '1', '2026-01-20 11:05:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2554, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878809993.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878809993.png',
        'image/png', 158983, '1', '2026-01-20 11:13:31', '1', '2026-01-20 11:13:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2555, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878911033.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878911033.png',
        'image/png', 158983, '1', '2026-01-20 11:15:12', '1', '2026-01-20 11:15:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2556, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768878994918.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768878994918.png',
        'image/png', 158983, '1', '2026-01-20 11:16:36', '1', '2026-01-20 11:16:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2557, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879092226.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879092226.png',
        'image/png', 158983, '1', '2026-01-20 11:18:13', '1', '2026-01-20 11:18:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2558, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879266450.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879266450.png',
        'image/png', 158983, '1', '2026-01-20 11:21:07', '1', '2026-01-20 11:21:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2559, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879411996.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879411996.png',
        'image/png', 158983, '1', '2026-01-20 11:23:33', '1', '2026-01-20 11:23:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2560, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879498355.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879498355.png',
        'image/png', 158983, '1', '2026-01-20 11:24:59', '1', '2026-01-20 11:24:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2561, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879607779.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879607779.png',
        'image/png', 158983, '1', '2026-01-20 11:26:49', '1', '2026-01-20 11:26:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2562, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879734821.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879734821.png',
        'image/png', 159243, '1', '2026-01-20 11:28:56', '1', '2026-01-20 11:28:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2563, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879809897.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879809897.png',
        'image/png', 159243, '1', '2026-01-20 11:30:11', '1', '2026-01-20 11:30:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2564, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879908968.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879908968.png',
        'image/png', 159243, '1', '2026-01-20 11:31:51', '1', '2026-01-20 11:31:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2565, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768879985772.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768879985772.png',
        'image/png', 159243, '1', '2026-01-20 11:33:07', '1', '2026-01-20 11:33:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2566, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880086652.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880086652.png',
        'image/png', 159243, '1', '2026-01-20 11:34:48', '1', '2026-01-20 11:34:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2567, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880190488.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880190488.png',
        'image/png', 15219, '1', '2026-01-20 11:36:34', '1', '2026-01-20 11:36:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2568, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880280970.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880280970.png',
        'image/png', 15219, '1', '2026-01-20 11:38:02', '1', '2026-01-20 11:38:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2569, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880369094.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880369094.png',
        'image/png', 15219, '1', '2026-01-20 11:39:30', '1', '2026-01-20 11:39:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2570, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880450737.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880450737.png',
        'image/png', 15219, '1', '2026-01-20 11:40:51', '1', '2026-01-20 11:40:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2571, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880526718.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880526718.png',
        'image/png', 15219, '1', '2026-01-20 11:42:07', '1', '2026-01-20 11:42:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2572, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880609288.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880609288.png',
        'image/png', 15219, '1', '2026-01-20 11:43:30', '1', '2026-01-20 11:43:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2573, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880687332.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880687332.png',
        'image/png', 15219, '1', '2026-01-20 11:44:49', '1', '2026-01-20 11:44:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2574, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880775220.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880775220.png',
        'image/png', 15219, '1', '2026-01-20 11:46:16', '1', '2026-01-20 11:46:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2575, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880872423.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880872423.png',
        'image/png', 15219, '1', '2026-01-20 11:47:53', '1', '2026-01-20 11:47:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2576, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768880962429.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768880962429.png',
        'image/png', 15219, '1', '2026-01-20 11:49:23', '1', '2026-01-20 11:49:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2577, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768881039528.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768881039528.png',
        'image/png', 15219, '1', '2026-01-20 11:50:40', '1', '2026-01-20 11:50:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2578, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768881377834.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768881377834.png',
        'image/png', 15219, '1', '2026-01-20 11:56:18', '1', '2026-01-20 11:56:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2579, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768881508058.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768881508058.png',
        'image/png', 15219, '1', '2026-01-20 11:58:29', '1', '2026-01-20 11:58:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2580, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768881785977.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768881785977.png',
        'image/png', 15219, '1', '2026-01-20 12:03:07', '1', '2026-01-20 12:03:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2581, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768881981736.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768881981736.png',
        'image/png', 15219, '1', '2026-01-20 12:06:22', '1', '2026-01-20 12:06:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2582, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882059447.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882059447.png',
        'image/png', 15219, '1', '2026-01-20 12:07:40', '1', '2026-01-20 12:07:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2583, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882136611.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882136611.png',
        'image/png', 15219, '1', '2026-01-20 12:08:57', '1', '2026-01-20 12:08:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2584, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882212361.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882212361.png',
        'image/png', 15219, '1', '2026-01-20 12:10:13', '1', '2026-01-20 12:10:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2585, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882288486.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882288486.png',
        'image/png', 15219, '1', '2026-01-20 12:11:29', '1', '2026-01-20 12:11:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2586, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882367136.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882367136.png',
        'image/png', 15219, '1', '2026-01-20 12:12:47', '1', '2026-01-20 12:12:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2587, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882442858.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882442858.png',
        'image/png', 15219, '1', '2026-01-20 12:14:03', '1', '2026-01-20 12:14:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2588, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882520045.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882520045.png',
        'image/png', 15219, '1', '2026-01-20 12:15:20', '1', '2026-01-20 12:15:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2589, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882595777.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882595777.png',
        'image/png', 15219, '1', '2026-01-20 12:16:36', '1', '2026-01-20 12:16:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2590, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882669926.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882669926.png',
        'image/png', 15219, '1', '2026-01-20 12:17:50', '1', '2026-01-20 12:17:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2591, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882742665.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882742665.png',
        'image/png', 15219, '1', '2026-01-20 12:19:03', '1', '2026-01-20 12:19:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2592, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882838829.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882838829.png',
        'image/png', 15219, '1', '2026-01-20 12:20:39', '1', '2026-01-20 12:20:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2593, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882919640.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882919640.png',
        'image/png', 15219, '1', '2026-01-20 12:22:00', '1', '2026-01-20 12:22:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2594, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768882998986.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768882998986.png',
        'image/png', 15219, '1', '2026-01-20 12:23:19', '1', '2026-01-20 12:23:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2595, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883073415.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883073415.png',
        'image/png', 15219, '1', '2026-01-20 12:24:34', '1', '2026-01-20 12:24:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2596, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883149342.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883149342.png',
        'image/png', 15219, '1', '2026-01-20 12:25:50', '1', '2026-01-20 12:25:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2597, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883226816.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883226816.png',
        'image/png', 15219, '1', '2026-01-20 12:27:07', '1', '2026-01-20 12:27:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2598, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883302386.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883302386.png',
        'image/png', 15219, '1', '2026-01-20 12:28:23', '1', '2026-01-20 12:28:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2599, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883382995.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883382995.png',
        'image/png', 15219, '1', '2026-01-20 12:29:43', '1', '2026-01-20 12:29:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2600, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883461165.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883461165.png',
        'image/png', 15219, '1', '2026-01-20 12:31:01', '1', '2026-01-20 12:31:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2601, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883536691.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883536691.png',
        'image/png', 15219, '1', '2026-01-20 12:32:17', '1', '2026-01-20 12:32:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2602, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883613029.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883613029.png',
        'image/png', 15219, '1', '2026-01-20 12:33:33', '1', '2026-01-20 12:33:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2603, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883687058.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883687058.png',
        'image/png', 15219, '1', '2026-01-20 12:34:47', '1', '2026-01-20 12:34:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2604, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883769869.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883769869.png',
        'image/png', 15219, '1', '2026-01-20 12:36:10', '1', '2026-01-20 12:36:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2605, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883845369.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883845369.png',
        'image/png', 15219, '1', '2026-01-20 12:37:26', '1', '2026-01-20 12:37:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2606, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883922979.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883922979.png',
        'image/png', 15219, '1', '2026-01-20 12:38:43', '1', '2026-01-20 12:38:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2607, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768883996481.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768883996481.png',
        'image/png', 15219, '1', '2026-01-20 12:39:57', '1', '2026-01-20 12:39:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2608, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884074422.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884074422.png',
        'image/png', 15219, '1', '2026-01-20 12:41:15', '1', '2026-01-20 12:41:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2609, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884151975.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884151975.png',
        'image/png', 15219, '1', '2026-01-20 12:42:32', '1', '2026-01-20 12:42:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2610, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884229009.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884229009.png',
        'image/png', 15219, '1', '2026-01-20 12:43:49', '1', '2026-01-20 12:43:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2611, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884306172.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884306172.png',
        'image/png', 15219, '1', '2026-01-20 12:45:06', '1', '2026-01-20 12:45:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2612, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884382911.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884382911.png',
        'image/png', 15219, '1', '2026-01-20 12:46:23', '1', '2026-01-20 12:46:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2613, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884462992.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884462992.png',
        'image/png', 15219, '1', '2026-01-20 12:47:43', '1', '2026-01-20 12:47:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2614, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884538439.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884538439.png',
        'image/png', 15219, '1', '2026-01-20 12:48:59', '1', '2026-01-20 12:48:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2615, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884613514.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884613514.png',
        'image/png', 15219, '1', '2026-01-20 12:50:14', '1', '2026-01-20 12:50:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2616, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884688404.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884688404.png',
        'image/png', 15219, '1', '2026-01-20 12:51:29', '1', '2026-01-20 12:51:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2617, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884762980.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884762980.png',
        'image/png', 15219, '1', '2026-01-20 12:52:43', '1', '2026-01-20 12:52:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2618, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884848429.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884848429.png',
        'image/png', 15219, '1', '2026-01-20 12:54:09', '1', '2026-01-20 12:54:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2619, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768884925741.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768884925741.png',
        'image/png', 15219, '1', '2026-01-20 12:55:26', '1', '2026-01-20 12:55:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2620, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885001959.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885001959.png',
        'image/png', 15219, '1', '2026-01-20 12:56:42', '1', '2026-01-20 12:56:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2621, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885076914.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885076914.png',
        'image/png', 15219, '1', '2026-01-20 12:57:57', '1', '2026-01-20 12:57:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2622, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885154558.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885154558.png',
        'image/png', 15219, '1', '2026-01-20 12:59:15', '1', '2026-01-20 12:59:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2623, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885231328.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885231328.png',
        'image/png', 15219, '1', '2026-01-20 13:00:32', '1', '2026-01-20 13:00:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2624, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885309004.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885309004.png',
        'image/png', 15219, '1', '2026-01-20 13:01:49', '1', '2026-01-20 13:01:49', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2625, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885387258.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885387258.png',
        'image/png', 15219, '1', '2026-01-20 13:03:08', '1', '2026-01-20 13:03:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2626, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885463224.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885463224.png',
        'image/png', 15219, '1', '2026-01-20 13:04:24', '1', '2026-01-20 13:04:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2627, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885539835.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885539835.png',
        'image/png', 15219, '1', '2026-01-20 13:05:40', '1', '2026-01-20 13:05:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2628, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885617610.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885617610.png',
        'image/png', 15219, '1', '2026-01-20 13:06:58', '1', '2026-01-20 13:06:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2629, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885692569.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885692569.png',
        'image/png', 15219, '1', '2026-01-20 13:08:13', '1', '2026-01-20 13:08:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2630, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885765204.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885765204.png',
        'image/png', 15219, '1', '2026-01-20 13:09:25', '1', '2026-01-20 13:09:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2631, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885851484.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885851484.png',
        'image/png', 15219, '1', '2026-01-20 13:10:52', '1', '2026-01-20 13:10:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2632, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768885937830.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768885937830.png',
        'image/png', 15219, '1', '2026-01-20 13:12:18', '1', '2026-01-20 13:12:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2633, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886013901.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886013901.png',
        'image/png', 15219, '1', '2026-01-20 13:13:34', '1', '2026-01-20 13:13:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2634, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886091416.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886091416.png',
        'image/png', 15219, '1', '2026-01-20 13:14:52', '1', '2026-01-20 13:14:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2635, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886167088.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886167088.png',
        'image/png', 15219, '1', '2026-01-20 13:16:07', '1', '2026-01-20 13:16:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2636, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886247338.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886247338.png',
        'image/png', 15219, '1', '2026-01-20 13:17:28', '1', '2026-01-20 13:17:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2637, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886323980.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886323980.png',
        'image/png', 15219, '1', '2026-01-20 13:18:44', '1', '2026-01-20 13:18:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2638, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886399220.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886399220.png',
        'image/png', 15219, '1', '2026-01-20 13:20:00', '1', '2026-01-20 13:20:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2639, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886475800.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886475800.png',
        'image/png', 15219, '1', '2026-01-20 13:21:16', '1', '2026-01-20 13:21:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2640, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886552438.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886552438.png',
        'image/png', 15219, '1', '2026-01-20 13:22:33', '1', '2026-01-20 13:22:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2641, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886631002.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886631002.png',
        'image/png', 15219, '1', '2026-01-20 13:23:51', '1', '2026-01-20 13:23:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2642, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886707339.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886707339.png',
        'image/png', 15219, '1', '2026-01-20 13:25:08', '1', '2026-01-20 13:25:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2643, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886785647.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886785647.png',
        'image/png', 15219, '1', '2026-01-20 13:26:26', '1', '2026-01-20 13:26:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2644, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886871007.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886871007.png',
        'image/png', 15219, '1', '2026-01-20 13:27:51', '1', '2026-01-20 13:27:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2645, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768886947208.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768886947208.png',
        'image/png', 15219, '1', '2026-01-20 13:29:08', '1', '2026-01-20 13:29:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2646, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887021905.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887021905.png',
        'image/png', 15219, '1', '2026-01-20 13:30:22', '1', '2026-01-20 13:30:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2647, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887098808.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887098808.png',
        'image/png', 15219, '1', '2026-01-20 13:31:39', '1', '2026-01-20 13:31:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2648, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887175551.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887175551.png',
        'image/png', 15219, '1', '2026-01-20 13:32:56', '1', '2026-01-20 13:32:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2649, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887252291.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887252291.png',
        'image/png', 15219, '1', '2026-01-20 13:34:13', '1', '2026-01-20 13:34:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2650, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887329185.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887329185.png',
        'image/png', 15219, '1', '2026-01-20 13:35:29', '1', '2026-01-20 13:35:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2651, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887407013.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887407013.png',
        'image/png', 15219, '1', '2026-01-20 13:36:47', '1', '2026-01-20 13:36:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2652, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887482049.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887482049.png',
        'image/png', 15219, '1', '2026-01-20 13:38:02', '1', '2026-01-20 13:38:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2653, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887557263.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887557263.png',
        'image/png', 15219, '1', '2026-01-20 13:39:18', '1', '2026-01-20 13:39:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2654, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887629886.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887629886.png',
        'image/png', 15219, '1', '2026-01-20 13:40:30', '1', '2026-01-20 13:40:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2655, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887703061.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887703061.png',
        'image/png', 15219, '1', '2026-01-20 13:41:43', '1', '2026-01-20 13:41:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2656, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887790915.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887790915.png',
        'image/png', 15219, '1', '2026-01-20 13:43:11', '1', '2026-01-20 13:43:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2657, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887864855.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887864855.png',
        'image/png', 15219, '1', '2026-01-20 13:44:25', '1', '2026-01-20 13:44:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2658, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768887943999.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768887943999.png',
        'image/png', 15219, '1', '2026-01-20 13:45:44', '1', '2026-01-20 13:45:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2659, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888018616.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888018616.png',
        'image/png', 15219, '1', '2026-01-20 13:46:59', '1', '2026-01-20 13:46:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2660, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888094073.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888094073.png',
        'image/png', 15219, '1', '2026-01-20 13:48:14', '1', '2026-01-20 13:48:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2661, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888170852.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888170852.png',
        'image/png', 15219, '1', '2026-01-20 13:49:31', '1', '2026-01-20 13:49:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2662, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888249864.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888249864.png',
        'image/png', 15219, '1', '2026-01-20 13:50:50', '1', '2026-01-20 13:50:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2663, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888325864.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888325864.png',
        'image/png', 15219, '1', '2026-01-20 13:52:06', '1', '2026-01-20 13:52:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2664, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888405859.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888405859.png',
        'image/png', 15219, '1', '2026-01-20 13:53:26', '1', '2026-01-20 13:53:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2665, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888482985.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888482985.png',
        'image/png', 15219, '1', '2026-01-20 13:54:43', '1', '2026-01-20 13:54:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2666, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888558451.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888558451.png',
        'image/png', 15219, '1', '2026-01-20 13:55:59', '1', '2026-01-20 13:55:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2667, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888635093.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888635093.png',
        'image/png', 15219, '1', '2026-01-20 13:57:15', '1', '2026-01-20 13:57:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2668, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888707662.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888707662.png',
        'image/png', 15219, '1', '2026-01-20 13:58:28', '1', '2026-01-20 13:58:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2669, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888781066.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888781066.png',
        'image/png', 15219, '1', '2026-01-20 13:59:41', '1', '2026-01-20 13:59:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2670, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888871176.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888871176.png',
        'image/png', 15219, '1', '2026-01-20 14:01:11', '1', '2026-01-20 14:01:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2671, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768888945258.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768888945258.png',
        'image/png', 15219, '1', '2026-01-20 14:02:26', '1', '2026-01-20 14:02:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2672, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889022966.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889022966.png',
        'image/png', 15219, '1', '2026-01-20 14:03:43', '1', '2026-01-20 14:03:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2673, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889097886.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889097886.png',
        'image/png', 15219, '1', '2026-01-20 14:04:58', '1', '2026-01-20 14:04:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2674, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889174035.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889174035.png',
        'image/png', 15219, '1', '2026-01-20 14:06:14', '1', '2026-01-20 14:06:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2675, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889251822.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889251822.png',
        'image/png', 15219, '1', '2026-01-20 14:07:32', '1', '2026-01-20 14:07:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2676, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889330995.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889330995.png',
        'image/png', 15219, '1', '2026-01-20 14:08:51', '1', '2026-01-20 14:08:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2677, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889400743.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889400743.png',
        'image/png', 15219, '1', '2026-01-20 14:10:01', '1', '2026-01-20 14:10:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2678, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889411492.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889411492.png',
        'image/png', 15219, '1', '2026-01-20 14:10:12', '1', '2026-01-20 14:10:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2679, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889441866.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889441866.png',
        'image/png', 15219, '1', '2026-01-20 14:10:42', '1', '2026-01-20 14:10:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2680, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889621177.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889621177.png',
        'image/png', 154968, '1', '2026-01-20 14:13:42', '1', '2026-01-20 14:13:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2681, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889704024.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889704024.png',
        'image/png', 154968, '1', '2026-01-20 14:15:04', '1', '2026-01-20 14:15:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2682, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889778043.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889778043.png',
        'image/png', 154968, '1', '2026-01-20 14:16:19', '1', '2026-01-20 14:16:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2683, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889862840.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889862840.png',
        'image/png', 154968, '1', '2026-01-20 14:17:43', '1', '2026-01-20 14:17:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2684, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768889939388.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768889939388.png',
        'image/png', 154968, '1', '2026-01-20 14:19:00', '1', '2026-01-20 14:19:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2685, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890016126.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890016126.png',
        'image/png', 154968, '1', '2026-01-20 14:20:17', '1', '2026-01-20 14:20:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2686, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890092165.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890092165.png',
        'image/png', 154968, '1', '2026-01-20 14:21:33', '1', '2026-01-20 14:21:33', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2687, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890174506.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890174506.png',
        'image/png', 154791, '1', '2026-01-20 14:22:55', '1', '2026-01-20 14:22:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2688, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890257383.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890257383.png',
        'image/png', 154791, '1', '2026-01-20 14:24:18', '1', '2026-01-20 14:24:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2689, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890336251.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890336251.png',
        'image/png', 154791, '1', '2026-01-20 14:25:37', '1', '2026-01-20 14:25:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2690, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890417244.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890417244.png',
        'image/png', 154791, '1', '2026-01-20 14:26:58', '1', '2026-01-20 14:26:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2691, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890507677.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890507677.png',
        'image/png', 154791, '1', '2026-01-20 14:28:28', '1', '2026-01-20 14:28:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2692, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890593782.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890593782.png',
        'image/png', 154791, '1', '2026-01-20 14:29:55', '1', '2026-01-20 14:29:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2693, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890676290.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890676290.png',
        'image/png', 154791, '1', '2026-01-20 14:31:17', '1', '2026-01-20 14:31:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2694, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890761701.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890761701.png',
        'image/png', 154791, '1', '2026-01-20 14:32:42', '1', '2026-01-20 14:32:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2695, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890846129.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890846129.png',
        'image/png', 154791, '1', '2026-01-20 14:34:07', '1', '2026-01-20 14:34:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2696, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768890929706.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768890929706.png',
        'image/png', 154791, '1', '2026-01-20 14:35:30', '1', '2026-01-20 14:35:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2697, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891054749.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891054749.png',
        'image/png', 154791, '1', '2026-01-20 14:37:35', '1', '2026-01-20 14:37:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2698, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891204628.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891204628.png',
        'image/png', 154791, '1', '2026-01-20 14:40:06', '1', '2026-01-20 14:40:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2699, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891326104.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891326104.png',
        'image/png', 154791, '1', '2026-01-20 14:42:07', '1', '2026-01-20 14:42:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2700, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891447065.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891447065.png',
        'image/png', 154791, '1', '2026-01-20 14:44:08', '1', '2026-01-20 14:44:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2701, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891575167.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891575167.png',
        'image/png', 154791, '1', '2026-01-20 14:46:16', '1', '2026-01-20 14:46:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2702, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891716366.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891716366.png',
        'image/png', 154791, '1', '2026-01-20 14:48:37', '1', '2026-01-20 14:48:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2703, 22, 'go-view/23_index_preview.png', '20260120/23_index_preview_1768891814514.png', 'http://test.yudao.iocoder.cn/20260120/23_index_preview_1768891814514.png',
        'image/png', 154791, '1', '2026-01-20 14:50:15', '1', '2026-01-20 14:50:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2704, 22, 'image.png', 'editor-default-image/20260120/image_1768893667615.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260120/image_1768893667615.png',
        'image/png', 233243, '1', '2026-01-20 15:21:10', '1', '2026-01-20 15:21:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2705, 22, 'image.png', 'editor-default-image/20260120/image_1768894291784.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260120/image_1768894291784.png',
        'image/png', 3552, '1', '2026-01-20 15:31:32', '1', '2026-01-20 15:31:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2706, 22, 'image.png', 'editor-default-image/20260121/image_1768967293120.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260121/image_1768967293120.png',
        'image/png', 4986, '1', '2026-01-21 11:48:15', '1', '2026-01-21 11:48:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2707, 22, 'image.png', 'editor-default-image/20260122/image_1769048877187.png', 'http://test.yudao.iocoder.cn/editor-default-image/20260122/image_1769048877187.png',
        'image/png', 3054, '1', '2026-01-22 10:28:00', '1', '2026-01-22 10:28:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2708, 22, '附件.docx', '20260123/附件_1769134986502.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769134986502.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-23 10:23:09', '20220907048', '2026-01-23 10:23:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2709, 22, 'lghjft_wtfk.sql', '20260123/lghjft_wtfk_1769135000523.sql', 'http://test.yudao.iocoder.cn/20260123/lghjft_wtfk_1769135000523.sql', 'application/octet-stream', 0,
        '20220907048', '2026-01-23 10:23:21', '20220907048', '2026-01-23 10:23:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2710, 22, '附件.docx', '20260123/附件_1769135856012.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769135856012.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-23 10:37:36', '1', '2026-01-23 10:37:36', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2711, 22, '附件.docx', '20260123/附件_1769136023443.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769136023443.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-23 10:40:24', '20220907048', '2026-01-23 10:40:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2712, 22, '附件.docx', '20260123/附件_1769137748721.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769137748721.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-23 11:09:11', '1', '2026-01-23 11:09:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2713, 22, '屏幕截图 2026-01-23 113726.png', '20260123/屏幕截图 2026-01-23 113726_1769140855317.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-23 113726_1769140855317.png', 'image/png', 74597, '1', '2026-01-23 12:00:59', '1', '2026-01-23 12:00:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2714, 22, '屏幕截图 2026-01-22 170918.png', '20260123/屏幕截图 2026-01-22 170918_1769151524304.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-22 170918_1769151524304.png', 'image/png', 155505, '20220907048', '2026-01-23 14:58:47', '20220907048',
        '2026-01-23 14:58:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2715, 22, '屏幕截图 2026-01-21 094952.png', '20260123/屏幕截图 2026-01-21 094952_1769151962398.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-21 094952_1769151962398.png', 'image/png', 103773, '1', '2026-01-23 15:06:04', '1', '2026-01-23 15:06:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2716, 22, '附件.docx', '20260123/附件_1769152086087.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769152086087.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-23 15:08:06', '20220907048', '2026-01-23 15:08:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2717, 22, '附件.docx', '20260123/附件_1769152209538.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769152209538.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-23 15:10:10', '20220907048', '2026-01-23 15:10:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2718, 22, '新文件 1.c', '20260123/新文件 1_1769152209554.c', 'http://test.yudao.iocoder.cn/20260123/新文件 1_1769152209554.c', 'application/octet-stream', 1560,
        '20220907048', '2026-01-23 15:10:10', '20220907048', '2026-01-23 15:10:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2719, 22, '屏幕截图 2026-01-14 100856.png', '20260123/屏幕截图 2026-01-14 100856_1769152409900.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-14 100856_1769152409900.png', 'image/png', 104811, '1', '2026-01-23 15:13:30', '1', '2026-01-23 15:13:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2720, 22, '附件.docx', '20260123/附件_1769156530287.docx', 'http://test.yudao.iocoder.cn/20260123/附件_1769156530287.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-23 16:22:13', '20220907048', '2026-01-23 16:22:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2721, 22, '新文件 1.c', '20260123/新文件 1_1769156530287.c', 'http://test.yudao.iocoder.cn/20260123/新文件 1_1769156530287.c', 'application/octet-stream', 1560,
        '20220907048', '2026-01-23 16:22:13', '20220907048', '2026-01-23 16:22:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2722, 22, '屏幕截图 2026-01-12 100206.png', '20260123/屏幕截图 2026-01-12 100206_1769158886724.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-12 100206_1769158886724.png', 'image/png', 13141, '1', '2026-01-23 17:01:27', '1', '2026-01-23 17:01:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2723, 22, '屏幕截图 2026-01-12 100432.png', '20260123/屏幕截图 2026-01-12 100432_1769158886724.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-12 100432_1769158886724.png', 'image/png', 108703, '1', '2026-01-23 17:01:27', '1', '2026-01-23 17:01:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2724, 22, '屏幕截图 2026-01-12 100800.png', '20260123/屏幕截图 2026-01-12 100800_1769158886724.png',
        'http://test.yudao.iocoder.cn/20260123/屏幕截图 2026-01-12 100800_1769158886724.png', 'image/png', 96391, '1', '2026-01-23 17:01:27', '1', '2026-01-23 17:01:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2725, 22, '屏幕截图 2026-01-12 100206.png', '20260126/屏幕截图 2026-01-12 100206_1769388700890.png',
        'http://test.yudao.iocoder.cn/20260126/屏幕截图 2026-01-12 100206_1769388700890.png', 'image/png', 13141, '1', '2026-01-26 08:51:43', '1', '2026-01-26 08:51:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2726, 22, '附件.docx', '20260126/附件_1769389329314.docx', 'http://test.yudao.iocoder.cn/20260126/附件_1769389329314.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-26 09:02:10', '20220907048', '2026-01-26 09:02:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2727, 22, '新文件 1.c', '20260126/新文件 1_1769389357426.c', 'http://test.yudao.iocoder.cn/20260126/新文件 1_1769389357426.c', 'application/octet-stream', 1560,
        '20220907048', '2026-01-26 09:02:38', '20220907048', '2026-01-26 09:02:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2728, 22, '附件.docx', '20260126/附件_1769389941040.docx', 'http://test.yudao.iocoder.cn/20260126/附件_1769389941040.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-26 09:12:21', '1', '2026-01-26 09:12:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2729, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260126/20160512095836_4PiGk.thumb.1000_0_1769417520172.jpeg',
        'http://test.yudao.iocoder.cn/20260126/20160512095836_4PiGk.thumb.1000_0_1769417520172.jpeg', 'image/jpeg', 84595, '1', '2026-01-26 16:52:02', '1', '2026-01-26 16:52:02',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2730, 22, 'R-C.jpg', '20260126/R-C_1769417520172.jpg', 'http://test.yudao.iocoder.cn/20260126/R-C_1769417520172.jpg', 'image/jpeg', 62533, '1', '2026-01-26 16:52:02', '1',
        '2026-01-26 16:52:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2731, 22, '70f1-hiycyfx9128819.jpg', '20260126/70f1-hiycyfx9128819_1769417520172.jpg', 'http://test.yudao.iocoder.cn/20260126/70f1-hiycyfx9128819_1769417520172.jpg',
        'image/jpeg', 926210, '1', '2026-01-26 16:52:02', '1', '2026-01-26 16:52:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2732, 22, 'R-C.jpg', '20260126/R-C_1769421594799.jpg', 'http://test.yudao.iocoder.cn/20260126/R-C_1769421594799.jpg', 'image/jpeg', 62533, '1', '2026-01-26 17:59:55', '1',
        '2026-01-26 17:59:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2733, 22, 'R-C.jpg', '20260127/R-C_1769481597219.jpg', 'http://test.yudao.iocoder.cn/20260127/R-C_1769481597219.jpg', 'image/jpeg', 62533, '1', '2026-01-27 10:40:01', '1',
        '2026-01-27 10:40:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2734, 22, '70f1-hiycyfx9128819.jpg', '20260127/70f1-hiycyfx9128819_1769483173761.jpg', 'http://test.yudao.iocoder.cn/20260127/70f1-hiycyfx9128819_1769483173761.jpg',
        'image/jpeg', 926210, '1', '2026-01-27 11:06:16', '1', '2026-01-27 11:06:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2735, 22, '70f1-hiycyfx9128819.jpg', '20260127/70f1-hiycyfx9128819_1769483253084.jpg', 'http://test.yudao.iocoder.cn/20260127/70f1-hiycyfx9128819_1769483253084.jpg',
        'image/jpeg', 926210, '1', '2026-01-27 11:07:34', '1', '2026-01-27 11:07:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2736, 22, '70f1-hiycyfx9128819.jpg', '20260127/70f1-hiycyfx9128819_1769500554807.jpg', 'http://test.yudao.iocoder.cn/20260127/70f1-hiycyfx9128819_1769500554807.jpg',
        'image/jpeg', 926210, '1', '2026-01-27 15:55:58', '1', '2026-01-27 15:55:58', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2737, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260127/20160512095836_4PiGk.thumb.1000_0_1769501359274.jpeg',
        'http://test.yudao.iocoder.cn/20260127/20160512095836_4PiGk.thumb.1000_0_1769501359274.jpeg', 'image/jpeg', 84595, '1', '2026-01-27 16:09:20', '1', '2026-01-27 16:09:20',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2738, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769567257901.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769567257901.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:27:43', '1', '2026-01-28 10:27:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2739, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769567262933.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769567262933.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:27:43', '1', '2026-01-28 10:27:43',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2740, 22, 'R-C.jpg', '20260128/R-C_1769567281862.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769567281862.jpg', 'image/jpeg', 62533, '1', '2026-01-28 10:28:02', '1',
        '2026-01-28 10:28:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2741, 22, 'R-C.jpg', '20260128/R-C_1769567381137.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769567381137.jpg', 'image/jpeg', 62533, '1', '2026-01-28 10:29:42', '1',
        '2026-01-28 10:29:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2742, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769567393079.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769567393079.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:29:54', '1', '2026-01-28 10:29:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2743, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769567637541.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769567637541.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:33:59', '1', '2026-01-28 10:33:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2744, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769567658169.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769567658169.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:34:18', '1', '2026-01-28 10:34:18',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2745, 22, 'R-C.jpg', '20260128/R-C_1769567674832.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769567674832.jpg', 'image/jpeg', 62533, '1', '2026-01-28 10:34:35', '1',
        '2026-01-28 10:34:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2746, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769567905476.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769567905476.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:38:26', '1', '2026-01-28 10:38:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2747, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769567922776.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769567922776.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:38:43', '1', '2026-01-28 10:38:43',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2748, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769568109271.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769568109271.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:41:50', '1', '2026-01-28 10:41:50',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2749, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769568151693.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769568151693.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:42:34', '1', '2026-01-28 10:42:34',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2750, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769568149390.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769568149390.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:42:39', '1', '2026-01-28 10:42:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2751, 22, 'R-C.jpg', '20260128/R-C_1769568174785.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769568174785.jpg', 'image/jpeg', 62533, '1', '2026-01-28 10:42:55', '1',
        '2026-01-28 10:42:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2752, 22, 'R-C.jpg', '20260128/R-C_1769568182419.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769568182419.jpg', 'image/jpeg', 62533, '1', '2026-01-28 10:43:03', '1',
        '2026-01-28 10:43:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2753, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769568192719.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769568192719.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 10:43:13', '1', '2026-01-28 10:43:13',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2754, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769568196603.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769568196603.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 10:43:20', '1', '2026-01-28 10:43:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2755, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769578861277.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769578861277.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 13:41:02', '1', '2026-01-28 13:41:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2756, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769578865480.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769578865480.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 13:41:06', '1', '2026-01-28 13:41:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2757, 22, 'R-C.jpg', '20260128/R-C_1769578874830.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769578874830.jpg', 'image/jpeg', 62533, '1', '2026-01-28 13:41:15', '1',
        '2026-01-28 13:41:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2758, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769581697207.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769581697207.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 14:28:18', '1', '2026-01-28 14:28:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2759, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769581703277.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769581703277.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 14:28:24', '1', '2026-01-28 14:28:24',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2760, 22, 'R-C.jpg', '20260128/R-C_1769581706653.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769581706653.jpg', 'image/jpeg', 62533, '1', '2026-01-28 14:28:27', '1',
        '2026-01-28 14:28:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2761, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769584315879.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769584315879.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:11:57', '1', '2026-01-28 15:11:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2762, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769584323721.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769584323721.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:12:06', '1', '2026-01-28 15:12:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2763, 22, 'R-C.jpg', '20260128/R-C_1769584326211.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769584326211.jpg', 'image/jpeg', 62533, '1', '2026-01-28 15:12:06', '1',
        '2026-01-28 15:12:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2764, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769584545485.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769584545485.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:15:47', '1', '2026-01-28 15:15:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2765, 22, 'R-C.jpg', '20260128/R-C_1769584556385.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769584556385.jpg', 'image/jpeg', 62533, '1', '2026-01-28 15:16:05', '1',
        '2026-01-28 15:16:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2766, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769584561763.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769584561763.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:16:05', '1', '2026-01-28 15:16:05',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2767, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769584569976.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769584569976.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:16:12', '1', '2026-01-28 15:16:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2768, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769584577838.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769584577838.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:16:18', '1', '2026-01-28 15:16:18',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2769, 22, 'R-C.jpg', '20260128/R-C_1769584581014.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769584581014.jpg', 'image/jpeg', 62533, '1', '2026-01-28 15:16:21', '1',
        '2026-01-28 15:16:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2770, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585108444.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585108444.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:25:11', '1', '2026-01-28 15:25:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2771, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769585112503.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769585112503.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:25:13', '1', '2026-01-28 15:25:13',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2772, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585129061.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585129061.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:25:30', '1', '2026-01-28 15:25:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2773, 22, 'R-C.jpg', '20260128/R-C_1769585137811.jpg', 'http://test.yudao.iocoder.cn/20260128/R-C_1769585137811.jpg', 'image/jpeg', 62533, '1', '2026-01-28 15:25:38', '1',
        '2026-01-28 15:25:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2774, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769585495976.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769585495976.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:31:37', '1', '2026-01-28 15:31:37',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2775, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585493426.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585493426.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:31:37', '1', '2026-01-28 15:31:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2776, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585502394.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585502394.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:31:44', '1', '2026-01-28 15:31:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2777, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585657296.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585657296.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:34:24', '1', '2026-01-28 15:34:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2778, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769585669862.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769585669862.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:34:31', '1', '2026-01-28 15:34:31',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2779, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769585689215.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769585689215.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:34:50', '1', '2026-01-28 15:34:50',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2780, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769585685978.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769585685978.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 15:34:50', '1', '2026-01-28 15:34:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2781, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769585699452.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769585699452.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 15:35:00', '1', '2026-01-28 15:35:00',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2782, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769588396062.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769588396062.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 16:19:57', '1', '2026-01-28 16:19:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2783, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260128/20160512095836_4PiGk.thumb.1000_0_1769588413796.jpeg',
        'http://test.yudao.iocoder.cn/20260128/20160512095836_4PiGk.thumb.1000_0_1769588413796.jpeg', 'image/jpeg', 84595, '1', '2026-01-28 16:20:14', '1', '2026-01-28 16:20:14',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2784, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769593585805.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769593585805.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 17:46:30', '1', '2026-01-28 17:46:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2785, 22, '70f1-hiycyfx9128819.jpg', '20260128/70f1-hiycyfx9128819_1769593898265.jpg', 'http://test.yudao.iocoder.cn/20260128/70f1-hiycyfx9128819_1769593898265.jpg',
        'image/jpeg', 926210, '1', '2026-01-28 17:51:39', '1', '2026-01-28 17:51:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2786, 22, '70f1-hiycyfx9128819.jpg', '20260129/70f1-hiycyfx9128819_1769646992162.jpg', 'http://test.yudao.iocoder.cn/20260129/70f1-hiycyfx9128819_1769646992162.jpg',
        'image/jpeg', 926210, '1', '2026-01-29 08:36:35', '1', '2026-01-29 08:36:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2787, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260129/20160512095836_4PiGk.thumb.1000_0_1769648440710.jpeg',
        'http://test.yudao.iocoder.cn/20260129/20160512095836_4PiGk.thumb.1000_0_1769648440710.jpeg', 'image/jpeg', 84595, '1', '2026-01-29 09:00:43', '1', '2026-01-29 09:00:43',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2788, 22, '70f1-hiycyfx9128819.jpg', '20260129/70f1-hiycyfx9128819_1769648676996.jpg', 'http://test.yudao.iocoder.cn/20260129/70f1-hiycyfx9128819_1769648676996.jpg',
        'image/jpeg', 926210, '1', '2026-01-29 09:04:38', '1', '2026-01-29 09:04:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2789, 22, '70f1-hiycyfx9128819.jpg', '20260129/70f1-hiycyfx9128819_1769649621457.jpg', 'http://test.yudao.iocoder.cn/20260129/70f1-hiycyfx9128819_1769649621457.jpg',
        'image/jpeg', 926210, '1', '2026-01-29 09:20:26', '1', '2026-01-29 09:20:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2790, 22, '1769653451814_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769653451814_4487a19affc2c31df154ae4af624abfb_1769653456000.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769653451814_4487a19affc2c31df154ae4af624abfb_1769653456000.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 10:24:20', '1',
        '2026-01-29 10:24:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2791, 22, '1769655010402_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769655010402_4487a19affc2c31df154ae4af624abfb_1769655014196.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655010402_4487a19affc2c31df154ae4af624abfb_1769655014196.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 10:50:15', '1',
        '2026-01-29 10:50:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2792, 22, '1769655018690_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769655018690_c749170f255ee040219dd5d608b9be46_1769655022497.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655018690_c749170f255ee040219dd5d608b9be46_1769655022497.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 10:50:23', '1',
        '2026-01-29 10:50:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2793, 22, '1769655018739_9743df745840170e5ba2134158f7b212.jpg', '20260129/1769655018739_9743df745840170e5ba2134158f7b212_1769655022797.jpg',
        'http://test.yudao.iocoder.cn/20260129/1769655018739_9743df745840170e5ba2134158f7b212_1769655022797.jpg', 'image/jpeg', 29467, '1', '2026-01-29 10:50:23', '1',
        '2026-01-29 10:50:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2794, 22, '1769655028051_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769655028051_4487a19affc2c31df154ae4af624abfb_1769655031722.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655028051_4487a19affc2c31df154ae4af624abfb_1769655031722.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 10:50:32', '1',
        '2026-01-29 10:50:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2795, 22, '1769655106146_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769655106146_4487a19affc2c31df154ae4af624abfb_1769655109871.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655106146_4487a19affc2c31df154ae4af624abfb_1769655109871.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 10:51:50', '1',
        '2026-01-29 10:51:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2796, 22, '1769655106184_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769655106184_c749170f255ee040219dd5d608b9be46_1769655110358.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655106184_c749170f255ee040219dd5d608b9be46_1769655110358.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 10:51:50', '1',
        '2026-01-29 10:51:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2797, 22, '1769655156413_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769655156413_c749170f255ee040219dd5d608b9be46_1769655160052.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655156413_c749170f255ee040219dd5d608b9be46_1769655160052.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 10:52:40', '1',
        '2026-01-29 10:52:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2798, 22, '1769655182209_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769655182209_4487a19affc2c31df154ae4af624abfb_1769655185917.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769655182209_4487a19affc2c31df154ae4af624abfb_1769655185917.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 10:53:06', '1',
        '2026-01-29 10:53:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2799, 22, '1769657335520_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769657335520_4487a19affc2c31df154ae4af624abfb_1769657339068.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769657335520_4487a19affc2c31df154ae4af624abfb_1769657339068.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:28:59', '1',
        '2026-01-29 11:28:59', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2800, 22, '1769657335627_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769657335627_c749170f255ee040219dd5d608b9be46_1769657339661.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769657335627_c749170f255ee040219dd5d608b9be46_1769657339661.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 11:29:00', '1',
        '2026-01-29 11:29:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2801, 22, '1769657335648_9743df745840170e5ba2134158f7b212.jpg', '20260129/1769657335648_9743df745840170e5ba2134158f7b212_1769657339957.jpg',
        'http://test.yudao.iocoder.cn/20260129/1769657335648_9743df745840170e5ba2134158f7b212_1769657339957.jpg', 'image/jpeg', 29467, '1', '2026-01-29 11:29:00', '1',
        '2026-01-29 11:29:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2802, 22, '1769657347080_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769657347080_4487a19affc2c31df154ae4af624abfb_1769657350384.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769657347080_4487a19affc2c31df154ae4af624abfb_1769657350384.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:29:11', '1',
        '2026-01-29 11:29:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2803, 22, '1769657458408_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769657458408_4487a19affc2c31df154ae4af624abfb_1769657461839.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769657458408_4487a19affc2c31df154ae4af624abfb_1769657461839.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:31:02', '1',
        '2026-01-29 11:31:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2804, 22, '1769657458453_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769657458453_c749170f255ee040219dd5d608b9be46_1769657462332.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769657458453_c749170f255ee040219dd5d608b9be46_1769657462332.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 11:31:02', '1',
        '2026-01-29 11:31:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2805, 22, '1769657458477_9743df745840170e5ba2134158f7b212.jpg', '20260129/1769657458477_9743df745840170e5ba2134158f7b212_1769657462637.jpg',
        'http://test.yudao.iocoder.cn/20260129/1769657458477_9743df745840170e5ba2134158f7b212_1769657462637.jpg', 'image/jpeg', 29467, '1', '2026-01-29 11:31:03', '1',
        '2026-01-29 11:31:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2806, 22, '附件.docx', '20260129/附件_1769657667865.docx', 'http://test.yudao.iocoder.cn/20260129/附件_1769657667865.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-29 11:34:28', '1', '2026-01-29 11:34:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2807, 22, '1769658376245_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769658376245_4487a19affc2c31df154ae4af624abfb_1769658379540.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769658376245_4487a19affc2c31df154ae4af624abfb_1769658379540.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:46:20', '1',
        '2026-01-29 11:46:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2808, 22, '1769658440662_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769658440662_4487a19affc2c31df154ae4af624abfb_1769658443918.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769658440662_4487a19affc2c31df154ae4af624abfb_1769658443918.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:47:24', '1',
        '2026-01-29 11:47:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2809, 22, '屏幕截图 2026-01-29 112702.png', '20260129/屏幕截图 2026-01-29 112702_1769658530941.png',
        'http://test.yudao.iocoder.cn/20260129/屏幕截图 2026-01-29 112702_1769658530941.png', 'image/png', 87713, '1', '2026-01-29 11:48:51', '1', '2026-01-29 11:48:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2810, 22, '1769658740478_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769658740478_4487a19affc2c31df154ae4af624abfb_1769658743867.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769658740478_4487a19affc2c31df154ae4af624abfb_1769658743867.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 11:52:24', '1',
        '2026-01-29 11:52:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2811, 22, '1769658740533_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769658740533_c749170f255ee040219dd5d608b9be46_1769658744364.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769658740533_c749170f255ee040219dd5d608b9be46_1769658744364.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 11:52:24', '1',
        '2026-01-29 11:52:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2812, 22, '1769658740558_9743df745840170e5ba2134158f7b212.jpg', '20260129/1769658740558_9743df745840170e5ba2134158f7b212_1769658744657.jpg',
        'http://test.yudao.iocoder.cn/20260129/1769658740558_9743df745840170e5ba2134158f7b212_1769658744657.jpg', 'image/jpeg', 29467, '1', '2026-01-29 11:52:25', '1',
        '2026-01-29 11:52:25', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2813, 22, '1769659438235_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769659438235_4487a19affc2c31df154ae4af624abfb_1769659441584.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769659438235_4487a19affc2c31df154ae4af624abfb_1769659441584.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 12:04:02', '1',
        '2026-01-29 12:04:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2814, 22, '1769659438337_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769659438337_c749170f255ee040219dd5d608b9be46_1769659442074.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769659438337_c749170f255ee040219dd5d608b9be46_1769659442074.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 12:04:02', '1',
        '2026-01-29 12:04:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2815, 22, 'R-C.jpg', '20260129/R-C_1769663667753.jpg', 'http://test.yudao.iocoder.cn/20260129/R-C_1769663667753.jpg', 'image/jpeg', 62533, '1', '2026-01-29 13:14:30', '1',
        '2026-01-29 13:14:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2816, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260129/20160512095836_4PiGk.thumb.1000_0_1769667331598.jpeg',
        'http://test.yudao.iocoder.cn/20260129/20160512095836_4PiGk.thumb.1000_0_1769667331598.jpeg', 'image/jpeg', 84595, '1', '2026-01-29 14:15:32', '1', '2026-01-29 14:15:32',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2817, 22, '70f1-hiycyfx9128819.jpg', '20260129/70f1-hiycyfx9128819_1769667351176.jpg', 'http://test.yudao.iocoder.cn/20260129/70f1-hiycyfx9128819_1769667351176.jpg',
        'image/jpeg', 926210, '1', '2026-01-29 14:15:52', '1', '2026-01-29 14:15:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2818, 22, '70f1-hiycyfx9128819.jpg', '20260129/70f1-hiycyfx9128819_1769668482745.jpg', 'http://test.yudao.iocoder.cn/20260129/70f1-hiycyfx9128819_1769668482745.jpg',
        'image/jpeg', 926210, '1', '2026-01-29 14:34:44', '1', '2026-01-29 14:34:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2819, 22, '附件.docx', '20260129/附件_1769672042799.docx', 'http://test.yudao.iocoder.cn/20260129/附件_1769672042799.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-29 15:34:05', '1', '2026-01-29 15:34:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2820, 22, 'lghjft_wtfk.sql', '20260129/lghjft_wtfk_1769672079629.sql', 'http://test.yudao.iocoder.cn/20260129/lghjft_wtfk_1769672079629.sql', 'application/octet-stream', 0,
        '1', '2026-01-29 15:34:40', '1', '2026-01-29 15:34:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2821, 22, '屏幕截图 2026-01-29 103551.png', '20260129/屏幕截图 2026-01-29 103551_1769672286470.png',
        'http://test.yudao.iocoder.cn/20260129/屏幕截图 2026-01-29 103551_1769672286470.png', 'image/png', 46315, '1', '2026-01-29 15:38:07', '1', '2026-01-29 15:38:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2822, 22, '屏幕截图 2026-01-12 104240.png', '20260129/屏幕截图 2026-01-12 104240_1769674381184.png',
        'http://test.yudao.iocoder.cn/20260129/屏幕截图 2026-01-12 104240_1769674381184.png', 'image/png', 251590, '1', '2026-01-29 16:13:03', '1', '2026-01-29 16:13:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2823, 22, '1769675459279_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769675459279_4487a19affc2c31df154ae4af624abfb_1769675462796.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675459279_4487a19affc2c31df154ae4af624abfb_1769675462796.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 16:31:05', '1',
        '2026-01-29 16:31:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2824, 22, '1769675603397_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769675603397_4487a19affc2c31df154ae4af624abfb_1769675606816.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675603397_4487a19affc2c31df154ae4af624abfb_1769675606816.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 16:33:27', '1',
        '2026-01-29 16:33:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2825, 22, '1769675603447_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769675603447_c749170f255ee040219dd5d608b9be46_1769675607312.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675603447_c749170f255ee040219dd5d608b9be46_1769675607312.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 16:33:27', '1',
        '2026-01-29 16:33:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2826, 22, '1769675603473_9743df745840170e5ba2134158f7b212.jpg', '20260129/1769675603473_9743df745840170e5ba2134158f7b212_1769675607608.jpg',
        'http://test.yudao.iocoder.cn/20260129/1769675603473_9743df745840170e5ba2134158f7b212_1769675607608.jpg', 'image/jpeg', 29467, '1', '2026-01-29 16:33:28', '1',
        '2026-01-29 16:33:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2827, 22, '1769675644668_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769675644668_c749170f255ee040219dd5d608b9be46_1769675648000.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675644668_c749170f255ee040219dd5d608b9be46_1769675648000.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 16:34:08', '1',
        '2026-01-29 16:34:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2828, 22, '1769675746172_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769675746172_c749170f255ee040219dd5d608b9be46_1769675749558.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675746172_c749170f255ee040219dd5d608b9be46_1769675749558.jpeg', 'image/jpeg', 14648, '1', '2026-01-29 16:35:50', '1',
        '2026-01-29 16:35:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2829, 22, '1769675746201_4487a19affc2c31df154ae4af624abfb.jpeg', '20260129/1769675746201_4487a19affc2c31df154ae4af624abfb_1769675749940.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769675746201_4487a19affc2c31df154ae4af624abfb_1769675749940.jpeg', 'image/jpeg', 29659, '1', '2026-01-29 16:35:50', '1',
        '2026-01-29 16:35:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2830, 22, '1769676207115_c749170f255ee040219dd5d608b9be46.jpeg', '20260129/1769676207115_c749170f255ee040219dd5d608b9be46_1769676210442.jpeg',
        'http://test.yudao.iocoder.cn/20260129/1769676207115_c749170f255ee040219dd5d608b9be46_1769676210442.jpeg', 'image/jpeg', 14648, '20220907048', '2026-01-29 16:43:31',
        '20220907048', '2026-01-29 16:43:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2831, 22, '1769735409811_4487a19affc2c31df154ae4af624abfb.jpeg', '20260130/1769735409811_4487a19affc2c31df154ae4af624abfb_1769735411914.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769735409811_4487a19affc2c31df154ae4af624abfb_1769735411914.jpeg', 'image/jpeg', 29659, '20220907048', '2026-01-30 09:10:14',
        '20220907048', '2026-01-30 09:10:14', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2832, 22, '附件.docx', '20260130/附件_1769735557720.docx', 'http://test.yudao.iocoder.cn/20260130/附件_1769735557720.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '20220907048', '2026-01-30 09:12:38', '20220907048', '2026-01-30 09:12:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2833, 22, '1769740501560_c749170f255ee040219dd5d608b9be46.jpeg', '20260130/1769740501560_c749170f255ee040219dd5d608b9be46_1769740504593.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769740501560_c749170f255ee040219dd5d608b9be46_1769740504593.jpeg', 'image/jpeg', 14648, '20220907048', '2026-01-30 10:35:05',
        '20220907048', '2026-01-30 10:35:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2834, 22, '1769740501703_9743df745840170e5ba2134158f7b212.jpg', '20260130/1769740501703_9743df745840170e5ba2134158f7b212_1769740505274.jpg',
        'http://test.yudao.iocoder.cn/20260130/1769740501703_9743df745840170e5ba2134158f7b212_1769740505274.jpg', 'image/jpeg', 29467, '20220907048', '2026-01-30 10:35:05',
        '20220907048', '2026-01-30 10:35:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2835, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769743495187.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769743495187.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 11:25:03', '1', '2026-01-30 11:25:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2836, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769745104057.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769745104057.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 11:51:45', '1', '2026-01-30 11:51:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2837, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769749499089.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769749499089.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 13:05:00', '1', '2026-01-30 13:05:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2838, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769749504462.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769749504462.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 13:05:05', '1', '2026-01-30 13:05:05',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2839, 22, 'R-C.jpg', '20260130/R-C_1769749507660.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769749507660.jpg', 'image/jpeg', 62533, '1', '2026-01-30 13:05:08', '1',
        '2026-01-30 13:05:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2840, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769749994523.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769749994523.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 13:13:15', '1', '2026-01-30 13:13:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2841, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769749996761.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769749996761.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 13:13:17', '1', '2026-01-30 13:13:17',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2842, 22, 'R-C.jpg', '20260130/R-C_1769749999575.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769749999575.jpg', 'image/jpeg', 62533, '1', '2026-01-30 13:13:20', '1',
        '2026-01-30 13:13:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2843, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769751081063.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769751081063.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 13:31:23', '1', '2026-01-30 13:31:23',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2844, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769751078217.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769751078217.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 13:31:23', '1', '2026-01-30 13:31:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2845, 22, 'R-C.jpg', '20260130/R-C_1769751085573.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769751085573.jpg', 'image/jpeg', 62533, '1', '2026-01-30 13:31:26', '1',
        '2026-01-30 13:31:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2846, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769752493643.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769752493643.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 13:54:56', '1', '2026-01-30 13:54:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2847, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769756403992.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769756403992.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 15:00:07', '1', '2026-01-30 15:00:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2848, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769756552782.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769756552782.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 15:02:34', '1', '2026-01-30 15:02:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2849, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769757831431.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769757831431.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 15:23:54', '1', '2026-01-30 15:23:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2850, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769757833649.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769757833649.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 15:23:54', '1', '2026-01-30 15:23:54',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2851, 22, 'R-C.jpg', '20260130/R-C_1769757841465.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769757841465.jpg', 'image/jpeg', 62533, '1', '2026-01-30 15:24:02', '1',
        '2026-01-30 15:24:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2852, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769758898263.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769758898263.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 15:41:41', '1', '2026-01-30 15:41:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2853, 22, 'R-C.jpg', '20260130/R-C_1769758917071.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769758917071.jpg', 'image/jpeg', 62533, '1', '2026-01-30 15:41:57', '1',
        '2026-01-30 15:41:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2854, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769758923095.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769758923095.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 15:42:03', '1', '2026-01-30 15:42:03',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2855, 22, '1769760647520_mmexport1754714876136.jpg', '20260130/1769760647520_mmexport1754714876136_1769760648561.jpg',
        'http://test.yudao.iocoder.cn/20260130/1769760647520_mmexport1754714876136_1769760648561.jpg', 'image/jpeg', 60495, '20220907048', '2026-01-30 16:10:51', '20220907048',
        '2026-01-30 16:10:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2856, 22, '附件.docx', '20260130/附件_1769760775744.docx', 'http://test.yudao.iocoder.cn/20260130/附件_1769760775744.docx',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 0, '1', '2026-01-30 16:12:56', '1', '2026-01-30 16:12:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2857, 22, '1769760834612_1751793683652.jpg', '20260130/1769760834612_1751793683652_1769760835756.jpg',
        'http://test.yudao.iocoder.cn/20260130/1769760834612_1751793683652_1769760835756.jpg', 'image/jpeg', 67156, '20220907048', '2026-01-30 16:13:56', '20220907048',
        '2026-01-30 16:13:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2858, 22, '1769760834730_1761184848475.jpeg', '20260130/1769760834730_1761184848475_1769760836231.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769760834730_1761184848475_1769760836231.jpeg', 'image/jpeg', 76886, '20220907048', '2026-01-30 16:13:56', '20220907048',
        '2026-01-30 16:13:56', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2859, 22, '1769762717915_4487a19affc2c31df154ae4af624abfb.jpeg', '20260130/1769762717915_4487a19affc2c31df154ae4af624abfb_1769762721003.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769762717915_4487a19affc2c31df154ae4af624abfb_1769762721003.jpeg', 'image/jpeg', 29659, '20220907048', '2026-01-30 16:45:23',
        '20220907048', '2026-01-30 16:45:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2860, 22, '1769762747042_9743df745840170e5ba2134158f7b212.jpg', '20260130/1769762747042_9743df745840170e5ba2134158f7b212_1769762749858.jpg',
        'http://test.yudao.iocoder.cn/20260130/1769762747042_9743df745840170e5ba2134158f7b212_1769762749858.jpg', 'image/jpeg', 29467, '20220907048', '2026-01-30 16:45:50',
        '20220907048', '2026-01-30 16:45:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2861, 22, '1769762799794_c749170f255ee040219dd5d608b9be46.jpeg', '20260130/1769762799794_c749170f255ee040219dd5d608b9be46_1769762802546.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769762799794_c749170f255ee040219dd5d608b9be46_1769762802546.jpeg', 'image/jpeg', 14648, '20220907048', '2026-01-30 16:46:43',
        '20220907048', '2026-01-30 16:46:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2862, 22, '1769762822209_4487a19affc2c31df154ae4af624abfb.jpeg', '20260130/1769762822209_4487a19affc2c31df154ae4af624abfb_1769762824968.jpeg',
        'http://test.yudao.iocoder.cn/20260130/1769762822209_4487a19affc2c31df154ae4af624abfb_1769762824968.jpeg', 'image/jpeg', 29659, '20220907048', '2026-01-30 16:47:05',
        '20220907048', '2026-01-30 16:47:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2863, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769763017090.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769763017090.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 16:50:20', '1', '2026-01-30 16:50:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2864, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769763494417.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769763494417.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 16:58:15', '1', '2026-01-30 16:58:15',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2865, 22, '70f1-hiycyfx9128819.jpg', '20260130/70f1-hiycyfx9128819_1769763498405.jpg', 'http://test.yudao.iocoder.cn/20260130/70f1-hiycyfx9128819_1769763498405.jpg',
        'image/jpeg', 926210, '1', '2026-01-30 16:58:19', '1', '2026-01-30 16:58:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2866, 22, 'R-C.jpg', '20260130/R-C_1769763502910.jpg', 'http://test.yudao.iocoder.cn/20260130/R-C_1769763502910.jpg', 'image/jpeg', 62533, '1', '2026-01-30 16:58:23', '1',
        '2026-01-30 16:58:23', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2867, 22, '经费收缴方面.doc', '20260130/经费收缴方面_1769763682765.doc', 'http://test.yudao.iocoder.cn/20260130/经费收缴方面_1769763682765.doc', 'application/msword',
        36403, '1', '2026-01-30 17:01:24', '1', '2026-01-30 17:01:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2868, 22, '单元素养练习.pdf', '20260130/单元素养练习_1769763696728.pdf', 'http://test.yudao.iocoder.cn/20260130/单元素养练习_1769763696728.pdf', 'application/pdf', 4257805,
        '1', '2026-01-30 17:01:39', '1', '2026-01-30 17:01:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2869, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260130/20160512095836_4PiGk.thumb.1000_0_1769763707453.jpeg',
        'http://test.yudao.iocoder.cn/20260130/20160512095836_4PiGk.thumb.1000_0_1769763707453.jpeg', 'image/jpeg', 84595, '1', '2026-01-30 17:01:48', '1', '2026-01-30 17:01:48',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2870, 22, '1770001724685_4487a19affc2c31df154ae4af624abfb.jpeg', '20260202/1770001724685_4487a19affc2c31df154ae4af624abfb_1770001726747.jpeg',
        'http://test.yudao.iocoder.cn/20260202/1770001724685_4487a19affc2c31df154ae4af624abfb_1770001726747.jpeg', 'image/jpeg', 29659, '1', '2026-02-02 11:08:50', '1',
        '2026-02-02 11:08:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2871, 22, '工会经费通-问题反馈.xls', '20260202/工会经费通-问题反馈_1770002046295.xls', 'http://test.yudao.iocoder.cn/20260202/工会经费通-问题反馈_1770002046295.xls',
        'application/vnd.ms-excel', 7305, '1', '2026-02-02 11:14:07', '1', '2026-02-02 11:14:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2872, 22, 'R-C.jpg', '20260202/R-C_1770002304185.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770002304185.jpg', 'image/jpeg', 62533, '1', '2026-02-02 11:18:26', '1',
        '2026-02-02 11:18:26', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2873, 22, 'R-C.jpg', '20260202/R-C_1770002921725.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770002921725.jpg', 'image/jpeg', 62533, '1', '2026-02-02 11:28:42', '1',
        '2026-02-02 11:28:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2874, 22, 'R-C.jpg', '20260202/R-C_1770013348999.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770013348999.jpg', 'image/jpeg', 62533, '1', '2026-02-02 14:22:31', '1',
        '2026-02-02 14:22:31', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2875, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770013704483.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770013704483.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 14:28:26', '1', '2026-02-02 14:28:26',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2876, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770013991904.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770013991904.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 14:33:12', '1', '2026-02-02 14:33:12',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2877, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770013988624.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770013988624.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 14:33:13', '1', '2026-02-02 14:33:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2878, 22, 'R-C.jpg', '20260202/R-C_1770014083928.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770014083928.jpg', 'image/jpeg', 62533, '1', '2026-02-02 14:34:44', '1',
        '2026-02-02 14:34:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2879, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770014091539.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770014091539.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 14:34:52', '1', '2026-02-02 14:34:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2880, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770015585478.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770015585478.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 14:59:48', '1', '2026-02-02 14:59:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2881, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770015591088.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770015591088.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 14:59:51', '1', '2026-02-02 14:59:51',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2882, 22, 'R-C.jpg', '20260202/R-C_1770015594570.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770015594570.jpg', 'image/jpeg', 62533, '1', '2026-02-02 14:59:55', '1',
        '2026-02-02 14:59:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2883, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770018315839.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770018315839.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 15:45:18', '1', '2026-02-02 15:45:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2884, 22, 'R-C.jpg', '20260202/R-C_1770018323356.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770018323356.jpg', 'image/jpeg', 62533, '1', '2026-02-02 15:45:24', '1',
        '2026-02-02 15:45:24', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2885, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770018326599.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770018326599.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 15:45:27', '1', '2026-02-02 15:45:27',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2886, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770019002790.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770019002790.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 15:56:45', '1', '2026-02-02 15:56:45',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2887, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770019010081.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770019010081.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 15:56:50', '1', '2026-02-02 15:56:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2888, 22, 'R-C.jpg', '20260202/R-C_1770019013869.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770019013869.jpg', 'image/jpeg', 62533, '1', '2026-02-02 15:56:54', '1',
        '2026-02-02 15:56:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2889, 22, 'R-C.jpg', '20260202/R-C_1770019299144.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770019299144.jpg', 'image/jpeg', 62533, '1', '2026-02-02 16:01:41', '1',
        '2026-02-02 16:01:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2890, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260202/20160512095836_4PiGk.thumb.1000_0_1770019303588.jpeg',
        'http://test.yudao.iocoder.cn/20260202/20160512095836_4PiGk.thumb.1000_0_1770019303588.jpeg', 'image/jpeg', 84595, '1', '2026-02-02 16:01:44', '1', '2026-02-02 16:01:44',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2891, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770019306683.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770019306683.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 16:01:47', '1', '2026-02-02 16:01:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2892, 22, 'R-C.jpg', '20260202/R-C_1770019551032.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770019551032.jpg', 'image/jpeg', 62533, '1', '2026-02-02 16:05:53', '1',
        '2026-02-02 16:05:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2893, 22, '70f1-hiycyfx9128819.jpg', '20260202/70f1-hiycyfx9128819_1770019560557.jpg', 'http://test.yudao.iocoder.cn/20260202/70f1-hiycyfx9128819_1770019560557.jpg',
        'image/jpeg', 926210, '1', '2026-02-02 16:06:03', '1', '2026-02-02 16:06:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2894, 22, 'R-C.jpg', '20260202/R-C_1770019808946.jpg', 'http://test.yudao.iocoder.cn/20260202/R-C_1770019808946.jpg', 'image/jpeg', 62533, '1', '2026-02-02 16:10:11', '1',
        '2026-02-02 16:10:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2895, 22, 'R-C.jpg', '20260203/R-C_1770079657867.jpg', 'http://test.yudao.iocoder.cn/20260203/R-C_1770079657867.jpg', 'image/jpeg', 62533, '1', '2026-02-03 08:47:41', '1',
        '2026-02-03 08:47:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2896, 22, '1770100032006_4487a19affc2c31df154ae4af624abfb.jpeg', '20260203/1770100032006_4487a19affc2c31df154ae4af624abfb_1770100035369.jpeg',
        'http://test.yudao.iocoder.cn/20260203/1770100032006_4487a19affc2c31df154ae4af624abfb_1770100035369.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-03 14:27:18',
        '20220907048', '2026-02-03 14:27:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2897, 22, 'R-C.jpg', '20260203/R-C_1770105071276.jpg', 'http://test.yudao.iocoder.cn/20260203/R-C_1770105071276.jpg', 'image/jpeg', 62533, '1', '2026-02-03 15:51:13', '1',
        '2026-02-03 15:51:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2898, 22, '70f1-hiycyfx9128819.jpg', '20260203/70f1-hiycyfx9128819_1770105078688.jpg', 'http://test.yudao.iocoder.cn/20260203/70f1-hiycyfx9128819_1770105078688.jpg',
        'image/jpeg', 926210, '1', '2026-02-03 15:51:40', '1', '2026-02-03 15:51:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2899, 22, 'R-C.jpg', '20260204/R-C_1770172013353.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770172013353.jpg', 'image/jpeg', 62533, '1', '2026-02-04 10:26:55', '1',
        '2026-02-04 10:26:55', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2900, 22, '1770173465237_c749170f255ee040219dd5d608b9be46.jpeg', '20260204/1770173465237_c749170f255ee040219dd5d608b9be46_1770173467367.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770173465237_c749170f255ee040219dd5d608b9be46_1770173467367.jpeg', 'image/jpeg', 14648, '1', '2026-02-04 10:51:10', '1',
        '2026-02-04 10:51:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2901, 22, '1770173465290_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770173465290_4487a19affc2c31df154ae4af624abfb_1770173470596.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770173465290_4487a19affc2c31df154ae4af624abfb_1770173470596.jpeg', 'image/jpeg', 29659, '1', '2026-02-04 10:51:11', '1',
        '2026-02-04 10:51:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2902, 22, '1770173465332_9743df745840170e5ba2134158f7b212.jpg', '20260204/1770173465332_9743df745840170e5ba2134158f7b212_1770173471076.jpg',
        'http://test.yudao.iocoder.cn/20260204/1770173465332_9743df745840170e5ba2134158f7b212_1770173471076.jpg', 'image/jpeg', 29467, '1', '2026-02-04 10:51:11', '1',
        '2026-02-04 10:51:11', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2903, 22, '1770174109787_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770174109787_4487a19affc2c31df154ae4af624abfb_1770174111702.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770174109787_4487a19affc2c31df154ae4af624abfb_1770174111702.jpeg', 'image/jpeg', 29659, '1', '2026-02-04 11:01:52', '1',
        '2026-02-04 11:01:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2904, 22, '微信截图_20250710164649.png', '20260204/微信截图_20250710164649_1770174435365.png',
        'http://test.yudao.iocoder.cn/20260204/微信截图_20250710164649_1770174435365.png', 'image/png', 391591, '1', '2026-02-04 11:07:21', '1', '2026-02-04 11:07:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2905, 22, 'R-C.jpg', '20260204/R-C_1770187155052.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770187155052.jpg', 'image/jpeg', 62533, '1', '2026-02-04 14:39:18', '1',
        '2026-02-04 14:39:18', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2906, 22, '70f1-hiycyfx9128819.jpg', '20260204/70f1-hiycyfx9128819_1770187158328.jpg', 'http://test.yudao.iocoder.cn/20260204/70f1-hiycyfx9128819_1770187158328.jpg',
        'image/jpeg', 926210, '1', '2026-02-04 14:39:20', '1', '2026-02-04 14:39:20', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2907, 22, '70f1-hiycyfx9128819.jpg', '20260204/70f1-hiycyfx9128819_1770187933555.jpg', 'http://test.yudao.iocoder.cn/20260204/70f1-hiycyfx9128819_1770187933555.jpg',
        'image/jpeg', 926210, '1', '2026-02-04 14:52:15', '1', '2026-02-04 14:52:15', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2908, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260204/20160512095836_4PiGk.thumb.1000_0_1770187938400.jpeg',
        'http://test.yudao.iocoder.cn/20260204/20160512095836_4PiGk.thumb.1000_0_1770187938400.jpeg', 'image/jpeg', 84595, '1', '2026-02-04 14:52:19', '1', '2026-02-04 14:52:19',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2909, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260204/20160512095836_4PiGk.thumb.1000_0_1770187942457.jpeg',
        'http://test.yudao.iocoder.cn/20260204/20160512095836_4PiGk.thumb.1000_0_1770187942457.jpeg', 'image/jpeg', 84595, '1', '2026-02-04 14:52:23', '1', '2026-02-04 14:52:23',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2910, 22, 'R-C.jpg', '20260204/R-C_1770188128147.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770188128147.jpg', 'image/jpeg', 62533, '1', '2026-02-04 14:55:29', '1',
        '2026-02-04 14:55:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2911, 22, '1770188167412_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770188167412_4487a19affc2c31df154ae4af624abfb_1770188169459.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770188167412_4487a19affc2c31df154ae4af624abfb_1770188169459.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-04 14:56:12',
        '20220907048', '2026-02-04 14:56:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2912, 22, '1770188167518_c749170f255ee040219dd5d608b9be46.jpeg', '20260204/1770188167518_c749170f255ee040219dd5d608b9be46_1770188172646.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770188167518_c749170f255ee040219dd5d608b9be46_1770188172646.jpeg', 'image/jpeg', 14648, '20220907048', '2026-02-04 14:56:13',
        '20220907048', '2026-02-04 14:56:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2913, 22, '屏幕截图 2026-01-12 100206.png', '20260204/屏幕截图 2026-01-12 100206_1770188409228.png',
        'http://test.yudao.iocoder.cn/20260204/屏幕截图 2026-01-12 100206_1770188409228.png', 'image/png', 13141, '20220907048', '2026-02-04 15:00:10', '20220907048',
        '2026-02-04 15:00:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2914, 22, '屏幕截图 2026-01-12 100432.png', '20260204/屏幕截图 2026-01-12 100432_1770188438083.png',
        'http://test.yudao.iocoder.cn/20260204/屏幕截图 2026-01-12 100432_1770188438083.png', 'image/png', 108703, '20220907048', '2026-02-04 15:00:38', '20220907048',
        '2026-02-04 15:00:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2915, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260204/20160512095836_4PiGk.thumb.1000_0_1770189303050.jpeg',
        'http://test.yudao.iocoder.cn/20260204/20160512095836_4PiGk.thumb.1000_0_1770189303050.jpeg', 'image/jpeg', 84595, '1', '2026-02-04 15:15:04', '1', '2026-02-04 15:15:04',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2916, 22, 'R-C.jpg', '20260204/R-C_1770189306453.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770189306453.jpg', 'image/jpeg', 62533, '1', '2026-02-04 15:15:07', '1',
        '2026-02-04 15:15:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2917, 22, '70f1-hiycyfx9128819.jpg', '20260204/70f1-hiycyfx9128819_1770189310141.jpg', 'http://test.yudao.iocoder.cn/20260204/70f1-hiycyfx9128819_1770189310141.jpg',
        'image/jpeg', 926210, '1', '2026-02-04 15:15:12', '1', '2026-02-04 15:15:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2918, 22, 'R-C.jpg', '20260204/R-C_1770189628650.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770189628650.jpg', 'image/jpeg', 62533, '1', '2026-02-04 15:20:29', '1',
        '2026-02-04 15:20:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2919, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260204/20160512095836_4PiGk.thumb.1000_0_1770189632170.jpeg',
        'http://test.yudao.iocoder.cn/20260204/20160512095836_4PiGk.thumb.1000_0_1770189632170.jpeg', 'image/jpeg', 84595, '1', '2026-02-04 15:20:32', '1', '2026-02-04 15:20:32',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2920, 22, '70f1-hiycyfx9128819.jpg', '20260204/70f1-hiycyfx9128819_1770189636318.jpg', 'http://test.yudao.iocoder.cn/20260204/70f1-hiycyfx9128819_1770189636318.jpg',
        'image/jpeg', 926210, '1', '2026-02-04 15:20:37', '1', '2026-02-04 15:20:37', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2921, 22, 'R-C.jpg', '20260204/R-C_1770190168194.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770190168194.jpg', 'image/jpeg', 62533, '1', '2026-02-04 15:29:29', '1',
        '2026-02-04 15:29:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2922, 22, '1770190179312_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770190179312_4487a19affc2c31df154ae4af624abfb_1770190181193.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770190179312_4487a19affc2c31df154ae4af624abfb_1770190181193.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-04 15:29:42',
        '20220907048', '2026-02-04 15:29:42', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2923, 22, '1770190609431_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770190609431_4487a19affc2c31df154ae4af624abfb_1770190611401.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770190609431_4487a19affc2c31df154ae4af624abfb_1770190611401.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-04 15:36:52',
        '20220907048', '2026-02-04 15:36:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2924, 22, '1770192989339_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770192989339_4487a19affc2c31df154ae4af624abfb_1770192991228.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770192989339_4487a19affc2c31df154ae4af624abfb_1770192991228.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-04 16:16:32',
        '20220907048', '2026-02-04 16:16:32', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2925, 22, '1770193463275_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770193463275_4487a19affc2c31df154ae4af624abfb_1770193465330.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770193463275_4487a19affc2c31df154ae4af624abfb_1770193465330.jpeg', 'image/jpeg', 29659, '20220907048', '2026-02-04 16:24:27',
        '20220907048', '2026-02-04 16:24:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2926, 22, '1770193797345_c749170f255ee040219dd5d608b9be46.jpeg', '20260204/1770193797345_c749170f255ee040219dd5d608b9be46_1770193799229.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770193797345_c749170f255ee040219dd5d608b9be46_1770193799229.jpeg', 'image/jpeg', 14648, '20220907048', '2026-02-04 16:30:00',
        '20220907048', '2026-02-04 16:30:00', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2927, 22, '1770195405268_9743df745840170e5ba2134158f7b212.jpg', '20260204/1770195405268_9743df745840170e5ba2134158f7b212_1770195407168.jpg',
        'http://test.yudao.iocoder.cn/20260204/1770195405268_9743df745840170e5ba2134158f7b212_1770195407168.jpg', 'image/jpeg', 29467, null, '2026-02-04 16:56:48', null,
        '2026-02-04 16:56:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2928, 22, '1770195447748_9743df745840170e5ba2134158f7b212.jpg', '20260204/1770195447748_9743df745840170e5ba2134158f7b212_1770195449627.jpg',
        'http://test.yudao.iocoder.cn/20260204/1770195447748_9743df745840170e5ba2134158f7b212_1770195449627.jpg', 'image/jpeg', 29467, '20220907048', '2026-02-04 16:57:30',
        '20220907048', '2026-02-04 16:57:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2929, 22, 'R-C.jpg', '20260204/R-C_1770198970452.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770198970452.jpg', 'image/jpeg', 62533, '1', '2026-02-04 17:56:13', '1',
        '2026-02-04 17:56:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2930, 22, 'R-C.jpg', '20260204/R-C_1770199048281.jpg', 'http://test.yudao.iocoder.cn/20260204/R-C_1770199048281.jpg', 'image/jpeg', 62533, '1', '2026-02-04 17:57:29', '1',
        '2026-02-04 17:57:29', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2931, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260204/20160512095836_4PiGk.thumb.1000_0_1770199051206.jpeg',
        'http://test.yudao.iocoder.cn/20260204/20160512095836_4PiGk.thumb.1000_0_1770199051206.jpeg', 'image/jpeg', 84595, '1', '2026-02-04 17:57:31', '1', '2026-02-04 17:57:31',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2932, 22, '70f1-hiycyfx9128819.jpg', '20260204/70f1-hiycyfx9128819_1770199053984.jpg', 'http://test.yudao.iocoder.cn/20260204/70f1-hiycyfx9128819_1770199053984.jpg',
        'image/jpeg', 926210, '1', '2026-02-04 17:57:38', '1', '2026-02-04 17:57:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2933, 22, '1770199175869_4487a19affc2c31df154ae4af624abfb.jpeg', '20260204/1770199175869_4487a19affc2c31df154ae4af624abfb_1770199178018.jpeg',
        'http://test.yudao.iocoder.cn/20260204/1770199175869_4487a19affc2c31df154ae4af624abfb_1770199178018.jpeg', 'image/jpeg', 29659, '1', '2026-02-04 17:59:38', '1',
        '2026-02-04 17:59:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2934, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770259803293.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770259803293.png', 'image/png', 13141, null, '2026-02-05 10:50:05', null, '2026-02-05 10:50:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2935, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770259803293.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770259803293.png', 'image/png', 108703, null, '2026-02-05 10:50:06', null, '2026-02-05 10:50:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2936, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770259803293.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770259803293.png', 'image/png', 202289, null, '2026-02-05 10:50:06', null, '2026-02-05 10:50:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2937, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770259887714.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770259887714.png', 'image/png', 13141, null, '2026-02-05 10:51:28', null, '2026-02-05 10:51:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2938, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770259887714.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770259887714.png', 'image/png', 108703, null, '2026-02-05 10:51:28', null, '2026-02-05 10:51:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2939, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770259887719.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770259887719.png', 'image/png', 202289, null, '2026-02-05 10:51:28', null, '2026-02-05 10:51:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2940, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770260018739.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770260018739.png', 'image/png', 96391, null, '2026-02-05 10:53:39', null, '2026-02-05 10:53:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2941, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770260018739.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770260018739.png', 'image/png', 108703, null, '2026-02-05 10:53:39', null, '2026-02-05 10:53:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2942, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770260018748.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770260018748.png', 'image/png', 251590, null, '2026-02-05 10:53:39', null, '2026-02-05 10:53:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2943, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770260433976.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770260433976.png', 'image/png', 13141, null, '2026-02-05 11:00:34', null, '2026-02-05 11:00:34', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2944, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770260433976.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770260433976.png', 'image/png', 108703, null, '2026-02-05 11:00:35', null, '2026-02-05 11:00:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2945, 22, '屏幕截图 2026-01-12 112707.png', '20260205/屏幕截图 2026-01-12 112707_1770260433984.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 112707_1770260433984.png', 'image/png', 266130, null, '2026-02-05 11:00:35', null, '2026-02-05 11:00:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2946, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770260947672.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770260947672.png', 'image/png', 13141, null, '2026-02-05 11:09:08', null, '2026-02-05 11:09:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2947, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770260947672.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770260947672.png', 'image/png', 108703, null, '2026-02-05 11:09:08', null, '2026-02-05 11:09:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2948, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770260947672.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770260947672.png', 'image/png', 202289, null, '2026-02-05 11:09:08', null, '2026-02-05 11:09:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2949, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770261140858.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770261140858.png', 'image/png', 13141, null, '2026-02-05 11:12:21', null, '2026-02-05 11:12:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2950, 22, '屏幕截图 2026-01-13 085429.png', '20260205/屏幕截图 2026-01-13 085429_1770261140863.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-13 085429_1770261140863.png', 'image/png', 18575, null, '2026-02-05 11:12:21', null, '2026-02-05 11:12:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2951, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770261140863.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770261140863.png', 'image/png', 202289, null, '2026-02-05 11:12:21', null, '2026-02-05 11:12:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2952, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770261180662.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770261180662.png', 'image/png', 13141, null, '2026-02-05 11:13:01', null, '2026-02-05 11:13:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2953, 22, '屏幕截图 2026-01-13 085429.png', '20260205/屏幕截图 2026-01-13 085429_1770261180662.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-13 085429_1770261180662.png', 'image/png', 18575, null, '2026-02-05 11:13:01', null, '2026-02-05 11:13:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2954, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770261180666.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770261180666.png', 'image/png', 202289, null, '2026-02-05 11:13:01', null, '2026-02-05 11:13:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2955, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770261369667.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770261369667.png', 'image/png', 13141, null, '2026-02-05 11:16:12', null, '2026-02-05 11:16:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2956, 22, '屏幕截图 2026-01-13 085429.png', '20260205/屏幕截图 2026-01-13 085429_1770261369667.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-13 085429_1770261369667.png', 'image/png', 18575, null, '2026-02-05 11:16:12', null, '2026-02-05 11:16:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2957, 22, '屏幕截图 2026-01-12 103500.png', '20260205/屏幕截图 2026-01-12 103500_1770261369667.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 103500_1770261369667.png', 'image/png', 202289, null, '2026-02-05 11:16:12', null, '2026-02-05 11:16:12', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2958, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770261560349.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770261560349.png', 'image/png', 13141, null, '2026-02-05 11:19:21', null, '2026-02-05 11:19:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2959, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770261560349.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770261560349.png', 'image/png', 13141, null, '2026-02-05 11:19:21', null, '2026-02-05 11:19:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2960, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770261560350.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770261560350.png', 'image/png', 108703, null, '2026-02-05 11:19:21', null, '2026-02-05 11:19:21', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2961, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770262858889.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770262858889.png', 'image/png', 13141, null, '2026-02-05 11:41:01', null, '2026-02-05 11:41:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2962, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770262858889.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770262858889.png', 'image/png', 13141, null, '2026-02-05 11:41:01', null, '2026-02-05 11:41:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2963, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770262858889.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770262858889.png', 'image/png', 108703, null, '2026-02-05 11:41:01', null, '2026-02-05 11:41:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2964, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770262926017.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770262926017.png', 'image/png', 13141, null, '2026-02-05 11:42:06', null, '2026-02-05 11:42:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2965, 22, '屏幕截图 2026-01-12 100432.png', '20260205/屏幕截图 2026-01-12 100432_1770262926002.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100432_1770262926002.png', 'image/png', 108703, null, '2026-02-05 11:42:06', null, '2026-02-05 11:42:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2966, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770262926005.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770262926005.png', 'image/png', 251590, null, '2026-02-05 11:42:07', null, '2026-02-05 11:42:07', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2967, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770263103867.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770263103867.png', 'image/png', 96391, null, '2026-02-05 11:45:04', null, '2026-02-05 11:45:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2968, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770263103867.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770263103867.png', 'image/png', 13141, null, '2026-02-05 11:45:04', null, '2026-02-05 11:45:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2969, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770263103873.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770263103873.png', 'image/png', 251590, null, '2026-02-05 11:45:04', null, '2026-02-05 11:45:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2970, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770263468906.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770263468906.png', 'image/png', 96391, null, '2026-02-05 11:51:09', null, '2026-02-05 11:51:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2971, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770263468906.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770263468906.png', 'image/png', 13141, null, '2026-02-05 11:51:10', null, '2026-02-05 11:51:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2972, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770263468908.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770263468908.png', 'image/png', 251590, null, '2026-02-05 11:51:10', null, '2026-02-05 11:51:10', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2973, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770263582514.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770263582514.png', 'image/png', 13141, null, '2026-02-05 11:53:03', null, '2026-02-05 11:53:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2974, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770263582514.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770263582514.png', 'image/png', 96391, null, '2026-02-05 11:53:03', null, '2026-02-05 11:53:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2975, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770263582518.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770263582518.png', 'image/png', 251590, null, '2026-02-05 11:53:03', null, '2026-02-05 11:53:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2976, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770263667272.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770263667272.png', 'image/png', 13141, null, '2026-02-05 11:54:28', null, '2026-02-05 11:54:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2977, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770263667272.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770263667272.png', 'image/png', 96391, null, '2026-02-05 11:54:28', null, '2026-02-05 11:54:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2978, 22, '屏幕截图 2026-01-12 104240.png', '20260205/屏幕截图 2026-01-12 104240_1770263667280.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 104240_1770263667280.png', 'image/png', 251590, null, '2026-02-05 11:54:28', null, '2026-02-05 11:54:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2979, 22, '屏幕截图 2026-01-12 100206.png', '20260205/屏幕截图 2026-01-12 100206_1770263763218.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100206_1770263763218.png', 'image/png', 13141, null, '2026-02-05 11:56:05', null, '2026-02-05 11:56:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2980, 22, '屏幕截图 2026-01-12 100800.png', '20260205/屏幕截图 2026-01-12 100800_1770263763219.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 100800_1770263763219.png', 'image/png', 96391, null, '2026-02-05 11:56:05', null, '2026-02-05 11:56:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2981, 22, '屏幕截图 2026-01-12 112707.png', '20260205/屏幕截图 2026-01-12 112707_1770263763228.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 112707_1770263763228.png', 'image/png', 266130, null, '2026-02-05 11:56:08', null, '2026-02-05 11:56:08', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2982, 22, '《税务部门代收工会经费（筹备金）实施办法》0726工会(1).doc', '20260205/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)_1770277418355.doc',
        'http://test.yudao.iocoder.cn/20260205/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)_1770277418355.doc', 'application/msword', 162232, '1', '2026-02-05 15:43:41', '1',
        '2026-02-05 15:43:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2983, 22, '1770278563851_c749170f255ee040219dd5d608b9be46.jpeg', '20260205/1770278563851_c749170f255ee040219dd5d608b9be46_1770278565911.jpeg',
        'http://test.yudao.iocoder.cn/20260205/1770278563851_c749170f255ee040219dd5d608b9be46_1770278565911.jpeg', 'image/jpeg', 14648, '1', '2026-02-05 16:02:46', '1',
        '2026-02-05 16:02:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2984, 22, '屏幕截图 2026-01-12 150040.png', '20260205/屏幕截图 2026-01-12 150040_1770278645471.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-01-12 150040_1770278645471.png', 'image/png', 133098, '1', '2026-02-05 16:04:06', '1', '2026-02-05 16:04:06', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2985, 22, '1770278760266_c749170f255ee040219dd5d608b9be46.jpeg', '20260205/1770278760266_c749170f255ee040219dd5d608b9be46_1770278762103.jpeg',
        'http://test.yudao.iocoder.cn/20260205/1770278760266_c749170f255ee040219dd5d608b9be46_1770278762103.jpeg', 'image/jpeg', 14648, '1', '2026-02-05 16:06:02', '1',
        '2026-02-05 16:06:02', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2986, 22, '屏幕截图 2026-02-05 160527.png', '20260205/屏幕截图 2026-02-05 160527_1770278924325.png',
        'http://test.yudao.iocoder.cn/20260205/屏幕截图 2026-02-05 160527_1770278924325.png', 'image/png', 124870, '1', '2026-02-05 16:08:45', '1', '2026-02-05 16:08:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2987, 22, 'R-C.jpg', '20260206/R-C_1770349358296.jpg', 'http://test.yudao.iocoder.cn/20260206/R-C_1770349358296.jpg', 'image/jpeg', 62533, '1', '2026-02-06 11:42:41', '1',
        '2026-02-06 11:42:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2988, 22, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260206/20160512095836_4PiGk.thumb.1000_0_1770349364787.jpeg',
        'http://test.yudao.iocoder.cn/20260206/20160512095836_4PiGk.thumb.1000_0_1770349364787.jpeg', 'image/jpeg', 84595, '1', '2026-02-06 11:42:45', '1', '2026-02-06 11:42:45',
        false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2989, 22, '70f1-hiycyfx9128819.jpg', '20260206/70f1-hiycyfx9128819_1770349369229.jpg', 'http://test.yudao.iocoder.cn/20260206/70f1-hiycyfx9128819_1770349369229.jpg',
        'image/jpeg', 926210, '1', '2026-02-06 11:42:50', '1', '2026-02-06 11:42:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2990, 22, 'R-C.jpg', '20260206/R-C_1770362563233.jpg', 'http://test.yudao.iocoder.cn/20260206/R-C_1770362563233.jpg', 'image/jpeg', 62533, '1', '2026-02-06 15:22:45', '1',
        '2026-02-06 15:22:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2991, 22, '经费收缴方面.doc', '20260206/经费收缴方面_1770362583399.doc', 'http://test.yudao.iocoder.cn/20260206/经费收缴方面_1770362583399.doc', 'application/msword',
        36403, '1', '2026-02-06 15:23:04', '1', '2026-02-06 15:23:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2992, 22, '单元素养练习.pdf', '20260206/单元素养练习_1770362587128.pdf', 'http://test.yudao.iocoder.cn/20260206/单元素养练习_1770362587128.pdf', 'application/pdf', 4257805,
        '1', '2026-02-06 15:23:09', '1', '2026-02-06 15:23:09', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2993, 22, '1770367066531_c749170f255ee040219dd5d608b9be46.jpeg', '20260206/1770367066531_c749170f255ee040219dd5d608b9be46_1770367070193.jpeg',
        'http://test.yudao.iocoder.cn/20260206/1770367066531_c749170f255ee040219dd5d608b9be46_1770367070193.jpeg', 'image/jpeg', 14648, '1', '2026-02-06 16:37:53', '1',
        '2026-02-06 16:37:53', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2994, 29, '屏幕截图 2026-01-12 150040.png', '20260209/屏幕截图 2026-01-12 150040_1770607129734.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 150040_1770607129734.png', 'image/png', 133098, '1', '2026-02-09 11:18:50', '1',
        '2026-02-09 11:18:50', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2995, 29, '屏幕截图 2026-01-12 100432.png', '20260209/屏幕截图 2026-01-12 100432_1770607182865.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 100432_1770607182865.png', 'image/png', 108703, null, '2026-02-09 11:19:43', null,
        '2026-02-09 11:19:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2996, 29, '屏幕截图 2026-01-12 103500.png', '20260209/屏幕截图 2026-01-12 103500_1770607182872.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 103500_1770607182872.png', 'image/png', 202289, null, '2026-02-09 11:19:43', null,
        '2026-02-09 11:19:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2997, 29, '屏幕截图 2026-01-12 112707.png', '20260209/屏幕截图 2026-01-12 112707_1770607182878.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 112707_1770607182878.png', 'image/png', 266130, null, '2026-02-09 11:19:43', null,
        '2026-02-09 11:19:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2998, 29, '屏幕截图 2026-01-13 090016.png', '20260209/屏幕截图 2026-01-13 090016_1770608264664.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 090016_1770608264664.png', 'image/png', 73594, null, '2026-02-09 11:37:45', null,
        '2026-02-09 11:37:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (2999, 29, '屏幕截图 2026-01-13 085429.png', '20260209/屏幕截图 2026-01-13 085429_1770608264667.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 085429_1770608264667.png', 'image/png', 18575, null, '2026-02-09 11:37:45', null,
        '2026-02-09 11:37:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3000, 29, '屏幕截图 2026-01-13 090116.png', '20260209/屏幕截图 2026-01-13 090116_1770608264670.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 090116_1770608264670.png', 'image/png', 182027, null, '2026-02-09 11:37:45', null,
        '2026-02-09 11:37:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3001, 29, '屏幕截图 2026-01-12 100432.png', '20260209/屏幕截图 2026-01-12 100432_1770608342765.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 100432_1770608342765.png', 'image/png', 108703, null, '2026-02-09 11:39:03', null,
        '2026-02-09 11:39:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3002, 29, '屏幕截图 2026-01-12 145552.png', '20260209/屏幕截图 2026-01-12 145552_1770608342765.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 145552_1770608342765.png', 'image/png', 21495, null, '2026-02-09 11:39:03', null,
        '2026-02-09 11:39:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3003, 29, '屏幕截图 2026-01-12 100206.png', '20260209/屏幕截图 2026-01-12 100206_1770608342765.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-12 100206_1770608342765.png', 'image/png', 13141, null, '2026-02-09 11:39:03', null,
        '2026-02-09 11:39:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3004, 29, '屏幕截图 2026-01-13 085934.png', '20260209/屏幕截图 2026-01-13 085934_1770608525104.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 085934_1770608525104.png', 'image/png', 56829, null, '2026-02-09 11:42:05', null,
        '2026-02-09 11:42:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3005, 29, '屏幕截图 2026-01-13 085606.png', '20260209/屏幕截图 2026-01-13 085606_1770608525100.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 085606_1770608525100.png', 'image/png', 171444, null, '2026-02-09 11:42:05', null,
        '2026-02-09 11:42:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3006, 29, '屏幕截图 2026-01-13 085934.png', '20260209/屏幕截图 2026-01-13 085934_1770608525104.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260209/屏幕截图 2026-01-13 085934_1770608525104.png', 'image/png', 56829, null, '2026-02-09 11:42:05', null,
        '2026-02-09 11:42:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3007, 29, 'R-C.jpg', '20260211/R-C_1770771013120.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/R-C_1770771013120.jpg', 'image/jpeg', 62533, '1',
        '2026-02-11 08:50:13', '1', '2026-02-11 08:50:13', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3008, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260211/20160512095836_4PiGk.thumb.1000_0_1770771016238.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/20160512095836_4PiGk.thumb.1000_0_1770771016238.jpeg', 'image/jpeg', 84595, '1', '2026-02-11 08:50:16', '1',
        '2026-02-11 08:50:16', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3009, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260211/20160512095836_4PiGk.thumb.1000_0_1770771507011.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/20160512095836_4PiGk.thumb.1000_0_1770771507011.jpeg', 'image/jpeg', 84595, '1', '2026-02-11 08:58:27', '1',
        '2026-02-11 08:58:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3010, 29, '陇工惠经费通 - 流程详情.pdf', '20260211/陇工惠经费通 - 流程详情_1770771509782.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/陇工惠经费通 - 流程详情_1770771509782.pdf', 'application/pdf', 79656, '1', '2026-02-11 08:58:30', '1',
        '2026-02-11 08:58:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3011, 29, '甘肃省石化工会工作委员会-资产备查簿.xlsx', '20260211/甘肃省石化工会工作委员会-资产备查簿_1770771520664.xlsx',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/甘肃省石化工会工作委员会-资产备查簿_1770771520664.xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 4254, '1', '2026-02-11 08:58:41', '1', '2026-02-11 08:58:41', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3012, 29, '屏幕截图 2026-01-12 100206.png', '20260211/屏幕截图 2026-01-12 100206_1770793170180.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/屏幕截图 2026-01-12 100206_1770793170180.png', 'image/png', 13141, '1', '2026-02-11 14:59:30', '1',
        '2026-02-11 14:59:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3013, 29, 'R-C.jpg', '20260211/R-C_1770795510354.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/R-C_1770795510354.jpg', 'image/jpeg', 62533, '1',
        '2026-02-11 15:38:30', '1', '2026-02-11 15:38:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3014, 29, '屏幕截图 2026-01-12 100800.png', '20260211/屏幕截图 2026-01-12 100800_1770803383231.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/屏幕截图 2026-01-12 100800_1770803383231.png', 'image/png', 96391, null, '2026-02-11 17:49:43', null,
        '2026-02-11 17:49:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3015, 29, '屏幕截图 2026-01-12 103500.png', '20260211/屏幕截图 2026-01-12 103500_1770803383231.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/屏幕截图 2026-01-12 103500_1770803383231.png', 'image/png', 202289, null, '2026-02-11 17:49:43', null,
        '2026-02-11 17:49:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3016, 29, '屏幕截图 2026-01-12 100206.png', '20260211/屏幕截图 2026-01-12 100206_1770803383231.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260211/屏幕截图 2026-01-12 100206_1770803383231.png', 'image/png', 13141, null, '2026-02-11 17:49:43', null,
        '2026-02-11 17:49:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3017, 29, '70f1-hiycyfx9128819.jpg', '20260212/70f1-hiycyfx9128819_1770866558790.jpg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/70f1-hiycyfx9128819_1770866558790.jpg', 'image/jpeg', 926210, '1', '2026-02-12 11:22:39', '1',
        '2026-02-12 11:22:39', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3018, 29, 'R-C.jpg', '20260212/R-C_1770866564374.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/R-C_1770866564374.jpg', 'image/jpeg', 62533, '1',
        '2026-02-12 11:22:44', '1', '2026-02-12 11:22:44', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3019, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260212/20160512095836_4PiGk.thumb.1000_0_1770866567232.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/20160512095836_4PiGk.thumb.1000_0_1770866567232.jpeg', 'image/jpeg', 84595, '1', '2026-02-12 11:22:47', '1',
        '2026-02-12 11:22:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3020, 29, 'R-C.jpg', '20260212/R-C_1770876522472.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/R-C_1770876522472.jpg', 'image/jpeg', 62533,
        '20220907049', '2026-02-12 14:08:43', '20220907049', '2026-02-12 14:08:43', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3021, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260212/20160512095836_4PiGk.thumb.1000_0_1770876645281.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/20160512095836_4PiGk.thumb.1000_0_1770876645281.jpeg', 'image/jpeg', 84595, '1', '2026-02-12 14:10:45', '1',
        '2026-02-12 14:10:45', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3022, 29, 'R-C.jpg', '20260212/R-C_1770884847866.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260212/R-C_1770884847866.jpg', 'image/jpeg', 62533, '1',
        '2026-02-12 16:27:28', '1', '2026-02-12 16:27:28', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3023, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770944088422.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770944088422.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 08:54:48', '1',
        '2026-02-13 08:54:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3024, 29, '1770971915324_c749170f255ee040219dd5d608b9be46.jpeg', '20260213/1770971915324_c749170f255ee040219dd5d608b9be46_1770971917705.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/1770971915324_c749170f255ee040219dd5d608b9be46_1770971917705.jpeg', 'image/jpeg', 14648, '20220907048',
        '2026-02-13 16:38:38', '20220907048', '2026-02-13 16:38:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3025, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770971959375.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770971959375.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:39:19', '1',
        '2026-02-13 16:39:19', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3026, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770972030210.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770972030210.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:40:30', '1',
        '2026-02-13 16:40:30', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3027, 29, 'R-C.jpg', '20260213/R-C_1770972081873.jpg', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/R-C_1770972081873.jpg', 'image/jpeg', 62533, '1',
        '2026-02-13 16:41:22', '1', '2026-02-13 16:41:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3028, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770972141734.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770972141734.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:42:22', '1',
        '2026-02-13 16:42:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3029, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770972172440.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770972172440.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:42:52', '1',
        '2026-02-13 16:42:52', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3030, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770972176727.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770972176727.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 16:42:57', '1',
        '2026-02-13 16:42:57', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3031, 29, '《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1).doc', '20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770972394678.doc',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770972394678.doc', 'application/msword', 162232, '1',
        '2026-02-13 16:46:35', '1', '2026-02-13 16:46:35', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3032, 29, '武威市生态环境局工会委员会-科目期初.xlsx', '20260213/武威市生态环境局工会委员会-科目期初_1770972407838.xlsx',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/武威市生态环境局工会委员会-科目期初_1770972407838.xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 6122, '1', '2026-02-13 16:46:48', '1', '2026-02-13 16:46:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3033, 29, '2025_武威市生态环境局工会委员会_620694.xlsx', '20260213/2025_武威市生态环境局工会委员会_620694_1770972723832.xlsx',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/2025_武威市生态环境局工会委员会_620694_1770972723832.xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 16082, '1', '2026-02-13 16:52:04', '1', '2026-02-13 16:52:04', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3034, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770972741719.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770972741719.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 16:52:22', '1',
        '2026-02-13 16:52:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3035, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770972746823.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770972746823.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:52:27', '1',
        '2026-02-13 16:52:27', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3036, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770972886122.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770972886122.pdf', 'application/pdf', 79656, '1', '2026-02-13 16:54:46', '1',
        '2026-02-13 16:54:46', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3037, 29, '《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1).doc', '20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770972901268.doc',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770972901268.doc', 'application/msword', 162232, '1',
        '2026-02-13 16:55:01', '1', '2026-02-13 16:55:01', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3038, 29, '单元素养练习.pdf', '20260213/单元素养练习_1770973074063.pdf', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/单元素养练习_1770973074063.pdf',
        'application/pdf', 4257805, '1', '2026-02-13 16:57:54', '1', '2026-02-13 16:57:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3039, 29, '经费收缴方面.doc', '20260213/经费收缴方面_1770973085480.doc', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/经费收缴方面_1770973085480.doc',
        'application/msword', 36403, '1', '2026-02-13 16:58:05', '1', '2026-02-13 16:58:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3040, 29, '单元素养练习.pdf', '20260213/单元素养练习_1770973251001.pdf', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/单元素养练习_1770973251001.pdf',
        'application/pdf', 4257805, '1', '2026-02-13 17:00:51', '1', '2026-02-13 17:00:51', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3041, 29, '《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1).doc', '20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770973263143.doc',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/《税务部门代收工会经费（筹备金）实施办法》0726工会(1)(1)_1770973263143.doc', 'application/msword', 162232, '1',
        '2026-02-13 17:01:03', '1', '2026-02-13 17:01:03', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3042, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770973276684.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770973276684.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 17:01:17', '1',
        '2026-02-13 17:01:17', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3043, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770973324734.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770973324734.pdf', 'application/pdf', 79656, '1', '2026-02-13 17:02:05', '1',
        '2026-02-13 17:02:05', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3044, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770973538218.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770973538218.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 17:05:38', '1',
        '2026-02-13 17:05:38', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3045, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770973540450.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770973540450.pdf', 'application/pdf', 79656, '1', '2026-02-13 17:05:40', '1',
        '2026-02-13 17:05:40', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3046, 29, '经费收缴方面.doc', '20260213/经费收缴方面_1770973547401.doc', 'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/经费收缴方面_1770973547401.doc',
        'application/msword', 36403, '1', '2026-02-13 17:05:47', '1', '2026-02-13 17:05:47', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3047, 29, '陇工惠经费通 - 流程详情.pdf', '20260213/陇工惠经费通 - 流程详情_1770974567894.pdf',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/陇工惠经费通 - 流程详情_1770974567894.pdf', 'application/pdf', 79656, '1', '2026-02-13 17:22:48', '1',
        '2026-02-13 17:22:48', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3048, 29, '20160512095836_4PiGk.thumb.1000_0.jpeg', '20260213/20160512095836_4PiGk.thumb.1000_0_1770974574423.jpeg',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/20160512095836_4PiGk.thumb.1000_0_1770974574423.jpeg', 'image/jpeg', 84595, '1', '2026-02-13 17:22:54', '1',
        '2026-02-13 17:22:54', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3049, 29, '屏幕截图 2026-02-13 145327.png', '20260213/屏幕截图 2026-02-13 145327_1770975801908.png',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/屏幕截图 2026-02-13 145327_1770975801908.png', 'image/png', 44710, '1', '2026-02-13 17:43:22', '1',
        '2026-02-13 17:43:22', false);
INSERT INTO infra_file (id, config_id, name, path, url, type, size, creator, create_time, updater, update_time, deleted)
VALUES (3050, 29, '原系统2025年记账单位组织数据(2)(1).xlsx', '20260213/原系统2025年记账单位组织数据(2)(1)_1770975811872.xlsx',
        'http://127.0.0.1:48080/admin-api/infra/file/29/get/20260213/原系统2025年记账单位组织数据(2)(1)_1770975811872.xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 91386, '1', '2026-02-13 17:43:32', '1', '2026-02-13 17:43:32', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (4, '数据库（示例）', 1, '我是数据库', false, '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.db.DBFileClientConfig","domain":"http://127.0.0.1:48080"}',
        '1', '2022-03-15 23:56:24', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (22, '七牛存储器（示例）', 20, '请换成你自己的密钥！！！', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"s3.cn-south-1.qiniucs.com","domain":"http://test.yudao.iocoder.cn","bucket":"ruoyi-vue-pro","accessKey":"3TvrJ70gl2Gt6IBe7_IZT1F6i_k0iMuRtyEv4EyS","accessSecret":"wd0tbVBYlp0S-ihA8Qg2hPLncoP83wyrIq24OZuY","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-01-13 22:11:12', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (24, '腾讯云存储（示例）', 20, '请换成你的密钥！！！', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"https://cos.ap-shanghai.myqcloud.com","domain":"http://tengxun-oss.iocoder.cn","bucket":"aoteman-1255880240","accessKey":"AKIDAF6WSh1uiIjwqtrOsGSN3WryqTM6cTMt","accessSecret":"X","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-11-09 16:03:22', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (25, '阿里云存储（示例）', 20, '', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"oss-cn-beijing.aliyuncs.com","domain":"http://ali-oss.iocoder.cn","bucket":"yunai-aoteman","accessKey":"LTAI5tEQLgnDyjh3WpNcdMKA","accessSecret":"X","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-11-09 16:47:08', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (26, '火山云存储（示例）', 20, '', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"tos-s3-cn-beijing.volces.com","domain":null,"bucket":"yunai","accessKey":"AKLTZjc3Zjc4MzZmMjU3NDk0ZTgxYmIyMmFkNTIwMDI1ZGE","accessSecret":"X==","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-11-09 16:56:42', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (27, '华为云存储（示例）', 20, '', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"obs.cn-east-3.myhuaweicloud.com","domain":"","bucket":"yudao","accessKey":"PVDONDEIOTW88LF8DC4U","accessSecret":"X","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-11-09 17:18:41', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (28, 'MinIO 存储（示例）', 20, '', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"http://127.0.0.1:9000","domain":"http://127.0.0.1:9000/yudao","bucket":"yudao","accessKey":"admin","accessSecret":"password","enablePathStyleAccess":false,"enablePublicAccess":true}',
        '1', '2024-11-09 17:43:10', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (29, '本地存储（示例）', 10, 'mac/linux 使用 /，windows 使用 \\', true,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.local.LocalFileClientConfig","basePath":"/Users/yunai/tmp/file","domain":"http://127.0.0.1:48080"}',
        '1', '2025-05-02 11:25:45', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (30, 'SFTP 存储（示例）', 12, '', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.sftp.SftpFileClientConfig","basePath":"/upload","domain":"http://127.0.0.1:48080","host":"127.0.0.1","port":2222,"username":"foo","password":"pass"}',
        '1', '2025-05-02 16:34:10', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (34, '七牛云存储【私有】（示例）', 20, '请换成你自己的密钥！！！', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"s3.cn-south-1.qiniucs.com","domain":"http://t151glocd.hn-bkt.clouddn.com","bucket":"ruoyi-vue-pro-private","accessKey":"3TvrJ70gl2Gt6IBe7_IZT1F6i_k0iMuRtyEv4EyS","accessSecret":"wd0tbVBYlp0S-ihA8Qg2hPLncoP83wyrIq24OZuY","enablePathStyleAccess":false,"enablePublicAccess":false}',
        '1', '2025-08-17 21:22:00', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_file_config (id, name, storage, remark, master, config, creator, create_time, updater, update_time, deleted)
VALUES (35, '1', 20, '1', false,
        '{"@class":"cn.iocoder.yudao.module.infra.framework.file.core.client.s3.S3FileClientConfig","endpoint":"http://www.baidu.com","domain":"http://www.xxx.com","bucket":"1","accessKey":"2","accessSecret":"3","enablePathStyleAccess":false,"enablePublicAccess":false}',
        '1', '2025-10-02 14:32:12', '1', '2026-02-09 11:18:28', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (5, '支付通知 Job', 2, 'payNotifyJob', null, '* * * * * ?', 0, 0, 0, '1', '2021-10-27 08:34:42', '1', '2024-09-12 13:32:48', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (17, '支付订单同步 Job', 2, 'payOrderSyncJob', null, '0 0/1 * * * ?', 0, 0, 0, '1', '2023-07-22 14:36:26', '1', '2023-07-22 15:39:08', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (18, '支付订单过期 Job', 2, 'payOrderExpireJob', null, '0 0/1 * * * ?', 0, 0, 0, '1', '2023-07-22 15:36:23', '1', '2023-07-22 15:39:54', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (19, '退款订单的同步 Job', 2, 'payRefundSyncJob', null, '0 0/1 * * * ?', 0, 0, 0, '1', '2023-07-23 21:03:44', '1', '2023-07-23 21:09:00', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (21, 'Mall 交易订单的自动过期 Job', 2, 'tradeOrderAutoCancelJob', '', '0 * * * * ?', 3, 0, 0, '1', '2023-09-25 23:43:26', '1', '2025-10-02 11:08:34', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (22, 'Mall 交易订单的自动收货 Job', 2, 'tradeOrderAutoReceiveJob', '', '0 * * * * ?', 3, 0, 0, '1', '2023-09-26 19:23:53', '1', '2025-10-02 11:08:36', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (23, 'Mall 交易订单的自动评论 Job', 2, 'tradeOrderAutoCommentJob', '', '0 * * * * ?', 3, 0, 0, '1', '2023-09-26 23:38:29', '1', '2025-10-02 11:08:38', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (24, 'Mall 佣金解冻 Job', 2, 'brokerageRecordUnfreezeJob', '', '0 * * * * ?', 3, 0, 0, '1', '2023-09-28 22:01:46', '1', '2025-10-02 11:08:04', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (25, '访问日志清理 Job', 2, 'accessLogCleanJob', '', '0 0 0 * * ?', 3, 0, 0, '1', '2023-10-03 10:59:41', '1', '2023-10-03 11:01:10', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (26, '错误日志清理 Job', 2, 'errorLogCleanJob', '', '0 0 0 * * ?', 3, 0, 0, '1', '2023-10-03 11:00:43', '1', '2023-10-03 11:01:12', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (27, '任务日志清理 Job', 2, 'jobLogCleanJob', '', '0 0 0 * * ?', 3, 0, 0, '1', '2023-10-03 11:01:33', '1', '2024-09-12 13:40:34', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (33, 'demoJob', 2, 'demoJob', '', '0 * * * * ?', 1, 1, 0, '1', '2024-10-27 19:38:46', '1', '2025-05-10 18:13:54', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (35, '转账订单的同步 Job', 2, 'payTransferSyncJob', '', '0 * * * * ?', 0, 0, 0, '1', '2025-05-10 17:35:54', '1', '2025-05-10 18:13:52', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (36, 'IoT 设备离线检查 Job', 2, 'iotDeviceOfflineCheckJob', '', '0 * * * * ?', 0, 0, 0, '1', '2025-07-03 23:48:44', '"1"', '2025-07-03 23:48:47', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (37, 'IoT OTA 升级推送 Job', 2, 'iotOtaUpgradeJob', '', '0 * * * * ?', 0, 0, 0, '1', '2025-07-03 23:49:07', '"1"', '2025-07-03 23:49:13', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (38, 'Mall 拼团过期 Job', 2, 'combinationRecordExpireJob', '', '0 * * * * ?', 0, 0, 0, '1', '2025-10-02 11:07:11', '1', '2025-10-02 11:07:14', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (39, 'Mall 优惠券过期 Job', 2, 'couponExpireJob', '', '0 * * * * ?', 0, 0, 0, '1', '2025-10-02 11:07:34', '1', '2025-10-02 11:07:37', false);
INSERT INTO infra_job (id, name, status, handler_name, handler_param, cron_expression, retry_count, retry_interval, monitor_timeout, creator, create_time, updater, update_time,
                       deleted)
VALUES (40, 'Mall 商品统计 Job', 2, 'productStatisticsJob', '', '0 0 0 * * ?', 0, 0, 0, '1', '2025-11-22 18:51:25', '1', '2025-11-22 18:56:21', false);


INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (8, 1, 0, 'user-sms-login', '前台用户短信登录', '您的验证码是{code}', '["code"]', null, '4372216', 5, 'LONGGONGHUI', '1', '2021-10-11 08:10:00', '1',
        '2024-08-18 11:57:06', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (9, 2, 0, 'bpm_task_assigned', '【工作流】任务被分配', '您收到了一条新的待办任务：{processInstanceName}-{taskName}，申请人：{startUserNickname}，处理链接：{detailUrl}',
        '["processInstanceName","taskName","startUserNickname","detailUrl"]', null, 'suibian', 4, 'DEBUG_DING_TALK', '1', '2022-01-21 22:31:19', '1', '2022-01-22 00:03:36', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (10, 2, 0, 'bpm_process_instance_reject', '【工作流】流程被不通过', '您的流程被审批不通过：{processInstanceName}，原因：{reason}，查看链接：{detailUrl}',
        '["processInstanceName","reason","detailUrl"]', null, 'suibian', 4, 'DEBUG_DING_TALK', '1', '2022-01-22 00:03:31', '1', '2022-05-01 12:33:14', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (11, 2, 0, 'bpm_process_instance_approve', '【工作流】流程被通过', '您的流程被审批通过：{processInstanceName}，查看链接：{detailUrl}', '["processInstanceName","detailUrl"]',
        null, 'suibian', 4, 'DEBUG_DING_TALK', '1', '2022-01-22 00:04:31', '1', '2022-03-27 20:32:21', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (17, 2, 0, 'bpm_task_timeout', '【工作流】任务审批超时', '您收到了一条超时的待办任务：{processInstanceName}-{taskName}，处理链接：{detailUrl}',
        '["processInstanceName","taskName","detailUrl"]', '', 'X', 4, 'DEBUG_DING_TALK', '1', '2024-08-16 21:59:15', '1', '2024-08-16 21:59:34', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (18, 1, 0, 'admin-reset-password', '后台用户 - 忘记密码', '您的验证码{code}，该验证码 5 分钟内有效，请勿泄漏于他人！', '["code"]', '', 'null', 5, 'LONGGONGHUI', '1',
        '2025-03-16 14:19:34', '1', '2025-03-16 14:19:45', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (19, 1, 0, 'admin-sms-login', '后台用户短信登录', '您的验证码是{code}', '["code"]', '', '4372216', 5, 'LONGGONGHUI', '1', '2025-04-08 09:36:03', '1',
        '2025-04-08 09:36:17', false);
INSERT INTO system_sms_template (id, type, status, code, name, content, params, remark, api_template_id, channel_id, channel_code, creator, create_time, updater, update_time,
                                 deleted)
VALUES (20, 2, 0, 'lghjft-xxtx-tzdx', '陇工惠消息提醒短信', '{dxnr}', '["dxnr"]', '消息提醒短信模板', 'LGH_XXTX_TZDX', 5, 'LONGGONGHUI', '1', '2026-03-12 08:30:00',
        '1', '2026-03-12 08:30:00', false);
UPDATE system_sms_template
SET channel_id = 5,
    channel_code = 'LONGGONGHUI'
WHERE id IN (8, 18, 19, 20);
INSERT INTO system_sms_channel (id, signature, code, status, remark, api_key, api_secret, callback_url, kzcs, creator, create_time, updater, update_time, deleted)
VALUES (4, '测试渠道', 'DEBUG_DING_TALK', 0, '123', '696b5d8ead48071237e4aa5861ff08dbadb2b4ded1c688a7b7c9afc615579859',
        'SEC5c4e5ff888bc8a9923ae47f59e7ccd30af1f14d93c55b4e2c9cb094e35aeed67', null, null, '1', '2021-04-13 00:23:14', '1', '2022-03-27 20:29:49', false);
INSERT INTO system_sms_channel (id, signature, code, status, remark, api_key, api_secret, callback_url, kzcs, creator, create_time, updater, update_time, deleted)
VALUES (5, '陇工惠', 'LONGGONGHUI', 0, '已预置文档测试参数，通知短信默认复用 sendMobileCode 接口并通过 code 字段发送提醒内容', null, null, null,
        '{
          "yzm": {
            "yyid": "testApp",
            "pt": "testApp",
            "jmmy": "8244bbf03a214426",
            "qmmy": "28a740ea93b74d61",
            "wgdz": "https://gansu-gateway-test.gobestsoft.cn",
            "jkdz": "/gbs/admin/sys/sms/sendMobileCode"
          },
          "tzdx": {
            "yyid": "testApp",
            "pt": "testApp",
            "jmmy": "8244bbf03a214426",
            "qmmy": "28a740ea93b74d61",
            "wgdz": "https://gansu-gateway-test.gobestsoft.cn",
            "jkdz": "/gbs/admin/sys/sms/sendMobileCode",
            "qqfs": "GET",
            "sjhmzd": "mobile",
            "nrzd": "code",
            "mbdmzd": "",
            "mbdm": ""
          }
        }',
        '1', '2026-03-12 08:30:00', '1', '2026-03-12 08:30:00', false);


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

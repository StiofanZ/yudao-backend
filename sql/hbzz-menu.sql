-- ============================================================
-- 汇拨征收 菜单（可直接在线上数据库执行）
-- ============================================================

-- 防重复：先删除已有记录
DELETE FROM system_menu WHERE id BETWEEN 215100 AND 215149;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 215100 AND 215149;

-- 同时删除旧的菜单记录（将被替换）
DELETE FROM system_menu WHERE id BETWEEN 213644 AND 213651;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 213644 AND 213651;

-- ===================== 汇拨征收 hbzz =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215100, '汇拨征收', '', 1, 15, 0, '/hbzz', 'ep:coin', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 单位缴费台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215101, '单位缴费台账', '', 2, 1, 215100, 'jfmx', '', '/lghjft/hbzz/jfmx/index', 'LghjftHbzzJfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215102, '查询', 'lghjft:hbzz-jfmx:query', 3, 1, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215103, '导出', 'lghjft:hbzz-jfmx:export', 3, 2, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 单位返拨查询
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215104, '单位返拨查询', '', 2, 2, 215100, 'hkxx', 'ep:wallet-filled', '/lghjft/hbzz/hkxx/index', 'LghjftHbzzHkxx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215105, '查询', 'lghjft:hbzz-hkxx:query', 3, 1, 215104, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215106, '处理', 'lghjft:hbzz-hkxx:update', 3, 2, 215104, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215107, '导出', 'lghjft:hbzz-hkxx:export', 3, 3, 215104, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 基层经费做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215108, '基层经费做账', '', 2, 3, 215100, 'jcjfzz', '', '/lghjft/hbzz/jcjfzz/index', 'LghjftHbzzJcjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215109, '查询', 'lghjft:hbzz-hkxx:query', 3, 1, 215108, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215110, '导出', 'lghjft:hbzz-hkxx:export', 3, 2, 215108, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 角色菜单关联（role_id=1 为超级管理员） =====================
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 215100, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215101, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215102, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215103, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215104, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215105, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215106, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215107, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215108, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215109, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215110, '1', NOW(), '1', NOW(), 0, 1);

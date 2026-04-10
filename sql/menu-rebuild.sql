-- ============================================================
-- 菜单重建 SQL（严格按 v1 结构）
-- 影响范围：hbzz, ghcbj, xwqy, xejf, cxtj, jfsy
-- ============================================================

-- ===================== 1. 删除旧菜单 =====================

-- 删除 jfcl #215000
DELETE FROM system_menu WHERE id BETWEEN 215000 AND 215099;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 215000 AND 215099;
-- 删除 hbzz/汇拨征收 #215100
DELETE FROM system_menu WHERE id BETWEEN 215100 AND 215199;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 215100 AND 215199;
-- 删除 ghcbj #216300
DELETE FROM system_menu WHERE id BETWEEN 216300 AND 216399;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 216300 AND 216399;
-- 删除 xwqy #214700
DELETE FROM system_menu WHERE id BETWEEN 214700 AND 214749;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214700 AND 214749;
-- 删除 xejf #216200
DELETE FROM system_menu WHERE id BETWEEN 216200 AND 216299;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 216200 AND 216299;
-- 删除 cxtj #214750
DELETE FROM system_menu WHERE id BETWEEN 214750 AND 214899;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214750 AND 214899;
-- 删除 jfsy #216400
DELETE FROM system_menu WHERE id BETWEEN 216400 AND 216419;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 216400 AND 216419;

-- ===================== 2. 划拨做账 hbzz (215100-215199) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215100, '划拨做账', '', 1, 3, 0, '/hbzz', 'ep:coin', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费分成做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215101, '经费分成做账', '', 2, 1, 215100, 'ghjfzz', '', '/lghjft/hbzz/ghjfzz/index', 'LghjftHbzzGhjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215102, '查询', 'lghjft:hbzz-ghjfzz:query', 3, 1, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215103, '新增', 'lghjft:hbzz-ghjfzz:create', 3, 2, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215104, '修改', 'lghjft:hbzz-ghjfzz:update', 3, 3, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215105, '删除', 'lghjft:hbzz-ghjfzz:delete', 3, 4, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215106, '导出', 'lghjft:hbzz-ghjfzz:export', 3, 5, 215101, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 省总到账核对
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215107, '省总到账核对', '', 2, 2, 215100, 'szdzhd', '', '/lghjft/hbzz/szdzhd/index', 'LghjftHbzzSzdzhd', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215108, '查询', 'lghjft:hbzz-szdzhd:query', 3, 1, 215107, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215109, '新增', 'lghjft:hbzz-szdzhd:create', 3, 2, 215107, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215110, '修改', 'lghjft:hbzz-szdzhd:update', 3, 3, 215107, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215111, '删除', 'lghjft:hbzz-szdzhd:delete', 3, 4, 215107, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215112, '导出', 'lghjft:hbzz-szdzhd:export', 3, 5, 215107, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 基层经费到账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215113, '基层经费到账', '', 2, 3, 215100, 'jcjfzz', '', '/lghjft/hbzz/jcjfzz/index', 'LghjftHbzzJcjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215114, '查询', 'lghjft:hbzz-jcjfzz:query', 3, 1, 215113, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215115, '新增', 'lghjft:hbzz-jcjfzz:create', 3, 2, 215113, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215116, '修改', 'lghjft:hbzz-jcjfzz:update', 3, 3, 215113, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215117, '删除', 'lghjft:hbzz-jcjfzz:delete', 3, 4, 215113, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215118, '导出', 'lghjft:hbzz-jcjfzz:export', 3, 5, 215113, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 应返基层代管经费做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215119, '应返基层代管经费做账', '', 2, 4, 215100, 'fjcjfzz', '', '/lghjft/hbzz/fjcjfzz/index', 'LghjftHbzzFjcjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215120, '查询', 'lghjft:hbzz-fjcjfzz:query', 3, 1, 215119, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215121, '新增', 'lghjft:hbzz-fjcjfzz:create', 3, 2, 215119, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215122, '修改', 'lghjft:hbzz-fjcjfzz:update', 3, 3, 215119, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215123, '删除', 'lghjft:hbzz-fjcjfzz:delete', 3, 4, 215119, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215124, '导出', 'lghjft:hbzz-fjcjfzz:export', 3, 5, 215119, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 代管经费台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215125, '代管经费台账', '', 2, 5, 215100, 'dgjftz', '', '/lghjft/hbzz/dgjftz/index', 'LghjftHbzzDgjftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215126, '查询', 'lghjft:hbzz-dgjftz:query', 3, 1, 215125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215127, '新增', 'lghjft:hbzz-dgjftz:create', 3, 2, 215125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215128, '修改', 'lghjft:hbzz-dgjftz:update', 3, 3, 215125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215129, '删除', 'lghjft:hbzz-dgjftz:delete', 3, 4, 215125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215130, '导出', 'lghjft:hbzz-dgjftz:export', 3, 5, 215125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 拨付明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215131, '拨付明细', '', 2, 6, 215100, 'jfbfmx', '', '/lghjft/hbzz/jfbfmx/index', 'LghjftHbzzJfbfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215132, '查询', 'lghjft:hbzz-jfbfmx:query', 3, 1, 215131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215133, '新增', 'lghjft:hbzz-jfbfmx:create', 3, 2, 215131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215134, '修改', 'lghjft:hbzz-jfbfmx:update', 3, 3, 215131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215135, '删除', 'lghjft:hbzz-jfbfmx:delete', 3, 4, 215131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215136, '导出', 'lghjft:hbzz-jfbfmx:export', 3, 5, 215131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215137, '筹备金账', '', 2, 7, 215100, 'cbjzz', '', '/lghjft/hbzz/cbjzz/index', 'LghjftHbzzCbjzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215138, '查询', 'lghjft:hbzz-cbjzz:query', 3, 1, 215137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215139, '新增', 'lghjft:hbzz-cbjzz:create', 3, 2, 215137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215140, '修改', 'lghjft:hbzz-cbjzz:update', 3, 3, 215137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215141, '删除', 'lghjft:hbzz-cbjzz:delete', 3, 4, 215137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215142, '导出', 'lghjft:hbzz-cbjzz:export', 3, 5, 215137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 滞纳金账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215143, '滞纳金账', '', 2, 8, 215100, 'znjzz', '', '/lghjft/hbzz/znjzz/index', 'LghjftHbzzZnjzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215144, '查询', 'lghjft:hbzz-znjzz:query', 3, 1, 215143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215145, '新增', 'lghjft:hbzz-znjzz:create', 3, 2, 215143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215146, '修改', 'lghjft:hbzz-znjzz:update', 3, 3, 215143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215147, '删除', 'lghjft:hbzz-znjzz:delete', 3, 4, 215143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215148, '导出', 'lghjft:hbzz-znjzz:export', 3, 5, 215143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 手续费做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215149, '手续费做账', '', 2, 9, 215100, 'sxfzz', '', '/lghjft/hbzz/sxfzz/index', 'LghjftHbzzSxfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215150, '查询', 'lghjft:hbzz-sxfzz:query', 3, 1, 215149, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215151, '新增', 'lghjft:hbzz-sxfzz:create', 3, 2, 215149, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215152, '修改', 'lghjft:hbzz-sxfzz:update', 3, 3, 215149, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215153, '删除', 'lghjft:hbzz-sxfzz:delete', 3, 4, 215149, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215154, '导出', 'lghjft:hbzz-sxfzz:export', 3, 5, 215149, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 滞纳金2020年2月后
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215155, '滞纳金2020年2月后', '', 2, 10, 215100, 'znjtz', '', '/lghjft/hbzz/znjtz/index', 'LghjftHbzzZnjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215156, '查询', 'lghjft:hbzz-znjtz:query', 3, 1, 215155, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215157, '新增', 'lghjft:hbzz-znjtz:create', 3, 2, 215155, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215158, '修改', 'lghjft:hbzz-znjtz:update', 3, 3, 215155, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215159, '删除', 'lghjft:hbzz-znjtz:delete', 3, 4, 215155, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215160, '导出', 'lghjft:hbzz-znjtz:export', 3, 5, 215155, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金下沉标记
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215161, '筹备金下沉标记', '', 2, 11, 215100, 'cbjxc', '', '/lghjft/hbzz/cbjxc/index', 'LghjftHbzzCbjxc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215162, '查询', 'lghjft:hbzz-cbjxc:query', 3, 1, 215161, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215163, '新增', 'lghjft:hbzz-cbjxc:create', 3, 2, 215161, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215164, '修改', 'lghjft:hbzz-cbjxc:update', 3, 3, 215161, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215165, '删除', 'lghjft:hbzz-cbjxc:delete', 3, 4, 215161, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215166, '导出', 'lghjft:hbzz-cbjxc:export', 3, 5, 215161, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 3. 筹备金账 ghcbj (216300-216349) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216300, '筹备金账', '', 1, 6, 0, '/ghcbj', 'ep:money', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金结算台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216301, '筹备金结算台账', '', 2, 1, 216300, 'cbjtz', '', '/lghjft/ghcbj/cbjtz/index', 'LghjftGhcbjCbjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216302, '查询', 'lghjft:ghcbj-cbjtz:query', 3, 1, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216303, '新增', 'lghjft:ghcbj-cbjtz:create', 3, 2, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216304, '修改', 'lghjft:ghcbj-cbjtz:update', 3, 3, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216305, '删除', 'lghjft:ghcbj-cbjtz:delete', 3, 4, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216306, '导出', 'lghjft:ghcbj-cbjtz:export', 3, 5, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金21年8月后
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216307, '筹备金21年8月后', '', 2, 2, 216300, 'cbjqfmx', '', '/lghjft/sjwh/cbjqfmx/index', 'LghjftSjwhCbjqfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216308, '查询', 'lghjft:ghcbj-cbjqfmx:query', 3, 1, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216309, '新增', 'lghjft:ghcbj-cbjqfmx:create', 3, 2, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216310, '修改', 'lghjft:ghcbj-cbjqfmx:update', 3, 3, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216311, '删除', 'lghjft:ghcbj-cbjqfmx:delete', 3, 4, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216312, '导出', 'lghjft:ghcbj-cbjqfmx:export', 3, 5, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金全返
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216313, '筹备金全返', '', 2, 3, 216300, 'ghjfcbjqf', '', '/lghjft/cxtj/ghjfcbjqf/index', 'LghjftCxtjGhjfcbjqf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216314, '查询', 'lghjft:ghcbj-ghjfcbjqf:query', 3, 1, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216315, '新增', 'lghjft:ghcbj-ghjfcbjqf:create', 3, 2, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216316, '修改', 'lghjft:ghcbj-ghjfcbjqf:update', 3, 3, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216317, '删除', 'lghjft:ghcbj-ghjfcbjqf:delete', 3, 4, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216318, '导出', 'lghjft:ghcbj-ghjfcbjqf:export', 3, 5, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216319, '筹备金统计', '', 2, 4, 216300, 'cbjhztj', '', '/lghjft/cxtj/cbjmx/index', 'LghjftCxtjCbjmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216320, '查询', 'lghjft:ghcbj-cbjhztj:query', 3, 1, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216321, '新增', 'lghjft:ghcbj-cbjhztj:create', 3, 2, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216322, '修改', 'lghjft:ghcbj-cbjhztj:update', 3, 3, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216323, '删除', 'lghjft:ghcbj-cbjhztj:delete', 3, 4, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216324, '导出', 'lghjft:ghcbj-cbjhztj:export', 3, 5, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金清理台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216325, '筹备金清理台账', '', 2, 5, 216300, 'cbjqltz', '', '/lghjft/ghcbj/cbjqltz/index', 'LghjftGhcbjCbjqltz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216326, '查询', 'lghjft:ghcbj-cbjqltz:query', 3, 1, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216327, '新增', 'lghjft:ghcbj-cbjqltz:create', 3, 2, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216328, '修改', 'lghjft:ghcbj-cbjqltz:update', 3, 3, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216329, '删除', 'lghjft:ghcbj-cbjqltz:delete', 3, 4, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216330, '导出', 'lghjft:ghcbj-cbjqltz:export', 3, 5, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 4. 小微台账 xwqy (214700-214749) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214700, '小微台账', '', 1, 5, 0, '/xwqy', 'ep:data-board', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214701, '小微企业管理', '', 2, 1, 214700, 'xwqygl', '', '/lghjft/xwqy/xwqygl/index', 'LghjftXwqyXwqygl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214702, '查询', 'lghjft:xwqy-xwqygl:query', 3, 1, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214703, '新增', 'lghjft:xwqy-xwqygl:create', 3, 2, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214704, '修改', 'lghjft:xwqy-xwqygl:update', 3, 3, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214705, '删除', 'lghjft:xwqy-xwqygl:delete', 3, 4, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214706, '导出', 'lghjft:xwqy-xwqygl:export', 3, 5, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业95%
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214707, '小微企业95%', '', 2, 2, 214700, 'xwqyjfmx', '', '/lghjft/xwqy/xwqyjfmx/index', 'LghjftXwqyXwqyjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214708, '查询', 'lghjft:xwqy-xwqyjfmx:query', 3, 1, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214709, '新增', 'lghjft:xwqy-xwqyjfmx:create', 3, 2, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214710, '修改', 'lghjft:xwqy-xwqyjfmx:update', 3, 3, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214711, '删除', 'lghjft:xwqy-xwqyjfmx:delete', 3, 4, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214712, '导出', 'lghjft:xwqy-xwqyjfmx:export', 3, 5, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业60%
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214713, '小微企业60%', '', 2, 3, 214700, 'xwqyjfyfmx', '', '/lghjft/xwqy/xwqyjfyfmx/index', 'LghjftXwqyXwqyjfyfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214714, '查询', 'lghjft:xwqy-xwqyjfyfmx:query', 3, 1, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214715, '新增', 'lghjft:xwqy-xwqyjfyfmx:create', 3, 2, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214716, '修改', 'lghjft:xwqy-xwqyjfyfmx:update', 3, 3, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214717, '删除', 'lghjft:xwqy-xwqyjfyfmx:delete', 3, 4, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214718, '导出', 'lghjft:xwqy-xwqyjfyfmx:export', 3, 5, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微经费统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214719, '小微经费统计', '', 2, 4, 214700, 'xwqyjftj', '', '/lghjft/xwqy/xwqyjftj/index', 'LghjftXwqyXwqyjftj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214720, '查询', 'lghjft:xwqy-xwqyjftj:query', 3, 1, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214721, '新增', 'lghjft:xwqy-xwqyjftj:create', 3, 2, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214722, '修改', 'lghjft:xwqy-xwqyjftj:update', 3, 3, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214723, '删除', 'lghjft:xwqy-xwqyjftj:delete', 3, 4, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214724, '导出', 'lghjft:xwqy-xwqyjftj:export', 3, 5, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 5. 小额台账 xejf (216200-216299) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216200, '小额台账', '', 1, 5, 0, '/xejf', 'ep:files', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 23小额确认情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216201, '23小额确认情况', '', 2, 1, 216200, 'xejfold', '', '/lghjft/xejf/xejfold/index', 'LghjftXejfXejfold', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216202, '查询', 'lghjft:xejf-xejfold:query', 3, 1, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216203, '新增', 'lghjft:xejf-xejfold:create', 3, 2, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216204, '修改', 'lghjft:xejf-xejfold:update', 3, 3, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216205, '删除', 'lghjft:xejf-xejfold:delete', 3, 4, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216206, '导出', 'lghjft:xejf-xejfold:export', 3, 5, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 24小额确认情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216207, '24小额确认情况', '', 2, 2, 216200, 'xejf24', '', '/lghjft/xejf/xejf24/index', 'LghjftXejfXejf24', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216208, '查询', 'lghjft:xejf-xejf24:query', 3, 1, 216207, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216209, '新增', 'lghjft:xejf-xejf24:create', 3, 2, 216207, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216210, '修改', 'lghjft:xejf-xejf24:update', 3, 3, 216207, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216211, '删除', 'lghjft:xejf-xejf24:delete', 3, 4, 216207, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216212, '导出', 'lghjft:xejf-xejf24:export', 3, 5, 216207, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费组织管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216213, '小额缴费组织管理', '', 2, 3, 216200, 'xejfzzgl', '', '/lghjft/xejf/xejfzzgl/index', 'LghjftXejfXejfzzgl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216214, '查询', 'lghjft:xejf-xejfzzgl:query', 3, 1, 216213, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216215, '新增', 'lghjft:xejf-xejfzzgl:create', 3, 2, 216213, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216216, '修改', 'lghjft:xejf-xejfzzgl:update', 3, 3, 216213, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216217, '删除', 'lghjft:xejf-xejfzzgl:delete', 3, 4, 216213, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216218, '导出', 'lghjft:xejf-xejfzzgl:export', 3, 5, 216213, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216219, '小额缴费明细', '', 2, 4, 216200, 'xejf2023', '', '/lghjft/xejf/xejf2023/index', 'LghjftXejfXejf2023', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216220, '查询', 'lghjft:xejf-xejf2023:query', 3, 1, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216221, '新增', 'lghjft:xejf-xejf2023:create', 3, 2, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216222, '修改', 'lghjft:xejf-xejf2023:update', 3, 3, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216223, '删除', 'lghjft:xejf-xejf2023:delete', 3, 4, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216224, '导出', 'lghjft:xejf-xejf2023:export', 3, 5, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216225, '小额缴费台账', '', 2, 5, 216200, 'xetz', '', '/lghjft/xejf/xetz/index', 'LghjftXejfXetz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216226, '查询', 'lghjft:xejf-xetz:query', 3, 1, 216225, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216227, '新增', 'lghjft:xejf-xetz:create', 3, 2, 216225, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216228, '修改', 'lghjft:xejf-xetz:update', 3, 3, 216225, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216229, '删除', 'lghjft:xejf-xetz:delete', 3, 4, 216225, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216230, '导出', 'lghjft:xejf-xetz:export', 3, 5, 216225, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216231, '小额缴费统计', '', 2, 6, 216200, 'xejftz2023', '', '/lghjft/xejf/xejftz2023/index', 'LghjftXejfXejftz2023', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216232, '查询', 'lghjft:xejf-xejftz2023:query', 3, 1, 216231, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216233, '新增', 'lghjft:xejf-xejftz2023:create', 3, 2, 216231, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216234, '修改', 'lghjft:xejf-xejftz2023:update', 3, 3, 216231, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216235, '删除', 'lghjft:xejf-xejftz2023:delete', 3, 4, 216231, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216236, '导出', 'lghjft:xejf-xejftz2023:export', 3, 5, 216231, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费拨付台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216237, '小额缴费拨付台账', '', 2, 7, 216200, 'xebftz', '', '/lghjft/xejf/xebftz/index', 'LghjftXejfXebftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216238, '查询', 'lghjft:xejf-xebftz:query', 3, 1, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216239, '新增', 'lghjft:xejf-xebftz:create', 3, 2, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216240, '修改', 'lghjft:xejf-xebftz:update', 3, 3, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216241, '删除', 'lghjft:xejf-xebftz:delete', 3, 4, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216242, '导出', 'lghjft:xejf-xebftz:export', 3, 5, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额拨付占比
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216243, '小额拨付占比', '', 2, 8, 216200, 'xebfzb', '', '/lghjft/xejf/xebfzb/index', 'LghjftXejfXebfzb', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216244, '查询', 'lghjft:xejf-xebfzb:query', 3, 1, 216243, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216245, '新增', 'lghjft:xejf-xebfzb:create', 3, 2, 216243, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216246, '修改', 'lghjft:xejf-xebfzb:update', 3, 3, 216243, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216247, '删除', 'lghjft:xejf-xebfzb:delete', 3, 4, 216243, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216248, '导出', 'lghjft:xejf-xebfzb:export', 3, 5, 216243, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额拨付记账凭证
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216249, '小额拨付记账凭证', '', 2, 9, 216200, 'hkxxxejf', '', '/lghjft/xejf/hkxxxejf/index', 'LghjftXejfHkxxxejf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216250, '查询', 'lghjft:xejf-hkxxxejf:query', 3, 1, 216249, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216251, '新增', 'lghjft:xejf-hkxxxejf:create', 3, 2, 216249, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216252, '修改', 'lghjft:xejf-hkxxxejf:update', 3, 3, 216249, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216253, '删除', 'lghjft:xejf-hkxxxejf:delete', 3, 4, 216249, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216254, '导出', 'lghjft:xejf-hkxxxejf:export', 3, 5, 216249, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额筹备金做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216255, '小额筹备金做账', '', 2, 10, 216200, 'hkxxxejfcbj', '', '/lghjft/xejf/hkxxxejfcbj/index', 'LghjftXejfHkxxxejfcbj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216256, '查询', 'lghjft:xejf-hkxxxejfcbj:query', 3, 1, 216255, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216257, '新增', 'lghjft:xejf-hkxxxejfcbj:create', 3, 2, 216255, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216258, '修改', 'lghjft:xejf-hkxxxejfcbj:update', 3, 3, 216255, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216259, '删除', 'lghjft:xejf-hkxxxejfcbj:delete', 3, 4, 216255, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216260, '导出', 'lghjft:xejf-hkxxxejfcbj:export', 3, 5, 216255, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额代管经费做账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216261, '小额代管经费做账', '', 2, 11, 216200, 'xedgjf', '', '/lghjft/xejf/xedgjf/index', 'LghjftXejfXedgjf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216262, '查询', 'lghjft:xejf-xedgjf:query', 3, 1, 216261, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216263, '新增', 'lghjft:xejf-xedgjf:create', 3, 2, 216261, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216264, '修改', 'lghjft:xejf-xedgjf:update', 3, 3, 216261, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216265, '删除', 'lghjft:xejf-xedgjf:delete', 3, 4, 216261, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216266, '导出', 'lghjft:xejf-xedgjf:export', 3, 5, 216261, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额缴费基层到账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216267, '小额缴费基层到账', '', 2, 12, 216200, 'hkxxxejfjcdz', '', '/lghjft/xejf/hkxxxejfjcdz/index', 'LghjftXejfHkxxxejfjcdz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216268, '查询', 'lghjft:xejf-hkxxxejfjcdz:query', 3, 1, 216267, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216269, '新增', 'lghjft:xejf-hkxxxejfjcdz:create', 3, 2, 216267, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216270, '修改', 'lghjft:xejf-hkxxxejfjcdz:update', 3, 3, 216267, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216271, '删除', 'lghjft:xejf-hkxxxejfjcdz:delete', 3, 4, 216267, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216272, '导出', 'lghjft:xejf-hkxxxejfjcdz:export', 3, 5, 216267, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额划拨失败记录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216273, '小额划拨失败记录', '', 2, 13, 216200, 'xejfhbsb', '', '/lghjft/xejf/xejfhbsb/index', 'LghjftXejfXejfhbsb', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216274, '查询', 'lghjft:xejf-xejfhbsb:query', 3, 1, 216273, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216275, '新增', 'lghjft:xejf-xejfhbsb:create', 3, 2, 216273, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216276, '修改', 'lghjft:xejf-xejfhbsb:update', 3, 3, 216273, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216277, '删除', 'lghjft:xejf-xejfhbsb:delete', 3, 4, 216273, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216278, '导出', 'lghjft:xejf-xejfhbsb:export', 3, 5, 216273, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额失败已修改
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216279, '小额失败已修改', '', 2, 14, 216200, 'xejfhbsbyxg', '', '/lghjft/xejf/xejfhbsbyxg/index', 'LghjftXejfXejfhbsbyxg', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216280, '查询', 'lghjft:xejf-xejfhbsbyxg:query', 3, 1, 216279, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216281, '新增', 'lghjft:xejf-xejfhbsbyxg:create', 3, 2, 216279, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216282, '修改', 'lghjft:xejf-xejfhbsbyxg:update', 3, 3, 216279, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216283, '删除', 'lghjft:xejf-xejfhbsbyxg:delete', 3, 4, 216279, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216284, '导出', 'lghjft:xejf-xejfhbsbyxg:export', 3, 5, 216279, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 6. 查询统计 cxtj (214750-214899) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214750, '查询统计', '', 1, 6, 0, '/cxtj', 'ep:data-analysis', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214751, '经费明细', '', 2, 1, 214750, 'jfmx', '', '/lghjft/cxtj/jfmx/index', 'LghjftCxtjJfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214752, '查询', 'lghjft:cxtj-jfmx:query', 3, 1, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214753, '新增', 'lghjft:cxtj-jfmx:create', 3, 2, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214754, '修改', 'lghjft:cxtj-jfmx:update', 3, 3, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214755, '删除', 'lghjft:cxtj-jfmx:delete', 3, 4, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214756, '导出', 'lghjft:cxtj-jfmx:export', 3, 5, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214757, '经费台账', '', 2, 2, 214750, 'dhjftz', '', '/lghjft/cxtj/dhjftz/index', 'LghjftCxtjDhjftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214758, '查询', 'lghjft:cxtj-dhjftz:query', 3, 1, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214759, '新增', 'lghjft:cxtj-dhjftz:create', 3, 2, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214760, '修改', 'lghjft:cxtj-dhjftz:update', 3, 3, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214761, '删除', 'lghjft:cxtj-dhjftz:delete', 3, 4, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214762, '导出', 'lghjft:cxtj-dhjftz:export', 3, 5, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分月统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214763, '分月统计', '', 2, 3, 214750, 'jffymx', '', '/lghjft/cxtj/jffymx/index', 'LghjftCxtjJffymx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214764, '查询', 'lghjft:cxtj-jffymx:query', 3, 1, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214765, '新增', 'lghjft:cxtj-jffymx:create', 3, 2, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214766, '修改', 'lghjft:cxtj-jffymx:update', 3, 3, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214767, '删除', 'lghjft:cxtj-jffymx:delete', 3, 4, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214768, '导出', 'lghjft:cxtj-jffymx:export', 3, 5, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分年统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214769, '分年统计', '', 2, 4, 214750, 'jffnmx', '', '/lghjft/cxtj/jffnmx/index', 'LghjftCxtjJffnmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214770, '查询', 'lghjft:cxtj-jffnmx:query', 3, 1, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214771, '新增', 'lghjft:cxtj-jffnmx:create', 3, 2, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214772, '修改', 'lghjft:cxtj-jffnmx:update', 3, 3, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214773, '删除', 'lghjft:cxtj-jffnmx:delete', 3, 4, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214774, '导出', 'lghjft:cxtj-jffnmx:export', 3, 5, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费台账分年
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214775, '经费台账分年', '', 2, 5, 214750, 'jftzfn', '', '/lghjft/cxtj/jftzfn/index', 'LghjftCxtjJftzfn', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214776, '查询', 'lghjft:cxtj-jftzfn:query', 3, 1, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214777, '新增', 'lghjft:cxtj-jftzfn:create', 3, 2, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214778, '修改', 'lghjft:cxtj-jftzfn:update', 3, 3, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214779, '删除', 'lghjft:cxtj-jftzfn:delete', 3, 4, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214780, '导出', 'lghjft:cxtj-jftzfn:export', 3, 5, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分税务机关统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214781, '分税务机关统计', '', 2, 6, 214750, 'jftzfswjg', '', '/lghjft/cxtj/jftzfswjg/index', 'LghjftCxtjJftzfswjg', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214782, '查询', 'lghjft:cxtj-jftzfswjg:query', 3, 1, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214783, '新增', 'lghjft:cxtj-jftzfswjg:create', 3, 2, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214784, '修改', 'lghjft:cxtj-jftzfswjg:update', 3, 3, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214785, '删除', 'lghjft:cxtj-jftzfswjg:delete', 3, 4, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214786, '导出', 'lghjft:cxtj-jftzfswjg:export', 3, 5, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分年各级分成情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214787, '分年各级分成情况', '', 2, 7, 214750, 'jffsjzqmx', '', '/lghjft/cxtj/jffsjzqmx/index', 'LghjftCxtjJffsjzqmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214788, '查询', 'lghjft:cxtj-jffsjzqmx:query', 3, 1, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214789, '新增', 'lghjft:cxtj-jffsjzqmx:create', 3, 2, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214790, '修改', 'lghjft:cxtj-jffsjzqmx:update', 3, 3, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214791, '删除', 'lghjft:cxtj-jffsjzqmx:delete', 3, 4, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214792, '导出', 'lghjft:cxtj-jffsjzqmx:export', 3, 5, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分上缴周期统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214793, '分上缴周期统计', '', 2, 8, 214750, 'ndrwwc', '', '/lghjft/cxtj/ndrwwc/index', 'LghjftCxtjNdrwwc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214794, '查询', 'lghjft:cxtj-ndrwwc:query', 3, 1, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214795, '新增', 'lghjft:cxtj-ndrwwc:create', 3, 2, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214796, '修改', 'lghjft:cxtj-ndrwwc:update', 3, 3, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214797, '删除', 'lghjft:cxtj-ndrwwc:delete', 3, 4, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214798, '导出', 'lghjft:cxtj-ndrwwc:export', 3, 5, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 缴费排行
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214799, '缴费排行', '', 2, 9, 214750, 'top', '', '/lghjft/cxtj/top/index', 'LghjftCxtjTop', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214800, '查询', 'lghjft:cxtj-top:query', 3, 1, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214801, '新增', 'lghjft:cxtj-top:create', 3, 2, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214802, '修改', 'lghjft:cxtj-top:update', 3, 3, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214803, '删除', 'lghjft:cxtj-top:delete', 3, 4, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214804, '导出', 'lghjft:cxtj-top:export', 3, 5, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 近三年缴费情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214805, '近三年缴费情况', '', 2, 10, 214750, 'ghjfjfdw', '', '/lghjft/cxtj/ghjfjfdw/index', 'LghjftCxtjGhjfjfdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214806, '查询', 'lghjft:cxtj-ghjfjfdw:query', 3, 1, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214807, '新增', 'lghjft:cxtj-ghjfjfdw:create', 3, 2, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214808, '修改', 'lghjft:cxtj-ghjfjfdw:update', 3, 3, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214809, '删除', 'lghjft:cxtj-ghjfjfdw:delete', 3, 4, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214810, '导出', 'lghjft:cxtj-ghjfjfdw:export', 3, 5, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 划拨失败记录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214811, '划拨失败记录', '', 2, 11, 214750, 'hbsbjl', '', '/lghjft/cxtj/hbsbjl/index', 'LghjftCxtjHbsbjl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214812, '查询', 'lghjft:cxtj-hbsbjl:query', 3, 1, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214813, '新增', 'lghjft:cxtj-hbsbjl:create', 3, 2, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214814, '修改', 'lghjft:cxtj-hbsbjl:update', 3, 3, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214815, '删除', 'lghjft:cxtj-hbsbjl:delete', 3, 4, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214816, '导出', 'lghjft:cxtj-hbsbjl:export', 3, 5, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 划拨失败已修改
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214817, '划拨失败已修改', '', 2, 12, 214750, 'hbsbjlyxg', '', '/lghjft/cxtj/hbsbjlyxg/index', 'LghjftCxtjHbsbjlyxg', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214818, '查询', 'lghjft:cxtj-hbsbjlyxg:query', 3, 1, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214819, '新增', 'lghjft:cxtj-hbsbjlyxg:create', 3, 2, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214820, '修改', 'lghjft:cxtj-hbsbjlyxg:update', 3, 3, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214821, '删除', 'lghjft:cxtj-hbsbjlyxg:delete', 3, 4, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214822, '导出', 'lghjft:cxtj-hbsbjlyxg:export', 3, 5, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 已建会信息(年报)
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214823, '已建会信息(年报)', '', 2, 13, 214750, 'yjhxx', '', '/lghjft/cxtj/yjhxx/index', 'LghjftCxtjYjhxx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214824, '查询', 'lghjft:cxtj-yjhxx:query', 3, 1, 214823, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214825, '新增', 'lghjft:cxtj-yjhxx:create', 3, 2, 214823, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214826, '修改', 'lghjft:cxtj-yjhxx:update', 3, 3, 214823, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214827, '删除', 'lghjft:cxtj-yjhxx:delete', 3, 4, 214823, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214828, '导出', 'lghjft:cxtj-yjhxx:export', 3, 5, 214823, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 金融工会信息核对
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214829, '金融工会信息核对', '', 2, 14, 214750, 'zgjrgh', '', '/lghjft/cxtj/zgjrgh/index', 'LghjftCxtjZgjrgh', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214830, '查询', 'lghjft:cxtj-zgjrgh:query', 3, 1, 214829, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214831, '新增', 'lghjft:cxtj-zgjrgh:create', 3, 2, 214829, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214832, '修改', 'lghjft:cxtj-zgjrgh:update', 3, 3, 214829, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214833, '删除', 'lghjft:cxtj-zgjrgh:delete', 3, 4, 214829, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214834, '导出', 'lghjft:cxtj-zgjrgh:export', 3, 5, 214829, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 金融保险证券单位
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214835, '金融保险证券单位', '', 2, 15, 214750, 'jrbxzqdw', '', '/lghjft/cxtj/jrbxzqdw/index', 'LghjftCxtjJrbxzqdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214836, '查询', 'lghjft:cxtj-jrbxzqdw:query', 3, 1, 214835, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214837, '新增', 'lghjft:cxtj-jrbxzqdw:create', 3, 2, 214835, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214838, '修改', 'lghjft:cxtj-jrbxzqdw:update', 3, 3, 214835, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214839, '删除', 'lghjft:cxtj-jrbxzqdw:delete', 3, 4, 214835, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214840, '导出', 'lghjft:cxtj-jrbxzqdw:export', 3, 5, 214835, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 代收情况(入库日期)
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214841, '代收情况(入库日期)', '', 2, 16, 214750, 'fydsqk', '', '/lghjft/cxtj/fydsqk/index', 'LghjftCxtjFydsqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214842, '查询', 'lghjft:cxtj-fydsqk:query', 3, 1, 214841, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214843, '新增', 'lghjft:cxtj-fydsqk:create', 3, 2, 214841, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214844, '修改', 'lghjft:cxtj-fydsqk:update', 3, 3, 214841, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214845, '删除', 'lghjft:cxtj-fydsqk:delete', 3, 4, 214841, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214846, '导出', 'lghjft:cxtj-fydsqk:export', 3, 5, 214841, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分月代收情况(入库)
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214847, '分月代收情况(入库)', '', 2, 17, 214750, 'szjffydsqk', '', '/lghjft/cxtj/szjffydsqk/index', 'LghjftCxtjSzjffydsqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214848, '查询', 'lghjft:cxtj-szjffydsqk:query', 3, 1, 214847, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214849, '新增', 'lghjft:cxtj-szjffydsqk:create', 3, 2, 214847, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214850, '修改', 'lghjft:cxtj-szjffydsqk:update', 3, 3, 214847, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214851, '删除', 'lghjft:cxtj-szjffydsqk:delete', 3, 4, 214847, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214852, '导出', 'lghjft:cxtj-szjffydsqk:export', 3, 5, 214847, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 征收未主管单位
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214853, '征收未主管单位', '', 2, 18, 214750, 'zswzgdw', '', '/lghjft/cxtj/zswzgdw/index', 'LghjftCxtjZswzgdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214854, '查询', 'lghjft:cxtj-zswzgdw:query', 3, 1, 214853, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214855, '新增', 'lghjft:cxtj-zswzgdw:create', 3, 2, 214853, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214856, '修改', 'lghjft:cxtj-zswzgdw:update', 3, 3, 214853, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214857, '删除', 'lghjft:cxtj-zswzgdw:delete', 3, 4, 214853, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214858, '导出', 'lghjft:cxtj-zswzgdw:export', 3, 5, 214853, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分成情况(入库日期)
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214859, '分成情况(入库日期)', '', 2, 19, 214750, 'fyfcqk', '', '/lghjft/cxtj/fyfcqk/index', 'LghjftCxtjFyfcqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214860, '查询', 'lghjft:cxtj-fyfcqk:query', 3, 1, 214859, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214861, '新增', 'lghjft:cxtj-fyfcqk:create', 3, 2, 214859, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214862, '修改', 'lghjft:cxtj-fyfcqk:update', 3, 3, 214859, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214863, '删除', 'lghjft:cxtj-fyfcqk:delete', 3, 4, 214859, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214864, '导出', 'lghjft:cxtj-fyfcqk:export', 3, 5, 214859, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- v2 extras: 操作日志
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214865, '操作日志', '', 2, 20, 214750, 'czrj', '', '/lghjft/cxtj/czrj/index', 'LghjftCxtjCzrj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214866, '查询', 'lghjft:cxtj-czrj:query', 3, 1, 214865, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214867, '新增', 'lghjft:cxtj-czrj:create', 3, 2, 214865, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214868, '修改', 'lghjft:cxtj-czrj:update', 3, 3, 214865, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214869, '删除', 'lghjft:cxtj-czrj:delete', 3, 4, 214865, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214870, '导出', 'lghjft:cxtj-czrj:export', 3, 5, 214865, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 首页汇总信息
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214871, '首页汇总信息', '', 2, 21, 214750, 'syhzxx', '', '/lghjft/cxtj/syhzxx/index', 'LghjftCxtjSyhzxx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214872, '查询', 'lghjft:cxtj-syhzxx:query', 3, 1, 214871, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214873, '新增', 'lghjft:cxtj-syhzxx:create', 3, 2, 214871, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214874, '修改', 'lghjft:cxtj-syhzxx:update', 3, 3, 214871, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214875, '删除', 'lghjft:cxtj-syhzxx:delete', 3, 4, 214871, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214876, '导出', 'lghjft:cxtj-syhzxx:export', 3, 5, 214871, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 首页汇总信息分月
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214877, '首页汇总信息分月', '', 2, 22, 214750, 'syhzxxfy', '', '/lghjft/cxtj/syhzxxfy/index', 'LghjftCxtjSyhzxxfy', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214878, '查询', 'lghjft:cxtj-syhzxxfy:query', 3, 1, 214877, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214879, '新增', 'lghjft:cxtj-syhzxxfy:create', 3, 2, 214877, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214880, '修改', 'lghjft:cxtj-syhzxxfy:update', 3, 3, 214877, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214881, '删除', 'lghjft:cxtj-syhzxxfy:delete', 3, 4, 214877, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214882, '导出', 'lghjft:cxtj-syhzxxfy:export', 3, 5, 214877, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 近一周缴费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214883, '近一周缴费明细', '', 2, 23, 214750, 'dpzsjyzjfmx', '', '/lghjft/cxtj/dpzsjyzjfmx/index', 'LghjftCxtjDpzsjyzjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214884, '查询', 'lghjft:cxtj-dpzsjyzjfmx:query', 3, 1, 214883, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214885, '新增', 'lghjft:cxtj-dpzsjyzjfmx:create', 3, 2, 214883, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214886, '修改', 'lghjft:cxtj-dpzsjyzjfmx:update', 3, 3, 214883, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214887, '删除', 'lghjft:cxtj-dpzsjyzjfmx:delete', 3, 4, 214883, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214888, '导出', 'lghjft:cxtj-dpzsjyzjfmx:export', 3, 5, 214883, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业经费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214889, '小微企业经费明细', '', 2, 24, 214750, 'xwqyjfmx', '', '/lghjft/cxtj/xwqyjfmx/index', 'LghjftCxtjXwqyjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214890, '查询', 'lghjft:cxtj-xwqyjfmx:query', 3, 1, 214889, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214891, '新增', 'lghjft:cxtj-xwqyjfmx:create', 3, 2, 214889, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214892, '修改', 'lghjft:cxtj-xwqyjfmx:update', 3, 3, 214889, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214893, '删除', 'lghjft:cxtj-xwqyjfmx:delete', 3, 4, 214889, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214894, '导出', 'lghjft:cxtj-xwqyjfmx:export', 3, 5, 214889, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 7. 经费审验 jfsy (216400-216419) =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216400, '经费审验', '', 1, 7, 0, '/jfsy', 'ep:document-checked', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 工资总额查询
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216401, '工资总额查询', '', 2, 1, 216400, 'gzze', '', '/lghjft/jfsy/gzze/index', 'LghjftJfsyGzze', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216402, '查询', 'lghjft:jfsy-gzze:query', 3, 1, 216401, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216403, '导出', 'lghjft:jfsy-gzze:export', 3, 2, 216401, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 8. 角色菜单关联（role_id=1 为超级管理员） =====================
-- hbzz 215100-215166
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
       (1, 215110, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215111, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215112, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215113, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215114, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215115, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215116, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215117, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215118, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215119, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215120, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215121, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215122, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215123, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215124, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215125, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215126, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215127, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215128, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215129, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215130, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215131, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215132, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215133, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215134, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215135, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215136, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215137, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215138, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215139, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215140, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215141, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215142, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215143, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215144, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215145, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215146, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215147, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215148, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215149, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215150, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215151, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215152, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215153, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215154, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215155, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215156, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215157, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215158, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215159, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215160, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215161, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215162, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215163, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215164, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215165, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215166, '1', NOW(), '1', NOW(), 0, 1);

-- ghcbj 216300-216330
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 216300, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216301, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216302, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216303, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216304, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216305, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216306, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216307, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216308, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216309, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216310, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216311, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216312, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216313, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216314, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216315, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216316, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216317, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216318, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216319, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216320, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216321, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216322, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216323, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216324, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216325, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216326, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216327, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216328, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216329, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216330, '1', NOW(), '1', NOW(), 0, 1);

-- xwqy 214700-214724
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 214700, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214701, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214702, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214703, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214704, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214705, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214706, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214707, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214708, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214709, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214710, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214711, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214712, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214713, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214714, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214715, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216716, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214717, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214718, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214719, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214720, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214721, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214722, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214723, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214724, '1', NOW(), '1', NOW(), 0, 1);

-- xejf 216200-216284
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 216200, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216201, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216202, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216203, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216204, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216205, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216206, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216207, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216208, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216209, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216210, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216211, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216212, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216213, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216214, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216215, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216216, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216217, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216218, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216219, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216220, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216221, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216222, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216223, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216224, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216225, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216226, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216227, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216228, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216229, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216230, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216231, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216232, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216233, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216234, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216235, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216236, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216237, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216238, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216239, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216240, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216241, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216242, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216243, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216244, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216245, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216246, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216247, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216248, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216249, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216250, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216251, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216252, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216253, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216254, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216255, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216256, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216257, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216258, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216259, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216260, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216261, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216262, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216263, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216264, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216265, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216266, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216267, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216268, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216269, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216270, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216271, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216272, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216273, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216274, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216275, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216276, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216277, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216278, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216279, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216280, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216281, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216282, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216283, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216284, '1', NOW(), '1', NOW(), 0, 1);

-- cxtj 214750-214894
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 214750, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214751, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214752, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214753, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214754, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214755, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214756, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214757, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214758, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214759, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214760, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214761, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214762, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214763, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214764, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214765, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214766, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214767, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214768, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214769, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214770, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214771, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214772, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214773, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214774, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214775, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214776, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214777, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214778, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214779, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214780, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214781, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214782, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214783, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214784, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214785, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214786, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214787, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214788, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214789, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214790, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214791, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214792, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214793, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214794, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214795, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214796, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214797, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214798, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214799, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214800, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214801, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214802, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214803, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214804, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214805, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214806, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214807, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214808, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214809, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214810, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214811, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214812, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214813, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214814, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214815, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214816, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214817, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214818, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214819, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214820, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214821, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214822, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214823, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214824, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214825, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214826, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214827, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214828, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214829, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214830, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214831, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214832, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214833, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214834, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214835, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214836, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214837, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214838, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214839, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214840, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214841, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214842, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214843, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214844, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214845, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214846, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214847, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214848, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214849, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214850, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214851, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214852, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214853, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214854, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214855, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214856, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214857, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214858, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214859, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214860, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214861, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214862, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214863, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214864, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214865, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214866, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214867, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214868, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214869, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214870, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214871, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214872, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214873, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214874, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214875, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214876, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214877, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214878, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214879, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214880, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214881, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214882, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214883, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214884, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214885, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214886, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214887, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214888, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214889, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214890, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214891, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214892, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214893, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214894, '1', NOW(), '1', NOW(), 0, 1);

-- jfsy 216400-216403
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 216400, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216401, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216402, '1', NOW(), '1', NOW(), 0, 1),
       (1, 216403, '1', NOW(), '1', NOW(), 0, 1);

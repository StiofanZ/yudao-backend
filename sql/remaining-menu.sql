-- ============================================================
-- 剩余模块菜单 SQL（sjwh, xejf, xwqy, hjgl, qx, workflow, nrgl, xxzx, ghcbj）
-- 可直接在线上数据库执行
-- ============================================================

-- ============================================================
-- 防重复：先删除将要使用的 ID 范围
-- ============================================================
DELETE FROM system_role_menu WHERE menu_id BETWEEN 216100 AND 216599;
DELETE FROM system_menu WHERE id BETWEEN 216100 AND 216599;

-- 清除旧 xwqy 条目 214700-214749，重新创建
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214700 AND 214749;
DELETE FROM system_menu WHERE id BETWEEN 214700 AND 214749;

-- ============================================================
-- 一、数据维护 sjwh（parent_id = 205047 已存在）
-- 已有子菜单: dmwh(205075), rws(213602), jhdwyds(213652),
--   bfzhpc(206365), swrksj(214980), wjgl(215200),
--   jczhkxwh(214990), xwqyglcbj(214996)
-- 需新增子菜单:
--   cbjzz, jfbfmx, cbjtz, dgjftz, cbjxc, ghjfzz, znjzz,
--   sxfzz, fjcjfzz, cbjqfmx, szqzjzdc, jcjfzz, cbjqf, znjtz,
--   hkxxbfzhpc, szdzhd
-- ============================================================

-- ---------- 筹备金做账 cbjzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216100, '筹备金做账', '', 2, 25, 205047, 'cbjzz', '', 'lghjft/sjwh/cbjzz/index', 'SjwhCbjzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216101, '查询', 'lghjft:sjwh-cbjzz:query', 3, 1, 216100, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216102, '新增', 'lghjft:sjwh-cbjzz:create', 3, 2, 216100, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216103, '修改', 'lghjft:sjwh-cbjzz:update', 3, 3, 216100, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216104, '删除', 'lghjft:sjwh-cbjzz:delete', 3, 4, 216100, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216105, '导出', 'lghjft:sjwh-cbjzz:export', 3, 5, 216100, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 经费拨付明细 jfbfmx ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216106, '经费拨付明细', '', 2, 26, 205047, 'jfbfmx', '', 'lghjft/sjwh/jfbfmx/index', 'SjwhJfbfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216107, '查询', 'lghjft:sjwh-jfbfmx:query', 3, 1, 216106, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216108, '新增', 'lghjft:sjwh-jfbfmx:create', 3, 2, 216106, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216109, '修改', 'lghjft:sjwh-jfbfmx:update', 3, 3, 216106, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216110, '删除', 'lghjft:sjwh-jfbfmx:delete', 3, 4, 216106, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216111, '导出', 'lghjft:sjwh-jfbfmx:export', 3, 5, 216106, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 筹备金台账 cbjtz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216112, '筹备金台账', '', 2, 27, 205047, 'cbjtz', '', 'lghjft/sjwh/cbjtz/index', 'SjwhCbjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216113, '查询', 'lghjft:sjwh-cbjtz:query', 3, 1, 216112, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216114, '新增', 'lghjft:sjwh-cbjtz:create', 3, 2, 216112, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216115, '修改', 'lghjft:sjwh-cbjtz:update', 3, 3, 216112, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216116, '删除', 'lghjft:sjwh-cbjtz:delete', 3, 4, 216112, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216117, '导出', 'lghjft:sjwh-cbjtz:export', 3, 5, 216112, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 单位经费台账 dgjftz (uses cbjtz API) ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216118, '单位经费台账', '', 2, 28, 205047, 'dgjftz', '', 'lghjft/sjwh/dgjftz/index', 'SjwhDgjftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216119, '查询', 'lghjft:sjwh-cbjtz:query', 3, 1, 216118, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216120, '导出', 'lghjft:sjwh-cbjtz:export', 3, 2, 216118, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 筹备金修正 cbjxc (uses cbjtz API) ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216121, '筹备金修正', '', 2, 29, 205047, 'cbjxc', '', 'lghjft/sjwh/cbjxc/index', 'SjwhCbjxc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216122, '查询', 'lghjft:sjwh-cbjtz:query', 3, 1, 216121, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216123, '修改', 'lghjft:sjwh-cbjtz:update', 3, 2, 216121, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216124, '导出', 'lghjft:sjwh-cbjtz:export', 3, 3, 216121, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 工会经费做账 ghjfzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216125, '工会经费做账', '', 2, 30, 205047, 'ghjfzz', '', 'lghjft/sjwh/ghjfzz/index', 'SjwhGhjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216126, '查询', 'lghjft:sjwh-ghjfzz:query', 3, 1, 216125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216127, '新增', 'lghjft:sjwh-ghjfzz:create', 3, 2, 216125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216128, '修改', 'lghjft:sjwh-ghjfzz:update', 3, 3, 216125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216129, '删除', 'lghjft:sjwh-ghjfzz:delete', 3, 4, 216125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216130, '导出', 'lghjft:sjwh-ghjfzz:export', 3, 5, 216125, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 滞纳金做账 znjzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216131, '滞纳金做账', '', 2, 31, 205047, 'znjzz', '', 'lghjft/sjwh/znjzz/index', 'SjwhZnjzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216132, '查询', 'lghjft:sjwh-znjzz:query', 3, 1, 216131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216133, '新增', 'lghjft:sjwh-znjzz:create', 3, 2, 216131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216134, '修改', 'lghjft:sjwh-znjzz:update', 3, 3, 216131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216135, '删除', 'lghjft:sjwh-znjzz:delete', 3, 4, 216131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216136, '导出', 'lghjft:sjwh-znjzz:export', 3, 5, 216131, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 手续费做账 sxfzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216137, '手续费做账', '', 2, 32, 205047, 'sxfzz', '', 'lghjft/sjwh/sxfzz/index', 'SjwhSxfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216138, '查询', 'lghjft:sjwh-sxfzz:query', 3, 1, 216137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216139, '新增', 'lghjft:sjwh-sxfzz:create', 3, 2, 216137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216140, '修改', 'lghjft:sjwh-sxfzz:update', 3, 3, 216137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216141, '删除', 'lghjft:sjwh-sxfzz:delete', 3, 4, 216137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216142, '导出', 'lghjft:sjwh-sxfzz:export', 3, 5, 216137, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 返还经费拨付明细 hkxxbfzhpc ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216143, '拨付经费排除解除', '', 2, 33, 205047, 'hkxxbfzhpc', '', 'lghjft/sjwh/hkxxbfzhpc/index', 'Bfjfpcjc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216144, '查询', 'lghjft:sjwh-bfzhpc:query', 3, 1, 216143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216145, '修改', 'lghjft:sjwh-bfzhpc:update', 3, 2, 216143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216146, '导出', 'lghjft:sjwh-bfzhpc:export', 3, 3, 216143, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 非基层经费做账 fjcjfzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216147, '非基层经费做账', '', 2, 34, 205047, 'fjcjfzz', '', 'lghjft/sjwh/fjcjfzz/index', 'SjwhFjcjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216148, '查询', 'lghjft:sjwh-fjcjfzz:query', 3, 1, 216147, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216149, '新增', 'lghjft:sjwh-fjcjfzz:create', 3, 2, 216147, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216150, '修改', 'lghjft:sjwh-fjcjfzz:update', 3, 3, 216147, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216151, '删除', 'lghjft:sjwh-fjcjfzz:delete', 3, 4, 216147, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216152, '导出', 'lghjft:sjwh-fjcjfzz:export', 3, 5, 216147, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 收支对账核对 szdzhd ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216153, '收支对账核对', '', 2, 35, 205047, 'szdzhd', '', 'lghjft/sjwh/szdzhd/index', 'SjwhSzdzhd', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216154, '查询', 'lghjft:sjwh-cbjtz:query', 3, 1, 216153, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216155, '导出', 'lghjft:sjwh-cbjtz:export', 3, 2, 216153, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 筹备金全返明细 cbjqfmx ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216156, '筹备金全返明细', '', 2, 36, 205047, 'cbjqfmx', '', 'lghjft/sjwh/cbjqfmx/index', 'SjwhCbjqfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216157, '查询', 'lghjft:sjwh-cbjqfmx:query', 3, 1, 216156, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216158, '新增', 'lghjft:sjwh-cbjqfmx:create', 3, 2, 216156, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216159, '修改', 'lghjft:sjwh-cbjqfmx:update', 3, 3, 216156, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216160, '删除', 'lghjft:sjwh-cbjqfmx:delete', 3, 4, 216156, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216161, '导出', 'lghjft:sjwh-cbjqfmx:export', 3, 5, 216156, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 市州欠账减贷抵查 szqzjzdc ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216162, '市州欠账减贷抵查', '', 2, 37, 205047, 'szqzjzdc', '', 'lghjft/sjwh/szqzjzdc/index', 'SjwhSzqzjzdc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216163, '查询', 'lghjft:sjwh-szqzjzdc:query', 3, 1, 216162, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216164, '导出', 'lghjft:sjwh-szqzjzdc:export', 3, 2, 216162, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 基层经费做账 jcjfzz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216165, '基层经费做账', '', 2, 38, 205047, 'jcjfzz', '', 'lghjft/sjwh/jcjfzz/index', 'SjwhJcjfzz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216166, '查询', 'lghjft:sjwh-jcjfzz:query', 3, 1, 216165, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216167, '新增', 'lghjft:sjwh-jcjfzz:create', 3, 2, 216165, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216168, '修改', 'lghjft:sjwh-jcjfzz:update', 3, 3, 216165, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216169, '删除', 'lghjft:sjwh-jcjfzz:delete', 3, 4, 216165, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216170, '导出', 'lghjft:sjwh-jcjfzz:export', 3, 5, 216165, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 筹备金全返 cbjqf ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216171, '筹备金全返', '', 2, 39, 205047, 'cbjqf', '', 'lghjft/sjwh/cbjqf/index', 'SjwhCbjqf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216172, '查询', 'lghjft:sjwh-cbjqf:query', 3, 1, 216171, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216173, '新增', 'lghjft:sjwh-cbjqf:create', 3, 2, 216171, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216174, '修改', 'lghjft:sjwh-cbjqf:update', 3, 3, 216171, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216175, '删除', 'lghjft:sjwh-cbjqf:delete', 3, 4, 216171, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216176, '导出', 'lghjft:sjwh-cbjqf:export', 3, 5, 216171, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 滞纳金台账 znjtz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216177, '滞纳金台账', '', 2, 40, 205047, 'znjtz', '', 'lghjft/sjwh/znjtz/index', 'SjwhZnjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216178, '查询', 'lghjft:sjwh-znjtz:query', 3, 1, 216177, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216179, '新增', 'lghjft:sjwh-znjtz:create', 3, 2, 216177, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216180, '修改', 'lghjft:sjwh-znjtz:update', 3, 3, 216177, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216181, '删除', 'lghjft:sjwh-znjtz:delete', 3, 4, 216177, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216182, '导出', 'lghjft:sjwh-znjtz:export', 3, 5, 216177, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ============================================================
-- 二、小额经费 xejf（新建一级目录）
-- ============================================================
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216200, '小额经费', '', 1, 8, 0, '/xejf', 'ep:wallet', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额拨付台账 xebftz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216201, '小额拨付台账', '', 2, 1, 216200, 'xebftz', '', 'lghjft/xejf/xebftz/index', 'Xebftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216202, '查询', 'lghjft:xejf-xebftz:query', 3, 1, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216203, '导出', 'lghjft:xejf-xebftz:export', 3, 2, 216201, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额经费(历史) xejfold ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216204, '小额经费(历史)', '', 2, 2, 216200, 'xejfold', '', 'lghjft/xejf/xejfold/index', 'Xejfold', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216205, '查询', 'lghjft:xejf-xejfold:query', 3, 1, 216204, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216206, '新增', 'lghjft:xejf-xejfold:create', 3, 2, 216204, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216207, '修改', 'lghjft:xejf-xejfold:update', 3, 3, 216204, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216208, '删除', 'lghjft:xejf-xejfold:delete', 3, 4, 216204, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 返还小额经费筹备金 hkxxxejfcbj ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216209, '返还小额经费筹备金', '', 2, 3, 216200, 'hkxxxejfcbj', '', 'lghjft/xejf/hkxxxejfcbj/index', 'Hkxxxejfcbj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216210, '查询', 'lghjft:xejf-hkxxxejfcbj:query', 3, 1, 216209, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216211, '新增', 'lghjft:xejf-hkxxxejfcbj:create', 3, 2, 216209, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216212, '修改', 'lghjft:xejf-hkxxxejfcbj:update', 3, 3, 216209, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216213, '删除', 'lghjft:xejf-hkxxxejfcbj:delete', 3, 4, 216209, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额经费(24年起) xejf24 ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216214, '小额经费(24年起)', '', 2, 4, 216200, 'xejf24', '', 'lghjft/xejf/xejf24/index', 'Xejf24', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216215, '查询', 'lghjft:xejf-xejf24:query', 3, 1, 216214, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216216, '新增', 'lghjft:xejf-xejf24:create', 3, 2, 216214, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216217, '修改', 'lghjft:xejf-xejf24:update', 3, 3, 216214, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216218, '删除', 'lghjft:xejf-xejf24:delete', 3, 4, 216214, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 返还小额经费基层对账 hkxxxejfjcdz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216219, '返还小额经费基层对账', '', 2, 5, 216200, 'hkxxxejfjcdz', '', 'lghjft/xejf/hkxxxejfjcdz/index', 'Hkxxxejfjcdz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216220, '查询', 'lghjft:xejf-hkxxxejfjcdz:query', 3, 1, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216221, '新增', 'lghjft:xejf-hkxxxejfjcdz:create', 3, 2, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216222, '修改', 'lghjft:xejf-hkxxxejfjcdz:update', 3, 3, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216223, '删除', 'lghjft:xejf-hkxxxejfjcdz:delete', 3, 4, 216219, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 返还小额经费 hkxxxejf ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216224, '返还小额经费', '', 2, 6, 216200, 'hkxxxejf', '', 'lghjft/xejf/hkxxxejf/index', 'Hkxxxejf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216225, '查询', 'lghjft:xejf-hkxxxejf:query', 3, 1, 216224, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216226, '新增', 'lghjft:xejf-hkxxxejf:create', 3, 2, 216224, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216227, '修改', 'lghjft:xejf-hkxxxejf:update', 3, 3, 216224, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216228, '删除', 'lghjft:xejf-hkxxxejf:delete', 3, 4, 216224, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额经费做账管理 xejfzzgl ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216229, '小额经费做账管理', '', 2, 7, 216200, 'xejfzzgl', '', 'lghjft/xejf/xejfzzgl/index', 'Xejfzzgl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216230, '查询', 'lghjft:xejf-xejfzzgl:query', 3, 1, 216229, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216231, '新增', 'lghjft:xejf-xejfzzgl:create', 3, 2, 216229, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216232, '修改', 'lghjft:xejf-xejfzzgl:update', 3, 3, 216229, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216233, '删除', 'lghjft:xejf-xejfzzgl:delete', 3, 4, 216229, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额台账 xetz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216234, '小额台账', '', 2, 8, 216200, 'xetz', '', 'lghjft/xejf/xetz/index', 'Xetz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216235, '查询', 'lghjft:xejf-xetz:query', 3, 1, 216234, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216236, '导出', 'lghjft:xejf-xetz:export', 3, 2, 216234, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额经费(2023) xejf2023 ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216237, '小额经费(2023)', '', 2, 9, 216200, 'xejf2023', '', 'lghjft/xejf/xejf2023/index', 'Xejf2023', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216238, '查询', 'lghjft:xejf-xejf2023:query', 3, 1, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216239, '新增', 'lghjft:xejf-xejf2023:create', 3, 2, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216240, '修改', 'lghjft:xejf-xejf2023:update', 3, 3, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216241, '删除', 'lghjft:xejf-xejf2023:delete', 3, 4, 216237, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 小额拨付总表 xebfzb ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216242, '小额拨付总表', '', 2, 10, 216200, 'xebfzb', '', 'lghjft/xejf/xebfzb/index', 'Xebfzb', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216243, '查询', 'lghjft:xejf-xebfzb:query', 3, 1, 216242, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216244, '新增', 'lghjft:xejf-xebfzb:create', 3, 2, 216242, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216245, '修改', 'lghjft:xejf-xebfzb:update', 3, 3, 216242, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216246, '删除', 'lghjft:xejf-xebfzb:delete', 3, 4, 216242, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ============================================================
-- 三、小微台账 xwqy（重建 214700-214709）
-- ============================================================
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214700, '小微台账', '', 1, 5, 0, '/xwqy', 'ep:data-board', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 企业管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214701, '企业管理', '', 2, 1, 214700, 'xwqygl', '', 'lghjft/xwqygl/index', 'LghjftXwqyXwqygl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
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

-- 筹备金管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214710, '筹备金管理', '', 2, 2, 214700, 'xwqyglcbj', '', 'lghjft/xwqygl/indexcbj', 'LghjftXwqyXwqyglcbj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214711, '查询', 'lghjft:xwqy-xwqygl:query', 3, 1, 214710, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214712, '修改', 'lghjft:xwqy-xwqygl:update', 3, 2, 214710, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214720, '经费统计', '', 2, 3, 214700, 'xwqyjftj', '', 'lghjft/xwqy/xwqyjftj/index', 'LghjftXwqyXwqyjftj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214721, '查询', 'lghjft:xwqyjftj:query', 3, 1, 214720, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214722, '导出', 'lghjft:xwqyjftj:export', 3, 2, 214720, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微经费明细 (xwqyjf controller)
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214730, '小微经费明细', '', 2, 4, 214700, 'xwqyjf', '', 'lghjft/xwqy/xwqyjftj/index', 'LghjftXwqyXwqyjf', 0, 0, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214731, '查询', 'lghjft:xwqyjf:query', 3, 1, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214732, '新增', 'lghjft:xwqyjf:create', 3, 2, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214733, '修改', 'lghjft:xwqyjf:update', 3, 3, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214734, '删除', 'lghjft:xwqyjf:delete', 3, 4, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214735, '导出', 'lghjft:xwqyjf:export', 3, 5, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ============================================================
-- 四、户籍管理 hjgl（parent_id = 213591 已存在，子菜单已有 jcxx/bqgl/xwxe）
-- 所有子菜单已存在，仅需确认 button permissions
-- ============================================================
-- hjgl 的 button 条目已全部存在（213594-213601, 213667, 214978），跳过

-- ============================================================
-- 五、权限管理 qx（子菜单挂在 200001 系统管理下，已存在）
-- 所有子菜单和 button 已存在（213567-213590, 213668-213680），跳过
-- ============================================================

-- ============================================================
-- 六、流程事项 workflow（parent_id = 213468 已存在）
-- 所有子菜单已存在（213538-213553, 213580-213585, 213687-213692），跳过
-- ============================================================

-- ============================================================
-- 七、内容管理 nrgl（parent_id = 213487 已存在）
-- 所有子菜单已存在（213498-213530, 213562-213566, 213658-213665），跳过
-- ============================================================

-- ============================================================
-- 八、消息中心 xxzx（parent_id = 213481 已存在）
-- 所有子菜单已存在（213482-213508），跳过
-- ============================================================

-- ============================================================
-- 九、工会筹备金 ghcbj（新建一级目录）
-- ============================================================
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216300, '工会筹备金', '', 1, 9, 0, '/ghcbj', 'ep:money', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ---------- 筹备金全量台账 cbjqltz ----------
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216301, '筹备金全量台账', '', 2, 1, 216300, 'cbjqltz', '', 'lghjft/ghcbj/cbjqltz/index', 'GhcbjCbjqltz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216302, '查询', 'lghjft:cbjqltz:query', 3, 1, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216303, '新增', 'lghjft:cbjqltz:create', 3, 2, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216304, '修改', 'lghjft:cbjqltz:update', 3, 3, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216305, '删除', 'lghjft:cbjqltz:delete', 3, 4, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (216306, '导出', 'lghjft:cbjqltz:export', 3, 5, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ============================================================
-- 角色菜单关联（role_id=1 为超级管理员）
-- ============================================================
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
-- sjwh 新增子菜单
(1, 216100, '1', NOW(), '1', NOW(), 0, 1),
(1, 216101, '1', NOW(), '1', NOW(), 0, 1),
(1, 216102, '1', NOW(), '1', NOW(), 0, 1),
(1, 216103, '1', NOW(), '1', NOW(), 0, 1),
(1, 216104, '1', NOW(), '1', NOW(), 0, 1),
(1, 216105, '1', NOW(), '1', NOW(), 0, 1),
(1, 216106, '1', NOW(), '1', NOW(), 0, 1),
(1, 216107, '1', NOW(), '1', NOW(), 0, 1),
(1, 216108, '1', NOW(), '1', NOW(), 0, 1),
(1, 216109, '1', NOW(), '1', NOW(), 0, 1),
(1, 216110, '1', NOW(), '1', NOW(), 0, 1),
(1, 216111, '1', NOW(), '1', NOW(), 0, 1),
(1, 216112, '1', NOW(), '1', NOW(), 0, 1),
(1, 216113, '1', NOW(), '1', NOW(), 0, 1),
(1, 216114, '1', NOW(), '1', NOW(), 0, 1),
(1, 216115, '1', NOW(), '1', NOW(), 0, 1),
(1, 216116, '1', NOW(), '1', NOW(), 0, 1),
(1, 216117, '1', NOW(), '1', NOW(), 0, 1),
(1, 216118, '1', NOW(), '1', NOW(), 0, 1),
(1, 216119, '1', NOW(), '1', NOW(), 0, 1),
(1, 216120, '1', NOW(), '1', NOW(), 0, 1),
(1, 216121, '1', NOW(), '1', NOW(), 0, 1),
(1, 216122, '1', NOW(), '1', NOW(), 0, 1),
(1, 216123, '1', NOW(), '1', NOW(), 0, 1),
(1, 216124, '1', NOW(), '1', NOW(), 0, 1),
(1, 216125, '1', NOW(), '1', NOW(), 0, 1),
(1, 216126, '1', NOW(), '1', NOW(), 0, 1),
(1, 216127, '1', NOW(), '1', NOW(), 0, 1),
(1, 216128, '1', NOW(), '1', NOW(), 0, 1),
(1, 216129, '1', NOW(), '1', NOW(), 0, 1),
(1, 216130, '1', NOW(), '1', NOW(), 0, 1),
(1, 216131, '1', NOW(), '1', NOW(), 0, 1),
(1, 216132, '1', NOW(), '1', NOW(), 0, 1),
(1, 216133, '1', NOW(), '1', NOW(), 0, 1),
(1, 216134, '1', NOW(), '1', NOW(), 0, 1),
(1, 216135, '1', NOW(), '1', NOW(), 0, 1),
(1, 216136, '1', NOW(), '1', NOW(), 0, 1),
(1, 216137, '1', NOW(), '1', NOW(), 0, 1),
(1, 216138, '1', NOW(), '1', NOW(), 0, 1),
(1, 216139, '1', NOW(), '1', NOW(), 0, 1),
(1, 216140, '1', NOW(), '1', NOW(), 0, 1),
(1, 216141, '1', NOW(), '1', NOW(), 0, 1),
(1, 216142, '1', NOW(), '1', NOW(), 0, 1),
(1, 216143, '1', NOW(), '1', NOW(), 0, 1),
(1, 216144, '1', NOW(), '1', NOW(), 0, 1),
(1, 216145, '1', NOW(), '1', NOW(), 0, 1),
(1, 216146, '1', NOW(), '1', NOW(), 0, 1),
(1, 216147, '1', NOW(), '1', NOW(), 0, 1),
(1, 216148, '1', NOW(), '1', NOW(), 0, 1),
(1, 216149, '1', NOW(), '1', NOW(), 0, 1),
(1, 216150, '1', NOW(), '1', NOW(), 0, 1),
(1, 216151, '1', NOW(), '1', NOW(), 0, 1),
(1, 216152, '1', NOW(), '1', NOW(), 0, 1),
(1, 216153, '1', NOW(), '1', NOW(), 0, 1),
(1, 216154, '1', NOW(), '1', NOW(), 0, 1),
(1, 216155, '1', NOW(), '1', NOW(), 0, 1),
(1, 216156, '1', NOW(), '1', NOW(), 0, 1),
(1, 216157, '1', NOW(), '1', NOW(), 0, 1),
(1, 216158, '1', NOW(), '1', NOW(), 0, 1),
(1, 216159, '1', NOW(), '1', NOW(), 0, 1),
(1, 216160, '1', NOW(), '1', NOW(), 0, 1),
(1, 216161, '1', NOW(), '1', NOW(), 0, 1),
(1, 216162, '1', NOW(), '1', NOW(), 0, 1),
(1, 216163, '1', NOW(), '1', NOW(), 0, 1),
(1, 216164, '1', NOW(), '1', NOW(), 0, 1),
(1, 216165, '1', NOW(), '1', NOW(), 0, 1),
(1, 216166, '1', NOW(), '1', NOW(), 0, 1),
(1, 216167, '1', NOW(), '1', NOW(), 0, 1),
(1, 216168, '1', NOW(), '1', NOW(), 0, 1),
(1, 216169, '1', NOW(), '1', NOW(), 0, 1),
(1, 216170, '1', NOW(), '1', NOW(), 0, 1),
(1, 216171, '1', NOW(), '1', NOW(), 0, 1),
(1, 216172, '1', NOW(), '1', NOW(), 0, 1),
(1, 216173, '1', NOW(), '1', NOW(), 0, 1),
(1, 216174, '1', NOW(), '1', NOW(), 0, 1),
(1, 216175, '1', NOW(), '1', NOW(), 0, 1),
(1, 216176, '1', NOW(), '1', NOW(), 0, 1),
(1, 216177, '1', NOW(), '1', NOW(), 0, 1),
(1, 216178, '1', NOW(), '1', NOW(), 0, 1),
(1, 216179, '1', NOW(), '1', NOW(), 0, 1),
(1, 216180, '1', NOW(), '1', NOW(), 0, 1),
(1, 216181, '1', NOW(), '1', NOW(), 0, 1),
(1, 216182, '1', NOW(), '1', NOW(), 0, 1),
-- xejf 模块
(1, 216200, '1', NOW(), '1', NOW(), 0, 1),
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
-- xwqy 重建
(1, 214700, '1', NOW(), '1', NOW(), 0, 1),
(1, 214701, '1', NOW(), '1', NOW(), 0, 1),
(1, 214702, '1', NOW(), '1', NOW(), 0, 1),
(1, 214703, '1', NOW(), '1', NOW(), 0, 1),
(1, 214704, '1', NOW(), '1', NOW(), 0, 1),
(1, 214705, '1', NOW(), '1', NOW(), 0, 1),
(1, 214706, '1', NOW(), '1', NOW(), 0, 1),
(1, 214710, '1', NOW(), '1', NOW(), 0, 1),
(1, 214711, '1', NOW(), '1', NOW(), 0, 1),
(1, 214712, '1', NOW(), '1', NOW(), 0, 1),
(1, 214720, '1', NOW(), '1', NOW(), 0, 1),
(1, 214721, '1', NOW(), '1', NOW(), 0, 1),
(1, 214722, '1', NOW(), '1', NOW(), 0, 1),
(1, 214730, '1', NOW(), '1', NOW(), 0, 1),
(1, 214731, '1', NOW(), '1', NOW(), 0, 1),
(1, 214732, '1', NOW(), '1', NOW(), 0, 1),
(1, 214733, '1', NOW(), '1', NOW(), 0, 1),
(1, 214734, '1', NOW(), '1', NOW(), 0, 1),
(1, 214735, '1', NOW(), '1', NOW(), 0, 1),
-- ghcbj 模块
(1, 216300, '1', NOW(), '1', NOW(), 0, 1),
(1, 216301, '1', NOW(), '1', NOW(), 0, 1),
(1, 216302, '1', NOW(), '1', NOW(), 0, 1),
(1, 216303, '1', NOW(), '1', NOW(), 0, 1),
(1, 216304, '1', NOW(), '1', NOW(), 0, 1),
(1, 216305, '1', NOW(), '1', NOW(), 0, 1),
(1, 216306, '1', NOW(), '1', NOW(), 0, 1);

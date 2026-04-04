-- ============================================================
-- 小微台账 + 查询统计 菜单（可直接在线上数据库执行）
-- ============================================================

-- 防重复：先删除已有记录
DELETE FROM system_menu WHERE id BETWEEN 214700 AND 214749;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214700 AND 214749;

-- ===================== 小微台账 xwqy =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214700, '小微台账', '', 1, 5, 0, '/xwqy', 'ep:data-board', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 企业管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214701, '企业管理', '', 2, 1, 214700, 'xwqygl', 'ep:office-building', '/lghjft/xwqygl/index', 'LghjftXwqyXwqygl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214702, '企业管理查询', 'lghjft:xwqy-xwqygl:query', 3, 1, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214703, '企业管理导出', 'lghjft:xwqy-xwqygl:export', 3, 2, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金管理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214704, '筹备金管理', '', 2, 2, 214700, 'xwqyglcbj', 'ep:money', '/lghjft/xwqygl/indexcbj', 'LghjftXwqyXwqyglcbj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214705, '筹备金管理查询', 'lghjft:xwqy-xwqygl:query', 3, 1, 214704, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214706, '筹备金管理编辑', 'lghjft:xwqy-xwqygl:update', 3, 2, 214704, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费统计
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214707, '经费统计', '', 2, 3, 214700, 'xwqyjftj', 'ep:histogram', '/lghjft/xwqy/xwqyjftj/index', 'LghjftXwqyXwqyjftj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214708, '经费统计查询', 'lghjft:xwqyjftj:query', 3, 1, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214709, '经费统计导出', 'lghjft:xwqyjftj:export', 3, 2, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 查询统计 cxtj =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214710, '查询统计', '', 1, 6, 0, '/cxtj', 'ep:data-analysis', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业经费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214711, '小微企业经费明细', '', 2, 1, 214710, 'xwqyjfmx', 'ep:document', '/lghjft/cxtj/xwqyjfmx/index', 'LghjftCxtjXwqyjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214712, '小微经费明细查询', 'lghjft:xwqyjf:query', 3, 1, 214711, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214713, '小微经费明细导出', 'lghjft:xwqyjf:export', 3, 2, 214711, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 查询统计 - 单户经费台账 dhjftz =====================
-- 单户经费台账（parent: 查询统计 214710）
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214714, '单户经费台账', '', 2, 2, 214710, 'dhjftz', 'ep:house', '/lghjft/cxtj/dhjftz/index', 'LghjftCxtjDhjftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214715, '单户经费台账查询', 'lghjft:dhjftz:query', 3, 1, 214714, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214716, '单户经费台账导出', 'lghjft:dhjftz:export', 3, 2, 214714, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 数据维护 sjwh =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214720, '数据维护', '', 1, 7, 0, '/sjwh', 'ep:setting', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214721, '筹备金台账', '', 2, 1, 214720, 'cbjtz', 'ep:money', '/lghjft/sjwh/cbjtz/index', 'LghjftSjwhCbjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214722, '筹备金台账查询', 'lghjft:cbjtz:query', 3, 1, 214721, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214723, '筹备金台账创建', 'lghjft:cbjtz:create', 3, 2, 214721, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214724, '筹备金台账更新', 'lghjft:cbjtz:update', 3, 3, 214721, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214725, '筹备金台账删除', 'lghjft:cbjtz:delete', 3, 4, 214721, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214726, '筹备金台账导出', 'lghjft:cbjtz:export', 3, 5, 214721, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 滞纳金台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214730, '滞纳金台账', '', 2, 2, 214720, 'znjtz', 'ep:coin', '/lghjft/sjwh/znjtz/index', 'LghjftSjwhZnjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214731, '滞纳金台账查询', 'lghjft:znjtz:query', 3, 1, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214732, '滞纳金台账创建', 'lghjft:znjtz:create', 3, 2, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214733, '滞纳金台账更新', 'lghjft:znjtz:update', 3, 3, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214734, '滞纳金台账删除', 'lghjft:znjtz:delete', 3, 4, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214735, '滞纳金台账导出', 'lghjft:znjtz:export', 3, 5, 214730, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 小额经费 xejf =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214740, '小额经费', '', 1, 8, 0, '/xejf', 'ep:wallet', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214741, '小额台账', '', 2, 1, 214740, 'xetz', 'ep:document', '/lghjft/xejf/xetz/index', 'LghjftXejfXetz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214742, '小额台账查询', 'lghjft:xejf:xetz:query', 3, 1, 214741, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214743, '小额台账导出', 'lghjft:xejf:xetz:export', 3, 2, 214741, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小额拨付台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214744, '小额拨付台账', '', 2, 2, 214740, 'xebftz', 'ep:tickets', '/lghjft/xejf/xebftz/index', 'LghjftXejfXebftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214745, '小额拨付台账查询', 'lghjft:xejf:xebftz:query', 3, 1, 214744, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214746, '小额拨付台账导出', 'lghjft:xejf:xebftz:export', 3, 2, 214744, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 角色菜单关联（role_id=1 为超级管理员） =====================
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
       -- 单户经费台账
       (1, 214714, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214715, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214716, '1', NOW(), '1', NOW(), 0, 1),
       -- 数据维护
       (1, 214720, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214721, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214722, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214723, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214724, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214725, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214726, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214730, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214731, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214732, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214733, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214734, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214735, '1', NOW(), '1', NOW(), 0, 1),
       -- 小额经费
       (1, 214740, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214741, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214742, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214743, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214744, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214745, '1', NOW(), '1', NOW(), 0, 1),
       (1, 214746, '1', NOW(), '1', NOW(), 0, 1);

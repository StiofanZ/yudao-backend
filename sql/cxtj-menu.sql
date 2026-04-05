-- ============================================================
-- 查询统计 菜单（可直接在线上数据库执行）
-- ============================================================

-- 防重复：先删除已有记录
DELETE FROM system_menu WHERE id BETWEEN 214750 AND 214899;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214750 AND 214899;
-- 清理旧的部分条目
DELETE FROM system_menu WHERE id BETWEEN 214710 AND 214749;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 214710 AND 214749;

-- ===================== 查询统计 cxtj =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214750, '查询统计', '', 1, 20, 0, '/cxtj', 'ep:data-analysis', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 缴费排行
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214751, '缴费排行', '', 2, 1, 214750, 'top', '', '/lghjft/cxtj/top/index', 'LghjftCxtjTop', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214752, '查询', 'lghjft:cxtj-top:query', 3, 1, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214753, '导出', 'lghjft:cxtj-top:export', 3, 2, 214751, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分年各级分成情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214754, '分年各级分成情况', '', 2, 2, 214750, 'ndrwwc', '', '/lghjft/cxtj/ndrwwc/index', 'LghjftCxtjNdrwwc', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214755, '查询', 'lghjft:cxtj-ndrwwc:query', 3, 1, 214754, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214756, '导出', 'lghjft:cxtj-ndrwwc:export', 3, 2, 214754, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分月代收情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214757, '分月代收情况', '', 2, 3, 214750, 'fydsqk', '', '/lghjft/cxtj/fydsqk/index', 'LghjftCxtjFydsqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214758, '查询', 'lghjft:cxtj-fydsqk:query', 3, 1, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214759, '导出', 'lghjft:cxtj-fydsqk:export', 3, 2, 214757, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 分月分成情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214760, '分月分成情况', '', 2, 4, 214750, 'fyfcqk', '', '/lghjft/cxtj/fyfcqk/index', 'LghjftCxtjFyfcqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214761, '查询', 'lghjft:cxtj-fyfcqk:query', 3, 1, 214760, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214762, '导出', 'lghjft:cxtj-fyfcqk:export', 3, 2, 214760, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 首页汇总信息
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214763, '首页汇总信息', '', 2, 5, 214750, 'syhzxx', '', '/lghjft/cxtj/syhzxx/index', 'LghjftCxtjSyhzxx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214764, '查询', 'lghjft:cxtj-syhzxx:query', 3, 1, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214765, '导出', 'lghjft:cxtj-syhzxx:export', 3, 2, 214763, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 首页汇总信息分月
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214766, '首页汇总信息分月', '', 2, 6, 214750, 'syhzxxfy', '', '/lghjft/cxtj/syhzxxfy/index', 'LghjftCxtjSyhzxxfy', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214767, '查询', 'lghjft:cxtj-syhzxxfy:query', 3, 1, 214766, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214768, '导出', 'lghjft:cxtj-syhzxxfy:export', 3, 2, 214766, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 金融工会信息核对
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214769, '金融工会信息核对', '', 2, 7, 214750, 'zgjrgh', '', '/lghjft/cxtj/zgjrgh/index', 'LghjftCxtjZgjrgh', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214770, '查询', 'lghjft:cxtj-zgjrgh:query', 3, 1, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214771, '导出', 'lghjft:cxtj-zgjrgh:export', 3, 2, 214769, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 征收未主管单位
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214772, '征收未主管单位', '', 2, 8, 214750, 'zswzgdw', '', '/lghjft/cxtj/zswzgdw/index', 'LghjftCxtjZswzgdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214773, '查询', 'lghjft:cxtj-zswzgdw:query', 3, 1, 214772, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214774, '导出', 'lghjft:cxtj-zswzgdw:export', 3, 2, 214772, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 市州分月代收情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214775, '市州分月代收情况', '', 2, 9, 214750, 'szjffydsqk', '', '/lghjft/cxtj/szjffydsqk/index', 'LghjftCxtjSzjffydsqk', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214776, '查询', 'lghjft:cxtj-szjffydsqk:query', 3, 1, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214777, '导出', 'lghjft:cxtj-szjffydsqk:export', 3, 2, 214775, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 近一周缴费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214778, '近一周缴费明细', '', 2, 10, 214750, 'dpzsjyzjfmx', '', '/lghjft/cxtj/dpzsjyzjfmx/index', 'LghjftCxtjDpzsjyzjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214779, '查询', 'lghjft:cxtj-dpzsjyzjfmx:query', 3, 1, 214778, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214780, '导出', 'lghjft:cxtj-dpzsjyzjfmx:export', 3, 2, 214778, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 金融保险证券单位
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214781, '金融保险证券单位', '', 2, 11, 214750, 'jrbxzqdw', '', '/lghjft/cxtj/jrbxzqdw/index', 'LghjftCxtjJrbxzqdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214782, '查询', 'lghjft:cxtj-jrbxzqdw:query', 3, 1, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214783, '导出', 'lghjft:cxtj-jrbxzqdw:export', 3, 2, 214781, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 划拨失败记录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214784, '划拨失败记录', '', 2, 12, 214750, 'hbsbjl', '', '/lghjft/cxtj/hbsbjl/index', 'LghjftCxtjHbsbjl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214785, '查询', 'lghjft:cxtj-hbsbjl:query', 3, 1, 214784, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214786, '导出', 'lghjft:cxtj-hbsbjl:export', 3, 2, 214784, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 筹备金明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214787, '筹备金明细', '', 2, 13, 214750, 'cbjmx', '', '/lghjft/cxtj/cbjmx/index', 'LghjftCxtjCbjmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214788, '查询', 'lghjft:cxtj-cbjmx:query', 3, 1, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214789, '导出', 'lghjft:cxtj-cbjmx:export', 3, 2, 214787, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 近三年缴费情况
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214790, '近三年缴费情况', '', 2, 14, 214750, 'ghjfjfdw', '', '/lghjft/cxtj/ghjfjfdw/index', 'LghjftCxtjGhjfjfdw', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214791, '查询', 'lghjft:cxtj-ghjfjfdw:query', 3, 1, 214790, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214792, '导出', 'lghjft:cxtj-ghjfjfdw:export', 3, 2, 214790, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 已建会信息
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214793, '已建会信息', '', 2, 15, 214750, 'yjhxx', '', '/lghjft/cxtj/yjhxx/index', 'LghjftCxtjYjhxx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214794, '查询', 'lghjft:cxtj-yjhxx:query', 3, 1, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214795, '新增', 'lghjft:cxtj-yjhxx:create', 3, 2, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214796, '修改', 'lghjft:cxtj-yjhxx:update', 3, 3, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214797, '删除', 'lghjft:cxtj-yjhxx:delete', 3, 4, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214798, '导出', 'lghjft:cxtj-yjhxx:export', 3, 5, 214793, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 操作日志
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214799, '操作日志', '', 2, 16, 214750, 'czrj', '', '/lghjft/cxtj/czrj/index', 'LghjftCxtjCzrj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214800, '查询', 'lghjft:cxtj-czrj:query', 3, 1, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214801, '导出', 'lghjft:cxtj-czrj:export', 3, 2, 214799, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 工会经费筹备金全返
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214802, '工会经费筹备金全返', '', 2, 17, 214750, 'ghjfcbjqf', '', '/lghjft/cxtj/ghjfcbjqf/index', 'LghjftCxtjGhjfcbjqf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214803, '查询', 'lghjft:cxtj-ghjfcbjqf:query', 3, 1, 214802, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214804, '导出', 'lghjft:cxtj-ghjfcbjqf:export', 3, 2, 214802, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费分年明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214805, '经费分年明细', '', 2, 18, 214750, 'jffnmx', '', '/lghjft/cxtj/jffnmx/index', 'LghjftCxtjJffnmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214806, '查询', 'lghjft:cxtj-jffnmx:query', 3, 1, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214807, '导出', 'lghjft:cxtj-jffnmx:export', 3, 2, 214805, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费分月明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214808, '经费分月明细', '', 2, 19, 214750, 'jffymx', '', '/lghjft/cxtj/jffymx/index', 'LghjftCxtjJffymx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214809, '查询', 'lghjft:cxtj-jffymx:query', 3, 1, 214808, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214810, '导出', 'lghjft:cxtj-jffymx:export', 3, 2, 214808, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费台账分年
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214811, '经费台账分年', '', 2, 20, 214750, 'jftzfn', '', '/lghjft/cxtj/jftzfn/index', 'LghjftCxtjJftzfn', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214812, '查询', 'lghjft:cxtj-jftzfn:query', 3, 1, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214813, '导出', 'lghjft:cxtj-jftzfn:export', 3, 2, 214811, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 小微企业经费明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214814, '小微企业经费明细', '', 2, 21, 214750, 'xwqyjfmx', '', '/lghjft/cxtj/xwqyjfmx/index', 'LghjftCxtjXwqyjfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214815, '查询', 'lghjft:cxtj-xwqyjfmx:query', 3, 1, 214814, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214816, '导出', 'lghjft:cxtj-xwqyjfmx:export', 3, 2, 214814, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 单户经费台账
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214817, '单户经费台账', '', 2, 22, 214750, 'dhjftz', '', '/lghjft/cxtj/dhjftz/index', 'LghjftCxtjDhjftz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214818, '查询', 'lghjft:cxtj-dhjftz:query', 3, 1, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (214819, '导出', 'lghjft:cxtj-dhjftz:export', 3, 2, 214817, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

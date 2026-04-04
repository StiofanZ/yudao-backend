-- ============================================================
-- 经费处理 菜单（可直接在线上数据库执行）
-- ============================================================

-- 防重复：先删除已有记录
DELETE FROM system_menu WHERE id BETWEEN 215000 AND 215099;
DELETE FROM system_role_menu WHERE menu_id BETWEEN 215000 AND 215099;

-- ===================== 经费处理 jfcl =====================
-- 一级目录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215000, '经费处理', '', 1, 10, 0, '/jfcl', 'ep:money', null, null, 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费结算
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215001, '经费结算', '', 2, 1, 215000, 'jfjs', '', '/lghjft/jfcl/jfjs/index', 'LghjftJfclJfjs', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215002, '查询', 'lghjft:jfcl-jfjs:query', 3, 1, 215001, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215003, '导出', 'lghjft:jfcl-jfjs:export', 3, 2, 215001, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费补结算
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215004, '经费补结算', '', 2, 2, 215000, 'jfbjs', '', '/lghjft/jfcl/jfbjs/index', 'LghjftJfclJfbjs', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215005, '查询', 'lghjft:jfcl-jfbjs:query', 3, 1, 215004, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215006, '导出', 'lghjft:jfcl-jfbjs:export', 3, 2, 215004, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 当期单笔数据
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215007, '当期单笔数据', '', 2, 3, 215000, 'dqdssj', '', '/lghjft/jfcl/dqdssj/index', 'LghjftJfclDqdssj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215008, '查询', 'lghjft:jfcl-dqdssj:query', 3, 1, 215007, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215009, '新增', 'lghjft:jfcl-dqdssj:create', 3, 2, 215007, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215010, '导出', 'lghjft:jfcl-dqdssj:export', 3, 3, 215007, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 当期总量单数据
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215011, '当期总量单数据', '', 2, 4, 215000, 'dqzldssj', '', '/lghjft/jfcl/dqzldssj/index', 'LghjftJfclDqzldssj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215012, '查询', 'lghjft:jfcl-dqzldssj:query', 3, 1, 215011, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215013, '导出', 'lghjft:jfcl-dqzldssj:export', 3, 2, 215011, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 经费明细维护
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215014, '经费明细维护', '', 2, 5, 215000, 'jfmxwh', '', '/lghjft/jfcl/jfmxwh/index', 'LghjftJfclJfmxwh', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215015, '查询', 'lghjft:jfcl-jfmxwh:query', 3, 1, 215014, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215016, '新增', 'lghjft:jfcl-jfmxwh:create', 3, 2, 215014, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215017, '修改', 'lghjft:jfcl-jfmxwh:update', 3, 3, 215014, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215018, '删除', 'lghjft:jfcl-jfmxwh:delete', 3, 4, 215014, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215019, '导出', 'lghjft:jfcl-jfmxwh:export', 3, 5, 215014, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 录入退回凭证
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215020, '录入退回凭证', '', 2, 6, 215000, 'lrthpz', '', '/lghjft/jfcl/lrthpz/index', 'LghjftJfclLrthpz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215021, '查询', 'lghjft:jfcl-lrthpz:query', 3, 1, 215020, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215022, '新增', 'lghjft:jfcl-lrthpz:create', 3, 2, 215020, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215023, '修改', 'lghjft:jfcl-lrthpz:update', 3, 3, 215020, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215024, '删除', 'lghjft:jfcl-lrthpz:delete', 3, 4, 215020, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 退回凭证重发
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215025, '退回凭证重发', '', 2, 7, 215000, 'thpzcf', '', '/lghjft/jfcl/thpzcf/index', 'LghjftJfclThpzcf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215026, '查询', 'lghjft:jfcl-thpzcf:query', 3, 1, 215025, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215027, '修改', 'lghjft:jfcl-thpzcf:update', 3, 2, 215025, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215028, '删除', 'lghjft:jfcl-thpzcf:delete', 3, 3, 215025, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 手工拨付录入
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215029, '手工拨付录入', '', 2, 8, 215000, 'sgbflr', '', '/lghjft/jfcl/sgbflr/index', 'LghjftJfclSgbflr', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215030, '查询', 'lghjft:jfcl-sgbflr:query', 3, 1, 215029, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215031, '新增', 'lghjft:jfcl-sgbflr:create', 3, 2, 215029, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215032, '修改', 'lghjft:jfcl-sgbflr:update', 3, 3, 215029, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215033, '删除', 'lghjft:jfcl-sgbflr:delete', 3, 4, 215029, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215034, '导出', 'lghjft:jfcl-sgbflr:export', 3, 5, 215029, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 实查汇报无结
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215035, '实查汇报无结', '', 2, 9, 215000, 'schbwj', '', '/lghjft/jfcl/schbwj/index', 'LghjftJfclSchbwj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215036, '查询', 'lghjft:jfcl-schbwj:query', 3, 1, 215035, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215037, '生成划拨', 'lghjft:jfcl-schbwj:create', 3, 2, 215035, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215038, '导出', 'lghjft:jfcl-schbwj:export', 3, 3, 215035, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 特殊经费处理
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215039, '特殊经费处理', '', 2, 10, 215000, 'tsjfcl', '', '/lghjft/jfcl/tsjfcl/index', 'LghjftJfclTsjfcl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215040, '查询', 'lghjft:jfcl-tsjfcl:query', 3, 1, 215039, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215041, '导出', 'lghjft:jfcl-tsjfcl:export', 3, 2, 215039, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 特殊经费查询
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215042, '特殊经费查询', '', 2, 11, 215000, 'tsjfcx', '', '/lghjft/jfcl/tsjfcx/index', 'LghjftJfclTsjfcx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215043, '查询', 'lghjft:jfcl-tsjfcx:query', 3, 1, 215042, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215044, '导出', 'lghjft:jfcl-tsjfcx:export', 3, 2, 215042, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 银行拨付明细
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215045, '银行拨付明细', '', 2, 12, 215000, 'yhbfmx', '', '/lghjft/jfcl/yhbfmx/index', 'LghjftJfclYhbfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215046, '查询', 'lghjft:jfcl-yhbfmx:query', 3, 1, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215047, '新增', 'lghjft:jfcl-yhbfmx:create', 3, 2, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215048, '修改', 'lghjft:jfcl-yhbfmx:update', 3, 3, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215049, '删除', 'lghjft:jfcl-yhbfmx:delete', 3, 4, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215050, '导出', 'lghjft:jfcl-yhbfmx:export', 3, 5, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215051, '结算生成', 'lghjft:jfcl-yhbfmx:js', 3, 6, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215052, '补结算生成', 'lghjft:jfcl-yhbfmx:bjs', 3, 7, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215053, '失败退回重发', 'lghjft:jfcl-yhbfmx:sbthcb', 3, 8, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215054, '生成汇总', 'lghjft:jfcl-yhbfmx:yhbfhz', 3, 9, 215045, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 银行拨付汇总
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215055, '银行拨付汇总', '', 2, 13, 215000, 'yhbfhz', '', '/lghjft/jfcl/yhbfhz/index', 'LghjftJfclYhbfhz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215056, '查询', 'lghjft:jfcl-yhbfhz:query', 3, 1, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215057, '新增', 'lghjft:jfcl-yhbfhz:create', 3, 2, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215058, '修改', 'lghjft:jfcl-yhbfhz:update', 3, 3, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215059, '删除', 'lghjft:jfcl-yhbfhz:delete', 3, 4, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215060, '导出', 'lghjft:jfcl-yhbfhz:export', 3, 5, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215061, '提交银行', 'lghjft:jfcl-yhbfhz:yhbfhztj', 3, 6, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215062, '支付明细同步', 'lghjft:jfcl-yhbfhz:zfmxcx', 3, 7, 215055, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 银行拨付汇总监控记录
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215063, '银行拨付汇总监控记录', '', 2, 14, 215000, 'yhbfhzjkjl', '', '/lghjft/jfcl/yhbfhzjkjl/index', 'LghjftJfclYhbfhzjkjl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215064, '查询', 'lghjft:jfcl-yhbfhzjkjl:query', 3, 1, 215063, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215065, '新增', 'lghjft:jfcl-yhbfhzjkjl:create', 3, 2, 215063, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215066, '修改', 'lghjft:jfcl-yhbfhzjkjl:update', 3, 3, 215063, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215067, '删除', 'lghjft:jfcl-yhbfhzjkjl:delete', 3, 4, 215063, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215068, '导出', 'lghjft:jfcl-yhbfhzjkjl:export', 3, 5, 215063, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 银行拨付结果查询
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215069, '银行拨付结果查询', '', 2, 15, 215000, 'yhbfjgcx', '', '/lghjft/jfcl/yhbfjgcx/index', 'LghjftJfclYhbfjgcx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215070, '查询', 'lghjft:jfcl-yhbfjgcx:query', 3, 1, 215069, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215071, '新增', 'lghjft:jfcl-yhbfjgcx:create', 3, 2, 215069, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215072, '修改', 'lghjft:jfcl-yhbfjgcx:update', 3, 3, 215069, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215073, '删除', 'lghjft:jfcl-yhbfjgcx:delete', 3, 4, 215069, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);
INSERT INTO system_menu(id, name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES (215074, '导出', 'lghjft:jfcl-yhbfjgcx:export', 3, 5, 215069, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- ===================== 角色菜单关联（role_id=1 为超级管理员） =====================
INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, 215000, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215001, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215002, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215003, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215004, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215005, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215006, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215007, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215008, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215009, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215010, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215011, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215012, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215013, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215014, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215015, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215016, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215017, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215018, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215019, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215020, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215021, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215022, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215023, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215024, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215025, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215026, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215027, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215028, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215029, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215030, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215031, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215032, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215033, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215034, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215035, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215036, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215037, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215038, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215039, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215040, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215041, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215042, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215043, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215044, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215045, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215046, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215047, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215048, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215049, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215050, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215051, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215052, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215053, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215054, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215055, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215056, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215057, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215058, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215059, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215060, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215061, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215062, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215063, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215064, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215065, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215066, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215067, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215068, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215069, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215070, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215071, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215072, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215073, '1', NOW(), '1', NOW(), 0, 1),
       (1, 215074, '1', NOW(), '1', NOW(), 0, 1);

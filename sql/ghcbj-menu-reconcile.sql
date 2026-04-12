-- 筹备金账菜单收敛脚本
-- 当前实库已存在 canonical 菜单 ID，本脚本采用“原地更新 + 清理多余按钮”的最小风险方式：
-- 1. 根菜单统一为 /ghcbj
-- 2. 5 个二级菜单 component 统一到 /lghjft/ghcbj/*
-- 3. 仅保留真实页面使用的按钮：
--    cbjtz      -> query / update / export
--    cbjqfmx    -> query / export
--    ghjfcbjqf  -> query / update / export
--    cbjhztj    -> query / export
--    cbjqltz    -> query / update / export

SET @ghcbj_root_id := (
  SELECT id
  FROM system_menu
  WHERE deleted = 0
    AND parent_id = 0
    AND name IN ('筹备金账', '工会筹备金')
  ORDER BY CASE WHEN name = '筹备金账' THEN 0 ELSE 1 END, id DESC
  LIMIT 1
);

INSERT INTO system_menu (
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
SELECT
  216300, '筹备金账', '', 1, 9, 0, '/ghcbj', 'ep:money', NULL, NULL,
  0, 1, 1, 1, '1', NOW(), '1', NOW(), 0
WHERE @ghcbj_root_id IS NULL;

SET @ghcbj_root_id := COALESCE(@ghcbj_root_id, 216300);

UPDATE system_menu
SET name = '筹备金账',
    path = '/ghcbj',
    icon = 'ep:money',
    permission = '',
    type = 1,
    sort = 9,
    parent_id = 0,
    component = NULL,
    component_name = NULL,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    deleted = 0,
    updater = '1',
    update_time = NOW()
WHERE id = @ghcbj_root_id;

-- 规范化二级菜单
INSERT INTO system_menu (
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
VALUES
  (216301, '筹备金结算台账', '', 2, 1, @ghcbj_root_id, 'cbjtz', '', '/lghjft/ghcbj/cbjtz/index', 'LghjftGhcbjCbjtz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216307, '筹备金21年8月后', '', 2, 2, @ghcbj_root_id, 'cbjqfmx', '', '/lghjft/ghcbj/cbjqfmx/index', 'LghjftGhcbjCbjqfmx', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216313, '筹备金全返', '', 2, 3, @ghcbj_root_id, 'ghjfcbjqf', '', '/lghjft/ghcbj/ghjfcbjqf/index', 'LghjftGhcbjGhjfcbjqf', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216319, '筹备金统计', '', 2, 4, @ghcbj_root_id, 'cbjhztj', '', '/lghjft/ghcbj/cbjhztj/index', 'LghjftGhcbjCbjhztj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216325, '筹备金清理台账', '', 2, 5, @ghcbj_root_id, 'cbjqltz', '', '/lghjft/ghcbj/cbjqltz/index', 'LghjftGhcbjCbjqltz', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  type = VALUES(type),
  sort = VALUES(sort),
  parent_id = VALUES(parent_id),
  path = VALUES(path),
  icon = VALUES(icon),
  component = VALUES(component),
  component_name = VALUES(component_name),
  status = VALUES(status),
  visible = VALUES(visible),
  keep_alive = VALUES(keep_alive),
  always_show = VALUES(always_show),
  deleted = 0,
  updater = '1',
  update_time = NOW();

-- 规范化保留按钮
INSERT INTO system_menu (
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
VALUES
  (216302, '查询', 'lghjft:ghcbj-cbjtz:query', 3, 1, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216304, '修改', 'lghjft:ghcbj-cbjtz:update', 3, 2, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216306, '导出', 'lghjft:ghcbj-cbjtz:export', 3, 3, 216301, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216308, '查询', 'lghjft:ghcbj-cbjqfmx:query', 3, 1, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216312, '导出', 'lghjft:ghcbj-cbjqfmx:export', 3, 2, 216307, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216314, '查询', 'lghjft:ghcbj-ghjfcbjqf:query', 3, 1, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216316, '修改', 'lghjft:ghcbj-ghjfcbjqf:update', 3, 2, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216318, '导出', 'lghjft:ghcbj-ghjfcbjqf:export', 3, 3, 216313, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216320, '查询', 'lghjft:ghcbj-cbjhztj:query', 3, 1, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216324, '导出', 'lghjft:ghcbj-cbjhztj:export', 3, 2, 216319, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216326, '查询', 'lghjft:ghcbj-cbjqltz:query', 3, 1, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216328, '修改', 'lghjft:ghcbj-cbjqltz:update', 3, 2, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (216330, '导出', 'lghjft:ghcbj-cbjqltz:export', 3, 3, 216325, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  type = VALUES(type),
  sort = VALUES(sort),
  parent_id = VALUES(parent_id),
  deleted = 0,
  status = 0,
  visible = 1,
  keep_alive = 1,
  always_show = 1,
  updater = '1',
  update_time = NOW();

DROP TEMPORARY TABLE IF EXISTS tmp_ghcbj_removed_button_ids;
CREATE TEMPORARY TABLE tmp_ghcbj_removed_button_ids (
  menu_id BIGINT PRIMARY KEY
);

INSERT INTO tmp_ghcbj_removed_button_ids(menu_id)
VALUES (216303), (216305), (216309), (216310), (216311), (216315), (216317), (216321), (216322), (216323), (216327), (216329);

DELETE rm
FROM system_role_menu rm
JOIN tmp_ghcbj_removed_button_ids d ON d.menu_id = rm.menu_id
WHERE rm.deleted = 0;

UPDATE system_menu
SET deleted = 1,
    updater = '1',
    update_time = NOW()
WHERE id IN (SELECT menu_id FROM tmp_ghcbj_removed_button_ids)
  AND deleted = 0;

-- 回归检查：
-- SELECT id, parent_id, name, path, component, permission, deleted
-- FROM system_menu
-- WHERE id IN (216300,216301,216302,216304,216306,216307,216308,216312,216313,216314,216316,216318,216319,216320,216324,216325,216326,216328,216330,216303,216305,216309,216310,216311,216315,216317,216321,216322,216323,216327,216329)
-- ORDER BY parent_id, sort, id;

-- ============================================================
-- XWQY menu reconciliation script
-- Keep only the 4 v2 leaf menus and their button permissions
-- ============================================================

DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_root_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_menu_role_map;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_parent_menu_role_map;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_keep_role_menu;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_keep_role_stage;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_delete_seed_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_exact_delete_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_delete_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_expand_ids;

CREATE TEMPORARY TABLE tmp_xwqy_root_ids (
  id BIGINT PRIMARY KEY
);

CREATE TEMPORARY TABLE tmp_xwqy_menu_role_map (
  source_menu_id BIGINT PRIMARY KEY,
  target_menu_id BIGINT NOT NULL
);

CREATE TEMPORARY TABLE tmp_xwqy_parent_menu_role_map (
  source_menu_id BIGINT PRIMARY KEY,
  target_menu_id BIGINT NOT NULL
);

CREATE TEMPORARY TABLE tmp_xwqy_keep_role_menu (
  role_id BIGINT NOT NULL,
  menu_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, menu_id, tenant_id)
);

CREATE TEMPORARY TABLE tmp_xwqy_keep_role_stage (
  role_id BIGINT NOT NULL,
  menu_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, menu_id, tenant_id)
);

CREATE TEMPORARY TABLE tmp_xwqy_delete_seed_ids (
  id BIGINT PRIMARY KEY
);

CREATE TEMPORARY TABLE tmp_xwqy_exact_delete_ids (
  id BIGINT PRIMARY KEY
);

CREATE TEMPORARY TABLE tmp_xwqy_delete_ids (
  id BIGINT PRIMARY KEY
);

CREATE TEMPORARY TABLE tmp_xwqy_expand_ids (
  id BIGINT PRIMARY KEY
);

INSERT INTO tmp_xwqy_root_ids (id)
SELECT id
FROM system_menu
WHERE parent_id = 0
  AND path = '/xwqy'
  AND name = '小微台账';

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT id, 214700
FROM tmp_xwqy_root_ids;

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT menu.id, 214701
FROM system_menu menu
JOIN system_menu root ON menu.parent_id = root.id
WHERE root.parent_id = 0
  AND root.deleted = 0
  AND menu.deleted = 0
  AND root.path = '/xwqy'
  AND root.name = '小微台账'
  AND menu.name IN ('企业管理', '小微企业管理')
  AND menu.path = 'xwqygl';

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT menu.id, 214707
FROM system_menu menu
JOIN system_menu root ON menu.parent_id = root.id
WHERE root.parent_id = 0
  AND root.deleted = 0
  AND menu.deleted = 0
  AND (
    (
      root.path = '/xwqy'
      AND root.name = '小微台账'
      AND menu.name IN ('小微企业95%', '小微企业经费明细')
      AND menu.path IN ('xwqyjfmx95', 'xwqyjfmx')
    )
    OR
    (
      root.path = '/cxtj'
      AND root.name = '查询统计'
      AND menu.name IN ('小微企业95%', '小微企业经费明细')
      AND menu.path = 'xwqyjfmx'
    )
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT menu.id, 214713
FROM system_menu menu
JOIN system_menu root ON menu.parent_id = root.id
WHERE root.parent_id = 0
  AND root.deleted = 0
  AND menu.deleted = 0
  AND (
    (
      root.path = '/xwqy'
      AND root.name = '小微台账'
      AND menu.name = '小微企业60%'
      AND menu.path IN ('xwqyjfmx60', 'xwqyjfyfmx', 'xwqyjfmx')
    )
    OR
    (
      root.path = '/cxtj'
      AND root.name = '查询统计'
      AND menu.name = '小微企业60%'
      AND menu.path IN ('xwqyjfyfmx', 'xwqyjfmx')
    )
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT menu.id, 214719
FROM system_menu menu
JOIN system_menu root ON menu.parent_id = root.id
WHERE root.parent_id = 0
  AND root.deleted = 0
  AND menu.deleted = 0
  AND root.path = '/xwqy'
  AND root.name = '小微台账'
  AND menu.name IN ('经费统计', '小微经费统计')
  AND menu.path = 'xwqyjftj';

INSERT IGNORE INTO tmp_xwqy_parent_menu_role_map (source_menu_id, target_menu_id)
SELECT source_menu_id, target_menu_id
FROM tmp_xwqy_menu_role_map
WHERE target_menu_id IN (214701, 214707, 214713, 214719);

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214702
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214701
  AND button.deleted = 0
  AND button.permission = 'lghjft:xwqy-xwqygl:query';

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214703
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214701
  AND button.deleted = 0
  AND button.permission = 'lghjft:xwqy-xwqygl:export';

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214708
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214707
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjfmx95:query',
    'lghjft:xwqyjf:query',
    'lghjft:cxtj-xwqyjfmx:query',
    'lghjft:xwqy-xwqyjf:query'
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214709
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214707
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjfmx95:export',
    'lghjft:xwqyjf:export',
    'lghjft:cxtj-xwqyjfmx:export',
    'lghjft:xwqy-xwqyjf:export'
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214714
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214713
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjfmx60:query',
    'lghjft:xwqy-xwqyjfyfmx:query',
    'lghjft:xwqyjf:query',
    'lghjft:cxtj-xwqyjfmx:query'
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214715
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214713
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjfmx60:export',
    'lghjft:xwqy-xwqyjfyfmx:export',
    'lghjft:xwqyjf:export',
    'lghjft:cxtj-xwqyjfmx:export'
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214720
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214719
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjftj:query',
    'lghjft:xwqyjftj:query'
  );

INSERT IGNORE INTO tmp_xwqy_menu_role_map (source_menu_id, target_menu_id)
SELECT button.id, 214721
FROM system_menu button
JOIN tmp_xwqy_parent_menu_role_map parent_map ON button.parent_id = parent_map.source_menu_id
WHERE parent_map.target_menu_id = 214719
  AND button.deleted = 0
  AND button.permission IN (
    'lghjft:xwqy-xwqyjftj:export',
    'lghjft:xwqyjftj:export'
  );

INSERT IGNORE INTO tmp_xwqy_keep_role_menu (role_id, menu_id, tenant_id)
SELECT rm.role_id, role_map.target_menu_id, COALESCE(rm.tenant_id, 1)
FROM system_role_menu rm
JOIN tmp_xwqy_menu_role_map role_map ON rm.menu_id = role_map.source_menu_id
JOIN system_menu source_menu ON source_menu.id = role_map.source_menu_id
WHERE rm.deleted = 0
  AND source_menu.deleted = 0;

INSERT IGNORE INTO tmp_xwqy_keep_role_stage (role_id, menu_id, tenant_id)
SELECT role_id,
       CASE menu_id
         WHEN 214702 THEN 214701
         WHEN 214703 THEN 214701
         WHEN 214708 THEN 214707
         WHEN 214709 THEN 214707
         WHEN 214714 THEN 214713
         WHEN 214715 THEN 214713
         WHEN 214720 THEN 214719
         WHEN 214721 THEN 214719
       END AS menu_id,
       tenant_id
FROM tmp_xwqy_keep_role_menu
WHERE menu_id IN (214702, 214703, 214708, 214709, 214714, 214715, 214720, 214721);

INSERT IGNORE INTO tmp_xwqy_keep_role_menu (role_id, menu_id, tenant_id)
SELECT role_id, menu_id, tenant_id
FROM tmp_xwqy_keep_role_stage;

DELETE FROM tmp_xwqy_keep_role_stage;

INSERT IGNORE INTO tmp_xwqy_keep_role_stage (role_id, menu_id, tenant_id)
SELECT role_id, 214700, tenant_id
FROM tmp_xwqy_keep_role_menu
WHERE menu_id IN (214701, 214702, 214703, 214707, 214708, 214709, 214713, 214714, 214715, 214719, 214720, 214721);

INSERT IGNORE INTO tmp_xwqy_keep_role_menu (role_id, menu_id, tenant_id)
SELECT role_id, menu_id, tenant_id
FROM tmp_xwqy_keep_role_stage;

INSERT IGNORE INTO tmp_xwqy_delete_seed_ids (id)
SELECT id
FROM tmp_xwqy_root_ids;

INSERT IGNORE INTO tmp_xwqy_delete_seed_ids (id)
SELECT source_menu_id
FROM tmp_xwqy_menu_role_map;

INSERT IGNORE INTO tmp_xwqy_delete_seed_ids (id)
SELECT menu.id
FROM system_menu menu
JOIN system_menu root ON menu.parent_id = root.id
WHERE root.parent_id = 0
  AND root.path = '/xwqy'
  AND root.name = '小微台账'
  AND menu.name = '筹备金管理';

INSERT IGNORE INTO tmp_xwqy_exact_delete_ids (id)
VALUES
  (214700), (214701), (214702), (214703), (214707), (214708), (214709),
  (214713), (214714), (214715), (214719), (214720), (214721);

INSERT IGNORE INTO tmp_xwqy_delete_seed_ids (id)
SELECT id
FROM tmp_xwqy_exact_delete_ids;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_delete_seed_ids;

-- Seed canonical IDs before expanding descendants so extra legacy children under
-- 214700/214701-214703/214707-214709/214713-214715/214719-214721 are cleaned together.
-- Expand with a staging table so MySQL never reads and writes tmp_xwqy_delete_ids
-- in the same statement, avoiding "Can't reopen table" on 5.7/8.0 runners.
DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM tmp_xwqy_expand_ids;

INSERT IGNORE INTO tmp_xwqy_expand_ids (id)
SELECT child.id
FROM system_menu child
JOIN tmp_xwqy_delete_ids parent_tree ON child.parent_id = parent_tree.id;

INSERT IGNORE INTO tmp_xwqy_delete_ids (id)
SELECT id
FROM tmp_xwqy_expand_ids;

DELETE FROM system_role_menu
WHERE menu_id IN (
  SELECT id
  FROM tmp_xwqy_delete_ids
);

DELETE FROM system_menu
WHERE id IN (
  SELECT id
  FROM tmp_xwqy_delete_ids
);

-- Drop orphaned xwqy role bindings that still point at removed menu IDs.
DELETE rm
FROM system_role_menu rm
LEFT JOIN system_menu menu ON menu.id = rm.menu_id
WHERE menu.id IS NULL
  AND rm.menu_id IN (
    214700, 214701, 214702, 214703,
    214704, 214705, 214706,
    214707, 214708, 214709,
    214710, 214711, 214712,
    214713, 214714, 214715,
    214716, 214717, 214718,
    214719, 214720, 214721,
    214722, 214723, 214724,
    214730, 214731, 214732,
    214733, 214734, 214735,
    214814, 214815, 214816,
    214889, 214890, 214891,
    214892, 214893, 214894
  );

SET @xwqy_root_id := (
  SELECT id
  FROM system_menu
  WHERE id = 214700
    AND parent_id = 0
    AND path = '/xwqy'
    AND name = '小微台账'
    AND deleted = 0
  LIMIT 1
);

-- Create the root menu when missing; otherwise reuse the canonical root ID
INSERT INTO system_menu(
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
SELECT
  214700, '小微台账', '', 1, 5, 0, '/xwqy', 'ep:data-board', NULL, NULL,
  0, 1, 1, 1, '1', NOW(), '1', NOW(), 0
WHERE @xwqy_root_id IS NULL;

SET @xwqy_root_id := 214700;

INSERT INTO system_menu(
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
VALUES
  (214701, '小微企业管理', '', 2, 1, @xwqy_root_id, 'xwqygl', 'ep:office-building', '/lghjft/xwqy/xwqygl/index', 'LghjftXwqyXwqygl', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214707, '小微企业95%', '', 2, 2, @xwqy_root_id, 'xwqyjfmx95', 'ep:document', '/lghjft/xwqy/xwqyjfmx95/index', 'LghjftXwqyXwqyjfmx95', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214713, '小微企业60%', '', 2, 3, @xwqy_root_id, 'xwqyjfmx60', 'ep:document', '/lghjft/xwqy/xwqyjfmx60/index', 'LghjftXwqyXwqyjfmx60', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214719, '小微经费统计', '', 2, 4, @xwqy_root_id, 'xwqyjftj', 'ep:histogram', '/lghjft/xwqy/xwqyjftj/index', 'LghjftXwqyXwqyjftj', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

INSERT INTO system_menu(
  id, name, permission, type, sort, parent_id, path, icon, component, component_name,
  status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
)
VALUES
  (214702, '小微企业管理查询', 'lghjft:xwqy-xwqygl:query', 3, 1, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214703, '小微企业管理导出', 'lghjft:xwqy-xwqygl:export', 3, 2, 214701, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214708, '小微企业95%查询', 'lghjft:xwqy-xwqyjfmx95:query', 3, 1, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214709, '小微企业95%导出', 'lghjft:xwqy-xwqyjfmx95:export', 3, 2, 214707, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214714, '小微企业60%查询', 'lghjft:xwqy-xwqyjfmx60:query', 3, 1, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214715, '小微企业60%导出', 'lghjft:xwqy-xwqyjfmx60:export', 3, 2, 214713, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214720, '小微经费统计查询', 'lghjft:xwqy-xwqyjftj:query', 3, 1, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0),
  (214721, '小微经费统计导出', 'lghjft:xwqy-xwqyjftj:export', 3, 2, 214719, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT keep.role_id, keep.menu_id, '1', NOW(), '1', NOW(), 0, keep.tenant_id
FROM tmp_xwqy_keep_role_menu keep;

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 1, final_menu.menu_id, '1', NOW(), '1', NOW(), 0, 1
FROM (
  SELECT 214700 AS menu_id
  UNION ALL SELECT 214701
  UNION ALL SELECT 214702
  UNION ALL SELECT 214703
  UNION ALL SELECT 214707
  UNION ALL SELECT 214708
  UNION ALL SELECT 214709
  UNION ALL SELECT 214713
  UNION ALL SELECT 214714
  UNION ALL SELECT 214715
  UNION ALL SELECT 214719
  UNION ALL SELECT 214720
  UNION ALL SELECT 214721
) final_menu
LEFT JOIN system_role_menu existing
  ON existing.role_id = 1
 AND existing.menu_id = final_menu.menu_id
 AND existing.tenant_id = 1
WHERE existing.menu_id IS NULL;

DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_delete_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_expand_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_exact_delete_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_delete_seed_ids;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_keep_role_stage;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_keep_role_menu;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_menu_role_map;
DROP TEMPORARY TABLE IF EXISTS tmp_xwqy_root_ids;

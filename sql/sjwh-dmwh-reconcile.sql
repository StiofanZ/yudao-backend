-- 数据维护 / 代码维护 菜单权限规范化
-- 目标：
-- 1. 将代码维护 6 个子功能按钮权限统一为 lghjft:sjwh-dmwh-{功能}:*
-- 2. 清理分配比例的重复按钮 216722-216725，保留原始按钮 213633-213637

START TRANSACTION;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-xzqh:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213609
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-xzqh:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213610
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-xzqh:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213611
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-xzqh:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213612
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-xzqh:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213613
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-yhwd:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213615
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-yhwd:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213616
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-yhwd:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213617
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-yhwd:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213618
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-yhwd:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213619
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-swjg:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213621
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-swjg:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213622
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-swjg:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213623
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-swjg:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213624
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-swjg:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213625
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-hjfl:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213627
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-hjfl:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213628
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-hjfl:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213629
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-hjfl:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213630
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-hjfl:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213631
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-fpbl:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213633
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-fpbl:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213634
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-fpbl:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213635
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-fpbl:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213636
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-fpbl:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213637
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-skgk:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213639
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-skgk:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213640
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-skgk:update',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213641
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-skgk:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213642
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-dmwh-skgk:export',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213643
  AND deleted = 0;

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted)
SELECT rm.role_id, 213634, 'codex', NOW(), 'codex', NOW(), 0
FROM system_role_menu rm
WHERE rm.menu_id = 216722
  AND rm.deleted = 0
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu existing
    WHERE existing.role_id = rm.role_id
      AND existing.menu_id = 213634
      AND existing.deleted = 0
  );

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted)
SELECT rm.role_id, 213636, 'codex', NOW(), 'codex', NOW(), 0
FROM system_role_menu rm
WHERE rm.menu_id = 216723
  AND rm.deleted = 0
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu existing
    WHERE existing.role_id = rm.role_id
      AND existing.menu_id = 213636
      AND existing.deleted = 0
  );

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted)
SELECT rm.role_id, 213637, 'codex', NOW(), 'codex', NOW(), 0
FROM system_role_menu rm
WHERE rm.menu_id = 216724
  AND rm.deleted = 0
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu existing
    WHERE existing.role_id = rm.role_id
      AND existing.menu_id = 213637
      AND existing.deleted = 0
  );

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted)
SELECT rm.role_id, 213635, 'codex', NOW(), 'codex', NOW(), 0
FROM system_role_menu rm
WHERE rm.menu_id = 216725
  AND rm.deleted = 0
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu existing
    WHERE existing.role_id = rm.role_id
      AND existing.menu_id = 213635
      AND existing.deleted = 0
  );

UPDATE system_role_menu
SET deleted = 1,
    updater = 'codex',
    update_time = NOW()
WHERE menu_id IN (216722, 216723, 216724, 216725)
  AND deleted = 0;

UPDATE system_menu
SET deleted = 1,
    updater = 'codex',
    update_time = NOW()
WHERE id IN (216722, 216723, 216724, 216725)
  AND deleted = 0;

COMMIT;

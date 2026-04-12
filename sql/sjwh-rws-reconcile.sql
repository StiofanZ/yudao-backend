-- 数据维护 / 年度任务 菜单收敛
-- 目标：
-- 1. 页面组件统一到 /lghjft/sjwh/rws/index
-- 2. 按钮权限统一到 lghjft:sjwh-rws:*
-- 3. 清理旧 lghjft:rws:* 重复按钮，并迁移角色绑定

START TRANSACTION;

UPDATE system_menu
SET path = 'rws',
    component = '/lghjft/sjwh/rws/index',
    component_name = 'LghjftSjwhRws',
    update_time = NOW()
WHERE id = 213602
  AND deleted = 0;

UPDATE system_menu
SET name = '年度任务查询',
    permission = 'lghjft:sjwh-rws:query',
    sort = 1,
    update_time = NOW()
WHERE id = 213603
  AND deleted = 0;

UPDATE system_menu
SET name = '年度任务创建',
    permission = 'lghjft:sjwh-rws:create',
    sort = 2,
    update_time = NOW()
WHERE id = 213604
  AND deleted = 0;

UPDATE system_menu
SET name = '年度任务更新',
    permission = 'lghjft:sjwh-rws:update',
    sort = 3,
    update_time = NOW()
WHERE id = 213605
  AND deleted = 0;

UPDATE system_menu
SET name = '年度任务删除',
    permission = 'lghjft:sjwh-rws:delete',
    sort = 4,
    update_time = NOW()
WHERE id = 213606
  AND deleted = 0;

UPDATE system_menu
SET name = '年度任务导出',
    permission = 'lghjft:sjwh-rws:export',
    sort = 5,
    update_time = NOW()
WHERE id = 213607
  AND deleted = 0;

INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT srm.role_id, 213604, '1', NOW(), '1', NOW(), b'0', 0
FROM system_role_menu srm
WHERE srm.menu_id = 216737
  AND srm.deleted = b'0'
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu target
    WHERE target.role_id = srm.role_id
      AND target.menu_id = 213604
      AND target.deleted = b'0'
  );

INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT srm.role_id, 213606, '1', NOW(), '1', NOW(), b'0', 0
FROM system_role_menu srm
WHERE srm.menu_id = 216738
  AND srm.deleted = b'0'
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu target
    WHERE target.role_id = srm.role_id
      AND target.menu_id = 213606
      AND target.deleted = b'0'
  );

INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT srm.role_id, 213607, '1', NOW(), '1', NOW(), b'0', 0
FROM system_role_menu srm
WHERE srm.menu_id = 216739
  AND srm.deleted = b'0'
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu target
    WHERE target.role_id = srm.role_id
      AND target.menu_id = 213607
      AND target.deleted = b'0'
  );

INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT srm.role_id, 213605, '1', NOW(), '1', NOW(), b'0', 0
FROM system_role_menu srm
WHERE srm.menu_id = 216740
  AND srm.deleted = b'0'
  AND NOT EXISTS (
    SELECT 1
    FROM system_role_menu target
    WHERE target.role_id = srm.role_id
      AND target.menu_id = 213605
      AND target.deleted = b'0'
  );

UPDATE system_role_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE menu_id IN (216737, 216738, 216739, 216740)
  AND deleted = b'0';

UPDATE system_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE id IN (216737, 216738, 216739, 216740)
  AND deleted = b'0';

COMMIT;

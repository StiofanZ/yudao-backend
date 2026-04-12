-- 数据维护 / 户籍管理恢复
-- 目标：
-- 1. 恢复 v1 户籍管理在 数据维护 下的入口
-- 2. 保留 v2 现有独立根 户籍管理 不变
-- 3. 复用历史菜单 214694 与按钮 214950-214954，避免重新分裂 ID

START TRANSACTION;

UPDATE system_menu
SET name = '户籍管理',
    permission = '',
    type = 2,
    sort = 1,
    parent_id = 205047,
    path = 'hjgl',
    icon = 'ep:avatar',
    component = '/lghjft/sjwh/hjgl/index',
    component_name = 'LghjftSjwhHjgl',
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214694;

UPDATE system_menu
SET name = '查询',
    permission = 'lghjft:hjgl:query',
    type = 3,
    sort = 1,
    parent_id = 214694,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214950;

UPDATE system_menu
SET name = '新增',
    permission = 'lghjft:hjgl:create',
    type = 3,
    sort = 2,
    parent_id = 214694,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214951;

UPDATE system_menu
SET name = '修改',
    permission = 'lghjft:hjgl:update',
    type = 3,
    sort = 3,
    parent_id = 214694,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214952;

UPDATE system_menu
SET name = '删除',
    permission = 'lghjft:hjgl:delete',
    type = 3,
    sort = 4,
    parent_id = 214694,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214953;

UPDATE system_menu
SET name = '导出',
    permission = 'lghjft:hjgl:export',
    type = 3,
    sort = 5,
    parent_id = 214694,
    status = 0,
    visible = 1,
    keep_alive = 1,
    always_show = 1,
    updater = 'codex',
    update_time = NOW(),
    deleted = 0
WHERE id = 214954;

DROP TEMPORARY TABLE IF EXISTS tmp_hjgl_restore_role_map;
CREATE TEMPORARY TABLE tmp_hjgl_restore_role_map (
    target_menu_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (target_menu_id, role_id)
);

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214694, role_id
FROM system_role_menu
WHERE menu_id = 213592
  AND deleted = 0;

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214950, role_id
FROM system_role_menu
WHERE menu_id = 213597
  AND deleted = 0;

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214951, role_id
FROM system_role_menu
WHERE menu_id = 213594
  AND deleted = 0;

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214952, role_id
FROM system_role_menu
WHERE menu_id = 213595
  AND deleted = 0;

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214953, role_id
FROM system_role_menu
WHERE menu_id = 213596
  AND deleted = 0;

INSERT INTO tmp_hjgl_restore_role_map(target_menu_id, role_id)
SELECT 214954, role_id
FROM system_role_menu
WHERE menu_id = 213597
  AND deleted = 0;

UPDATE system_role_menu rm
JOIN tmp_hjgl_restore_role_map desired
  ON desired.target_menu_id = rm.menu_id
 AND desired.role_id = rm.role_id
SET rm.deleted = 0,
    rm.updater = 'codex',
    rm.update_time = NOW()
WHERE rm.deleted = 1;

INSERT INTO system_role_menu(role_id, menu_id, creator, create_time, updater, update_time, deleted)
SELECT desired.role_id, desired.target_menu_id, 'codex', NOW(), 'codex', NOW(), 0
FROM tmp_hjgl_restore_role_map desired
LEFT JOIN system_role_menu existing
  ON existing.role_id = desired.role_id
 AND existing.menu_id = desired.target_menu_id
WHERE existing.role_id IS NULL;

DROP TEMPORARY TABLE IF EXISTS tmp_hjgl_restore_role_map;

COMMIT;

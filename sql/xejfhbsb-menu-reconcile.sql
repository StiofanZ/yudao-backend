-- ============================================================
-- XEJFHBSB / XEJFHBSBYXG menu reconciliation script
-- Align v2 real DB menus with v1 canonical xejf entry points
-- ============================================================

DROP TEMPORARY TABLE IF EXISTS tmp_xejfhbsb_old_role_menu;
DROP TEMPORARY TABLE IF EXISTS tmp_xejfhbsbyxg_old_role_menu;

CREATE TEMPORARY TABLE tmp_xejfhbsb_old_role_menu (
  role_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, tenant_id)
);

CREATE TEMPORARY TABLE tmp_xejfhbsbyxg_old_role_menu (
  role_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, tenant_id)
);

INSERT INTO tmp_xejfhbsb_old_role_menu (role_id, tenant_id)
SELECT DISTINCT role_id, COALESCE(tenant_id, 1)
FROM system_role_menu
WHERE deleted = 0
  AND menu_id = 103266;

INSERT INTO tmp_xejfhbsbyxg_old_role_menu (role_id, tenant_id)
SELECT DISTINCT role_id, COALESCE(tenant_id, 1)
FROM system_role_menu
WHERE deleted = 0
  AND menu_id = 103267;

UPDATE system_menu
SET component = '/lghjft/xejf/xejfhbsb/index',
    component_name = 'LghjftXejfXejfhbsb',
    updater = '1',
    update_time = NOW()
WHERE id = 216273
  AND deleted = 0;

UPDATE system_menu
SET component = '/lghjft/xejf/xejfhbsbyxg/index',
    component_name = 'LghjftXejfXejfhbsbyxg',
    updater = '1',
    update_time = NOW()
WHERE id = 216279
  AND deleted = 0;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216200, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsb_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216273, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsb_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216274, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsb_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216276, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsb_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216278, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsb_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216200, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsbyxg_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216279, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsbyxg_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216280, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsbyxg_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216284, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xejfhbsbyxg_old_role_menu;

UPDATE system_role_menu s
JOIN system_role_menu t
  ON s.role_id = t.role_id
 AND s.menu_id = t.menu_id
 AND s.tenant_id = t.tenant_id
 AND s.id > t.id
 AND s.deleted = 0
 AND t.deleted = 0
SET s.deleted = 1,
    s.updater = '1',
    s.update_time = NOW()
WHERE s.menu_id IN (216200, 216273, 216274, 216276, 216278, 216279, 216280, 216284);

UPDATE system_role_menu
SET deleted = 1,
    updater = '1',
    update_time = NOW()
WHERE deleted = 0
  AND menu_id IN (103266, 103267);

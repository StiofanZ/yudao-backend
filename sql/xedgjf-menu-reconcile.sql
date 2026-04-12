-- ============================================================
-- XEDGJF menu reconciliation script
-- Keep canonical v2 menu 216261 and retire old duplicate 103268
-- ============================================================

DROP TEMPORARY TABLE IF EXISTS tmp_xedgjf_old_role_menu;

CREATE TEMPORARY TABLE tmp_xedgjf_old_role_menu (
  role_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, tenant_id)
);

INSERT INTO tmp_xedgjf_old_role_menu (role_id, tenant_id)
SELECT DISTINCT role_id, COALESCE(tenant_id, 1)
FROM system_role_menu
WHERE deleted = 0
  AND menu_id = 103268;

UPDATE system_menu
SET component = '/lghjft/xejf/xedgjf/index',
    component_name = 'LghjftXejfXedgjf',
    updater = '1',
    update_time = NOW()
WHERE id = 216261
  AND deleted = 0;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216200, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xedgjf_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216261, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xedgjf_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216262, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xedgjf_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216264, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xedgjf_old_role_menu;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 216266, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xedgjf_old_role_menu;

UPDATE system_role_menu
SET deleted = 1,
    updater = '1',
    update_time = NOW()
WHERE deleted = 0
  AND menu_id = 103268;

UPDATE system_menu
SET deleted = 1,
    updater = '1',
    update_time = NOW()
WHERE id = 103268
  AND deleted = 0;

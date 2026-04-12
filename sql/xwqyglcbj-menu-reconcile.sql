-- ============================================================
-- XWQYGLCBJ menu reconciliation script
-- Align 数据维护/已建会缴筹备金 with canonical sjwh route + permissions
-- ============================================================

DROP TEMPORARY TABLE IF EXISTS tmp_xwqyglcbj_role_seed;

CREATE TEMPORARY TABLE tmp_xwqyglcbj_role_seed (
  role_id BIGINT NOT NULL,
  tenant_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, tenant_id)
);

INSERT INTO tmp_xwqyglcbj_role_seed (role_id, tenant_id)
SELECT DISTINCT role_id, COALESCE(tenant_id, 1)
FROM system_role_menu
WHERE deleted = 0
  AND menu_id IN (103087, 103088, 103089, 214996, 214997, 214998, 214999);

UPDATE system_menu
SET component = '/lghjft/sjwh/xwqyglcbj/index',
    component_name = 'LghjftSjwhXwqyglcbj',
    updater = '1',
    update_time = NOW()
WHERE id = 214996
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-xwqyglcbj:query',
    updater = '1',
    update_time = NOW()
WHERE id = 214997
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-xwqyglcbj:update',
    updater = '1',
    update_time = NOW()
WHERE id = 214998
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-xwqyglcbj:export',
    updater = '1',
    update_time = NOW()
WHERE id = 214999
  AND deleted = 0;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 205047, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xwqyglcbj_role_seed;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 214996, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xwqyglcbj_role_seed;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 214997, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xwqyglcbj_role_seed;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 214998, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xwqyglcbj_role_seed;

INSERT IGNORE INTO system_role_menu (role_id, menu_id, tenant_id, creator, create_time, updater, update_time, deleted)
SELECT role_id, 214999, tenant_id, '1', NOW(), '1', NOW(), 0
FROM tmp_xwqyglcbj_role_seed;

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
WHERE s.menu_id IN (205047, 214996, 214997, 214998, 214999);

UPDATE system_role_menu
SET deleted = 1,
    updater = '1',
    update_time = NOW()
WHERE deleted = 0
  AND menu_id IN (103087, 103088, 103089);

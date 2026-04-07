-- ============================================================
-- 迁移：合并 gh_qx_dlzh 到 system_users
-- 执行前请备份数据库！
-- ============================================================

-- Step 1: system_users 新增 shxydm 列
ALTER TABLE system_users
    ADD COLUMN shxydm varchar(20) DEFAULT NULL COMMENT '社会信用代码',
    ADD UNIQUE KEY system_users_shxydm_uindex (shxydm);

-- Step 2: 将 gh_qx_dlzh 中的用户插入 system_users（保留原 id）
--   条件：system_users 中不存在相同 id 且不存在相同 username
INSERT INTO system_users
    (id, username, password, nickname, remark, email, mobile, sex, avatar, status,
     login_ip, login_date, shxydm,
     creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    d.id, d.yhzh, d.password, d.yhxm, d.yhbz, d.yhyx, d.lxdh, d.yhxb, d.txdz, d.zt,
    d.login_ip, d.login_date, d.shxydm,
    d.creator, d.create_time, d.updater, d.update_time, d.deleted, d.tenant_id
FROM gh_qx_dlzh d
WHERE NOT EXISTS (SELECT 1 FROM system_users u WHERE u.id = d.id)
  AND NOT EXISTS (SELECT 1 FROM system_users u WHERE u.username = d.yhzh AND d.yhzh IS NOT NULL);

-- Step 3: 对已存在于 system_users（按 username 匹配）的用户，补全 shxydm / mobile
UPDATE system_users u
    JOIN gh_qx_dlzh d ON u.username = d.yhzh
SET u.shxydm = COALESCE(u.shxydm, d.shxydm),
    u.mobile  = COALESCE(u.mobile, d.lxdh)
WHERE d.yhzh IS NOT NULL;

-- Step 4: 修正 gh_qx_sfxx.dlzh_id
--   对 id 不同但 username 相同（Step 3 的那批）的用户，更新引用
UPDATE gh_qx_sfxx s
    JOIN gh_qx_dlzh d ON s.dlzh_id = d.id
    JOIN system_users u ON u.username = d.yhzh
SET s.dlzh_id = u.id
WHERE u.id != d.id;

-- Step 5: 删除旧表
DROP TABLE gh_qx_dlzh;

-- Step 6: 重命名 gh_qx_sfxx → system_user_sfxx
RENAME TABLE gh_qx_sfxx TO system_user_sfxx;

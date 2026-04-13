-- 经费处理 第三阶段（凭证管理容器）菜单/权限收敛
-- 基准：v1 sys_menu 2095 -> 2145 / 2151 / 2163
-- 目标：v2 system_menu 216020 -> 216021 / 216022 / 216023

START TRANSACTION;

UPDATE system_menu SET permission = 'lghjft:jfcl-lrthpz:query', update_time = NOW() WHERE id = 216055 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-lrthpz:create', update_time = NOW() WHERE id = 216056 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-lrthpz:update', update_time = NOW() WHERE id = 216057 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-lrthpz:delete', update_time = NOW() WHERE id = 216058 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-lrthpz:export', update_time = NOW() WHERE id = 216059 AND deleted = 0;

UPDATE system_menu SET permission = 'lghjft:jfcl-thpzcf:query', update_time = NOW() WHERE id = 216060 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-thpzcf:update', update_time = NOW() WHERE id = 216061 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-thpzcf:export', update_time = NOW() WHERE id = 216062 AND deleted = 0;

UPDATE system_menu SET permission = 'lghjft:jfcl-sgbflr:query', update_time = NOW() WHERE id = 216063 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-sgbflr:create', update_time = NOW() WHERE id = 216064 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-sgbflr:update', update_time = NOW() WHERE id = 216065 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-sgbflr:delete', update_time = NOW() WHERE id = 216066 AND deleted = 0;
UPDATE system_menu SET permission = 'lghjft:jfcl-sgbflr:export', update_time = NOW() WHERE id = 216067 AND deleted = 0;

COMMIT;

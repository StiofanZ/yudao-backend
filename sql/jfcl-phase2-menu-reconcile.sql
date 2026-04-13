-- 经费处理 第二阶段（特殊经费容器）菜单/权限收敛
-- 基准：v1 sys_menu 2094 -> 2121 / 2139 / 3106
-- 目标：v2 system_menu 216010 -> 216011 / 216012 / 216013

START TRANSACTION;

-- 处理特殊经费：查询 / 修改 / 导出
UPDATE system_menu
SET permission = 'lghjft:jfcl-tsjfcl:query',
    update_time = NOW()
WHERE id = 216047 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-tsjfcl:update',
    update_time = NOW()
WHERE id = 216048 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-tsjfcl:export',
    update_time = NOW()
WHERE id = 216049 AND deleted = 0;

-- 查询特殊经费：查询 / 导出
UPDATE system_menu
SET permission = 'lghjft:jfcl-tsjfcx:query',
    update_time = NOW()
WHERE id = 216050 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-tsjfcx:export',
    update_time = NOW()
WHERE id = 216051 AND deleted = 0;

-- 经费明细维护：查询 / 修改 / 导出
UPDATE system_menu
SET permission = 'lghjft:jfcl-jfmxwh:query',
    update_time = NOW()
WHERE id = 216052 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-jfmxwh:update',
    update_time = NOW()
WHERE id = 216053 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-jfmxwh:export',
    update_time = NOW()
WHERE id = 216054 AND deleted = 0;

COMMIT;

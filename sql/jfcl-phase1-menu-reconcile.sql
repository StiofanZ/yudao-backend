-- 经费处理 第一阶段（5 条主链）菜单/权限收敛
-- 基准：v1 sys_menu + 当前 v2 canonical 代码权限

START TRANSACTION;

-- 读取代收数据
UPDATE system_menu
SET permission = 'lghjft:jfcl-dqdssj:query'
WHERE id = 216035 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-dqdssj:create'
WHERE id = 216036 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-dqdssj:export'
WHERE id = 216717 AND deleted = 0;

-- 读取增量代收数据
UPDATE system_menu
SET component = 'lghjft/jfcl/dqzldssj/index',
    component_name = 'LghjftJfclDqzldssj'
WHERE id = 216002 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-dqzldssj:query'
WHERE id = 216037 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-dqzldssj:create'
WHERE id = 216038 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-dqzldssj:export'
WHERE id = 216718 AND deleted = 0;

-- 经费结算
UPDATE system_menu
SET permission = 'lghjft:jfcl-jfjs:query'
WHERE id = 216039 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-jfjs:update'
WHERE id = 216040 AND deleted = 0;

UPDATE system_menu
SET deleted = 1
WHERE id = 216041 AND deleted = 0;

-- 经费补结算
UPDATE system_menu
SET permission = 'lghjft:jfcl-jfbjs:query'
WHERE id = 216042 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-jfbjs:update'
WHERE id = 216043 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-jfbjs:export'
WHERE id = 216044 AND deleted = 0;

-- 生成划拨文件
UPDATE system_menu
SET permission = 'lghjft:jfcl-schbwj:query'
WHERE id = 216045 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-schbwj:create'
WHERE id = 216046 AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:jfcl-schbwj:export'
WHERE id = 216080 AND deleted = 0;

COMMIT;

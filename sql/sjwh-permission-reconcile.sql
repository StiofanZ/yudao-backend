-- 数据维护模块权限规范化
-- 目标：将应代收单位、入库未维护户籍、文件管理的旧权限前缀统一到 sjwh 规范前缀

START TRANSACTION;

UPDATE system_menu
SET permission = 'lghjft:sjwh-jhdwyds:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213653
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-jhdwyds:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 213656
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-swrksj:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 214981
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-swrksj:create',
    updater = 'codex',
    update_time = NOW()
WHERE id = 214982
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-swrksj:delete',
    updater = 'codex',
    update_time = NOW()
WHERE id = 214984
  AND deleted = 0;

UPDATE system_menu
SET permission = 'lghjft:sjwh-wjgl:query',
    updater = 'codex',
    update_time = NOW()
WHERE id = 215201
  AND deleted = 0;

COMMIT;

-- 数据维护 / 拨付信息排除解除 菜单收敛
-- 目标：
-- 1. 统一按钮权限到 lghjft:sjwh-bfzhpc:*
-- 2. 清理不符合 v1 能力集的 create/delete 按钮

START TRANSACTION;

UPDATE system_menu
SET permission = 'lghjft:sjwh-bfzhpc:query',
    updater = '1',
    update_time = NOW()
WHERE id = 213706
  AND deleted = b'0';

UPDATE system_role_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE menu_id IN (213707, 213709)
  AND deleted = b'0';

UPDATE system_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE id IN (213707, 213709)
  AND deleted = b'0';

COMMIT;

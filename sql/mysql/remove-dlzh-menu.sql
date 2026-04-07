-- 删除登录管理菜单及其子权限（已由用户管理-外部用户替代）
DELETE FROM system_menu WHERE id IN (213567, 213569, 213570, 213571, 213572, 213573);

-- 删除角色与这些菜单的关联
DELETE FROM system_role_menu WHERE menu_id IN (213567, 213569, 213570, 213571, 213572, 213573);

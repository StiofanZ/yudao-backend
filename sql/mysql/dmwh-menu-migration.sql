-- ============================================================
-- Migration: Rename dm module menus to lghjft/sjwh/dmwh pattern
-- Date: 2026-04-01
-- Description: Update component paths and permissions for
--   skgk, swjg, hjfl, yhwd, xzqh modules from dm/* to lghjft/sjwh/dmwh/*
--   Also update fpbl permissions from lghjft:fpbl:* to lghjft:dmwh-fpbl:*
-- ============================================================

-- 1. Update component paths (view routing)
UPDATE system_menu SET component = 'lghjft/sjwh/dmwh/skgk/index' WHERE component = 'dm/skgk/index' AND deleted = 0;
UPDATE system_menu SET component = 'lghjft/sjwh/dmwh/swjg/index' WHERE component = 'dm/swjg/index' AND deleted = 0;
UPDATE system_menu SET component = 'lghjft/sjwh/dmwh/hjfl/index' WHERE component = 'dm/hjfl/index' AND deleted = 0;
UPDATE system_menu SET component = 'lghjft/sjwh/dmwh/yhwd/index' WHERE component = 'dm/yhwd/index' AND deleted = 0;
UPDATE system_menu SET component = 'lghjft/sjwh/dmwh/xzqh/index' WHERE component = 'dm/xzqh/index' AND deleted = 0;

-- 2. Update permissions for dm modules
UPDATE system_menu SET permission = REPLACE(permission, 'dm:skgk:', 'lghjft:dmwh-skgk:') WHERE permission LIKE 'dm:skgk:%' AND deleted = 0;
UPDATE system_menu SET permission = REPLACE(permission, 'dm:swjg:', 'lghjft:dmwh-swjg:') WHERE permission LIKE 'dm:swjg:%' AND deleted = 0;
UPDATE system_menu SET permission = REPLACE(permission, 'dm:hjfl:', 'lghjft:dmwh-hjfl:') WHERE permission LIKE 'dm:hjfl:%' AND deleted = 0;
UPDATE system_menu SET permission = REPLACE(permission, 'dm:yhwd:', 'lghjft:dmwh-yhwd:') WHERE permission LIKE 'dm:yhwd:%' AND deleted = 0;
UPDATE system_menu SET permission = REPLACE(permission, 'dm:xzqh:', 'lghjft:dmwh-xzqh:') WHERE permission LIKE 'dm:xzqh:%' AND deleted = 0;

-- 3. Update fpbl permissions to match the unified dmwh pattern
UPDATE system_menu SET permission = REPLACE(permission, 'lghjft:fpbl:', 'lghjft:dmwh-fpbl:') WHERE permission LIKE 'lghjft:fpbl:%' AND deleted = 0;

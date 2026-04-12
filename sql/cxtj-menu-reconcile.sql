-- ============================================================
-- CXTJ menu reconciliation script
-- v1 real sys_menu has 16 visible query-stat features + 3 hidden entries
-- Prune v2-only extras, align visibility with v1,
-- and remove unused generated button menus based on active cxtj pages
-- ============================================================

UPDATE system_menu
SET visible = b'1',
    updater = '1',
    update_time = NOW()
WHERE id IN (
  214751, 214757, 214763, 214769, 214775, 214781, 214787, 214793,
  214799, 214805, 214811, 214817, 214823, 214829, 214835, 214853
)
  AND deleted = b'0';

UPDATE system_menu
SET visible = b'0',
    updater = '1',
    update_time = NOW()
WHERE id IN (214841, 214847, 214859)
  AND deleted = b'0';

UPDATE system_role_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE deleted = b'0'
  AND menu_id IN (
    214865, 214866, 214867, 214868, 214869, 214870,
    214871, 214872, 214873, 214874, 214875, 214876,
    214877, 214878, 214879, 214880, 214881, 214882,
    214883, 214884, 214885, 214886, 214887, 214888
  );

UPDATE system_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE id IN (
  214865, 214866, 214867, 214868, 214869, 214870,
  214871, 214872, 214873, 214874, 214875, 214876,
  214877, 214878, 214879, 214880, 214881, 214882,
  214883, 214884, 214885, 214886, 214887, 214888
)
  AND deleted = b'0';

UPDATE system_role_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE deleted = b'0'
  AND menu_id IN (
    214753, 214754, 214755,
    214759, 214760, 214761,
    214765, 214766, 214767,
    214771, 214772, 214773,
    214777, 214778, 214779,
    214783, 214784, 214785,
    214789, 214790, 214791,
    214795, 214796, 214797,
    214801, 214802, 214803,
    214807, 214808, 214809,
    214813, 214815,
    214819, 214821,
    214827,
    214831, 214832, 214833,
    214837, 214839,
    214855, 214857
  );

UPDATE system_menu
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE id IN (
  214753, 214754, 214755,
  214759, 214760, 214761,
  214765, 214766, 214767,
  214771, 214772, 214773,
  214777, 214778, 214779,
  214783, 214784, 214785,
  214789, 214790, 214791,
  214795, 214796, 214797,
  214801, 214802, 214803,
  214807, 214808, 214809,
  214813, 214815,
  214819, 214821,
  214827,
  214831, 214832, 214833,
  214837, 214839,
  214855, 214857
)
  AND deleted = b'0';

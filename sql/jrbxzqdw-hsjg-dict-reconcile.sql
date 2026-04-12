-- 补齐 v2 缺失的金融保险证券单位核实结果字典
-- 来源基准：v1 sys_dict_type / sys_dict_data 中的 sys_hsjg

INSERT INTO system_dict_type (
  name, type, status, remark, creator, create_time, updater, update_time, deleted
)
SELECT
  t.dict_name,
  t.dict_type,
  CAST(COALESCE(t.status, '0') AS UNSIGNED),
  t.remark,
  t.create_by,
  COALESCE(t.create_time, NOW()),
  t.update_by,
  COALESCE(t.update_time, NOW()),
  b'0'
FROM sys_dict_type t
WHERE t.dict_type = 'sys_hsjg'
  AND NOT EXISTS (
    SELECT 1
    FROM system_dict_type st
    WHERE st.type = t.dict_type
      AND st.deleted = b'0'
  );

INSERT INTO system_dict_data (
  sort, label, value, dict_type, status, color_type, css_class, remark,
  creator, create_time, updater, update_time, deleted
)
SELECT
  COALESCE(d.dict_sort, 0),
  d.dict_label,
  d.dict_value,
  d.dict_type,
  CAST(COALESCE(d.status, '0') AS UNSIGNED),
  d.list_class,
  d.css_class,
  d.remark,
  d.create_by,
  COALESCE(d.create_time, NOW()),
  d.update_by,
  COALESCE(d.update_time, NOW()),
  b'0'
FROM sys_dict_data d
WHERE d.dict_type = 'sys_hsjg'
  AND NOT EXISTS (
    SELECT 1
    FROM system_dict_data sd
    WHERE sd.dict_type = d.dict_type
      AND sd.value = d.dict_value
      AND sd.deleted = b'0'
  );

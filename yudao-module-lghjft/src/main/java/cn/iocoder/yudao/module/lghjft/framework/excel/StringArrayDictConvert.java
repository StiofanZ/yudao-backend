package cn.iocoder.yudao.module.lghjft.framework.excel;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 将 v1 兼容的 String[] 字段导出为逗号分隔文本，支持字典标签转换。
 */
public class StringArrayDictConvert implements Converter<String[]> {

    private static String getDictType(ExcelContentProperty contentProperty) {
        if (contentProperty == null || contentProperty.getField() == null) {
            return null;
        }
        DictFormat dictFormat = contentProperty.getField().getAnnotation(DictFormat.class);
        return dictFormat == null ? null : dictFormat.value();
    }

    private static String formatValue(String dictType, String value) {
        if (dictType == null || dictType.isEmpty()) {
            return value;
        }
        String label = DictFrameworkUtils.parseDictDataLabel(dictType, value);
        return label == null ? value : label;
    }

    @Override
    public Class<?> supportJavaTypeKey() {
        return String[].class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<String> convertToExcelData(String[] values, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (values == null || values.length == 0) {
            return new WriteCellData<>("");
        }
        String dictType = getDictType(contentProperty);
        String text = Arrays.stream(values)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(value -> formatValue(dictType, value))
                .collect(Collectors.joining(","));
        return new WriteCellData<>(text);
    }
}

package cn.iocoder.yudao.module.lghjft.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BackEnums {

    CBJTHBJ_Y("Y", "是"),
    CBJTHBJ_N("N", "否"),

    JSBJ_Y("Y", "结算"),
    JSBJ_N("N", "未结算"),
    JSBJ_E("E", "错误");

    private final String code;
    private final String desc;
}

package cn.iocoder.yudao.module.file.utils;

import lombok.Getter;

/**
 * 删除标志
 *
 * @author shipj
 */
@Getter
public enum DelFlag {

    NO_DEL("0", "未删除"), DEL("1", "已删除");

    private final String key;

    private final String value;

    DelFlag(String key, String value) {
        this.key = key;
        this.value = value;
    }

}

package cn.iocoder.yudao.module.dm.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode XZQH_NOT_EXISTS = new ErrorCode(2_000_000_001, "行政区划不存在");
    ErrorCode XZQH_PARENT_NOT_EXITS = new ErrorCode(2_000_000_002,"父级行政区划不存在");
    ErrorCode SWJG_NOT_EXISTS = new ErrorCode(2_000_000_003, "税务机关不存在");
}

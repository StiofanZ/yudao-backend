package cn.iocoder.yudao.module.lghjft.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode WF_SQ_TFSQ_NOT_EXISTS = new ErrorCode(2_000_000_000, "申请-退费申请不存在");
    ErrorCode BQ_HAS_VALID_DATA = new ErrorCode(2_000_000_001, "该标签下存在有效数据，禁止作废");
}

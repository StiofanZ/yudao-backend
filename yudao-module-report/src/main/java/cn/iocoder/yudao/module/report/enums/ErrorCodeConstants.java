package cn.iocoder.yudao.module.report.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Report 错误码枚举类
 *
 * report 系统，使用 1-003-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== GoView 模块 1-003-000-000 ==========
    ErrorCode GO_VIEW_PROJECT_NOT_EXISTS = new ErrorCode(1_003_000_000, "GoView 项目不存在");

    // ========== 报表快照 1-003-001-000 ==========
    ErrorCode BBHC_NOT_EXISTS = new ErrorCode(1_003_001_000, "当前报表快照不存在");
    ErrorCode BBHC_LS_NOT_EXISTS = new ErrorCode(1_003_001_001, "历史报表快照不存在");
    ErrorCode BBHC_BB_NOT_EXISTS = new ErrorCode(1_003_001_002, "积木报表不存在");
    ErrorCode BBHC_CQSC_FAIL = new ErrorCode(1_003_001_003, "报表快照重生成失败");

}

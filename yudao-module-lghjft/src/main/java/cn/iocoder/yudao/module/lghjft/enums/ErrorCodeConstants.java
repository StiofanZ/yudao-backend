package cn.iocoder.yudao.module.lghjft.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode WF_SQ_TFSQ_NOT_EXISTS = new ErrorCode(2_000_000_000, "申请-退费申请不存在");
    ErrorCode BQ_HAS_VALID_DATA = new ErrorCode(2_000_000_001, "该标签下存在有效数据，禁止作废");

    ErrorCode FPBL_COPY_NOT_EXISTS = new ErrorCode(2_004_000_001, "分配比例不存在");
    ErrorCode YHWD_NOT_EXISTS = new ErrorCode(2_001_000_001, "银行网点不存在");
    ErrorCode SKGK_NOT_EXISTS = new ErrorCode(2_005_000_001, "收款国库不存在");
    ErrorCode HJFL_NOT_EXISTS = new ErrorCode(2_006_000_001, "户籍分类不存在");
    ErrorCode RWS_NOT_EXISTS = new ErrorCode(2_007_000_001, "年度任务不存在");

    ErrorCode WTFK_NOT_EXISTS = new ErrorCode(2_008_000_001, "问题反馈不存在");
    ErrorCode CONTENT_NOT_EXISTS = new ErrorCode(2_009_000_001, "内容管理不存在");
    ErrorCode MARKER_INFO_NOT_EXISTS = new ErrorCode(2_010_000_001, "高德地图标注点信息不存在");
    ErrorCode SFXX_NOT_EXISTS = new ErrorCode(2_011_000_001, "身份信息不存在");
    ErrorCode DLZH_NOT_EXISTS = new ErrorCode(2_012_000_001, "登录账号不存在");
    ErrorCode DLZH_PASSWORD_REQUIRED = new ErrorCode(2_012_000_002, "密码不能为空");
    ErrorCode DLZH_YHZH_EXISTS = new ErrorCode(2_012_000_003, "用户账号已存在");
    ErrorCode DLZH_LXDH_EXISTS = new ErrorCode(2_012_000_004, "联系电话已存在");
    ErrorCode DLZH_YHYX_EXISTS = new ErrorCode(2_012_000_005, "用户邮箱已存在");
    ErrorCode DLZH_SHXYDM_EXISTS = new ErrorCode(2_012_000_006, "社会信用代码已存在");
    ErrorCode WF_HZJF_SQ_NOT_EXISTS = new ErrorCode(2_011_000_001, "汇总缴费申请不存在");
    ErrorCode  WF_TDF_SQ_NOT_EXISTS= new ErrorCode(2_012_000_001, "退还申请不存在");
    ErrorCode WF_JFHJ_SQ_NOT_EXISTS = new ErrorCode(2_012_000_002, "缴费缓缴申请不存在");

}

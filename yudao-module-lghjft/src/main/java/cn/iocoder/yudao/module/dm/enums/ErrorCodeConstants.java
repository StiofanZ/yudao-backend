package cn.iocoder.yudao.module.dm.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
//行政区划
    ErrorCode XZQH_NOT_EXISTS = new ErrorCode(2_000_000_001, "行政区划不存在");
    ErrorCode XZQH_EXITS_CHILDREN = new ErrorCode(2_000_000_002, "存在存在子行政区划，无法删除");
    ErrorCode XZQH_PARENT_NOT_EXITS = new ErrorCode(2_000_000_003,"父级行政区划不存在");
    ErrorCode XZQH_PARENT_ERROR = new ErrorCode(2_000_000_004, "不能设置自己为父行政区划");
    ErrorCode XZQH_XZQHMC_DUPLICATE = new ErrorCode(2_000_000_005, "已经存在该行政区划名称的行政区划");
    ErrorCode XZQH_PARENT_IS_CHILD = new ErrorCode(2_000_000_006, "不能设置自己的子Xzqh为父Xzqh");
//银行网点
    ErrorCode YHWD_NOT_EXISTS = new ErrorCode(2_001_000_001, "银行网点不存在");
    ErrorCode YHWD_EXITS_CHILDREN = new ErrorCode(2_001_000_002, "存在存在子银行网点，无法删除");
    ErrorCode YHWD_PARENT_NOT_EXITS = new ErrorCode(2_001_000_003,"父级银行网点不存在");
    ErrorCode YHWD_PARENT_ERROR = new ErrorCode(2_001_000_004, "不能设置自己为父银行网点");
    ErrorCode YHWD_YHWDMC_DUPLICATE = new ErrorCode(2_001_000_005, "已经存在该网点名称的银行网点");
    ErrorCode YHWD_PARENT_IS_CHILD = new ErrorCode(2_001_000_006, "不能设置自己的子Yhwd为父Yhwd");
    // 如果没有，需要添加
    ErrorCode YHWD_BANK_EMPTY = new ErrorCode(2_001_000_007, "请选择银行");
    ErrorCode YHWD_BANK_FORMAT_ERROR = new ErrorCode(2_001_000_008, "银行代码格式错误");
//税务机关
    ErrorCode SWJG_NOT_EXISTS = new ErrorCode(2_002_000_001, "税务机关不存在");
    ErrorCode SWJG_EXITS_CHILDREN = new ErrorCode(2_002_000_002, "存在存在子税务机关，无法删除");
    ErrorCode SWJG_PARENT_NOT_EXITS = new ErrorCode(2_002_000_003,"父级税务机关不存在");
    ErrorCode SWJG_PARENT_ERROR = new ErrorCode(2_002_000_004, "不能设置自己为父税务机关");
    ErrorCode SWJG_SWJGMC_DUPLICATE = new ErrorCode(2_002_000_005, "已经存在该税务机关名称的税务机关");
    ErrorCode SWJG_PARENT_IS_CHILD = new ErrorCode(2_002_000_006, "不能设置自己的子Swjg为父Swjg");

    ErrorCode YHHB_NOT_EXISTS = new ErrorCode(2_003_000_001, "银行行别不存在");
    ErrorCode FPBL_COPY_NOT_EXISTS = new ErrorCode(2_004_000_001, "分配比例不存在");









}

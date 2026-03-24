package cn.iocoder.yudao.module.lghjft.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode WF_SQ_TFSQ_NOT_EXISTS = new ErrorCode(2_000_000_000, "申请-退费申请不存在");
    ErrorCode BQ_HAS_VALID_DATA = new ErrorCode(2_000_000_001, "该标签下存在有效数据，禁止作废");
    ErrorCode BQGL_BQ_EXISTS = new ErrorCode(2_000_000_002, "标签名称已存在");
    ErrorCode BQGL_BQ_NOT_EXISTS = new ErrorCode(2_000_000_003, "标签不存在");

    ErrorCode FPBL_COPY_NOT_EXISTS = new ErrorCode(2_004_000_001, "分配比例不存在");
    ErrorCode YHWD_NOT_EXISTS = new ErrorCode(2_001_000_001, "银行网点不存在");
    ErrorCode SKGK_NOT_EXISTS = new ErrorCode(2_005_000_001, "收款国库不存在");
    ErrorCode HJFL_NOT_EXISTS = new ErrorCode(2_006_000_001, "户籍分类不存在");
    ErrorCode RWS_NOT_EXISTS = new ErrorCode(2_007_000_001, "年度任务不存在");

    ErrorCode WTFK_NOT_EXISTS = new ErrorCode(2_008_000_001, "问题反馈不存在");
    ErrorCode CONTENT_NOT_EXISTS = new ErrorCode(2_009_000_001, "内容管理不存在");
    ErrorCode MARKER_INFO_NOT_EXISTS = new ErrorCode(2_010_000_001, "高德地图标注点信息不存在");

    // SVN additions
    ErrorCode WF_HZJF_SQ_NOT_EXISTS = new ErrorCode(2_011_000_001, "汇总缴费申请不存在");
    ErrorCode WF_TDF_SQ_NOT_EXISTS = new ErrorCode(2_012_000_001, "退还申请不存在");
    ErrorCode WF_JFHJ_SQ_NOT_EXISTS = new ErrorCode(2_012_000_002, "缴费缓缴申请不存在");

    // My additions (Renumbered)
    ErrorCode SFXX_NOT_EXISTS = new ErrorCode(2_015_000_001, "身份信息不存在");
    ErrorCode SFXX_REJECT_REASON_REQUIRED = new ErrorCode(2_015_000_002, "拒绝授权时，拒绝原因不能为空");

    ErrorCode DLZH_NOT_EXISTS = new ErrorCode(2_016_000_001, "登录账号不存在");
    ErrorCode DLZH_PASSWORD_REQUIRED = new ErrorCode(2_016_000_002, "密码不能为空");
    ErrorCode DLZH_YHZH_EXISTS = new ErrorCode(2_016_000_003, "用户账号已存在");
    ErrorCode DLZH_LXDH_EXISTS = new ErrorCode(2_016_000_004, "联系电话已存在");
    ErrorCode DLZH_YHYX_EXISTS = new ErrorCode(2_016_000_005, "用户邮箱已存在");
    ErrorCode DLZH_SHXYDM_EXISTS = new ErrorCode(2_016_000_006, "社会信用代码已存在");

    ErrorCode GH_HJ_JCXX_NOT_EXISTS = new ErrorCode(2_017_000_001, "户籍基础信息不存在");
    ErrorCode GH_HJ_JCXX_DJXH_EXISTS = new ErrorCode(2_017_000_002, "户籍基础信息登记序号已存在");
    ErrorCode GH_JF_NOT_EXISTS = new ErrorCode(2_018_000_001, "税务入库不存在");
    ErrorCode JHDWYDS_NOT_EXISTS = new ErrorCode(2_018_000_002, "应代收单位不存在");

    ErrorCode HKXX_NOT_EXISTS = new ErrorCode(2_019_000_001, "基层经费到账对象不存在");
    ErrorCode JF_NOT_EXISTS = new ErrorCode(2_019_000_002, " 经费明细对象不存在");

    ErrorCode ZHWH_NOT_EXISTS = new ErrorCode(2_020_000_001, "账户维护申请不存在");
    ErrorCode ZHWH_STATUS_NOT_PENDING = new ErrorCode(2_020_000_002, "当前申请不是待审核状态，不能继续处理");
    ErrorCode ZHWH_REJECT_REASON_REQUIRED = new ErrorCode(2_020_000_003, "驳回账户维护申请时，审核意见不能为空");
    ErrorCode WF_DBSQ_NOT_EXISTS = new ErrorCode(2_023_000_003, "该单位今天已提交过申请，不可重复提交！");
    ErrorCode WF_DBSQ_NOT_STATUS = new ErrorCode(2_023_000_004, "工会已停用，无法提交申请");

    ErrorCode AQZX_VERIFY_MOBILE_NOT_EXISTS = new ErrorCode(2_021_000_001, "当前账号未配置可校验手机号");

    ErrorCode DWXXSP_BUSINESS_TYPE_NOT_SUPPORT = new ErrorCode(2_022_000_001, "暂不支持的审批业务类型");

    ErrorCode BBBM_NOT_SUPPORT = new ErrorCode(2_023_000_001, "报表编码不支持");
    ErrorCode BB_ZX_FAIL = new ErrorCode(2_023_000_002, "报表执行失败：{}");

    ErrorCode YHBFMX_NOT_EXISTS = new ErrorCode(2_023_000_003, "银行拨付明细不存在");

    ErrorCode YHBFHZ_NOT_EXISTS = new ErrorCode(2_023_000_004, "银行拨付汇总不存在");
    // 银行拨付
    ErrorCode YHBFMX_PARAM_ERROR = new ErrorCode(2_023_000_005, "参数不能为空！");
    ErrorCode YHBFMX_NO_DATA = new ErrorCode(2_023_000_006, "未查询到可处理的数据！");
    ErrorCode YHBFMX_HAS_GENERATED = new ErrorCode(2_023_000_007, "已生成汇总，不可重复生成！");
    ErrorCode YHBFMX_BEGIN_ERROR = new ErrorCode(2_023_000_008, "开始时间和结束时间不能为空！");
    ErrorCode YHBFMX_THCB_DATA = new ErrorCode(2_023_000_009, "未查询到可退回重拨数据！");
    ErrorCode YHBFJGCX_NOT_EXISTS = new ErrorCode(2_023_000_010, "银行拨付结果查询不存在");

    ErrorCode WF_HZJF_UNIT_ARREARS = new ErrorCode(1000900, "汇总单位【{}】存在欠缴，请先结清");
    ErrorCode WF_HZJF_SUB_UNIT_ARREARS = new ErrorCode(1000901, "下属单位【{}】存在欠缴，请先结清");
    ErrorCode WF_HZJF_MAIN_DEPTID_EMPTY = new ErrorCode(1000908, "汇总单位【{}】未查询到主管工会");
    ErrorCode WF_HZJF_SUB_DEPTID_EMPTY = new ErrorCode(1000909, "下属单位【{}】未查询到主管工会");
    ErrorCode WF_HZJF_NOT_SAME_DEPT = new ErrorCode(1000910, "下属单位【{}】所属工会({})与汇总单位工会({})不一致，不允许提交");
    ErrorCode HKXX_BFZHPC_NOT_EXISTS = new ErrorCode(2_023_000_011, "拨付信息不存在");
}

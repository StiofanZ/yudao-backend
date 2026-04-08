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

    ErrorCode GH_HJ_JCXX_NOT_EXISTS = new ErrorCode(2_017_000_001, "户籍基础信息不存在");
    ErrorCode GH_HJ_JCXX_DJXH_EXISTS = new ErrorCode(2_017_000_002, "户籍基础信息登记序号已存在");
    ErrorCode GH_JF_NOT_EXISTS = new ErrorCode(2_018_000_001, "税务入库不存在");
    ErrorCode JHDWYDS_NOT_EXISTS = new ErrorCode(2_018_000_002, "应代收单位不存在");

    ErrorCode HKXX_NOT_EXISTS = new ErrorCode(2_019_000_001, "基层经费到账对象不存在");
    ErrorCode JF_NOT_EXISTS = new ErrorCode(2_019_000_002, " 经费明细对象不存在");
    ErrorCode DHJFTZ_NOT_EXISTS = new ErrorCode(2_019_000_003, "到户经费台账不存在");
    ErrorCode NDRWWC_NOT_EXISTS = new ErrorCode(2_019_000_004, "分上缴周期统计不存在");
    ErrorCode TOP_NOT_EXISTS = new ErrorCode(2_019_000_005, "缴费排行不存在");
    ErrorCode GHJFJFDW_NOT_EXISTS = new ErrorCode(2_019_000_006, "近三年缴费情况不存在");
    ErrorCode ZGJRGH_NOT_EXISTS = new ErrorCode(2_019_000_007, "金融工会信息核对不存在");
    ErrorCode ZSWZGDW_NOT_EXISTS = new ErrorCode(2_019_000_008, "征收未主管单位不存在");

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

    ErrorCode SYS_TREE_DICT_NOT_EXISTS = new ErrorCode(2_024_000_001, "树形字典不存在");
    ErrorCode SYS_TREE_DICT_DATA_NOT_EXISTS = new ErrorCode(2_024_000_002, "树形字典数据不存在");

    // XEJF module
    ErrorCode XEJF24_NOT_EXISTS = new ErrorCode(2_025_000_001, "24年小额确认不存在");
    ErrorCode XEJFOLD_NOT_EXISTS = new ErrorCode(2_025_000_002, "23年小额确认不存在");
    ErrorCode HKXXXEJF_NOT_EXISTS = new ErrorCode(2_025_000_003, "小额拨付记账凭证不存在");
    ErrorCode HKXXXEJFCBJ_NOT_EXISTS = new ErrorCode(2_025_000_004, "小额筹备金做账不存在");
    ErrorCode HKXXXEJFJCDZ_NOT_EXISTS = new ErrorCode(2_025_000_005, "小额缴费基层到账不存在");
    ErrorCode XEJF2023_NOT_EXISTS = new ErrorCode(2_025_000_006, "小额缴费明细不存在");
    ErrorCode XEJFZZGL_NOT_EXISTS = new ErrorCode(2_025_000_007, "小额缴费组织管理不存在");
    ErrorCode XETZ_NOT_EXISTS = new ErrorCode(2_025_000_008, "小额台账不存在");
    ErrorCode XEBF_NOT_EXISTS = new ErrorCode(2_025_000_009, "小额缴费拨付台账不存在");
    ErrorCode XEBFZB_NOT_EXISTS = new ErrorCode(2_025_000_010, "小额拨付占比不存在");

    // JFCL module
    ErrorCode JFCL_NOT_EXISTS = new ErrorCode(2_026_000_001, "经费处理记录不存在");
    ErrorCode JFCL_JFJS_NO_DATA = new ErrorCode(2_026_000_002, "无有效数据进行结算");
    ErrorCode JFCL_JFBJS_NO_DATA = new ErrorCode(2_026_000_003, "无有效数据进行结算");

    // HJGL module - special operations
    ErrorCode HJGL_DJXHS_EMPTY = new ErrorCode(2_027_000_001, "登记序号列表不能为空");
    ErrorCode HJGL_ALLOCATION_PARAM_EMPTY = new ErrorCode(2_027_000_002, "参数为空，无法调拨");
    ErrorCode HJGL_ALLOCATION_FAILED = new ErrorCode(2_027_000_003, "户籍调拨失败，未找到匹配记录");

    // IDOR protection
    ErrorCode OPERATION_NOT_PERMITTED = new ErrorCode(2_028_000_001, "无权操作该记录");

    ErrorCode YJHXX_NOT_EXISTS = new ErrorCode(2_029_000_001, "已建会信息不存在");

    ErrorCode XZQH_NOT_EXISTS = new ErrorCode(2_030_000_001, "行政区划不存在");
    ErrorCode SWJG_NOT_EXISTS = new ErrorCode(2_030_000_002, "税务机关不存在");
    ErrorCode FPBL_NOT_EXISTS = new ErrorCode(2_030_000_003, "分配比例不存在");
}

package cn.iocoder.yudao.module.lghjft.service.qx.dwxxsp;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspAuditReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspDetailResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhAuditReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.GhQxSfxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.zhwh.GhQxZhwhMapper;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.zhwh.ZhwhService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.DWXXSP_BUSINESS_TYPE_NOT_SUPPORT;

@Service
@Validated
public class DwxxspServiceImpl implements DwxxspService {

    public static final String BUSINESS_TYPE_ACCOUNT_CHANGE = "ACCOUNT_CHANGE";
    public static final String BUSINESS_TYPE_IDENTITY = "IDENTITY";

    @Resource
    private GhQxZhwhMapper ghQxZhwhMapper;
    @Resource
    private GhQxSfxxMapper ghQxSfxxMapper;
    @Resource
    private GhQxDlzhService ghQxDlzhService;
    @Resource
    private NsrxxService nsrxxService;
    @Resource
    private DeptApi deptApi;
    @Resource
    private ZhwhService zhwhService;
    @Resource
    private GhQxSfxxService ghQxSfxxService;

    @Override
    public PageResult<DwxxspResVO> getDwxxspPage(DwxxspPageReqVO reqVO) {
        List<DwxxspResVO> records = new ArrayList<>();
        if (StrUtil.isBlank(reqVO.getBusinessType()) || BUSINESS_TYPE_ACCOUNT_CHANGE.equals(reqVO.getBusinessType())) {
            records.addAll(buildAccountChangeRecords(reqVO));
        }
        if (StrUtil.isBlank(reqVO.getBusinessType()) || BUSINESS_TYPE_IDENTITY.equals(reqVO.getBusinessType())) {
            records.addAll(buildIdentityRecords(reqVO));
        }

        records = records.stream()
                .filter(item -> matchKeyword(item, reqVO.getKeyword()))
                .sorted(Comparator.comparing(DwxxspResVO::getCreateTime, Comparator.nullsLast(LocalDateTime::compareTo)).reversed())
                .toList();
        int fromIndex = Math.max((reqVO.getPageNo() - 1) * reqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + reqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    @Override
    public DwxxspDetailResVO getDwxxspDetail(String businessType, Long businessId) {
        if (BUSINESS_TYPE_ACCOUNT_CHANGE.equals(businessType)) {
            return buildAccountChangeDetail(zhwhService.getZhwh(businessId));
        }
        if (BUSINESS_TYPE_IDENTITY.equals(businessType)) {
            return buildIdentityDetail(ghQxSfxxService.getSfxx(businessId));
        }
        throw exception(DWXXSP_BUSINESS_TYPE_NOT_SUPPORT);
    }

    @Override
    public void audit(DwxxspAuditReqVO reqVO) {
        if (BUSINESS_TYPE_ACCOUNT_CHANGE.equals(reqVO.getBusinessType())) {
            ZhwhAuditReqVO zhwhAuditReqVO = new ZhwhAuditReqVO();
            zhwhAuditReqVO.setId(reqVO.getBusinessId());
            zhwhAuditReqVO.setStatus(reqVO.getStatus());
            zhwhAuditReqVO.setRemark(reqVO.getRemark());
            zhwhService.auditZhwh(zhwhAuditReqVO);
            return;
        }
        if (BUSINESS_TYPE_IDENTITY.equals(reqVO.getBusinessType())) {
            ghQxSfxxService.auditSfxx(reqVO.getBusinessId(), reqVO.getStatus(), reqVO.getRemark());
            return;
        }
        throw exception(DWXXSP_BUSINESS_TYPE_NOT_SUPPORT);
    }

    private List<DwxxspResVO> buildAccountChangeRecords(DwxxspPageReqVO reqVO) {
        return ghQxZhwhMapper.selectList(new LambdaQueryWrapperX<GhQxZhwhDO>()
                        .eqIfPresent(GhQxZhwhDO::getStatus, reqVO.getStatus())
                        .orderByDesc(GhQxZhwhDO::getCreateTime)
                        .orderByDesc(GhQxZhwhDO::getId))
                .stream()
                .map(this::buildAccountChangeRecord)
                .toList();
    }

    private DwxxspResVO buildAccountChangeRecord(GhQxZhwhDO zhwh) {
        if (zhwh == null) {
            return null;
        }
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(zhwh.getDlzhId());
        DeptRespDTO dept = zhwh.getDeptId() != null ? deptApi.getDept(zhwh.getDeptId()) : null;
        DwxxspResVO respVO = new DwxxspResVO();
        respVO.setBusinessType(BUSINESS_TYPE_ACCOUNT_CHANGE);
        respVO.setBusinessId(zhwh.getId());
        respVO.setApplyNo(zhwh.getApplyNo());
        respVO.setApplicantName(dlzh != null ? dlzh.getYhxm() : null);
        respVO.setApplicantPhone(dlzh != null ? dlzh.getLxdh() : null);
        respVO.setDwmc(zhwh.getDwmc());
        respVO.setShxydm(zhwh.getShxydm());
        respVO.setSummary("账户变更：" + StrUtil.blankToDefault(zhwh.getOldJcghzh(), "-") + " -> "
                + StrUtil.blankToDefault(zhwh.getNewJcghzh(), "-"));
        respVO.setDeptId(zhwh.getDeptId());
        respVO.setDeptName(dept != null ? dept.getName() : null);
        respVO.setStatus(zhwh.getStatus());
        respVO.setCreateTime(zhwh.getCreateTime());
        return respVO;
    }

    private List<DwxxspResVO> buildIdentityRecords(DwxxspPageReqVO reqVO) {
        return ghQxSfxxMapper.selectList(new LambdaQueryWrapperX<GhQxSfxxDO>()
                        .eqIfPresent(GhQxSfxxDO::getStatus, reqVO.getStatus())
                        .orderByDesc(GhQxSfxxDO::getCreateTime)
                        .orderByDesc(GhQxSfxxDO::getId))
                .stream()
                .map(this::buildIdentityRecord)
                .toList();
    }

    private DwxxspResVO buildIdentityRecord(GhQxSfxxDO sfxx) {
        if (sfxx == null) {
            return null;
        }
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(sfxx.getDlzhId());
        NsrxxDO nsrxx = nsrxxService.getNsrxx(sfxx.getDjxh());
        DeptRespDTO dept = sfxx.getDeptId() != null ? deptApi.getDept(sfxx.getDeptId()) : null;
        DwxxspResVO respVO = new DwxxspResVO();
        respVO.setBusinessType(BUSINESS_TYPE_IDENTITY);
        respVO.setBusinessId(sfxx.getId());
        respVO.setApplyNo("SFXX-" + sfxx.getId());
        respVO.setApplicantName(dlzh != null ? dlzh.getYhxm() : null);
        respVO.setApplicantPhone(dlzh != null ? dlzh.getLxdh() : null);
        respVO.setDwmc(nsrxx != null ? nsrxx.getNsrmc() : null);
        respVO.setShxydm(nsrxx != null ? StrUtil.blankToDefault(nsrxx.getShxydm(), nsrxx.getNsrsbh()) : null);
        respVO.setSummary("身份授权：" + toSflxText(sfxx.getSflx()) + " / " + toQxlxText(sfxx.getQxlx()));
        respVO.setDeptId(sfxx.getDeptId());
        respVO.setDeptName(dept != null ? dept.getName() : null);
        respVO.setStatus(sfxx.getStatus());
        respVO.setCreateTime(sfxx.getCreateTime());
        return respVO;
    }

    private DwxxspDetailResVO buildAccountChangeDetail(GhQxZhwhDO zhwh) {
        if (zhwh == null) {
            return null;
        }
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(zhwh.getDlzhId());
        DeptRespDTO dept = zhwh.getDeptId() != null ? deptApi.getDept(zhwh.getDeptId()) : null;
        DwxxspDetailResVO detail = new DwxxspDetailResVO();
        detail.setBusinessType(BUSINESS_TYPE_ACCOUNT_CHANGE);
        detail.setBusinessId(zhwh.getId());
        detail.setApplyNo(zhwh.getApplyNo());
        detail.setStatus(zhwh.getStatus());
        detail.setApplicantName(dlzh != null ? dlzh.getYhxm() : null);
        detail.setApplicantPhone(dlzh != null ? dlzh.getLxdh() : null);
        detail.setDwmc(zhwh.getDwmc());
        detail.setShxydm(zhwh.getShxydm());
        detail.setDeptName(dept != null ? dept.getName() : null);
        detail.setCreateTime(zhwh.getCreateTime());
        detail.setApplyReason(zhwh.getApplyReason());
        detail.setAuditRemark(zhwh.getAuditRemark());
        detail.setOldJcghzh(zhwh.getOldJcghzh());
        detail.setOldJcghhm(zhwh.getOldJcghhm());
        detail.setOldJcghhh(zhwh.getOldJcghhh());
        detail.setOldJcghyh(zhwh.getOldJcghyh());
        detail.setNewJcghzh(zhwh.getNewJcghzh());
        detail.setNewJcghhm(zhwh.getNewJcghhm());
        detail.setNewJcghhh(zhwh.getNewJcghhh());
        detail.setNewJcghyh(zhwh.getNewJcghyh());
        detail.setMaterials(zhwh.getMaterials());
        return detail;
    }

    private DwxxspDetailResVO buildIdentityDetail(GhQxSfxxDO sfxx) {
        if (sfxx == null) {
            return null;
        }
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(sfxx.getDlzhId());
        NsrxxDO nsrxx = nsrxxService.getNsrxx(sfxx.getDjxh());
        DeptRespDTO dept = sfxx.getDeptId() != null ? deptApi.getDept(sfxx.getDeptId()) : null;
        DwxxspDetailResVO detail = new DwxxspDetailResVO();
        detail.setBusinessType(BUSINESS_TYPE_IDENTITY);
        detail.setBusinessId(sfxx.getId());
        detail.setApplyNo("SFXX-" + sfxx.getId());
        detail.setStatus(sfxx.getStatus());
        detail.setApplicantName(dlzh != null ? dlzh.getYhxm() : null);
        detail.setApplicantPhone(dlzh != null ? dlzh.getLxdh() : null);
        detail.setDwmc(nsrxx != null ? nsrxx.getNsrmc() : null);
        detail.setShxydm(nsrxx != null ? StrUtil.blankToDefault(nsrxx.getShxydm(), nsrxx.getNsrsbh()) : null);
        detail.setDeptName(dept != null ? dept.getName() : null);
        detail.setCreateTime(sfxx.getCreateTime());
        detail.setApplyReason(sfxx.getSqyy());
        detail.setAuditRemark(sfxx.getJjyy());
        detail.setSflx(sfxx.getSflx());
        detail.setGhlx(sfxx.getGhlx());
        detail.setQxlx(sfxx.getQxlx());
        detail.setRejectReason(sfxx.getJjyy());
        return detail;
    }

    private boolean matchKeyword(DwxxspResVO item, String keyword) {
        if (StrUtil.isBlank(keyword)) {
            return true;
        }
        return StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getApplyNo()), keyword)
                || StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getApplicantName()), keyword)
                || StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getApplicantPhone()), keyword)
                || StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getDwmc()), keyword)
                || StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getShxydm()), keyword)
                || StrUtil.containsIgnoreCase(StrUtil.nullToEmpty(item.getSummary()), keyword);
    }

    private String toSflxText(String sflx) {
        return switch (StrUtil.nullToEmpty(sflx)) {
            case "01" -> "法定代表人";
            case "02" -> "财务负责人";
            default -> "未知身份";
        };
    }

    private String toQxlxText(String qxlx) {
        return switch (StrUtil.nullToEmpty(qxlx)) {
            case "01" -> "管理员";
            case "02" -> "一般人";
            default -> "未知权限";
        };
    }
}

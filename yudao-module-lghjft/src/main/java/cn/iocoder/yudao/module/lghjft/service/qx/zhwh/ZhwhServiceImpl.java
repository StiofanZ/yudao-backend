package cn.iocoder.yudao.module.lghjft.service.qx.zhwh;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhjyhxx.GhHjYhxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhjyhxx.GhHjYhxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.jcxx.GhHjJcxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.GhQxSfxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.zhwh.GhQxZhwhMapper;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ZhwhServiceImpl implements ZhwhService {

    @Resource
    private GhQxZhwhMapper ghQxZhwhMapper;
    @Resource
    private GhQxDlzhService ghQxDlzhService;
    @Resource
    private JcxxService jcxxService;
    @Resource
    private GhQxSfxxMapper ghQxSfxxMapper;
    @Resource
    private GhHjJcxxMapper ghHjJcxxMapper;
    @Resource
    private GhHjYhxxMapper ghHjYhxxMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createZhwh(ZhwhCreateReqVO createReqVO) {
        GhQxZhwhDO zhwh = buildZhwhDO(createReqVO, null);
        zhwh.setApplyNo("ZHWH" + System.currentTimeMillis());
        zhwh.setStatus(0);
        zhwh.setSyncStatus(0);
        ghQxZhwhMapper.insert(zhwh);
        return zhwh.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZhwh(ZhwhUpdateReqVO updateReqVO) {
        GhQxZhwhDO exist = validatePending(updateReqVO.getId());
        GhQxZhwhDO updateObj = buildZhwhDO(updateReqVO, exist);
        updateObj.setId(exist.getId());
        updateObj.setApplyNo(exist.getApplyNo());
        updateObj.setStatus(exist.getStatus());
        updateObj.setSyncStatus(exist.getSyncStatus());
        updateObj.setSyncMessage(exist.getSyncMessage());
        updateObj.setSyncTime(exist.getSyncTime());
        updateObj.setAuditRemark(null);
        updateObj.setAuditTime(null);
        updateObj.setAuditUserId(null);
        ghQxZhwhMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZhwh(Long id) {
        validatePending(id);
        ghQxZhwhMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelZhwh(Long id) {
        GhQxZhwhDO exist = validatePending(id);
        ghQxZhwhMapper.updateById(GhQxZhwhDO.builder()
                .id(exist.getId())
                .status(3)
                .syncStatus(0)
                .syncMessage("申请已撤回")
                .syncTime(LocalDateTime.now())
                .build());
    }

    @Override
    public GhQxZhwhDO getZhwh(Long id) {
        return ghQxZhwhMapper.selectById(id);
    }

    @Override
    public PageResult<GhQxZhwhDO> getZhwhPage(ZhwhPageReqVO pageReqVO) {
        return ghQxZhwhMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditZhwh(ZhwhAuditReqVO reqVO) {
        GhQxZhwhDO exist = validatePending(reqVO.getId());
        Integer status = reqVO.getStatus();
        if (Integer.valueOf(2).equals(status) && StrUtil.isBlank(reqVO.getRemark())) {
            throw exception(ZHWH_REJECT_REASON_REQUIRED);
        }
        if (Integer.valueOf(1).equals(status)) {
            syncBankInfo(exist);
        }
        ghQxZhwhMapper.updateById(GhQxZhwhDO.builder()
                .id(exist.getId())
                .status(status)
                .auditRemark(reqVO.getRemark())
                .auditUserId(SecurityFrameworkUtils.getLoginUserId())
                .auditTime(LocalDateTime.now())
                .syncStatus(Integer.valueOf(1).equals(status) ? 1 : 0)
                .syncMessage(Integer.valueOf(1).equals(status)
                        ? "已触发工会/税务账户同步（模拟）"
                        : StrUtil.blankToDefault(reqVO.getRemark(), "申请已驳回"))
                .syncTime(LocalDateTime.now())
                .build());
    }

    private GhQxZhwhDO buildZhwhDO(ZhwhBaseVO reqVO, GhQxZhwhDO exist) {
        Long dlzhId = reqVO.getDlzhId() != null ? reqVO.getDlzhId() : SecurityFrameworkUtils.getLoginUserId();
        GhQxDlzhDO dlzh = ghQxDlzhService.getDlzh(dlzhId);
        if (dlzh == null) {
            throw exception(ZHWH_NOT_EXISTS);
        }
        GhHjJcxxDO jcxx = validateJcxxExists(reqVO.getDjxh());
        GhQxZhwhDO zhwh = BeanUtils.toBean(reqVO, GhQxZhwhDO.class);
        zhwh.setDlzhId(dlzhId);
        zhwh.setDeptId(resolveDeptId(dlzhId, reqVO.getDjxh(), exist));
        zhwh.setShxydm(StrUtil.blankToDefault(jcxx.getShxydm(), jcxx.getNsrsbh()));
        zhwh.setDwmc(jcxx.getNsrmc());
        zhwh.setOldJcghzh(jcxx.getJcghzh());
        zhwh.setOldJcghhm(jcxx.getJcghhm());
        zhwh.setOldJcghhh(jcxx.getJcghhh());
        zhwh.setOldJcghyh(jcxx.getJcghyh());
        return zhwh;
    }

    private Long resolveDeptId(Long dlzhId, String djxh, GhQxZhwhDO exist) {
        if (exist != null && exist.getDeptId() != null) {
            return exist.getDeptId();
        }
        GhQxSfxxDO sfxx = ghQxSfxxMapper.selectOne(new LambdaQueryWrapperX<GhQxSfxxDO>()
                .eq(GhQxSfxxDO::getDlzhId, dlzhId)
                .eq(GhQxSfxxDO::getDjxh, djxh)
                .eq(GhQxSfxxDO::getStatus, 1)
                .orderByDesc(GhQxSfxxDO::getId)
                .last("limit 1"));
        if (sfxx != null) {
            return sfxx.getDeptId();
        }
        return getLoginUserDeptId();
    }

    private GhQxZhwhDO validatePending(Long id) {
        GhQxZhwhDO zhwh = validateZhwhExists(id);
        if (!Objects.equals(zhwh.getStatus(), 0)) {
            throw exception(ZHWH_STATUS_NOT_PENDING);
        }
        return zhwh;
    }

    private GhQxZhwhDO validateZhwhExists(Long id) {
        GhQxZhwhDO zhwh = ghQxZhwhMapper.selectById(id);
        if (zhwh == null) {
            throw exception(ZHWH_NOT_EXISTS);
        }
        return zhwh;
    }

    private GhHjJcxxDO validateJcxxExists(String djxh) {
        GhHjJcxxDO jcxx = jcxxService.getJcxx(djxh);
        if (jcxx == null) {
            throw exception(GH_HJ_JCXX_NOT_EXISTS);
        }
        return jcxx;
    }

    private void syncBankInfo(GhQxZhwhDO zhwh) {
        GhHjJcxxDO jcxx = validateJcxxExists(zhwh.getDjxh());
        ghHjJcxxMapper.updateById(GhHjJcxxDO.builder()
                .djxh(zhwh.getDjxh())
                .jcghzh(zhwh.getNewJcghzh())
                .jcghhm(zhwh.getNewJcghhm())
                .jcghhh(zhwh.getNewJcghhh())
                .jcghyh(zhwh.getNewJcghyh())
                .build());

        GhHjYhxxDO todayRecord = ghHjYhxxMapper.selectOne(new LambdaQueryWrapperX<GhHjYhxxDO>()
                .eq(GhHjYhxxDO::getDjxh, zhwh.getDjxh())
                .eq(GhHjYhxxDO::getYxqq, LocalDate.now())
                .isNull(GhHjYhxxDO::getYxqz));
        if (todayRecord != null) {
            todayRecord.setJcghzh(zhwh.getNewJcghzh());
            todayRecord.setJcghhm(zhwh.getNewJcghhm());
            todayRecord.setJcghhh(zhwh.getNewJcghhh());
            todayRecord.setJcghyh(zhwh.getNewJcghyh());
            ghHjYhxxMapper.updateById(todayRecord);
            return;
        }

        if (hasBankInfoChanged(jcxx, zhwh)) {
            ghHjYhxxMapper.update(null, new LambdaUpdateWrapper<GhHjYhxxDO>()
                    .eq(GhHjYhxxDO::getDjxh, zhwh.getDjxh())
                    .isNull(GhHjYhxxDO::getYxqz)
                    .set(GhHjYhxxDO::getYxqz, LocalDate.now()));
        }
        ghHjYhxxMapper.insert(GhHjYhxxDO.builder()
                .djxh(zhwh.getDjxh())
                .jcghzh(zhwh.getNewJcghzh())
                .jcghhm(zhwh.getNewJcghhm())
                .jcghhh(zhwh.getNewJcghhh())
                .jcghyh(zhwh.getNewJcghyh())
                .yxqq(LocalDate.now())
                .build());
    }

    private boolean hasBankInfoChanged(GhHjJcxxDO jcxx, GhQxZhwhDO zhwh) {
        return !Objects.equals(jcxx.getJcghzh(), zhwh.getNewJcghzh())
                || !Objects.equals(jcxx.getJcghhm(), zhwh.getNewJcghhm())
                || !Objects.equals(jcxx.getJcghhh(), zhwh.getNewJcghhh())
                || !Objects.equals(jcxx.getJcghyh(), zhwh.getNewJcghyh());
    }
}

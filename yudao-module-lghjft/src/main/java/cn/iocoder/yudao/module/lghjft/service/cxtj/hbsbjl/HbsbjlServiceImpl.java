package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjl.HbsbjlMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class HbsbjlServiceImpl implements HbsbjlService {

    @Resource
    private HbsbjlMapper hbsbjlMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHbsbjl(HbsbjlSaveReqVO createReqVO) {
        HbsbjlDO obj = BeanUtils.toBean(createReqVO, HbsbjlDO.class);
        hbsbjlMapper.insert(obj);
        return obj.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHbsbjl(HbsbjlSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        // V1 restricted update: only xzh, xhm, xhh, xgbj, bz allowed
        HbsbjlDO updateObj = new HbsbjlDO();
        updateObj.setHkxxId(updateReqVO.getHkxxId());
        updateObj.setXzh(updateReqVO.getXzh());
        updateObj.setXhm(updateReqVO.getXhm());
        updateObj.setXhh(updateReqVO.getXhh());
        updateObj.setXgbj(updateReqVO.getXgbj());
        updateObj.setBz(updateReqVO.getBz());
        // V1 auto-sets xgr (modifier nickname) and xgsj (modification time)
        AdminUserDO user = adminUserService.getUser(getLoginUserId());
        if (user != null) {
            updateObj.setXgr(user.getNickname());
        }
        updateObj.setXgsj(LocalDateTime.now());
        hbsbjlMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjl(Long id) {
        validateExists(id);
        hbsbjlMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjlListByIds(List<Long> ids) {
        hbsbjlMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (hbsbjlMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public HbsbjlDO getHbsbjl(Long id) {
        return hbsbjlMapper.selectById(id);
    }

    @Override
    public PageResult<HbsbjlDO> getHbsbjlPage(HbsbjlPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return hbsbjlMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int fushenPlByhkxxIds(List<Long> hkxxIds, Map<String, Object> updateFields) {
        return hbsbjlMapper.fushenPlByhkxxIds(hkxxIds, updateFields);
    }
}

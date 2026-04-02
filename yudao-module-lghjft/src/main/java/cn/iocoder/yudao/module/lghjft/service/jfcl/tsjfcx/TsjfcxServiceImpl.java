package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcx.TsjfcxMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class TsjfcxServiceImpl implements TsjfcxService {

    @Resource
    private TsjfcxMapper tsjfcxMapper;
    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTsjfcx(TsjfcxSaveReqVO createReqVO) {
        TsjfcxDO entity = BeanUtils.toBean(createReqVO, TsjfcxDO.class);
        tsjfcxMapper.insert(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTsjfcx(TsjfcxSaveReqVO updateReqVO) {
        validateTsjfcxExists(updateReqVO.getGhjfId());
        TsjfcxDO updateObj = BeanUtils.toBean(updateReqVO, TsjfcxDO.class);
        tsjfcxMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfcx(Long id) {
        validateTsjfcxExists(id);
        tsjfcxMapper.deleteById(id);
    }

    @Override
    public TsjfcxDO getTsjfcx(Long id) {
        return tsjfcxMapper.selectById(id);
    }

    @Override
    public PageResult<TsjfcxDO> getTsjfcxPage(TsjfcxPageReqVO pageReqVO) {
        // v1 deptId filtering
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    pageReqVO.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) { /* ignore */ }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TsjfcxDO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        com.baomidou.mybatisplus.core.metadata.IPage<TsjfcxDO> ipage = tsjfcxMapper.selectTsjfcxPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    private void validateTsjfcxExists(Long id) {
        if (tsjfcxMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}

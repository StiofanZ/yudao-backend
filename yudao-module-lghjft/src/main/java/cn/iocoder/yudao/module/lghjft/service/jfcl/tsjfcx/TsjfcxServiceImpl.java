package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.GhJfTsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcx.TsjfcxMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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
        entity.setCreateTime(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // v1: set createBy from login user
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            if (user != null) {
                entity.setCreateBy(user.getNickname());
            }
        } catch (Exception ignored) {}
        tsjfcxMapper.insertTsjfcx(entity);
        // v1: insert child gh_jf_tsjf records
        insertGhJfTsjf(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTsjfcx(TsjfcxSaveReqVO updateReqVO) {
        validateTsjfcxExists(updateReqVO.getGhjfId());
        TsjfcxDO updateObj = BeanUtils.toBean(updateReqVO, TsjfcxDO.class);
        updateObj.setUpdateTime(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // v1: set updateBy from login user
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            if (user != null) {
                updateObj.setUpdateBy(user.getNickname());
            }
        } catch (Exception ignored) {}
        // v1: delete + re-insert child records
        tsjfcxMapper.deleteGhJfTsjfByGhjfId(updateObj.getGhjfId());
        insertGhJfTsjf(updateObj);
        tsjfcxMapper.updateTsjfcx(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfcx(Long id) {
        validateTsjfcxExists(id);
        // v1: cascade delete child first then main
        tsjfcxMapper.deleteGhJfTsjfByGhjfId(id);
        tsjfcxMapper.deleteTsjfcxByGhjfId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfcxByIds(Long[] ghjfIds) {
        // v1: cascade delete child first then main
        tsjfcxMapper.deleteGhJfTsjfByGhjfIds(ghjfIds);
        tsjfcxMapper.deleteTsjfcxByGhjfIds(ghjfIds);
    }

    @Override
    public TsjfcxDO getTsjfcx(Long id) {
        // v1: selectTsjfcxByGhjfId with LEFT JOIN collection for child records
        return tsjfcxMapper.selectTsjfcxByGhjfId(id);
    }

    @Override
    public PageResult<TsjfcxDO> getTsjfcxPage(TsjfcxPageReqVO pageReqVO) {
        // v1 deptId filtering: default to login user's dept, root=100000 means all
        if (pageReqVO.getDeptId() == null || pageReqVO.getDeptId().isEmpty()) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    pageReqVO.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception ignored) {}
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        Page<TsjfcxDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        return new PageResult<>(
                tsjfcxMapper.selectTsjfcxPage(page, pageReqVO).getRecords(),
                page.getTotal()
        );
    }

    /**
     * v1: insertGhJfTsjf - insert child gh_jf_tsjf records
     */
    private void insertGhJfTsjf(TsjfcxDO tsjfcx) {
        List<GhJfTsjfDO> ghJfTsjfList = tsjfcx.getGhJfTsjfList();
        Long ghjfId = tsjfcx.getGhjfId();
        if (ghJfTsjfList != null && !ghJfTsjfList.isEmpty()) {
            List<GhJfTsjfDO> list = new ArrayList<>();
            for (GhJfTsjfDO item : ghJfTsjfList) {
                item.setGhjfId(ghjfId);
                list.add(item);
            }
            tsjfcxMapper.batchGhJfTsjf(list);
        }
    }

    private void validateTsjfcxExists(Long id) {
        if (tsjfcxMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}

package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.GhJfTsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcl.TsjfclMapper;
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
public class TsjfclServiceImpl implements TsjfclService {

    @Resource
    private TsjfclMapper tsjfclMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTsjfcl(TsjfclSaveReqVO createReqVO) {
        TsjfclDO entity = BeanUtils.toBean(createReqVO, TsjfclDO.class);
        entity.setCreateTime(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // v1: set createBy from login user
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            if (user != null) {
                entity.setCreateBy(user.getNickname());
            }
        } catch (Exception ignored) {}
        tsjfclMapper.insertTsjfcl(entity);
        // v1: insert child gh_jf_tsjf records
        insertGhJfTsjf(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTsjfcl(TsjfclSaveReqVO updateReqVO) {
        validateTsjfclExists(updateReqVO.getGhjfId());
        TsjfclDO updateObj = BeanUtils.toBean(updateReqVO, TsjfclDO.class);
        updateObj.setUpdateTime(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // v1: set updateBy from login user
        try {
            AdminUserDO user = userService.getUser(getLoginUserId());
            if (user != null) {
                updateObj.setUpdateBy(user.getNickname());
            }
        } catch (Exception ignored) {}
        // v1: delete + re-insert child records
        tsjfclMapper.deleteGhJfTsjfByGhjfId(updateObj.getGhjfId());
        insertGhJfTsjf(updateObj);
        tsjfclMapper.updateTsjfcl(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfcl(Long id) {
        validateTsjfclExists(id);
        // v1: cascade delete child first then main
        tsjfclMapper.deleteGhJfTsjfByGhjfId(id);
        tsjfclMapper.deleteTsjfclByGhjfId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTsjfclByIds(Long[] ghjfIds) {
        // v1: cascade delete child first then main
        tsjfclMapper.deleteGhJfTsjfByGhjfIds(ghjfIds);
        tsjfclMapper.deleteTsjfclByGhjfIds(ghjfIds);
    }

    @Override
    public TsjfclDO getTsjfcl(Long id) {
        // v1: selectTsjfclByGhjfId with LEFT JOIN collection for child records
        return tsjfclMapper.selectTsjfclByGhjfId(id);
    }

    @Override
    public PageResult<TsjfclDO> getTsjfclPage(TsjfclPageReqVO pageReqVO) {
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
        Page<TsjfclDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        return new PageResult<>(
                tsjfclMapper.selectTsjfclPage(page, pageReqVO).getRecords(),
                page.getTotal()
        );
    }

    @Override
    public void batchTsjfclpl(List<GhJfTsjfDO> ghJfTsjfList) {
        // v1: batchInsertOrUpdateChildData + batchUpdateMainTableModifyTime
        tsjfclMapper.batchInsertOrUpdateChildData(ghJfTsjfList);
        tsjfclMapper.batchUpdateMainTableModifyTime(ghJfTsjfList);
    }

    /**
     * v1: insertGhJfTsjf - insert child gh_jf_tsjf records
     */
    private void insertGhJfTsjf(TsjfclDO tsjfcl) {
        List<GhJfTsjfDO> ghJfTsjfList = tsjfcl.getGhJfTsjfList();
        Long ghjfId = tsjfcl.getGhjfId();
        if (ghJfTsjfList != null && !ghJfTsjfList.isEmpty()) {
            List<GhJfTsjfDO> list = new ArrayList<>();
            for (GhJfTsjfDO item : ghJfTsjfList) {
                item.setGhjfId(ghjfId);
                item.setSpuuid(tsjfcl.getSpuuid());
                // v1: set createBy from login user nickname
                try {
                    AdminUserDO user = userService.getUser(getLoginUserId());
                    if (user != null) {
                        item.setCreateBy(user.getNickname());
                    }
                } catch (Exception ignored) {}
                list.add(item);
            }
            tsjfclMapper.batchGhJfTsjf(list);
        }
    }

    private void validateTsjfclExists(Long id) {
        if (tsjfclMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}

package cn.iocoder.yudao.module.lghjft.service.xxzx.tzgg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggSaveReqVO;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.tzgg.TzggDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.tzgg.TzggMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;

/**
 * 通知公告 Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class TzggServiceImpl implements TzggService {

    @Resource
    private TzggMapper tzggMapper;

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Long createTzgg(TzggSaveReqVO createReqVO) {
        TzggDO notice = BeanUtils.toBean(createReqVO, TzggDO.class);
        // 记录当前用户的部门ID
        notice.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        tzggMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateTzgg(TzggSaveReqVO updateReqVO) {
        // 校验是否存在
        validateTzggExists(updateReqVO.getId());
        // 更新通知公告
        TzggDO updateObj = BeanUtils.toBean(updateReqVO, TzggDO.class);
        // 记录当前用户的部门ID
        updateObj.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        tzggMapper.updateById(updateObj);
    }

    @Override
    public void deleteTzgg(Long id) {
        // 校验是否存在
        validateTzggExists(id);
        // 删除通知公告
        tzggMapper.deleteById(id);
    }

    @Override
    public void deleteTzggList(List<Long> ids) {
        tzggMapper.deleteByIds(ids);
    }

    @Override
    public PageResult<TzggDO> getTzggPage(TzggPageReqVO reqVO) {
        // 获取当前用户的部门ID
        Long currentDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        // 获取当前部门及其所有上级部门的ID列表
        Set<Long> deptIds = getCurrentAndParentDeptIds(currentDeptId);
        // 设置部门ID列表到请求参数中
        reqVO.setDeptIds(new ArrayList<>(deptIds));
        return tzggMapper.selectPage(reqVO);
    }

    /**
     * 获取当前部门及其所有上级部门的ID列表
     *
     * @param deptId 当前部门ID
     * @return 当前部门及其所有上级部门的ID列表
     */
    private Set<Long> getCurrentAndParentDeptIds(Long deptId) {
        Set<Long> deptIds = new LinkedHashSet<>();
        deptIds.add(deptId);
        
        // 递归获取所有上级部门
        for (int i = 0; i < Short.MAX_VALUE; i++) { // 使用 Short.MAX_VALUE 避免 bug 场景下，存在死循环
            DeptDO dept = deptMapper.selectById(deptId);
            if (dept == null || DeptDO.PARENT_ID_ROOT.equals(dept.getParentId())) {
                break;
            }
            deptId = dept.getParentId();
            deptIds.add(deptId);
        }
        
        return deptIds;
    }

    @Override
    public TzggDO getTzgg(Long id) {
        return tzggMapper.selectById(id);
    }

    @VisibleForTesting
    public void validateTzggExists(Long id) {
        if (id == null) {
            return;
        }
        TzggDO notice = tzggMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }

}

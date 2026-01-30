package cn.iocoder.yudao.module.lghjft.service.nrgl.cjwt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.cjwt.CjwtMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * 常见问题 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CjwtServiceImpl implements CjwtService {

    @Resource
    private CjwtMapper cjwtMapper;

    @Resource
    private DeptApi deptApi;

    @Override
    public Long createCjwt(CjwtCreateReqVO createReqVO) {
        // 校验上级内容的可见性
        validateParentKjfw(createReqVO.getParentId(), createReqVO.getKjfw());
        
        CjwtDO cjwt = BeanUtils.toBean(createReqVO, CjwtDO.class);
        // 设置当前登录用户的部门ID
        cjwt.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        // 默认状态为草稿 (0)
        cjwt.setStatus(0);
        // 默认阅读量 0
        cjwt.setReadCount(0);
        cjwtMapper.insert(cjwt);
        return cjwt.getId();
    }

    @Override
    public void updateCjwt(CjwtUpdateReqVO updateReqVO) {
        // 校验存在
        CjwtDO oldCjwt = validateCjwtExists(updateReqVO.getId());
        
        // 校验权限：仅允许修改自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldCjwt.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 校验状态：已发布内容不允许修改
        if (oldCjwt.getStatus() == 1) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "已发布内容不允许修改"));
        }
        
        // 校验上级内容的可见性
        validateParentKjfw(updateReqVO.getParentId(), updateReqVO.getKjfw());
        
        // 校验子内容的可见性（如果修改了可见性）
        if (!oldCjwt.getKjfw().equals(updateReqVO.getKjfw())) {
            validateChildrenKjfw(updateReqVO.getId(), updateReqVO.getKjfw());
        }

        // 更新
        CjwtDO updateObj = BeanUtils.toBean(updateReqVO, CjwtDO.class);
        // 不允许修改发布部门
        updateObj.setDeptId(null); 
        cjwtMapper.updateById(updateObj);
    }
    
    /**
     * 校验上级内容的可见性
     * 1: 完全可见, 2: 下级可见, 3: 本级可见
     */
    private void validateParentKjfw(Long parentId, Integer kjfw) {
        if (parentId == null || parentId == 0) {
            return;
        }
        CjwtDO parent = cjwtMapper.selectById(parentId);
        if (parent == null) {
            return;
        }
        
        Integer pVis = parent.getKjfw();
        
        // 如果上级是完全可见(1)，下级任意
        if (pVis == 1) {
            return;
        }
        
        // 如果上级是下级可见(2)，下级只能是 2 或 3
        if (pVis == 2) {
            if (kjfw == 1) {
                throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "上级内容为'下级可见'，本内容不能设置为'完全可见'"));
            }
        }
        
        // 如果上级是本级可见(3)，下级只能是 3
        if (pVis == 3) {
            if (kjfw != 3) {
                throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "上级内容为'本级可见'，本内容只能设置为'本级可见'"));
            }
        }
    }
    
    /**
     * 校验子内容的可见性
     */
    private void validateChildrenKjfw(Long parentId, Integer newKjfw) {
        List<CjwtDO> children = cjwtMapper.selectList(CjwtDO::getParentId, parentId);
        if (children.isEmpty()) {
            return;
        }
        
        for (CjwtDO child : children) {
            Integer cVis = child.getKjfw();
            
            // 如果本内容变成 下级可见(2)
            if (newKjfw == 2) {
                // 子内容如果是 完全可见(1)，则冲突
                if (cVis == 1) {
                     throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "存在子内容可见性为'完全可见'，无法将本内容修改为'下级可见'或更小范围"));
                }
            }
            
            // 如果本内容变成 本级可见(3)
            if (newKjfw == 3) {
                // 子内容如果是 1 或 2，则冲突
                if (cVis == 1 || cVis == 2) {
                    throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "存在子内容可见性范围更大，无法将本内容修改为'本级可见'"));
                }
            }
            
            // 递归校验
            validateChildrenKjfw(child.getId(), newKjfw);
        }
    }

    @Override
    public void deleteCjwt(Long id) {
        // 校验存在
        CjwtDO oldCjwt = validateCjwtExists(id);
        
        // 校验权限：仅允许删除自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldCjwt.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }
        
        // 删除
        cjwtMapper.deleteById(id);
    }

    private CjwtDO validateCjwtExists(Long id) {
        CjwtDO cjwt = cjwtMapper.selectById(id);
        if (cjwt == null) {
            throw exception(ErrorCodeConstants.CONTENT_NOT_EXISTS);
        }
        return cjwt;
    }

    @Override
    public CjwtDO getCjwt(Long id) {
        CjwtDO cjwt = cjwtMapper.selectById(id);
        if (cjwt != null) {
            // 增加阅读量
            CjwtDO updateObj = new CjwtDO();
            updateObj.setId(id);
            updateObj.setReadCount(cjwt.getReadCount() == null ? 1 : cjwt.getReadCount() + 1);
            cjwtMapper.updateById(updateObj);
            cjwt.setReadCount(updateObj.getReadCount());
        }
        return cjwt;
    }

    @Override
    public PageResult<CjwtDO> getCjwtPage(CjwtReqVO reqVO) {
        // 1. 确定上下文部门ID
        Long loginDeptId = null;
        try {
            loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        } catch (Exception e) {
            // ignore
        }
        
        // 2. 确定用于权限查询的基础部门ID
        Long baseDeptId = null;
        if (loginDeptId != null) {
            baseDeptId = loginDeptId;
        } else if (reqVO.getDeptId() != null) {
            baseDeptId = reqVO.getDeptId();
        } else {
            return PageResult.empty();
        }

        // 3. 获取上级部门ID列表
        List<Long> ancestorIds = getAncestorIds(baseDeptId);

        // 4. 执行分页查询 (包含排名计算)
        IPage<CjwtDO> page = MyBatisUtils.buildPage(reqVO);
        cjwtMapper.selectPageWithRank(page, reqVO, loginDeptId, ancestorIds);
        
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void publishCjwt(Long id) {
        CjwtDO cjwt = validateCjwtExists(id);
        
        // 校验权限
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(cjwt.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 如果是 620000，允许直接发布
        if (loginDeptId != null && loginDeptId == 620000L) {
             cjwt.setStatus(2); // 2: 已发布
             cjwtMapper.updateById(cjwt);
             return;
        }

        // 其他部门，必须是 已审核(1) 才能发布
        if (cjwt.getStatus() != 1) {
             throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "该内容未审核，无法发布"));
        }

        // 更新状态为发布 (2)
        cjwt.setStatus(2);
        cjwtMapper.updateById(cjwt);
    }

    @Override
    public void offShelfCjwt(Long id, String reason) {
        CjwtDO cjwt = validateCjwtExists(id);
        // 校验权限：允许自己部门下架
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(cjwt.getDeptId(), loginDeptId) && (loginDeptId == null || loginDeptId != 620000L)) {
             throw exception(FORBIDDEN);
        }
        
        // 允许 已发布(2) 或 已过期(3) 下架
        if (cjwt.getStatus() != 2 && cjwt.getStatus() != 3) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "当前状态不允许下架"));
        }
        
        cjwt.setStatus(4); // 4: 已下架
        cjwt.setXjyy(reason);
        cjwtMapper.updateById(cjwt);
    }

    @Override
    public void auditCjwt(Long id, Integer status) {
        // 校验权限：仅 620000 可审核
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (loginDeptId == null || loginDeptId != 620000L) {
             throw exception(FORBIDDEN);
        }
        
        CjwtDO cjwt = validateCjwtExists(id);
        cjwt.setStatus(status);
        cjwtMapper.updateById(cjwt);
    }

    @Override
    public List<CjwtDO> getPublicCjwtList(Long deptId) {
        if (deptId == null) {
            return Collections.emptyList();
        }
        // 获取当前部门及上级部门ID列表
        List<Long> deptIds = getAncestorIds(deptId);
        if (deptIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 查询公开且已发布的内容
        return cjwtMapper.selectList(new LambdaQueryWrapper<CjwtDO>()
                .eq(CjwtDO::getStatus, 1) // 1: 发布
                .and(wrapper -> {
                    // 本级部门
                    wrapper.eq(CjwtDO::getDeptId, deptId)
                           .or(subWrapper -> {
                               // 上级部门：查看 1 和 2
                               subWrapper.in(CjwtDO::getDeptId, deptIds)
                                         .ne(CjwtDO::getDeptId, deptId)
                                         .in(CjwtDO::getKjfw, 1, 2);
                           });
                })
                .orderByDesc(CjwtDO::getSort));
    }

    /**
     * 获取部门及其所有上级部门的ID列表
     */
    private List<Long> getAncestorIds(Long deptId) {
        List<Long> ids = new ArrayList<>();
        Long currentId = deptId;
        // 防止死循环，设置最大深度
        int maxDepth = 100;
        int depth = 0;
        
        while (currentId != null && currentId != 0L && depth < maxDepth) {
            ids.add(currentId);
            try {
                DeptRespDTO dept = deptApi.getDept(currentId);
                if (dept == null) {
                    break;
                }
                currentId = dept.getParentId();
            } catch (Exception e) {
                // 如果获取部门失败，终止向上查找
                break;
            }
            depth++;
        }
        return ids;
    }

}

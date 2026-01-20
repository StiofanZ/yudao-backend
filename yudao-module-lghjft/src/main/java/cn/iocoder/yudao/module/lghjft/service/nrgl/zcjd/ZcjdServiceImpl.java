package cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcjd.ZcjdMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * 政策解读 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ZcjdServiceImpl implements ZcjdService {

    @Resource
    private ZcjdMapper zcjdMapper;

    @Resource
    private DeptApi deptApi;

    @Override
    public Long createZcjd(ZcjdCreateReqVO createReqVO) {
        // 校验上级内容的可见性
        validateParentKjfw(createReqVO.getParentId(), createReqVO.getKjfw());
        
        ZcjdDO zcjd = BeanUtils.toBean(createReqVO, ZcjdDO.class);
        // 设置当前登录用户的部门ID
        zcjd.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        // 默认状态为草稿 (0)
        zcjd.setStatus(0);
        zcjdMapper.insert(zcjd);
        return zcjd.getId();
    }

    @Override
    public void updateZcjd(ZcjdUpdateReqVO updateReqVO) {
        // 校验存在
        ZcjdDO oldZcjd = validateZcjdExists(updateReqVO.getId());
        
        // 校验权限：仅允许修改自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldZcjd.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 校验状态：已发布内容不允许修改
        if (oldZcjd.getStatus() == 1) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "已发布内容不允许修改"));
        }
        
        // 校验上级内容的可见性
        validateParentKjfw(updateReqVO.getParentId(), updateReqVO.getKjfw());
        
        // 校验子内容的可见性（如果修改了可见性）
        if (!oldZcjd.getKjfw().equals(updateReqVO.getKjfw())) {
            validateChildrenKjfw(updateReqVO.getId(), updateReqVO.getKjfw());
        }

        // 更新
        ZcjdDO updateObj = BeanUtils.toBean(updateReqVO, ZcjdDO.class);
        // 不允许修改发布部门
        updateObj.setDeptId(null); 
        zcjdMapper.updateById(updateObj);
    }
    
    /**
     * 校验上级内容的可见性
     * 1: 完全可见, 2: 下级可见, 3: 本级可见
     */
    private void validateParentKjfw(Long parentId, Integer kjfw) {
        if (parentId == null || parentId == 0) {
            return;
        }
        ZcjdDO parent = zcjdMapper.selectById(parentId);
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
        List<ZcjdDO> children = zcjdMapper.selectList(ZcjdDO::getParentId, parentId);
        if (children.isEmpty()) {
            return;
        }
        
        for (ZcjdDO child : children) {
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
    public void deleteZcjd(Long id) {
        // 校验存在
        ZcjdDO oldZcjd = validateZcjdExists(id);
        
        // 校验权限：仅允许删除自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldZcjd.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }
        
        // 删除
        zcjdMapper.deleteById(id);
    }

    private ZcjdDO validateZcjdExists(Long id) {
        ZcjdDO zcjd = zcjdMapper.selectById(id);
        if (zcjd == null) {
            throw exception(ErrorCodeConstants.CONTENT_NOT_EXISTS);
        }
        return zcjd;
    }

    @Override
    public ZcjdDO getZcjd(Long id) {
        return zcjdMapper.selectById(id);
    }

    @Override
    public List<ZcjdDO> getZcjdList(ZcjdListReqVO listReqVO) {
        // 获取当前部门及上级部门ID列表
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        List<Long> deptIds = getAncestorIds(loginDeptId);

        // 构建查询条件
        LambdaQueryWrapper<ZcjdDO> queryWrapper = new LambdaQueryWrapper<>();
        // 基础查询条件
        if (listReqVO.getTitle() != null) {
            queryWrapper.like(ZcjdDO::getTitle, listReqVO.getTitle());
        }
        if (listReqVO.getStatus() != null) {
            queryWrapper.eq(ZcjdDO::getStatus, listReqVO.getStatus());
        }
        // 部门权限过滤：查看本级及上级部门的内容，并应用可见性过滤
        if (!deptIds.isEmpty()) {
            queryWrapper.and(wrapper -> {
                // 本级部门：查看所有
                wrapper.eq(ZcjdDO::getDeptId, loginDeptId)
                       .or(subWrapper -> {
                           // 上级部门：仅查看 完全可见(1) 和 下级可见(2) 的内容
                           // 排除 本级可见(3)
                           subWrapper.in(ZcjdDO::getDeptId, deptIds)
                                     .ne(ZcjdDO::getDeptId, loginDeptId)
                                     .in(ZcjdDO::getKjfw, 1, 2);
                       });
            });
        }
        
        queryWrapper.orderByDesc(ZcjdDO::getSort);

        return zcjdMapper.selectList(queryWrapper);
    }

    @Override
    public void publishZcjd(Long id) {
        ZcjdDO zcjd = validateZcjdExists(id);
        
        // 校验权限
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(zcjd.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 如果是 620000，允许直接发布
        if (loginDeptId != null && loginDeptId == 620000L) {
             zcjd.setStatus(2); // 2: 已发布
             zcjdMapper.updateById(zcjd);
             return;
        }

        // 其他部门，必须是 已审核(1) 才能发布
        if (zcjd.getStatus() != 1) {
             throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "该内容未审核，无法发布"));
        }

        // 更新状态为发布 (2)
        zcjd.setStatus(2);
        zcjdMapper.updateById(zcjd);
    }

    @Override
    public void offShelfZcjd(Long id, String reason) {
        ZcjdDO zcjd = validateZcjdExists(id);
        // 校验权限：允许自己部门下架
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(zcjd.getDeptId(), loginDeptId) && (loginDeptId == null || loginDeptId != 620000L)) {
             throw exception(FORBIDDEN);
        }
        
        // 允许 已发布(2) 或 已过期(3) 下架
        if (zcjd.getStatus() != 2 && zcjd.getStatus() != 3) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "当前状态不允许下架"));
        }
        
        zcjd.setStatus(4); // 4: 已下架
        zcjd.setXjyy(reason);
        zcjdMapper.updateById(zcjd);
    }

    @Override
    public void auditZcjd(Long id, Integer status) {
        // 校验权限：仅 620000 可审核
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (loginDeptId == null || loginDeptId != 620000L) {
             throw exception(FORBIDDEN);
        }
        
        ZcjdDO zcjd = validateZcjdExists(id);
        zcjd.setStatus(status);
        zcjdMapper.updateById(zcjd);
    }

    @Override
    public List<ZcjdDO> getPublicZcjdList(Long deptId) {
        if (deptId == null) {
            return Collections.emptyList();
        }
        // 获取当前部门及上级部门ID列表
        List<Long> deptIds = getAncestorIds(deptId);
        if (deptIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 查询公开且已发布的内容
        return zcjdMapper.selectList(new LambdaQueryWrapper<ZcjdDO>()
                .eq(ZcjdDO::getStatus, 1) // 1: 发布
                .and(wrapper -> {
                    // 本级部门
                    wrapper.eq(ZcjdDO::getDeptId, deptId)
                           .or(subWrapper -> {
                               // 上级部门：查看 1 和 2
                               subWrapper.in(ZcjdDO::getDeptId, deptIds)
                                         .ne(ZcjdDO::getDeptId, deptId)
                                         .in(ZcjdDO::getKjfw, 1, 2);
                           });
                })
                .orderByDesc(ZcjdDO::getSort));
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

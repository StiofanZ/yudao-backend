package cn.iocoder.yudao.module.lghjft.service.nrgl.bszn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bszn.BsznMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * 办事指南 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BsznServiceImpl implements BsznService {

    @Resource
    private BsznMapper bsznMapper;

    @Resource
    private DeptApi deptApi;

    @Override
    public Long createBszn(BsznCreateReqVO createReqVO) {
        // 校验上级内容的可见性
        validateParentKjfw(createReqVO.getParentId(), createReqVO.getKjfw());
        
        BsznDO bszn = BeanUtils.toBean(createReqVO, BsznDO.class);
        // 设置当前登录用户的部门ID
        bszn.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        // 默认状态为草稿 (0)
        bszn.setStatus(0);
        bsznMapper.insert(bszn);
        return bszn.getId();
    }

    @Override
    public void updateBszn(BsznUpdateReqVO updateReqVO) {
        // 校验存在
        BsznDO oldBszn = validateBsznExists(updateReqVO.getId());
        
        // 校验权限：仅允许修改自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldBszn.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 校验状态：已发布内容不允许修改
        if (oldBszn.getStatus() == 1) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "已发布内容不允许修改"));
        }
        
        // 校验上级内容的可见性
        validateParentKjfw(updateReqVO.getParentId(), updateReqVO.getKjfw());
        
        // 校验子内容的可见性（如果修改了可见性）
        if (!oldBszn.getKjfw().equals(updateReqVO.getKjfw())) {
            validateChildrenKjfw(updateReqVO.getId(), updateReqVO.getKjfw());
        }

        // 更新
        BsznDO updateObj = BeanUtils.toBean(updateReqVO, BsznDO.class);
        // 不允许修改发布部门
        updateObj.setDeptId(null); 
        bsznMapper.updateById(updateObj);
    }
    
    /**
     * 校验上级内容的可见性
     * 1: 完全可见, 2: 下级可见, 3: 本级可见
     */
    private void validateParentKjfw(Long parentId, Integer kjfw) {
        if (parentId == null || parentId == 0) {
            return;
        }
        BsznDO parent = bsznMapper.selectById(parentId);
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
        List<BsznDO> children = bsznMapper.selectList(BsznDO::getParentId, parentId);
        if (children.isEmpty()) {
            return;
        }
        
        for (BsznDO child : children) {
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
    public void deleteBszn(Long id) {
        // 校验存在
        BsznDO oldBszn = validateBsznExists(id);
        
        // 校验权限：仅允许删除自己部门发布的内容
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(oldBszn.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }
        
        // 删除
        bsznMapper.deleteById(id);
    }

    private BsznDO validateBsznExists(Long id) {
        BsznDO bszn = bsznMapper.selectById(id);
        if (bszn == null) {
            throw exception(ErrorCodeConstants.CONTENT_NOT_EXISTS);
        }
        return bszn;
    }

    @Override
    public BsznDO getBszn(Long id) {
        return bsznMapper.selectById(id);
    }

    @Override
    public List<BsznDO> getBsznList(BsznListReqVO listReqVO) {
        // 获取当前部门及上级部门ID列表
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        List<Long> deptIds = getAncestorIds(loginDeptId);

        // 构建查询条件
        LambdaQueryWrapper<BsznDO> queryWrapper = new LambdaQueryWrapper<>();
        // 基础查询条件
        if (listReqVO.getTitle() != null) {
            queryWrapper.like(BsznDO::getTitle, listReqVO.getTitle());
        }
        if (listReqVO.getStatus() != null) {
            queryWrapper.eq(BsznDO::getStatus, listReqVO.getStatus());
        }
        // 部门权限过滤：查看本级及上级部门的内容，并应用可见性过滤
        if (!deptIds.isEmpty()) {
            queryWrapper.and(wrapper -> {
                // 本级部门：查看所有
                wrapper.eq(BsznDO::getDeptId, loginDeptId)
                       .or(subWrapper -> {
                           // 上级部门：仅查看 完全可见(1) 和 下级可见(2) 的内容
                           // 排除 本级可见(3)
                           subWrapper.in(BsznDO::getDeptId, deptIds)
                                     .ne(BsznDO::getDeptId, loginDeptId)
                                     .in(BsznDO::getKjfw, 1, 2);
                       });
            });
        }
        
        queryWrapper.orderByDesc(BsznDO::getSort);

        return bsznMapper.selectList(queryWrapper);
    }

    @Override
    public void publishBszn(Long id) {
        BsznDO bszn = validateBsznExists(id);
        
        // 校验权限
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(bszn.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 更新状态为发布 (1)
        bszn.setStatus(1);
        bsznMapper.updateById(bszn);
    }

    @Override
    public List<BsznDO> getPublicBsznList(Long deptId) {
        if (deptId == null) {
            return Collections.emptyList();
        }
        // 获取当前部门及上级部门ID列表
        List<Long> deptIds = getAncestorIds(deptId);
        if (deptIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 查询公开且已发布的内容
        return bsznMapper.selectList(new LambdaQueryWrapper<BsznDO>()
                .eq(BsznDO::getStatus, 1) // 1: 发布
                .and(wrapper -> {
                    // 本级部门
                    wrapper.eq(BsznDO::getDeptId, deptId)
                           .or(subWrapper -> {
                               // 上级部门：查看 1 和 2
                               subWrapper.in(BsznDO::getDeptId, deptIds)
                                         .ne(BsznDO::getDeptId, deptId)
                                         .in(BsznDO::getKjfw, 1, 2);
                           });
                })
                .orderByDesc(BsznDO::getSort));
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

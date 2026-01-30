package cn.iocoder.yudao.module.lghjft.service.nrgl.bszn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bszn.BsznMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

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
        // ... (保持不变)
        // 校验上级内容的可见性
        validateParentKjfw(createReqVO.getParentId(), createReqVO.getKjfw());

        BsznDO bszn = BeanUtils.toBean(createReqVO, BsznDO.class);
        // 设置当前登录用户的部门ID
        bszn.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        // 默认状态为草稿 (0)
        bszn.setStatus(0);
        // 默认阅读量 0
        bszn.setReadCount(0);
        bsznMapper.insert(bszn);
        return bszn.getId();
    }

    // ... (updateBszn, deleteBszn, validateParentKjfw, validateChildrenKjfw 保持不变)

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
        BsznDO bszn = bsznMapper.selectById(id);
        if (bszn != null) {
            // 增加阅读量
            BsznDO updateObj = new BsznDO();
            updateObj.setId(id);
            updateObj.setReadCount(bszn.getReadCount() == null ? 1 : bszn.getReadCount() + 1);
            bsznMapper.updateById(updateObj);
            bszn.setReadCount(updateObj.getReadCount());
        }
        return bszn;
    }

    @Override
    public PageResult<BsznDO> getBsznPage(BsznListReqVO listReqVO) {
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
        } else if (listReqVO.getDeptId() != null) {
            baseDeptId = listReqVO.getDeptId();
        } else {
            // 既无登录也无指定部门，返回空
            return PageResult.empty();
        }

        // 3. 获取上级部门ID列表
        List<Long> ancestorIds = getAncestorIds(baseDeptId);

        // 4. 执行分页查询 (包含排名计算)
        IPage<BsznDO> page = MyBatisUtils.buildPage(listReqVO);
        bsznMapper.selectPageWithRank(page, listReqVO, loginDeptId, ancestorIds);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    // ... (publishBszn, offShelfBszn, auditBszn, getAncestorIds 保持不变)

    @Override
    public void publishBszn(Long id) {
        BsznDO bszn = validateBsznExists(id);

        // 校验权限
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(bszn.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }

        // 如果是 620000，允许直接发布
        if (loginDeptId != null && loginDeptId == 620000L) {
            bszn.setStatus(2); // 2: 已发布
            bsznMapper.updateById(bszn);
            return;
        }

        // 其他部门，必须是 已审核(1) 才能发布
        if (bszn.getStatus() != 1) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "该内容未审核，无法发布"));
        }

        // 更新状态为发布 (2)
        bszn.setStatus(2);
        bsznMapper.updateById(bszn);
    }

    @Override
    public void offShelfBszn(Long id, String reason) {
        BsznDO bszn = validateBsznExists(id);
        // 校验权限：允许自己部门下架
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(bszn.getDeptId(), loginDeptId) && (loginDeptId == null || loginDeptId != 620000L)) {
            throw exception(FORBIDDEN);
        }

        // 允许 已发布(2) 或 已过期(3) 下架
        if (bszn.getStatus() != 2 && bszn.getStatus() != 3) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(400, "当前状态不允许下架"));
        }

        bszn.setStatus(4); // 4: 已下架
        bszn.setXjyy(reason);
        bsznMapper.updateById(bszn);
    }

    @Override
    public void auditBszn(Long id, Integer status) {
        // 校验权限：仅 620000 可审核
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (loginDeptId == null || loginDeptId != 620000L) {
            throw exception(FORBIDDEN);
        }

        BsznDO bszn = validateBsznExists(id);
        bszn.setStatus(status);
        bsznMapper.updateById(bszn);
    }

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

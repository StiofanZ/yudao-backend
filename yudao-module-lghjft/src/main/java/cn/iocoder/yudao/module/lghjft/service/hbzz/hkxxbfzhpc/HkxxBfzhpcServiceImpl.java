package cn.iocoder.yudao.module.lghjft.service.hbzz.hkxxbfzhpc;

import cn.hutool.core.collection.CollUtil;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxxbfzhpc.HkxxBfzhpcDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.hkxxbfzhpc.HkxxBfzhpcMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

/**
 * 拨付信息 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class HkxxBfzhpcServiceImpl implements HkxxBfzhpcService {

    @Resource
    private HkxxBfzhpcMapper hkxxBfzhpcMapper;
    @Resource
    private AdminUserService userService;

    // ========== deptId 自动填充（与 v1 Service 逻辑一致） ==========

    private void fillDeptId(HkxxBfzhpcPageReqVO pageReqVO) {
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            pageReqVO.setDeptId(user.getDeptId().toString());
        }
        // deptId == "100000" 为管理员，查全部
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
    }

    // ========== 账户排除 ==========

    @Override
    public PageResult<HkxxBfzhpcResVO> getBfzhpcPage(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        Page<HkxxBfzhpcResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<HkxxBfzhpcResVO> ipage = hkxxBfzhpcMapper.selectBfzhpcPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBfzhpc(HkxxBfzhpcSaveReqVO updateReqVO) {
        // 1. 批量插入/更新子表 gh_hkxx_bfzhpc
        insertGhHkxxBfzhpcList(updateReqVO);
        // 2. 更新主表 gh_hj（与 v1 updateBfzhpc 一致）
        hkxxBfzhpcMapper.updateBfzhpcByJcghzh(updateReqVO.getJcghzh());
    }

    @Override
    public HkxxBfzhpcResVO getHkxxBfzhpcByJcghzh(String jcghzh) {
        return hkxxBfzhpcMapper.selectByJcghzh(jcghzh);
    }

    // ========== 单位排除 ==========

    @Override
    public PageResult<HkxxBfzhpcResVO> getBfdwPage(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        Page<HkxxBfzhpcResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<HkxxBfzhpcResVO> ipage = hkxxBfzhpcMapper.selectBfdwPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBfdwpc(HkxxBfzhpcSaveReqVO updateReqVO) {
        // 1. 批量插入/更新子表 gh_hkxx_bfzhpc
        insertGhHkxxBfzhpcList(updateReqVO);
        // 2. 更新主表 gh_hj（与 v1 updateBfdwpc 一致）
        hkxxBfzhpcMapper.updateBfdwpcByDjxh(updateReqVO.getDjxh());
    }

    @Override
    public HkxxBfzhpcResVO getBfdwpcByDjxh(String djxh) {
        return hkxxBfzhpcMapper.selectBfdwByDjxh(djxh);
    }

    // ========== 经费排除 ==========

    @Override
    public PageResult<HkxxBfzhpcResVO> getBfjfpcPage(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        Page<HkxxBfzhpcResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<HkxxBfzhpcResVO> ipage = hkxxBfzhpcMapper.selectBfjfpcPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<HkxxBfzhpcResVO> getBfjfPage(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        Page<HkxxBfzhpcResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<HkxxBfzhpcResVO> ipage = hkxxBfzhpcMapper.selectBfjfPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateHkxxBfzhpc(HkxxBfzhpcSaveReqVO updateReqVO) {
        // 1. 批量插入/更新子表 gh_hkxx_bfzhpc
        insertGhHkxxBfzhpcList(updateReqVO);
        // 2. 更新主表 gh_jf（与 v1 updateBfjfpc 一致）
        hkxxBfzhpcMapper.updateBfjfpc(updateReqVO.getSpuuid());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createHkxxBfzhpc(HkxxBfzhpcSaveReqVO createReqVO) {
        insertGhHkxxBfzhpcList(createReqVO);
        hkxxBfzhpcMapper.updateBfjfpc(createReqVO.getSpuuid());
    }

    @Override
    public HkxxBfzhpcResVO getHkxxBfzhpc(String spuuid) {
        return hkxxBfzhpcMapper.selectByspuuid(spuuid);
    }

    // ========== 删除 ==========

    @Override
    public void deleteHkxxBfzhpcListByIds(List<Integer> ids) {
        hkxxBfzhpcMapper.deleteByIds(ids);
    }

    // ========== 导出 ==========

    @Override
    public List<HkxxBfzhpcResVO> getBfzhpcList(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        return hkxxBfzhpcMapper.selectBfzhpcList(pageReqVO);
    }

    @Override
    public List<HkxxBfzhpcResVO> getBfdwList(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        return hkxxBfzhpcMapper.selectBfdwList(pageReqVO);
    }

    @Override
    public List<HkxxBfzhpcResVO> getBfjfList(HkxxBfzhpcPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        return hkxxBfzhpcMapper.selectBfjfList(pageReqVO);
    }

    // ========== 内部方法 ==========

    /**
     * 批量插入或更新子表（复制旧代码逻辑）
     */
    private void insertGhHkxxBfzhpcList(HkxxBfzhpcSaveReqVO bfzhpc) {
        List<HkxxBfzhpcSaveReqVO.GhHkxxBfzhpcItem> ghHkxxBfzhpcList = bfzhpc.getGhHkxxBfzhpcList();
        String djxh = bfzhpc.getDjxh();
        String spuuid = bfzhpc.getSpuuid();

        if (CollUtil.isEmpty(ghHkxxBfzhpcList)) {
            return;
        }

        List<HkxxBfzhpcDO> list = new ArrayList<>();
        AdminUserDO user = userService.getUser(SecurityFrameworkUtils.getLoginUserId());

        for (HkxxBfzhpcSaveReqVO.GhHkxxBfzhpcItem item : ghHkxxBfzhpcList) {
            HkxxBfzhpcDO ghHkxxBfzhpc = new HkxxBfzhpcDO();
            ghHkxxBfzhpc.setZhpcid(item.getZhpcid());
            ghHkxxBfzhpc.setDjxh(djxh);
            ghHkxxBfzhpc.setSpuuid(spuuid);
            ghHkxxBfzhpc.setZh(bfzhpc.getJcghzh());
            ghHkxxBfzhpc.setHm(bfzhpc.getJcghhm());
            ghHkxxBfzhpc.setHh(bfzhpc.getJcghhh());
            ghHkxxBfzhpc.setZt(item.getZt());
            ghHkxxBfzhpc.setBz(item.getBz());
            ghHkxxBfzhpc.setCreateBy(user.getUsername());
            ghHkxxBfzhpc.setUpdateBy(user.getUsername());
            list.add(ghHkxxBfzhpc);
        }

        if (!list.isEmpty()) {
            hkxxBfzhpcMapper.batchGhHkxxBfzhpc(list);
        }
    }
}

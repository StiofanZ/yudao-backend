package cn.iocoder.yudao.module.lghjft.service.sjwh.hkxxbfzhpc;

import cn.hutool.core.collection.CollUtil;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hkxxbfzhpc.HkxxBfzhpcDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.hkxxbfzhpc.HkxxBfzhpcMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

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


    //    @Override
//    public void updateHkxxBfzhpc(HkxxBfzhpcSaveReqVO updateReqVO) {
//        // 校验存在
//        validateHkxxBfzhpcExists(Math.toIntExact(updateReqVO.getZhpcid()));
//        // 更新
//        HkxxBfzhpcDO updateObj = BeanUtils.toBean(updateReqVO, HkxxBfzhpcDO.class);
//        hkxxBfzhpcMapper.updateById(updateObj);
//    }
//
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateHkxxBfzhpc(HkxxBfzhpcSaveReqVO updateReqVO) {
        // 1. 批量插入或更新子表 gh_hkxx_bfzhpc
        insertGhHkxxBfzhpcList(updateReqVO);

        // 2. 更新主表 gh_jf（只更新时间，和旧代码一致）
        hkxxBfzhpcMapper.updateBfjfpc(updateReqVO.getSpuuid());
    }

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

            // 关键：zhpcid 可能为 null（新增）或有值（更新）
            ghHkxxBfzhpc.setZhpcid(item.getZhpcid());
            ghHkxxBfzhpc.setDjxh(djxh);
            ghHkxxBfzhpc.setSpuuid(spuuid);
            // 从主对象取账号信息（和旧代码一致）
            ghHkxxBfzhpc.setZh(bfzhpc.getJcghzh());
            ghHkxxBfzhpc.setHm(bfzhpc.getJcghhm());
            ghHkxxBfzhpc.setHh(bfzhpc.getJcghhh());
            // 从子表取 zt 和 bz（关键！）
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

    @Override
    public void deleteHkxxBfzhpcListByIds(List<Integer> ids) {
        // 删除
        hkxxBfzhpcMapper.deleteByIds(ids);
    }


//    private void validateHkxxBfzhpcExists(Integer id) {
//        if (hkxxBfzhpcMapper.selectById(id) == null) {
//            throw exception(HKXX_BFZHPC_NOT_EXISTS);
//        }
//    }

    @Override
    public HkxxBfzhpcRespVO getHkxxBfzhpc(String spuuid) {
        return hkxxBfzhpcMapper.selectByspuuid(spuuid);
    }


    @Override
    public PageResult<HkxxBfzhpcRespVO> getBfjfpcPage(HkxxBfzhpcPageReqVO pageReqVO) {
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            pageReqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        // 创建分页对象
        Page<HkxxBfzhpcRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 执行分页查询
        IPage<HkxxBfzhpcRespVO> ipage = hkxxBfzhpcMapper.selectBfjfpcPage(page, pageReqVO);

        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

}
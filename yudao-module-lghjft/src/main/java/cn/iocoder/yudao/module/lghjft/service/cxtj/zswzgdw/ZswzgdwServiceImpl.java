package cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwQrVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwQrDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw.ZswzgdwMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.ZSWZGDW_NOT_EXISTS;

@Service
@Validated
public class ZswzgdwServiceImpl implements ZswzgdwService {

    @Resource
    private ZswzgdwMapper zswzgdwMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createZswzgdw(ZswzgdwSaveReqVO createReqVO) {
        // 1. 插入主表
        ZswzgdwDO obj = BeanUtils.toBean(createReqVO, ZswzgdwDO.class);
        zswzgdwMapper.insertZswzgdw(obj);

        // 2. 级联插入 zswzgdw_qr (V1: insertZswzgdwQr)
        insertZswzgdwQr(obj.getDjxh(), createReqVO.getZswzgdwQrList());

        return obj.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZswzgdw(ZswzgdwSaveReqVO updateReqVO) {
        // V1: 注释掉了 delete，直接用 batchZswzgdwQr (ON DUPLICATE KEY UPDATE)
        insertZswzgdwQr(updateReqVO.getDjxh(), updateReqVO.getZswzgdwQrList());
        // V1: updateZswzgdw 更新 zswzgdw_qr 表的 updateBy
        ZswzgdwDO updateObj = BeanUtils.toBean(updateReqVO, ZswzgdwDO.class);
        String currentUser = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setQrjgDm(updateReqVO.getQrjgDm());
        // 注：V1 updateZswzgdw 更新的是 zswzgdw_qr，不是 zswzgdw 主表
        // 这里保持一致，不调 updateById
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZswzgdwByDjxhs(String[] djxhs) {
        // V1: 先删 qr，再删主表
        zswzgdwMapper.deleteZswzgdwQrByDjxhs(djxhs);
        zswzgdwMapper.deleteZswzgdwByDjxhs(djxhs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZswzgdw(String djxh) {
        // V1: 先删 qr，再删主表
        zswzgdwMapper.deleteZswzgdwQrByDjxh(djxh);
        zswzgdwMapper.deleteZswzgdwByDjxh(djxh);
    }

    @Override
    public ZswzgdwDO getZswzgdw(String djxh) {
        // V1: selectZswzgdwByDjxh — WITH cascade (returns zswzgdwQrList)
        return zswzgdwMapper.selectZswzgdwByDjxh(djxh);
    }

    @Override
    public PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO) {
        // V1 dept filtering: root = "100000"
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        // V1 uses startPage() for physical pagination. XML returns List, manual pagination.
        List<ZswzgdwDO> allList = zswzgdwMapper.selectZswzgdwList(pageReqVO);
        long total = allList.size();

        // PAGE_SIZE_NONE (-1) means return all records without pagination
        if (pageReqVO.getPageSize() == null || pageReqVO.getPageSize() < 0) {
            return new PageResult<>(allList, total);
        }
        int fromIndex = (pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize();
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), allList.size());
        List<ZswzgdwDO> pageList;
        if (fromIndex >= allList.size()) {
            pageList = List.of();
        } else {
            pageList = allList.subList(fromIndex, toIndex);
        }

        return new PageResult<>(pageList, total);
    }

    // ========== V1: insertZswzgdwQr — batch insert with ON DUPLICATE KEY UPDATE ==========

    /**
     * V1 行为：
     * - 为每条 QR 记录设置 djxh
     * - 设置当前登录用户为 createBy / updateBy
     * - 批量插入（ON DUPLICATE KEY UPDATE）
     */
    private void insertZswzgdwQr(String djxh, List<ZswzgdwQrVO> qrVOList) {
        if (CollUtil.isEmpty(qrVOList)) {
            return;
        }
        String currentUser = SecurityFrameworkUtils.getLoginUserNickname();
        List<ZswzgdwQrDO> list = new ArrayList<>();
        for (ZswzgdwQrVO qrVO : qrVOList) {
            ZswzgdwQrDO qrDO = BeanUtils.toBean(qrVO, ZswzgdwQrDO.class);
            qrDO.setDjxh(djxh);
            qrDO.setCreateBy(currentUser);
            qrDO.setUpdateBy(currentUser);
            list.add(qrDO);
        }
        if (!list.isEmpty()) {
            zswzgdwMapper.batchZswzgdwQr(list);
        }
    }
}

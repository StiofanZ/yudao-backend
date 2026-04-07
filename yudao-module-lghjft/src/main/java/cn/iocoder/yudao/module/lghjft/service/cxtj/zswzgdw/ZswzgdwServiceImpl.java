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
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw.ZswzgdwQrMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.ZSWZGDW_NOT_EXISTS;

@Service
@Validated
public class ZswzgdwServiceImpl implements ZswzgdwService {

    @Resource
    private ZswzgdwMapper zswzgdwMapper;

    @Resource
    private ZswzgdwQrMapper zswzgdwQrMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createZswzgdw(ZswzgdwSaveReqVO createReqVO) {
        // 1. 插入主表
        ZswzgdwDO obj = BeanUtils.toBean(createReqVO, ZswzgdwDO.class);
        zswzgdwMapper.insert(obj);

        // 2. 级联插入 zswzgdw_qr
        insertZswzgdwQrList(obj.getDjxh(), createReqVO.getZswzgdwQrList());

        return obj.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZswzgdw(ZswzgdwSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());

        // 1. 更新主表
        ZswzgdwDO updateObj = BeanUtils.toBean(updateReqVO, ZswzgdwDO.class);
        zswzgdwMapper.updateById(updateObj);

        // 2. 级联更新 zswzgdw_qr：先删旧记录，再批量插入新记录
        // V1 注释掉了 delete 步骤，使用 ON DUPLICATE KEY UPDATE 做 upsert
        // 这里还原完整的 delete + insert 逻辑更安全
        zswzgdwQrMapper.deleteByDjxh(updateReqVO.getDjxh());
        insertZswzgdwQrList(updateReqVO.getDjxh(), updateReqVO.getZswzgdwQrList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZswzgdw(String djxh) {
        validateExists(djxh);

        // 1. 级联删除 zswzgdw_qr
        zswzgdwQrMapper.deleteByDjxh(djxh);

        // 2. 删除主表
        zswzgdwMapper.deleteById(djxh);
    }

    @Override
    public ZswzgdwDO getZswzgdw(String djxh) {
        return zswzgdwMapper.selectById(djxh);
    }

    @Override
    public PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return zswzgdwMapper.selectPage(pageReqVO);
    }

    // ========== 内部方法 ==========

    private void validateExists(String djxh) {
        if (zswzgdwMapper.selectById(djxh) == null) {
            throw exception(ZSWZGDW_NOT_EXISTS);
        }
    }

    /**
     * 批量插入 zswzgdw_qr 记录（还原 V1 cascade 逻辑）
     *
     * V1 在 insertZswzgdwQr 中：
     * - 为每条 QR 记录设置 djxh
     * - 设置当前登录用户为 createBy / updateBy
     * - 批量插入（ON DUPLICATE KEY UPDATE）
     */
    private void insertZswzgdwQrList(String djxh, List<ZswzgdwQrVO> qrList) {
        if (CollUtil.isEmpty(qrList)) {
            return;
        }
        String currentUser = SecurityFrameworkUtils.getLoginUserNickname();
        LocalDateTime now = LocalDateTime.now();

        for (ZswzgdwQrVO qrVO : qrList) {
            ZswzgdwQrDO qrDO = BeanUtils.toBean(qrVO, ZswzgdwQrDO.class);
            qrDO.setDjxh(djxh);
            qrDO.setCreateBy(currentUser);
            qrDO.setUpdateBy(currentUser);
            qrDO.setUpdateTime(now);
            // 保留前端传入的 createTime，若未传则用当前时间
            if (qrDO.getCreateTime() == null) {
                qrDO.setCreateTime(now);
            }
            zswzgdwQrMapper.insert(qrDO);
        }
    }
}

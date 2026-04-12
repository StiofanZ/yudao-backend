package cn.iocoder.yudao.module.lghjft.service.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjtsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf.GhjfcbjqfMapper;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 筹备金全返 Service 实现
 */
@Service
@Validated
public class GhjfcbjqfServiceImpl implements GhjfcbjqfService {

    @Resource
    private GhjfcbjqfMapper ghjfcbjqfMapper;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageResult<GhjfcbjqfDO> getGhjfcbjqfPage(GhjfcbjqfPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        return ghjfcbjqfMapper.selectPage(pageReqVO);
    }

    @Override
    public GhjfcbjqfDO getGhjfcbjqf(Long id) {
        return ghjfcbjqfMapper.selectDetailById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhjfcbjqf(GhjfcbjqfSaveReqVO updateReqVO) {
        GhjfcbjqfDO existing = ghjfcbjqfMapper.selectById(updateReqVO.getGhjfId());
        if (existing == null) {
            throw new ServiceException(404, "筹备金全返记录不存在");
        }

        GhjfcbjqfDO updateObj = new GhjfcbjqfDO();
        updateObj.setGhjfId(updateReqVO.getGhjfId());
        updateObj.setCbjqfzt(updateReqVO.getCbjqfzt());
        updateObj.setCbjqfrq(updateReqVO.getCbjqfrq());
        updateObj.setCbjqfjg(updateReqVO.getCbjqfjg());
        updateObj.setCbjjsbfjczt(updateReqVO.getCbjjsbfjczt());
        updateObj.setCbjjsbfjcrq(updateReqVO.getCbjjsbfjcrq());
        updateObj.setHkpch(updateReqVO.getHkpch());
        updateObj.setQfpch(updateReqVO.getQfpch());
        updateObj.setBz(updateReqVO.getBz());
        updateObj.setUpdateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        if (StringUtils.hasText(loginUserNickname)) {
            updateObj.setUpdateBy(loginUserNickname);
        }
        ghjfcbjqfMapper.updateById(updateObj);

        ghjfcbjqfMapper.deleteCbjtsjfByGhjfId(updateReqVO.getGhjfId());
        List<GhjfcbjtsjfDO> childList = buildChildList(updateReqVO.getGhjfId(), updateReqVO.getGhJfCbjtsjfList());
        if (!CollectionUtils.isEmpty(childList)) {
            ghjfcbjqfMapper.batchInsertCbjtsjf(childList);
        }
    }

    private void fillDeptId(GhjfcbjqfPageReqVO reqVO) {
        if (!StringUtils.hasText(reqVO.getDeptId())) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (loginDeptId != null) {
                reqVO.setDeptId(String.valueOf(loginDeptId));
            }
        }
        if ("100000".equals(reqVO.getDeptId())) {
            reqVO.setDeptId(null);
        }
    }

    private List<GhjfcbjtsjfDO> buildChildList(Long ghjfId, List<GhjfcbjqfSaveReqVO.GhJfCbjtsjfItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return new ArrayList<>();
        }
        List<GhjfcbjtsjfDO> result = new ArrayList<>(items.size());
        for (GhjfcbjqfSaveReqVO.GhJfCbjtsjfItem item : items) {
            GhjfcbjtsjfDO child = GhjfcbjtsjfDO.builder()
                    .ghjfId(ghjfId)
                    .spuuid(item.getSpuuid())
                    .tsjfbj(item.getTsjfbj())
                    .tsjfsm(item.getTsjfsm())
                    .clsj(item.getClsj())
                    .tsjfwj(item.getTsjfwj())
                    .tsjftp(item.getTsjftp())
                    .createBy(item.getCreateBy())
                    .createTime(item.getCreateTime())
                    .updateBy(item.getUpdateBy())
                    .updateTime(item.getUpdateTime())
                    .build();
            result.add(child);
        }
        return result;
    }
}

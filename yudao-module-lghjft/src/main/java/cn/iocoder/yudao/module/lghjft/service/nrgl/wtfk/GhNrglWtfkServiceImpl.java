package cn.iocoder.yudao.module.lghjft.service.nrgl.wtfk;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkClmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.wtfk.GhNrglWtfkClmxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.wtfk.GhNrglWtfkMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.OPERATION_NOT_PERMITTED;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.WTFK_NOT_EXISTS;

@Service
@Validated
public class GhNrglWtfkServiceImpl implements GhNrglWtfkService {

    @Resource
    private GhNrglWtfkMapper wtfkMapper;
    @Resource
    private GhNrglWtfkClmxMapper wtfkClmxMapper;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createGhNrglWtfk(GhNrglWtfkSaveReqVO createReqVO) {
        GhNrglWtfkDO wtfk = BeanUtils.toBean(createReqVO, GhNrglWtfkDO.class);
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        wtfk.setYhId(loginUserId);
        wtfk.setZt(createReqVO.getZt() == null ? 1 : createReqVO.getZt());
        wtfk.setFkbh("FK" + System.currentTimeMillis());
        if (loginUserId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(loginUserId);
            if (user != null) {
                wtfk.setYhmc(user.getNickname());
            }
        }
        if (CollUtil.isEmpty(createReqVO.getFjList())) {
            wtfk.setFjList(Collections.emptyList());
        }
        wtfkMapper.insert(wtfk);
        return wtfk.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhNrglWtfk(GhNrglWtfkSaveReqVO updateReqVO) {
        validateGhNrglWtfkExists(updateReqVO.getId());
        GhNrglWtfkDO updateObj = BeanUtils.toBean(updateReqVO, GhNrglWtfkDO.class);
        if (updateReqVO.getFjList() == null) {
            updateObj.setFjList(null);
        }
        wtfkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhNrglWtfkWithOwnerCheck(GhNrglWtfkSaveReqVO updateReqVO) {
        GhNrglWtfkDO wtfk = wtfkMapper.selectById(updateReqVO.getId());
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
        validateWtfkOwner(wtfk);
        updateGhNrglWtfk(updateReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGhNrglWtfk(Long id, Boolean isAdminView) {
        GhNrglWtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
        // IDOR check: only the owner can delete from App (non-admin) view
        if (!Boolean.TRUE.equals(isAdminView)) {
            validateWtfkOwner(wtfk);
        }
        int currentStatus = Objects.requireNonNullElse(wtfk.getZt(), 1);
        int nextStatus = Boolean.TRUE.equals(isAdminView)
                ? (currentStatus == 5 ? 6 : 4)
                : (currentStatus == 4 ? 6 : 5);
        GhNrglWtfkDO updateObj = new GhNrglWtfkDO();
        updateObj.setId(id);
        updateObj.setZt(nextStatus);
        wtfkMapper.updateById(updateObj);
    }

    @Override
    public void deleteGhNrglWtfkListByIds(List<Long> ids, Boolean isAdminView) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        ids.forEach(id -> deleteGhNrglWtfk(id, isAdminView));
    }

    @Override
    public GhNrglWtfkDO getGhNrglWtfk(Long id) {
        return wtfkMapper.selectById(id);
    }

    @Override
    public PageResult<GhNrglWtfkDO> getGhNrglWtfkPage(GhNrglWtfkPageReqVO pageReqVO) {
        if (pageReqVO.getZt() != null) {
            if (Objects.equals(pageReqVO.getZt(), 3)) {
                pageReqVO.setStatuses(Arrays.asList(3, 4, 5));
            } else if (Objects.equals(pageReqVO.getZt(), 1)) {
                pageReqVO.setStatuses(Arrays.asList(0, 1));
            } else {
                pageReqVO.setStatuses(Collections.singletonList(pageReqVO.getZt()));
            }
        }
        if (Boolean.TRUE.equals(pageReqVO.getIsAdminView())) {
            if (CollUtil.isEmpty(pageReqVO.getStatuses())) {
                pageReqVO.setStatuses(Arrays.asList(0, 1, 2, 3, 5));
            } else {
                pageReqVO.setStatuses(pageReqVO.getStatuses().stream()
                        .filter(status -> status != 4 && status != 6)
                        .collect(Collectors.toList()));
            }
        } else {
            pageReqVO.setYhId(SecurityFrameworkUtils.getLoginUserId());
            if (CollUtil.isEmpty(pageReqVO.getStatuses())) {
                pageReqVO.setStatuses(Arrays.asList(0, 1, 2, 3, 4));
            } else {
                pageReqVO.setStatuses(pageReqVO.getStatuses().stream()
                        .filter(status -> status != 5 && status != 6)
                        .collect(Collectors.toList()));
            }
        }
        return wtfkMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleProcessWithOwnerCheck(@Valid GhNrglWtfkClReqVO reqVO) {
        GhNrglWtfkDO wtfk = wtfkMapper.selectById(reqVO.getId());
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
        validateWtfkOwner(wtfk);
        handleProcess(reqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleProcess(@Valid GhNrglWtfkClReqVO reqVO) {
        GhNrglWtfkDO wtfk = wtfkMapper.selectById(reqVO.getId());
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        String clrmc = null;
        if (loginUserId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(loginUserId);
            clrmc = user != null ? user.getNickname() : null;
        }
        GhNrglWtfkClmxDO clmx = GhNrglWtfkClmxDO.builder()
                .wtfkId(reqVO.getId())
                .clrId(loginUserId)
                .clrmc(clrmc)
                .clsm(reqVO.getClsm())
                .zt(reqVO.getZt())
                .build();
        wtfkClmxMapper.insert(clmx);

        GhNrglWtfkDO updateObj = new GhNrglWtfkDO();
        updateObj.setId(reqVO.getId());
        updateObj.setZt(reqVO.getZt());
        updateObj.setClrId(loginUserId);
        updateObj.setClsj(LocalDateTime.now());
        updateObj.setClsm(reqVO.getClsm());
        wtfkMapper.updateById(updateObj);
    }

    @Override
    public List<GhNrglWtfkClmxResVO> getGhNrglWtfkClmxList(Long wtfkId) {
        List<GhNrglWtfkClmxDO> list = wtfkClmxMapper.selectListByWtfkId(wtfkId);
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<Long> clrIds = list.stream()
                .map(GhNrglWtfkClmxDO::getClrId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = clrIds.isEmpty() ? Collections.emptyMap() : adminUserApi.getUserMap(clrIds);
        List<GhNrglWtfkClmxResVO> result = new ArrayList<>(list.size());
        for (GhNrglWtfkClmxDO item : list) {
            GhNrglWtfkClmxResVO respVO = BeanUtils.toBean(item, GhNrglWtfkClmxResVO.class);
            if (respVO.getClrmc() == null && item.getClrId() != null) {
                AdminUserRespDTO user = userMap.get(item.getClrId());
                if (user != null) {
                    respVO.setClrmc(user.getNickname());
                }
            }
            result.add(respVO);
        }
        return result;
    }

    @Override
    public GhNrglWtfkResVO getGhNrglWtfkDetail(Long id) {
        GhNrglWtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
        GhNrglWtfkResVO respVO = BeanUtils.toBean(wtfk, GhNrglWtfkResVO.class);
        if (wtfk.getClrId() != null) {
            AdminUserRespDTO user = adminUserApi.getUser(wtfk.getClrId());
            if (user != null) {
                respVO.setClrmc(user.getNickname());
            }
        }
        return respVO;
    }

    private void validateGhNrglWtfkExists(Long id) {
        if (wtfkMapper.selectById(id) == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
    }

    private void validateWtfkOwner(GhNrglWtfkDO wtfk) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (!Objects.equals(wtfk.getYhId(), loginUserId)) {
            throw exception(OPERATION_NOT_PERMITTED);
        }
    }
}

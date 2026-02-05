package cn.iocoder.yudao.module.lghjft.service.wtfk;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkLogDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk.WtfkLogMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk.WtfkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 * 工会经费通-问题反馈 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WtfkServiceImpl implements WtfkService {

    @Resource
    private WtfkMapper wtfkMapper;
    @Resource
    private AdminUserApi adminUserApi; // 用于获取系统用户信息
    @Resource
    private WtfkLogMapper wtfkLogMapper; // 日志 Mapper

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleProcess(WtfkProcessReqVO reqVO) {
        // 1. 校验主表数据是否存在
        WtfkDO wtfk = wtfkMapper.selectById(reqVO.getId());
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }

        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();

        // 2. 插入处理日志
        WtfkLogDO logDO = WtfkLogDO.builder()
                .feedbackId(reqVO.getId())
                .operatorId(loginUserId)
                .content(reqVO.getProcessNotes())
                .status(reqVO.getStatus())
                .build();
        wtfkLogMapper.insert(logDO);

        // 3. 更新主表状态
        WtfkDO updateObj = new WtfkDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(reqVO.getStatus());
        updateObj.setProcessNotes(reqVO.getProcessNotes());
        updateObj.setProcessTime(LocalDateTime.now());
        updateObj.setProcessorId(loginUserId);

        wtfkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createWtfk(WtfkSaveReqVO createReqVO) {
        WtfkDO wtfk = BeanUtils.toBean(createReqVO, WtfkDO.class);
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();

        wtfk.setUserId(loginUserId);
        if (loginUserId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(loginUserId);
            if (user != null) wtfk.setUserName(user.getNickname());
        }

        wtfk.setFeedbackId("FB" + System.currentTimeMillis());
        wtfk.setStatus(1); // 默认待处理

        // 直接转换并赋值 Files，利用 DO 中的 TypeHandler 自动存 JSON
        // 这里假设 createReqVO.getFiles() 返回的是 List<FileInfoVO>，需要转成 DO 内定义的 List<FileItem>
        // 如果字段类型不一致，需要加一层转换；如果 VO 中直接复用了 DO.FileItem 或结构一致，BeanUtils 会处理
        if (CollUtil.isNotEmpty(createReqVO.getFiles())) {
            List<WtfkDO.FileItem> fileItems = BeanUtils.toBean(createReqVO.getFiles(), WtfkDO.FileItem.class);
            wtfk.setFiles(fileItems);
        }

        wtfkMapper.insert(wtfk);
        return wtfk.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWtfk(WtfkSaveReqVO updateReqVO) {
        validateWtfkExists(updateReqVO.getId());

        WtfkDO updateObj = BeanUtils.toBean(updateReqVO, WtfkDO.class);

        // 手动处理 Files 列表转换
        if (CollUtil.isNotEmpty(updateReqVO.getFiles())) {
            List<WtfkDO.FileItem> fileItems = BeanUtils.toBean(updateReqVO.getFiles(), WtfkDO.FileItem.class);
            updateObj.setFiles(fileItems);
        } else {
            // 如果前端传空数组，意味着清空附件
            updateObj.setFiles(Collections.emptyList());
        }

        if (Objects.equals(updateReqVO.getStatus(), 1)) {
            updateObj.setProcessTime(LocalDateTime.now());
            updateObj.setProcessorId(getLoginUserId());
        }

        wtfkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWtfk(Long id, Boolean isAdminView) {
        WtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);
        }

        int currentStatus = wtfk.getStatus();
        int nextStatus;

        if (Boolean.TRUE.equals(isAdminView)) {
            nextStatus = (currentStatus == 5) ? 6 : 4;
        } else {
            nextStatus = (currentStatus == 4) ? 6 : 5;
        }

        WtfkDO update = new WtfkDO();
        update.setId(id);
        update.setStatus(nextStatus);
        wtfkMapper.updateById(update);
    }

    @Override
    public void deleteWtfkListByIds(List<Long> ids, Boolean isAdminView) {
        if (CollUtil.isNotEmpty(ids)) {
            ids.forEach(id -> this.deleteWtfk(id, isAdminView));
        }
    }

    private void validateWtfkExists(Long id) {
        if (wtfkMapper.selectById(id) == null) {
            throw exception(WTFK_NOT_EXISTS);
        }
    }

    @Override
    public WtfkDO getWtfk(Long id) {
        return wtfkMapper.selectById(id);
    }

    @Override
    public PageResult<WtfkDO> getWtfkPage(WtfkPageReqVO pageReqVO) {
        // 状态筛选逻辑
        if (pageReqVO.getStatus() != null) {
            if (Objects.equals(pageReqVO.getStatus(), 3)) {
                pageReqVO.setStatuses(Arrays.asList(3, 4, 5));
            } else if (Objects.equals(pageReqVO.getStatus(), 1)) {
                pageReqVO.setStatuses(Arrays.asList(0, 1));
            } else {
                pageReqVO.setStatuses(Collections.singletonList(pageReqVO.getStatus()));
            }
        }

        if (pageReqVO.getIsAdminView() == null || !pageReqVO.getIsAdminView()) {
            pageReqVO.setUserId(SecurityFrameworkUtils.getLoginUserId());
            if (CollUtil.isEmpty(pageReqVO.getStatuses())) {
                pageReqVO.setStatuses(Arrays.asList(0, 1, 2, 3, 4));
            } else {
                pageReqVO.setStatuses(pageReqVO.getStatuses().stream()
                        .filter(s -> s != 5 && s != 6).collect(Collectors.toList()));
            }
        } else {
            if (CollUtil.isEmpty(pageReqVO.getStatuses())) {
                pageReqVO.setStatuses(Arrays.asList(0, 1, 2, 3, 5));
            } else {
                pageReqVO.setStatuses(pageReqVO.getStatuses().stream()
                        .filter(s -> s != 4 && s != 6).collect(Collectors.toList()));
            }
        }

        return wtfkMapper.selectPage(pageReqVO);
    }

    @Override
    public List<Map<String, Object>> getWtfkLogList(Long feedbackId) {
        List<WtfkLogDO> list = wtfkLogMapper.selectListByFeedbackId(feedbackId);
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }

        Set<Long> userIds = cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet(list, WtfkLogDO::getOperatorId);
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);

        return list.stream().map(log -> {
            Map<String, Object> map = BeanUtils.toBean(log, HashMap.class);
            AdminUserRespDTO user = userMap.get(log.getOperatorId());
            map.put("operatorName", user != null ? user.getNickname() : "系统");
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public WtfkRespVO getWtfkDetail(Long id) {
        WtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) return null;

        WtfkRespVO respVO = BeanUtils.toBean(wtfk, WtfkRespVO.class);

        if (respVO.getProcessorId() != null) {
            AdminUserRespDTO user = adminUserApi.getUser(respVO.getProcessorId());
            if (user != null) respVO.setProcessorName(user.getNickname());
        }

        // 确保 WtfkRespVO 中的 files 字段类型与 DO 转换后的类型兼容

        return respVO;
    }
}
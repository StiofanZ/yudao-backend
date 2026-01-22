package cn.iocoder.yudao.module.lghjft.service.wtfk;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
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
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk.WtfkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
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
    private WtfkLogMapper wtfkLogMapper; // 注入建立的日志 Mapper
    /**
     * 实现处理逻辑：多次处理记录并更新主表状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 开启事务，任何异常触发回滚
    public void handleProcess(WtfkProcessReqVO reqVO) {
        // 1. 校验主表数据是否存在
        WtfkDO wtfk = wtfkMapper.selectById(reqVO.getId());
        if (wtfk == null) {
            throw exception(WTFK_NOT_EXISTS);// 替换为你定义的错误码
        }

        // 获取当前操作员 ID
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();

        // 2. 向 lghjft_wtfk_log 插入一条处理记录
        WtfkLogDO logDO = WtfkLogDO.builder()
                .feedbackId(reqVO.getId())
                .operatorId(loginUserId)

                .content(reqVO.getProcessNotes()) // 此次填写的处理意见
                .status(reqVO.getStatus())       // 此次选择的状态
                .build();
        wtfkLogMapper.insert(logDO);

        // 3. 更新主表 lghjft_wtfk
        // 更新内容：状态、最新备注、处理时间、处理人ID
        WtfkDO updateObj = new WtfkDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(reqVO.getStatus());
        updateObj.setProcessNotes(reqVO.getProcessNotes()); // 更新为最新的那条备注
        updateObj.setProcessTime(LocalDateTime.now());     // 设置处理时间为当前
        updateObj.setProcessorId(loginUserId);             // 设置当前处理人ID

        wtfkMapper.updateById(updateObj);
    }

    @Override
    public Long createWtfk(WtfkSaveReqVO createReqVO) {
        // 1. 插入前转换 DO
        WtfkDO wtfk = BeanUtils.toBean(createReqVO, WtfkDO.class);

        // 2. 获取当前登录用户 ID
        // 使用框架提供的 SecurityFrameworkUtils 获取当前会话 ID
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        wtfk.setUserId(loginUserId);

        // 3. 获取并设置用户名（昵称）
        // 通过 AdminUserApi 查找用户信息，确保数据库中 userName 字段不为空
        if (loginUserId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(loginUserId);
            if (user != null) {
                wtfk.setUserName(user.getNickname());
            }
        }

        // 4. 生成业务单号 (例如：FB + 时间戳)
        // 也可以使用框架自带的序列号生成工具
        String bizId = "FB" + System.currentTimeMillis();
        wtfk.setFeedbackId(bizId);

        // 3. 插入数据库
        wtfkMapper.insert(wtfk);
        return wtfk.getId();
    }

    @Override
    public void updateWtfk(WtfkSaveReqVO updateReqVO) {

        // 1. 校验存在
        validateWtfkExists(updateReqVO.getId());

        // 2. 更新数据转换
        WtfkDO updateObj = BeanUtils.toBean(updateReqVO, WtfkDO.class);

        // 3. 核心：如果状态变为“已处理(1)”，则设置当前时间
        if (Objects.equals(updateReqVO.getStatus(), 1)) {
            updateObj.setProcessTime(LocalDateTime.now());
            // 如果能获取到当前登录用户 ID，也可以顺便设置处理人
            updateObj.setProcessorId(getLoginUserId());
        }

        // 4. 更新
        wtfkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWtfk(Long id) {

        // 校验存在
        validateWtfkExists(id);
        // 删除
        WtfkDO updateObj = new WtfkDO().setId(id).setStatus(4);
        wtfkMapper.updateById(updateObj);

        //wtfkLogMapper.deleteByFeedbackId(id);
    }

    @Override
        public void deleteWtfkListByIds(List<Long> ids) {
        // 删除
        //wtfkMapper.deleteByIds(ids);
        if (CollUtil.isNotEmpty(ids)) {
            ids.forEach(this::deleteWtfk);
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
        // 判断是否【非】管理端视图
        // 如果 isAdminView 为 null (默认) 或 false，则认为是“用户个人中心”场景
        if (pageReqVO.getIsAdminView() == null || !pageReqVO.getIsAdminView()) {
            // 获取当前登录人 ID 并强制赋值给查询参数
            pageReqVO.setUserId(SecurityFrameworkUtils.getLoginUserId());
        }

        // 如果 isAdminView 为 true，则不进入上面的 if 语句
        // 这样 userId 保持前端传来的值（通常是空），实现查询全量数据（管理端场景）
        return wtfkMapper.selectPage(pageReqVO);

    }

    @Override
    public List<Map<String, Object>> getWtfkLogList(Long feedbackId) {
        // 1. 获取原始日志 DO 列表
        List<WtfkLogDO> list = wtfkLogMapper.selectListByFeedbackId(feedbackId);
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }

        // 2. 批量获取用户信息，避免在循环中查数据库
        Set<Long> userIds = cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet(list, WtfkLogDO::getOperatorId);
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);

        // 3. 转换为 Map 列表并补齐 operatorName
        return list.stream().map(log -> {
            // 将 DO 转为 Map
            Map<String, Object> map = BeanUtils.toBean(log, HashMap.class);

            // 查找操作人姓名
            AdminUserRespDTO user = userMap.get(log.getOperatorId());
            // 补齐字段，对应前端的 log.operatorName
            map.put("operatorName", user != null ? user.getNickname() : "系统");

            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public WtfkRespVO getWtfkDetail(Long id) {
        // 1. 查询数据库主表数据 (DO)
        WtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) {
            return null;
        }

        // 2. 将 DO 转换为 VO
        WtfkRespVO respVO = BeanUtils.toBean(wtfk, WtfkRespVO.class);

        // 3. 补齐处理人姓名 (processorName)
        if (respVO.getProcessorId() != null) {
            // 调用你已经注入好的 adminUserApi
            AdminUserRespDTO user = adminUserApi.getUser(respVO.getProcessorId());
            if (user != null) {
                respVO.setProcessorName(user.getNickname());
            }
        }

        return respVO;
    }

}
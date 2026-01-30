package cn.iocoder.yudao.module.lghjft.service.wtfk;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.file.dal.dataobject.dos.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.mysql.FileInfoMapper;
import cn.iocoder.yudao.module.file.service.IFileInfoService;
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

    @Resource
    private IFileInfoService fileInfoService;//关于文件上传

    @Resource
    private FileInfoMapper fileInfoMapper; //关于文件上传
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
    @Transactional(rollbackFor = Exception.class)
    public Long createWtfk(WtfkSaveReqVO createReqVO) {
        // 1. 基础信息设置
        WtfkDO wtfk = BeanUtils.toBean(createReqVO, WtfkDO.class);
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        wtfk.setUserId(loginUserId);

        // 获取昵称
        if (loginUserId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(loginUserId);
            if (user != null) wtfk.setUserName(user.getNickname());
        }

        // 生成业务单号
        wtfk.setFeedbackId("FB" + System.currentTimeMillis());

        wtfk.setStatus(1); //新增的反馈单默认设为1待处理

        // 插入数据库
        wtfkMapper.insert(wtfk);

        //  使用 Service 批量绑定附件
        this.saveFileInfos(wtfk.getId(), createReqVO.getFileUrls());

        return wtfk.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
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
        fileInfoMapper.delete(new LambdaQueryWrapperX<FileInfoDO>()
                .eq(FileInfoDO::getBizId, updateReqVO.getId())
                .eq(FileInfoDO::getTableName, "lghjft_wtfk"));
        this.saveFileInfos(updateReqVO.getId(), updateReqVO.getFileUrls());
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

        // 1. 核心逻辑：状态转换处理
        if (pageReqVO.getStatus() != null) {
            if (Objects.equals(pageReqVO.getStatus(), 3)) {
                // 选“已处理”，实际查 3 和 4
                pageReqVO.setStatuses(Arrays.asList(3, 4));
            } else if (Objects.equals(pageReqVO.getStatus(), 1)) {
                // 选“待处理”，实际查 0 和 1
                pageReqVO.setStatuses(Arrays.asList(0, 1));
            } else {
                // 其他状态（如跟进中），查它自己
                pageReqVO.setStatuses(Collections.singletonList(pageReqVO.getStatus()));
            }
        }


        if (pageReqVO.getIsAdminView() == null || !pageReqVO.getIsAdminView()) {
            // 【普通用户端】逻辑：锁定用户 ID，statuses 保持包含 4 的状态
            pageReqVO.setUserId(SecurityFrameworkUtils.getLoginUserId());
        } else {
            // 【管理员端】逻辑：处理“isAdminView 为 true”的情况
            // 如果管理员没有筛选特定状态（statuses 为空），为了不显示 4，强制设置范围为 0, 1, 2, 3
            if (CollUtil.isEmpty(pageReqVO.getStatuses())) {
                pageReqVO.setStatuses(Arrays.asList(0, 1, 2, 3));
            } else {
                // 如果管理员筛选了“已处理”（此时 statuses 包含 3 和 4），则移除 4
                List<Integer> filteredStatuses = pageReqVO.getStatuses().stream()
                        .filter(s -> !Objects.equals(s, 4))
                        .collect(Collectors.toList());
                pageReqVO.setStatuses(filteredStatuses);
            }
        }

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
        WtfkDO wtfk = wtfkMapper.selectById(id);
        if (wtfk == null) return null;

        WtfkRespVO respVO = BeanUtils.toBean(wtfk, WtfkRespVO.class);

        // 补齐处理人姓名
        if (respVO.getProcessorId() != null) {
            AdminUserRespDTO user = adminUserApi.getUser(respVO.getProcessorId());
            if (user != null) respVO.setProcessorName(user.getNickname());
        }

        // 4. 使用 Service 查询附件 (重构点)
        List<FileInfoDO> files = fileInfoMapper.selectList(new LambdaQueryWrapperX<FileInfoDO>()
                .eq(FileInfoDO::getBizId, id)
                .eq(FileInfoDO::getTableName, "lghjft_wtfk"));
        if (CollUtil.isNotEmpty(files)) {
            respVO.setFileUrls(files.stream()
                    .map(FileInfoDO::getFileUrl)
                    .collect(Collectors.toList()));
        }

        return respVO;
    }
    /**
     * 封装 FileInfoDO 并调用 Service 批量入库
     */
    private void saveFileInfos(Long bizId, List<String> fileUrls) {
        if (CollUtil.isEmpty(fileUrls)) {
            return;
        }

        // 1. 直接获取 Long 类型的用户 ID
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        // 如果获取不到登录用户（例如未登录反馈），给一个默认的管理员 ID 1L
        Long creatorId = (loginUserId != null) ? loginUserId : 1L;

        List<FileInfoDO> fileInfoList = fileUrls.stream().map(url -> {
            FileInfoDO fileInfo = new FileInfoDO();
            fileInfo.setBizId(bizId);
            fileInfo.setTableName("lghjft_wtfk");
            fileInfo.setFileUrl(url);

            String fileName = url.substring(url.lastIndexOf("/") + 1);
            fileInfo.setFileName(fileName);
            fileInfo.setFileOriginName(fileName);

            // 2. 修复点：直接传入 Long 类型变量
            fileInfo.setCreateBy(creatorId);

            fileInfo.setCreateTime(LocalDateTime.now());
            fileInfo.setDelFlag("0");
            fileInfo.setFileType("1");

            return fileInfo;
        }).collect(Collectors.toList());

        fileInfoService.insertBizIdBatchFileInfo(fileInfoList);
    }
}
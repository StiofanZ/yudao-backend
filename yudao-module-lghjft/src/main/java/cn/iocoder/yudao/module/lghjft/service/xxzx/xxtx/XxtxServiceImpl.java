package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageReceiverDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxMessageMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxMessageReceiverMapper;
import cn.iocoder.yudao.module.lghjft.service.dx.DxfwService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.sms.SmsLogDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import cn.iocoder.yudao.module.system.enums.sms.SmsSendStatusEnum;
import cn.iocoder.yudao.module.system.service.sms.SmsLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.CONTENT_NOT_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.OPERATION_NOT_PERMITTED;

/**
 * 消息提醒 Service 实现类
 */
@Service
public class XxtxServiceImpl implements XxtxService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Resource
    private XxtxMessageMapper messageMapper;
    @Resource
    private XxtxMessageReceiverMapper messageReceiverMapper;
    @Resource
    private AdminUserMapper userMapper;
    @Resource
    private DeptMapper deptMapper;
    @Resource
    private DxfwService dxfwService;
    @Resource
    private SmsLogService smsLogService;

    @Override
    public Long createMessage(XxtxMessageSaveReqVO createReqVO) {
        yzfsxx(createReqVO);
        XxtxMessageDO message = BeanUtils.toBean(createReqVO, XxtxMessageDO.class);
        // 设置发送者信息
        message.setSenderId(SecurityFrameworkUtils.getLoginUserId());
        message.setSenderName(SecurityFrameworkUtils.getLoginUserNickname());
        // 设置状态为草稿
        message.setStatus(0);
        if (message.getSfznx() == null) {
            message.setSfznx(1);
        }
        if (message.getSfdx() == null) {
            message.setSfdx(0);
        }
        messageMapper.insert(message);
        return message.getId();
    }

    @Override
    public void updateMessage(XxtxMessageSaveReqVO updateReqVO) {
        // 校验消息是否存在
        validateMessageExists(updateReqVO.getId());
        yzfsxx(updateReqVO);
        XxtxMessageDO message = BeanUtils.toBean(updateReqVO, XxtxMessageDO.class);
        messageMapper.updateById(message);
    }

    @Override
    public void deleteMessage(Long id) {
        // 校验消息是否存在
        validateMessageExists(id);
        messageMapper.deleteById(id);
        // 删除相关的接收记录
        messageReceiverMapper.delete(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, id));
    }

    @Override
    public void deleteMessageList(List<Long> ids) {
        messageMapper.deleteBatchIds(ids);
        // 删除相关的接收记录
        messageReceiverMapper.delete(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                .in(XxtxMessageReceiverDO::getMessageId, ids));
    }

    @Transactional
    @Override
    public void sendMessage(XxtxMessageSendReqVO sendReqVO) {
        // 1. 校验消息是否存在
        XxtxMessageDO message = validateMessageExists(sendReqVO.getMessageId());

        // 2. 更新消息状态为已发送
        message.setStatus(1);
        message.setSendTime(formatter.format(LocalDateTime.now()));
        messageMapper.updateById(message);

        // 3. 处理接收者，支持部门和用户
        // 优先使用请求参数中的接收者，如果为空则使用消息中保存的接收者
        List<Long> targetDeptIds = sendReqVO.getDeptIds();
        if (targetDeptIds == null || targetDeptIds.isEmpty()) {
            targetDeptIds = message.getDeptIds();
        }

        List<Long> targetUserIds = sendReqVO.getUserIds();
        if (targetUserIds == null || targetUserIds.isEmpty()) {
            targetUserIds = message.getUserIds();
        }

        Set<Long> userIds = new HashSet<>();

        // 3.1 确定接收用户列表
        // 如果指定了接收人，则仅向指定的接收人发送消息
        if (targetUserIds != null && !targetUserIds.isEmpty()) {
            userIds.addAll(targetUserIds);
        }
        // 如果未指定接收人，则向指定的部门下所有人发送消息
        else if (targetDeptIds != null && !targetDeptIds.isEmpty()) {
            for (Long deptId : targetDeptIds) {
                // 获取部门下的所有用户
                List<AdminUserDO> users = userMapper.selectList(new LambdaQueryWrapper<AdminUserDO>()
                        .eq(AdminUserDO::getDeptId, deptId)
                        .eq(AdminUserDO::getStatus, 0)); // 只发送给启用状态的用户
                users.forEach(user -> userIds.add(user.getId()));
            }
        }

        // 3.3 保存用户接收记录（实际消息接收者）
        for (Long userId : userIds) {
            XxtxMessageReceiverDO receiver = getOrCreateReceiver(message.getId(), userId);
            if (Integer.valueOf(1).equals(message.getSfdx())) {
                AdminUserDO user = userMapper.selectById(userId);
                try {
                    Long dxrzid = dxfwService.fstzdx(user != null ? user.getMobile() : null, userId,
                            UserTypeEnum.ADMIN.getValue(), message.getDxnr());
                    messageReceiverMapper.updateById(XxtxMessageReceiverDO.builder()
                            .id(receiver.getId())
                            .dxrzid(dxrzid)
                            .dxzt(SmsSendStatusEnum.INIT.getStatus())
                            .dxbz("已提交发送")
                            .build());
                    tbDxjg(receiver.getId(), dxrzid);
                } catch (Exception ex) {
                    messageReceiverMapper.updateById(XxtxMessageReceiverDO.builder()
                            .id(receiver.getId())
                            .dxzt(SmsSendStatusEnum.FAILURE.getStatus())
                            .dxbz(ex.getMessage())
                            .build());
                }
            }
        }
    }

    @Override
    public void recallMessage(Long id) {
        // 校验消息是否存在
        XxtxMessageDO message = validateMessageExists(id);
        // 更新消息状态为已撤回
        message.setStatus(2);
        messageMapper.updateById(message);
    }

    @Override
    public PageResult<XxtxMessageResVO> getMessagePage(XxtxMessagePageReqVO reqVO) {
        PageResult<XxtxMessageDO> pageResult = messageMapper.selectPage(reqVO, new LambdaQueryWrapperX<XxtxMessageDO>()
                .likeIfPresent(XxtxMessageDO::getTitle, reqVO.getTitle())
                .eqIfPresent(XxtxMessageDO::getMessageType, reqVO.getMessageType())
                .eqIfPresent(XxtxMessageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(XxtxMessageDO::getSenderId, reqVO.getSenderId())
                .betweenIfPresent(XxtxMessageDO::getSendTime, reqVO.getSendTimeBegin(), reqVO.getSendTimeEnd())
                .orderByDesc(XxtxMessageDO::getId));
        List<XxtxMessageResVO> list = pageResult.getList().stream().map(this::buildMessageResVO).toList();
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public XxtxMessageResVO getMessageDetail(Long id) {
        XxtxMessageDO message = validateMessageExists(id);
        // IDOR check: verify the current user is a receiver of this message
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            XxtxMessageReceiverDO receiver = messageReceiverMapper.selectOne(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                    .eq(XxtxMessageReceiverDO::getMessageId, id)
                    .eq(XxtxMessageReceiverDO::getReceiverId, loginUserId)
                    .eq(XxtxMessageReceiverDO::getReceiverType, 1));
            // Also allow the sender to view the message
            if (receiver == null && !loginUserId.equals(message.getSenderId())) {
                throw exception(OPERATION_NOT_PERMITTED);
            }
        }
        return buildMessageResVO(message);
    }

    @Override
    public XxtxMessageDO getMessage(Long id) {
        return validateMessageExists(id);
    }

    @Override
    public void markMessageAsRead(Long messageId, Long userId) {
        messageReceiverMapper.update(null, new LambdaUpdateWrapper<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, messageId)
                .eq(XxtxMessageReceiverDO::getReceiverType, 1)
                .eq(XxtxMessageReceiverDO::getReceiverId, userId)
                .set(XxtxMessageReceiverDO::getReadStatus, 1)
                .set(XxtxMessageReceiverDO::getReadTime, formatter.format(LocalDateTime.now())));
    }

    @Override
    public PageResult<XxtxMessageReceiverDO> getMessageReceiverPage(XxtxMessagePageReqVO reqVO) {
        List<XxtxMessageReceiverDO> allList = messageReceiverMapper.selectList(new LambdaQueryWrapperX<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getReceiverId, reqVO.getReceiverId())
                .eq(XxtxMessageReceiverDO::getDeleted, 0)
                .in(XxtxMessageReceiverDO::getReadStatus, ObjectUtils.isEmpty(reqVO.getReadStatus()) ? List.of(0, 1).toArray() : new Object[]{reqVO.getReadStatus()})
                .orderByDesc(XxtxMessageReceiverDO::getId));
        List<XxtxMessageReceiverDO> filterList = allList.stream().filter(receiverDO -> {
            XxtxMessageDO messageDO = messageMapper.selectById(receiverDO.getMessageId());
            return messageDO != null && Integer.valueOf(1).equals(messageDO.getSfznx()) && Integer.valueOf(1).equals(messageDO.getStatus());
        }).toList();
        return toPageResult(filterList, reqVO.getPageNo(), reqVO.getPageSize());
    }

    @Override
    public List<XxtxReceiverResVO> getMessageReceiverList(Long messageId) {
        validateMessageExists(messageId);
        List<XxtxMessageReceiverDO> receiverList = messageReceiverMapper.selectList(new LambdaQueryWrapperX<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, messageId)
                .eq(XxtxMessageReceiverDO::getDeleted, 0)
                .orderByDesc(XxtxMessageReceiverDO::getId));
        if (receiverList.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Long, AdminUserDO> userMap = hqYhMap(receiverList.stream().map(XxtxMessageReceiverDO::getReceiverId).toList());
        return receiverList.stream().map(receiver -> buildReceiverResVO(receiver, userMap.get(receiver.getReceiverId()))).toList();
    }

    /**
     * 保存接收者记录
     */
    private void saveReceiver(Long messageId, Integer receiverType, Long receiverId) {
        XxtxMessageReceiverDO receiver = new XxtxMessageReceiverDO();
        receiver.setMessageId(messageId);
        receiver.setReceiverType(receiverType);
        receiver.setReceiverId(receiverId);
        receiver.setReadStatus(0); // 初始状态为未读
        messageReceiverMapper.insert(receiver);
    }

    private XxtxMessageReceiverDO getOrCreateReceiver(Long messageId, Long receiverId) {
        XxtxMessageReceiverDO receiver = messageReceiverMapper.selectOne(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, messageId)
                .eq(XxtxMessageReceiverDO::getReceiverId, receiverId)
                .eq(XxtxMessageReceiverDO::getReceiverType, 1));
        if (receiver != null) {
            return receiver;
        }
        saveReceiver(messageId, 1, receiverId);
        return messageReceiverMapper.selectOne(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, messageId)
                .eq(XxtxMessageReceiverDO::getReceiverId, receiverId)
                .eq(XxtxMessageReceiverDO::getReceiverType, 1));
    }

    /**
     * 校验消息是否存在
     */
    private XxtxMessageDO validateMessageExists(Long id) {
        XxtxMessageDO message = messageMapper.selectById(id);
        if (message == null) {
            throw exception(CONTENT_NOT_EXISTS);
        }
        return message;
    }

    private void yzfsxx(XxtxMessageSaveReqVO reqVO) {
        int sfznx = reqVO.getSfznx() == null ? 1 : reqVO.getSfznx();
        int sfdx = reqVO.getSfdx() == null ? 0 : reqVO.getSfdx();
        if (sfznx != 1 && sfdx != 1) {
            throw new IllegalArgumentException("站内信和短信至少选择一种发送方式");
        }
        if (sfznx == 1 && ObjectUtils.isEmpty(reqVO.getContent())) {
            throw new IllegalArgumentException("发送站内信时，消息内容不能为空");
        }
        if (sfdx == 1 && ObjectUtils.isEmpty(reqVO.getDxnr())) {
            throw new IllegalArgumentException("发送短信时，短信内容不能为空");
        }
    }

    private XxtxMessageResVO buildMessageResVO(XxtxMessageDO message) {
        XxtxMessageResVO respVO = BeanUtils.toBean(message, XxtxMessageResVO.class);
        if (message.getDeptIds() != null && !message.getDeptIds().isEmpty()) {
            List<DeptDO> depts = deptMapper.selectBatchIds(message.getDeptIds());
            List<String> deptNames = new ArrayList<>();
            depts.forEach(dept -> deptNames.add(dept.getName()));
            respVO.setDeptNames(deptNames);
        }
        if (message.getUserIds() != null && !message.getUserIds().isEmpty()) {
            List<AdminUserDO> users = userMapper.selectBatchIds(message.getUserIds());
            List<String> userNames = new ArrayList<>();
            users.forEach(user -> userNames.add(user.getNickname()));
            respVO.setUserNames(userNames);
        }
        List<XxtxMessageReceiverDO> receiverList = messageReceiverMapper.selectList(new LambdaQueryWrapperX<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getMessageId, message.getId())
                .eq(XxtxMessageReceiverDO::getDeleted, 0));
        respVO.setDxdsl(Integer.valueOf(1).equals(message.getSfdx()) ? receiverList.size() : 0);
        int dxcgsl = 0;
        int dxsbsl = 0;
        for (XxtxMessageReceiverDO receiver : receiverList) {
            sxDxjg(receiver);
            Integer dxzt = receiver.getDxzt();
            if (SmsSendStatusEnum.SUCCESS.getStatus() == dxzt) {
                dxcgsl++;
            } else if (SmsSendStatusEnum.FAILURE.getStatus() == dxzt) {
                dxsbsl++;
            }
        }
        respVO.setDxcgsl(dxcgsl);
        respVO.setDxsbsl(dxsbsl);
        return respVO;
    }

    private XxtxReceiverResVO buildReceiverResVO(XxtxMessageReceiverDO receiver, AdminUserDO userDO) {
        XxtxReceiverResVO respVO = BeanUtils.toBean(receiver, XxtxReceiverResVO.class);
        if (userDO != null) {
            respVO.setReceiverName(userDO.getNickname());
            respVO.setLxdh(userDO.getMobile());
        }
        sxDxjg(receiver);
        respVO.setDxzt(receiver.getDxzt());
        respVO.setDxbz(receiver.getDxbz());
        return respVO;
    }

    private void sxDxjg(XxtxMessageReceiverDO receiver) {
        if (receiver.getDxrzid() == null) {
            return;
        }
        SmsLogDO smsLogDO = smsLogService.getSmsLog(receiver.getDxrzid());
        if (smsLogDO == null) {
            return;
        }
        Integer dxzt = smsLogDO.getSendStatus();
        String dxbz = dxzt.equals(SmsSendStatusEnum.SUCCESS.getStatus()) ? "发送成功"
                : org.apache.commons.lang3.StringUtils.defaultIfBlank(smsLogDO.getApiSendMsg(), receiver.getDxbz());
        if (!Objects.equals(receiver.getDxzt(), dxzt)
                || !org.apache.commons.lang3.StringUtils.equals(receiver.getDxbz(), dxbz)) {
            messageReceiverMapper.updateById(XxtxMessageReceiverDO.builder()
                    .id(receiver.getId())
                    .dxzt(dxzt)
                    .dxbz(dxbz)
                    .build());
            receiver.setDxzt(dxzt);
            receiver.setDxbz(dxbz);
        }
    }

    private void tbDxjg(Long jsrId, Long dxrzid) {
        for (int i = 0; i < 5; i++) {
            SmsLogDO smsLogDO = smsLogService.getSmsLog(dxrzid);
            if (smsLogDO != null && SmsSendStatusEnum.INIT.getStatus() != smsLogDO.getSendStatus()) {
                messageReceiverMapper.updateById(XxtxMessageReceiverDO.builder()
                        .id(jsrId)
                        .dxzt(smsLogDO.getSendStatus())
                        .dxbz(smsLogDO.getSendStatus() == SmsSendStatusEnum.SUCCESS.getStatus()
                                ? "发送成功"
                                : org.apache.commons.lang3.StringUtils.defaultIfBlank(smsLogDO.getApiSendMsg(), "发送失败"))
                        .build());
                return;
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private Map<Long, AdminUserDO> hqYhMap(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<AdminUserDO> users = userMapper.selectBatchIds(userIds);
        Map<Long, AdminUserDO> userMap = new HashMap<>();
        users.forEach(user -> userMap.put(user.getId(), user));
        return userMap;
    }

    private PageResult<XxtxMessageReceiverDO> toPageResult(List<XxtxMessageReceiverDO> list, Integer pageNo, Integer pageSize) {
        List<XxtxMessageReceiverDO> sortList = new ArrayList<>(list);
        sortList.sort(Comparator.comparing(XxtxMessageReceiverDO::getId).reversed());
        // PAGE_SIZE_NONE (-1) means return all records without pagination
        if (pageSize == null || pageSize < 0) {
            return new PageResult<>(sortList, (long) sortList.size());
        }
        int fromIndex = Math.max((pageNo - 1) * pageSize, 0);
        if (fromIndex >= sortList.size()) {
            return new PageResult<>(Collections.emptyList(), (long) sortList.size());
        }
        int toIndex = Math.min(fromIndex + pageSize, sortList.size());
        return new PageResult<>(sortList.subList(fromIndex, toIndex), (long) sortList.size());
    }

}

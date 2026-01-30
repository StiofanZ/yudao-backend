package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSendReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageReceiverDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxMessageMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxMessageReceiverMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public Long createMessage(XxtxMessageSaveReqVO createReqVO) {
        XxtxMessageDO message = BeanUtils.toBean(createReqVO, XxtxMessageDO.class);
        // 设置发送者信息
        message.setSenderId(SecurityFrameworkUtils.getLoginUserId());
        message.setSenderName(SecurityFrameworkUtils.getLoginUserNickname());
        // 设置状态为草稿
        message.setStatus(0);
        messageMapper.insert(message);
        return message.getId();
    }

    @Override
    public void updateMessage(XxtxMessageSaveReqVO updateReqVO) {
        // 校验消息是否存在
        validateMessageExists(updateReqVO.getId());
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
                        .eq(AdminUserDO::getStatus, 1)); // 只发送给启用状态的用户
                users.forEach(user -> userIds.add(user.getId()));
            }
        }

        // 3.3 保存用户接收记录（实际消息接收者）
        for (Long userId : userIds) {
            // 检查是否已经存在接收记录（防止已读用户被重置为未读）
            Long count = messageReceiverMapper.selectCount(new LambdaQueryWrapper<XxtxMessageReceiverDO>()
                    .eq(XxtxMessageReceiverDO::getMessageId, message.getId())
                    .eq(XxtxMessageReceiverDO::getReceiverId, userId)
                    .eq(XxtxMessageReceiverDO::getReceiverType, 1));

            if (count == 0) {
                saveReceiver(message.getId(), 1, userId);
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
    public PageResult<XxtxMessageDO> getMessagePage(XxtxMessagePageReqVO reqVO) {
        return messageMapper.selectPage(reqVO, new LambdaQueryWrapperX<XxtxMessageDO>()
                .likeIfPresent(XxtxMessageDO::getTitle, reqVO.getTitle())
                .eqIfPresent(XxtxMessageDO::getMessageType, reqVO.getMessageType())
                .eqIfPresent(XxtxMessageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(XxtxMessageDO::getSenderId, reqVO.getSenderId())
                .betweenIfPresent(XxtxMessageDO::getSendTime, reqVO.getSendTimeBegin(), reqVO.getSendTimeEnd())
                .orderByDesc(XxtxMessageDO::getId));
    }

    @Override
    public XxtxMessageRespVO getMessageDetail(Long id) {
        XxtxMessageDO message = validateMessageExists(id);
        XxtxMessageRespVO respVO = BeanUtils.toBean(message, XxtxMessageRespVO.class);

        // 填充部门名称
        if (message.getDeptIds() != null && !message.getDeptIds().isEmpty()) {
            List<DeptDO> depts = deptMapper.selectBatchIds(message.getDeptIds());
            List<String> deptNames = new ArrayList<>();
            depts.forEach(dept -> deptNames.add(dept.getName()));
            respVO.setDeptNames(deptNames);
        }

        // 填充用户名称
        if (message.getUserIds() != null && !message.getUserIds().isEmpty()) {
            List<AdminUserDO> users = userMapper.selectBatchIds(message.getUserIds());
            List<String> userNames = new ArrayList<>();
            users.forEach(user -> userNames.add(user.getNickname()));
            respVO.setUserNames(userNames);
        }

        return respVO;
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
        return messageReceiverMapper.selectPage(reqVO, new LambdaQueryWrapperX<XxtxMessageReceiverDO>()
                .eq(XxtxMessageReceiverDO::getReceiverId, reqVO.getReceiverId())
                .eq(XxtxMessageReceiverDO::getDeleted, 0)
                .in(XxtxMessageReceiverDO::getReadStatus, ObjectUtils.isEmpty(reqVO.getReadStatus()) ? List.of(0, 1).toArray() : new Object[]{reqVO.getReadStatus()}));
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

    /**
     * 校验消息是否存在
     */
    private XxtxMessageDO validateMessageExists(Long id) {
        XxtxMessageDO message = messageMapper.selectById(id);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        return message;
    }

}

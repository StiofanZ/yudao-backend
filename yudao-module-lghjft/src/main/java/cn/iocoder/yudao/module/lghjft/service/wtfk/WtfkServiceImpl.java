package cn.iocoder.yudao.module.lghjft.service.wtfk;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkLogDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk.WtfkLogMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
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
        WtfkDO wtfk = new WtfkDO();
        BeanUtils.copyProperties(createReqVO, wtfk);

        // 2. 生成业务单号 (例如：FB + 时间戳)
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
    public void deleteWtfk(Long id) {
        // 校验存在
        validateWtfkExists(id);
        // 删除
        wtfkMapper.deleteById(id);
    }

    @Override
        public void deleteWtfkListByIds(List<Long> ids) {
        // 删除
        wtfkMapper.deleteByIds(ids);
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
        return wtfkMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WtfkLogDO> getWtfkLogList(Long feedbackId) {
        return wtfkLogMapper.selectListByFeedbackId(feedbackId);
    }

}
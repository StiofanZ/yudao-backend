package cn.iocoder.yudao.module.lghjft.service.workflow.jfhjsq;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhjsq.vo.GhWfJfhjsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.GhWfJfhjsqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhjsq.GhWfJfhjsqMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.HashMap;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Service
@Validated
public class GhWfJfhjsqServiceImpl implements GhWfJfhjsqService {

    public static final String PROCESS_KEY = "HJSQ";

    @Resource
    private GhWfJfhjsqMapper jfhjsqMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createGhWfJfhjsq(GhWfJfhjsqSaveReqVO createReqVO) {
        GhWfJfhjsqDO data = BeanUtils.toBean(createReqVO, GhWfJfhjsqDO.class);
        Long loginUserId = WebFrameworkUtils.getLoginUserId();
        AdminUserDO user = userService.getUser(getLoginUserId());
        if (user != null && (data.getJbr() == null || data.getJbr().isBlank())) {
            data.setJbr(user.getNickname());
        }
        if (data.getSqrq() == null) {
            data.setSqrq(LocalDate.now());
        }
        data.setUpdater(loginUserId == null ? null : String.valueOf(loginUserId));
        jfhjsqMapper.insert(data);

        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                loginUserId,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(data.getId()))
                        .setVariables(new HashMap<>())
        );
        GhWfJfhjsqDO updateObj = new GhWfJfhjsqDO();
        updateObj.setId(data.getId());
        updateObj.setLcslId(lcslId);
        jfhjsqMapper.updateById(updateObj);
        return data.getId();
    }

    @Override
    public GhWfJfhjsqDO getGhWfJfhjsq(Long id) {
        GhWfJfhjsqDO data = jfhjsqMapper.selectById(id);
        if (data == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_JFHJ_SQ_NOT_EXISTS);
        }
        return data;
    }

    @Override
    public PageResult<GhWfJfhjsqDO> getSelfPage(Long userId, @Valid GhWfJfhjsqAppPageReqVO pageReqVO) {
        return jfhjsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<GhWfJfhjsqDO>()
                .eq(GhWfJfhjsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(GhWfJfhjsqDO::getId));
    }
}

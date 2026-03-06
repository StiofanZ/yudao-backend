package cn.iocoder.yudao.module.lghjft.service.workflow.jfhzjnsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqmxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo.GhWfJfhzjnsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.GhWfJfhzjnsqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.GhWfJfhzjnsqmxMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Service
@Validated
public class GhWfJfhzjnsqServiceImpl implements GhWfJfhzjnsqService {

    public static final String PROCESS_KEY = "HZJNSQ";

    @Resource
    private GhWfJfhzjnsqMapper jfhzjnsqMapper;
    @Resource
    private GhWfJfhzjnsqmxMapper jfhzjnsqmxMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private AdminUserService userService;

    @Override
    public Long createGhWfJfhzjnsq(GhWfJfhzjnsqSaveReqVO createReqVO) {
        GhWfJfhzjnsqDO main = BeanUtils.toBean(createReqVO, GhWfJfhzjnsqDO.class);
        Long loginUserId = WebFrameworkUtils.getLoginUserId();
        AdminUserDO user = userService.getUser(getLoginUserId());
        if (user != null) {
            if (main.getJbr() == null || main.getJbr().isBlank()) {
                main.setJbr(user.getNickname());
            }
            if (main.getJbrdh() == null || main.getJbrdh().isBlank()) {
                main.setJbrdh(user.getMobile());
            }
        }
        if (main.getSqrq() == null) {
            main.setSqrq(LocalDate.now());
        }
        main.setUpdater(loginUserId == null ? null : String.valueOf(loginUserId));
        jfhzjnsqMapper.insert(main);

        if (CollUtil.isNotEmpty(createReqVO.getFzjgmxList())) {
            List<GhWfJfhzjnsqmxDO> detailList = BeanUtils.toBean(createReqVO.getFzjgmxList(), GhWfJfhzjnsqmxDO.class);
            detailList.forEach(item -> item.setJfhzjnsqId(main.getId()));
            jfhzjnsqmxMapper.insertBatch(detailList);
        }

        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                loginUserId,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(main.getId()))
                        .setVariables(new HashMap<>())
        );
        GhWfJfhzjnsqDO updateObj = new GhWfJfhzjnsqDO();
        updateObj.setId(main.getId());
        updateObj.setLcslId(lcslId);
        jfhzjnsqMapper.updateById(updateObj);
        return main.getId();
    }

    @Override
    public GhWfJfhzjnsqRespVO getDetail(Long id) {
        GhWfJfhzjnsqDO main = jfhzjnsqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_SQ_NOT_EXISTS);
        }
        List<GhWfJfhzjnsqmxDO> detailList = jfhzjnsqmxMapper.selectList(new LambdaQueryWrapperX<GhWfJfhzjnsqmxDO>()
                .eq(GhWfJfhzjnsqmxDO::getJfhzjnsqId, id)
                .orderByAsc(GhWfJfhzjnsqmxDO::getId));
        GhWfJfhzjnsqRespVO respVO = BeanUtils.toBean(main, GhWfJfhzjnsqRespVO.class);
        respVO.setFzjgmxList(BeanUtils.toBean(detailList, GhWfJfhzjnsqmxRespVO.class));
        return respVO;
    }

    @Override
    public PageResult<GhWfJfhzjnsqDO> getSelfPage(Long userId, GhWfJfhzjnsqAppPageReqVO pageReqVO) {
        return jfhzjnsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<GhWfJfhzjnsqDO>()
                .eq(GhWfJfhzjnsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(GhWfJfhzjnsqDO::getId));
    }
}

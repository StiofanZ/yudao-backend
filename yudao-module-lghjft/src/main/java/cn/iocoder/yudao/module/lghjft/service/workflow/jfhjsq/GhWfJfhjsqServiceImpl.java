package cn.iocoder.yudao.module.lghjft.service.workflow.jfhjsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhjsq.vo.GhWfJfhjsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.GhWfJfhjsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.HjsqfjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhjsq.GhWfJfhjsqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhjsq.JfhjsqfjMapper;
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
import java.util.List;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Service
@Validated
public class GhWfJfhjsqServiceImpl implements GhWfJfhjsqService {

    public static final String PROCESS_KEY = "HJSQ";

    @Resource
    private GhWfJfhjsqMapper jfhjsqMapper;

    // ====================== 附件 Mapper ======================
    @Resource
    private JfhjsqfjMapper jfhjsqfjMapper;

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

        // ====================== 【新增】插入附件 ======================
        saveAttachment(data.getId(), createReqVO.getFjList());

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

    // ====================== 【新增】保存附件 ======================
    private void saveAttachment(Long hjsqId, List<GhWfJfhjsqSaveReqVO.FjItem> fjList) {
        if (CollUtil.isEmpty(fjList)) {
            return;
        }
        List<HjsqfjDO> attachList = fjList.stream()
                .map(item -> HjsqfjDO.builder()
                        .jfhjsqId(hjsqId)
                        .fjlx(item.getFjlx())
                        .wjlj(item.getWjlj())
                        .wjmc(item.getWjmc())
                        .ywjmc(item.getYwjmc())
                        .build())
                .toList();
        jfhjsqfjMapper.insertBatch(attachList);
    }

    // ====================== 【完善】查询附件列表 ======================
    private List<HjsqfjDO> getAttachmentList(Long hjsqId) {
        return jfhjsqfjMapper.selectList(new LambdaQueryWrapperX<HjsqfjDO>()
                .eq(HjsqfjDO::getJfhjsqId, hjsqId)
                .orderByAsc(HjsqfjDO::getId));
    }

    // ====================== 【完善】详情接口（带附件） ======================
    @Override
    public GhWfJfhjsqRespVO getGhWfJfhjsq(Long id) {
        GhWfJfhjsqDO data = jfhjsqMapper.selectById(id);
        if (data == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_JFHJ_SQ_NOT_EXISTS);
        }

        // 查询附件
        List<HjsqfjDO> fjList = getAttachmentList(id);

        // 转换VO
        GhWfJfhjsqRespVO respVO = BeanUtils.toBean(data, GhWfJfhjsqRespVO.class);

        // 手动赋值附件 → 无类型冲突
        List<GhWfJfhjsqRespVO.FjItem> respFjList = fjList.stream().map(fj -> {
            GhWfJfhjsqRespVO.FjItem item = new GhWfJfhjsqRespVO.FjItem();
            item.setFjlx(fj.getFjlx());
            item.setWjlj(fj.getWjlj());
            item.setWjmc(fj.getWjmc());
            item.setYwjmc(fj.getYwjmc());
            return item;
        }).toList();
        respVO.setFjList(respFjList);

        return respVO;
    }

//    @Override
//    public GhWfJfhjsqDO getGhWfJfhjsq(Long id) {
//        GhWfJfhjsqDO data = jfhjsqMapper.selectById(id);
//        if (data == null) {
//            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_JFHJ_SQ_NOT_EXISTS);
//        }
//        return data;
//    }

    @Override
    public PageResult<GhWfJfhjsqDO> getSelfPage(Long userId, @Valid GhWfJfhjsqAppPageReqVO pageReqVO) {
        return jfhjsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<GhWfJfhjsqDO>()
                .eq(GhWfJfhjsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(GhWfJfhjsqDO::getId));
    }
}
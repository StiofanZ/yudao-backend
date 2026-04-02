package cn.iocoder.yudao.module.lghjft.service.workflow.jfhzjnsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo.GhWfJfhzjnsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.HzsqFjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.GhWfJfhzjnsqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.GhWfJfhzjnsqmxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq.JfhzfjMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    private JfhzfjMapper jfhzfjMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private AdminUserService userService;

    // ====================== 【修复】添加事务 ======================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createGhWfJfhzjnsq(GhWfJfhzjnsqSaveReqVO createReqVO) {
        // 1. 获取汇总单位的登记序号 → 查 gh_hj 获得 deptid
        String mainDeptId = jfhzjnsqmxMapper.selectDeptIdByDjxh(createReqVO.getDjxh());

        // 2. 汇总单位工会ID不能为空
        if (mainDeptId == null || mainDeptId.isBlank()) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_MAIN_DEPTID_EMPTY);
        }

        // 3. 遍历下属单位，逐个校验工会是否一致
        for (var mx : createReqVO.getFzjgmxList()) {
            // 子单位登记序号
            String subDjxh = mx.getDjxh();

            // 根据子单位登记序号查工会ID
            String subDeptId = jfhzjnsqmxMapper.selectDeptIdByDjxh(subDjxh);

            // 子单位工会ID不能为空
            if (subDeptId == null || subDeptId.isBlank()) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_SUB_DEPTID_EMPTY, mx.getDwmc());
            }

            // ====================== 关键判断：工会不一致则拦截 ======================
            if (!Objects.equals(mainDeptId, subDeptId)) {
                throw ServiceExceptionUtil.exception(
                        ErrorCodeConstants.WF_HZJF_NOT_SAME_DEPT,
                        mx.getDwmc(), subDeptId, mainDeptId
                );
            }
        }
        // 1. 判断汇总单位是否欠缴费
        if (!checkUnitHasNoArrears(createReqVO.getDjxh())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_UNIT_ARREARS, createReqVO.getDwmc());
        }

        // 2. 判断所有下属单位是否欠缴费
        for (var mx : createReqVO.getFzjgmxList()) {
            if (!checkUnitHasNoArrears(mx.getDjxh())) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_SUB_UNIT_ARREARS, mx.getDwmc());
            }
        }

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

        // 保存明细
        if (CollUtil.isNotEmpty(createReqVO.getFzjgmxList())) {
            List<GhWfJfhzjnsqmxDO> detailList = BeanUtils.toBean(createReqVO.getFzjgmxList(), GhWfJfhzjnsqmxDO.class);
            detailList.forEach(item -> item.setJfhzjnsqId(main.getId()));
            jfhzjnsqmxMapper.insertBatch(detailList);
        }

        // ========== 保存附件 ==========
        saveHzsqAttachment(main.getId(), createReqVO.getFjList());

        // 创建流程
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

    /**
     * 【附件功能】保存汇总缴纳附件
     */
    private void saveHzsqAttachment(Long hzJnId, List<GhWfJfhzjnsqSaveReqVO.FjItem> fjList) {
        if (CollUtil.isEmpty(fjList)) {
            return;
        }
        List<HzsqFjDO> attachList = fjList.stream()
                .map(item -> HzsqFjDO.builder()
                        .jfhzjnsqId(hzJnId)
                        .wjmc(item.getWjmc())
                        .ywjmc(item.getYwjmc())
                        .wjlj(item.getWjlj())
                        .fjlx(item.getFjlx())
                        .build())
                .toList();
        jfhzfjMapper.insertBatch(attachList);
    }

    /**
     * 【附件功能】查询附件列表
     */
    private List<HzsqFjDO> getHzsqAttachmentList(Long hzJnId) {
        return jfhzfjMapper.selectList(new LambdaQueryWrapperX<HzsqFjDO>()
                .eq(HzsqFjDO::getJfhzjnsqId, hzJnId)
                .orderByAsc(HzsqFjDO::getId));
    }

    // ====================== 【修复】详情：类型完全匹配 ======================
    @Override
    public GhWfJfhzjnsqResVO getDetail(Long id) {
        GhWfJfhzjnsqDO main = jfhzjnsqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_SQ_NOT_EXISTS);
        }
        // 查询明细
        List<GhWfJfhzjnsqmxDO> detailList = jfhzjnsqmxMapper.selectList(new LambdaQueryWrapperX<GhWfJfhzjnsqmxDO>()
                .eq(GhWfJfhzjnsqmxDO::getJfhzjnsqId, id)
                .orderByAsc(GhWfJfhzjnsqmxDO::getId));
        // 查询附件
        List<HzsqFjDO> fjList = getHzsqAttachmentList(id);

        GhWfJfhzjnsqResVO respVO = BeanUtils.toBean(main, GhWfJfhzjnsqResVO.class);
        respVO.setFzjgmxList(BeanUtils.toBean(detailList, GhWfJfhzjnsqmxResVO.class));

        // ====================== 【修复】手动构造 → 100% 无类型冲突 ======================
        List<GhWfJfhzjnsqResVO.FjItem> respFjList = fjList.stream().map(fjDO -> {
            GhWfJfhzjnsqResVO.FjItem item = new GhWfJfhzjnsqResVO.FjItem();
            item.setWjmc(fjDO.getWjmc());
            item.setYwjmc(fjDO.getYwjmc());
            item.setWjlj(fjDO.getWjlj());
            item.setFjlx(fjDO.getFjlx());
            return item;
        }).toList();
        respVO.setFjList(respFjList);

        return respVO;
    }

    @Override
    public PageResult<GhWfJfhzjnsqDO> getSelfPage(Long userId, GhWfJfhzjnsqAppPageReqVO pageReqVO) {
        return jfhzjnsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<GhWfJfhzjnsqDO>()
                .eq(GhWfJfhzjnsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(GhWfJfhzjnsqDO::getId));
    }

    // 欠缴判断
    private boolean checkUnitHasNoArrears(String djxh) {
        Integer count = jfhzjnsqmxMapper.selectArrearsCountByDjxh(djxh);
        return count == null || count <= 0;
    }
}
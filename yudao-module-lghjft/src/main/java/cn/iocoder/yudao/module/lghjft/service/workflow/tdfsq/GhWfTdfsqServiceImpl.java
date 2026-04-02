package cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqKtfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq.vo.GhWfTdfsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqFjDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.tdfsq.GhWfTdfsqFjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.tdfsq.GhWfTdfsqMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.OPERATION_NOT_PERMITTED;

@Service
public class GhWfTdfsqServiceImpl implements GhWfTdfsqService {

    public static final String PROCESS_KEY = "TDFSQ";

    @Resource
    private GhWfTdfsqMapper tdfsqMapper;
    @Resource
    private GhWfTdfsqFjMapper tdfsqFjMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(@Valid GhWfTdfsqSaveReqVO req) {
        AdminUserDO user = userService.getUser(getLoginUserId());
        GhWfTdfsqDO main = BeanUtils.toBean(req, GhWfTdfsqDO.class);
        if (user != null) {
            if (main.getJbr() == null || main.getJbr().isBlank()) {
                main.setJbr(user.getNickname());
            }
            if (main.getLxdh() == null || main.getLxdh().isBlank()) {
                main.setLxdh(user.getMobile());
            }
        }
        if (main.getSqrq() == null) {
            main.setSqrq(LocalDate.now());
        }
        main.setCreator(String.valueOf(WebFrameworkUtils.getLoginUserId()));

        // ====================== 保存退费类型 ======================
        main.setSqtflxDm(req.getSqtflxDm());

        tdfsqMapper.insert(main);
        Long mainId = main.getId();

        // 保存附件
        if (CollUtil.isNotEmpty(req.getFjList())) {
            List<GhWfTdfsqFjDO> list = req.getFjList().stream()
                    .map(item -> GhWfTdfsqFjDO.builder()
                            .tdfsqId(mainId)
                            .fjlx(item.getFjlx())
                            .wjlj(item.getWjlj())
                            .build())
                    .toList();
            tdfsqFjMapper.insertBatch(list);
        }

        // ====================== 保存退费明细 ======================
        if (CollUtil.isNotEmpty(req.getMxList())) {
            List<GhWfTdfsqmxDO> mxList = req.getMxList().stream().map(item ->
                    GhWfTdfsqmxDO.builder()
                            .tdfsqId(mainId)
                            .spuuid(item.getSpuuid())
                            .rkje(item.getRkje())
                            .tfsqJe(item.getTfsqJe())
                            .shxydm(item.getShxydm())
                            .nsrmc(item.getNsrmc())
                            .skssqq(item.getSkssqq())
                            .skssqz(item.getSkssqz())
                            .build()
            ).toList();
            tdfsqMapper.insertBatchMx(mxList);
        }

        // 发起流程
        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(mainId))
                        .setVariables(new HashMap<>())
        );
        GhWfTdfsqDO updateObj = new GhWfTdfsqDO();
        updateObj.setId(mainId);
        updateObj.setLcslId(lcslId);
        tdfsqMapper.updateById(updateObj);
        return mainId;
    }

//    @Override
//    public GhWfTdfsqResVO getDetail(Long id) {
//        GhWfTdfsqDO main = tdfsqMapper.selectById(id);
//        if (main == null) {
//            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
//        }
//        List<GhWfTdfsqFjDO> fjList = tdfsqFjMapper.selectList(new LambdaQueryWrapperX<GhWfTdfsqFjDO>()
//                .eq(GhWfTdfsqFjDO::getTdfsqId, id)
//                .orderByAsc(GhWfTdfsqFjDO::getId));
//        GhWfTdfsqResVO respVO = BeanUtils.toBean(main, GhWfTdfsqResVO.class);
//        respVO.setFjList(fjList.stream().map(item -> {
//            GhWfTdfsqResVO.FjItem fjItem = new GhWfTdfsqResVO.FjItem();
//            fjItem.setFjlx(item.getFjlx());
//            fjItem.setWjlj(item.getWjlj());
//            fjItem.setWjmc(resolveFileName(item.getWjlj()));
//            fjItem.setYwjmc(resolveFileName(item.getWjlj()));
//            return fjItem;
//        }).toList());
//        return respVO;
//    }
@Override
public GhWfTdfsqResVO getDetailWithOwnerCheck(Long id) {
    GhWfTdfsqDO main = tdfsqMapper.selectById(id);
    if (main == null) {
        throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
    }
    Long loginUserId = getLoginUserId();
    if (!Objects.equals(main.getCreator(), loginUserId == null ? null : String.valueOf(loginUserId))) {
        throw exception(OPERATION_NOT_PERMITTED);
    }
    return getDetail(id);
}

    @Override
    public GhWfTdfsqResVO getDetail(Long id) {
    // 1. 查询主表
    GhWfTdfsqDO main = tdfsqMapper.selectById(id);
    if (main == null) {
        throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
    }
    // 2. 查询附件
    List<GhWfTdfsqFjDO> fjList = tdfsqFjMapper.selectList(new LambdaQueryWrapperX<GhWfTdfsqFjDO>()
            .eq(GhWfTdfsqFjDO::getTdfsqId, id)
            .orderByAsc(GhWfTdfsqFjDO::getId));

    // ====================== 3. 查询退费明细（从数据库取） ======================
    List<GhWfTdfsqmxDO> mxDOList = tdfsqMapper.selectMxListByTdfsqId(id);

    // ====================== 4. DO → VO 转换 ======================
        List<GhWfTdfsqResVO.TdfsqMxItem> mxItemList = mxDOList.stream().map(mx -> {
            GhWfTdfsqResVO.TdfsqMxItem item = new GhWfTdfsqResVO.TdfsqMxItem();
        item.setId(mx.getId());
        item.setSpuuid(mx.getSpuuid());
        item.setTfsqJe(mx.getTfsqJe());
        item.setShxydm(mx.getShxydm());
        item.setNsrmc(mx.getNsrmc());
        item.setSkssqq(mx.getSkssqq());
        item.setSkssqz(mx.getSkssqz());
        return item;
    }).toList();

    // 5. 主表 → VO
        GhWfTdfsqResVO respVO = BeanUtils.toBean(main, GhWfTdfsqResVO.class);

    // 6. 设置附件
    respVO.setFjList(fjList.stream().map(item -> {
        GhWfTdfsqResVO.FjItem fjItem = new GhWfTdfsqResVO.FjItem();
        fjItem.setFjlx(item.getFjlx());
        fjItem.setWjlj(item.getWjlj());
        fjItem.setWjmc(resolveFileName(item.getWjlj()));
        fjItem.setYwjmc(resolveFileName(item.getWjlj()));
        return fjItem;
    }).toList());

    // ====================== 7. 设置明细到 VO ======================
    respVO.setMxList(mxItemList);

    return respVO;
}
    @Override
    public PageResult<GhWfTdfsqDO> getSelfPage(Long userId, GhWfTdfsqAppPageReqVO pageReqVO) {
        return tdfsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<GhWfTdfsqDO>()
                .eq(GhWfTdfsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(GhWfTdfsqDO::getId));
    }

    private String resolveFileName(String wjlj) {
        if (wjlj == null || wjlj.isBlank()) {
            return "";
        }
        String cleanPath = wjlj.split("\\?")[0];
        int index = cleanPath.lastIndexOf('/');
        return index >= 0 ? cleanPath.substring(index + 1) : cleanPath;
    }

    // ====================== 查询可退费明细 ======================
    @Override
    public List<GhWfTdfsqKtfxxResVO> getKtfxxList(String djxh, Integer sqtflxDm) {
        // 1. 获取当前日期
        LocalDate now = LocalDate.now();

        // 2. 定义 所属期起(ssq)、所属期止(esq)
        LocalDate ssq = null, esq = null;

        // 3. 根据退费类型（sqtflxDm）来自动算时间范围
        if (sqtflxDm == 1) { // 当期 → 查当月
            ssq = now.withDayOfMonth(1);                // 当月 1 号
            esq = now.withDayOfMonth(now.lengthOfMonth()); // 当月最后一天
        } else { // 往期 → 查【近 1 年】
            ssq = now.minusYears(6);  // 当前日期往前推 1 年
            esq = now;                // 截止到今天
        }

        // 4. 调用 mapper 查询该登记序号下、该时间段内的可退费明细
        return tdfsqMapper.getKtfxxList(djxh, ssq, esq);
    }
}

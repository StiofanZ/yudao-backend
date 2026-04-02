package cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.dwdbsq.vo.WfDbsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqfjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqfjMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.WF_DBSQ_NOT_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.WF_DBSQ_NOT_STATUS;

/**
 * 工会隶属关系调拨申请 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WfDbsqServiceImpl implements WfDbsqService {
    public static final String PROCESS_KEY = "DWDBSQ";

    @Resource
    private WfDbsqMapper wfDbsqMapper;
    @Resource
    private WfDbsqfjMapper wfDbsqfjMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private DeptService deptService;
    @Resource
    private JcxxService jcxxService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(@Valid WfDbsqSaveReqVO req) {

        // 重复提交校验
        LambdaQueryWrapper<WfDbsqDO> checkQuery = new LambdaQueryWrapper<>();
        checkQuery.eq(WfDbsqDO::getShxydm, req.getShxydm());
        checkQuery.eq(WfDbsqDO::getSqrq, LocalDate.now());
        if (wfDbsqMapper.selectCount(checkQuery) > 0) {
            throw exception(WF_DBSQ_NOT_EXISTS);
        }
        // 判断汇总单位是否欠缴费
        if (!checkUnitHasNoArrears(req.getDjxh())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_UNIT_ARREARS, req.getDwmc());
        }
        //工会状态校验
        DeptDO oldDept = deptService.getDept(req.getOldDeptId());
        DeptDO newDept = deptService.getDept(req.getNewDeptId());
        if (oldDept.getStatus() == 1 || newDept.getStatus() == 1) {
            throw exception(WF_DBSQ_NOT_STATUS);
        }
        AdminUserDO user = userService.getUser(getLoginUserId());
        WfDbsqDO main = BeanUtils.toBean(req, WfDbsqDO.class);

        // 先插入主表，生成 ID
        if (user != null) {
            if (main.getYzghjbr() == null || main.getYzghjbr().isBlank()) {
                main.setYzghjbr(user.getNickname());
            }
            if (main.getLxdh() == null || main.getLxdh().isBlank()) {
                main.setLxdh(user.getMobile());
            }
        }
        if (main.getSqrq() == null) {
            main.setSqrq(LocalDate.now());
            main.setYzghgzrq(LocalDate.now());
        }
        main.setCreator(String.valueOf(WebFrameworkUtils.getLoginUserId()));
        wfDbsqMapper.insert(main); //  这里执行完，main.getId() 才有值！

        // 【关键】构建流程变量，存入调拨所需参数
        Map<String, Object> variables = new HashMap<>();
        variables.put("newDeptId", req.getNewDeptId());           // 目标工会ID
        variables.put("djxh", req.getDjxh());                   // 登记序号
        variables.put("hyghbz", String.valueOf(req.getHyghbz())); // 行业工会标志
        variables.put("businessId", main.getId());              // 业务ID

        // 再创建流程（带上变量）
        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(main.getId()))
                        .setVariables(variables)  // ← 传入变量，不是空HashMap
        );

        // ：最后更新 lcslId
        WfDbsqDO updateObj = new WfDbsqDO();
        updateObj.setId(main.getId());
        updateObj.setLcslId(lcslId);
        wfDbsqMapper.updateById(updateObj);
        // 插入附件
        if (CollUtil.isNotEmpty(req.getFjList())) {
            Collection<WfDbsqfjDO> list = req.getFjList().stream()
                    .map(item -> WfDbsqfjDO.builder()
                            .dbsqId(main.getId())
                            .fjlx(item.getFjlx())
                            .wjlj(item.getWjlj())
                            .wjmc(item.getWjmc())
                            .ywjmc(item.getYwjmc())
                            .build())
                    .toList();
            wfDbsqfjMapper.insertBatch(list);
        }

        return main.getId();
    }

    @Override
    public WfDbsqResVO getDetail(Long id) {
        WfDbsqDO main = wfDbsqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
        }
        List<WfDbsqfjDO> fjList = wfDbsqfjMapper.selectList(new LambdaQueryWrapperX<WfDbsqfjDO>()
                .eq(WfDbsqfjDO::getDbsqId, id)
                .orderByAsc(WfDbsqfjDO::getId));

        WfDbsqResVO respVO = BeanUtils.toBean(main, WfDbsqResVO.class);
        respVO.setFjList(fjList.stream().map(item -> {
            WfDbsqResVO.FjItem fjItem = new WfDbsqResVO.FjItem();
            fjItem.setFjlx(item.getFjlx());
            fjItem.setWjlj(item.getWjlj());
            fjItem.setWjmc(resolveFileName(item.getWjlj()));
            fjItem.setYwjmc(resolveFileName(item.getWjlj()));
            return fjItem;
        }).toList());
        return respVO;
    }

    @Override
    public PageResult<WfDbsqDO> getSelfPage(Long userId, WfDbsqAppPageReqVO pageReqVO) {
        return wfDbsqMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<WfDbsqDO>()
                .eq(WfDbsqDO::getCreator, userId == null ? null : String.valueOf(userId))
                .orderByDesc(WfDbsqDO::getId));
    }

    private String resolveFileName(String wjlj) {
        if (wjlj == null || wjlj.isBlank()) {
            return "";
        }
        String cleanPath = wjlj.split("\\?")[0];
        int index = cleanPath.lastIndexOf('/');
        return index >= 0 ? cleanPath.substring(index + 1) : cleanPath;
    }

    // 欠缴判断
    private boolean checkUnitHasNoArrears(String djxh) {
        Integer count = wfDbsqMapper.selectArrearsCountByDjxh(djxh);
        return count == null || count <= 0;
    }
}
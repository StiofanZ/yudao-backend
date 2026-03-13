package cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqfjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq.WfDbsqfjMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
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
        // ======================
        //工会状态校验
        // ======================
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
        }
        main.setCreator(String.valueOf(WebFrameworkUtils.getLoginUserId()));
        wfDbsqMapper.insert(main); //  这里执行完，main.getId() 才有值！

        // 再创建流程
        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(main.getId()))
                        .setVariables(new HashMap<>())
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
    public WfDbsqRespVO getDetail(Long id) {
        WfDbsqDO main = wfDbsqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
        }
        List<WfDbsqfjDO> fjList = wfDbsqfjMapper.selectList(new LambdaQueryWrapperX<WfDbsqfjDO>()
                .eq(WfDbsqfjDO::getDbsqId, id)
                .orderByAsc(WfDbsqfjDO::getId));

        WfDbsqRespVO respVO = BeanUtils.toBean(main, WfDbsqRespVO.class);
        respVO.setFjList(fjList.stream().map(item -> {
            WfDbsqRespVO.FjItem fjItem = new WfDbsqRespVO.FjItem();
            fjItem.setFjlx(item.getFjlx());
            fjItem.setWjlj(item.getWjlj());
            fjItem.setWjmc(resolveFileName(item.getWjlj()));
            fjItem.setYwjmc(resolveFileName(item.getWjlj()));
            return fjItem;
        }).toList());
        return respVO;
    }

    @Override
    public PageResult<WfDbsqDO> getSelfPage(Long userId, WfDbsqPageReqVO pageReqVO) {
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

}
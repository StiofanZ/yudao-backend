package cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs;

import cn.hutool.core.collection.CollUtil;
//import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzPageReqVO;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzmxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo.WfHzAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqhzjf.WfHzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqhzjf.WfHzmxMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 工会经费汇总缴纳申请表（主表） Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class WfHzServiceImpl implements WfHzService {
    public static final String PROCESS_KEY = "HZJNSQ";
    @Resource
    private WfHzmxMapper wfHzmxMapper;
    @Resource
    private WfHzMapper wfHzMapper;
    @Resource
    private BpmProcessInstanceApi bpmProcessInstanceApi;
    @Resource
    private AdminUserService userService;
    @Override
    public Long createWfHz(WfHzSaveReqVO createReqVO) {
        // 1. 插入主表数据
        WfHzDO wfHz = BeanUtils.toBean(createReqVO, WfHzDO.class);
        Long loginUserId = WebFrameworkUtils.getLoginUserId();

        //2.获取当前用户信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        String nickname = user.getNickname();
        String mobile = user.getMobile();
        wfHz.setJbrdh(nickname);
        wfHz.setJbrdh(mobile);
        wfHz.setUpdater(String.valueOf(loginUserId));
        wfHz.setSqrq(LocalDate.now());//        申请时间
        wfHz.setCreateTime(LocalDateTime.now()); // 补充创建时间（如果DO里有该字段）
        wfHz.setUpdateTime(LocalDateTime.now()); // 补充更新时间（如果DO里有该字段）
        wfHzMapper.insert(wfHz);
        Long mainId = wfHz.getId(); // 获取主表自增ID

        // 2. 批量插入明细表数据（核心：补充的明细表存储逻辑）
        List<WfHzmxSaveReqVO> detailVOList = createReqVO.getDetailList();
        if (!CollectionUtils.isEmpty(detailVOList)) {
            // 把明细表VO转换成DO，并绑定主表ID、补充公共字段
            List<WfHzmxDO> detailDOList = Lists.newArrayList();
            for (WfHzmxSaveReqVO detailVO : detailVOList) {
                WfHzmxDO detailDO = BeanUtils.toBean(detailVO, WfHzmxDO.class);
                // 绑定主表外键（关键：关联主表）
                detailDO.setHzId(mainId);
                // 补充公共字段（创建人、创建/更新时间）
                detailDO.setCreateTime(LocalDateTime.now());
                detailDO.setUpdateTime(LocalDateTime.now());
                detailDOList.add(detailDO);
            }
            // 批量插入明细表（避免循环单插，提升性能）
            wfHzmxMapper.insertBatch(detailDOList);
        }

        // 3. 启动流程
        String processInstanceId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(wfHz.getId()))
                        .setVariables(new HashMap<>())
        );

        // 4. 更新流程实例ID
        wfHzMapper.updateById(new WfHzDO()
                .setId(wfHz.getId())
                .setProcessInstanceId(processInstanceId));
        return wfHz.getId();
    }


    public WfHzRespVO getDetail(Long id) {
        // 1. 查询主表数据，校验存在性
        WfHzDO mainDO = wfHzMapper.selectById(id);
        if (mainDO == null) {
            // 建议替换为你项目中统一的异常码（比如 ErrorCodeConstants.WF_HZ_NOT_EXISTS）
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_HZJF_SQ_NOT_EXISTS);
        }

        // 2按主表ID（hzId）查询所有关联的明细
        List<WfHzmxDO> mxDOList = wfHzmxMapper.selectList(
                new LambdaQueryWrapper<WfHzmxDO>()
                        .eq(WfHzmxDO::getHzId, id) // 关联字段：明细表hzId = 主表id
        );

        // 3.1 主表DO → 主表VO
        WfHzRespVO respVO = BeanUtils.toBean(mainDO, WfHzRespVO.class);

        // 3.2 明细表DO列表 → 明细表VO列表
        List<WfHzmxRespVO> mxVOList = new ArrayList<>(); // 初始化空列表，避免null
        if (mxDOList != null && !mxDOList.isEmpty()) {
            for (WfHzmxDO mxDO : mxDOList) {
                // 逐个转换
                WfHzmxRespVO mxVO = BeanUtils.toBean(mxDO, WfHzmxRespVO.class);
                mxVOList.add(mxVO);
            }
        }
        // 3.3 把明细表VO列表设置到主VO的detailList字段（你的核心嵌套字段）
        respVO.setDetailList(mxVOList);

        return respVO;
    }

    @Override
    public PageResult<WfHzDO> getSelfPage(Long userId, WfHzAppPageReqVO pageReqVO) {
        return wfHzMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<WfHzDO>()
                .eq(WfHzDO::getCreator, userId) //  核心：强制只查当前用户创建的数据
                .orderByDesc(WfHzDO::getId)); // 按创建时间倒序
    }

}
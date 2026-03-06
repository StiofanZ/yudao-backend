package cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq.vo.GhWfTdfsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqFjDO;
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

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

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
        tdfsqMapper.insert(main);

        if (CollUtil.isNotEmpty(req.getFjList())) {
            List<GhWfTdfsqFjDO> list = req.getFjList().stream()
                    .map(item -> GhWfTdfsqFjDO.builder()
                            .tdfsqId(main.getId())
                            .fjlx(item.getFjlx())
                            .wjlj(item.getWjlj())
                            .build())
                    .toList();
            tdfsqFjMapper.insertBatch(list);
        }

        String lcslId = bpmProcessInstanceApi.createProcessInstance(
                WebFrameworkUtils.getLoginUserId(),
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)
                        .setBusinessKey(String.valueOf(main.getId()))
                        .setVariables(new HashMap<>())
        );
        GhWfTdfsqDO updateObj = new GhWfTdfsqDO();
        updateObj.setId(main.getId());
        updateObj.setLcslId(lcslId);
        tdfsqMapper.updateById(updateObj);
        return main.getId();
    }

    @Override
    public GhWfTdfsqRespVO getDetail(Long id) {
        GhWfTdfsqDO main = tdfsqMapper.selectById(id);
        if (main == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WF_TDF_SQ_NOT_EXISTS);
        }
        List<GhWfTdfsqFjDO> fjList = tdfsqFjMapper.selectList(new LambdaQueryWrapperX<GhWfTdfsqFjDO>()
                .eq(GhWfTdfsqFjDO::getTdfsqId, id)
                .orderByAsc(GhWfTdfsqFjDO::getId));
        GhWfTdfsqRespVO respVO = BeanUtils.toBean(main, GhWfTdfsqRespVO.class);
        respVO.setFjList(fjList.stream().map(item -> {
            GhWfTdfsqRespVO.FjItem fjItem = new GhWfTdfsqRespVO.FjItem();
            fjItem.setFjlx(item.getFjlx());
            fjItem.setWjlj(item.getWjlj());
            fjItem.setWjmc(resolveFileName(item.getWjlj()));
            fjItem.setYwjmc(resolveFileName(item.getWjlj()));
            return fjItem;
        }).toList());
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
}

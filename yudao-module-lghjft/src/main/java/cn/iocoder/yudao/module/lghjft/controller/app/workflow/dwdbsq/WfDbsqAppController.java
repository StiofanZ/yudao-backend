package cn.iocoder.yudao.module.lghjft.controller.app.workflow.dwdbsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.dwdbsq.vo.WfDbsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq.WfDbsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


@Tag(name = "用户 app - 工会隶属关系调拨申请")
@RestController
@RequestMapping("/lghjft/wf-dbsq")
@Validated
public class WfDbsqAppController {

    @Resource
    private WfDbsqService wfDbsqService;

    @PostMapping("/create")
    @Operation(summary = "创建工会隶属关系调拨申请")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createWfDbsq(@Valid @RequestBody WfDbsqSaveReqVO createReqVO) {
        return success(wfDbsqService.create(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得工会隶属关系调拨申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<WfDbsqResVO> getWfDbsq(@RequestParam("id") Long id) {
        WfDbsqResVO wfDbsq = wfDbsqService.getDetail(id);
        return success(BeanUtils.toBean(wfDbsq, WfDbsqResVO.class));
    }


    @GetMapping("/page")
    @Operation(summary = "获得单位调拨申请分页（我的）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<WfDbsqResVO>> getGhWfTdfsqPage(@Valid WfDbsqAppPageReqVO pageReqVO) {
        PageResult<WfDbsqDO> pageResult = wfDbsqService.getSelfPage(getLoginUserId(), pageReqVO);
        return success(BeanUtils.toBean(pageResult, WfDbsqResVO.class));
    }


}

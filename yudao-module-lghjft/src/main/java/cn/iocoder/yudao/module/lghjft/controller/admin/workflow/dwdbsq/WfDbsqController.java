package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq;

import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.workflow.dwdbsq.WfDbsqService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 工会隶属关系调拨申请")
@RestController
@RequestMapping("/lghjft/wf-dbsq")
@Validated
public class WfDbsqController {

    @Resource
    private WfDbsqService wfDbsqService;

    @PostMapping("/create")
    @Operation(summary = "创建工会隶属关系调拨申请")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-dbsq:create')")
    public CommonResult<Long> createWfDbsq(@Valid @RequestBody WfDbsqSaveReqVO createReqVO) {
        return success(wfDbsqService.create(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得工会隶属关系调拨申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:wf-dbsq:query')")
    public CommonResult<WfDbsqRespVO> getWfDbsq(@RequestParam("id") Long id) {
        WfDbsqRespVO wfDbsq = wfDbsqService.getDetail(id);
        return success(BeanUtils.toBean(wfDbsq, WfDbsqRespVO.class));
    }





}
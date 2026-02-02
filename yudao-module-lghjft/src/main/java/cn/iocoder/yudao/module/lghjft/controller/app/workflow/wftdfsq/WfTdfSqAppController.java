package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo.WfTdfSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.wftdfsq.WfTdfSqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 工会经费退抵费申请")
@RestController
@RequestMapping("/lghjft/workflow/wftdfsq")
@Validated
public class WfTdfSqAppController {

    @Resource
    private WfTdfSqService wfTdfSqService;

    @GetMapping("/get")
    @Operation(summary = "获取退还申请详情")
    public CommonResult<WfTdfSqRespVO> get(@RequestParam("id") Long id) {
        return success(wfTdfSqService.getDetail(id));
    }

    @PostMapping("/create")
    @Operation(summary = "提交退还申请")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-wftdfsq:create')")
    public CommonResult<Long> create(@Valid @RequestBody WfTdfSqSaveReqVO req) {
        return success(wfTdfSqService.create(req));
    }


}
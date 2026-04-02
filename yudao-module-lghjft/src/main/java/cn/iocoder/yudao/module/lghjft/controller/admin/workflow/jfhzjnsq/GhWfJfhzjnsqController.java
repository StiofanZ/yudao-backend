package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.workflow.jfhzjnsq.GhWfJfhzjnsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 汇总缴纳申请")
@RestController
@RequestMapping("/lghjft/workflow/jfhzjnsq")
@Validated
public class GhWfJfhzjnsqController {

    @Resource
    private GhWfJfhzjnsqService jfhzjnsqService;

    @PostMapping("/create")
    @Operation(summary = "创建汇总缴纳申请")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-jfhzjnsq:create')")
    public CommonResult<Long> createGhWfJfhzjnsq(@Valid @RequestBody GhWfJfhzjnsqSaveReqVO createReqVO) {
        return success(jfhzjnsqService.createGhWfJfhzjnsq(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得汇总缴纳申请详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-jfhzjnsq:query')")
    public CommonResult<GhWfJfhzjnsqResVO> getGhWfJfhzjnsq(@RequestParam("id") Long id) {
        return success(jfhzjnsqService.getDetail(id));
    }
}

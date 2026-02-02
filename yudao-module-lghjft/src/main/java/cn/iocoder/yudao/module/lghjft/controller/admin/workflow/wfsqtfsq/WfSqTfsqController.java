package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo.WfSqTfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo.WfSqTfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo.WfSqTfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wfsqtfsq.WfSqTfsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 申请-退费申请")
@RestController
@RequestMapping("/lghjft/workflow/wfsqtfsq")
@Validated
public class WfSqTfsqController {

    @Resource
    private WfSqTfsqService wfSqTfsqService;

    @GetMapping("/get")
    @Operation(summary = "获得申请退费信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<WfSqTfsqRespVO> getWfSqTfsq(@RequestParam("id") Long id) {
        return success(wfSqTfsqService.getWfSqTfsq(id));
    }

    @PostMapping("/save")
        @Operation(summary = "申请退费")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-wfsqtfsq:create')")
    public CommonResult<Long> save(@RequestBody List<WfSqTfsqSaveReqVO> list) {
        return success(wfSqTfsqService.save(list));
    }

    @GetMapping("/ktfxx")
    @Operation(summary = "获得可退费信息列表")
    @Parameter(name = "djxh", description = "登记序号", required = true, example = "1001")
    @Parameter(name = "skssqq", description = "税款所属期起", required = false, example = "2023-01-01")
    @Parameter(name = "skssqz", description = "税款所属期止", required = false, example = "2023-01-31")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-wfsqtfsq:query')")
    public CommonResult<List<WfSqTfsqKtfxxRespVO>> getKtfxxList(@RequestParam("djxh") String djxh,
                                                                @RequestParam(value = "skssqq", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate skssqq,
                                                                @RequestParam(value = "skssqz", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate skssqz) {
        return success(wfSqTfsqService.getKtfxxList(djxh, skssqq, skssqz));
    }

}

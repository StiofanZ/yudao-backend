package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq.GhWfTdfsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 退抵费申请")
@RestController
@RequestMapping("/lghjft/workflow/tdfsq")
@Validated
public class GhWfTdfsqController {

    @Resource
    private GhWfTdfsqService tdfsqService;

    @GetMapping("/get")
    @Operation(summary = "获得退抵费申请详情")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-tdfsq:query')")
    public CommonResult<GhWfTdfsqRespVO> get(@RequestParam("id") Long id) {
        return success(tdfsqService.getDetail(id));
    }

    @PostMapping("/create")
    @Operation(summary = "提交退抵费申请")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-tdfsq:create')")
    public CommonResult<Long> create(@Valid @RequestBody GhWfTdfsqSaveReqVO req) {
        return success(tdfsqService.create(req));
    }
    // ====================== 新增：查询可退费明细（当期/往期） ======================
    @PostMapping("/getKtfxxList")
    @Operation(summary = "查询可退费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-tdfsq:query')")
    public CommonResult<List<GhWfTdfsqKtfxxRespVO>> getKtfxxList(@RequestParam String djxh, @RequestParam Integer sqtflxDm) {
        return success(tdfsqService.getKtfxxList(djxh, sqtflxDm));
    }
}

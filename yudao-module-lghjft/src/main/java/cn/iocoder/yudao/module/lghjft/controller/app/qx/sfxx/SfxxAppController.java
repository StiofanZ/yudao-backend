package cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx.vo.SfxxAppSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxAppController {
    @Resource
    private GhQxSfxxService ghQxSfxxService;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:create')")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxAppSaveReqVO createReqVO) {
        return success(ghQxSfxxService.createSfxx(createReqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        ghQxSfxxService.auditSfxx(id, status);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "解绑身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> unbindSfxx(@RequestParam("id") Long id, @RequestParam("jbyy") String jbyy) {
        ghQxSfxxService.unbindSfxx(id, jbyy);
        return success(true);
    }
}

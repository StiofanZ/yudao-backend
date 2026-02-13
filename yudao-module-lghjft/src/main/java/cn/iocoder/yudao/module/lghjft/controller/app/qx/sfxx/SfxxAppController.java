package cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx.vo.SfxxAppSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxAppController {
    @Resource
    private GhQxSfxxService ghQxSfxxService;
    @Resource
    private GhQxDlzhService ghQxDlzhService;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxAppSaveReqVO createReqVO) {
        return success(ghQxSfxxService.createSfxx(createReqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "身份授权")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id,
                                           @RequestParam("status") Integer status,
                                           @RequestParam(value = "jjyy", required = false) String jjyy) {
        ghQxSfxxService.auditSfxx(id, status, jjyy);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "解绑身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> unbindSfxx(@RequestParam("id") Long id, @RequestParam("jbyy") String jbyy) {
        ghQxSfxxService.unbindSfxx(id, jbyy);
        return success(true);
    }

    @GetMapping("/get-kbdsfxx")
    @Operation(summary = "获得可绑定身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<KbdsfxxRespVO>> getKbdsfxx() {
        GhQxDlzhDO ghQxDlzhDO = ghQxDlzhService.getDlzh(getLoginUserId());
        if (ghQxDlzhDO == null || StrUtil.isBlank(ghQxDlzhDO.getLxdh())) {
            return success(Collections.emptyList());
        }
        return success(ghQxSfxxService.getKbdsfxxList(ghQxDlzhDO.getLxdh()));
    }

}

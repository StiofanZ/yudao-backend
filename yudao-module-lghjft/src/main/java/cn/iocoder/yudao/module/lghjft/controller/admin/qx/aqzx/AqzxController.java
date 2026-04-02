package cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxProfileResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxSendSmsReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdateNoticeMobileReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdatePasswordReqVO;
import cn.iocoder.yudao.module.lghjft.service.qx.aqzx.AqzxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 安全中心")
@RestController
@RequestMapping("/lghjft/qx/aqzx")
@Validated
public class AqzxController {

    @Resource
    private AqzxService aqzxService;

    @GetMapping("/profile")
    @Operation(summary = "获得当前账号安全信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AqzxProfileResVO> getProfile() {
        return success(aqzxService.getCurrentProfile());
    }

    @PostMapping("/send-sms-code")
    @Operation(summary = "发送短信验证码")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> sendSmsCode(@Valid @RequestBody AqzxSendSmsReqVO reqVO) {
        aqzxService.sendSmsCode(reqVO.getMobile());
        return success(true);
    }

    @PutMapping("/update-notice-mobile")
    @Operation(summary = "更新通知手机号")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updateNoticeMobile(@Valid @RequestBody AqzxUpdateNoticeMobileReqVO reqVO) {
        aqzxService.updateNoticeMobile(reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "更新密码")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updatePassword(@Valid @RequestBody AqzxUpdatePasswordReqVO reqVO) {
        aqzxService.updatePassword(reqVO);
        return success(true);
    }
}

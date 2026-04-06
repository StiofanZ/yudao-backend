package cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx.vo.SfxxAppReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.qx.sfxx.vo.SfxxAppSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.SfxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.SystemUserSfxxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxAppController {
    @Resource
    private SystemUserSfxxService systemUserSfxxService;
    @Resource
    private JcxxService jcxxService;
    @Resource
    private SfxxService sfxxService;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxAppSaveReqVO createReqVO) {
        return success(systemUserSfxxService.createSfxx(createReqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "身份授权")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id,
                                           @RequestParam("status") Integer status,
                                           @RequestParam(value = "jjyy", required = false) String jjyy) {
        systemUserSfxxService.auditSfxxWithOwnerCheck(id, status, jjyy);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "解绑身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> unbindSfxx(@RequestParam("id") Long id, @RequestParam("jbyy") String jbyy) {
        systemUserSfxxService.unbindSfxxWithOwnerCheck(id, jbyy);
        return success(true);
    }

    @PostMapping("/get-kbdsfxx")
    @Operation(summary = "获得可绑定身份信息")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<KbdsfxxResVO>> getKbdsfxx(@Valid @RequestBody SfxxAppReqVO reqVO) {
        List<KbdsfxxResVO> kbdsfxxResVOList = sfxxService.getKbdsfxx(reqVO);
        kbdsfxxResVOList.removeIf(kbdsfxxResVO -> !(kbdsfxxResVO.getDwmc().contains(reqVO.getKeywords())
                && kbdsfxxResVO.getShxydm().contains(reqVO.getKeywords())
                && kbdsfxxResVO.getLxdh().contains(reqVO.getKeywords())
                && kbdsfxxResVO.getRyxm().contains(reqVO.getKeywords())));
        return success(kbdsfxxResVOList);
    }

}

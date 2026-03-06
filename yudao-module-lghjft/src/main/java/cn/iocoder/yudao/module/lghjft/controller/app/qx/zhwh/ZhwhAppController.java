package cn.iocoder.yudao.module.lghjft.controller.app.qx.zhwh;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import cn.iocoder.yudao.module.lghjft.service.qx.zhwh.ZhwhService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 账户维护")
@RestController
@RequestMapping("/lghjft/qx/zhwh/app")
@Validated
public class ZhwhAppController {

    @Resource
    private ZhwhService zhwhService;

    @PostMapping("/create")
    @Operation(summary = "创建账户维护申请")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createZhwh(@Valid @RequestBody ZhwhCreateReqVO reqVO) {
        reqVO.setDlzhId(SecurityFrameworkUtils.getLoginUserId());
        return success(zhwhService.createZhwh(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新账户维护申请")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updateZhwh(@Valid @RequestBody ZhwhUpdateReqVO reqVO) {
        reqVO.setDlzhId(SecurityFrameworkUtils.getLoginUserId());
        zhwhService.updateZhwh(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "撤回账户维护申请")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> cancelZhwh(@RequestParam("id") Long id) {
        zhwhService.cancelZhwh(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得账户维护申请")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<ZhwhRespVO> getZhwh(@RequestParam("id") Long id) {
        GhQxZhwhDO zhwh = zhwhService.getZhwh(id);
        return success(BeanUtils.toBean(zhwh, ZhwhRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得账户维护申请分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<ZhwhRespVO>> getZhwhPage(@Valid ZhwhPageReqVO reqVO) {
        reqVO.setDlzhId(SecurityFrameworkUtils.getLoginUserId());
        PageResult<GhQxZhwhDO> pageResult = zhwhService.getZhwhPage(reqVO);
        return success(BeanUtils.toBean(pageResult, ZhwhRespVO.class));
    }
}

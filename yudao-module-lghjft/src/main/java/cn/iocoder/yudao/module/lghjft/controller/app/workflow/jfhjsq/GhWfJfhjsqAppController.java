package cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhjsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhjsq.vo.GhWfJfhjsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.GhWfJfhjsqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.jfhjsq.GhWfJfhjsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 app - 经费缓缴申请")
@RestController
@RequestMapping("/lghjft/workflow/jfhjsq")
@Validated
public class GhWfJfhjsqAppController {

    @Resource
    private GhWfJfhjsqService jfhjsqService;

    @PostMapping("/create")
    @Operation(summary = "创建经费缓缴申请")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createGhWfJfhjsq(@Valid @RequestBody GhWfJfhjsqSaveReqVO createReqVO) {
        return success(jfhjsqService.createGhWfJfhjsq(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费缓缴申请详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<GhWfJfhjsqResVO> getGhWfJfhjsq(@RequestParam("id") Long id) {
        return success(jfhjsqService.getGhWfJfhjsqWithOwnerCheck(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费缓缴申请分页（我的）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<GhWfJfhjsqResVO>> getGhWfJfhjsqPage(@Valid GhWfJfhjsqAppPageReqVO pageReqVO) {
        PageResult<GhWfJfhjsqDO> pageResult = jfhjsqService.getSelfPage(getLoginUserId(), pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhWfJfhjsqResVO.class));
    }
}

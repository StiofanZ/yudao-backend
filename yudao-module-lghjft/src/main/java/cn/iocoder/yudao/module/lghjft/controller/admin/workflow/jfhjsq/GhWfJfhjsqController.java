package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqSaveReqVO;
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

@Tag(name = "管理后台 - 经费缓缴申请")
@RestController
@RequestMapping("/lghjft/workflow/jfhjsq")
@Validated
public class GhWfJfhjsqController {

    @Resource
    private GhWfJfhjsqService jfhjsqService;

    @PostMapping("/create")
    @Operation(summary = "创建经费缓缴申请")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-jfhjsq:create')")
    public CommonResult<Long> createGhWfJfhjsq(@Valid @RequestBody GhWfJfhjsqSaveReqVO createReqVO) {
        return success(jfhjsqService.createGhWfJfhjsq(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费缓缴申请详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-jfhjsq:query')")
    public CommonResult<GhWfJfhjsqRespVO> getGhWfJfhjsq(@RequestParam("id") Long id) {
        GhWfJfhjsqDO data = jfhjsqService.getGhWfJfhjsq(id);
        return success(BeanUtils.toBean(data, GhWfJfhjsqRespVO.class));
    }
}

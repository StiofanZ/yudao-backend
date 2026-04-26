package cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo.GhWfJfhzjnsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhzjnsq.vo.GhWfJfhzjnsqSaveAppReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqDO;
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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 app - 汇总缴纳申请")
@RestController
@RequestMapping("/lghjft/workflow/jfhzjnsq")
@Validated
public class GhWfJfhzjnsqAppController {

    @Resource
    private GhWfJfhzjnsqService jfhzjnsqService;

    @PostMapping("/create")
    @Operation(summary = "创建汇总缴纳申请")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createGhWfJfhzjnsq(@Valid @RequestBody GhWfJfhzjnsqSaveAppReqVO createReqVO) {
        return success(jfhzjnsqService.createGhWfJfhzjnsq(BeanUtils.toBean(createReqVO, GhWfJfhzjnsqSaveReqVO.class)));
    }

    @GetMapping("/get")
    @Operation(summary = "获得汇总缴纳申请详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<GhWfJfhzjnsqResVO> getGhWfJfhzjnsq(@RequestParam("id") Long id) {
        return success(jfhzjnsqService.getDetail(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得汇总缴纳申请分页（我的）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<GhWfJfhzjnsqResVO>> getGhWfJfhzjnsqPage(@Valid GhWfJfhzjnsqAppPageReqVO pageReqVO) {
        PageResult<GhWfJfhzjnsqDO> pageResult = jfhzjnsqService.getSelfPage(getLoginUserId(), pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhWfJfhzjnsqResVO.class));
    }
}

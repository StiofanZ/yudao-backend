package cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.tdfsq.vo.GhWfTdfsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.tdfsq.GhWfTdfsqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "APP - 退抵费申请")
@RestController
@RequestMapping("/lghjft/workflow/tdfsq")
@Validated
public class GhWfTdfsqAppController {

    @Resource
    private GhWfTdfsqService tdfsqService;

    @GetMapping("/get")
    @Operation(summary = "获得退抵费申请详情")
    public CommonResult<GhWfTdfsqRespVO> get(@RequestParam("id") Long id) {
        return success(tdfsqService.getDetail(id));
    }

    @PostMapping("/create")
    @Operation(summary = "提交退抵费申请")
    public CommonResult<Long> create(@Valid @RequestBody GhWfTdfsqSaveReqVO req) {
        return success(tdfsqService.create(req));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退抵费申请分页（我的）")
    public CommonResult<PageResult<GhWfTdfsqRespVO>> getGhWfTdfsqPage(@Valid GhWfTdfsqAppPageReqVO pageReqVO) {
        PageResult<GhWfTdfsqDO> pageResult = tdfsqService.getSelfPage(getLoginUserId(), pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhWfTdfsqRespVO.class));
    }
}

package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo.WfTdfSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wftdfsq.vo.WfTdfSqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq.WfTdfSqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wftdfsq.WfTdfSqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "App - 工会经费退抵费申请")
@RestController
@RequestMapping("/lghjft/workflow/wftdfsq")
@Validated
public class WfTdfSqAppController {

    @Resource
    private WfTdfSqService wfTdfSqService;

    @GetMapping("/get")
    @Operation(summary = "获取退还申请详情")
    public CommonResult<WfTdfSqRespVO> get(@RequestParam("id") Long id) {
        return success(wfTdfSqService.getDetail(id));
    }

    @PostMapping("/create")
    @Operation(summary = "提交退还申请")
    @PreAuthorize("@ss.hasPermission('lghjft:workflow-wftdfsq:create')")
    public CommonResult<Long> create(@Valid @RequestBody WfTdfSqSaveReqVO req) {
        return success(wfTdfSqService.create(req));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工会经费退抵费申请分页（我的）")
    public CommonResult<PageResult<WfTdfSqRespVO>> getWfTdfSqPage(@Valid WfTdfSqAppPageReqVO pageReqVO) {
        // 1. 获取当前用户 ID
        Long loginUserId = getLoginUserId();

        // 2. 查询 DO 分页
        PageResult<WfTdfSqDO> pageResult = wfTdfSqService.getSelfPage(loginUserId, pageReqVO);

        // 3. 转换为 VO 分页返回
        return success(BeanUtils.toBean(pageResult, WfTdfSqRespVO.class));
    }


}
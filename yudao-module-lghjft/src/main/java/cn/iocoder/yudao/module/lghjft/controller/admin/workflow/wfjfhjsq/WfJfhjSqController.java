package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.WfJfhjSqService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
@Tag(name = "管理后台 - 工会经费缓缴申请")
@RestController
@RequestMapping("/lghjft/wf-jfhj-sq")
@Validated
public class WfJfhjSqController {

    @Resource
    private WfJfhjSqService wfJfhjSqService;

    @PostMapping("/create")
    @Operation(summary = "创建工会经费缓缴申请")
//    @PreAuthorize("@ss.hasPermission('lghjft:wf-jfhj-sq:create')")
    public CommonResult<Long> createWfJfhjSq(@Valid @RequestBody WfJfhjSqSaveReqVO createReqVO) {
        return success(wfJfhjSqService.createWfJfhjSq(createReqVO));
    }



    @GetMapping("/get")
    @Operation(summary = "获得工会经费缓缴申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('lghjft:wf-jfhj-sq:query')")
    public CommonResult<WfJfhjSqRespVO> getWfJfhjSq(@RequestParam("id") Long id) {
        WfJfhjSqDO wfJfhjSq = wfJfhjSqService.getWfJfhjSq(id);
        return success(BeanUtils.toBean(wfJfhjSq, WfJfhjSqRespVO.class));
    }


}
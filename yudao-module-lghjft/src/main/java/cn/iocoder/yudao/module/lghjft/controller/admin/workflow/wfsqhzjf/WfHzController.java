package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf;

import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs.WfHzService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 工会经费汇总缴纳申请表（主表）")
@RestController
@RequestMapping("/lghjft/wf-hz")
@Validated
public class WfHzController {

    @Resource
    private WfHzService wfHzService;

    @PostMapping("/create")
    @Operation(summary = "创建工会经费汇总缴纳申请表（主表）")
//    @PreAuthorize("@ss.hasPermission('lghjft:w  f-hz:create')")
    public CommonResult<Long> createWfHz(@Valid @RequestBody WfHzSaveReqVO createReqVO) {
        return success(wfHzService.createWfHz(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得工会经费汇总缴纳申请表（主表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('lghjft:wf-hz:query')")
    public CommonResult<WfHzRespVO> getWfHz(@RequestParam("id") Long id) {
        WfHzRespVO wfHz = wfHzService.getDetail(id);
        return success(BeanUtils.toBean(wfHz, WfHzRespVO.class));
    }



}
package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;
import cn.iocoder.yudao.module.lghjft.service.jfcl.GhHkxxYhbfhzJkjl.IGhHkxxYhbfhzJkjlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

//import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;
//import cn.iocoder.yudao.module.lghjft.service.jfcl.GhHkxxYhbfhzJkjl.IGhHkxxYhbfhzJkjlService;

@Tag(name = "管理后台 - 银行拨付汇总监控记录")
@RestController
@RequestMapping("/lghjft/jfcl/jkjl")
@Validated
public class GhHkxxYhbfhzJkjlController {

    @Resource
    private IGhHkxxYhbfhzJkjlService ghHkxxYhbfhzJkjlService;

    @GetMapping("/page")
    @Operation(summary = "查询银行拨付汇总监控记录列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:query')")
    public CommonResult<PageResult<GhHkxxYhbfhzJkjlResVO>> page(@Valid GhHkxxYhbfhzJkjlPageReqVO pageReqVO) {
        PageResult<GhHkxxYhbfhzJkjl> pageResult = ghHkxxYhbfhzJkjlService.selectGhHkxxYhbfhzJkjlPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHkxxYhbfhzJkjlResVO.class));
    }

    @PostMapping("/export")
    @Operation(summary = "导出银行拨付汇总监控记录列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(HttpServletResponse response, @Valid GhHkxxYhbfhzJkjlPageReqVO pageReqVO) throws IOException {
        // 模板写法：直接从分页方法拿list，无需单独写list查询
        List<GhHkxxYhbfhzJkjl> list = ghHkxxYhbfhzJkjlService.selectGhHkxxYhbfhzJkjlPage(pageReqVO).getList();
        ExcelUtils.write(response, "银行拨付汇总监控记录.xls", "数据", GhHkxxYhbfhzJkjlResVO.class,
                BeanUtils.toBean(list, GhHkxxYhbfhzJkjlResVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取银行拨付汇总监控记录详细信息")
    @Parameter(name = "yhbfhzJkjlId", description = "ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:query')")
    public CommonResult<GhHkxxYhbfhzJkjlResVO> getInfo(@RequestParam("yhbfhzJkjlId") Long yhbfhzJkjlId) {
        GhHkxxYhbfhzJkjl info = ghHkxxYhbfhzJkjlService.selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
        return success(BeanUtils.toBean(info, GhHkxxYhbfhzJkjlResVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新增银行拨付汇总监控记录")
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:create')")
    public CommonResult<Long> add(@Valid @RequestBody GhHkxxYhbfhzJkjlSaveReqVO createReqVO) {
        return success(ghHkxxYhbfhzJkjlService.insertGhHkxxYhbfhzJkjl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "修改银行拨付汇总监控记录")
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> edit(@Valid @RequestBody GhHkxxYhbfhzJkjlSaveReqVO updateReqVO) {
        ghHkxxYhbfhzJkjlService.updateGhHkxxYhbfhzJkjl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付汇总监控记录")
    @Parameter(name = "ids", description = "编号数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jkjl:delete')")
    @ApiAccessLog(operateType = DELETE)
    public CommonResult<Boolean> remove(@RequestParam("ids") List<Long> ids) {
        ghHkxxYhbfhzJkjlService.deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(ids);
        return success(true);
    }

}
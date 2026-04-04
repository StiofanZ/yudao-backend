package cn.iocoder.yudao.module.lghjft.controller.app.jfcl.yhbfhzjkjl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhzjkjl.YhbfhzjkjlDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhzjkjl.YhbfhzjkjlService;
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

@Tag(name = "app     - 银行拨付汇总监控记录")
@RestController
@RequestMapping("/gh/jkjl")
@Validated
public class YhbfhzjkjlAppController {

    @Resource
    private YhbfhzjkjlService yhbfhzjkjlService;

    @GetMapping("/page")
    @Operation(summary = "查询银行拨付汇总监控记录列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:list')")
    public CommonResult<PageResult<YhbfhzjkjlResVO>> page(@Valid YhbfhzjkjlPageReqVO pageReqVO) {
        PageResult<YhbfhzjkjlDO> pageResult = yhbfhzjkjlService.selectYhbfhzJkjlPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfhzjkjlResVO.class));
    }

    @PostMapping("/export")
    @Operation(summary = "导出银行拨付汇总监控记录列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(HttpServletResponse response, @Valid YhbfhzjkjlPageReqVO pageReqVO) throws IOException {
        // 模板写法：直接从分页方法拿list，无需单独写list查询
        List<YhbfhzjkjlDO> list = yhbfhzjkjlService.selectYhbfhzJkjlPage(pageReqVO).getList();
        ExcelUtils.write(response, "银行拨付汇总监控记录.xls", "数据", YhbfhzjkjlResVO.class,
                BeanUtils.toBean(list, YhbfhzjkjlResVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取银行拨付汇总监控记录详细信息")
    @Parameter(name = "yhbfhzJkjlId", description = "ID", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:query')")
    public CommonResult<YhbfhzjkjlResVO> getInfo(@RequestParam("yhbfhzJkjlId") Long yhbfhzJkjlId) {
        YhbfhzjkjlDO info = yhbfhzjkjlService.selectYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
        return success(BeanUtils.toBean(info, YhbfhzjkjlResVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新增银行拨付汇总监控记录")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:create')")
    public CommonResult<Long> add(@Valid @RequestBody YhbfhzjkjlSaveReqVO createReqVO) {
        return success(yhbfhzjkjlService.insertYhbfhzJkjl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "修改银行拨付汇总监控记录")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:edit')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> edit(@Valid @RequestBody YhbfhzjkjlSaveReqVO updateReqVO) {
        yhbfhzjkjlService.updateYhbfhzJkjl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付汇总监控记录")
    @Parameter(name = "ids", description = "编号数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfhzjkjl:remove')")
    @ApiAccessLog(operateType = DELETE)
    public CommonResult<Boolean> remove(@RequestParam("ids") List<Long> ids) {
        yhbfhzjkjlService.deleteYhbfhzJkjlByYhbfhzJkjlIds(ids);
        return success(true);
    }

}

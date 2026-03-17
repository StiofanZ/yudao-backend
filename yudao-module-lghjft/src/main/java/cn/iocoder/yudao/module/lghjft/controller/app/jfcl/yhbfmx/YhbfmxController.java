package cn.iocoder.yudao.module.lghjft.controller.app.jfcl.yhbfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfmx.YhbfmxService;
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

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "app - 银行拨付明细")
@RestController
@RequestMapping("/gh/yhbfmx")
@Validated
public class YhbfmxController {

    @Resource
    private YhbfmxService yhbfmxService;

    @PostMapping("/create")
    @Operation(summary = "创建银行拨付明细")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:create')")
    public CommonResult<Integer> createYhbfmx(@Valid @RequestBody YhbfmxSaveReqVO createReqVO) {
        return success(yhbfmxService.createYhbfmx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新银行拨付明细")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:update')")
    public CommonResult<Boolean> updateYhbfmx(@Valid @RequestBody YhbfmxSaveReqVO updateReqVO) {
        yhbfmxService.updateYhbfmx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除银行拨付明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:delete')")
    public CommonResult<Boolean> deleteYhbfmx(@RequestParam("id") Integer id) {
        yhbfmxService.deleteYhbfmx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除银行拨付明细")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:delete')")
    public CommonResult<Boolean> deleteYhbfmxList(@RequestParam("ids") List<Integer> ids) {
        yhbfmxService.deleteYhbfmxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得银行拨付明细")
    @Parameter(name = "bfid", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:query')")
    public CommonResult<YhbfmxRespVO> getYhbfmx(@RequestParam("bfid") Integer bfid) {
        YhbfmxDO yhbfmx = yhbfmxService.getYhbfmx(bfid);
        return success(BeanUtils.toBean(yhbfmx, YhbfmxRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得银行拨付明细分页")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:query')")
    public CommonResult<PageResult<YhbfmxRespVO>> getYhbfmxPage(@Valid YhbfmxPageReqVO pageReqVO) {
        PageResult<YhbfmxDO> pageResult = yhbfmxService.getYhbfmxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfmxRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出银行拨付明细 Excel")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportYhbfmxExcel(@Valid YhbfmxPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<YhbfmxDO> list = yhbfmxService.getYhbfmxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "银行拨付明细.xls", "数据", YhbfmxRespVO.class,
                BeanUtils.toBean(list, YhbfmxRespVO.class));
    }


    /**
     * 结算文件生成银行拨付数据
     */
    @PostMapping("/js")
    @Operation(summary = "结算文件生成银行拨付数据")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:js')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> js(@RequestBody YhbfmxSaveReqVO reqVO) {
        yhbfmxService.updateGhyhbfmxJs(reqVO);
        return success(true);
    }

    /**
     * 补结算文件生成银行拨付数据
     */
    @PostMapping("/bjs")
    @Operation(summary = "补结算文件生成银行拨付数据")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:bjs')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> bjs(@RequestBody YhbfmxSaveReqVO reqVO) {
        yhbfmxService.updateGhyhbfmxBjs(reqVO);
        return success(true);
    }

    /**
     * 失败退回重拨数据生成银行拨付数据
     */
    @PostMapping("/sbthcb")
    @Operation(summary = "失败退回重拨数据生成银行拨付数据")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:sbthcb')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> sbthcb(@RequestBody YhbfmxSaveReqVO reqVO) {
        yhbfmxService.updateGhyhbfmxSbthcb(reqVO);
        return success(true);
    }

    /**
     * 生成银行拨付汇总
     */
    @PostMapping("/yhbfhz")
    @Operation(summary = "生成银行拨付汇总")
    @PreAuthorize("@ss.hasPermission('gh:yhbfmx:yhbfhz')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateGhHkxxYhbfhz(@RequestBody YhbfmxSaveReqVO reqVO) {
        yhbfmxService.updateGhHkxxYhbfhz(reqVO);
        return success(true);
    }

}
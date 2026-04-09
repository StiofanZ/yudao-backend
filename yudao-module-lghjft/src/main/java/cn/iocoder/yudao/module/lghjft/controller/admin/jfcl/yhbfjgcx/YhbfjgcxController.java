package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx.YhbfjgcxService;
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
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 银行拨付结果查询")
@RestController
@RequestMapping("/lghjft/jfcl/yhbfjgcx")
@Validated
public class YhbfjgcxController {

    @Resource
    private YhbfjgcxService yhbfjgcxService;

    /**
     * 查询银行拨付结果查询列表
     */
    @GetMapping("/page")
    @Operation(summary = "查询银行拨付结果查询分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:query')")
    public CommonResult<PageResult<YhbfjgcxResVO>> getYhbfjgcxPage(@Valid YhbfjgcxPageReqVO pageReqVO) {
        PageResult<YhbfjgcxDO> pageResult = yhbfjgcxService.selectYhbfjgcxList(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfjgcxResVO.class));
    }

    /**
     * 导出银行拨付结果查询列表
     */
    @GetMapping("/export")
    @Operation(summary = "导出银行拨付结果查询列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void export(HttpServletResponse response, @Valid YhbfjgcxPageReqVO pageReqVO) throws IOException {
        List<YhbfjgcxDO> list = yhbfjgcxService.selectYhbfjgcxList(pageReqVO).getList();
        ExcelUtils.write(response, "银行拨付结果查询数据.xls", "数据",
                YhbfjgcxResVO.class, BeanUtils.toBean(list, YhbfjgcxResVO.class));
    }

    /**
     * 获取银行拨付结果查询详细信息
     */
    @GetMapping("/{bfpch}")
    @Operation(summary = "获取银行拨付结果查询详细信息")
    @Parameter(name = "bfpch", description = "银行拨付结果查询主键", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:query')")
    public CommonResult<YhbfjgcxResVO> getInfo(@PathVariable("bfpch") String bfpch) {
        YhbfjgcxDO yhbfjgcxDO = yhbfjgcxService.selectYhbfjgcxByBfpch(bfpch);
        return success(BeanUtils.toBean(yhbfjgcxDO, YhbfjgcxResVO.class));
    }

    /**
     * 新增银行拨付结果查询
     */
    @PostMapping
    @Operation(summary = "新增银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:create')")
    public CommonResult<Boolean> add(@Valid @RequestBody YhbfjgcxSaveReqVO saveReqVO) {
        YhbfjgcxDO yhbfjgcxDO = BeanUtils.toBean(saveReqVO, YhbfjgcxDO.class);
        yhbfjgcxService.insertYhbfjgcx(yhbfjgcxDO);
        return success(true);
    }

    /**
     * 修改银行拨付结果查询
     */
    @PutMapping
    @Operation(summary = "修改银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:update')")
    public CommonResult<Boolean> edit(@Valid @RequestBody YhbfjgcxSaveReqVO saveReqVO) {
        YhbfjgcxDO yhbfjgcxDO = BeanUtils.toBean(saveReqVO, YhbfjgcxDO.class);
        yhbfjgcxService.updateYhbfjgcx(yhbfjgcxDO);
        return success(true);
    }

    /**
     * 删除银行拨付结果查询
     */
    @DeleteMapping("/{bfpchs}")
    @Operation(summary = "删除银行拨付结果查询")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-yhbfjgcx:delete')")
    public CommonResult<Boolean> remove(@PathVariable String[] bfpchs) {
        yhbfjgcxService.deleteYhbfjgcxByBfpchs(bfpchs);
        return success(true);
    }

}

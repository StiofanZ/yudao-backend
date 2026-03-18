package cn.iocoder.yudao.module.lghjft.controller.app.jfcl.yhbfhz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.hbfhz.YhbfhzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "app - 银行拨付汇总")
@RestController
@RequestMapping("/jfcl/yhbfhz")
@Validated
public class YhbfhzAppController {

    @Resource
    private YhbfhzService yhbfhzService;

    /**
     * 查询银行拨付汇总列表
     */
    @GetMapping("/page")
    @Operation(summary = "查询银行拨付汇总分页")
//@PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:query')")
    public CommonResult<PageResult<YhbfhzRespVO>> getYhbfhzPage(@Valid YhbfhzPageReqVO pageReqVO) {
        PageResult<YhbfhzDO> pageResult = yhbfhzService.selectGhHkxxYhbfhzList(pageReqVO);
        return success(BeanUtils.toBean(pageResult, YhbfhzRespVO.class));
    }

    /**
     * 导出银行拨付汇总列表
     */
    @PostMapping("/export")
    @Operation(summary = "导出银行拨付汇总列表")
// @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void export(HttpServletResponse response, @Valid YhbfhzPageReqVO pageReqVO) throws IOException {
        // 直接用 pageReqVO 传入，不用转 DO！
        List<YhbfhzDO> list = yhbfhzService.selectGhHkxxYhbfhzList(pageReqVO).getList();
        ExcelUtils.write(response, "银行拨付汇总数据.xls", "数据",
                YhbfhzRespVO.class, BeanUtils.toBean(list, YhbfhzRespVO.class));
    }

    /**
     * 获取银行拨付汇总详细信息
     */
    @GetMapping("/{bfhzid}")
    @Operation(summary = "获取银行拨付汇总详细信息")
    @Parameter(name = "bfhzid", description = "银行拨付汇总主键", required = true)
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:query')")
    public CommonResult<YhbfhzRespVO> getInfo(@PathVariable("bfhzid") String bfhzid) {
        YhbfhzDO yhbfhzDO = yhbfhzService.selectGhHkxxYhbfhzByBfhzid(bfhzid);
        return success(BeanUtils.toBean(yhbfhzDO, YhbfhzRespVO.class));
    }

    /**
     * 新增银行拨付汇总
     */
    @PostMapping
    @Operation(summary = "新增银行拨付汇总")
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:create')")
    public CommonResult<Boolean> add(@Valid @RequestBody YhbfhzSaveReqVO saveReqVO) {
        YhbfhzDO yhbfhzDO = BeanUtils.toBean(saveReqVO, YhbfhzDO.class);
        yhbfhzService.insertGhHkxxYhbfhz(yhbfhzDO);
        return success(true);
    }

    /**
     * 修改银行拨付汇总
     */
    @PutMapping
    @Operation(summary = "修改银行拨付汇总")
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:update')")
    public CommonResult<Boolean> edit(@Valid @RequestBody YhbfhzSaveReqVO saveReqVO) {
        YhbfhzDO yhbfhzDO = BeanUtils.toBean(saveReqVO, YhbfhzDO.class);
        yhbfhzService.updateGhHkxxYhbfhz(yhbfhzDO);
        return success(true);
    }

    /**
     * 删除银行拨付汇总
     */
    @DeleteMapping("/{bfhzids}")
    @Operation(summary = "删除银行拨付汇总")
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:delete')")
    public CommonResult<Boolean> remove(@PathVariable String[] bfhzids) {
        yhbfhzService.deleteGhHkxxYhbfhzByBfhzids(bfhzids);
        return success(true);
    }

    /**
     * 提交银行审核
     */
    @PostMapping("/yhbfhztj")
    @Operation(summary = "提交银行审核")
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:yhbfhztj')")
    public CommonResult<String> yhbfhztj(@Valid @RequestBody YhbfhzSaveReqVO saveReqVO) {
        YhbfhzDO yhbfhzDO = BeanUtils.toBean(saveReqVO, YhbfhzDO.class);
        yhbfhzService.updateYhbfhztj(yhbfhzDO);
        return success("已提交银行经办，待审批完成后进行支付明细结果同步！");
    }

    /**
     * 支付明细查询
     */
    @PostMapping("/zfmxcx")
    @Operation(summary = "支付明细查询")
//    @PreAuthorize("@ss.hasPermission('lghjft:yhbfhz:zfmxcx')")
    public CommonResult<String> zfmxcx(@Valid @RequestBody YhbfhzSaveReqVO saveReqVO) {
        YhbfhzDO yhbfhzDO = BeanUtils.toBean(saveReqVO, YhbfhzDO.class);
        yhbfhzService.updateZfmxcx(yhbfhzDO);
        return success("支付明细结果同步成功，请在拨付明细数据中查询！");
    }

}
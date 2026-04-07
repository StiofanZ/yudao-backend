package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx.CxtjJfmxService;
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

@Tag(name = "管理后台 - 经费明细(查询统计)")
@RestController("cxtjJfmxController")
@RequestMapping("/lghjft/cxtj/jfmx")
@Validated
public class JfmxController {

    @Resource
    private CxtjJfmxService cxtjJfmxService;

    @PostMapping("/create")
    @Operation(summary = "创建经费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:create')")
    public CommonResult<String> createJfmx(@Valid @RequestBody JfmxSaveReqVO createReqVO) {
        return success(cxtjJfmxService.createJfmx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经费明细")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:update')")
    public CommonResult<Boolean> updateJfmx(@Valid @RequestBody JfmxSaveReqVO updateReqVO) {
        cxtjJfmxService.updateJfmx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经费明细")
    @Parameter(name = "spuuid", description = "spuuid", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:delete')")
    public CommonResult<Boolean> deleteJfmx(@RequestParam("spuuid") String spuuid) {
        cxtjJfmxService.deleteJfmx(spuuid);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经费明细")
    @Parameter(name = "spuuid", description = "spuuid", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<JfmxResVO> getJfmx(@RequestParam("spuuid") String spuuid) {
        CxtjJfmxDO obj = cxtjJfmxService.getJfmx(spuuid);
        return success(BeanUtils.toBean(obj, JfmxResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费明细分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:query')")
    public CommonResult<PageResult<JfmxResVO>> getJfmxPage(@Valid JfmxPageReqVO pageReqVO) {
        return success(cxtjJfmxService.getJfmxPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经费明细 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jfmx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfmxExcel(@Valid JfmxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfmxResVO> list = cxtjJfmxService.getJfmxPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费明细.xls", "数据", JfmxResVO.class, list);
    }
}

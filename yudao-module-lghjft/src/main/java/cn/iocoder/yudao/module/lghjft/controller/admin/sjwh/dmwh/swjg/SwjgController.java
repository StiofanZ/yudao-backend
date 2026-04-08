package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.swjg.SwjgDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.swjg.SwjgService;
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

@Tag(name = "管理后台 - 税务机关")
@RestController
@RequestMapping("/lghjft/sjwh/dmwh/swjg")
@Validated
public class SwjgController {

    @Resource
    private SwjgService swjgService;

    @PostMapping("/create")
    @Operation(summary = "创建税务机关")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:create')")
    public CommonResult<String> createSwjg(@Valid @RequestBody SwjgSaveReqVO createReqVO) {
        return success(swjgService.createSwjg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新税务机关")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:update')")
    public CommonResult<Boolean> updateSwjg(@Valid @RequestBody SwjgSaveReqVO updateReqVO) {
        swjgService.updateSwjg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除税务机关")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:delete')")
    public CommonResult<Boolean> deleteSwjgList(@RequestParam("ids") List<String> ids) {
        swjgService.deleteSwjgByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得税务机关")
    @Parameter(name = "swjgDm", description = "机关代码", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:query')")
    public CommonResult<SwjgResVO> getSwjg(@RequestParam("swjgDm") String swjgDm) {
        SwjgDO swjg = swjgService.getSwjg(swjgDm);
        return success(BeanUtils.toBean(swjg, SwjgResVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得税务机关列表")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:query')")
    public CommonResult<List<SwjgResVO>> getSwjgList(@Valid SwjgListReqVO listReqVO) {
        List<SwjgDO> list = swjgService.getSwjgList(listReqVO);
        return success(BeanUtils.toBean(list, SwjgResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出税务机关 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-swjg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSwjgExcel(@Valid SwjgListReqVO listReqVO,
                                HttpServletResponse response) throws IOException {
        List<SwjgDO> list = swjgService.getSwjgList(listReqVO);
        ExcelUtils.write(response, "税务机关.xls", "数据", SwjgResVO.class,
                BeanUtils.toBean(list, SwjgResVO.class));
    }

}

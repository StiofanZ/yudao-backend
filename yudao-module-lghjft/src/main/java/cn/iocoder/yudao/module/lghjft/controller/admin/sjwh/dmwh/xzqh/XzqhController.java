package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh.XzqhService;
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

@Tag(name = "管理后台 - 行政区划")
@RestController
@RequestMapping("/lghjft/sjwh/dmwh/xzqh")
@Validated
public class XzqhController {

    @Resource
    private XzqhService xzqhService;

    @PostMapping("/create")
    @Operation(summary = "创建行政区划")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:create')")
    public CommonResult<String> createXzqh(@Valid @RequestBody XzqhSaveReqVO createReqVO) {
        return success(xzqhService.createXzqh(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行政区划")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:update')")
    public CommonResult<Boolean> updateXzqh(@Valid @RequestBody XzqhSaveReqVO updateReqVO) {
        xzqhService.updateXzqh(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除行政区划")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:delete')")
    public CommonResult<Boolean> deleteXzqhList(@RequestParam("ids") List<String> ids) {
        xzqhService.deleteXzqhByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行政区划")
    @Parameter(name = "xzqhDm", description = "行政区划代码", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:query')")
    public CommonResult<XzqhResVO> getXzqh(@RequestParam("xzqhDm") String xzqhDm) {
        XzqhDO xzqh = xzqhService.getXzqh(xzqhDm);
        return success(BeanUtils.toBean(xzqh, XzqhResVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得行政区划列表")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:query')")
    public CommonResult<List<XzqhResVO>> getXzqhList(@Valid XzqhListReqVO listReqVO) {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        return success(BeanUtils.toBean(list, XzqhResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-dmwh-xzqh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXzqhExcel(@Valid XzqhListReqVO listReqVO,
                                HttpServletResponse response) throws IOException {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        ExcelUtils.write(response, "行政区划.xls", "数据", XzqhResVO.class,
                BeanUtils.toBean(list, XzqhResVO.class));
    }

}

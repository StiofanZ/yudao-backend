package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.thpzcf.ThpzcfService;
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
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 退回凭证重发")
@RestController
@RequestMapping("/lghjft/jfcl/thpzcf")
@Validated
public class ThpzcfController {

    @Resource
    private ThpzcfService thpzcfService;

    @PostMapping("/create")
    @Operation(summary = "创建退回凭证重发")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:create')")
    public CommonResult<Long> createThpzcf(@Valid @RequestBody ThpzcfSaveReqVO createReqVO) {
        return success(thpzcfService.createThpzcf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新退回凭证重发")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:update')")
    public CommonResult<Boolean> updateThpzcf(@Valid @RequestBody ThpzcfSaveReqVO updateReqVO) {
        thpzcfService.updateThpzcf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除退回凭证重发")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:delete')")
    public CommonResult<Boolean> deleteThpzcf(@RequestParam("id") Long id) {
        thpzcfService.deleteThpzcf(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除退回凭证重发")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:delete')")
    public CommonResult<Boolean> deleteThpzcfList(@RequestParam("ids") String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        thpzcfService.deleteThpzcfBatch(idList);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得退回凭证重发")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:query')")
    public CommonResult<ThpzcfResVO> getThpzcf(@RequestParam("id") Long id) {
        ThpzcfDO data = thpzcfService.getThpzcf(id);
        return success(BeanUtils.toBean(data, ThpzcfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退回凭证重发分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:query')")
    public CommonResult<PageResult<ThpzcfResVO>> getThpzcfPage(@Valid ThpzcfPageReqVO pageReqVO) {
        PageResult<ThpzcfDO> pageResult = thpzcfService.getThpzcfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ThpzcfResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出退回凭证重发 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-thpzcf:export')")
    public void exportThpzcfExcel(@Valid ThpzcfPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ThpzcfDO> list = thpzcfService.getThpzcfPage(pageReqVO).getList();
        ExcelUtils.write(response, "退回凭证重发.xls", "数据", ThpzcfResVO.class,
                BeanUtils.toBean(list, ThpzcfResVO.class));
    }
}

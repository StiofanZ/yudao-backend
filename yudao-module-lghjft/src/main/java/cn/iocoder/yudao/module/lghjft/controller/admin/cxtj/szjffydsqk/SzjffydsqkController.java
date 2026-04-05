package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk.SzjffydsqkDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.szjffydsqk.SzjffydsqkService;
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

@Tag(name = "管理后台 - 分月代收情况(入库)")
@RestController
@RequestMapping("/lghjft/cxtj/szjffydsqk")
@Validated
public class SzjffydsqkController {

    @Resource
    private SzjffydsqkService szjffydsqkService;

    @PostMapping("/create")
    @Operation(summary = "创建分月代收情况(入库)")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:create')")
    public CommonResult<String> createSzjffydsqk(@Valid @RequestBody SzjffydsqkSaveReqVO createReqVO) {
        return success(szjffydsqkService.createSzjffydsqk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分月代收情况(入库)")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:update')")
    public CommonResult<Boolean> updateSzjffydsqk(@Valid @RequestBody SzjffydsqkSaveReqVO updateReqVO) {
        szjffydsqkService.updateSzjffydsqk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分月代收情况(入库)")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:delete')")
    public CommonResult<Boolean> deleteSzjffydsqk(@RequestParam("id") String id) {
        szjffydsqkService.deleteSzjffydsqk(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分月代收情况(入库)")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:delete')")
    public CommonResult<Boolean> deleteSzjffydsqkList(@RequestParam("ids") List<String> ids) {
        szjffydsqkService.deleteSzjffydsqkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分月代收情况(入库)")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:query')")
    public CommonResult<SzjffydsqkResVO> getSzjffydsqk(@RequestParam("id") String id) {
        SzjffydsqkDO obj = szjffydsqkService.getSzjffydsqk(id);
        return success(BeanUtils.toBean(obj, SzjffydsqkResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分月代收情况(入库)分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:query')")
    public CommonResult<PageResult<SzjffydsqkResVO>> getSzjffydsqkPage(@Valid SzjffydsqkPageReqVO pageReqVO) {
        PageResult<SzjffydsqkDO> pageResult = szjffydsqkService.getSzjffydsqkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SzjffydsqkResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分月代收情况(入库) Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-szjffydsqk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzjffydsqkExcel(@Valid SzjffydsqkPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SzjffydsqkDO> list = szjffydsqkService.getSzjffydsqkPage(pageReqVO).getList();
        ExcelUtils.write(response, "分月代收情况(入库).xls", "数据", SzjffydsqkResVO.class,
                BeanUtils.toBean(list, SzjffydsqkResVO.class));
    }
}

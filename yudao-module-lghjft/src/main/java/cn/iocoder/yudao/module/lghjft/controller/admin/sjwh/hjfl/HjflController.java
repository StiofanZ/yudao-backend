package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflSaveReqVO;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hjfl.HjflDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.hjfl.HjflService;
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

@Tag(name = "管理后台 - 户籍分类")
@RestController
@RequestMapping("/dm/hjfl")
@Validated
public class HjflController {

    @Resource
    private HjflService hjflService;

    @PostMapping("/create")
    @Operation(summary = "创建户籍分类")
    @PreAuthorize("@ss.hasPermission('dm:hjfl:create')")
    public CommonResult<Integer> createHjfl(@Valid @RequestBody HjflSaveReqVO createReqVO) {
        return success(hjflService.createHjfl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新户籍分类")
    @PreAuthorize("@ss.hasPermission('dm:hjfl:update')")
    public CommonResult<Boolean> updateHjfl(@Valid @RequestBody HjflSaveReqVO updateReqVO) {
        hjflService.updateHjfl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除户籍分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('dm:hjfl:delete')")
    public CommonResult<Boolean> deleteHjfl(@RequestParam("id") Integer id) {
        hjflService.deleteHjfl(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除户籍分类")
                @PreAuthorize("@ss.hasPermission('dm:hjfl:delete')")
    public CommonResult<Boolean> deleteHjflList(@RequestParam("ids") List<Integer> ids) {
        hjflService.deleteHjflListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得户籍分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('dm:hjfl:query')")
    public CommonResult<HjflRespVO> getHjfl(@RequestParam("id") Integer id) {
        HjflDO hjfl = hjflService.getHjfl(id);
        return success(BeanUtils.toBean(hjfl, HjflRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得户籍分类分页")
    @PreAuthorize("@ss.hasPermission('dm:hjfl:query')")
    public CommonResult<PageResult<HjflRespVO>> getHjflPage(@Valid HjflPageReqVO pageReqVO) {
        PageResult<HjflDO> pageResult = hjflService.getHjflPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HjflRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出户籍分类 Excel")
    @PreAuthorize("@ss.hasPermission('dm:hjfl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHjflExcel(@Valid HjflPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HjflDO> list = hjflService.getHjflPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "户籍分类.xls", "数据", HjflRespVO.class,
                        BeanUtils.toBean(list, HjflRespVO.class));
    }

}
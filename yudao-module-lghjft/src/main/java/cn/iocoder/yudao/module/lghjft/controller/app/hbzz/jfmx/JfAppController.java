package cn.iocoder.yudao.module.lghjft.controller.app.hbzz.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfRespVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx.JfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "app -  经费明细对象")
@RestController
@RequestMapping("/lghjft/jf")
@Validated
public class JfAppController {

    @Resource
    private JfService jfService;


    /**
     * 查询经费明细列表
     */
    @GetMapping("/listmx")
    @Operation(summary = "获得 经费明细对象明细")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
    public CommonResult<PageResult<JfRespVO>> list(@Valid JfPageReqVO jfmx) {
        PageResult<JfRespVO> pageResult = jfService.selectJftzmxList(jfmx);
        return success(BeanUtils.toBean(pageResult, JfRespVO.class));
    }



//    @PostMapping("/create")
//    @Operation(summary = "创建 经费明细对象")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:create')")
//    public CommonResult<Integer> createJf(@Valid @RequestBody JfSaveReqVO createReqVO) {
//        return success(jfService.createJf(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新 经费明细对象")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:update')")
//    public CommonResult<Boolean> updateJf(@Valid @RequestBody JfSaveReqVO updateReqVO) {
//        jfService.updateJf(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除 经费明细对象")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:delete')")
//    public CommonResult<Boolean> deleteJf(@RequestParam("id") Integer id) {
//        jfService.deleteJf(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除 经费明细对象")
//                @PreAuthorize("@ss.hasPermission('lghjft:jf:delete')")
//    public CommonResult<Boolean> deleteJfList(@RequestParam("ids") List<Integer> ids) {
//        jfService.deleteJfListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得 经费明细对象")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
//    public CommonResult<JfRespVO> getJf(@RequestParam("id") Integer id) {
//        JfDO jf = jfService.getJf(id);
//        return success(BeanUtils.toBean(jf, JfRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得 经费明细对象分页")
//    @PreAuthorize("@ss.hasPermission('lghjft:jf:query')")
//    public CommonResult<PageResult<JfRespVO>> getJfPage(@Valid JfPageReqVO pageReqVO) {
//        PageResult<JfDO> pageResult = jfService.getJfPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, JfRespVO.class));
//    }
//
    @GetMapping("/export-excel")
    @Operation(summary = "导出 经费明细对象 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:jf:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfExcel(@Valid JfPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfRespVO> list = jfService.selectJftzmxList(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, " 经费明细对象.xls", "数据", JfRespVO.class,
                        BeanUtils.toBean(list, JfRespVO.class));
    }

}

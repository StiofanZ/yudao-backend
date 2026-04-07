package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.jrbxzqdw.JrbxzqdwService;
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

@Tag(name = "管理后台 - 金融保险证券单位")
@RestController
@RequestMapping("/lghjft/cxtj/jrbxzqdw")
@Validated
public class JrbxzqdwController {

    @Resource
    private JrbxzqdwService jrbxzqdwService;

    @PostMapping("/create")
    @Operation(summary = "创建金融保险证券单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:create')")
    public CommonResult<String> createJrbxzqdw(@Valid @RequestBody JrbxzqdwSaveReqVO createReqVO) {
        return success(jrbxzqdwService.createJrbxzqdw(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新金融保险证券单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:update')")
    public CommonResult<Boolean> updateJrbxzqdw(@Valid @RequestBody JrbxzqdwSaveReqVO updateReqVO) {
        jrbxzqdwService.updateJrbxzqdw(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除金融保险证券单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:delete')")
    public CommonResult<Boolean> deleteJrbxzqdw(@RequestParam("id") String id) {
        jrbxzqdwService.deleteJrbxzqdw(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除金融保险证券单位")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:delete')")
    public CommonResult<Boolean> deleteJrbxzqdwList(@RequestParam("ids") List<String> ids) {
        jrbxzqdwService.deleteJrbxzqdwListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得金融保险证券单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:query')")
    public CommonResult<JrbxzqdwResVO> getJrbxzqdw(@RequestParam("id") String id) {
        JrbxzqdwDO obj = jrbxzqdwService.getJrbxzqdw(id);
        return success(BeanUtils.toBean(obj, JrbxzqdwResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得金融保险证券单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:query')")
    public CommonResult<PageResult<JrbxzqdwResVO>> getJrbxzqdwPage(@Valid JrbxzqdwPageReqVO pageReqVO) {
        PageResult<JrbxzqdwDO> pageResult = jrbxzqdwService.getJrbxzqdwPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JrbxzqdwResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出金融保险证券单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-jrbxzqdw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJrbxzqdwExcel(@Valid JrbxzqdwPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JrbxzqdwDO> list = jrbxzqdwService.getJrbxzqdwPage(pageReqVO).getList();
        ExcelUtils.write(response, "金融保险证券单位数据.xls", "数据", JrbxzqdwResVO.class,
                BeanUtils.toBean(list, JrbxzqdwResVO.class));
    }
}

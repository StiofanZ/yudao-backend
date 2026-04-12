package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsbyxg.XejfhbsbyxgDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsbyxg.XejfhbsbyxgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额失败已修改")
@RestController
@RequestMapping("/lghjft/xejf/xejfhbsbyxg")
@Validated
public class XejfhbsbyxgController {

    @Resource
    private XejfhbsbyxgService xejfhbsbyxgService;

    @PostMapping("/create")
    @Operation(summary = "创建小额失败已修改")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:create')")
    public CommonResult<Long> create(@Valid @RequestBody XejfhbsbyxgSaveReqVO createReqVO) {
        return success(xejfhbsbyxgService.createXejfhbsbyxg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额失败已修改")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XejfhbsbyxgSaveReqVO updateReqVO) {
        xejfhbsbyxgService.updateXejfhbsbyxg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额失败已修改")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        xejfhbsbyxgService.deleteXejfhbsbyxg(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额失败已修改")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        xejfhbsbyxgService.deleteXejfhbsbyxgListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额失败已修改")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:query')")
    public CommonResult<XejfhbsbyxgResVO> get(@RequestParam("id") Long id) {
        XejfhbsbyxgDO data = xejfhbsbyxgService.getXejfhbsbyxg(id);
        return success(BeanUtils.toBean(data, XejfhbsbyxgResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额失败已修改分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:query')")
    public CommonResult<PageResult<XejfhbsbyxgResVO>> page(@Valid XejfhbsbyxgPageReqVO pageReqVO) {
        PageResult<XejfhbsbyxgDO> pageResult = xejfhbsbyxgService.getXejfhbsbyxgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XejfhbsbyxgResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额失败已修改 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsbyxg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid XejfhbsbyxgPageReqVO pageReqVO, HttpServletResponse response)
            throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XejfhbsbyxgDO> list = xejfhbsbyxgService.getXejfhbsbyxgPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额失败已修改.xls", "数据", XejfhbsbyxgResVO.class,
                BeanUtils.toBean(list, XejfhbsbyxgResVO.class));
    }
}

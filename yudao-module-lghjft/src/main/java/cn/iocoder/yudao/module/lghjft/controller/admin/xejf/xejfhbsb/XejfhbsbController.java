package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsb.XejfhbsbDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsb.XejfhbsbService;
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

@Tag(name = "管理后台 - 小额划拨失败记录")
@RestController
@RequestMapping("/lghjft/xejf/xejfhbsb")
@Validated
public class XejfhbsbController {

    @Resource
    private XejfhbsbService xejfhbsbService;

    @PostMapping("/create")
    @Operation(summary = "创建小额划拨失败记录")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:create')")
    public CommonResult<Long> create(@Valid @RequestBody XejfhbsbSaveReqVO createReqVO) {
        return success(xejfhbsbService.createXejfhbsb(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额划拨失败记录")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XejfhbsbSaveReqVO updateReqVO) {
        xejfhbsbService.updateXejfhbsb(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额划拨失败记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        xejfhbsbService.deleteXejfhbsb(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额划拨失败记录")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        xejfhbsbService.deleteXejfhbsbListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额划拨失败记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:query')")
    public CommonResult<XejfhbsbResVO> get(@RequestParam("id") Long id) {
        XejfhbsbDO data = xejfhbsbService.getXejfhbsb(id);
        return success(BeanUtils.toBean(data, XejfhbsbResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额划拨失败记录分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:query')")
    public CommonResult<PageResult<XejfhbsbResVO>> page(@Valid XejfhbsbPageReqVO pageReqVO) {
        PageResult<XejfhbsbDO> pageResult = xejfhbsbService.getXejfhbsbPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XejfhbsbResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额划拨失败记录 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfhbsb:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid XejfhbsbPageReqVO pageReqVO, HttpServletResponse response)
            throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XejfhbsbDO> list = xejfhbsbService.getXejfhbsbPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额划拨失败记录.xls", "数据", XejfhbsbResVO.class,
                BeanUtils.toBean(list, XejfhbsbResVO.class));
    }
}

package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfzzgl.XejfghzzDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xejfzzgl.XejfghzzService;
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

@Tag(name = "管理后台 - 小额缴费组织管理")
@RestController
@RequestMapping("/lghjft/xejf/xejfzzgl")
@Validated
public class XejfghzzController {

    @Resource
    private XejfghzzService xejfghzzService;

    @PostMapping("/create")
    @Operation(summary = "创建小额缴费组织管理")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:create')")
    public CommonResult<String> create(@Valid @RequestBody XejfghzzSaveReqVO createReqVO) {
        return success(xejfghzzService.createXejfghzz(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额缴费组织管理")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XejfghzzSaveReqVO updateReqVO) {
        xejfghzzService.updateXejfghzz(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额缴费组织管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") String id) {
        xejfghzzService.deleteXejfghzz(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额缴费组织管理")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<String> ids) {
        xejfghzzService.deleteXejfghzzListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额缴费组织管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:query')")
    public CommonResult<XejfghzzResVO> get(@RequestParam("id") String id) {
        XejfghzzDO data = xejfghzzService.getXejfghzz(id);
        return success(BeanUtils.toBean(data, XejfghzzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额缴费组织管理分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:query')")
    public CommonResult<PageResult<XejfghzzResVO>> page(@Valid XejfghzzPageReqVO pageReqVO) {
        PageResult<XejfghzzDO> pageResult = xejfghzzService.getXejfghzzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XejfghzzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额缴费组织管理 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xejfzzgl:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid XejfghzzPageReqVO pageReqVO,
                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XejfghzzDO> list = xejfghzzService.getXejfghzzPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额缴费组织管理.xls", "数据", XejfghzzResVO.class,
                BeanUtils.toBean(list, XejfghzzResVO.class));
    }
}

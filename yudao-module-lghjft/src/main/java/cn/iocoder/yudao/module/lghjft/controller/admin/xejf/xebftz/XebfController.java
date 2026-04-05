package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xebftz.XebfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额缴费拨付台账")
@RestController
@RequestMapping("/lghjft/xejf/xebftz")
@Validated
public class XebfController {

    @Resource
    private XebfService xebfService;

    @GetMapping("/get")
    @Operation(summary = "获得小额缴费拨付台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:query')")
    public CommonResult<XebfResVO> get(@RequestParam("id") Long id) {
        XebfDO data = xebfService.getXebf(id);
        return success(BeanUtils.toBean(data, XebfResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额缴费拨付台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:query')")
    public CommonResult<PageResult<XebfResVO>> page(@Valid XebfPageReqVO pageReqVO) {
        PageResult<XebfDO> pageResult = xebfService.getXebfPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XebfResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小额缴费拨付台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXebfExcel(@Valid XebfPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XebfDO> list = xebfService.getXebfPage(pageReqVO).getList();
        ExcelUtils.write(response, "小额缴费拨付台账.xls", "数据", XebfResVO.class,
                BeanUtils.toBean(list, XebfResVO.class));
    }

    /**
     * 查询小额拨付省总列表 (v1: listsz)
     */
    @GetMapping("/list-sz")
    @Operation(summary = "获得小额拨付省总列表")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-hkxxxejf:query')")
    public CommonResult<List<GhhkxxxejfszResVO>> listSz(@Valid GhhkxxxejfszReqVO reqVO) {
        List<GhhkxxxejfszResVO> list = xebfService.getGhHkxxxejfszList(reqVO.getJfqj());
        return success(list);
    }

    /**
     * 导出小额拨付省总 Excel (v1: exportsz)
     */
    @GetMapping("/export-excel-sz")
    @Operation(summary = "导出小额拨付省总 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebftz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSzExcel(@Valid GhhkxxxejfszReqVO reqVO,
                              HttpServletResponse response) throws IOException {
        List<GhhkxxxejfszResVO> list = xebfService.getGhHkxxxejfszList(reqVO.getJfqj());
        ExcelUtils.write(response, "小额缴费拨付省总.xls", "数据",
                GhhkxxxejfszResVO.class, list);
    }
}

package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw.ZswzgdwService;
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

@Tag(name = "管理后台 - 征收未主管单位")
@RestController
@RequestMapping("/lghjft/cxtj/zswzgdw")
@Validated
public class ZswzgdwController {

    @Resource
    private ZswzgdwService zswzgdwService;

    @GetMapping("/get")
    @Operation(summary = "获得征收未主管单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:zswzgdw:query')")
    public CommonResult<ZswzgdwResVO> getZswzgdw(@RequestParam("id") String id) {
        ZswzgdwDO obj = zswzgdwService.getZswzgdw(id);
        return success(BeanUtils.toBean(obj, ZswzgdwResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得征收未主管单位分页")
    @PreAuthorize("@ss.hasPermission('lghjft:zswzgdw:query')")
    public CommonResult<PageResult<ZswzgdwResVO>> getZswzgdwPage(@Valid ZswzgdwPageReqVO pageReqVO) {
        PageResult<ZswzgdwDO> pageResult = zswzgdwService.getZswzgdwPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ZswzgdwResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出征收未主管单位 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:zswzgdw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportZswzgdwExcel(@Valid ZswzgdwPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ZswzgdwDO> list = zswzgdwService.getZswzgdwPage(pageReqVO).getList();
        ExcelUtils.write(response, "征收未主管单位.xls", "数据", ZswzgdwResVO.class,
                BeanUtils.toBean(list, ZswzgdwResVO.class));
    }
}

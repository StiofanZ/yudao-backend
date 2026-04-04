package cn.iocoder.yudao.module.lghjft.controller.app.hbzz.hkxx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxx.HkxxDO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.hkxx.HkxxService;
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

@Tag(name = "app - 基层经费到账对象")
@RestController
@RequestMapping("/lghjft/hbzz/hkxx")
@Validated
public class HkxxAppController {

    @Resource
    private HkxxService hkxxService;

    @GetMapping("/page")
    @Operation(summary = "获得基层经费到账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-hkxx:query')")
    public CommonResult<PageResult<HkxxResVO>> getHkxxPage(@Valid HkxxPageReqVO pageReqVO) {
        PageResult<HkxxResVO> pageResult = hkxxService.getHkxxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HkxxResVO.class));
    }

    @GetMapping("/summary")
    @Operation(summary = "获得返拨概况")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-hkxx:query')")
    public CommonResult<HkxxSummaryResVO> getHkxxSummary(@Valid HkxxPageReqVO pageReqVO) {
        return success(hkxxService.getHkxxSummary(pageReqVO));
    }

//获取详情

    @GetMapping("/get")
    @Operation(summary = "获得基层经费到账对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-hkxx:query')")
    public CommonResult<HkxxResVO> getHkxx(@RequestParam("id") Integer id) {
        HkxxDO hkxx = hkxxService.getHkxx(id);
        return success(BeanUtils.toBean(hkxx, HkxxResVO.class));
    }

    //修改
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-hkxx:update')")
    @Operation(summary = "更新基层经费到账对象")
    public CommonResult<Boolean> updateHkxx(@Valid @RequestBody HkxxSaveReqVO updateReqVO) {
        hkxxService.updateHkxx(updateReqVO);
        return success(true);
    }


    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hbzz-hkxx:export')")
    @Operation(summary = "导出基层经费到账对象 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHkxxExcel(@Valid HkxxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HkxxResVO> list = hkxxService.getHkxxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基层经费到账对象.xls", "数据", HkxxResVO.class,
                BeanUtils.toBean(list, HkxxResVO.class));
    }

}

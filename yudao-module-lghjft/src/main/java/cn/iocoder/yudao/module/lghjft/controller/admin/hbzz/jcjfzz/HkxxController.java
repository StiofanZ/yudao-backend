package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.HkxxDO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz.HkxxService;

@Tag(name = "管理后台 - 基层经费到账对象")
@RestController
@RequestMapping("/lghjft/hkxx")
@Validated
public class HkxxController {

    @Resource
    private HkxxService hkxxService;
    @GetMapping("/page")
    @Operation(summary = "获得基层经费到账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hkxx:query')")
    public CommonResult<PageResult<HkxxRespVO>> getHkxxPage(@Valid HkxxPageReqVO pageReqVO) {
        PageResult<HkxxRespVO> pageResult = hkxxService.getHkxxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HkxxRespVO.class));
    }

//获取详情

    @GetMapping("/get")
    @Operation(summary = "获得基层经费到账对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hkxx:query')")
    public CommonResult<HkxxRespVO> getHkxx(@RequestParam("id") Integer id) {
        HkxxDO hkxx = hkxxService.getHkxx(id);
        return success(BeanUtils.toBean(hkxx, HkxxRespVO.class));
    }
//修改
    @PutMapping("/update")
    @Operation(summary = "更新基层经费到账对象")
    @PreAuthorize("@ss.hasPermission('lghjft:hkxx:update')")
    public CommonResult<Boolean> updateHkxx(@Valid @RequestBody HkxxSaveReqVO updateReqVO) {
        hkxxService.updateHkxx(updateReqVO);
        return success(true);
    }


    @GetMapping("/export-excel")
    @Operation(summary = "导出基层经费到账对象 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:hkxx:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHkxxExcel(@Valid HkxxPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HkxxRespVO> list = hkxxService.getHkxxPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基层经费到账对象.xls", "数据", HkxxRespVO.class,
                BeanUtils.toBean(list, HkxxRespVO.class));
    }

}
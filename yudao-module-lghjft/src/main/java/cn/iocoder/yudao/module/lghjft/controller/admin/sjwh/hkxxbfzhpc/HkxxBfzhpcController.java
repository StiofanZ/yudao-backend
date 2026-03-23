package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc;

import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.HkxxSaveReqVO;
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

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hkxxbfzhpc.HkxxBfzhpcDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.hkxxbfzhpc.HkxxBfzhpcService;

@Tag(name = "管理后台 - 拨付经费排除解除")
@RestController
@RequestMapping("/lghjft/hkxx-bfzhpc")
@Validated
public class HkxxBfzhpcController {

    @Resource
    private HkxxBfzhpcService hkxxBfzhpcService;



//    @PutMapping("/update")
//    @Operation(summary = "更新拨付信息")
//    @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:update')")
//    public CommonResult<Boolean> updateHkxxBfzhpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO updateReqVO) {
//        hkxxBfzhpcService.updateHkxxBfzhpc(updateReqVO);
//        return success(true);
//    }


    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除拨付信息")
                @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:delete')")
    public CommonResult<Boolean> deleteHkxxBfzhpcList(@RequestParam("ids") List<Integer> ids) {
        hkxxBfzhpcService.deleteHkxxBfzhpcListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得拨付信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:query')")
    public CommonResult<HkxxBfzhpcRespVO> getHkxxBfzhpc(@RequestParam("spuuid") String spuuid) {
        HkxxBfzhpcRespVO hkxxBfzhpc = hkxxBfzhpcService.getHkxxBfzhpc(spuuid);
        return success(BeanUtils.toBean(hkxxBfzhpc, HkxxBfzhpcRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得拨付信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:query')")
    public CommonResult<PageResult<HkxxBfzhpcRespVO>> getHkxxBfzhpcPage(@Valid HkxxBfzhpcPageReqVO pageReqVO) {
        PageResult<HkxxBfzhpcRespVO> pageResult = hkxxBfzhpcService.getBfjfpcPage(pageReqVO);
        return success(pageResult);
    }
    /**
     * 修改账户排除解除
     */

    @PutMapping("/update")
    @Operation(summary = "更新拨付信息")
    @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:update')")
    public CommonResult<Boolean> updateHkxxBfzhpc(@Valid @RequestBody HkxxBfzhpcSaveReqVO updateReqVO) {
        hkxxBfzhpcService.updateHkxxBfzhpc(updateReqVO);
        return success(true);
    }
    @GetMapping("/export-excel")
    @Operation(summary = "导出拨付信息 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:bfzhpc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHkxxBfzhpcExcel(@Valid HkxxBfzhpcPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HkxxBfzhpcRespVO> list = hkxxBfzhpcService.getBfjfpcPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "拨付信息.xls", "数据", HkxxBfzhpcRespVO.class,
                        BeanUtils.toBean(list, HkxxBfzhpcRespVO.class));
    }

}
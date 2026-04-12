package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz.CbjqltzDO;
import cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjqltz.CbjqltzService;
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

@Tag(name = "管理后台 - 筹备金清理台账")
@RestController
@RequestMapping("/lghjft/ghcbj/cbjqltz")
@Validated
public class CbjqltzController {

    @Resource
    private CbjqltzService cbjqltzService;

    @PutMapping("/update")
    @Operation(summary = "更新筹备金清理台账")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjqltz:update')")
    public CommonResult<Boolean> updateCbjqltz(@Valid @RequestBody CbjqltzSaveReqVO updateReqVO) {
        cbjqltzService.updateCbjqltz(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得筹备金清理台账")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjqltz:query')")
    public CommonResult<CbjqltzResVO> getCbjqltz(@RequestParam("djxh") String djxh) {
        CbjqltzDO obj = cbjqltzService.getCbjqltz(djxh);
        return success(BeanUtils.toBean(obj, CbjqltzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得筹备金清理台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjqltz:query')")
    public CommonResult<PageResult<CbjqltzResVO>> getCbjqltzPage(@Valid CbjqltzPageReqVO pageReqVO) {
        PageResult<CbjqltzDO> pageResult = cbjqltzService.getCbjqltzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CbjqltzResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出筹备金清理台账 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:ghcbj-cbjqltz:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCbjqltzExcel(@Valid CbjqltzPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CbjqltzDO> list = cbjqltzService.getCbjqltzPage(pageReqVO).getList();
        ExcelUtils.write(response, "筹备金清理台账.xls", "数据", CbjqltzResVO.class,
                BeanUtils.toBean(list, CbjqltzResVO.class));
    }
}

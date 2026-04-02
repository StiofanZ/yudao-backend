package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj.DqdssjService;
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

@Tag(name = "管理后台 - 当期单笔数据")
@RestController
@RequestMapping("/lghjft/jfcl/dqdssj")
@Validated
public class DqdssjController {

    @Resource
    private DqdssjService dqdssjService;

    @PostMapping("/create")
    @Operation(summary = "创建当期单笔数据")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:create')")
    public CommonResult<Long> createDqdssj(@Valid @RequestBody DqdssjSaveReqVO createReqVO) {
        return success(dqdssjService.createDqdssj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新当期单笔数据")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:update')")
    public CommonResult<Boolean> updateDqdssj(@Valid @RequestBody DqdssjSaveReqVO updateReqVO) {
        dqdssjService.updateDqdssj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除当期单笔数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:delete')")
    public CommonResult<Boolean> deleteDqdssj(@RequestParam("id") Long id) {
        dqdssjService.deleteDqdssj(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得当期单笔数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:query')")
    public CommonResult<DqdssjResVO> getDqdssj(@RequestParam("id") Long id) {
        JfclDqdssjDO data = dqdssjService.getDqdssj(id);
        return success(BeanUtils.toBean(data, DqdssjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得当期单笔数据分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:query')")
    public CommonResult<PageResult<DqdssjResVO>> getDqdssjPage(@Valid DqdssjPageReqVO pageReqVO) {
        PageResult<JfclDqdssjDO> pageResult = dqdssjService.getDqdssjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DqdssjResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出当期单笔数据 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:export')")
    public void exportDqdssjExcel(@Valid DqdssjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclDqdssjDO> list = dqdssjService.getDqdssjPage(pageReqVO).getList();
        ExcelUtils.write(response, "当期单笔数据.xls", "数据", DqdssjResVO.class,
                BeanUtils.toBean(list, DqdssjResVO.class));
    }
}

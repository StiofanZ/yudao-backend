package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc.NdrwwcService;
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

@Tag(name = "管理后台 - 分上缴周期统计")
@RestController
@RequestMapping("/lghjft/cxtj/ndrwwc")
@Validated
public class NdrwwcController {

    @Resource
    private NdrwwcService ndrwwcService;

    /**
     * V1: POST — 新增分年各级分成情况
     */
    @PostMapping("/create")
    @Operation(summary = "创建分上缴周期统计")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:create')")
    public CommonResult<String> createNdrwwc(@Valid @RequestBody NdrwwcSaveReqVO createReqVO) {
        return success(ndrwwcService.createNdrwwc(createReqVO));
    }

    /**
     * V1: PUT — 修改分年各级分成情况
     */
    @PutMapping("/update")
    @Operation(summary = "更新分上缴周期统计")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:update')")
    public CommonResult<Boolean> updateNdrwwc(@Valid @RequestBody NdrwwcSaveReqVO updateReqVO) {
        ndrwwcService.updateNdrwwc(updateReqVO);
        return success(true);
    }

    /**
     * V1: DELETE /{nds} — 批量删除 (String[] 数组)
     */
    @DeleteMapping("/delete/{nds}")
    @Operation(summary = "批量删除分上缴周期统计")
    @Parameter(name = "nds", description = "年度数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:delete')")
    public CommonResult<Boolean> deleteNdrwwc(@PathVariable("nds") String[] nds) {
        ndrwwcService.deleteNdrwwcByNds(nds);
        return success(true);
    }

    /**
     * V1: GET /{nd} — 获取单条
     */
    @GetMapping("/get")
    @Operation(summary = "获得分上缴周期统计")
    @Parameter(name = "nd", description = "年度", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:query')")
    public CommonResult<NdrwwcResVO> getNdrwwc(@RequestParam("nd") String nd) {
        NdrwwcDO obj = ndrwwcService.getNdrwwc(nd);
        return success(BeanUtils.toBean(obj, NdrwwcResVO.class));
    }

    /**
     * V1: GET /list — NO pagination (startPage 被注释掉)
     */
    @GetMapping("/page")
    @Operation(summary = "获得分上缴周期统计分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:query')")
    public CommonResult<PageResult<NdrwwcResVO>> getNdrwwcPage(@Valid NdrwwcPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<NdrwwcDO> pageResult = ndrwwcService.getNdrwwcPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NdrwwcResVO.class));
    }

    /**
     * V1: POST /export — 导出 "分上缴周期统计数据"
     */
    @GetMapping("/export-excel")
    @Operation(summary = "导出分上缴周期统计 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-ndrwwc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportNdrwwcExcel(@Valid NdrwwcPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<NdrwwcDO> list = ndrwwcService.getNdrwwcPage(pageReqVO).getList();
        ExcelUtils.write(response, "分上缴周期统计数据.xls", "数据", NdrwwcResVO.class,
                BeanUtils.toBean(list, NdrwwcResVO.class));
    }
}

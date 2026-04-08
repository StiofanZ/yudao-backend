package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj.SchbwjService;
import io.swagger.v3.oas.annotations.Operation;
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

/**
 * 经费处理 - 生成划拨文件 Controller
 * 1:1 translation of V1 JfclSchbwjController
 */
@Tag(name = "管理后台 - 生成划拨文件")
@RestController
@RequestMapping("/lghjft/jfcl/schbwj")
@Validated
public class SchbwjController {

    @Resource
    private SchbwjService schbwjService;

    /**
     * v1: GET /list — paginated, gh_hkxx LEFT JOIN sys_user WHERE yxbj='Y' ORDER BY hkpch desc
     */
    @GetMapping("/page")
    @Operation(summary = "获得生成划拨文件分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-schbwj:query')")
    public CommonResult<PageResult<JfclSchbwjDO>> getSchbwjPage(@Valid SchbwjPageReqVO pageReqVO) {
        return success(schbwjService.getSchbwjPage(pageReqVO));
    }

    /**
     * v1: POST / — async thread for allocation generation (updateGhjfhb)
     */
    @PostMapping("/generate")
    @Operation(summary = "工会经费划拨 - 生成划拨数据")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-schbwj:create')")
    public CommonResult<String> generateHbData(@RequestBody SchbwjSaveReqVO reqVO) {
        return success(schbwjService.updateGhjfhb(reqVO));
    }

    /**
     * v1: POST /export — export with hkpch validation
     */
    @GetMapping("/export-excel")
    @Operation(summary = "导出生成划拨文件 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-schbwj:export')")
    public void exportSchbwjExcel(@Valid SchbwjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        if (pageReqVO.getHkpch() == null || pageReqVO.getHkpch().isEmpty()) {
            throw new RuntimeException("划拨批次号不能为空");
        }
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclSchbwjDO> list = schbwjService.getSchbwjPage(pageReqVO).getList();
        ExcelUtils.write(response, "经费划拨文件.xls", "数据", JfclSchbwjDO.class, list);
    }
}

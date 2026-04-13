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
 * V1: JfclDqdssjController - 读取代收数据主链
 */
@Tag(name = "管理后台 - 经费处理-读取代收数据")
@RestController
@RequestMapping("/lghjft/jfcl/dqdssj")
@Validated
public class DqdssjController {

    @Resource
    private DqdssjService dqdssjService;

    /**
     * V1: GET /list — paginated, queries gh_qsjshkrj table (list of import logs)
     */
    @GetMapping("/page")
    @Operation(summary = "查询读取代收数据列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:query')")
    public CommonResult<PageResult<DqdssjResVO>> getDqdssjPage(@Valid DqdssjPageReqVO pageReqVO) {
        PageResult<JfclDqdssjDO> pageResult = dqdssjService.getDqdssjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DqdssjResVO.class));
    }

    /**
     * V1: POST / — import dqdssj (async thread with complex proportion distribution)
     */
    @PostMapping("/create")
    @Operation(summary = "代收数据入库")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:create')")
    public CommonResult<String> dqdssjrk(@RequestBody DqdssjSaveReqVO reqVO) {
        String msg = dqdssjService.updateDqdssjrk(reqVO);
        return success(msg);
    }

    /**
     * V1: POST /export — export with date range validation
     */
    @GetMapping("/export-excel")
    @Operation(summary = "导出代收数据 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqdssj:export')")
    public void exportDqdssjExcel(@Valid DqdssjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        if (pageReqVO.getRkrqStart() == null || pageReqVO.getRkrqEnd() == null) {
            throw new RuntimeException("入库日期起止不能为空");
        }
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclDqdssjDO> list = dqdssjService.getDqdssjPage(pageReqVO).getList();
        ExcelUtils.write(response, "代收数据.xls", "数据", DqdssjResVO.class,
                BeanUtils.toBean(list, DqdssjResVO.class));
    }
}

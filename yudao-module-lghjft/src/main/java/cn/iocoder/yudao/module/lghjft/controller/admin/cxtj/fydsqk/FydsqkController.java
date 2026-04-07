package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.fydsqk.FydsqkService;
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

@Tag(name = "管理后台 - 分月代收情况")
@RestController
@RequestMapping("/lghjft/cxtj/fydsqk")
@Validated
public class FydsqkController {

    @Resource
    private FydsqkService fydsqkService;

    @GetMapping("/page")
    @Operation(summary = "获得分月代收情况分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-fydsqk:query')")
    public CommonResult<PageResult<FydsqkResVO>> getFydsqkPage(@Valid FydsqkPageReqVO pageReqVO) {
        PageResult<FydsqkDO> pageResult = fydsqkService.getFydsqkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FydsqkResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分月代收情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-fydsqk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFydsqkExcel(@Valid FydsqkPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FydsqkDO> list = fydsqkService.getFydsqkPage(pageReqVO).getList();
        ExcelUtils.write(response, "分月代收情况数据.xls", "数据", FydsqkResVO.class,
                BeanUtils.toBean(list, FydsqkResVO.class));
    }
}

package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.fyfcqk.FyfcqkService;
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

@Tag(name = "管理后台 - 分月分成情况")
@RestController
@RequestMapping("/lghjft/cxtj/fyfcqk")
@Validated
public class FyfcqkController {

    @Resource
    private FyfcqkService fyfcqkService;

    @GetMapping("/page")
    @Operation(summary = "获得分月分成情况分页")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-fyfcqk:query')")
    public CommonResult<PageResult<FyfcqkResVO>> getFyfcqkPage(@Valid FyfcqkPageReqVO pageReqVO) {
        PageResult<FyfcqkDO> pageResult = fyfcqkService.getFyfcqkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FyfcqkResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分月分成情况 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:cxtj-fyfcqk:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFyfcqkExcel(@Valid FyfcqkPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FyfcqkDO> list = fyfcqkService.getFyfcqkPage(pageReqVO).getList();
        ExcelUtils.write(response, "分月分成情况数据.xls", "数据", FyfcqkResVO.class,
                BeanUtils.toBean(list, FyfcqkResVO.class));
    }
}

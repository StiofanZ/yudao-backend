package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs.JfbjsService;
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

@Tag(name = "管理后台 - 经费补结算")
@RestController
@RequestMapping("/lghjft/jfcl/jfbjs")
@Validated
public class JfbjsController {

    @Resource
    private JfbjsService jfbjsService;

    /**
     * v1: GET /list — 查询经费补结算列表
     * 查询 gh_jf WHERE 65+ optional conditions AND jsbj='N'
     */
    @GetMapping("/page")
    @Operation(summary = "获得经费补结算分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:query')")
    public CommonResult<PageResult<JfbjsResVO>> getJfbjsPage(@Valid JfbjsPageReqVO pageReqVO) {
        PageResult<JfclJfbjsDO> pageResult = jfbjsService.getJfbjsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JfbjsResVO.class));
    }

    /**
     * v1: POST / — 工会经费补结算
     * 查询 gh_jf WHERE jsbj='N', 设置 jsbj='Y'/'W' based on jmse,
     * 同时更新 gh_qsjshkrj 结算日志
     */
    @PostMapping("/settle")
    @Operation(summary = "工会经费补结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:update')")
    public CommonResult<Boolean> settleJfbjs(@Valid @RequestBody JfbjsPageReqVO reqVO) {
        jfbjsService.settleJfbjs(reqVO);
        return success(true);
    }

    /**
     * v1: POST /export — 导出待补结算数据
     * 入库日期起止不能为空
     */
    @GetMapping("/export-excel")
    @Operation(summary = "导出待补结算数据")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfbjs:export')")
    public void exportJfbjsExcel(@Valid JfbjsPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        // v1: if(null == jfclJfbjs || null == jfclJfbjs.getRkrqStart() || null == jfclJfbjs.getRkrqEnd())
        if (pageReqVO.getRkrqStart() == null || pageReqVO.getRkrqStart().isEmpty()
                || pageReqVO.getRkrqEnd() == null || pageReqVO.getRkrqEnd().isEmpty()) {
            throw new cn.iocoder.yudao.framework.common.exception.ServiceException(
                    cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST.getCode(),
                    "入库日期起止不能为空");
        }
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfclJfbjsDO> list = jfbjsService.getJfbjsPage(pageReqVO).getList();
        ExcelUtils.write(response, "代收数据.xls", "数据", JfbjsResVO.class,
                BeanUtils.toBean(list, JfbjsResVO.class));
    }
}

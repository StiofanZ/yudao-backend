package cn.iocoder.yudao.module.lghjft.controller.app.hbzz.jfmx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfResVO;
import cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx.JfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 app - 经费明细对象")
@RestController
@RequestMapping("/lghjft/hbzz/jfmx")
@Validated
public class JfAppController {

    @Resource
    private JfService jfService;


    /**
     * 查询经费明细列表
     */
    @GetMapping("/listmx")
    @Operation(summary = "获得 经费明细对象明细")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<JfResVO>> list(@Valid JfPageReqVO jfmx) {
        PageResult<JfResVO> pageResult = jfService.selectJftzmxList(jfmx);
        return success(BeanUtils.toBean(pageResult, JfResVO.class));
    }


    @GetMapping("/export-excel")
    @PreAuthorize("isAuthenticated()")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJfExcel(@Valid JfPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfResVO> list = jfService.selectJftzmxList(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, " 经费明细对象.xls", "数据", JfResVO.class,
                BeanUtils.toBean(list, JfResVO.class));
    }

}

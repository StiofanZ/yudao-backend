package cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.Jfmx;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxExportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxQuery;
import cn.iocoder.yudao.module.lghjft.service.jf.jfmx.JfmxService;
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

/**
 * 经费明细 Controller
 */
@RestController
@RequestMapping("/lghjft/jf/jfmx")
@Validated
public class JfmxController {

    @Resource
    private JfmxService jfmxService;

    /**
     * 获取经费明细列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('lghjft:jf-jfmx:query')")
    public CommonResult<List<Jfmx>> list(@Valid JfmxQuery query) {
        List<Jfmx> list = jfmxService.selectJfmxList(query);
        return CommonResult.success(list);
    }

    /**
     * 获取经费明细分页列表
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('lghjft:jf-jfmx:query')")
    public CommonResult<PageResult<Jfmx>> page(@Valid JfmxQuery query) {
        PageResult<Jfmx> pageResult = jfmxService.selectJfmxPage(query);
        return CommonResult.success(pageResult);
    }

    /**
     * 导出经费明细
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('lghjft:jf-jfmx:export')")
    public void export(HttpServletResponse response, @Valid JfmxQuery query) {
        try {
            List<JfmxExportVO> list = jfmxService.selectJfmxExportList(query);
            ExcelUtils.write(response, "经费明细.xlsx", "经费明细", JfmxExportVO.class, list);
        } catch (IOException e) {
            e.printStackTrace();
            // 返回错误信息给前端
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
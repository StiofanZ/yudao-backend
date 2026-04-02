package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.service.xwqygl.XwqyglService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小微企业管理")
@RestController
@RequestMapping("/lghjft/xwqy/xwqygl")
@Validated
public class XwqyglController {

    @Resource
    private XwqyglService xwqyglService;

    /**
     * 解决前端空字符串无法转换为 Date 的问题
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 前端日期格式，如果前端是 "yyyy-MM-dd HH:mm:ss"，请改成相应格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        // true 表示允许空值，空字符串会自动转换为 null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小微企业管理分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:query')")
    public CommonResult<PageResult<XwqyglResVO>> getXwqyglPage(@Valid XwqyglQuery query) {
        PageResult<XwqyglResVO> pageResult = xwqyglService.getXwqyglPage(query);
        return success(pageResult);
    }

    /**
     * 导出小微企业管理列表
     */
    @GetMapping("/export")
    @Operation(summary = "导出小微企业管理 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:export')")
    public void exportExcel(@Valid XwqyglQuery query, HttpServletResponse response) throws IOException {
        List<XwqyglResVO> list = xwqyglService.getXwqyglList(query);
        ExcelUtils.write(response, "小微企业管理.xls", "数据",
                XwqyglResVO.class, list);
    }
}
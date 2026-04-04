package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.xwqygl.XwqyglService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
     * 获取小微企业管理详细信息
     */
    @GetMapping("/get")
    @Operation(summary = "获得小微企业管理详情")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:query')")
    public CommonResult<XwqyglResVO> getInfo(@RequestParam("djxh") String djxh) {
        XwqyglResVO data = xwqyglService.getXwqyglByDjxh(djxh);
        return success(data);
    }

    /**
     * 新增小微企业管理
     */
    @PostMapping("/create")
    @Operation(summary = "新增小微企业管理")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:create')")
    public CommonResult<Boolean> create(@Valid @RequestBody XwqyglSaveReqVO reqVO) {
        xwqyglService.createXwqygl(reqVO);
        return success(true);
    }

    /**
     * 修改小微企业管理
     */
    @PutMapping("/update")
    @Operation(summary = "修改小微企业管理")
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XwqyglSaveReqVO reqVO) {
        xwqyglService.updateXwqygl(reqVO);
        return success(true);
    }

    /**
     * 删除小微企业管理
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除小微企业管理")
    @Parameter(name = "djxhs", description = "登记序号数组", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xwqy-xwqygl:delete')")
    public CommonResult<Boolean> delete(@RequestParam("djxhs") String[] djxhs) {
        xwqyglService.deleteXwqyglByDjxhs(djxhs);
        return success(true);
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
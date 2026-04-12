package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.xwqyglcbj.XwqyglcbjService;
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

@Tag(name = "管理后台 - 已建会缴筹备金")
@RestController
@RequestMapping("/lghjft/sjwh/xwqyglcbj")
@Validated
public class XwqyglcbjController {

    @Resource
    private XwqyglcbjService xwqyglcbjService;

    @GetMapping("/page")
    @Operation(summary = "获得已建会缴筹备金分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-xwqyglcbj:query')")
    public CommonResult<PageResult<XwqyglcbjResVO>> getXwqyglcbjPage(@Valid XwqyglcbjPageReqVO pageReqVO) {
        return success(BeanUtils.toBean(xwqyglcbjService.getXwqyglcbjPage(pageReqVO), XwqyglcbjResVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得已建会缴筹备金详情")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-xwqyglcbj:query')")
    public CommonResult<XwqyglcbjResVO> getXwqyglcbj(@RequestParam("djxh") String djxh) {
        return success(BeanUtils.toBean(xwqyglcbjService.getXwqyglcbjByDjxh(djxh), XwqyglcbjResVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新已建会缴筹备金户标记")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-xwqyglcbj:update')")
    public CommonResult<Boolean> updateXwqyglcbj(@Valid @RequestBody XwqyglcbjSaveReqVO updateReqVO) {
        xwqyglcbjService.updateXwqyglcbj(updateReqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出已建会缴筹备金 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-xwqyglcbj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXwqyglcbjExcel(@Valid XwqyglcbjPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XwqyglcbjResVO> list = xwqyglcbjService.getXwqyglcbjList(pageReqVO);
        ExcelUtils.write(response, "已建会缴筹备金户.xls", "数据", XwqyglcbjResVO.class, list);
    }
}

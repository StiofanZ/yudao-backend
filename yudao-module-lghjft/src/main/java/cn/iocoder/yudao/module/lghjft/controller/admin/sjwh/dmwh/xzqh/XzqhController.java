package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh.XzqhService;
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

@Tag(name = "管理后台 - 行政区划")
@RestController
@RequestMapping("/lghjft/sjwh/dmwh/xzqh")
@Validated
public class XzqhController {

    @Resource
    private XzqhService xzqhService;


    @GetMapping("/list")
    @Operation(summary = "获得行政区划列表")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-xzqh:query')")
    public CommonResult<List<XzqhResVO>> getXzqhList(@Valid XzqhListReqVO listReqVO) {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        return success(BeanUtils.toBean(list, XzqhResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:dmwh-xzqh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXzqhExcel(@Valid XzqhListReqVO listReqVO,
                                HttpServletResponse response) throws IOException {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "行政区划.xls", "数据", XzqhResVO.class,
                BeanUtils.toBean(list, XzqhResVO.class));
    }

}
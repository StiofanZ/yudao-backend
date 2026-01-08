package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh.vo.XzqhRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.xzqh.XzqhDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.xzqh.XzqhService;
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
@RequestMapping("/dm/xzqh")
@Validated
public class XzqhController {

    @Resource
    private XzqhService xzqhService;


    @GetMapping("/list")
    @Operation(summary = "获得行政区划列表")
    @PreAuthorize("@ss.hasPermission('dm:xzqh:query')")
    public CommonResult<List<XzqhRespVO>> getXzqhList(@Valid XzqhListReqVO listReqVO) {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        return success(BeanUtils.toBean(list, XzqhRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('dm:xzqh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportXzqhExcel(@Valid XzqhListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<XzqhDO> list = xzqhService.getXzqhList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "行政区划.xls", "数据", XzqhRespVO.class,
                        BeanUtils.toBean(list, XzqhRespVO.class));
    }

}
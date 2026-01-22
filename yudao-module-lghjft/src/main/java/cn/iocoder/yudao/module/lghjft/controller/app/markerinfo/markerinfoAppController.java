package cn.iocoder.yudao.module.lghjft.controller.app.markerinfo;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.MarkerInfoPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.MarkerInfoRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import cn.iocoder.yudao.module.lghjft.service.markerinfo.MarkerInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户App  - 高德地图标注点信息")
@RestController
@RequestMapping("/lghjft/bsdt/marker-info")
@Validated
public class markerinfoAppController {
    @Resource
    private MarkerInfoService markerInfoService;
    @GetMapping("/get")
    @Operation(summary = "获得高德地图标注点信息")
    @PreAuthorize("isAuthenticated()")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<MarkerInfoRespVO> getMarkerInfo(@RequestParam("id") Long id) {
        MarkerInfoDO markerInfo = markerInfoService.getMarkerInfo(id);
        return success(BeanUtils.toBean(markerInfo, MarkerInfoRespVO.class));
    }

    @GetMapping("/list-page")
    @Operation(summary = "获得高德地图标注点信息分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<MarkerInfoRespVO>> getMarkerInfoPage(@Valid MarkerInfoPageReqVO pageReqVO) {
        PageResult<MarkerInfoDO> pageResult = markerInfoService.getMarkerInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MarkerInfoRespVO.class));
    }


}

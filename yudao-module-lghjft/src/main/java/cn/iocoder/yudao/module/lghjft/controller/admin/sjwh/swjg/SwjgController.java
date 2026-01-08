package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swjg.SwjgDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.swjg.SwjgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 税务机关")
@RestController
@RequestMapping("/dm/swjg")
@Validated
public class SwjgController {

    @Resource
    private SwjgService swjgService;


    @PutMapping("/update")
    @Operation(summary = "更新税务机关")
    @PreAuthorize("@ss.hasPermission('dm:swjg:update')")
    public CommonResult<Boolean> updateSwjg(@Valid @RequestBody SwjgSaveReqVO updateReqVO) {
        swjgService.updateSwjg(updateReqVO);
        return success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获得税务机关列表")
    @PreAuthorize("@ss.hasPermission('dm:swjg:query')")
    public CommonResult<List<SwjgRespVO>> getSwjgList(@Valid SwjgListReqVO listReqVO) {
        List<SwjgDO> list = swjgService.getSwjgList(listReqVO);
        return success(BeanUtils.toBean(list, SwjgRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得税务机关")
    @Parameter(name = "swjgDm", description = "机关代码", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('dm:swjg:query')")
    public CommonResult<SwjgRespVO> getSwjg(@RequestParam("swjgDm") String swjgDm) {
        SwjgDO swjg = swjgService.getSwjg(swjgDm);
        return success(BeanUtils.toBean(swjg, SwjgRespVO.class));
    }



}
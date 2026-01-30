package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.bbfb.BbfbService;
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

@Tag(name = "管理后台 - 版本发布")
@RestController
@RequestMapping("/lghjft/nrgl/bbfb")
@Validated
public class BbfbController {

    @Resource
    private BbfbService bbfbService;

    @PostMapping("/create")
    @Operation(summary = "创建版本发布")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:create')")
    public CommonResult<Long> createBbfb(@Valid @RequestBody BbfbCreateReqVO createReqVO) {
        return success(bbfbService.createBbfb(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新版本发布")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:update')")
    public CommonResult<Boolean> updateBbfb(@Valid @RequestBody BbfbUpdateReqVO updateReqVO) {
        bbfbService.updateBbfb(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除版本发布")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:delete')")
    public CommonResult<Boolean> deleteBbfb(@RequestParam("id") Long id) {
        bbfbService.deleteBbfb(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得版本发布")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:query')")
    public CommonResult<BbfbRespVO> getBbfb(@RequestParam("id") Long id) {
        BbfbDO bbfb = bbfbService.getBbfb(id);
        return success(BeanUtils.toBean(bbfb, BbfbRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得版本发布列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:query')")
    public CommonResult<List<BbfbRespVO>> getBbfbList(@Valid BbfbListReqVO listReqVO) {
        List<BbfbDO> list = bbfbService.getBbfbList(listReqVO);
        return success(BeanUtils.toBean(list, BbfbRespVO.class));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布版本")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-bbfb:update')")
    public CommonResult<Boolean> publish(@RequestParam("id") Long id) {
        bbfbService.publish(id);
        return success(true);
    }

    @GetMapping("/public/list")
    @Operation(summary = "获得公开版本发布列表")
    public CommonResult<List<BbfbRespVO>> getPublicBbfbList() {
        List<BbfbDO> list = bbfbService.getPublicBbfbList();
        return success(BeanUtils.toBean(list, BbfbRespVO.class));
    }

}

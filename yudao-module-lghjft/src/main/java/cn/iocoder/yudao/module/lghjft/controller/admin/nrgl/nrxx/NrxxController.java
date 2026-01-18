package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.nrxx.NrxxDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.nrxx.NrxxService;
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

@Tag(name = "管理后台 - 内容管理")
@RestController
@RequestMapping("/lghjft/nrgl/nrxx")
@Validated
public class NrxxController {

    @Resource
    private NrxxService nrService;

    @PostMapping("/create")
    @Operation(summary = "创建内容管理")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-nrxx:create')")
    public CommonResult<Long> createNrxx(@Valid @RequestBody NrxxCreateReqVO createReqVO) {
        return success(nrService.createNrxx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新内容管理")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-nrxx:update')")
    public CommonResult<Boolean> updateNrxx(@Valid @RequestBody NrxxUpdateReqVO updateReqVO) {
        nrService.updateNrxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除内容管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-nrxx:delete')")
    public CommonResult<Boolean> deleteNrxx(@RequestParam("id") Long id) {
        nrService.deleteNrxx(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得内容管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-nrxx:query')")
    public CommonResult<NrxxRespVO> getNrxx(@RequestParam("id") Long id) {
        NrxxDO nr = nrService.getNrxx(id);
        return success(BeanUtils.toBean(nr, NrxxRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得内容管理列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-nrxx:query')")
    public CommonResult<List<NrxxRespVO>> getNrxxList(@Valid NrxxListReqVO listReqVO) {
        List<NrxxDO> list = nrService.getNrxxList(listReqVO);
        return success(BeanUtils.toBean(list, NrxxRespVO.class));
    }

}

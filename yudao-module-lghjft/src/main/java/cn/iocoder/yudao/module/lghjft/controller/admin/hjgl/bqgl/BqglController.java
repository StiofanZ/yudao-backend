package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhDmHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhHjBqxxDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.bqgl.BqglService;
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

@Tag(name = "管理后台 - 标签管理")
@RestController
@RequestMapping("/lghjft/hjgl/bqgl")
@Validated
public class BqglController {

    @Resource
    private BqglService bqglService;

    @PostMapping("/create-bqdm")
    @Operation(summary = "创建标签")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:create')")
    public CommonResult<String> createBqdm(@Valid @RequestBody BqglCreateReqVO createReqVO) {
        return success(bqglService.createBqdm(createReqVO));
    }

    @PutMapping("/update-bqdm")
    @Operation(summary = "更新标签")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:update')")
    public CommonResult<Boolean> updateBqdm(@Valid @RequestBody BqglUpdateReqVO updateReqVO) {
        bqglService.updateBqdm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete-bqdm")
    @Operation(summary = "删除标签")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:delete')")
    public CommonResult<Boolean> deleteBqdm(@RequestParam("id") String id) {
        bqglService.deleteBqdm(id);
        return success(true);
    }

    @GetMapping("/get-bqdm")
    @Operation(summary = "获得标签")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:query')")
    public CommonResult<BqglRespVO> getBqdm(@RequestParam("id") String id) {
        GhDmHjBqDO bqgl = bqglService.getBqdm(id);
        return success(BeanUtils.toBean(bqgl, BqglRespVO.class));
    }

    @GetMapping("/list-bqdm")
    @Operation(summary = "获得标签列表")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:query')")
    public CommonResult<PageResult<BqglRespVO>> listBqdm(@Valid BqglPageReqVO pageReqVO) {
        PageResult<BqglRespVO> pageResult = bqglService.listBqdm(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/list-hjxx")
    @Operation(summary = "获得户籍信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:query')")
    public CommonResult<PageResult<BqglHjxxRespVO>> listHjxx(@Valid BqglHjxxPageReqVO pageReqVO) {
        PageResult<BqglHjxxRespVO> pageResult = bqglService.listHjxx(pageReqVO, null);
        return success(pageResult);
    }

    @PostMapping("/save-hjxx")
    @Operation(summary = "保存户籍标签")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:create')")
    public CommonResult<Boolean> saveHjxx(@Valid @RequestBody BqglHjxxSaveReqVO saveReqVO) {
        bqglService.saveHjxx(saveReqVO);
        return success(true);
    }

    @GetMapping("/get-hjxx")
    @Operation(summary = "获得户籍标签")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-bqgl:query')")
    public CommonResult<List<String>> getHjxx(@RequestParam("djxh") String djxh) {
        List<GhHjBqxxDO> list = bqglService.getHjxx(djxh);
        // Extract bqId list
        List<String> bqIds = BeanUtils.toBean(list, GhHjBqxxDO.class).stream().map(GhHjBqxxDO::getBqId).toList();
        return success(bqIds);
    }

}

package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhj.GhHjDO;
import cn.iocoder.yudao.module.lghjft.service.hj.ghhj.GhHjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 基层账户空需维护对象")
@RestController
@RequestMapping("/lghjft/gh-hj")
@Validated
public class GhHjController {

    @Resource
    private GhHjService ghHjService;

    @PostMapping("/create")
    @Operation(summary = "创建基层账户空需维护对象")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-hj:create')")
    public CommonResult<String> createGhHj(@Valid @RequestBody GhHjCreateReqVO createReqVO) {
        return success(ghHjService.createGhHj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基层账户空需维护对象")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-hj:update')")
    public CommonResult<Boolean> updateGhHj(@Valid @RequestBody GhHjUpdateReqVO updateReqVO) {
        ghHjService.updateGhHj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基层账户空需维护对象")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:gh-hj:delete')")
    public CommonResult<Boolean> deleteGhHj(@RequestParam("id") String id) {
        ghHjService.deleteGhHj(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基层账户空需维护对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-hj:query')")
    public CommonResult<GhHjResVO> getGhHj(@RequestParam("id") String id) {
        GhHjDO ghHj = ghHjService.getGhHj(id);
        return success(BeanUtils.toBean(ghHj, GhHjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基层账户空需维护对象分页")
    @PreAuthorize("@ss.hasPermission('lghjft:gh-hj:query')")
    public CommonResult<PageResult<GhHjResVO>> getGhHjPage(@Valid GhHjPageReqVO pageReqVO) {
        PageResult<GhHjDO> pageResult = ghHjService.getGhHjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhHjResVO.class));
    }

}

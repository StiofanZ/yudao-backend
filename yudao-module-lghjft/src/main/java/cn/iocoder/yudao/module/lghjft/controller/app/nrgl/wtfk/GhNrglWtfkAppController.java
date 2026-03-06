package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.wtfk;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.wtfk.GhNrglWtfkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "APP - 问题反馈")
@RestController
@RequestMapping("/lghjft/nrgl/wtfk")
@Validated
public class GhNrglWtfkAppController {

    @Resource
    private GhNrglWtfkService wtfkService;

    @PutMapping("/handle-process")
    @Operation(summary = "处理问题反馈")
    public CommonResult<Boolean> handleProcess(@Valid @RequestBody GhNrglWtfkClReqVO handleReqVO) {
        wtfkService.handleProcess(handleReqVO);
        return success(true);
    }

    @GetMapping("/log/list")
    @Operation(summary = "获得问题反馈处理明细列表")
    @Parameter(name = "wtfkId", description = "问题反馈ID", required = true)
    public CommonResult<List<GhNrglWtfkClmxRespVO>> getGhNrglWtfkClmxList(@RequestParam("wtfkId") Long wtfkId) {
        return success(wtfkService.getGhNrglWtfkClmxList(wtfkId));
    }

    @PostMapping("/create")
    @Operation(summary = "创建问题反馈")
    public CommonResult<Long> createGhNrglWtfk(@Valid @RequestBody GhNrglWtfkSaveReqVO createReqVO) {
        return success(wtfkService.createGhNrglWtfk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题反馈")
    public CommonResult<Boolean> updateGhNrglWtfk(@Valid @RequestBody GhNrglWtfkSaveReqVO updateReqVO) {
        wtfkService.updateGhNrglWtfk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题反馈")
    @Parameters({
            @Parameter(name = "id", description = "编号", required = true),
            @Parameter(name = "isAdminView", description = "是否管理端视图")
    })
    public CommonResult<Boolean> deleteGhNrglWtfk(@RequestParam("id") Long id,
                                                  @RequestParam(value = "isAdminView", defaultValue = "false") Boolean isAdminView) {
        wtfkService.deleteGhNrglWtfk(id, isAdminView);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除问题反馈")
    public CommonResult<Boolean> deleteGhNrglWtfkList(@RequestParam("ids") List<Long> ids,
                                                      @RequestParam(value = "isAdminView", defaultValue = "false") Boolean isAdminView) {
        wtfkService.deleteGhNrglWtfkListByIds(ids, isAdminView);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题反馈详情")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<GhNrglWtfkRespVO> getGhNrglWtfk(@RequestParam("id") Long id) {
        return success(wtfkService.getGhNrglWtfkDetail(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题反馈分页")
    public CommonResult<PageResult<GhNrglWtfkRespVO>> getGhNrglWtfkPage(@Valid GhNrglWtfkPageReqVO pageReqVO) {
        pageReqVO.setYhId(SecurityFrameworkUtils.getLoginUserId());
        pageReqVO.setIsAdminView(false);
        PageResult<GhNrglWtfkDO> pageResult = wtfkService.getGhNrglWtfkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GhNrglWtfkRespVO.class));
    }
}

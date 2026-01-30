package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg;

import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.infra.api.websocket.WebSocketSenderApi;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.tzgg.TzggDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.tzgg.TzggService;
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

@Tag(name = "管理后台 - 通知公告")
@RestController
@RequestMapping("/lghjft/xxzx/tzgg")
@Validated
public class TzggController {

    @Resource
    private TzggService tzggService;

    @Resource
    private WebSocketSenderApi webSocketSenderApi;

    @PostMapping("/create")
    @Operation(summary = "创建通知公告")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:create')")
    public CommonResult<Long> createTzgg(@Valid @RequestBody TzggSaveReqVO createReqVO) {
        Long noticeId = tzggService.createTzgg(createReqVO);
        return success(noticeId);
    }

    @PutMapping("/update")
    @Operation(summary = "修改通知公告")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:update')")
    public CommonResult<Boolean> updateTzgg(@Valid @RequestBody TzggSaveReqVO updateReqVO) {
        tzggService.updateTzgg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通知公告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:delete')")
    public CommonResult<Boolean> deleteTzgg(@RequestParam("id") Long id) {
        tzggService.deleteTzgg(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除通知公告")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:delete')")
    public CommonResult<Boolean> deleteTzggList(@RequestParam("ids") List<Long> ids) {
        tzggService.deleteTzggList(ids);
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得通知公告分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:query')")
    public CommonResult<PageResult<TzggRespVO>> getTzggPage(@Validated TzggPageReqVO pageReqVO) {
        PageResult<TzggDO> pageResult = tzggService.getTzggPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TzggRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得通知公告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:query')")
    public CommonResult<TzggRespVO> getTzgg(@RequestParam("id") Long id) {
        TzggDO tzgg = tzggService.getTzgg(id);
        return success(BeanUtils.toBean(tzgg, TzggRespVO.class));
    }

    @PostMapping("/push")
    @Operation(summary = "推送通知公告", description = "只发送给 websocket 连接在线的用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-tzgg:update')")
    public CommonResult<Boolean> push(@RequestParam("id") Long id) {
        TzggDO tzgg = tzggService.getTzgg(id);
        Assert.notNull(tzgg, "公告不能为空");
        // 通过 websocket 推送给在线的用户
        webSocketSenderApi.sendObject(UserTypeEnum.ADMIN.getValue(), "tzgg-push", tzgg);
        return success(true);
    }

}

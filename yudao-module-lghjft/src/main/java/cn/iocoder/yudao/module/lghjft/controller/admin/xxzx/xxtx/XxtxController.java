package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSendReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx.XxtxService;
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

@Tag(name = "管理后台 - 消息提醒")
@RestController
@RequestMapping("/lghjft/xxzx/xxtx")
@Validated
public class XxtxController {

    @Resource
    private XxtxService xxtxService;

    @PostMapping("/create")
    @Operation(summary = "创建消息")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:create')")
    public CommonResult<Long> createMessage(@Valid @RequestBody XxtxMessageSaveReqVO createReqVO) {
        Long messageId = xxtxService.createMessage(createReqVO);
        return success(messageId);
    }

    @PutMapping("/update")
    @Operation(summary = "修改消息")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:update')")
    public CommonResult<Boolean> updateMessage(@Valid @RequestBody XxtxMessageSaveReqVO updateReqVO) {
        xxtxService.updateMessage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除消息")
    @Parameter(name = "id", description = "消息ID", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:delete')")
    public CommonResult<Boolean> deleteMessage(@RequestParam("id") Long id) {
        xxtxService.deleteMessage(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除消息")
    @Parameter(name = "ids", description = "消息ID列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:delete')")
    public CommonResult<Boolean> deleteMessageList(@RequestParam("ids") List<Long> ids) {
        xxtxService.deleteMessageList(ids);
        return success(true);
    }

    @PostMapping("/send")
    @Operation(summary = "发送消息")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:send')")
    public CommonResult<Boolean> sendMessage(@Valid @RequestBody XxtxMessageSendReqVO sendReqVO) {
        xxtxService.sendMessage(sendReqVO);
        return success(true);
    }

    @PutMapping("/recall")
    @Operation(summary = "撤回消息")
    @Parameter(name = "id", description = "消息ID", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:update')")
    public CommonResult<Boolean> recallMessage(@RequestParam("id") Long id) {
        xxtxService.recallMessage(id);
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获取消息分页列表")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<PageResult<XxtxMessageRespVO>> getMessagePage(@Validated XxtxMessagePageReqVO pageReqVO) {
        PageResult<XxtxMessageDO> pageResult = xxtxService.getMessagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XxtxMessageRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取消息详情")
    @Parameter(name = "id", description = "消息ID", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<XxtxMessageRespVO> getMessage(@RequestParam("id") Long id) {
        return success(xxtxService.getMessageDetail(id));
    }

    @PutMapping("/mark-read")
    @Operation(summary = "标记消息为已读")
    @Parameter(name = "messageId", description = "消息ID", required = true, example = "1024")
    @Parameter(name = "userId", description = "用户ID", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:update')")
    public CommonResult<Boolean> markMessageAsRead(@RequestParam("messageId") Long messageId, @RequestParam("userId") Long userId) {
        xxtxService.markMessageAsRead(messageId, userId);
        return success(true);
    }

}

package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessageMyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessageRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx.XxtxMessageService;
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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 我的站内信")
@RestController
@RequestMapping("/lghjft/xxzx/xxtx-message")
@Validated
public class XxtxMessageController {

    @Resource
    private XxtxMessageService xxtxMessageService;

    // ========== 管理所有的站内信 ==========

    @GetMapping("/get")
    @Operation(summary = "获得站内信")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<XxtxMessageRespVO> getXxtxMessage(@RequestParam("id") Long id) {
        XxtxMessageDO message = xxtxMessageService.getXxtxMessage(id);
        return success(BeanUtils.toBean(message, XxtxMessageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得站内信分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<PageResult<XxtxMessageRespVO>> getXxtxMessagePage(@Valid XxtxMessagePageReqVO pageVO) {
        PageResult<XxtxMessageDO> pageResult = xxtxMessageService.getXxtxMessagePage(pageVO);
        return success(BeanUtils.toBean(pageResult, XxtxMessageRespVO.class));
    }

    // ========== 查看自己的站内信 ==========

    @GetMapping("/my-page")
    @Operation(summary = "获得我的站内信分页")
    public CommonResult<PageResult<XxtxMessageRespVO>> getMyMyXxtxMessagePage(@Valid XxtxMessageMyPageReqVO pageVO) {
        PageResult<XxtxMessageDO> pageResult = xxtxMessageService.getMyMyXxtxMessagePage(pageVO,
                getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(BeanUtils.toBean(pageResult, XxtxMessageRespVO.class));
    }

    @PutMapping("/update-read")
    @Operation(summary = "标记站内信为已读")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<Boolean> updateXxtxMessageRead(@RequestParam("ids") List<Long> ids) {
        xxtxMessageService.updateXxtxMessageRead(ids, getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    @PutMapping("/update-all-read")
    @Operation(summary = "标记所有站内信为已读")
    public CommonResult<Boolean> updateAllXxtxMessageRead() {
        xxtxMessageService.updateAllXxtxMessageRead(getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(Boolean.TRUE);
    }

    @GetMapping("/get-unread-list")
    @Operation(summary = "获取当前用户的最新站内信列表，默认 10 条")
    @Parameter(name = "size", description = "10")
    public CommonResult<List<XxtxMessageRespVO>> getUnreadXxtxMessageList(
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<XxtxMessageDO> list = xxtxMessageService.getUnreadXxtxMessageList(
                getLoginUserId(), UserTypeEnum.ADMIN.getValue(), size);
        return success(BeanUtils.toBean(list, XxtxMessageRespVO.class));
    }

    @GetMapping("/get-unread-count")
    @Operation(summary = "获得当前用户的未读站内信数量")
    @ApiAccessLog(enable = false) // 由于前端会不断轮询该接口，记录日志没有意义
    public CommonResult<Long> getUnreadXxtxMessageCount() {
        return success(xxtxMessageService.getUnreadXxtxMessageCount(
                getLoginUserId(), UserTypeEnum.ADMIN.getValue()));
    }

}

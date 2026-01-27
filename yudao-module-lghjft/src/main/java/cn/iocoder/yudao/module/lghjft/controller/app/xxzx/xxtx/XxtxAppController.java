package cn.iocoder.yudao.module.lghjft.controller.app.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageRespVO;
import cn.iocoder.yudao.module.lghjft.controller.app.xxzx.xxtx.vo.XxtxMessageAppRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageReceiverDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx.XxtxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 消息提醒")
@RestController
@RequestMapping("/lghjft/xxzx/xxtx")
@Validated
public class XxtxAppController {

    @Resource
    private XxtxService xxtxService;


    @GetMapping("/list-page")
    @Operation(summary = "获取消息分页列表")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<XxtxMessageRespVO>> getMessagePage(@Validated XxtxMessagePageReqVO pageReqVO) {
        pageReqVO.setReceiverId(SecurityFrameworkUtils.getLoginUserId());
        PageResult<XxtxMessageReceiverDO> receiverDOPageResult = xxtxService.getMessageReceiverPage(pageReqVO);
        PageResult<XxtxMessageAppRespVO> appRespVOPageResult = new PageResult<>();
        appRespVOPageResult.setTotal(receiverDOPageResult.getTotal());
        appRespVOPageResult.setList(receiverDOPageResult.getList().stream().map(receiverDO -> {
            XxtxMessageAppRespVO respVO = new XxtxMessageAppRespVO();
            BeanUtils.copyProperties(receiverDO, respVO);
            XxtxMessageDO messageDO = xxtxService.getMessage(receiverDO.getMessageId());
            BeanUtils.copyProperties(messageDO, respVO);
            respVO.setReadStatus(respVO.getReadStatus());
            return respVO;
        }).toList());
        return success(BeanUtils.toBean(appRespVOPageResult, XxtxMessageRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取消息详情")
    @Parameter(name = "id", description = "消息ID", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<XxtxMessageRespVO> getMessage(@RequestParam("id") Long id) {
        return success(xxtxService.getMessageDetail(id));
    }

    @PutMapping("/mark-read")
    @Operation(summary = "标记消息为已读")
    @Parameter(name = "messageId", description = "消息ID", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> markMessageAsRead(@RequestParam("messageId") Long messageId) {
        xxtxService.markMessageAsRead(messageId, SecurityFrameworkUtils.getLoginUserId());
        return success(true);
    }

}

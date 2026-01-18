package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplatePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplateRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplateSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplateSendReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx.XxtxSendService;
import cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx.XxtxTemplateService;
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

@Tag(name = "管理后台 - 站内信模版")
@RestController
@RequestMapping("/lghjft/xxzx/xxtx-template")
@Validated
public class XxtxTemplateController {

    @Resource
    private XxtxTemplateService xxtxTemplateService;

    @Resource
    private XxtxSendService xxtxSendService;

    @PostMapping("/create")
    @Operation(summary = "创建站内信模版")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:create')")
    public CommonResult<Long> createXxtxTemplate(@Valid @RequestBody XxtxTemplateSaveReqVO createReqVO) {
        return success(xxtxTemplateService.createXxtxTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新站内信模版")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:update')")
    public CommonResult<Boolean> updateXxtxTemplate(@Valid @RequestBody XxtxTemplateSaveReqVO updateReqVO) {
        xxtxTemplateService.updateXxtxTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除站内信模版")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:delete')")
    public CommonResult<Boolean> deleteXxtxTemplate(@RequestParam("id") Long id) {
        xxtxTemplateService.deleteXxtxTemplate(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除站内信模版")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:delete')")
    public CommonResult<Boolean> deleteXxtxTemplateList(@RequestParam("ids") List<Long> ids) {
        xxtxTemplateService.deleteXxtxTemplateList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得站内信模版")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<XxtxTemplateRespVO> getXxtxTemplate(@RequestParam("id") Long id) {
        XxtxTemplateDO template = xxtxTemplateService.getXxtxTemplate(id);
        return success(BeanUtils.toBean(template, XxtxTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得站内信模版分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:query')")
    public CommonResult<PageResult<XxtxTemplateRespVO>> getXxtxTemplatePage(@Valid XxtxTemplatePageReqVO pageVO) {
        PageResult<XxtxTemplateDO> pageResult = xxtxTemplateService.getXxtxTemplatePage(pageVO);
        return success(BeanUtils.toBean(pageResult, XxtxTemplateRespVO.class));
    }

    @PostMapping("/send-xxtx")
    @Operation(summary = "发送站内信")
    @PreAuthorize("@ss.hasPermission('lghjft:xxzx-xxtx:send-xxtx')")
    public CommonResult<Long> sendXxtx(@Valid @RequestBody XxtxTemplateSendReqVO sendReqVO) {
        if (UserTypeEnum.MEMBER.getValue().equals(sendReqVO.getUserType())) {
            return success(xxtxSendService.sendSingleXxtxToMember(sendReqVO.getUserId(),
                    sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
        } else {
            return success(xxtxSendService.sendSingleXxtxToAdmin(sendReqVO.getUserId(),
                    sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
        }
    }
}

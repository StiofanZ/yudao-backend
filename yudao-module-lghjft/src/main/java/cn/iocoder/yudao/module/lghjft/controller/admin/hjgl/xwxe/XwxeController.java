package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo.XwxePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo.XwxeResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.xwxe.XwxeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小微小额")
@RestController
@RequestMapping("/lghjft/hjgl/xwxe")
@Validated
public class XwxeController {

    @Resource
    private XwxeService xwxeService;

    @GetMapping("/page")
    @Operation(summary = "获得小微小额分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-xwxe:query')")
    public CommonResult<PageResult<XwxeResVO>> getPage(@Valid XwxePageReqVO reqVO) {
        PageResult<GhHjJcxxDO> pageResult = xwxeService.getPage(reqVO);
        return success(BeanUtils.toBean(pageResult, XwxeResVO.class));
    }
}

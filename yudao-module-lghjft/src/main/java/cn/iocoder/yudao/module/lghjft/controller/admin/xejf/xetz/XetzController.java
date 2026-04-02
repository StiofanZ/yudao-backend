package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz.XetzDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xetz.XetzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 小额台账")
@RestController
@RequestMapping("/lghjft/xejf/xetz")
@Validated
public class XetzController {

    @Resource
    private XetzService xetzService;

    @GetMapping("/get")
    @Operation(summary = "获得小额台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf:xetz:query')")
    public CommonResult<XetzResVO> get(@RequestParam("id") String id) {
        XetzDO data = xetzService.getXetz(id);
        return success(BeanUtils.toBean(data, XetzResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额台账分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf:xetz:query')")
    public CommonResult<PageResult<XetzResVO>> page(@Valid XetzPageReqVO pageReqVO) {
        PageResult<XetzDO> pageResult = xetzService.getXetzPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XetzResVO.class));
    }
}

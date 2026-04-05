package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb.XebfzbDO;
import cn.iocoder.yudao.module.lghjft.service.xejf.xebfzb.XebfzbService;
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

@Tag(name = "管理后台 - 小额拨付占比")
@RestController
@RequestMapping("/lghjft/xejf/xebfzb")
@Validated
public class XebfzbController {

    @Resource
    private XebfzbService xebfzbService;

    @PostMapping("/create")
    @Operation(summary = "创建小额拨付占比")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:create')")
    public CommonResult<String> create(@Valid @RequestBody XebfzbSaveReqVO createReqVO) {
        return success(xebfzbService.createXebfzb(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小额拨付占比")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody XebfzbSaveReqVO updateReqVO) {
        xebfzbService.updateXebfzb(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小额拨付占比")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") String id) {
        xebfzbService.deleteXebfzb(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除小额拨付占比")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:delete')")
    public CommonResult<Boolean> deleteList(@RequestParam("ids") List<String> ids) {
        xebfzbService.deleteXebfzbListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小额拨付占比")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:query')")
    public CommonResult<XebfzbResVO> get(@RequestParam("id") String id) {
        XebfzbDO data = xebfzbService.getXebfzb(id);
        return success(BeanUtils.toBean(data, XebfzbResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小额拨付占比分页")
    @PreAuthorize("@ss.hasPermission('lghjft:xejf-xebfzb:query')")
    public CommonResult<PageResult<XebfzbResVO>> page(@Valid XebfzbPageReqVO pageReqVO) {
        PageResult<XebfzbDO> pageResult = xebfzbService.getXebfzbPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XebfzbResVO.class));
    }
}

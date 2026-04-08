package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs.JfjsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 经费结算")
@RestController
@RequestMapping("/lghjft/jfcl/jfjs")
@Validated
public class JfjsController {

    @Resource
    private JfjsService jfjsService;

    @GetMapping("/page")
    @Operation(summary = "获得经费结算分页")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:query')")
    public CommonResult<PageResult<JfjsResVO>> getJfjsPage(@Valid JfjsPageReqVO pageReqVO) {
        PageResult<JfclJfjsDO> pageResult = jfjsService.getJfjsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JfjsResVO.class));
    }

    /**
     * 工会经费结算
     * v1: POST /jfcl/jfjs — queries gh_jf WHERE rkrq BETWEEN dates AND jsbj='N',
     *     sets jsbj='W' if jmse!=0 else 'Y', batch UPDATE gh_jf
     */
    @PostMapping("/settle")
    @Operation(summary = "工会经费结算")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-jfjs:update')")
    public CommonResult<Boolean> settleJfjs(@Valid @RequestBody JfjsPageReqVO reqVO) {
        jfjsService.settleJfjs(reqVO);
        return success(true);
    }
}

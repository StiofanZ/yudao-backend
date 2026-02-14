package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.nsrxx.NsrxxQueryReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.nsrxx.NsrxxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 户籍管理/基础信息")
@RestController
@RequestMapping("/lghjft/hjgl/jcxx")
@Validated
public class JcxxController {

    @Resource
    private JcxxService jcxxService;

    @Resource
    private NsrxxService nsrxxService;

    @PostMapping("/create")
    @Operation(summary = "创建户籍管理/基础信息")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:create')")
    public CommonResult<String> createJcxx(@Valid @RequestBody JcxxCreateReqVO createReqVO) {
        return success(jcxxService.createJcxx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新户籍管理/基础信息")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:update')")
    public CommonResult<Boolean> updateJcxx(@Valid @RequestBody JcxxUpdateReqVO updateReqVO) {
        jcxxService.updateJcxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除户籍管理/基础信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:delete')")
    public CommonResult<Boolean> deleteJcxx(@RequestParam("id") String id) {
        jcxxService.deleteJcxx(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得户籍管理/基础信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:query')")
    public CommonResult<JcxxRespVO> getJcxx(@RequestParam("id") String id) {
        GhHjJcxxDO jcxx = jcxxService.getJcxx(id);
        return success(BeanUtils.toBean(jcxx, JcxxRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得户籍管理/基础信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:query')")
    public CommonResult<PageResult<JcxxRespVO>> getJcxxPage(@Valid JcxxPageReqVO pageReqVO) {
        PageResult<GhHjJcxxDO> pageResult = jcxxService.getJcxxPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JcxxRespVO.class));
    }

    @GetMapping("/query-nsrxx")
    @Operation(summary = "查询纳税人信息")
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:query')")
    public CommonResult<List<NsrxxRespVO>> queryNsrxx(@Valid NsrxxQueryReqVO queryReqVO) {
        List<NsrxxDO> nsrxxList = nsrxxService.getNsrxxList(queryReqVO.getKeyword());
        return success(nsrxxList.stream().map(nsrxx -> {
            NsrxxRespVO respVO = BeanUtils.toBean(nsrxx, NsrxxRespVO.class);
            // Check if exists in local HJ table
            GhHjJcxxDO existingHj = jcxxService.getJcxx(nsrxx.getDjxh());
            if (existingHj != null) {
                respVO.setExistsInHj(true);
                // In a real scenario, we might want to fetch dept name, but here we just return ID or skip
                // respVO.setDeptName(existingHj.getDeptId()); 
            } else {
                respVO.setExistsInHj(false);
            }
            return respVO;
        }).collect(Collectors.toList()));
    }

    @GetMapping("/get-tax-info")
    @Operation(summary = "从税务机关获取最新户籍信息")
    @Parameter(name = "djxh", description = "登记序号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:hjgl-jcxx:update')")
    public CommonResult<JcxxRespVO> getTaxInfo(@RequestParam("djxh") String djxh) {
        NsrxxDO nsrxx = nsrxxService.getNsrxx(djxh);
        if (nsrxx == null) {
            return CommonResult.error(404, "未找到对应的税务信息");
        }
        // Map NsrxxDO to JcxxRespVO (or GhHjJcxxDO then to VO)
        JcxxRespVO respVO = BeanUtils.toBean(nsrxx, JcxxRespVO.class);
        return success(respVO);
    }

}

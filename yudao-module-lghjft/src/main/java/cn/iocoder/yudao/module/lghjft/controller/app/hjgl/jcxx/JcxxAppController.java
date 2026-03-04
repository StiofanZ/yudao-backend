package cn.iocoder.yudao.module.lghjft.controller.app.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxRespVO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 户籍管理-基础信息")
@RestController
@RequestMapping("/lghjft/hjgl/jcxx")
@Validated
public class JcxxAppController {

    @Resource
    private JcxxService jcxxService;

    @Resource
    private NsrxxService nsrxxService;

    @GetMapping("/get")
    @Operation(summary = "获得户籍管理/基础信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<JcxxRespVO> getJcxx(@RequestParam("id") String id) {
        GhHjJcxxDO jcxx = jcxxService.getJcxx(id);
        return success(BeanUtils.toBean(jcxx, JcxxRespVO.class));
    }


    @GetMapping("/query-nsrxx")
    @Operation(summary = "查询纳税人信息")
    @PreAuthorize("isAuthenticated()")
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

}

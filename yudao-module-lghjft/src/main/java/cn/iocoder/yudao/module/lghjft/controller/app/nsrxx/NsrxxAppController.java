package cn.iocoder.yudao.module.lghjft.controller.app.nsrxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo.NsrxxPayFormResVO;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 app - 纳税人信息")
@RestController
@RequestMapping("/lghjft/nsrxx")
@Validated
public class NsrxxAppController {
    @Resource
    private NsrxxService nsrxxService;

//查询纳税人单位信息
    @GetMapping("/getByDw")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<NsrxxPayFormResVO> getByShxydm(@RequestParam("shxydm") String shxydm) {
        // 2. 调用Service查询并直接返回数据（不封装多余信息）
        NsrxxPayFormResVO nsrxxPayFormResVO =  nsrxxService.getNsrdwxxByShxydm(shxydm);
        return success(nsrxxPayFormResVO);
    }
}

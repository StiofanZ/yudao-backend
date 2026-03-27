package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl.IGhHjService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 户籍管理Controller
 *
 * @author lyb
 * @date 2026-03-26
 */
@RestController
@RequestMapping("/lghjft/hjgl")
public class GhHjController {

    @Resource
    private IGhHjService ghHjService;

    /**
     * 获取户籍管理详细信息
     */
    @GetMapping("/{djxh}")
    public CommonResult getInfo(@PathVariable String djxh) {
        return CommonResult.success(ghHjService.selectGhHjBydjxh(djxh));
    }
}

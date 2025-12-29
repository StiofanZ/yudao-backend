package cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nsrxx.vo.NsrxxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 纳税人信息")
@RestController
@RequestMapping("/lghjft/nsrxx")
@Validated
public class NsrxxController {

    @Resource
    private NsrxxService nsrxxService;

    @GetMapping("/query")
    @Operation(summary = "根据纳税人识别号查询纳税人信息")
    @Parameter(name = "nsrsbh", description = "纳税人识别号", required = true, example = "91110108551385082Q")
    public CommonResult<NsrxxRespVO> queryNsrxxByNsrsbh(@RequestParam("nsrsbh") String nsrsbh) {
        NsrxxDO nsrxx = nsrxxService.getNsrxxByNsrsbh(nsrsbh);
        return success(BeanUtils.toBean(nsrxx, NsrxxRespVO.class));
    }

}

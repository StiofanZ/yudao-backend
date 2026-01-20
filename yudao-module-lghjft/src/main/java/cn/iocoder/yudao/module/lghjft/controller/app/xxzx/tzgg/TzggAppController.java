package cn.iocoder.yudao.module.lghjft.controller.app.xxzx.tzgg;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.tzgg.TzggDO;
import cn.iocoder.yudao.module.lghjft.service.xxzx.tzgg.TzggService;
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

@Tag(name = "用户 App - 通知公告")
@RestController
@RequestMapping("/lghjft/xxzx/tzgg")
@Validated
public class TzggAppController {

    @Resource
    private TzggService tzggService;

    @GetMapping("/list-page")
    @Operation(summary = "获取通知公告列表")
    public CommonResult<PageResult<TzggRespVO>> getTzggList(@Validated TzggPageReqVO pageReqVO) {
        PageResult<TzggDO> pageResult = tzggService.getTzggPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TzggRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得通知公告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<TzggRespVO> getTzgg(@RequestParam("id") Long id) {
        TzggDO tzgg = tzggService.getTzgg(id);
        return success(BeanUtils.toBean(tzgg, TzggRespVO.class));
    }

}

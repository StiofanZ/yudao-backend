package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglRespVO;
import cn.iocoder.yudao.module.lghjft.service.hj.bqgl.BqglService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 标签管理")
@RestController
@RequestMapping("/lghjft/hj/bqgl")
@Validated
public class BqglController {

    @Resource
    private BqglService bqglService;

    @GetMapping("/getBqxx")
    @Operation(summary = "查询所有标签信息")
    public CommonResult<List<BqglRespVO>> getBqxx() {
        Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
        return success(bqglService.getBqxx(deptId));
    }

    @PostMapping("/createBqxx")
    @Operation(summary = "创建标签")
    public CommonResult<String> createBqxx(@Valid @RequestBody BqglCreateReqVO createReqVO) {
        return success(bqglService.createBqxx(createReqVO));
    }

    @DeleteMapping("/deleteBqxx")
    @Operation(summary = "删除标签")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteBqxx(@RequestParam("id") String id) {
        bqglService.deleteBqxx(id);
        return success(true);
    }

    @GetMapping("/getHjxx")
    @Operation(summary = "查询标签关联的户籍信息")
    public CommonResult<PageResult<BqglHjxxRespVO>> getHjxx(@Valid BqglHjxxPageReqVO pageReqVO) {
        Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
        return success(bqglService.getHjxxPage(pageReqVO, deptId));
    }

    @PostMapping("/saveHjxx")
    @Operation(summary = "保存户籍标签信息")
    public CommonResult<Boolean> saveHjxx(@Valid @RequestBody BqglHjxxSaveReqVO saveReqVO) {
        bqglService.saveHjxx(saveReqVO);
        return success(true);
    }
}

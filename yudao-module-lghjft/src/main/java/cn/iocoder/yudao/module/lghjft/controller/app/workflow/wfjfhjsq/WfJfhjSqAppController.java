package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfjfhjsq;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfjfhjsq.vo.WfJfhjSqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfjfhjsq.WfJfhjSqDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wfjfhjsq.WfJfhjSqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfjfhjsq.vo.WfjfhjsqAppPageReqVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "app - 工会经费缓缴申请")
@RestController
@RequestMapping("/lghjft/workflow/wfjfhjsq")
@Validated
public class WfJfhjSqAppController {

    @Resource
    private WfJfhjSqService wfJfhjSqService;

    @PostMapping("/create")
    @Operation(summary = "创建工会经费缓缴申请")
//    @PreAuthorize("@ss.hasPermission('lghjft:wf-jfhj-sq:create')")
    public CommonResult<Long> createWfJfhjSq(@Valid @RequestBody WfJfhjSqSaveReqVO createReqVO) {
        return success(wfJfhjSqService.createWfJfhjSq(createReqVO));
    }



    @GetMapping("/get")
    @Operation(summary = "获得工会经费缓缴申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('lghjft:wf-jfhj-sq:query')")
    public CommonResult<WfJfhjSqRespVO> getWfJfhjSq(@RequestParam("id") Long id) {
        WfJfhjSqDO wfJfhjSq = wfJfhjSqService.getWfJfhjSq(id);
        // 核心转换逻辑（最简写法，合并拷贝+赋值）
        WfJfhjSqRespVO respVO = BeanUtils.toBean(wfJfhjSq, WfJfhjSqRespVO.class);
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得经费缓缴申请分页（我的）")
    public CommonResult<PageResult<WfJfhjSqRespVO>> getWfjfhjsqPage(@Valid WfjfhjsqAppPageReqVO pageReqVO) {
        // 1. 获取当前登录用户 ID
        Long loginUserId = getLoginUserId();

        // 2. 调用 Service (注意返回值类型匹配接口定义的 WfJfhjSqDO)
        PageResult<WfJfhjSqDO> pageResult = wfJfhjSqService.getSelfPage(loginUserId, pageReqVO);

        // 3. 转换并返回 (注意 RespVO 的大写拼写)
        return success(BeanUtils.toBean(pageResult, WfJfhjSqRespVO.class));
    }

}
package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzRespVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo.WfHzAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo.WfHzSaveAppReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf.WfHzDO;
import cn.iocoder.yudao.module.lghjft.service.workflow.wfsqhzjs.WfHzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "APP - 工会经费汇总缴纳申请表（主表）")
@RestController
@RequestMapping("/lghjft/wf-hz")
@Validated
public class WfHzAppontroller { // 注意类名拼写，你之前可能是 WfHzAppontroller

    @Resource
    private WfHzService wfHzService;

    // 1. 创建申请
    @PostMapping("/create")
    @Operation(summary = "创建工会经费汇总缴纳申请表（主表）")
    public CommonResult<Long> createWfHz(@Valid @RequestBody WfHzSaveAppReqVO createReqVO) {
        // 注意：这里需要根据你的 SaveAppReqVO 转换成 Service 需要的 SaveReqVO，
        // 如果两个 VO 结构一致，或者 Service 改为接收 AppVO，请根据实际情况调整。
        // 这里假设 Service 接收通用的 SaveReqVO (需要转换) 或者你已经处理好了重载。
        // 为简化展示，这里暂时保留你原有的调用逻辑。
        return success(wfHzService.createWfHz(BeanUtils.toBean(createReqVO, cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO.class)));
    }

    // 2. 获得详情
    @GetMapping("/get")
    @Operation(summary = "获得工会经费汇总缴纳申请表（主表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<WfHzRespVO> getWfHz(@RequestParam("id") Long id) {
        WfHzRespVO wfHz = wfHzService.getDetail(id);
        return success(BeanUtils.toBean(wfHz, WfHzRespVO.class));
    }



    @GetMapping("/page")
    @Operation(summary = "获得工会经费汇总缴纳申请分页（我的）")
    public CommonResult<PageResult<WfHzRespVO>> getWfHzPage(@Valid WfHzAppPageReqVO pageReqVO) {
        // 1. 获取当前登录用户 ID (从 Token 解析)
        Long loginUserId = getLoginUserId();

        // 2. 调用 Service 的 getSelfPage 方法
        PageResult<WfHzDO> pageResult = wfHzService.getSelfPage(loginUserId, pageReqVO);

        // 3. 将 DO 分页结果转换为 RespVO 分页结果并返回
        return success(BeanUtils.toBean(pageResult, WfHzRespVO.class));
    }
}
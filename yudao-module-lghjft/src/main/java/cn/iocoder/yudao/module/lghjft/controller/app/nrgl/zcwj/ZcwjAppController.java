package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcwj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjResVO;
import cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcwj.vo.ZcwjPageAppReqVO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.zcwj.ZcwjService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
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
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "用户 App - 政策文件")
@RestController
@RequestMapping("/lghjft/nrgl/zcwj")
@Validated
public class ZcwjAppController {

    @Resource
    private ZcwjService zcwjService;

    @Resource
    private DeptApi deptApi;

    @GetMapping("/app/list-page")
    @Operation(summary = "获得政策文件列表")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<ZcwjResVO>> getPublishedList(@Valid ZcwjPageAppReqVO reqVO) {
        ZcwjReqVO query = BeanUtils.toBean(reqVO, ZcwjReqVO.class);
        List<ZcwjResVO> result = BeanUtils.toBean(zcwjService.getPublishedList(query), ZcwjResVO.class);
        fillDeptName(result);
        return success(result);
    }

    @GetMapping("/app/get")
    @Operation(summary = "获得政策文件详情")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<ZcwjResVO> getZcwj(@RequestParam("id") Long id) {
        return success(BeanUtils.toBean(zcwjService.getZcwj(id), ZcwjResVO.class));
    }

    @GetMapping("/app/recommend")
    @Operation(summary = "获得政策文件推荐列表")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<ZcwjResVO>> getRecommendList(@RequestParam("id") Long id,
                                                          @RequestParam(value = "limit", required = false) Integer limit) {
        List<ZcwjResVO> result = BeanUtils.toBean(zcwjService.getRecommendList(id, limit), ZcwjResVO.class);
        fillDeptName(result);
        return success(result);
    }

    private void fillDeptName(List<ZcwjResVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(list, ZcwjResVO::getDeptId));
        list.forEach(item -> {
            DeptRespDTO dept = deptMap.get(item.getDeptId());
            if (dept != null) {
                item.setDeptName(dept.getName());
            }
        });
    }
}

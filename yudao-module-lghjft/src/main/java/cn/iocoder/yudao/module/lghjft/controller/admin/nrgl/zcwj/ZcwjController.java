package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 政策文件")
@RestController
@RequestMapping("/lghjft/nrgl/zcwj")
@Validated
public class ZcwjController {

    @Resource
    private ZcwjService zcwjService;

    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建政策文件")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:create')")
    public CommonResult<Long> createZcwj(@Valid @RequestBody ZcwjCreateReqVO createReqVO) {
        return success(zcwjService.createZcwj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策文件")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:update')")
    public CommonResult<Boolean> updateZcwj(@Valid @RequestBody ZcwjUpdateReqVO updateReqVO) {
        zcwjService.updateZcwj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策文件")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:delete')")
    public CommonResult<Boolean> deleteZcwj(@RequestParam("id") Long id) {
        zcwjService.deleteZcwj(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策文件")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:query')")
    public CommonResult<ZcwjResVO> getZcwj(@RequestParam("id") Long id) {
        return success(BeanUtils.toBean(zcwjService.getZcwj(id), ZcwjResVO.class));
    }

    @GetMapping("/list-page")
    @Operation(summary = "获得政策文件分页列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:query')")
    public CommonResult<PageResult<ZcwjResVO>> getZcwjPage(@Validated ZcwjReqVO reqVO) {
        PageResult<ZcwjDO> pageResult = zcwjService.getZcwjPage(reqVO);
        PageResult<ZcwjResVO> result = BeanUtils.toBean(pageResult, ZcwjResVO.class);
        fillDeptName(result.getList());
        return success(result);
    }

    @PutMapping("/publish")
    @Operation(summary = "发布政策文件")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:update')")
    public CommonResult<Boolean> publishZcwj(@RequestParam("id") Long id) {
        zcwjService.publishZcwj(id);
        return success(true);
    }

    @PutMapping("/off-shelf")
    @Operation(summary = "下架政策文件")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcwj:update')")
    public CommonResult<Boolean> offShelfZcwj(@RequestParam("id") Long id,
                                              @RequestParam("reason") String reason) {
        zcwjService.offShelfZcwj(id, reason);
        return success(true);
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

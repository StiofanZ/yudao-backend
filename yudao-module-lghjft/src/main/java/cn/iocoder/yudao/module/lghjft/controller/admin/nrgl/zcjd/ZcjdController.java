package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcwj.ZcwjMapper;
import cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd.ZcjdService;
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
import java.util.Set;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 政策解读")
@RestController
@RequestMapping("/lghjft/nrgl/zcjd")
@Validated
public class ZcjdController {

    @Resource
    private ZcjdService zcjdService;

    @Resource
    private DeptApi deptApi;

    @Resource
    private ZcwjService zcwjService;

    @Resource
    private ZcwjMapper zcwjMapper;

    @PostMapping("/create")
    @Operation(summary = "创建政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:create')")
    public CommonResult<Long> createZcjd(@Valid @RequestBody ZcjdCreateReqVO createReqVO) {
        return success(zcjdService.createZcjd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> updateZcjd(@Valid @RequestBody ZcjdUpdateReqVO updateReqVO) {
        zcjdService.updateZcjd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策解读")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:delete')")
    public CommonResult<Boolean> deleteZcjd(@RequestParam("id") Long id) {
        zcjdService.deleteZcjd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:query')")
    public CommonResult<ZcjdResVO> getZcjd(@RequestParam("id") Long id) {
        ZcjdDO zcjd = zcjdService.getZcjd(id);
        ZcjdResVO result = BeanUtils.toBean(zcjd, ZcjdResVO.class);
        if (result != null) {
            fillDeptName(List.of(result));
            fillLinkedPolicyInfo(List.of(result));
        }
        return success(result);
    }


    @GetMapping("/list-page")
    @Operation(summary = "获得政策解读分页列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:query')")
    public CommonResult<PageResult<ZcjdResVO>> getZcjdPage(@Validated ZcjdReqVO pageReqVO) {
        PageResult<ZcjdDO> pageResult = zcjdService.getZcjdPage(pageReqVO);
        PageResult<ZcjdResVO> result = BeanUtils.toBean(pageResult, ZcjdResVO.class);
        fillDeptName(result.getList());
        fillLinkedPolicyInfo(result.getList());
        return success(result);
    }

    @GetMapping("/published-policy-options")
    @Operation(summary = "获得已发布政策文件选项")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:query')")
    public CommonResult<List<ZcwjResVO>> getPublishedPolicyOptions(
            @RequestParam(value = "keyword", required = false) String keyword) {
        ZcwjReqVO reqVO = new ZcwjReqVO();
        reqVO.setKeyword(keyword);
        return success(BeanUtils.toBean(zcwjService.getPublishedList(reqVO), ZcwjResVO.class));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布政策解读")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> publishZcjd(@RequestParam("id") Long id) {
        zcjdService.publishZcjd(id);
        return success(true);
    }

    @PutMapping("/off-shelf")
    @Operation(summary = "下架政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> offShelfZcjd(@RequestParam("id") Long id, @RequestParam("reason") String reason) {
        zcjdService.offShelfZcjd(id, reason);
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核政策解读")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zcjd:update')")
    public CommonResult<Boolean> auditZcjd(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        zcjdService.auditZcjd(id, status);
        return success(true);
    }

    private void fillDeptName(List<ZcjdResVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(list, ZcjdResVO::getDeptId));
        list.forEach(item -> {
            DeptRespDTO dept = deptMap.get(item.getDeptId());
            if (dept != null) {
                item.setDeptName(dept.getName());
            }
        });
    }

    private void fillLinkedPolicyInfo(List<ZcjdResVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Set<Long> policyIds = convertSet(list, ZcjdResVO::getGlzcId);
        policyIds.remove(null);
        if (policyIds.isEmpty()) {
            return;
        }
        Map<Long, ZcwjDO> policyMap = convertMap(zcwjMapper.selectBatchIds(policyIds), ZcwjDO::getId);
        list.forEach(item -> {
            ZcwjDO policy = policyMap.get(item.getGlzcId());
            if (policy != null) {
                item.setGlzcTitle(policy.getTitle());
                item.setGlzcVersionNo(policy.getVersionNo());
                item.setGlzcAttachmentUrls(policy.getAttachmentUrls());
            }
        });
    }

}

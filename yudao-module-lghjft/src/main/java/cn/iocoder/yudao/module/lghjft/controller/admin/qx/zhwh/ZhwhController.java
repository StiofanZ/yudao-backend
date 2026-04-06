package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import cn.iocoder.yudao.module.lghjft.service.qx.zhwh.ZhwhService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 账户维护")
@RestController
@RequestMapping("/lghjft/qx/zhwh")
@Validated
public class ZhwhController {

    @Resource
    private ZhwhService zhwhService;
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建账户维护申请")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:create')")
    public CommonResult<Long> createZhwh(@Valid @RequestBody ZhwhCreateReqVO reqVO) {
        return success(zhwhService.createZhwh(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新账户维护申请")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:update')")
    public CommonResult<Boolean> updateZhwh(@Valid @RequestBody ZhwhUpdateReqVO reqVO) {
        zhwhService.updateZhwh(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除账户维护申请")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:delete')")
    public CommonResult<Boolean> deleteZhwh(@RequestParam("id") Long id) {
        zhwhService.deleteZhwh(id);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "撤回账户维护申请")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:update')")
    public CommonResult<Boolean> cancelZhwh(@RequestParam("id") Long id) {
        zhwhService.cancelZhwh(id);
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核账户维护申请")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:audit')")
    public CommonResult<Boolean> auditZhwh(@Valid @RequestBody ZhwhAuditReqVO reqVO) {
        zhwhService.auditZhwh(reqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得账户维护申请")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:query')")
    public CommonResult<ZhwhResVO> getZhwh(@RequestParam("id") Long id) {
        GhQxZhwhDO zhwh = zhwhService.getZhwh(id);
        return success(fillRelatedInfo(BeanUtils.toBean(zhwh, ZhwhResVO.class)));
    }

    @GetMapping("/page")
    @Operation(summary = "获得账户维护申请分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-zhwh:query')")
    public CommonResult<PageResult<ZhwhResVO>> getZhwhPage(@Valid ZhwhPageReqVO reqVO) {
        PageResult<GhQxZhwhDO> pageResult = zhwhService.getZhwhPage(reqVO);
        PageResult<ZhwhResVO> result = BeanUtils.toBean(pageResult, ZhwhResVO.class);
        if (CollUtil.isEmpty(result.getList())) {
            return success(result);
        }
        fillRelatedInfo(result.getList());
        return success(result);
    }

    private ZhwhResVO fillRelatedInfo(ZhwhResVO respVO) {
        if (respVO == null) {
            return null;
        }
        fillRelatedInfo(Collections.singletonList(respVO));
        return respVO;
    }

    private void fillRelatedInfo(List<ZhwhResVO> list) {
        Set<Long> dlzhIds = convertSet(list, ZhwhResVO::getDlzhId);
        Map<Long, AdminUserDO> dlzhMap = dlzhIds.isEmpty()
                ? Collections.emptyMap()
                : convertMap(adminUserMapper.selectBatchIds(dlzhIds), AdminUserDO::getId);
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(list, ZhwhResVO::getDeptId));
        Set<Long> auditUserIds = convertSet(list, ZhwhResVO::getAuditUserId);
        Map<Long, AdminUserRespDTO> auditUserMap = auditUserIds.isEmpty()
                ? Collections.emptyMap()
                : adminUserApi.getUserMap(auditUserIds);
        list.forEach(item -> {
            AdminUserDO dlzh = dlzhMap.get(item.getDlzhId());
            if (dlzh != null) {
                item.setApplicantName(dlzh.getNickname());
                item.setApplicantPhone(dlzh.getMobile());
            }
            DeptRespDTO dept = deptMap.get(item.getDeptId());
            if (dept != null) {
                item.setDeptName(dept.getName());
            }
            AdminUserRespDTO auditUser = auditUserMap.get(item.getAuditUserId());
            if (auditUser != null) {
                item.setAuditUserName(auditUser.getNickname());
            }
        });
    }
}

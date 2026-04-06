package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjNsrxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.SystemUserSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhhjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds.JhdwydsMapper;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.SfxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.SystemUserSfxxService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxController {

    @Resource
    private SystemUserSfxxService systemUserSfxxService;
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private NsrxxService nsrxxService;
    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;
    @Resource
    private JcxxService jcxxService;
    @Resource
    private SfxxService sfxxService;
    @Resource
    private GhhjMapper ghhjMapper;
    @Resource
    private JhdwydsMapper jhdwydsMapper;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:create')")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxSaveReqVO createReqVO) {
        return success(systemUserSfxxService.createSfxx(createReqVO));
    }

    @PostMapping("/create-batch")
    @Operation(summary = "批量创建身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:create')")
    public CommonResult<Boolean> createSfxxBatch(@Valid @RequestBody List<SfxxSaveReqVO> list) {
        for (SfxxSaveReqVO reqVO : list) {
            systemUserSfxxService.createSfxx(reqVO);
        }
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:update')")
    public CommonResult<Boolean> updateSfxx(@Valid @RequestBody SfxxSaveReqVO updateReqVO) {
        systemUserSfxxService.updateSfxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除身份信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxx(@RequestParam("id") Long id) {
        systemUserSfxxService.deleteSfxx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除身份信息")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxxList(@RequestParam("ids") List<Long> ids) {
        systemUserSfxxService.deleteSfxxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get-kbdsfxx")
    @Operation(summary = "获得可绑定身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<List<KbdsfxxResVO>> getKbdsfxx(@Valid SfxxReqVO reqVO) {
        return success(sfxxService.getKbdsfxx(reqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得身份信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<SfxxResVO> getSfxx(@RequestParam("id") Long id) {
        SystemUserSfxxDO sfxx = systemUserSfxxService.getSfxx(id);
        SfxxResVO respVO = BeanUtils.toBean(sfxx, SfxxResVO.class);
        if (respVO != null && respVO.getDeptId() != null) {
            DeptRespDTO dept = deptApi.getDept(respVO.getDeptId());
            if (dept != null) {
                respVO.setDeptName(dept.getName());
            }
        }
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得身份信息分页")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<PageResult<SfxxResVO>> getSfxxPage(@Valid SfxxPageReqVO pageReqVO) {
        PageResult<SystemUserSfxxDO> pageResult = systemUserSfxxService.getSfxxPage(pageReqVO);
        PageResult<SfxxResVO> result = BeanUtils.toBean(pageResult, SfxxResVO.class);
        if (CollUtil.isEmpty(result.getList())) {
            return success(result);
        }

        // 1. Fetch related Dlzh (Login Accounts)
        Set<Long> dlzhIds = convertSet(result.getList(), SfxxResVO::getDlzhId);
        Map<Long, AdminUserDO> dlzhMap = Collections.emptyMap();
        if (CollUtil.isNotEmpty(dlzhIds)) {
            List<AdminUserDO> dlzhList = adminUserMapper.selectBatchIds(dlzhIds);
            dlzhMap = convertMap(dlzhList, AdminUserDO::getId);
        }

        // 2. 通过 gh_hj 表关联（非基层工会：sfxx.djxh = gh_hj.djxh）
        Set<String> djxhs = convertSet(result.getList(), SfxxResVO::getDjxh);
        Map<String, GhHjNsrxxResVO> ghhjMap = Collections.emptyMap();
        if (CollUtil.isNotEmpty(djxhs)) {
            List<GhHjNsrxxResVO> ghhjList = ghhjMapper.selectListByDjxhs(djxhs);
            ghhjMap = ghhjList.stream().collect(Collectors.toMap(
                    GhHjNsrxxResVO::getDjxh, item -> item, (v1, v2) -> v1));
        }

        // 2b. gh_hj 未命中的 → 通过 jhdwyds 关联（基层工会：sfxx.djxh = nvl(ghshxydm, shxydm)）
        final Map<String, GhHjNsrxxResVO> finalGhhjMap = ghhjMap;
        Set<String> unmatchedDjxhs = djxhs.stream()
                .filter(d -> d != null && !finalGhhjMap.containsKey(d))
                .collect(Collectors.toSet());
        Map<String, JhdwydsDO> jhdwydsMap = Collections.emptyMap();
        if (CollUtil.isNotEmpty(unmatchedDjxhs)) {
            List<JhdwydsDO> jhdwydsList = jhdwydsMapper.selectList(new LambdaQueryWrapper<JhdwydsDO>()
                    .in(JhdwydsDO::getGhshxydm, unmatchedDjxhs)
                    .or().in(JhdwydsDO::getShxydm, unmatchedDjxhs));
            jhdwydsMap = new java.util.HashMap<>();
            for (JhdwydsDO jhdwyds : jhdwydsList) {
                if (StrUtil.isNotBlank(jhdwyds.getGhshxydm())) {
                    jhdwydsMap.putIfAbsent(jhdwyds.getGhshxydm(), jhdwyds);
                }
                if (StrUtil.isNotBlank(jhdwyds.getShxydm())) {
                    jhdwydsMap.putIfAbsent(jhdwyds.getShxydm(), jhdwyds);
                }
            }
        }

        // 3. Fetch related Dept (Departments)
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result.getList(), SfxxResVO::getDeptId));

        // 4. Assemble Data
        for (SfxxResVO vo : result.getList()) {
            // Populate Dept Name
            DeptRespDTO dept = deptMap.get(vo.getDeptId());
            if (dept != null) {
                vo.setDeptName(dept.getName());
            }

            // Populate Dlzh
            AdminUserDO dlzhDO = dlzhMap.get(vo.getDlzhId());
            if (dlzhDO != null) {
                String displayDlzh = StrUtil.isNotBlank(dlzhDO.getUsername()) ? dlzhDO.getUsername() :
                        (StrUtil.isNotBlank(dlzhDO.getMobile()) ? dlzhDO.getMobile() :
                         (StrUtil.isNotBlank(dlzhDO.getEmail()) ? dlzhDO.getEmail() :
                                        (StrUtil.isNotBlank(dlzhDO.getShxydm()) ? dlzhDO.getShxydm() : "")));
                vo.setDlzh(displayDlzh);
            }

            // Populate Shxydm — 优先从 gh_hj 查，未命中从 jhdwyds 查
            GhHjNsrxxResVO ghhjVO = finalGhhjMap.get(vo.getDjxh());
            if (ghhjVO != null) {
                vo.setShxydm(ghhjVO.getShxydm());
            } else {
                JhdwydsDO jhdwydsDO = jhdwydsMap.get(vo.getDjxh());
                if (jhdwydsDO != null) {
                    vo.setShxydm(StrUtil.isNotBlank(jhdwydsDO.getShxydm()) ? jhdwydsDO.getShxydm() : jhdwydsDO.getGhshxydm());
                }
            }
        }

        return success(result);
    }

    @PutMapping("/audit")
    @Operation(summary = "身份授权")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id,
                                           @RequestParam("status") Integer status,
                                           @RequestParam(value = "jjyy", required = false) String jjyy) {
        systemUserSfxxService.auditSfxx(id, status, jjyy);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "解绑身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> unbindSfxx(@RequestParam("id") Long id, @RequestParam("jbyy") String jbyy) {
        systemUserSfxxService.unbindSfxx(id, jbyy);
        return success(true);
    }

}

package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

@Tag(name = "管理后台 - 身份信息")
@RestController
@RequestMapping("/lghjft/qx/sfxx")
@Validated
public class SfxxController {

    @Resource
    private GhQxSfxxService ghQxSfxxService;
    @Resource
    private GhQxDlzhService ghQxDlzhService;
    @Resource
    private NsrxxService nsrxxService;
    @Resource
    private DeptApi deptApi;

    @PostMapping("/create")
    @Operation(summary = "创建身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:create')")
    public CommonResult<Long> createSfxx(@Valid @RequestBody SfxxSaveReqVO createReqVO) {
        return success(ghQxSfxxService.createSfxx(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:update')")
    public CommonResult<Boolean> updateSfxx(@Valid @RequestBody SfxxSaveReqVO updateReqVO) {
        ghQxSfxxService.updateSfxx(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除身份信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxx(@RequestParam("id") Long id) {
        ghQxSfxxService.deleteSfxx(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除身份信息")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:delete')")
    public CommonResult<Boolean> deleteSfxxList(@RequestParam("ids") List<Long> ids) {
        ghQxSfxxService.deleteSfxxListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得身份信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:query')")
    public CommonResult<SfxxResVO> getSfxx(@RequestParam("id") Long id) {
        GhQxSfxxDO sfxx = ghQxSfxxService.getSfxx(id);
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
        PageResult<GhQxSfxxDO> pageResult = ghQxSfxxService.getSfxxPage(pageReqVO);
        PageResult<SfxxResVO> result = BeanUtils.toBean(pageResult, SfxxResVO.class);
        if (CollUtil.isEmpty(result.getList())) {
            return success(result);
        }

        // 1. Fetch related Dlzh (Login Accounts)
        Set<Long> dlzhIds = convertSet(result.getList(), SfxxResVO::getDlzhId);
        Map<Long, GhQxDlzhDO> dlzhMap = Collections.emptyMap();
        if (CollUtil.isNotEmpty(dlzhIds)) {
            List<GhQxDlzhDO> dlzhList = ghQxDlzhService.getDlzhList(dlzhIds);
            dlzhMap = convertMap(dlzhList, GhQxDlzhDO::getId);
        }

        // 2. Fetch related Nsrxx (Taxpayer Info)
        Set<String> djxhs = convertSet(result.getList(), SfxxResVO::getDjxh);
        Map<String, NsrxxDO> nsrxxMap = Collections.emptyMap();
        if (CollUtil.isNotEmpty(djxhs)) {
            List<NsrxxDO> nsrxxList = nsrxxService.getNsrxxListByDjxhs(djxhs);
            // Need to handle BigInteger to String for map key, NsrxxDO uses BigInteger for djxh
            nsrxxMap = nsrxxList.stream().collect(Collectors.toMap(
                    item -> String.valueOf(item.getDjxh()),
                    item -> item,
                    (v1, v2) -> v1 // Conflict resolver, just in case
            ));
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
            GhQxDlzhDO dlzhDO = dlzhMap.get(vo.getDlzhId());
            if (dlzhDO != null) {
                // Priority: yhzh > lxdh > yhyx > shxydm
                String displayDlzh = StrUtil.isNotBlank(dlzhDO.getYhzh()) ? dlzhDO.getYhzh() :
                        (StrUtil.isNotBlank(dlzhDO.getLxdh()) ? dlzhDO.getLxdh() :
                                (StrUtil.isNotBlank(dlzhDO.getYhyx()) ? dlzhDO.getYhyx() :
                                        (StrUtil.isNotBlank(dlzhDO.getShxydm()) ? dlzhDO.getShxydm() : "")));
                vo.setDlzh(displayDlzh);
            }

            // Populate Shxydm (from Nsrxx)
            NsrxxDO nsrxxDO = nsrxxMap.get(vo.getDjxh());
            if (nsrxxDO != null) {
                // Priority: shxydm > nsrsbh
                String displayShxydm = StrUtil.isNotBlank(nsrxxDO.getShxydm()) ? nsrxxDO.getShxydm() :
                        (StrUtil.isNotBlank(nsrxxDO.getNsrsbh()) ? nsrxxDO.getNsrsbh() : "");
                vo.setShxydm(displayShxydm);
            }
        }

        return success(result);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核身份信息")
    @PreAuthorize("@ss.hasPermission('lghjft:qx-sfxx:audit')")
    public CommonResult<Boolean> auditSfxx(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        ghQxSfxxService.auditSfxx(id, status);
        return success(true);
    }

}

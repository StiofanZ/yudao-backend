package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.treedict.SysTreeDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 树形字典")
@RestController
@RequestMapping("/lghjft/tree-dict")
@Validated
public class SysTreeDictController {

    @Resource
    private SysTreeDictService sysTreeDictService;

    @PostMapping("/create")
    @Operation(summary = "创建树形字典")
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:create')")
    public CommonResult<String> createSysTreeDict(@Valid @RequestBody SysTreeDictSaveReqVO createReqVO) {
        return success(sysTreeDictService.createSysTreeDict(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新树形字典")
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:update')")
    public CommonResult<Boolean> updateSysTreeDict(@Valid @RequestBody SysTreeDictSaveReqVO updateReqVO) {
        sysTreeDictService.updateSysTreeDict(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除树形字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:delete')")
    public CommonResult<Boolean> deleteSysTreeDict(@RequestParam("id") String id) {
        sysTreeDictService.deleteSysTreeDict(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除树形字典")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:delete')")
    public CommonResult<Boolean> deleteSysTreeDictList(@RequestParam("ids") List<String> ids) {
        sysTreeDictService.deleteSysTreeDictListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得树形字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:query')")
    public CommonResult<SysTreeDictResVO> getSysTreeDict(@RequestParam("id") String id) {
        SysTreeDictDO sysTreeDict = sysTreeDictService.getSysTreeDict(id);
        return success(BeanUtils.toBean(sysTreeDict, SysTreeDictResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得树形字典分页")
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:query')")
    public CommonResult<PageResult<SysTreeDictResVO>> getSysTreeDictPage(@Valid SysTreeDictPageReqVO pageReqVO) {
        PageResult<SysTreeDictDO> pageResult = sysTreeDictService.getSysTreeDictPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysTreeDictResVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得树形字典列表")
    @PreAuthorize("@ss.hasPermission('lghjft:treedict:query')")
    public CommonResult<List<SysTreeDictResVO>> getSysTreeDictList() {
        List<SysTreeDictDO> list = sysTreeDictService.getSysTreeDictList();
        return success(BeanUtils.toBean(list, SysTreeDictResVO.class));
    }

}

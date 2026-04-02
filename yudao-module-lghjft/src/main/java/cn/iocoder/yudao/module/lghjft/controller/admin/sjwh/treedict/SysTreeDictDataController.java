package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDataDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.treedict.SysTreeDictDataService;
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

@Tag(name = "管理后台 - 树形字典数据")
@RestController
@RequestMapping("/lghjft/tree-dict-data")
@Validated
public class SysTreeDictDataController {

    @Resource
    private SysTreeDictDataService sysTreeDictDataService;

    @PostMapping("/create")
    @Operation(summary = "创建树形字典数据")
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:create')")
    public CommonResult<String> createSysTreeDictData(@Valid @RequestBody SysTreeDictDataSaveReqVO createReqVO) {
        return success(sysTreeDictDataService.createSysTreeDictData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新树形字典数据")
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:update')")
    public CommonResult<Boolean> updateSysTreeDictData(@Valid @RequestBody SysTreeDictDataSaveReqVO updateReqVO) {
        sysTreeDictDataService.updateSysTreeDictData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除树形字典数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:delete')")
    public CommonResult<Boolean> deleteSysTreeDictData(@RequestParam("id") String id) {
        sysTreeDictDataService.deleteSysTreeDictData(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除树形字典数据")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:delete')")
    public CommonResult<Boolean> deleteSysTreeDictDataList(@RequestParam("ids") List<String> ids) {
        sysTreeDictDataService.deleteSysTreeDictDataListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得树形字典数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:query')")
    public CommonResult<SysTreeDictDataResVO> getSysTreeDictData(@RequestParam("id") String id) {
        SysTreeDictDataDO sysTreeDictData = sysTreeDictDataService.getSysTreeDictData(id);
        return success(BeanUtils.toBean(sysTreeDictData, SysTreeDictDataResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得树形字典数据分页")
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:query')")
    public CommonResult<PageResult<SysTreeDictDataResVO>> getSysTreeDictDataPage(@Valid SysTreeDictDataPageReqVO pageReqVO) {
        PageResult<SysTreeDictDataDO> pageResult = sysTreeDictDataService.getSysTreeDictDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysTreeDictDataResVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得树形字典数据列表")
    @PreAuthorize("@ss.hasPermission('lghjft:treedictdata:query')")
    public CommonResult<List<SysTreeDictDataResVO>> getSysTreeDictDataList() {
        List<SysTreeDictDataDO> list = sysTreeDictDataService.getSysTreeDictDataList();
        return success(BeanUtils.toBean(list, SysTreeDictDataResVO.class));
    }

}

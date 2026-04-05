package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jczhkxwh.JczhkxwhDO;
import cn.iocoder.yudao.module.lghjft.service.sjwh.jczhkxwh.JczhkxwhService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 基层账户空需维护")
@RestController
@RequestMapping("/lghjft/sjwh/jczhkxwh")
@Validated
public class JczhkxwhController {

    @Resource
    private JczhkxwhService jczhkxwhService;

    @PostMapping("/create")
    @Operation(summary = "创建基层账户空需维护")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:create')")
    public CommonResult<String> createJczhkxwh(@Valid @RequestBody JczhkxwhSaveReqVO createReqVO) {
        return success(jczhkxwhService.createJczhkxwh(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基层账户空需维护")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:update')")
    public CommonResult<Boolean> updateJczhkxwh(@Valid @RequestBody JczhkxwhSaveReqVO updateReqVO) {
        jczhkxwhService.updateJczhkxwh(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基层账户空需维护")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:delete')")
    public CommonResult<Boolean> deleteJczhkxwh(@RequestParam("id") String id) {
        jczhkxwhService.deleteJczhkxwh(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除基层账户空需维护")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:delete')")
    public CommonResult<Boolean> deleteJczhkxwhList(@RequestParam("ids") List<String> ids) {
        jczhkxwhService.deleteJczhkxwhListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基层账户空需维护")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:query')")
    public CommonResult<JczhkxwhResVO> getJczhkxwh(@RequestParam("id") String id) {
        JczhkxwhDO jczhkxwh = jczhkxwhService.getJczhkxwh(id);
        return success(BeanUtils.toBean(jczhkxwh, JczhkxwhResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基层账户空需维护分页")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:query')")
    public CommonResult<PageResult<JczhkxwhResVO>> getJczhkxwhPage(@Valid JczhkxwhPageReqVO pageReqVO) {
        PageResult<JczhkxwhDO> pageResult = jczhkxwhService.getJczhkxwhPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JczhkxwhResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基层账户空需维护 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:sjwh-jczhkxwh:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJczhkxwhExcel(@Valid JczhkxwhPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JczhkxwhDO> list = jczhkxwhService.getJczhkxwhPage(pageReqVO).getList();
        ExcelUtils.write(response, "基层账户空需维护.xls", "数据", JczhkxwhResVO.class,
                BeanUtils.toBean(list, JczhkxwhResVO.class));
    }
}

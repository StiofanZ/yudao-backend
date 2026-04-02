package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.czrj.QxCzrjDO;
import cn.iocoder.yudao.module.lghjft.service.cxtj.czrj.QxCzrjService;
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

@Tag(name = "管理后台 - 历史日志")
@RestController
@RequestMapping("/lghjft/cxtj/czrj")
@Validated
public class QxCzrjController {

    @Resource
    private QxCzrjService qxCzrjService;

    @PostMapping("/create")
    @Operation(summary = "创建历史日志")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:create')")
    public CommonResult<Long> createQxCzrj(@Valid @RequestBody QxCzrjSaveReqVO createReqVO) {
        return success(qxCzrjService.createQxCzrj(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新历史日志")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:update')")
    public CommonResult<Boolean> updateQxCzrj(@Valid @RequestBody QxCzrjSaveReqVO updateReqVO) {
        qxCzrjService.updateQxCzrj(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除历史日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:delete')")
    public CommonResult<Boolean> deleteQxCzrj(@RequestParam("id") Long id) {
        qxCzrjService.deleteQxCzrj(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除历史日志")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:delete')")
    public CommonResult<Boolean> deleteQxCzrjList(@RequestParam("ids") List<Long> ids) {
        qxCzrjService.deleteQxCzrjListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得历史日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:query')")
    public CommonResult<QxCzrjResVO> getQxCzrj(@RequestParam("id") Long id) {
        QxCzrjDO obj = qxCzrjService.getQxCzrj(id);
        return success(BeanUtils.toBean(obj, QxCzrjResVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得历史日志分页")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:query')")
    public CommonResult<PageResult<QxCzrjResVO>> getQxCzrjPage(@Valid QxCzrjPageReqVO pageReqVO) {
        PageResult<QxCzrjDO> pageResult = qxCzrjService.getQxCzrjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QxCzrjResVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出历史日志 Excel")
    @PreAuthorize("@ss.hasPermission('lghjft:czrj:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQxCzrjExcel(@Valid QxCzrjPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QxCzrjDO> list = qxCzrjService.getQxCzrjPage(pageReqVO).getList();
        ExcelUtils.write(response, "历史日志.xls", "数据", QxCzrjResVO.class,
                BeanUtils.toBean(list, QxCzrjResVO.class));
    }
}

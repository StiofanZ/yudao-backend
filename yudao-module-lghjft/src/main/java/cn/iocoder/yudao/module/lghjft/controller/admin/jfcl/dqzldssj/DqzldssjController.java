package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;
import cn.iocoder.yudao.module.lghjft.service.jfcl.dqzldssj.DqzldssjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 经费处理-读取增量代收数据")
@RestController
@RequestMapping("/lghjft/jfcl/dqzldssj")
@Validated
public class DqzldssjController {

    @Resource
    private DqzldssjService dqzldssjService;

    @GetMapping("/page")
    @Operation(summary = "查询读取增量代收数据列表")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqzldssj:query')")
    public CommonResult<PageResult<DqzldssjResVO>> getDqzldssjPage(@Valid DqzldssjPageReqVO pageReqVO) {
        PageResult<JfzcDqdssjVo> pageResult = dqzldssjService.getDqzldssjPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DqzldssjResVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "代收数据入库增量")
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqzldssj:create')")
    public CommonResult<String> dqzldssjrk(@RequestBody DqzldssjSaveReqVO reqVO) {
        return success(dqzldssjService.updateDqzldssjrk(reqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出增量代收数据 Excel")
    @ApiAccessLog(operateType = EXPORT)
    @PreAuthorize("@ss.hasPermission('lghjft:jfcl-dqzldssj:export')")
    public void exportDqzldssjExcel(@Valid DqzldssjPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        if (pageReqVO.getRkrqStart() == null || pageReqVO.getRkrqStart().isEmpty()
                || pageReqVO.getRkrqEnd() == null || pageReqVO.getRkrqEnd().isEmpty()) {
            throw new RuntimeException("入库日期起止不能为空");
        }
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JfzcDqdssjVo> list = dqzldssjService.getDqzldssjPage(pageReqVO).getList();
        ExcelUtils.write(response, "增量代收数据.xls", "数据", DqzldssjResVO.class,
                BeanUtils.toBean(list, DqzldssjResVO.class));
    }
}

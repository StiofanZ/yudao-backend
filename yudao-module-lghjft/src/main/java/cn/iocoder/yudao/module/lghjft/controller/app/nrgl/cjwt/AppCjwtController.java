package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.cjwt;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.cjwt.CjwtService;
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

@Tag(name = "管理后台 - 常见问题")
@RestController
@RequestMapping("/lghjft/nrgl/cjwt")
@Validated
public class AppCjwtController {

    @Resource
    private CjwtService cjwtService;

    @Resource
    private DeptApi deptApi;

    @GetMapping("/get")
    @Operation(summary = "获得常见问题")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:query')")
    public CommonResult<CjwtResVO> getCjwt(@RequestParam("id") Long id) {
        CjwtDO cjwt = cjwtService.getCjwt(id);
        return success(BeanUtils.toBean(cjwt, CjwtResVO.class));
    }

    @GetMapping("/list-page")
    @Operation(summary = "获得常见问题分页列表")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-cjwt:query')")
    public CommonResult<PageResult<CjwtResVO>> getCjwtPage(@Valid CjwtReqVO listReqVO) {
        PageResult<CjwtDO> pageResult = cjwtService.getCjwtPage(listReqVO);
        List<CjwtResVO> result = BeanUtils.toBean(pageResult.getList(), CjwtResVO.class);
        
        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, CjwtResVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }
        
        return success(new PageResult<>(result, pageResult.getTotal()));
    }

}

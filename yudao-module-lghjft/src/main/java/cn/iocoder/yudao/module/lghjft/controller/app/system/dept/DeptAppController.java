package cn.iocoder.yudao.module.lghjft.controller.app.system.dept;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.app.system.dept.vo.DeptAppRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 部门")
@RestController
@RequestMapping("/lghjft/system/dept")
@Validated
public class DeptAppController {

    @Resource
    private DeptService deptService;
    @Resource
    private AdminUserServiceImpl adminUserService;

    @GetMapping("/get")
    @Operation(summary = "获得部门信息VO")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<DeptAppRespVO> get(@RequestParam("id") Long id) {
        DeptDO deptDO = deptService.getDept(id);
        DeptAppRespVO deptAppRespVO = BeanUtils.toBean(deptDO, DeptAppRespVO.class);
        DeptDO deptParentDO = deptService.getDept(deptDO.getParentId());
        if (!Objects.isNull(deptParentDO)) {
            deptAppRespVO.setParentName(deptParentDO.getName());
        }
        AdminUserDO userDO = adminUserService.getUser(deptDO.getLeaderUserId());
        deptAppRespVO.setLeaderUserName(userDO.getUsername());
        deptAppRespVO.setLeaderNickname(userDO.getNickname());
        return success(deptAppRespVO);
    }

}

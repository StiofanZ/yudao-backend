package cn.iocoder.yudao.module.lghjft.controller.admin.bbsj;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.jeecg.modules.drag.vo.TreeModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 报表数据")
@RestController
@RequestMapping("/report/data")
@Validated
public class BbsjController {

    @Resource
    private DeptService deptService;
    @Resource
    private AdminUserService userService;

    @GetMapping("/get-dept-tree")
    @Operation(summary = "获取部门树")
    @PreAuthorize("@ss.hasPermission('lghjft:report:query')")
    public CommonResult<List<TreeModel>> getDeptTree(@RequestParam(name = "params", required = false) String params) {
        Set<Long> parents = ConcurrentHashMap.newKeySet();
        List<DeptDO> depts = new CopyOnWriteArrayList<>(new ArrayList<>());
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();

        if (loginUser == null) {
            return success(List.of());
        }
        AdminUserDO user = userService.getUser(loginUser.getId());
        if (Objects.isNull(params)) {
            // 根节点：返回全国总工会（id=100000）作为树根，而非当前用户部门
            DeptDO rootDept = deptService.getDept(100000L);
            if (rootDept != null) {
                parents.add(rootDept.getParentId());
                depts.add(rootDept);
            } else {
                // fallback：如果根部门不存在，返回当前用户部门
                DeptDO dept = deptService.getDept(user.getDeptId());
                parents.add(dept.getParentId());
                depts.add(dept);
            }
        } else {
            Map<String, Object> map = JsonUtils.parseObject(params, new TypeReference<Map<String, Object>>() {
            });
            if (map.containsKey("pid")) {
                String parentId = String.valueOf(map.get("pid"));
                deptService.getChildDeptList(Long.parseLong(parentId)).parallelStream()
                        .filter(d -> Boolean.FALSE.equals(d.getDeleted()))
                        .forEach(d -> {
                            parents.add(d.getParentId());
                            if (parentId.equals(d.getParentId().toString())) {
                                depts.add(d);
                            }
                        });
            }
        }

        List<TreeModel> treeList = depts.stream()
                .sorted(Comparator.comparing(DeptDO::getId))
                .map(dept -> {
                    int isLeaf = parents.contains(dept.getId()) || "0".equals(dept.getParentId().toString()) ? 0 : 1;
                    return new TreeModel(
                            dept.getId().toString(),
                            dept.getParentId().toString(),
                            dept.getId().toString(),
                            dept.getName(),
                            isLeaf
                    );
                }).toList();
        return success(treeList);
    }
}

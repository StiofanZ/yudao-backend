package cn.iocoder.yudao.module.lghjft.controller.admin;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Tag(name = "管理后台 - 报表数据", description = "提供 SQL、HTTP 等数据查询的能力")
@RestController
@RequestMapping("/report/data")
@Validated
public class ReportDataController {
    @Resource
    private DeptService deptService;
    @Resource
    private AdminUserService userService;

    @RequestMapping("/get-dept-tree")
    @Operation(summary = "获取部门树", description = "根据部门信息获取部门树，如果部门信息为空则获取全量树", method = "GET")
    public List<TreeModel> getDeptTree(@RequestParam(name = "params", required = false) String params) { // params、body 按照需要去接收，这里仅仅是示例
        Set<Long> parents = ConcurrentHashMap.newKeySet();
        List<DeptDO> depts = new CopyOnWriteArrayList<>(new ArrayList<>());
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            AdminUserDO user = userService.getUser(loginUser.getId());
            if (Objects.isNull(params)) {
                DeptDO dept = deptService.getDept(user.getDeptId());
                parents.add(dept.getParentId());
                depts.add(dept);// 取 本级
            } else {
                Map<String, Object> map = JsonUtils.parseObject(params, new TypeReference<>() {
                });
                if (map.containsKey("pid")) {
                    String parentId = String.valueOf(map.get("pid"));// 取选中下级
                    deptService.getChildDeptList(Long.parseLong(parentId)).parallelStream()
                            .filter(d -> Boolean.FALSE.equals(d.getDeleted()))
                            .forEach(d -> {
                                parents.add(d.getParentId());
                                if (parentId.equals(d.getParentId().toString())) { //只取 下一级
                                    depts.add(d);
                                }
                            });
                }
            }
            return depts.stream().sorted(Comparator.comparing(DeptDO::getId)).map(dept -> {
                int isLeaf = 1;
                if (parents.contains(dept.getId()) || "0".equals(dept.getParentId().toString())) {
                    isLeaf = 0;
                }
                return new TreeModel(dept.getId().toString(), dept.getParentId().toString(), dept.getId().toString(), dept.getName(), isLeaf);
            }).toList();
        }
        return List.of();
    }
}

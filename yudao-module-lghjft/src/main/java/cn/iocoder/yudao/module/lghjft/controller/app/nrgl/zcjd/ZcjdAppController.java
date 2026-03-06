package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcjd;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdResVO;
import cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcjd.vo.ZcjdPageAppReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcwj.ZcwjMapper;
import cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd.ZcjdService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "用户 App - 政策解读")
@RestController
@RequestMapping("/lghjft/nrgl/zcjd")
@Validated
public class ZcjdAppController {

    @Resource
    private ZcjdService zcjdService;

    @Resource
    private DeptApi deptApi;

    @Resource
    private ZcwjMapper zcwjMapper;

    @GetMapping("/list-page")
    @Operation(summary = "获得政策解读列表")
    @Parameter(name = "deptId", description = "部门编号", required = true)
    public CommonResult<List<ZcjdResVO>> getZcjdList(@Valid ZcjdPageAppReqVO reqVO) {
        List<ZcjdDO> list = zcjdService.getZcjdList(reqVO);
        list.removeIf(zcjdDO -> !List.of(2, 3).contains(zcjdDO.getStatus())); //only 2,3
        list.removeIf(zcjdDO -> !ObjectUtils.isEmpty(reqVO.getFbbm()) && !reqVO.getFbbm().equals(zcjdDO.getFbbm()));
        List<ZcjdResVO> result = BeanUtils.toBean(list, ZcjdResVO.class);

        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, ZcjdResVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }
        fillLinkedPolicyInfo(result);

        return success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ZcjdResVO> getZcjd(@RequestParam("id") Long id) {
        ZcjdDO zcjd = zcjdService.getZcjd(id);
        ZcjdResVO result = BeanUtils.toBean(zcjd, ZcjdResVO.class);
        if (result != null) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(List.of(result), ZcjdResVO::getDeptId));
            DeptRespDTO dept = deptMap.get(result.getDeptId());
            if (dept != null) {
                result.setDeptName(dept.getName());
            }
            fillLinkedPolicyInfo(List.of(result));
        }
        return success(result);
    }

    private void fillLinkedPolicyInfo(List<ZcjdResVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Set<Long> policyIds = convertSet(list, ZcjdResVO::getGlzcId);
        policyIds.remove(null);
        if (policyIds.isEmpty()) {
            return;
        }
        Map<Long, ZcwjDO> policyMap = convertMap(zcwjMapper.selectBatchIds(policyIds), ZcwjDO::getId);
        list.forEach(item -> {
            ZcwjDO policy = policyMap.get(item.getGlzcId());
            if (policy != null) {
                item.setGlzcTitle(policy.getTitle());
                item.setGlzcVersionNo(policy.getVersionNo());
                item.setGlzcAttachmentUrls(policy.getAttachmentUrls());
            }
        });
    }

}

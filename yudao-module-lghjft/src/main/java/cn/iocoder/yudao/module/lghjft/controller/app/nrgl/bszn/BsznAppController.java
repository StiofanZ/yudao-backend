package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.bszn;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznRespVO;
import cn.iocoder.yudao.module.lghjft.controller.app.nrgl.bszn.vo.BsznPageAppReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.bszn.BsznService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "用户 App - 办事指南")
@RestController
@RequestMapping("/lghjft/nrgl/bszn")
@Validated
public class BsznAppController {

    @Resource
    private BsznService bsznService;

    @Resource
    private DeptApi deptApi;

    @GetMapping("/get")
    @Operation(summary = "获得办事指南")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<BsznRespVO> getBszn(@RequestParam("id") Long id) {
        BsznDO bszn = bsznService.getBszn(id);
        return success(BeanUtils.toBean(bszn, BsznRespVO.class));
    }

    @GetMapping("/list-page")
    @Operation(summary = "获得办事指南列表")
    public CommonResult<List<BsznRespVO>> getBsznList(@Valid BsznPageAppReqVO listReqVO) {
        List<BsznDO> list = bsznService.getBsznList(listReqVO);
        list.removeIf(zcjdDO -> !List.of(2, 3).contains(zcjdDO.getStatus())); //only 2,3
        List<BsznRespVO> result = BeanUtils.toBean(list, BsznRespVO.class);

        // 填充部门名称
        if (!result.isEmpty()) {
            Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(result, BsznRespVO::getDeptId));
            result.forEach(item -> {
                DeptRespDTO dept = deptMap.get(item.getDeptId());
                if (dept != null) {
                    item.setDeptName(dept.getName());
                }
            });
        }

        return success(result);
    }

}

package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zxzx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkClmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxFaqResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import cn.iocoder.yudao.module.lghjft.service.nrgl.cjwt.CjwtService;
import cn.iocoder.yudao.module.lghjft.service.nrgl.wtfk.GhNrglWtfkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 在线咨询")
@RestController
@RequestMapping("/lghjft/nrgl/zxzx/app")
@Validated
public class ZxzxAppController {

    private static final String CONSULT_TYPE = "5";

    @Resource
    private GhNrglWtfkService wtfkService;
    @Resource
    private CjwtService cjwtService;

    @PostMapping("/create")
    @Operation(summary = "创建在线咨询")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> create(@Valid @RequestBody ZxzxCreateReqVO reqVO) {
        GhNrglWtfkSaveReqVO saveReqVO = new GhNrglWtfkSaveReqVO();
        saveReqVO.setLx(CONSULT_TYPE);
        saveReqVO.setPtmc(reqVO.getPtmc());
        saveReqVO.setNr(reqVO.getNr());
        saveReqVO.setLxdh(reqVO.getLxdh());
        saveReqVO.setLxyx(reqVO.getLxyx());
        saveReqVO.setZt(1);
        saveReqVO.setFjList(BeanUtils.toBean(reqVO.getFjList(), GhNrglWtfkSaveReqVO.FjItem.class));
        return success(wtfkService.createGhNrglWtfk(saveReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在线咨询分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<ZxzxResVO>> getPage(@Valid ZxzxPageReqVO reqVO) {
        GhNrglWtfkPageReqVO pageReqVO = new GhNrglWtfkPageReqVO();
        pageReqVO.setPageNo(reqVO.getPageNo());
        pageReqVO.setPageSize(reqVO.getPageSize());
        pageReqVO.setFkbh(reqVO.getConsultNo());
        pageReqVO.setYhmc(reqVO.getYhmc());
        pageReqVO.setNr(reqVO.getNr());
        pageReqVO.setLxdh(reqVO.getLxdh());
        pageReqVO.setZt(reqVO.getZt());
        pageReqVO.setLx(CONSULT_TYPE);
        pageReqVO.setIsAdminView(false);
        PageResult<GhNrglWtfkDO> pageResult = wtfkService.getGhNrglWtfkPage(pageReqVO);
        PageResult<GhNrglWtfkResVO> converted = BeanUtils.toBean(pageResult, GhNrglWtfkResVO.class);
        List<ZxzxResVO> list = converted.getList().stream().map(this::convertResp).toList();
        return success(new PageResult<>(list, converted.getTotal()));
    }

    @GetMapping("/get")
    @Operation(summary = "获得在线咨询详情")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<ZxzxResVO> get(@RequestParam("id") Long id) {
        return success(convertResp(validateConsult(id)));
    }

    @GetMapping("/log/list")
    @Operation(summary = "获得在线咨询处理日志")
    @Parameter(name = "id", required = true)
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<GhNrglWtfkClmxResVO>> getLogList(@RequestParam("id") Long id) {
        validateConsult(id);
        return success(wtfkService.getGhNrglWtfkClmxList(id));
    }

    @GetMapping("/faq-suggestions")
    @Operation(summary = "获得 FAQ 建议")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<ZxzxFaqResVO>> getFaqSuggestions(@RequestParam("keyword") String keyword,
                                                               @RequestParam(value = "deptId", required = false) Long deptId) {
        List<CjwtDO> list = cjwtService.getPublicCjwtList(deptId).stream()
                .filter(item -> !StringUtils.hasText(keyword)
                        || item.getTitle().contains(keyword)
                        || (item.getContent() != null && item.getContent().contains(keyword)))
                .limit(5)
                .toList();
        return success(BeanUtils.toBean(list, ZxzxFaqResVO.class));
    }

    private ZxzxResVO convertResp(GhNrglWtfkResVO respVO) {
        ZxzxResVO result = BeanUtils.toBean(respVO, ZxzxResVO.class);
        result.setConsultNo(respVO.getFkbh());
        return result;
    }

    private GhNrglWtfkResVO validateConsult(Long id) {
        GhNrglWtfkDO wtfk = wtfkService.getGhNrglWtfk(id);
        if (wtfk == null || !CONSULT_TYPE.equals(wtfk.getLx())) {
            throw new IllegalArgumentException("在线咨询记录不存在");
        }
        return wtfkService.getGhNrglWtfkDetail(id);
    }
}

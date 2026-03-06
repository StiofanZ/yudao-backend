package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkClReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkClmxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxFaqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxProcessReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo.ZxzxRespVO;
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

@Tag(name = "管理后台 - 在线咨询")
@RestController
@RequestMapping("/lghjft/nrgl/zxzx")
@Validated
public class ZxzxController {

    private static final String CONSULT_TYPE = "5";

    @Resource
    private GhNrglWtfkService wtfkService;
    @Resource
    private CjwtService cjwtService;

    @GetMapping("/page")
    @Operation(summary = "获得在线咨询分页")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zxzx:query')")
    public CommonResult<PageResult<ZxzxRespVO>> getPage(@Valid ZxzxPageReqVO reqVO) {
        PageResult<GhNrglWtfkDO> pageResult = wtfkService.getGhNrglWtfkPage(buildPageReq(reqVO, true));
        return success(convertPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获得在线咨询详情")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zxzx:query')")
    public CommonResult<ZxzxRespVO> get(@RequestParam("id") Long id) {
        return success(convertResp(validateConsult(id)));
    }

    @GetMapping("/log/list")
    @Operation(summary = "获得在线咨询处理日志")
    @Parameter(name = "id", required = true)
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zxzx:query')")
    public CommonResult<List<GhNrglWtfkClmxRespVO>> getLogList(@RequestParam("id") Long id) {
        validateConsult(id);
        return success(wtfkService.getGhNrglWtfkClmxList(id));
    }

    @PutMapping("/handle-process")
    @Operation(summary = "处理在线咨询")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zxzx:update')")
    public CommonResult<Boolean> handleProcess(@Valid @RequestBody ZxzxProcessReqVO reqVO) {
        validateConsult(reqVO.getId());
        GhNrglWtfkClReqVO processReqVO = new GhNrglWtfkClReqVO();
        processReqVO.setId(reqVO.getId());
        processReqVO.setZt(reqVO.getZt());
        processReqVO.setClsm(reqVO.getClsm());
        wtfkService.handleProcess(processReqVO);
        return success(true);
    }

    @GetMapping("/faq-suggestions")
    @Operation(summary = "获得 FAQ 建议")
    @PreAuthorize("@ss.hasPermission('lghjft:nrgl-zxzx:query')")
    public CommonResult<List<ZxzxFaqRespVO>> getFaqSuggestions(@RequestParam("keyword") String keyword,
                                                               @RequestParam(value = "deptId", required = false) Long deptId) {
        List<CjwtDO> list = cjwtService.getPublicCjwtList(deptId).stream()
                .filter(item -> !StringUtils.hasText(keyword)
                        || item.getTitle().contains(keyword)
                        || (item.getContent() != null && item.getContent().contains(keyword)))
                .limit(5)
                .toList();
        return success(BeanUtils.toBean(list, ZxzxFaqRespVO.class));
    }

    private GhNrglWtfkPageReqVO buildPageReq(ZxzxPageReqVO reqVO, boolean adminView) {
        GhNrglWtfkPageReqVO pageReqVO = new GhNrglWtfkPageReqVO();
        pageReqVO.setPageNo(reqVO.getPageNo());
        pageReqVO.setPageSize(reqVO.getPageSize());
        pageReqVO.setFkbh(reqVO.getConsultNo());
        pageReqVO.setYhmc(reqVO.getYhmc());
        pageReqVO.setNr(reqVO.getNr());
        pageReqVO.setLxdh(reqVO.getLxdh());
        pageReqVO.setZt(reqVO.getZt());
        pageReqVO.setLx(CONSULT_TYPE);
        pageReqVO.setIsAdminView(reqVO.getIsAdminView() == null ? adminView : reqVO.getIsAdminView());
        return pageReqVO;
    }

    private PageResult<ZxzxRespVO> convertPage(PageResult<GhNrglWtfkDO> pageResult) {
        PageResult<GhNrglWtfkRespVO> converted = BeanUtils.toBean(pageResult, GhNrglWtfkRespVO.class);
        List<ZxzxRespVO> list = converted.getList().stream().map(this::convertResp).toList();
        return new PageResult<>(list, converted.getTotal());
    }

    private ZxzxRespVO convertResp(GhNrglWtfkRespVO respVO) {
        ZxzxRespVO result = BeanUtils.toBean(respVO, ZxzxRespVO.class);
        result.setConsultNo(respVO.getFkbh());
        return result;
    }

    private GhNrglWtfkRespVO validateConsult(Long id) {
        GhNrglWtfkDO wtfk = wtfkService.getGhNrglWtfk(id);
        if (wtfk == null || !CONSULT_TYPE.equals(wtfk.getLx())) {
            throw new IllegalArgumentException("在线咨询记录不存在");
        }
        return wtfkService.getGhNrglWtfkDetail(id);
    }
}

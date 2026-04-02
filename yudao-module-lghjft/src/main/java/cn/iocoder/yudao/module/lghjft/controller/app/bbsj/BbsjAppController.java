package cn.iocoder.yudao.module.lghjft.controller.app.bbsj;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo.BbsjResVO;
import cn.iocoder.yudao.module.lghjft.service.bbsj.BbsjService;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 报表数据")
@RestController
@RequestMapping("/report")
@Validated
public class BbsjAppController {

    @Resource
    private BbsjService bbsjService;

    /**
     * 允许的查询参数白名单，防止注入攻击
     */
    private static final java.util.Set<String> ALLOWED_PARAMS = java.util.Set.of(
            "deptId", "nd", "yf", "bbbm", "djxh", "shxydm", "nsrmc",
            "beginDate", "endDate", "skssqq", "skssqz", "rkrq", "pageNo", "pageSize"
    );

    @GetMapping("/{bbbm}")
    @Operation(summary = "按报表编码获取报表数据")
    @PreAuthorize("@ss.hasPermission('lghjft:bbsj:query')")
    public CommonResult<BbsjResVO> hqBbsj(@PathVariable("bbbm") String bbbm,
                                           HttpServletRequest request) {
        return success(bbsjService.hqBbsj(bbbm, hqCxcs(request)));
    }

    private Map<String, Object> hqCxcs(HttpServletRequest request) {
        Map<String, Object> cxcs = new LinkedHashMap<>();
        String params = request.getParameter("params");
        if (StrUtil.isNotBlank(params)) {
            Map<String, Object> paramsMap = JsonUtils.parseObject(params, new TypeReference<Map<String, Object>>() {
            });
            if (paramsMap != null) {
                paramsMap.forEach((key, value) -> {
                    if (ALLOWED_PARAMS.contains(key) && value instanceof String) {
                        cxcs.put(key, value);
                    }
                });
            }
        }
        ServletUtils.getParamMap(request).forEach((key, value) -> {
            if (!"params".equals(key) && ALLOWED_PARAMS.contains(key)) {
                cxcs.put(key, value);
            }
        });
        return cxcs;
    }
}

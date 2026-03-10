package cn.iocoder.yudao.module.lghjft.controller.app.bbsj;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo.BbsjRespVO;
import cn.iocoder.yudao.module.lghjft.service.bbsj.BbsjService;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/{bbbm}")
    @Operation(summary = "按报表编码获取报表数据")
    public CommonResult<BbsjRespVO> hqBbsj(@PathVariable("bbbm") String bbbm,
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
                cxcs.putAll(paramsMap);
            }
        }
        ServletUtils.getParamMap(request).forEach((key, value) -> {
            if (!"params".equals(key)) {
                cxcs.put(key, value);
            }
        });
        return cxcs;
    }
}

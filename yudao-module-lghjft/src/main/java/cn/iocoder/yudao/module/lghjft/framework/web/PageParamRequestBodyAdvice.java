package cn.iocoder.yudao.module.lghjft.framework.web;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * 为 @RequestBody 形式的 PageParam 提供和 query 参数一致的 pageSize 兼容归一。
 */
@ControllerAdvice(basePackages = "cn.iocoder.yudao.module.lghjft.controller")
public class PageParamRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return targetType instanceof Class<?> clazz && PageParam.class.isAssignableFrom(clazz);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof PageParam pageParam) {
            pageParam.setPageSize(PageParamBinderAdvice.normalizePageSize(pageParam.getPageSize()));
        }
        return body;
    }
}

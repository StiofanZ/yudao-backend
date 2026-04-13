package cn.iocoder.yudao.module.lghjft.framework.web;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PageParamBinderAdviceTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void initBinder_shouldNormalizeLegacyLargePageSizeBeforeValidation() {
        JffymxPageReqVO target = new JffymxPageReqVO();
        WebDataBinder binder = new WebDataBinder(target);

        new PageParamBinderAdvice().initBinder(binder);
        binder.bind(new MutablePropertyValues(Map.of("pageSize", "1000")));

        Set<ConstraintViolation<JffymxPageReqVO>> violations = validator.validate(target);

        assertEquals(200, target.getPageSize());
        assertTrue(violations.stream().noneMatch(v -> "pageSize".equals(v.getPropertyPath().toString())));
    }

    @Test
    void afterBodyRead_shouldNormalizeLegacyLargePageSizeBeforeValidation() throws Exception {
        JfjsPageReqVO body = new JfjsPageReqVO();
        body.setPageSize(1000);

        PageParamRequestBodyAdvice advice = new PageParamRequestBodyAdvice();
        Method method = DummyRequestBodyTarget.class.getDeclaredMethod("accept", JfjsPageReqVO.class);
        MethodParameter parameter = new MethodParameter(method, 0);
        @SuppressWarnings("unchecked")
        Class<? extends HttpMessageConverter<?>> converterType =
                (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class;

        Object normalized = advice.afterBodyRead(body, null, parameter, JfjsPageReqVO.class, converterType);
        Set<ConstraintViolation<JfjsPageReqVO>> violations = validator.validate((JfjsPageReqVO) normalized);

        assertEquals(200, ((JfjsPageReqVO) normalized).getPageSize());
        assertFalse(violations.stream().anyMatch(v -> "pageSize".equals(v.getPropertyPath().toString())));
    }

    private static class DummyRequestBodyTarget {
        @SuppressWarnings("unused")
        void accept(JfjsPageReqVO reqVO) {
        }
    }
}

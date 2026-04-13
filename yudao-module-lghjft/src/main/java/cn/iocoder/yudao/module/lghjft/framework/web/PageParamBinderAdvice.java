package cn.iocoder.yudao.module.lghjft.framework.web;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * 兼容 v1 页面仍可能提交的大页码参数，避免在 Bean Validation 阶段被框架上限直接拦截。
 *
 * <p>对于真正需要不分页的 lghjft 接口，控制器内部仍会显式设置 {@link PageParam#PAGE_SIZE_NONE}；
 * 这里仅负责把进入控制器前的 pageSize 归一化到框架允许范围内，保证旧页面不会因为 1000 等历史值直接 400。</p>
 */
@ControllerAdvice(basePackages = "cn.iocoder.yudao.module.lghjft.controller")
public class PageParamBinderAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (!(binder.getTarget() instanceof PageParam)) {
            return;
        }
        binder.registerCustomEditor(Integer.class, "pageSize", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                setValue(normalizePageSize(Integer.parseInt(text.trim())));
            }
        });
    }

    static Integer normalizePageSize(Integer pageSize) {
        if (pageSize == null) {
            return null;
        }
        if (pageSize == PageParam.PAGE_SIZE_NONE || pageSize > 200) {
            return 200;
        }
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }
}

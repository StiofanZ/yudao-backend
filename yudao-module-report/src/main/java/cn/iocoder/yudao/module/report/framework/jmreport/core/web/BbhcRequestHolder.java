package cn.iocoder.yudao.module.report.framework.jmreport.core.web;

import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcDO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BbhcRequestHolder {

    private static final String BBSJ_HC_KEY = BbhcRequestHolder.class.getName() + ".BBSJ_HC";

    public static void set(GhBbsjHcDO hc) {
        ServletRequestAttributes attrs = getAttrs();
        if (attrs != null) {
            attrs.setAttribute(BBSJ_HC_KEY, hc, RequestAttributes.SCOPE_REQUEST);
        }
    }

    public static GhBbsjHcDO get() {
        ServletRequestAttributes attrs = getAttrs();
        if (attrs == null) {
            return null;
        }
        Object value = attrs.getAttribute(BBSJ_HC_KEY, RequestAttributes.SCOPE_REQUEST);
        return value instanceof GhBbsjHcDO ? (GhBbsjHcDO) value : null;
    }

    public static void clear() {
        ServletRequestAttributes attrs = getAttrs();
        if (attrs != null) {
            attrs.removeAttribute(BBSJ_HC_KEY, RequestAttributes.SCOPE_REQUEST);
        }
    }

    private static ServletRequestAttributes getAttrs() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        return attrs instanceof ServletRequestAttributes ? (ServletRequestAttributes) attrs : null;
    }
}

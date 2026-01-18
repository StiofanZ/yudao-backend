package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTIFY_SEND_TEMPLATE_PARAM_MISS;

/**
 * 站内信发送 Service 实现类
 *
 * @author xrcoder
 */
@Service
@Validated
@Slf4j
public class XxtxSendServiceImpl implements XxtxSendService {

    @Resource
    private XxtxTemplateService xxtxTemplateService;

    @Resource
    private XxtxMessageService xxtxMessageService;

    @Override
    public Long sendSingleXxtxToAdmin(Long userId, String templateCode, Map<String, Object> templateParams) {
        return sendSingleXxtx(userId, UserTypeEnum.ADMIN.getValue(), templateCode, templateParams);
    }

    @Override
    public Long sendSingleXxtxToMember(Long userId, String templateCode, Map<String, Object> templateParams) {
        return sendSingleXxtx(userId, UserTypeEnum.MEMBER.getValue(), templateCode, templateParams);
    }

    @Override
    public Long sendSingleXxtx(Long userId, Integer userType, String templateCode, Map<String, Object> templateParams) {
        // 校验模版
        XxtxTemplateDO template = validateXxtxTemplate(templateCode);
        if (Objects.equals(template.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            log.info("[sendSingleXxtx][模版({})已经关闭，无法给用户({}/{})发送]", templateCode, userId, userType);
            return null;
        }
        // 校验参数
        validateTemplateParams(template, templateParams);

        // 发送站内信
        String content = xxtxTemplateService.formatXxtxTemplateContent(template.getContent(), templateParams);
        return xxtxMessageService.createXxtxMessage(userId, userType, template, content, templateParams);
    }

    @VisibleForTesting
    public XxtxTemplateDO validateXxtxTemplate(String templateCode) {
        // 获得站内信模板。考虑到效率，从缓存中获取
        XxtxTemplateDO template = xxtxTemplateService.getXxtxTemplateByCodeFromCache(templateCode);
        // 站内信模板不存在
        if (template == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
        return template;
    }

    /**
     * 校验站内信模版参数是否确实
     *
     * @param template 邮箱模板
     * @param templateParams 参数列表
     */
    @VisibleForTesting
    public void validateTemplateParams(XxtxTemplateDO template, Map<String, Object> templateParams) {
        template.getParams().forEach(key -> {
            Object value = templateParams.get(key);
            if (value == null) {
                throw exception(NOTIFY_SEND_TEMPLATE_PARAM_MISS, key);
            }
        });
    }
}

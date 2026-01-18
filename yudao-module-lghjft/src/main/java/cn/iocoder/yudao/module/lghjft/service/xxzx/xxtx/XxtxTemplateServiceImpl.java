package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplatePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplateSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxTemplateMapper;
import cn.iocoder.yudao.module.system.dal.redis.RedisKeyConstants;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTIFY_TEMPLATE_CODE_DUPLICATE;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTIFY_TEMPLATE_NOT_EXISTS;

/**
 * 站内信模版 Service 实现类
 *
 * @author xrcoder
 */
@Service
@Validated
@Slf4j
public class XxtxTemplateServiceImpl implements XxtxTemplateService {

    /**
     * 正则表达式，匹配 {} 中的变量
     */
    private static final Pattern PATTERN_PARAMS = Pattern.compile("\\{(.*?)}");

    @Resource
    private XxtxTemplateMapper xxtxTemplateMapper;

    @Override
    public Long createXxtxTemplate(XxtxTemplateSaveReqVO createReqVO) {
        // 校验站内信编码是否重复
        validateXxtxTemplateCodeDuplicate(null, createReqVO.getCode());

        // 插入
        XxtxTemplateDO xxtxTemplate = BeanUtils.toBean(createReqVO, XxtxTemplateDO.class);
        xxtxTemplate.setParams(parseTemplateContentParams(xxtxTemplate.getContent()));
        xxtxTemplateMapper.insert(xxtxTemplate);
        return xxtxTemplate.getId();
    }

    @Override
    @CacheEvict(cacheNames = RedisKeyConstants.NOTIFY_TEMPLATE,
            allEntries = true) // allEntries 清空所有缓存，因为可能修改到 code 字段，不好清理
    public void updateXxtxTemplate(XxtxTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateXxtxTemplateExists(updateReqVO.getId());
        // 校验站内信编码是否重复
        validateXxtxTemplateCodeDuplicate(updateReqVO.getId(), updateReqVO.getCode());

        // 更新
        XxtxTemplateDO updateObj = BeanUtils.toBean(updateReqVO, XxtxTemplateDO.class);
        updateObj.setParams(parseTemplateContentParams(updateObj.getContent()));
        xxtxTemplateMapper.updateById(updateObj);
    }

    @VisibleForTesting
    public List<String> parseTemplateContentParams(String content) {
        return ReUtil.findAllGroup1(PATTERN_PARAMS, content);
    }

    @Override
    @CacheEvict(cacheNames = RedisKeyConstants.NOTIFY_TEMPLATE,
            allEntries = true) // allEntries 清空所有缓存，因为 id 不是直接的缓存 code，不好清理
    public void deleteXxtxTemplate(Long id) {
        // 校验存在
        validateXxtxTemplateExists(id);
        // 删除
        xxtxTemplateMapper.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = RedisKeyConstants.NOTIFY_TEMPLATE,
            allEntries = true) // allEntries 清空所有缓存，因为 id 不是直接的缓存 code，不好清理
    public void deleteXxtxTemplateList(List<Long> ids) {
        xxtxTemplateMapper.deleteByIds(ids);
    }

    private void validateXxtxTemplateExists(Long id) {
        if (xxtxTemplateMapper.selectById(id) == null) {
            throw exception(NOTIFY_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public XxtxTemplateDO getXxtxTemplate(Long id) {
        return xxtxTemplateMapper.selectById(id);
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.NOTIFY_TEMPLATE, key = "#code",
            unless = "#result == null")
    public XxtxTemplateDO getXxtxTemplateByCodeFromCache(String code) {
        return xxtxTemplateMapper.selectByCode(code);
    }

    @Override
    public PageResult<XxtxTemplateDO> getXxtxTemplatePage(XxtxTemplatePageReqVO pageReqVO) {
        return xxtxTemplateMapper.selectPage(pageReqVO);
    }

    @VisibleForTesting
    void validateXxtxTemplateCodeDuplicate(Long id, String code) {
        XxtxTemplateDO template = xxtxTemplateMapper.selectByCode(code);
        if (template == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw exception(NOTIFY_TEMPLATE_CODE_DUPLICATE, code);
        }
        if (!template.getId().equals(id)) {
            throw exception(NOTIFY_TEMPLATE_CODE_DUPLICATE, code);
        }
    }

    /**
     * 格式化站内信内容
     *
     * @param content 站内信模板的内容
     * @param params  站内信内容的参数
     * @return 格式化后的内容
     */
    @Override
    public String formatXxtxTemplateContent(String content, Map<String, Object> params) {
        return StrUtil.format(content, params);
    }

}

package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplatePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplateSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * 站内信模版 Service 接口
 *
 * @author xrcoder
 */
public interface XxtxTemplateService {

    /**
     * 创建站内信模版
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createXxtxTemplate(@Valid XxtxTemplateSaveReqVO createReqVO);

    /**
     * 更新站内信模版
     *
     * @param updateReqVO 更新信息
     */
    void updateXxtxTemplate(@Valid XxtxTemplateSaveReqVO updateReqVO);

    /**
     * 删除站内信模版
     *
     * @param id 编号
     */
    void deleteXxtxTemplate(Long id);

    /**
     * 批量删除站内信模版
     *
     * @param ids 编号列表
     */
    void deleteXxtxTemplateList(List<Long> ids);

    /**
     * 获得站内信模版
     *
     * @param id 编号
     * @return 站内信模版
     */
    XxtxTemplateDO getXxtxTemplate(Long id);

    /**
     * 获得站内信模板，从缓存中
     *
     * @param code 模板编码
     * @return 站内信模板
     */
    XxtxTemplateDO getXxtxTemplateByCodeFromCache(String code);

    /**
     * 获得站内信模版分页
     *
     * @param pageReqVO 分页查询
     * @return 站内信模版分页
     */
    PageResult<XxtxTemplateDO> getXxtxTemplatePage(XxtxTemplatePageReqVO pageReqVO);

    /**
     * 格式化站内信内容
     *
     * @param content 站内信模板的内容
     * @param params 站内信内容的参数
     * @return 格式化后的内容
     */
    String formatXxtxTemplateContent(String content, Map<String, Object> params);

}

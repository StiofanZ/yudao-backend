package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessageMyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx.XxtxMessageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 站内信 Service 实现类
 *
 * @author xrcoder
 */
@Service
@Validated
public class XxtxMessageServiceImpl implements XxtxMessageService {

    @Resource
    private XxtxMessageMapper xxtxMessageMapper;

    @Override
    public Long createXxtxMessage(Long userId, Integer userType,
                                  XxtxTemplateDO template, String templateContent, Map<String, Object> templateParams) {
        XxtxMessageDO message = new XxtxMessageDO().setUserId(userId).setUserType(userType)
                .setTemplateId(template.getId()).setTemplateCode(template.getCode())
                .setTemplateType(template.getType()).setTemplateNickname(template.getNickname())
                .setTemplateContent(templateContent).setTemplateParams(templateParams).setReadStatus(false);
        xxtxMessageMapper.insert(message);
        return message.getId();
    }

    @Override
    public PageResult<XxtxMessageDO> getXxtxMessagePage(XxtxMessagePageReqVO pageReqVO) {
        return xxtxMessageMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<XxtxMessageDO> getMyMyXxtxMessagePage(XxtxMessageMyPageReqVO pageReqVO, Long userId, Integer userType) {
        return xxtxMessageMapper.selectPage(pageReqVO, userId, userType);
    }

    @Override
    public XxtxMessageDO getXxtxMessage(Long id) {
        return xxtxMessageMapper.selectById(id);
    }

    @Override
    public List<XxtxMessageDO> getUnreadXxtxMessageList(Long userId, Integer userType, Integer size) {
        return xxtxMessageMapper.selectUnreadListByUserIdAndUserType(userId, userType, size);
    }

    @Override
    public Long getUnreadXxtxMessageCount(Long userId, Integer userType) {
        return xxtxMessageMapper.selectUnreadCountByUserIdAndUserType(userId, userType);
    }

    @Override
    public int updateXxtxMessageRead(Collection<Long> ids, Long userId, Integer userType) {
        return xxtxMessageMapper.updateListRead(ids, userId, userType);
    }

    @Override
    public int updateAllXxtxMessageRead(Long userId, Integer userType) {
        return xxtxMessageMapper.updateListRead(userId, userType);
    }

}

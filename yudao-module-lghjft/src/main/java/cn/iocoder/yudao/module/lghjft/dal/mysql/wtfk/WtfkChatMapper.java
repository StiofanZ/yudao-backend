package cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkChatDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 问题反馈对话记录 Mapper
 */
@Mapper
public interface WtfkChatMapper extends BaseMapperX<WtfkChatDO> {

    default List<WtfkChatDO> selectListByFeedbackId(Long feedbackId) {
        return selectList(new LambdaQueryWrapperX<WtfkChatDO>()
                .eq(WtfkChatDO::getFeedbackId, feedbackId)
                .orderByAsc(WtfkChatDO::getCreateTime)); // 对话按时间升序排列
    }
}
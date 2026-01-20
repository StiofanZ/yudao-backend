package cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 问题反馈处理日志 Mapper
 * 用于操作 lghjft_wtfk_log 表，支持多次处理记录的增删改查
 *
 * @author 管理员
 */
@Mapper
public interface WtfkLogMapper extends BaseMapperX<WtfkLogDO> {

    /**
     * 根据反馈主表 ID 获取所有处理历史记录
     * 按照创建时间升序排列，以便前端时间轴按顺序展示
     *
     * @param feedbackId 反馈主表 ID
     * @return 处理日志列表
     */
    default List<WtfkLogDO> selectListByFeedbackId(Long feedbackId) {
        return selectList(new LambdaQueryWrapperX<WtfkLogDO>()
                .eq(WtfkLogDO::getFeedbackId, feedbackId)
                .orderByAsc(WtfkLogDO::getCreateTime));
    }

}
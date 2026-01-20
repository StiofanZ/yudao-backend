package cn.iocoder.yudao.module.lghjft.dal.mysql.wtfk;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo.*;

/**
 * 工会经费通-问题反馈 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface WtfkMapper extends BaseMapperX<WtfkDO> {

    default PageResult<WtfkDO> selectPage(WtfkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WtfkDO>()
                .eqIfPresent(WtfkDO::getUserId, reqVO.getUserId())
                .likeIfPresent(WtfkDO::getUserName, reqVO.getUserName())
                .eqIfPresent(WtfkDO::getType, reqVO.getType())
                .eqIfPresent(WtfkDO::getContent, reqVO.getContent())
                .eqIfPresent(WtfkDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(WtfkDO::getContactEmail, reqVO.getContactEmail())
                .eqIfPresent(WtfkDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WtfkDO::getProcessorId, reqVO.getProcessorId())
                .betweenIfPresent(WtfkDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(WtfkDO::getProcessNotes, reqVO.getProcessNotes())
                .betweenIfPresent(WtfkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WtfkDO::getId));
    }

}
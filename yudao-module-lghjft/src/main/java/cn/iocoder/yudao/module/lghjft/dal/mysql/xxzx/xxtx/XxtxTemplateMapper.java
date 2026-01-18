package cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.template.XxtxTemplatePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XxtxTemplateMapper extends BaseMapperX<XxtxTemplateDO> {

    default XxtxTemplateDO selectByCode(String code) {
        return selectOne(XxtxTemplateDO::getCode, code);
    }

    default PageResult<XxtxTemplateDO> selectPage(XxtxTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XxtxTemplateDO>()
                .likeIfPresent(XxtxTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(XxtxTemplateDO::getName, reqVO.getName())
                .eqIfPresent(XxtxTemplateDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(XxtxTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(XxtxTemplateDO::getId));
    }

}

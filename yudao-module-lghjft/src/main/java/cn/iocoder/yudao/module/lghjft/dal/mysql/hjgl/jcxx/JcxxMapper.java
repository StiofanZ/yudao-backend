package cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.JcxxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 户籍管理/基础信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface JcxxMapper extends BaseMapperX<JcxxDO> {

    default PageResult<JcxxDO> selectPage(JcxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JcxxDO>()
                .eqIfPresent(JcxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(JcxxDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(JcxxDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(JcxxDO::getJdxzDm, reqVO.getJdxzDm())
                .eqIfPresent(JcxxDO::getZgswskfjDm, reqVO.getZgswskfjDm())
                .eqIfPresent(JcxxDO::getJcghzh, reqVO.getJcghzh())
                .likeIfPresent(JcxxDO::getJcghhm, reqVO.getJcghhm())
                .likeIfPresent(JcxxDO::getJcghhh, reqVO.getJcghhh())
                .likeIfPresent(JcxxDO::getJcghyh, reqVO.getJcghyh())
                .eqIfPresent(JcxxDO::getGhlbDm, reqVO.getGhlbDm())
                .eqIfPresent(JcxxDO::getXtlbDm, reqVO.getXtlbDm())
                .eqIfPresent(JcxxDO::getClghbj, reqVO.getClghbj())
                .betweenIfPresent(JcxxDO::getClghrq, reqVO.getBeginClghrq(), reqVO.getEndClghrq())
                .orderByDesc(JcxxDO::getUpdateTime));
    }

}

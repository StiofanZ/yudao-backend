package cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxBaseVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 户籍管理/基础信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GhHjJcxxMapper extends BaseMapperX<GhHjJcxxDO> {
    default PageResult<GhHjJcxxDO> selectPage(JcxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHjJcxxDO>()
                .eqIfPresent(GhHjJcxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHjJcxxDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhHjJcxxDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhHjJcxxDO::getJdxzDm, reqVO.getJdxzDm())
                .eqIfPresent(GhHjJcxxDO::getZgswskfjDm, reqVO.getZgswskfjDm())
                .eqIfPresent(GhHjJcxxDO::getJcghzh, reqVO.getJcghzh())
                .likeIfPresent(GhHjJcxxDO::getJcghhm, reqVO.getJcghhm())
                .likeIfPresent(GhHjJcxxDO::getJcghhh, reqVO.getJcghhh())
                .likeIfPresent(GhHjJcxxDO::getJcghyh, reqVO.getJcghyh())
                .eqIfPresent(GhHjJcxxDO::getGhlbDm, reqVO.getGhlbDm())
                .eqIfPresent(GhHjJcxxDO::getXtlbDm, reqVO.getXtlbDm())
                .eqIfPresent(GhHjJcxxDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(GhHjJcxxDO::getLxdh, reqVO.getLxdh())
                .betweenIfPresent(GhHjJcxxDO::getClghrq, reqVO.getBeginClghrq(), reqVO.getEndClghrq())
                .orderByDesc(GhHjJcxxDO::getUpdateTime));
    }
    /**
     * 户籍调拨（单个）
     */
    int updateAllocationJcxx(JcxxBaseVO baseVO);

    List<Map<String, Object>> getListByDjNsrxxDto(JcxxBaseVO dto);
}

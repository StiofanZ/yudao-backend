package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bbfb;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版本发布 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BbfbMapper extends BaseMapperX<BbfbDO> {

    default List<BbfbDO> selectList(BbfbReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BbfbDO>()
                .likeIfPresent(BbfbDO::getTitle, reqVO.getTitle())
                .likeIfPresent(BbfbDO::getVersion, reqVO.getVersion())
                .eqIfPresent(BbfbDO::getStatus, reqVO.getStatus())
                .orderByDesc(BbfbDO::getId));
    }

    /**
     * 查询版本发布分页列表（带排名）
     *
     * @param page  分页参数
     * @param reqVO 查询条件
     * @return 分页结果
     */
    IPage<BbfbDO> selectPageWithRank(IPage<BbfbDO> page, @Param("reqVO") BbfbReqVO reqVO);

}

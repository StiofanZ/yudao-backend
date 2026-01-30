package cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.tzgg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.tzgg.TzggDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TzggMapper extends BaseMapperX<TzggDO> {

    default PageResult<TzggDO> selectPage(TzggPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TzggDO>()
                .likeIfPresent(TzggDO::getTitle, reqVO.getTitle())
                .eqIfPresent(TzggDO::getStatus, reqVO.getStatus())
                .inIfPresent(TzggDO::getDeptId, reqVO.getDeptIds())
                .eqIfPresent(TzggDO::getType, reqVO.getType())
                .orderByDesc(TzggDO::getId));
    }

    /**
     * 查询公告分页列表（带排名）
     *
     * @param page  分页参数
     * @param reqVO 查询条件
     * @return 分页结果
     */
    IPage<TzggDO> selectPageWithRank(IPage<TzggDO> page, @Param("reqVO") TzggPageReqVO reqVO);

}

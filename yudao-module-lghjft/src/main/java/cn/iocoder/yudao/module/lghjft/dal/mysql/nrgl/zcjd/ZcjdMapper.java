package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcjd;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 政策解读 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ZcjdMapper extends BaseMapperX<ZcjdDO> {

    /**
     * 查询政策解读分页列表（带排名）
     *
     * @param page        分页参数
     * @param reqVO       查询条件
     * @param loginDeptId 登录用户部门ID (可为 null)
     * @param ancestorIds 上级部门ID列表
     * @return 分页结果
     */
    IPage<ZcjdDO> selectPageWithRank(IPage<ZcjdDO> page,
                                     @Param("reqVO") ZcjdPageReqVO reqVO,
                                     @Param("loginDeptId") Long loginDeptId,
                                     @Param("ancestorIds") List<Long> ancestorIds);

    default List<ZcjdDO> selectList(ZcjdPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ZcjdDO>()
                .likeIfPresent(ZcjdDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ZcjdDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ZcjdDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(ZcjdDO::getSort));
    }

}

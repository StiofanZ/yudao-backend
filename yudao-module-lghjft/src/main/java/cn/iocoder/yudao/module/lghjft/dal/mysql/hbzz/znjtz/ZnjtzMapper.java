package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjtz.ZnjtzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ZnjtzMapper extends BaseMapperX<ZnjtzDO> {

    default PageResult<ZnjtzDO> selectPage(ZnjtzPageReqVO reqVO) {
        List<ZnjtzDO> records = selectZnjtzList(reqVO);
        if (reqVO.getPageSize() == null || reqVO.getPageSize() < 0) {
            return new PageResult<>(records, (long) records.size());
        }
        int fromIndex = Math.max((reqVO.getPageNo() - 1) * reqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + reqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }

    List<ZnjtzDO> selectZnjtzList(@Param("req") ZnjtzPageReqVO reqVO);

    /**
     * v1 cascade delete from cxtj_cbjqrfb
     */
    void deleteCbjqrfbByGhjfIds(@Param("ghjfIds") List<Long> ghjfIds);

    void deleteCbjqrfbByGhjfId(@Param("ghjfId") Long ghjfId);
}

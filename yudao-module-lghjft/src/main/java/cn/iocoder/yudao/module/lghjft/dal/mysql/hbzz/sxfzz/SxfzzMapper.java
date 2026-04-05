package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.sxfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.sxfzz.SxfzzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SxfzzMapper extends BaseMapperX<SxfzzDO> {

    default PageResult<SxfzzDO> selectPage(SxfzzPageReqVO reqVO) {
        QueryWrapper<SxfzzDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件：lx in ('8','C')
        wrapper.in("LX", "8", "C");
        wrapper.eq("YXBJ", "Y");

        // 搜索条件
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getLx() != null && !reqVO.getLx().isEmpty()) {
            wrapper.eq("LX", reqVO.getLx());
        }
        // v1: deptId 通过 dm_swjg 关联 GHJG_DM 过滤
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.inSql("MID(DZ,1,11)", "SELECT SWJG_DM FROM dm_swjg WHERE GHJG_DM = '" + reqVO.getDeptId() + "'");
        }

        wrapper.orderByDesc("HKPCH");

        return selectPage(reqVO, wrapper);
    }
}

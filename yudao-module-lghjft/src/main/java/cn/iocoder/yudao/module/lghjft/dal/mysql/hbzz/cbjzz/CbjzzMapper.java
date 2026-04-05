package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.cbjzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz.CbjzzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CbjzzMapper extends BaseMapperX<CbjzzDO> {

    default PageResult<CbjzzDO> selectPage(CbjzzPageReqVO reqVO) {
        QueryWrapper<CbjzzDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件：返还基层筹备金
        wrapper.apply("instr(FY,'返还基层筹备金') > 0");
        wrapper.eq("YXBJ", "Y");

        // 搜索条件
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }

        // v1 ORDER BY: dz asc, hkpch desc
        wrapper.orderByAsc("DZ");
        wrapper.orderByDesc("HKPCH");

        return selectPage(reqVO, wrapper);
    }
}

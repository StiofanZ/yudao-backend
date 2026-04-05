package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.JcjfzzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JcjfzzMapper extends BaseMapperX<JcjfzzDO> {

    default PageResult<JcjfzzDO> selectPage(JcjfzzPageReqVO reqVO) {
        QueryWrapper<JcjfzzDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件：工会费/工会经费 且含返还基层
        wrapper.and(w -> w.apply("instr(FY,'工会费') > 0").or().apply("instr(FY,'工会经费') > 0"));
        wrapper.like("FY", "返还基层");
        wrapper.eq("YXBJ", "Y");

        // 搜索条件
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getLx() != null && !reqVO.getLx().isEmpty()) {
            wrapper.eq("LX", reqVO.getLx());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }

        wrapper.orderByDesc("HKPCH");

        return selectPage(reqVO, wrapper);
    }
}

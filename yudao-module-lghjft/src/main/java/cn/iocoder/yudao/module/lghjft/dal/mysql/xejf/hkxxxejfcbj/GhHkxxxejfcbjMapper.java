package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHkxxxejfcbjMapper extends BaseMapperX<GhHkxxxejfcbjDO> {

    /**
     * v1 selectGhHkxxxejfcbjList 完整条件迁移
     * 关键: MID(HKPCH,1,8) between 日期范围, QRRQ between, order by dz asc
     */
    default PageResult<GhHkxxxejfcbjDO> selectPage(GhHkxxxejfcbjPageReqVO reqVO) {
        QueryWrapper<GhHkxxxejfcbjDO> wrapper = new QueryWrapper<>();
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("hkpch", reqVO.getHkpch());
        }
        // v1: MID(HKPCH,1,8) between beginHkpch and endHkpch
        if (reqVO.getBeginHkpch() != null && !reqVO.getBeginHkpch().isEmpty()
                && reqVO.getEndHkpch() != null && !reqVO.getEndHkpch().isEmpty()) {
            wrapper.apply("mid(hkpch,1,8) between {0} and {1}",
                    reqVO.getBeginHkpch(), reqVO.getEndHkpch());
        }
        // Note: qrrq is on gh_hkxx_qrsz, not on gh_hkxxxejf; skip this filter
        // for single-table QueryWrapper to avoid SQL error.
        if (reqVO.getJfqj() != null) {
            wrapper.eq("jfqj", reqVO.getJfqj());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        // v1: order by dz asc
        wrapper.orderByAsc("dz");
        return selectPage(reqVO, wrapper);
    }
}

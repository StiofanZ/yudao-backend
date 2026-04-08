package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jczhkxwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jczhkxwh.JczhkxwhDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JczhkxwhMapper extends BaseMapperX<JczhkxwhDO> {

    default PageResult<JczhkxwhDO> selectPage(JczhkxwhPageReqVO reqVO) {
        QueryWrapper<JczhkxwhDO> wrapper = new QueryWrapper<>();

        // v1 core filter: jcghzh/jcghhm/jcghhh incomplete
        wrapper.and(w -> w.isNull("jcghzh").or().isNull("jcghhm").or().isNull("jcghhh"));

        // v1 core filter: exclude specific xtlb_dm
        wrapper.and(w -> w.notIn("xtlb_dm", "1000", "1100", "2000", "2100", "2200").or().isNull("xtlb_dm"));

        // v1 core filter: exclude ghlb_dm='13'
        wrapper.and(w -> w.isNull("ghlb_dm").or().ne("ghlb_dm", "13"));

        // v1 core filter: must have specific jf records
        wrapper.inSql("djxh",
                "select distinct djxh from gh_jf where (jcghzh is null or jcghhm is null or jcghhh is null) " +
                        "and zspm_dm = '399001010' and jsbj = 'E'");

        // search conditions
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("clghbj", reqVO.getClghbj());
        }

        wrapper.orderByAsc("djxh");

        return selectPage(reqVO, wrapper);
    }
}

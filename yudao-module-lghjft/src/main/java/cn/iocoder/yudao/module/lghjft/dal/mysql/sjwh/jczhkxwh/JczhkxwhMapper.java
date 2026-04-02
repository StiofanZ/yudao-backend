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

        // v1 核心过滤：基层工会账户信息不完整
        wrapper.and(w -> w.isNull("JCGHZH").or().isNull("JCGHHM").or().isNull("JCGHHH"));

        // v1 核心过滤：排除特定系统类别
        wrapper.and(w -> w.notIn("XTLB_DM", "1000", "1100", "2000", "2100", "2200").or().isNull("XTLB_DM"));

        // v1 核心过滤：排除工会类别13
        wrapper.and(w -> w.isNull("GHLB_DM").or().ne("GHLB_DM", "13"));

        // v1 核心过滤：必须有特定经费记录
        wrapper.inSql("DJXH",
                "SELECT DISTINCT djxh FROM gh_jf WHERE (JCGHZH IS NULL OR JCGHHM IS NULL OR JCGHHH IS NULL) " +
                        "AND ZSPM_DM = '399001010' AND JSBJ = 'E'");

        // 搜索条件
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("DEPT_ID", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("CLGHBJ", reqVO.getClghbj());
        }

        wrapper.orderByAsc("DJXH");

        return selectPage(reqVO, wrapper);
    }
}

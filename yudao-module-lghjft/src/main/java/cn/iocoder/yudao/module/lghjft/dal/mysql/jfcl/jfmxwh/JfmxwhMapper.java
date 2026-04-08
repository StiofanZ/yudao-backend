package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfmxwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfmxwh.JfmxwhDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JfmxwhMapper extends BaseMapperX<JfmxwhDO> {

    default PageResult<JfmxwhDO> selectPage(JfmxwhPageReqVO reqVO) {
        QueryWrapper<JfmxwhDO> wrapper = new QueryWrapper<>();
        // v1 dual-dept: (dept_id = #{deptId} or SDGHJG_DM = #{deptId})
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w.eq("dept_id", reqVO.getDeptId())
                    .or().eq("sdghjg_dm", reqVO.getDeptId()));
        }
        if (reqVO.getSpuuid() != null && !reqVO.getSpuuid().isEmpty()) {
            wrapper.eq("spuuid", reqVO.getSpuuid());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc uses LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("zgswj_dm", reqVO.getZgswjDm());
        }
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("ghlb_dm", reqVO.getGhlbDm());
        }
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("xtlb_dm", reqVO.getXtlbDm());
        }
        // v1: skssqq >= #{skssqq}
        if (reqVO.getSkssqq() != null && !reqVO.getSkssqq().isEmpty()) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        // v1: skssqz <= #{skssqz}
        if (reqVO.getSkssqz() != null && !reqVO.getSkssqz().isEmpty()) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        if (reqVO.getYbtse() != null) {
            wrapper.eq("ybtse", reqVO.getYbtse());
        }
        if (reqVO.getJfzh() != null && !reqVO.getJfzh().isEmpty()) {
            wrapper.eq("jfzh", reqVO.getJfzh());
        }
        if (reqVO.getJfhm() != null && !reqVO.getJfhm().isEmpty()) {
            wrapper.eq("jfhm", reqVO.getJfhm());
        }
        // v1: rkrq between
        if (reqVO.getRkrqStart() != null && !reqVO.getRkrqStart().isEmpty()
                && reqVO.getRkrqEnd() != null && !reqVO.getRkrqEnd().isEmpty()) {
            wrapper.between("rkrq", reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("cbjthbj", reqVO.getCbjthbj());
        }
        // v1: jsrq between
        if (reqVO.getJsrqStart() != null && !reqVO.getJsrqStart().isEmpty()
                && reqVO.getJsrqEnd() != null && !reqVO.getJsrqEnd().isEmpty()) {
            wrapper.between("jsrq", reqVO.getJsrqStart(), reqVO.getJsrqEnd());
        }
        if (reqVO.getJsczy() != null && !reqVO.getJsczy().isEmpty()) {
            wrapper.eq("jsczy", reqVO.getJsczy());
        }
        if (reqVO.getJcghzh() != null && !reqVO.getJcghzh().isEmpty()) {
            wrapper.eq("jcghzh", reqVO.getJcghzh());
        }
        if (reqVO.getJcghhh() != null && !reqVO.getJcghhh().isEmpty()) {
            wrapper.eq("jcghhh", reqVO.getJcghhh());
        }
        if (reqVO.getJcghje() != null) {
            wrapper.eq("jcghje", reqVO.getJcghje());
        }
        // v1: hkpch uses LIKE
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("hkpch", reqVO.getHkpch());
        }
        // v1: bz uses LIKE
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.like("bz", reqVO.getBz());
        }
        if (reqVO.getBluuid() != null && !reqVO.getBluuid().isEmpty()) {
            wrapper.eq("bluuid", reqVO.getBluuid());
        }
        // v1: order by rkrq desc
        wrapper.orderByDesc("rkrq");
        return selectPage(reqVO, wrapper);
    }
}

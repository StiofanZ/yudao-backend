package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DhjftzMapper extends BaseMapperX<DhjftzDO> {

    /**
     * 单户经费台账分页 — 还原 v1 selectDhjftzList 完整 WHERE 条件
     * 表: gh_hj
     */
    default PageResult<DhjftzDO> selectPage(DhjftzPageReqVO reqVO) {
        QueryWrapper<DhjftzDO> wrapper = new QueryWrapper<>();

        // --- V1 完整条件 (SQL 列名全小写) ---
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getHyghbz() != null && !reqVO.getHyghbz().isEmpty()) {
            wrapper.eq("hyghbz", reqVO.getHyghbz());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        if (reqVO.getNsrsbh() != null && !reqVO.getNsrsbh().isEmpty()) {
            wrapper.eq("nsrsbh", reqVO.getNsrsbh());
        }
        // v1: nsrmc / nsrjc / zgswjmc / zgswskfjmc / zcdz / jcghmc / jcghhm / jcghyh 用 LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getNsrjc() != null && !reqVO.getNsrjc().isEmpty()) {
            wrapper.like("nsrjc", reqVO.getNsrjc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("zgswj_dm", reqVO.getZgswjDm());
        }
        if (reqVO.getZgswjmc() != null && !reqVO.getZgswjmc().isEmpty()) {
            wrapper.like("zgswjmc", reqVO.getZgswjmc());
        }
        if (reqVO.getZgswskfjDm() != null && !reqVO.getZgswskfjDm().isEmpty()) {
            wrapper.eq("zgswskfj_dm", reqVO.getZgswskfjDm());
        }
        if (reqVO.getZgswskfjmc() != null && !reqVO.getZgswskfjmc().isEmpty()) {
            wrapper.like("zgswskfjmc", reqVO.getZgswskfjmc());
        }
        if (reqVO.getSsglyDm() != null && !reqVO.getSsglyDm().isEmpty()) {
            wrapper.eq("ssgly_dm", reqVO.getSsglyDm());
        }
        if (reqVO.getSsglyxm() != null && !reqVO.getSsglyxm().isEmpty()) {
            wrapper.eq("ssglyxm", reqVO.getSsglyxm());
        }
        if (reqVO.getZzjglxDm() != null && !reqVO.getZzjglxDm().isEmpty()) {
            wrapper.eq("zzjglx_dm", reqVO.getZzjglxDm());
        }
        if (reqVO.getZzjglxmc() != null && !reqVO.getZzjglxmc().isEmpty()) {
            wrapper.eq("zzjglxmc", reqVO.getZzjglxmc());
        }
        if (reqVO.getHyDm() != null && !reqVO.getHyDm().isEmpty()) {
            wrapper.eq("hy_dm", reqVO.getHyDm());
        }
        if (reqVO.getHymc() != null && !reqVO.getHymc().isEmpty()) {
            wrapper.eq("hymc", reqVO.getHymc());
        }
        if (reqVO.getDjzclxDm() != null && !reqVO.getDjzclxDm().isEmpty()) {
            wrapper.eq("djzclx_dm", reqVO.getDjzclxDm());
        }
        if (reqVO.getDjzclxmc() != null && !reqVO.getDjzclxmc().isEmpty()) {
            wrapper.eq("djzclxmc", reqVO.getDjzclxmc());
        }
        if (reqVO.getDwlsgxDm() != null && !reqVO.getDwlsgxDm().isEmpty()) {
            wrapper.eq("dwlsgx_dm", reqVO.getDwlsgxDm());
        }
        if (reqVO.getDwlsgxmc() != null && !reqVO.getDwlsgxmc().isEmpty()) {
            wrapper.eq("dwlsgxmc", reqVO.getDwlsgxmc());
        }
        if (reqVO.getZgrs() != null) {
            wrapper.eq("zgrs", reqVO.getZgrs());
        }
        if (reqVO.getNsrztDm() != null && !reqVO.getNsrztDm().isEmpty()) {
            wrapper.eq("nsrzt_dm", reqVO.getNsrztDm());
        }
        if (reqVO.getNsrztmc() != null && !reqVO.getNsrztmc().isEmpty()) {
            wrapper.eq("nsrztmc", reqVO.getNsrztmc());
        }
        if (reqVO.getZcdz() != null && !reqVO.getZcdz().isEmpty()) {
            wrapper.like("zcdz", reqVO.getZcdz());
        }
        if (reqVO.getYzbm() != null && !reqVO.getYzbm().isEmpty()) {
            wrapper.eq("yzbm", reqVO.getYzbm());
        }
        if (reqVO.getLxr() != null && !reqVO.getLxr().isEmpty()) {
            wrapper.eq("lxr", reqVO.getLxr());
        }
        if (reqVO.getLxdh() != null && !reqVO.getLxdh().isEmpty()) {
            wrapper.eq("lxdh", reqVO.getLxdh());
        }
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("ghlb_dm", reqVO.getGhlbDm());
        }
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("xtlb_dm", reqVO.getXtlbDm());
        }
        if (reqVO.getHjfl1Dm() != null && !reqVO.getHjfl1Dm().isEmpty()) {
            wrapper.eq("hjfl1_dm", reqVO.getHjfl1Dm());
        }
        if (reqVO.getHjfl2Dm() != null && !reqVO.getHjfl2Dm().isEmpty()) {
            wrapper.eq("hjfl2_dm", reqVO.getHjfl2Dm());
        }
        if (reqVO.getHjfl3Dm() != null && !reqVO.getHjfl3Dm().isEmpty()) {
            wrapper.eq("hjfl3_dm", reqVO.getHjfl3Dm());
        }
        if (reqVO.getHjfl4Dm() != null && !reqVO.getHjfl4Dm().isEmpty()) {
            wrapper.eq("hjfl4_dm", reqVO.getHjfl4Dm());
        }
        if (reqVO.getHjfl5Dm() != null && !reqVO.getHjfl5Dm().isEmpty()) {
            wrapper.eq("hjfl5_dm", reqVO.getHjfl5Dm());
        }
        if (reqVO.getHjfl6Dm() != null && !reqVO.getHjfl6Dm().isEmpty()) {
            wrapper.eq("hjfl6_dm", reqVO.getHjfl6Dm());
        }
        if (reqVO.getHjfl7Dm() != null && !reqVO.getHjfl7Dm().isEmpty()) {
            wrapper.eq("hjfl7_dm", reqVO.getHjfl7Dm());
        }
        if (reqVO.getHjfl8Dm() != null && !reqVO.getHjfl8Dm().isEmpty()) {
            wrapper.eq("hjfl8_dm", reqVO.getHjfl8Dm());
        }
        if (reqVO.getHjfl9Dm() != null && !reqVO.getHjfl9Dm().isEmpty()) {
            wrapper.eq("hjfl9_dm", reqVO.getHjfl9Dm());
        }
        if (reqVO.getSdghjgDm() != null && !reqVO.getSdghjgDm().isEmpty()) {
            wrapper.eq("sdghjg_dm", reqVO.getSdghjgDm());
        }
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("clghbj", reqVO.getClghbj());
        }
        // v1 date range: CLGHRQ between
        if (reqVO.getBeginClghrq() != null && !reqVO.getBeginClghrq().isEmpty()
                && reqVO.getEndClghrq() != null && !reqVO.getEndClghrq().isEmpty()) {
            wrapper.between("clghrq", reqVO.getBeginClghrq(), reqVO.getEndClghrq());
        }
        if (reqVO.getJcghdm() != null && !reqVO.getJcghdm().isEmpty()) {
            wrapper.eq("jcghdm", reqVO.getJcghdm());
        }
        if (reqVO.getJcghmc() != null && !reqVO.getJcghmc().isEmpty()) {
            wrapper.like("jcghmc", reqVO.getJcghmc());
        }
        if (reqVO.getJcghzh() != null && !reqVO.getJcghzh().isEmpty()) {
            wrapper.eq("jcghzh", reqVO.getJcghzh());
        }
        if (reqVO.getJcghhm() != null && !reqVO.getJcghhm().isEmpty()) {
            wrapper.like("jcghhm", reqVO.getJcghhm());
        }
        if (reqVO.getJcghhh() != null && !reqVO.getJcghhh().isEmpty()) {
            wrapper.eq("jcghhh", reqVO.getJcghhh());
        }
        if (reqVO.getJcghyh() != null && !reqVO.getJcghyh().isEmpty()) {
            wrapper.like("jcghyh", reqVO.getJcghyh());
        }
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.eq("bz", reqVO.getBz());
        }
        if (reqVO.getJym() != null && !reqVO.getJym().isEmpty()) {
            wrapper.eq("jym", reqVO.getJym());
        }

        return selectPage(reqVO, wrapper);
    }
}

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

        // --- V1 完整条件 ---
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getHyghbz() != null && !reqVO.getHyghbz().isEmpty()) {
            wrapper.eq("HYGHBZ", reqVO.getHyghbz());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("DJXH", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrsbh() != null && !reqVO.getNsrsbh().isEmpty()) {
            wrapper.eq("NSRSBH", reqVO.getNsrsbh());
        }
        // v1: nsrmc / nsrjc / zgswjmc / zgswskfjmc / zcdz / jcghmc / jcghhm / jcghyh 用 LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getNsrjc() != null && !reqVO.getNsrjc().isEmpty()) {
            wrapper.like("NSRJC", reqVO.getNsrjc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("ZGSWJ_DM", reqVO.getZgswjDm());
        }
        if (reqVO.getZgswjmc() != null && !reqVO.getZgswjmc().isEmpty()) {
            wrapper.like("ZGSWJMC", reqVO.getZgswjmc());
        }
        if (reqVO.getZgswskfjDm() != null && !reqVO.getZgswskfjDm().isEmpty()) {
            wrapper.eq("ZGSWSKFJ_DM", reqVO.getZgswskfjDm());
        }
        if (reqVO.getZgswskfjmc() != null && !reqVO.getZgswskfjmc().isEmpty()) {
            wrapper.like("ZGSWSKFJMC", reqVO.getZgswskfjmc());
        }
        if (reqVO.getSsglyDm() != null && !reqVO.getSsglyDm().isEmpty()) {
            wrapper.eq("SSGLY_DM", reqVO.getSsglyDm());
        }
        if (reqVO.getSsglyxm() != null && !reqVO.getSsglyxm().isEmpty()) {
            wrapper.eq("SSGLYXM", reqVO.getSsglyxm());
        }
        if (reqVO.getZzjglxDm() != null && !reqVO.getZzjglxDm().isEmpty()) {
            wrapper.eq("ZZJGLX_DM", reqVO.getZzjglxDm());
        }
        if (reqVO.getZzjglxmc() != null && !reqVO.getZzjglxmc().isEmpty()) {
            wrapper.eq("ZZJGLXMC", reqVO.getZzjglxmc());
        }
        if (reqVO.getHyDm() != null && !reqVO.getHyDm().isEmpty()) {
            wrapper.eq("HY_DM", reqVO.getHyDm());
        }
        if (reqVO.getHymc() != null && !reqVO.getHymc().isEmpty()) {
            wrapper.eq("HYMC", reqVO.getHymc());
        }
        if (reqVO.getDjzclxDm() != null && !reqVO.getDjzclxDm().isEmpty()) {
            wrapper.eq("DJZCLX_DM", reqVO.getDjzclxDm());
        }
        if (reqVO.getDjzclxmc() != null && !reqVO.getDjzclxmc().isEmpty()) {
            wrapper.eq("DJZCLXMC", reqVO.getDjzclxmc());
        }
        if (reqVO.getDwlsgxDm() != null && !reqVO.getDwlsgxDm().isEmpty()) {
            wrapper.eq("DWLSGX_DM", reqVO.getDwlsgxDm());
        }
        if (reqVO.getDwlsgxmc() != null && !reqVO.getDwlsgxmc().isEmpty()) {
            wrapper.eq("DWLSGXMC", reqVO.getDwlsgxmc());
        }
        if (reqVO.getZgrs() != null) {
            wrapper.eq("ZGRS", reqVO.getZgrs());
        }
        if (reqVO.getNsrztDm() != null && !reqVO.getNsrztDm().isEmpty()) {
            wrapper.eq("NSRZT_DM", reqVO.getNsrztDm());
        }
        if (reqVO.getNsrztmc() != null && !reqVO.getNsrztmc().isEmpty()) {
            wrapper.eq("NSRZTMC", reqVO.getNsrztmc());
        }
        if (reqVO.getZcdz() != null && !reqVO.getZcdz().isEmpty()) {
            wrapper.like("ZCDZ", reqVO.getZcdz());
        }
        if (reqVO.getYzbm() != null && !reqVO.getYzbm().isEmpty()) {
            wrapper.eq("YZBM", reqVO.getYzbm());
        }
        if (reqVO.getLxr() != null && !reqVO.getLxr().isEmpty()) {
            wrapper.eq("LXR", reqVO.getLxr());
        }
        if (reqVO.getLxdh() != null && !reqVO.getLxdh().isEmpty()) {
            wrapper.eq("LXDH", reqVO.getLxdh());
        }
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("GHLB_DM", reqVO.getGhlbDm());
        }
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("XTLB_DM", reqVO.getXtlbDm());
        }
        if (reqVO.getHjfl1Dm() != null && !reqVO.getHjfl1Dm().isEmpty()) {
            wrapper.eq("HJFL1_DM", reqVO.getHjfl1Dm());
        }
        if (reqVO.getHjfl2Dm() != null && !reqVO.getHjfl2Dm().isEmpty()) {
            wrapper.eq("HJFL2_DM", reqVO.getHjfl2Dm());
        }
        if (reqVO.getHjfl3Dm() != null && !reqVO.getHjfl3Dm().isEmpty()) {
            wrapper.eq("HJFL3_DM", reqVO.getHjfl3Dm());
        }
        if (reqVO.getHjfl4Dm() != null && !reqVO.getHjfl4Dm().isEmpty()) {
            wrapper.eq("HJFL4_DM", reqVO.getHjfl4Dm());
        }
        if (reqVO.getHjfl5Dm() != null && !reqVO.getHjfl5Dm().isEmpty()) {
            wrapper.eq("HJFL5_DM", reqVO.getHjfl5Dm());
        }
        if (reqVO.getHjfl6Dm() != null && !reqVO.getHjfl6Dm().isEmpty()) {
            wrapper.eq("HJFL6_DM", reqVO.getHjfl6Dm());
        }
        if (reqVO.getHjfl7Dm() != null && !reqVO.getHjfl7Dm().isEmpty()) {
            wrapper.eq("HJFL7_DM", reqVO.getHjfl7Dm());
        }
        if (reqVO.getHjfl8Dm() != null && !reqVO.getHjfl8Dm().isEmpty()) {
            wrapper.eq("HJFL8_DM", reqVO.getHjfl8Dm());
        }
        if (reqVO.getHjfl9Dm() != null && !reqVO.getHjfl9Dm().isEmpty()) {
            wrapper.eq("HJFL9_DM", reqVO.getHjfl9Dm());
        }
        if (reqVO.getSdghjgDm() != null && !reqVO.getSdghjgDm().isEmpty()) {
            wrapper.eq("SDGHJG_DM", reqVO.getSdghjgDm());
        }
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("CLGHBJ", reqVO.getClghbj());
        }
        // v1 date range: CLGHRQ between
        if (reqVO.getBeginClghrq() != null && !reqVO.getBeginClghrq().isEmpty()
                && reqVO.getEndClghrq() != null && !reqVO.getEndClghrq().isEmpty()) {
            wrapper.between("CLGHRQ", reqVO.getBeginClghrq(), reqVO.getEndClghrq());
        }
        if (reqVO.getJcghdm() != null && !reqVO.getJcghdm().isEmpty()) {
            wrapper.eq("JCGHDM", reqVO.getJcghdm());
        }
        if (reqVO.getJcghmc() != null && !reqVO.getJcghmc().isEmpty()) {
            wrapper.like("JCGHMC", reqVO.getJcghmc());
        }
        if (reqVO.getJcghzh() != null && !reqVO.getJcghzh().isEmpty()) {
            wrapper.eq("JCGHZH", reqVO.getJcghzh());
        }
        if (reqVO.getJcghhm() != null && !reqVO.getJcghhm().isEmpty()) {
            wrapper.like("JCGHHM", reqVO.getJcghhm());
        }
        if (reqVO.getJcghhh() != null && !reqVO.getJcghhh().isEmpty()) {
            wrapper.eq("JCGHHH", reqVO.getJcghhh());
        }
        if (reqVO.getJcghyh() != null && !reqVO.getJcghyh().isEmpty()) {
            wrapper.like("JCGHYH", reqVO.getJcghyh());
        }
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.eq("BZ", reqVO.getBz());
        }
        if (reqVO.getJym() != null && !reqVO.getJym().isEmpty()) {
            wrapper.eq("JYM", reqVO.getJym());
        }

        return selectPage(reqVO, wrapper);
    }
}

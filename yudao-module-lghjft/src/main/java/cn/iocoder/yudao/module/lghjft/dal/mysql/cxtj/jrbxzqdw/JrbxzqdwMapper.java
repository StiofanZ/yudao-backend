package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JrbxzqdwMapper extends BaseMapperX<JrbxzqdwDO> {

    /**
     * 金融保险证券单位分页 -- 还原 V1 selectJrbxzqdwList 完整 WHERE + ORDER BY
     *
     * <p>V1 SQL 使用 LEFT JOIN gh_hj / jrbxzqdwjfqk1 / jrbxzqdwjfqk，
     * V2 为单表查询，deptId 条件通过 DWDM 和 gh_hj 子查询实现。
     */
    default PageResult<JrbxzqdwDO> selectPage(JrbxzqdwPageReqVO reqVO) {
        QueryWrapper<JrbxzqdwDO> wrapper = new QueryWrapper<>();

        // V1: and t.DWDM = #{dwdm}
        if (reqVO.getDwdm() != null && !reqVO.getDwdm().isEmpty()) {
            wrapper.eq("dwdm", reqVO.getDwdm());
        }
        // V1: and (t.DWDM=#{deptId} or h.DEPT_ID = #{deptId})
        // 单表查询: DWDM 直接匹配 或 DJXH 在 gh_hj 中匹配 dept_id
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w
                    .eq("dwdm", reqVO.getDeptId())
                    .or()
                    .inSql("djxh", "select djxh from gh_hj where dept_id = '" + reqVO.getDeptId() + "'"));
        }
        // V1: and t.SHXYDM = #{shxydm}
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // V1: and NSRSBH = #{nsrsbh}
        if (reqVO.getNsrsbh() != null && !reqVO.getNsrsbh().isEmpty()) {
            wrapper.eq("nsrsbh", reqVO.getNsrsbh());
        }
        // V1: and t.NSRMC like concat('%', #{nsrmc}, '%')
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        // V1: and DJXH = #{djxh}
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        // V1: and t.ZGJRGHZGDWBZ = #{zgjrghzgdwbz}
        if (reqVO.getZgjrghzgdwbz() != null && !reqVO.getZgjrghzgdwbz().isEmpty()) {
            wrapper.eq("zgjrghzgdwbz", reqVO.getZgjrghzgdwbz());
        }
        // V1: and nsrzt_dm = #{nsrztDm}
        if (reqVO.getNsrztDm() != null && !reqVO.getNsrztDm().isEmpty()) {
            wrapper.eq("nsrzt_dm", reqVO.getNsrztDm());
        }
        // V1: and DJZCLX_DM = #{djzclxDm}
        if (reqVO.getDjzclxDm() != null && !reqVO.getDjzclxDm().isEmpty()) {
            wrapper.eq("djzclx_dm", reqVO.getDjzclxDm());
        }
        // V1: and djzclxmc = #{djzclxmc}
        if (reqVO.getDjzclxmc() != null && !reqVO.getDjzclxmc().isEmpty()) {
            wrapper.eq("djzclxmc", reqVO.getDjzclxmc());
        }
        // V1: and SCJYDZ = #{scjydz}
        if (reqVO.getScjydz() != null && !reqVO.getScjydz().isEmpty()) {
            wrapper.eq("scjydz", reqVO.getScjydz());
        }
        // V1: and SCJYDZXZQHSZ_DM = #{scjydzxzqhszDm}
        if (reqVO.getScjydzxzqhszDm() != null && !reqVO.getScjydzxzqhszDm().isEmpty()) {
            wrapper.eq("scjydzxzqhsz_dm", reqVO.getScjydzxzqhszDm());
        }
        // V1: and XZQHMC = #{xzqhmc}
        if (reqVO.getXzqhmc() != null && !reqVO.getXzqhmc().isEmpty()) {
            wrapper.eq("xzqhmc", reqVO.getXzqhmc());
        }
        // V1: and t.JDXZ_DM = #{jdxzDm}
        if (reqVO.getJdxzDm() != null && !reqVO.getJdxzDm().isEmpty()) {
            wrapper.eq("jdxz_dm", reqVO.getJdxzDm());
        }
        // V1: and HY_DM = #{hyDm}
        if (reqVO.getHyDm() != null && !reqVO.getHyDm().isEmpty()) {
            wrapper.eq("hy_dm", reqVO.getHyDm());
        }
        // V1: and HYMC = #{hymc}
        if (reqVO.getHymc() != null && !reqVO.getHymc().isEmpty()) {
            wrapper.eq("hymc", reqVO.getHymc());
        }
        // V1: and DJJG_DM = #{djjgDm}
        if (reqVO.getDjjgDm() != null && !reqVO.getDjjgDm().isEmpty()) {
            wrapper.eq("djjg_dm", reqVO.getDjjgDm());
        }
        // V1: and djjgmc = #{djjgmc}
        if (reqVO.getDjjgmc() != null && !reqVO.getDjjgmc().isEmpty()) {
            wrapper.eq("djjgmc", reqVO.getDjjgmc());
        }
        // V1: and ZGSWJ_DM = #{zgswjDm}
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("zgswj_dm", reqVO.getZgswjDm());
        }
        // V1: and zgswjmc = #{zgswjmc}
        if (reqVO.getZgswjmc() != null && !reqVO.getZgswjmc().isEmpty()) {
            wrapper.eq("zgswjmc", reqVO.getZgswjmc());
        }
        // V1: and ZGSWSKFJ_DM = #{zgswskfjDm}
        if (reqVO.getZgswskfjDm() != null && !reqVO.getZgswskfjDm().isEmpty()) {
            wrapper.eq("zgswskfj_dm", reqVO.getZgswskfjDm());
        }
        // V1: and zgksfjmc = #{zgksfjmc}
        if (reqVO.getZgksfjmc() != null && !reqVO.getZgksfjmc().isEmpty()) {
            wrapper.eq("zgksfjmc", reqVO.getZgksfjmc());
        }
        // V1: and CWFZRXM = #{cwfzrxm}
        if (reqVO.getCwfzrxm() != null && !reqVO.getCwfzrxm().isEmpty()) {
            wrapper.eq("cwfzrxm", reqVO.getCwfzrxm());
        }
        // V1: and CWFZRGDDH = #{cwfzrgddh}
        if (reqVO.getCwfzrgddh() != null && !reqVO.getCwfzrgddh().isEmpty()) {
            wrapper.eq("cwfzrgddh", reqVO.getCwfzrgddh());
        }
        // V1: and CWFZRYDDH = #{cwfzryddh}
        if (reqVO.getCwfzryddh() != null && !reqVO.getCwfzryddh().isEmpty()) {
            wrapper.eq("cwfzryddh", reqVO.getCwfzryddh());
        }
        // V1: and CYRS = #{cyrs}
        if (reqVO.getCyrs() != null) {
            wrapper.eq("cyrs", reqVO.getCyrs());
        }
        // V1: and BZ = #{bz}
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.eq("bz", reqVO.getBz());
        }
        // V1: and t.HSJG = #{hsjg}
        if (reqVO.getHsjg() != null && !reqVO.getHsjg().isEmpty()) {
            wrapper.eq("hsjg", reqVO.getHsjg());
        }
        // V1: and sl = #{sl}  (sl is from joined table jrbxzqdwjfqk1, skip for single-table)
        // Note: sl does not exist in jrbxzqdw table; omitted from single-table query

        // V1: order by b.gzze desc (from joined table jrbxzqdwjfqk1)
        // Single-table fallback: order by id desc
        wrapper.orderByDesc("id");

        return selectPage(reqVO, wrapper);
    }
}

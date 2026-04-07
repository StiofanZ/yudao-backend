package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhjfjfdwMapper extends BaseMapperX<GhjfjfdwDO> {

    /**
     * 近三年缴费情况分页 -- 还原 V1 selectGhjfjfdwList 完整 17 个 WHERE + ORDER BY jfjebn desc
     */
    default PageResult<GhjfjfdwDO> selectPage(GhjfjfdwPageReqVO reqVO) {
        QueryWrapper<GhjfjfdwDO> wrapper = new QueryWrapper<>();

        // --- 字符串字段：非空则 eq / like ---
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // V1: nsrmc 用 LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getZsswjgDm() != null && !reqVO.getZsswjgDm().isEmpty()) {
            wrapper.eq("zsswjg_dm", reqVO.getZsswjgDm());
        }
        if (reqVO.getDjxhbn() != null && !reqVO.getDjxhbn().isEmpty()) {
            wrapper.eq("djxhbn", reqVO.getDjxhbn());
        }

        // --- 数值字段：非 null 则 eq ---
        if (reqVO.getBsbn() != null) {
            wrapper.eq("bsbn", reqVO.getBsbn());
        }
        if (reqVO.getYsbn() != null) {
            wrapper.eq("ysbn", reqVO.getYsbn());
        }
        if (reqVO.getJfjebn() != null) {
            wrapper.eq("jfjebn", reqVO.getJfjebn());
        }

        if (reqVO.getDjxhsn() != null && !reqVO.getDjxhsn().isEmpty()) {
            wrapper.eq("djxhsn", reqVO.getDjxhsn());
        }
        if (reqVO.getBssn() != null) {
            wrapper.eq("bssn", reqVO.getBssn());
        }
        if (reqVO.getYssn() != null) {
            wrapper.eq("yssn", reqVO.getYssn());
        }
        if (reqVO.getJfjesn() != null) {
            wrapper.eq("jfjesn", reqVO.getJfjesn());
        }

        if (reqVO.getDjxhqn() != null && !reqVO.getDjxhqn().isEmpty()) {
            wrapper.eq("djxhqn", reqVO.getDjxhqn());
        }
        if (reqVO.getBsqn() != null) {
            wrapper.eq("bsqn", reqVO.getBsqn());
        }
        if (reqVO.getYsqn() != null) {
            wrapper.eq("ysqn", reqVO.getYsqn());
        }
        if (reqVO.getJfjeqn() != null) {
            wrapper.eq("jfjeqn", reqVO.getJfjeqn());
        }

        // V1: order by JFJEBN desc
        wrapper.orderByDesc("jfjebn");

        return selectPage(reqVO, wrapper);
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HbsbjlMapper extends BaseMapperX<HbsbjlDO> {

    /**
     * 划拨失败记录分页 — 还原 V1 selectHbsbjlList 完整 WHERE 条件
     * 表: gh_hkxx, 硬编码条件: THBJ = 'Y'
     */
    default PageResult<HbsbjlDO> selectPage(HbsbjlPageReqVO reqVO) {
        QueryWrapper<HbsbjlDO> wrapper = new QueryWrapper<>();

        // --- V1 硬编码条件：仅查询退回标志为 Y 的记录 ---
        wrapper.eq("THBJ", "Y");

        // --- V1 完整条件 ---
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getXh() != null) {
            wrapper.eq("XH", reqVO.getXh());
        }
        if (reqVO.getLx() != null && !reqVO.getLx().isEmpty()) {
            wrapper.eq("LX", reqVO.getLx());
        }
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            wrapper.eq("ZH", reqVO.getZh());
        }
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("HM", reqVO.getHm());
        }
        if (reqVO.getHh() != null && !reqVO.getHh().isEmpty()) {
            wrapper.eq("HH", reqVO.getHh());
        }
        if (reqVO.getXzh() != null && !reqVO.getXzh().isEmpty()) {
            wrapper.eq("XZH", reqVO.getXzh());
        }
        if (reqVO.getXhm() != null && !reqVO.getXhm().isEmpty()) {
            wrapper.like("XHM", reqVO.getXhm());
        }
        if (reqVO.getXhh() != null && !reqVO.getXhh().isEmpty()) {
            wrapper.eq("XHH", reqVO.getXhh());
        }
        if (reqVO.getJe() != null && !reqVO.getJe().isEmpty()) {
            wrapper.like("JE", reqVO.getJe());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getDz() != null && !reqVO.getDz().isEmpty()) {
            wrapper.like("DZ", reqVO.getDz());
        }
        if (reqVO.getFy() != null && !reqVO.getFy().isEmpty()) {
            wrapper.like("FY", reqVO.getFy());
        }
        if (reqVO.getJym() != null && !reqVO.getJym().isEmpty()) {
            wrapper.eq("JYM", reqVO.getJym());
        }
        // V1 date range: THRQ between
        if (reqVO.getBeginThrq() != null && !reqVO.getBeginThrq().isEmpty()
                && reqVO.getEndThrq() != null && !reqVO.getEndThrq().isEmpty()) {
            wrapper.between("THRQ", reqVO.getBeginThrq(), reqVO.getEndThrq());
        }
        if (reqVO.getThyy() != null && !reqVO.getThyy().isEmpty()) {
            wrapper.eq("THYY", reqVO.getThyy());
        }
        if (reqVO.getXgbj() != null && !reqVO.getXgbj().isEmpty()) {
            wrapper.eq("XGBJ", reqVO.getXgbj());
        }
        if (reqVO.getHkxxidgl() != null && !reqVO.getHkxxidgl().isEmpty()) {
            wrapper.eq("HKXXIDGL", reqVO.getHkxxidgl());
        }
        if (reqVO.getSchkpch() != null && !reqVO.getSchkpch().isEmpty()) {
            wrapper.eq("SCHKPCH", reqVO.getSchkpch());
        }
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.like("BZ", reqVO.getBz());
        }
        if (reqVO.getScbz() != null && !reqVO.getScbz().isEmpty()) {
            wrapper.eq("SCBZ", reqVO.getScbz());
        }

        wrapper.orderByDesc("HKXX_ID");

        return selectPage(reqVO, wrapper);
    }

    /**
     * 批量复审 — 动态更新字段（还原 V1 fushenPlByhkxxIds）
     */
    int fushenPlByhkxxIds(@Param("hkxxIds") List<Long> hkxxIds,
                          @Param("updateFields") Map<String, Object> updateFields);
}

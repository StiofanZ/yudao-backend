package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjlyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg.HbsbjlyxgDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HbsbjlyxgMapper extends BaseMapperX<HbsbjlyxgDO> {

    /**
     * 划拨失败已修改分页 — 还原 V1 selectHbsbjlList 完整 WHERE 条件
     * 表: gh_hkxx, 硬编码条件: THBJ = 'Y' + XGBJ = 'Y'
     */
    default PageResult<HbsbjlyxgDO> selectPage(HbsbjlyxgPageReqVO reqVO) {
        QueryWrapper<HbsbjlyxgDO> wrapper = new QueryWrapper<>();

        // --- V1 硬编码条件：退回标志 Y + 修改标志 Y ---
        wrapper.eq("THBJ", "Y");
        wrapper.eq("XGBJ", "Y");

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
}

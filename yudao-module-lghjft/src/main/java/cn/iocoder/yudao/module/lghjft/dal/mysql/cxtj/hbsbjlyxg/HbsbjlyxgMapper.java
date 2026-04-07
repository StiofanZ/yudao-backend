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
     * 划拨失败已修改分页 — 还原 V1 条件: THBJ='Y' AND XGBJ='Y'
     * 表: gh_hkxx, 硬编码条件: THBJ = 'Y' (退回) + XGBJ = 'Y' (已修改)
     */
    default PageResult<HbsbjlyxgDO> selectPage(HbsbjlyxgPageReqVO reqVO) {
        QueryWrapper<HbsbjlyxgDO> wrapper = new QueryWrapper<>();

        // --- V1 硬编码条件：退回标志 Y + 修改标志 Y ---
        wrapper.eq("THBJ", "Y");
        wrapper.eq("XGBJ", "Y");

        // --- V1 可选条件 ---
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("HM", reqVO.getHm());
        }
        if (reqVO.getXhm() != null && !reqVO.getXhm().isEmpty()) {
            wrapper.like("XHM", reqVO.getXhm());
        }

        wrapper.orderByDesc("HKXX_ID");

        return selectPage(reqVO, wrapper);
    }
}

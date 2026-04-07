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
     * 近三年缴费情况分页 -- 还原 V1 selectGhjfjfdwList 完整 WHERE + ORDER BY
     */
    default PageResult<GhjfjfdwDO> selectPage(GhjfjfdwPageReqVO reqVO) {
        QueryWrapper<GhjfjfdwDO> wrapper = new QueryWrapper<>();

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

        // V1: order by JFJEBN desc
        wrapper.orderByDesc("jfjebn");

        return selectPage(reqVO, wrapper);
    }
}

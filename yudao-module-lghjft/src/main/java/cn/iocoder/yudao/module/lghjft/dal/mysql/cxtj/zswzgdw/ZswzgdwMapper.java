package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZswzgdwMapper extends BaseMapperX<ZswzgdwDO> {

    /**
     * V1 list 查询条件完整还原：
     * - djxh: eq
     * - id: eq
     * - dwdm: eq
     * - deptId: (DWDM = ? or DEPT_ID = ?)  -- V1 原始逻辑
     * - zgswjDm: eq
     * - shxydm: eq
     * - nsrmc: like
     * - bs: eq
     * - jfje: eq
     * - jcghje: eq
     */
    default PageResult<ZswzgdwDO> selectPage(ZswzgdwPageReqVO reqVO) {
        QueryWrapperX<ZswzgdwDO> wrapper = new QueryWrapperX<ZswzgdwDO>()
                .eqIfPresent("djxh", reqVO.getDjxh())
                .eqIfPresent("id", reqVO.getId())
                .eqIfPresent("dwdm", reqVO.getDwdm())
                .eqIfPresent("zgswj_dm", reqVO.getZgswjDm())
                .eqIfPresent("shxydm", reqVO.getShxydm())
                .likeIfPresent("nsrmc", reqVO.getNsrmc())
                .eqIfPresent("bs", reqVO.getBs())
                .eqIfPresent("jfje", reqVO.getJfje())
                .eqIfPresent("jcghje", reqVO.getJcghje())
                .orderByDesc("djxh");

        // V1: (a.DWDM=#{deptId} or a.DEPT_ID = #{deptId})
        if (StrUtil.isNotEmpty(reqVO.getDeptId())) {
            wrapper.and(w -> w.eq("dwdm", reqVO.getDeptId())
                    .or()
                    .eq("dept_id", reqVO.getDeptId()));
        }

        return selectPage(reqVO, wrapper);
    }
}

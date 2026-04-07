package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NdrwwcMapper extends BaseMapperX<NdrwwcDO> {

    /**
     * 分上缴周期统计分页 -- 还原 v1 selectNdrwwcList 完整 WHERE 条件
     * <p>
     * v1 dept filter:
     * (DWDM = #{dwdm} or DWDM IN (SELECT t.dept_id FROM sys_dept t WHERE find_in_set(#{dwdm}, ancestors)))
     */
    default PageResult<NdrwwcDO> selectPage(NdrwwcPageReqVO reqVO) {
        QueryWrapper<NdrwwcDO> wrapper = new QueryWrapper<>();

        // --- v1 完整条件 ---
        if (reqVO.getNd() != null && !reqVO.getNd().isEmpty()) {
            wrapper.eq("ND", reqVO.getNd());
        }
        // v1: dept hierarchy filter using find_in_set on sys_dept.ancestors
        if (reqVO.getDwdm() != null && !reqVO.getDwdm().isEmpty()) {
            wrapper.and(w -> w
                    .eq("DWDM", reqVO.getDwdm())
                    .or()
                    .inSql("DWDM",
                            "select t.dept_id from sys_dept t where find_in_set('"
                                    + reqVO.getDwdm() + "', ancestors)"));
        }
        if (reqVO.getDwmc() != null && !reqVO.getDwmc().isEmpty()) {
            wrapper.eq("DWMC", reqVO.getDwmc());
        }

        wrapper.orderByDesc("ND");

        return selectPage(reqVO, wrapper);
    }
}

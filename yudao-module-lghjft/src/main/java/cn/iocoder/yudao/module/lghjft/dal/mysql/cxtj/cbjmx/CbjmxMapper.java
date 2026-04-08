package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxtjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjmxMapper extends BaseMapperX<CbjmxDO> {

    default PageResult<CbjmxDO> selectPage(CbjmxPageReqVO reqVO) {
        QueryWrapper<CbjmxDO> wrapper = new QueryWrapper<>();

        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        if (reqVO.getNd() != null && !reqVO.getNd().isEmpty()) {
            wrapper.eq("nd", reqVO.getNd());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.eq("nsrmc", reqVO.getNsrmc());
        }

        return selectPage(reqVO, wrapper);
    }

    /**
     * v1 selectCbjmxtjList: GROUP BY dwdm, dept_id, shxydm, nsrmc, jsbj, cbjthbj, fbbj ORDER BY dwdm
     */
    List<CbjmxtjDO> selectCbjmxtjList(@Param("zspmDm") String zspmDm,
                                        @Param("nd") String nd,
                                        @Param("deptId") String deptId,
                                        @Param("shxydm") String shxydm,
                                        @Param("nsrmc") String nsrmc);

    /**
     * v1 selectCbjmxhzList: GROUP BY dwdm, dept_id, jsbj, cbjthbj, fbbj ORDER BY dwdm
     * v1 deptId filter: (dept_id = #{deptId} or dept_id IN (SELECT t.dept_id FROM sys_dept t WHERE find_in_set(#{deptId}, ancestors)))
     */
    List<CbjmxhzDO> selectCbjmxhzList(@Param("zspmDm") String zspmDm,
                                        @Param("nd") String nd,
                                        @Param("deptId") String deptId,
                                        @Param("shxydm") String shxydm,
                                        @Param("nsrmc") String nsrmc);
}

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
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.eq("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            wrapper.between("rkrq", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        if (reqVO.getBeginJsrq() != null && !reqVO.getBeginJsrq().isEmpty()
                && reqVO.getEndJsrq() != null && !reqVO.getEndJsrq().isEmpty()) {
            wrapper.between("jsrq", reqVO.getBeginJsrq(), reqVO.getEndJsrq());
        }
        if (reqVO.getSkssqq() != null && !reqVO.getSkssqq().isEmpty()) {
            wrapper.eq("skssqq", reqVO.getSkssqq());
        }
        if (reqVO.getSkssqz() != null && !reqVO.getSkssqz().isEmpty()) {
            wrapper.eq("skssqz", reqVO.getSkssqz());
        }
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("cbjthbj", reqVO.getCbjthbj());
        }
        if (reqVO.getJfje() != null && !reqVO.getJfje().isEmpty()) {
            wrapper.eq("jfje", reqVO.getJfje());
        }
        if (reqVO.getCbjje() != null && !reqVO.getCbjje().isEmpty()) {
            wrapper.eq("cbjje", reqVO.getCbjje());
        }
        if (reqVO.getFbbj() != null && !reqVO.getFbbj().isEmpty()) {
            wrapper.eq("fbbj", reqVO.getFbbj());
        }
        if (reqVO.getBeginFbrq() != null && !reqVO.getBeginFbrq().isEmpty()
                && reqVO.getEndFbrq() != null && !reqVO.getEndFbrq().isEmpty()) {
            wrapper.between("fbrq", reqVO.getBeginFbrq(), reqVO.getEndFbrq());
        }
        if (reqVO.getSfbje() != null && !reqVO.getSfbje().isEmpty()) {
            wrapper.eq("sfbje", reqVO.getSfbje());
        }

        return selectPage(reqVO, wrapper);
    }

    /**
     * v1 selectCbjmxtjList: GROUP BY dwdm, dept_id, shxydm, nsrmc, jsbj, cbjthbj, fbbj ORDER BY dwdm
     */
    List<CbjmxtjDO> selectCbjmxtjList(@Param("req") CbjmxPageReqVO req);

    /**
     * v1 selectCbjmxhzList: GROUP BY dwdm, dept_id, jsbj, cbjthbj, fbbj ORDER BY dwdm
     * v1 deptId filter: (dept_id = #{deptId} or dept_id IN (SELECT t.dept_id FROM sys_dept t WHERE find_in_set(#{deptId}, ancestors)))
     */
    List<CbjmxhzDO> selectCbjmxhzList(@Param("req") CbjmxPageReqVO req);
}

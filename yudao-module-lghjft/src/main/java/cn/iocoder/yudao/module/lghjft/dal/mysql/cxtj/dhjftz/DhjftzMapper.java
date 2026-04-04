package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DhjftzMapper extends BaseMapperX<DhjftzDO> {

    /**
     * 单户经费台账分页（v1 selectDhjftzList 完整条件）
     * 表: gh_hj
     */
    default PageResult<DhjftzDO> selectPage(DhjftzPageReqVO reqVO) {
        QueryWrapper<DhjftzDO> wrapper = new QueryWrapper<>();
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getHyghbz() != null && !reqVO.getHyghbz().isEmpty()) {
            wrapper.eq("HYGHBZ", reqVO.getHyghbz());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("DJXH", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrsbh() != null && !reqVO.getNsrsbh().isEmpty()) {
            wrapper.eq("NSRSBH", reqVO.getNsrsbh());
        }
        // v1: nsrmc uses LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getNsrjc() != null && !reqVO.getNsrjc().isEmpty()) {
            wrapper.like("NSRJC", reqVO.getNsrjc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("ZGSWJ_DM", reqVO.getZgswjDm());
        }
        if (reqVO.getSdghjgDm() != null && !reqVO.getSdghjgDm().isEmpty()) {
            wrapper.eq("SDGHJG_DM", reqVO.getSdghjgDm());
        }
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("CLGHBJ", reqVO.getClghbj());
        }
        if (reqVO.getJcghyh() != null && !reqVO.getJcghyh().isEmpty()) {
            wrapper.like("JCGHYH", reqVO.getJcghyh());
        }
        // v1 date range: CLGHRQ between
        if (reqVO.getBeginClghrq() != null && !reqVO.getBeginClghrq().isEmpty()
                && reqVO.getEndClghrq() != null && !reqVO.getEndClghrq().isEmpty()) {
            wrapper.between("CLGHRQ", reqVO.getBeginClghrq(), reqVO.getEndClghrq());
        }
        return selectPage(reqVO, wrapper);
    }
}

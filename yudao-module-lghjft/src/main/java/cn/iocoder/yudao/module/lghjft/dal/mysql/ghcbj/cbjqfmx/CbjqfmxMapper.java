package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjqfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo.CbjqfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqfmx.CbjqfmxDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CbjqfmxMapper extends BaseMapperX<CbjqfmxDO> {

    default PageResult<CbjqfmxDO> selectPage(CbjqfmxPageReqVO reqVO) {
        QueryWrapper<CbjqfmxDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件: zspm_dm ='399001020' AND RKRQ >= '2021-08-01'
        wrapper.eq("zspm_dm", "399001020");
        wrapper.ge("rkrq", "2021-08-01");

        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc like, nsrjc like
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getNsrjc() != null && !reqVO.getNsrjc().isEmpty()) {
            wrapper.like("nsrjc", reqVO.getNsrjc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("zgswj_dm", reqVO.getZgswjDm());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        // v1: skssqq >= #{skssqq}
        if (reqVO.getSkssqq() != null && !reqVO.getSkssqq().isEmpty()) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        // v1: skssqz <= #{skssqz}
        if (reqVO.getSkssqz() != null && !reqVO.getSkssqz().isEmpty()) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        // v1: RKRQ between beginRkrq and endRkrq
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            wrapper.between("rkrq", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("cbjthbj", reqVO.getCbjthbj());
        }
        // v1: CBJTHRQ between beginCbjthrq and endCbjthrq
        if (reqVO.getBeginCbjthrq() != null && !reqVO.getBeginCbjthrq().isEmpty()
                && reqVO.getEndCbjthrq() != null && !reqVO.getEndCbjthrq().isEmpty()) {
            wrapper.between("cbjthrq", reqVO.getBeginCbjthrq(), reqVO.getEndCbjthrq());
        }

        return selectPage(reqVO, wrapper);
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.znjtz.ZnjtzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZnjtzMapper extends BaseMapperX<ZnjtzDO> {

    default PageResult<ZnjtzDO> selectPage(ZnjtzPageReqVO reqVO) {
        QueryWrapper<ZnjtzDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件
        wrapper.ne("JSBJ", "Y");
        wrapper.eq("ZSPM_DM", "399001910");
        wrapper.between("RKRQ", "2020-02-01", "2020-12-31");

        // 搜索条件 - v1: nsrmc uses LIKE
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("ZGSWJ_DM", reqVO.getZgswjDm());
        }
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("JSBJ", reqVO.getJsbj());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("ZSPM_DM", reqVO.getZspmDm());
        }

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }
}

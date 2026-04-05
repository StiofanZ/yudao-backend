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

        // v1 硬编码条件
        wrapper.eq("ZSPM_DM", "399001020");
        wrapper.ge("RKRQ", "2021-08-01");

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
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("ZSPM_DM", reqVO.getZspmDm());
        }

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfbfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfbfmx.JfbfmxDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JfbfmxMapper extends BaseMapperX<JfbfmxDO> {

    default PageResult<JfbfmxDO> selectPage(JfbfmxPageReqVO reqVO) {
        QueryWrapper<JfbfmxDO> wrapper = new QueryWrapper<>();

        // 搜索条件 - v1: nsrmc uses LIKE (not eq!)
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

    List<JfbfmxtjResVO> selectTjByDept(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxjsbjResVO> selectTjByJsbj(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxcbjResVO> selectTjByCbj(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxzspmResVO> selectTjByZspm(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxhkResVO> selectTjByHkpch(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxSummaryResVO> selectSzmxList(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxDetailResVO> selectHymxList(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxDetailResVO> selectXjmxList(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxDetailResVO> selectSjmxList(@Param("req") JfbfmxPageReqVO req);

    List<JfbfmxDetailResVO> selectSdmxList(@Param("req") JfbfmxPageReqVO req);
}

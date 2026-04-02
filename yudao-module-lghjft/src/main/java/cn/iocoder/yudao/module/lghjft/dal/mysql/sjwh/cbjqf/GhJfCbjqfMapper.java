package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.cbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqf.GhJfCbjqfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhJfCbjqfMapper extends BaseMapperX<GhJfCbjqfDO> {

    default PageResult<GhJfCbjqfDO> selectPage(GhJfCbjqfPageReqVO reqVO) {
        QueryWrapper<GhJfCbjqfDO> wrapper = new QueryWrapper<>();

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

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }
}

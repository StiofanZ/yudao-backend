package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjqltz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz.CbjqltzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CbjqltzMapper extends BaseMapperX<CbjqltzDO> {

    default PageResult<CbjqltzDO> selectPage(CbjqltzPageReqVO reqVO) {
        QueryWrapper<CbjqltzDO> wrapper = new QueryWrapper<>();

        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc like
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getNsrztDm() != null && !reqVO.getNsrztDm().isEmpty()) {
            wrapper.eq("nsrzt_dm", reqVO.getNsrztDm());
        }
        if (reqVO.getJdxzDm() != null && !reqVO.getJdxzDm().isEmpty()) {
            wrapper.eq("jdxz_dm", reqVO.getJdxzDm());
        }

        wrapper.orderByDesc("djxh");

        return selectPage(reqVO, wrapper);
    }
}

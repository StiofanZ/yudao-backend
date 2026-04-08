package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhjfcbjqfMapper extends BaseMapperX<GhjfcbjqfDO> {

    default PageResult<GhjfcbjqfDO> selectPage(GhjfcbjqfPageReqVO reqVO) {
        QueryWrapper<GhjfcbjqfDO> wrapper = new QueryWrapper<>();

        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc like
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getJfzh() != null && !reqVO.getJfzh().isEmpty()) {
            wrapper.eq("jfzh", reqVO.getJfzh());
        }
        // v1: jfhm like
        if (reqVO.getJfhm() != null && !reqVO.getJfhm().isEmpty()) {
            wrapper.like("jfhm", reqVO.getJfhm());
        }

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }
}

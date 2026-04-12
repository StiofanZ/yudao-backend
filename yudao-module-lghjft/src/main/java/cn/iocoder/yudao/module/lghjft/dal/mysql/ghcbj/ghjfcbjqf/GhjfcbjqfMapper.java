package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjtsjfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;

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
        if (StringUtils.hasText(reqVO.getRkrq())) {
            wrapper.eq("rkrq", reqVO.getRkrq());
        }
        if (StringUtils.hasText(reqVO.getJsbj())) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }
        if (StringUtils.hasText(reqVO.getBeginJsrq()) && StringUtils.hasText(reqVO.getEndJsrq())) {
            wrapper.between("jsrq", reqVO.getBeginJsrq(), reqVO.getEndJsrq());
        }
        if (StringUtils.hasText(reqVO.getCbjqfzt())) {
            wrapper.eq("cbjqfzt", reqVO.getCbjqfzt());
        }
        if (StringUtils.hasText(reqVO.getBeginCbjqfrq()) && StringUtils.hasText(reqVO.getEndCbjqfrq())) {
            wrapper.between("cbjqfrq", reqVO.getBeginCbjqfrq(), reqVO.getEndCbjqfrq());
        }
        if (StringUtils.hasText(reqVO.getCbjjsbfjczt())) {
            wrapper.eq("cbjjsbfjczt", reqVO.getCbjjsbfjczt());
        }
        if (StringUtils.hasText(reqVO.getBeginCbjjsbfjcrq()) && StringUtils.hasText(reqVO.getEndCbjjsbfjcrq())) {
            wrapper.between("cbjjsbfjcrq", reqVO.getBeginCbjjsbfjcrq(), reqVO.getEndCbjjsbfjcrq());
        }
        if (StringUtils.hasText(reqVO.getHkpch())) {
            wrapper.eq("hkpch", reqVO.getHkpch());
        }
        if (StringUtils.hasText(reqVO.getQfpch())) {
            wrapper.eq("qfpch", reqVO.getQfpch());
        }

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }

    GhjfcbjqfDO selectDetailById(@Param("id") Long id);

    int deleteCbjtsjfByGhjfId(@Param("ghjfId") Long ghjfId);

    int batchInsertCbjtsjf(@Param("list") List<GhjfcbjtsjfDO> list);
}

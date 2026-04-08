package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.sgbflr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr.SgbflrDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SgbflrMapper extends BaseMapperX<SgbflrDO> {

    default PageResult<SgbflrDO> selectPage(SgbflrPageReqVO reqVO) {
        QueryWrapper<SgbflrDO> wrapper = new QueryWrapper<>();
        // v1: NO thbj/yxbj base filter for sgbflr
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("hkpch", reqVO.getHkpch());
        }
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            wrapper.eq("zh", reqVO.getZh());
        }
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("hm", reqVO.getHm());
        }
        if (reqVO.getHh() != null && !reqVO.getHh().isEmpty()) {
            wrapper.eq("hh", reqVO.getHh());
        }
        if (reqVO.getJe() != null) {
            wrapper.like("je", reqVO.getJe());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getDz() != null && !reqVO.getDz().isEmpty()) {
            wrapper.like("dz", reqVO.getDz());
        }
        if (reqVO.getFy() != null && !reqVO.getFy().isEmpty()) {
            wrapper.like("fy", reqVO.getFy());
        }
        if (reqVO.getThbj() != null && !reqVO.getThbj().isEmpty()) {
            wrapper.eq("thbj", reqVO.getThbj());
        }
        if (reqVO.getBeginThrq() != null && !reqVO.getBeginThrq().isEmpty()
                && reqVO.getEndThrq() != null && !reqVO.getEndThrq().isEmpty()) {
            wrapper.between("thrq", reqVO.getBeginThrq(), reqVO.getEndThrq());
        }
        if (reqVO.getThyy() != null && !reqVO.getThyy().isEmpty()) {
            wrapper.eq("thyy", reqVO.getThyy());
        }
        if (reqVO.getXgbj() != null && !reqVO.getXgbj().isEmpty()) {
            wrapper.eq("xgbj", reqVO.getXgbj());
        }
        if (reqVO.getYxbj() != null && !reqVO.getYxbj().isEmpty()) {
            wrapper.eq("yxbj", reqVO.getYxbj());
        }
        if (reqVO.getSchkpch() != null && !reqVO.getSchkpch().isEmpty()) {
            wrapper.eq("schkpch", reqVO.getSchkpch());
        }
        if (reqVO.getScbz() != null && !reqVO.getScbz().isEmpty()) {
            wrapper.eq("scbz", reqVO.getScbz());
        }
        // v1: no explicit order by, default by hkxx_id desc
        wrapper.orderByDesc("hkxx_id");
        return selectPage(reqVO, wrapper);
    }
}

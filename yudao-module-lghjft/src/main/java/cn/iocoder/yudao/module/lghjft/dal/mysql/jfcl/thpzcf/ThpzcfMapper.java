package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.thpzcf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThpzcfMapper extends BaseMapperX<ThpzcfDO> {

    default PageResult<ThpzcfDO> selectPage(ThpzcfPageReqVO reqVO) {
        QueryWrapper<ThpzcfDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: thbj='Y' and yxbj='Y'
        wrapper.apply("thbj = 'Y'");
        wrapper.apply("yxbj = 'Y'");
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("hkpch", reqVO.getHkpch());
        }
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            wrapper.eq("zh", reqVO.getZh());
        }
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("hm", reqVO.getHm());
        }
        if (reqVO.getXzh() != null && !reqVO.getXzh().isEmpty()) {
            wrapper.eq("xzh", reqVO.getXzh());
        }
        if (reqVO.getXhm() != null && !reqVO.getXhm().isEmpty()) {
            wrapper.like("xhm", reqVO.getXhm());
        }
        if (reqVO.getXhh() != null && !reqVO.getXhh().isEmpty()) {
            wrapper.eq("xhh", reqVO.getXhh());
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
        if (reqVO.getSchkpch() != null && !reqVO.getSchkpch().isEmpty()) {
            wrapper.eq("schkpch", reqVO.getSchkpch());
        }
        if (reqVO.getScbz() != null && !reqVO.getScbz().isEmpty()) {
            wrapper.eq("scbz", reqVO.getScbz());
        }
        if (reqVO.getBeginUpdateTime() != null && !reqVO.getBeginUpdateTime().isEmpty()
                && reqVO.getEndUpdateTime() != null && !reqVO.getEndUpdateTime().isEmpty()) {
            wrapper.between("update_time", reqVO.getBeginUpdateTime(), reqVO.getEndUpdateTime());
        }
        // v1 has no explicit ORDER BY in thpzcf list, default by hkxx_id desc
        wrapper.orderByDesc("hkxx_id");
        return selectPage(reqVO, wrapper);
    }
}

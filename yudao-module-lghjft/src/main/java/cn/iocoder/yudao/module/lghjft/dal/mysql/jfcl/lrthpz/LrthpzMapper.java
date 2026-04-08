package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.lrthpz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.lrthpz.LrthpzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LrthpzMapper extends BaseMapperX<LrthpzDO> {

    default PageResult<LrthpzDO> selectPage(LrthpzPageReqVO reqVO) {
        QueryWrapper<LrthpzDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: yxbj='Y'
        wrapper.apply("yxbj = 'Y'");
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.like("hkpch", reqVO.getHkpch());
        }
        if (reqVO.getBeginHkpch() != null && !reqVO.getBeginHkpch().isEmpty()
                && reqVO.getEndHkpch() != null && !reqVO.getEndHkpch().isEmpty()) {
            wrapper.apply("mid(hkpch, 1, 8) between {0} and {1}", reqVO.getBeginHkpch(), reqVO.getEndHkpch());
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
            wrapper.eq("je", reqVO.getJe());
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
        if (reqVO.getSchkpch() != null && !reqVO.getSchkpch().isEmpty()) {
            wrapper.eq("schkpch", reqVO.getSchkpch());
        }
        if (reqVO.getScbz() != null && !reqVO.getScbz().isEmpty()) {
            wrapper.eq("scbz", reqVO.getScbz());
        }
        // v1: order by hkpch desc
        wrapper.orderByDesc("hkpch");
        return selectPage(reqVO, wrapper);
    }

    /**
     * V1 import lookup: find gh_hkxx rows via gh_hkxx_yhbfmx join
     * SQL: SELECT t.* FROM gh_hkxx t LEFT JOIN gh_hkxx_yhbfmx yh on t.hkxx_id=yh.hkxx_id WHERE yh.bfid = #{bfid} and t.yxbj='Y'
     */
    @Select("select t.* from gh_hkxx t left join gh_hkxx_yhbfmx yh on t.hkxx_id = yh.hkxx_id where yh.bfid = #{bfid} and t.yxbj = 'Y'")
    List<LrthpzDO> selectByBfid(Long bfid);
}

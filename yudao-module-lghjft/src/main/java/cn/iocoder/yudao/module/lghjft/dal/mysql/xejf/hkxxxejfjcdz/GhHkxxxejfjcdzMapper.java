package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfjcdz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHkxxxejfjcdzMapper extends BaseMapperX<GhHkxxxejfjcdzDO> {

    /**
     * v1 selectGhHkxxxejfjcdzList 完整条件迁移
     * 关键: 硬编码 lx in ('3','4')
     * hm like, xhm like, dz like, fy like 模糊匹配
     */
    default PageResult<GhHkxxxejfjcdzDO> selectPage(GhHkxxxejfjcdzPageReqVO reqVO) {
        QueryWrapper<GhHkxxxejfjcdzDO> wrapper = new QueryWrapper<>();
        // v1: lx in ('3','4')
        wrapper.in("lx", "3", "4");
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("hkpch", reqVO.getHkpch());
        }
        if (reqVO.getJfqj() != null) {
            wrapper.eq("jfqj", reqVO.getJfqj());
        }
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            wrapper.eq("zh", reqVO.getZh());
        }
        // v1: hm like
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("hm", reqVO.getHm());
        }
        if (reqVO.getXzh() != null && !reqVO.getXzh().isEmpty()) {
            wrapper.eq("xzh", reqVO.getXzh());
        }
        // v1: xhm like
        if (reqVO.getXhm() != null && !reqVO.getXhm().isEmpty()) {
            wrapper.like("xhm", reqVO.getXhm());
        }
        if (reqVO.getJe() != null) {
            wrapper.eq("je", reqVO.getJe());
        }
        // v1: dz like
        if (reqVO.getDz() != null && !reqVO.getDz().isEmpty()) {
            wrapper.like("dz", reqVO.getDz());
        }
        // v1: fy like
        if (reqVO.getFy() != null && !reqVO.getFy().isEmpty()) {
            wrapper.like("fy", reqVO.getFy());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getThbj() != null && !reqVO.getThbj().isEmpty()) {
            wrapper.eq("thbj", reqVO.getThbj());
        }
        if (reqVO.getThrq() != null && !reqVO.getThrq().isEmpty()) {
            wrapper.eq("thrq", reqVO.getThrq());
        }
        if (reqVO.getXgbj() != null && !reqVO.getXgbj().isEmpty()) {
            wrapper.eq("xgbj", reqVO.getXgbj());
        }
        if (reqVO.getUpdateTime() != null && !reqVO.getUpdateTime().isEmpty()) {
            wrapper.eq("update_time", reqVO.getUpdateTime());
        }
        wrapper.orderByDesc("hkxx_id");
        return selectPage(reqVO, wrapper);
    }
}

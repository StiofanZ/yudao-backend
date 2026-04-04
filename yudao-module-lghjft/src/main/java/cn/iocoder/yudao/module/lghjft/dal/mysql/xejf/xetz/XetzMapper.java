package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xetz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz.XetzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XetzMapper extends BaseMapperX<XetzDO> {

    /**
     * v1 selectXetzList 完整条件迁移
     * 关键差异: deptId 同时匹配 DEPT_ID 和 SDGHJG_DM
     */
    default PageResult<XetzDO> selectPage(XetzPageReqVO reqVO) {
        QueryWrapper<XetzDO> wrapper = new QueryWrapper<>();
        if (reqVO.getJfqj() != null && !reqVO.getJfqj().isEmpty()) {
            wrapper.eq("JFQJ", reqVO.getJfqj());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("DJXH", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        // v1: and (dept_id = #{deptId} or SDGHJG_DM = #{deptId})
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w.eq("DEPT_ID", reqVO.getDeptId())
                    .or().eq("SDGHJG_DM", reqVO.getDeptId()));
        }
        if (reqVO.getXwlx() != null && !reqVO.getXwlx().isEmpty()) {
            wrapper.eq("XWLX", reqVO.getXwlx());
        }
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("GHLB_DM", reqVO.getGhlbDm());
        }
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("XTLB_DM", reqVO.getXtlbDm());
        }
        if (reqVO.getXelx23() != null && reqVO.getXelx23().length > 0) {
            wrapper.in("XELX23", (Object[]) reqVO.getXelx23());
        }
        if (reqVO.getXelx24() != null && reqVO.getXelx24().length > 0) {
            wrapper.in("XELX24", (Object[]) reqVO.getXelx24());
        }
        if (reqVO.getXelx25() != null && reqVO.getXelx25().length > 0) {
            wrapper.in("XELX25", (Object[]) reqVO.getXelx25());
        }
        if (reqVO.getBeginYbtse() != null && !reqVO.getBeginYbtse().isEmpty()
                && reqVO.getEndYbtse() != null && !reqVO.getEndYbtse().isEmpty()) {
            wrapper.between("YBTSE", reqVO.getBeginYbtse(), reqVO.getEndYbtse());
        }
        if (reqVO.getBeginSjje() != null && !reqVO.getBeginSjje().isEmpty()
                && reqVO.getEndSjje() != null && !reqVO.getEndSjje().isEmpty()) {
            wrapper.between("SJJE", reqVO.getBeginSjje(), reqVO.getEndSjje());
        }
        wrapper.orderByDesc("JFQJ");
        return selectPage(reqVO, wrapper);
    }
}

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
            wrapper.eq("jfqj", reqVO.getJfqj());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        // v1: and (dept_id = #{deptId} or SDGHJG_DM = #{deptId})
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w.eq("dept_id", reqVO.getDeptId())
                    .or().eq("sdghjg_dm", reqVO.getDeptId()));
        }
        if (reqVO.getXwlx() != null && !reqVO.getXwlx().isEmpty()) {
            wrapper.eq("xwlx", reqVO.getXwlx());
        }
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("ghlb_dm", reqVO.getGhlbDm());
        }
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("xtlb_dm", reqVO.getXtlbDm());
        }
        if (reqVO.getXelx23() != null && reqVO.getXelx23().length > 0) {
            wrapper.in("xelx23", (Object[]) reqVO.getXelx23());
        }
        if (reqVO.getXelx24() != null && reqVO.getXelx24().length > 0) {
            wrapper.in("xelx24", (Object[]) reqVO.getXelx24());
        }
        if (reqVO.getXelx25() != null && reqVO.getXelx25().length > 0) {
            wrapper.in("xelx25", (Object[]) reqVO.getXelx25());
        }
        if (reqVO.getBeginYbtse() != null && !reqVO.getBeginYbtse().isEmpty()
                && reqVO.getEndYbtse() != null && !reqVO.getEndYbtse().isEmpty()) {
            wrapper.between("ybtse", reqVO.getBeginYbtse(), reqVO.getEndYbtse());
        }
        if (reqVO.getBeginSjje() != null && !reqVO.getBeginSjje().isEmpty()
                && reqVO.getEndSjje() != null && !reqVO.getEndSjje().isEmpty()) {
            wrapper.between("sjje", reqVO.getBeginSjje(), reqVO.getEndSjje());
        }
        wrapper.orderByDesc("jfqj");
        return selectPage(reqVO, wrapper);
    }
}

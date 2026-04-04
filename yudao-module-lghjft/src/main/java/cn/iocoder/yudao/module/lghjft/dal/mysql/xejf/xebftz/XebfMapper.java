package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XebfMapper extends BaseMapperX<XebfDO> {

    /**
     * 小额缴费拨付台账分页（v1 selectXebfList 完整条件）
     */
    default PageResult<XebfDO> selectPage(XebfPageReqVO reqVO) {
        QueryWrapper<XebfDO> wrapper = new QueryWrapper<>();
        if (reqVO.getGhjfId() != null) {
            wrapper.eq("ghjf_id", reqVO.getGhjfId());
        }
        if (reqVO.getJfqj() != null && !reqVO.getJfqj().isEmpty()) {
            wrapper.eq("jfqj", reqVO.getJfqj());
        }
        // v1: jsbj IN array
        if (reqVO.getJsbj() != null && reqVO.getJsbj().length > 0) {
            wrapper.in("JSBJ", (Object[]) reqVO.getJsbj());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("DEPT_ID", reqVO.getDeptId());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("DJXH", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        // v1: nsrmc uses LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        // v1: xelx23/24/25 IN
        if (reqVO.getXelx23() != null && reqVO.getXelx23().length > 0) {
            wrapper.in("XELX23", (Object[]) reqVO.getXelx23());
        }
        if (reqVO.getXelx24() != null && reqVO.getXelx24().length > 0) {
            wrapper.in("XELX24", (Object[]) reqVO.getXelx24());
        }
        if (reqVO.getXelx25() != null && reqVO.getXelx25().length > 0) {
            // Note: v1 has a bug here — it filters XELX24 instead of XELX25,
            // we follow v1 exactly: and XELX24 IN (xelx25 values)
            wrapper.in("XELX24", (Object[]) reqVO.getXelx25());
        }
        if (reqVO.getXwlx() != null && !reqVO.getXwlx().isEmpty()) {
            wrapper.eq("XWLX", reqVO.getXwlx());
        }
        if (reqVO.getFbbj() != null && !reqVO.getFbbj().isEmpty()) {
            wrapper.eq("FBBJ", reqVO.getFbbj());
        }
        // v1 date ranges
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            wrapper.between("RKRQ", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        if (reqVO.getBeginJsrq() != null && !reqVO.getBeginJsrq().isEmpty()
                && reqVO.getEndJsrq() != null && !reqVO.getEndJsrq().isEmpty()) {
            wrapper.between("JSRQ", reqVO.getBeginJsrq(), reqVO.getEndJsrq());
        }
        if (reqVO.getBeginFbrq() != null && !reqVO.getBeginFbrq().isEmpty()
                && reqVO.getEndFbrq() != null && !reqVO.getEndFbrq().isEmpty()) {
            wrapper.between("FBRQ", reqVO.getBeginFbrq(), reqVO.getEndFbrq());
        }
        wrapper.orderByDesc("ghjf_id");
        return selectPage(reqVO, wrapper);
    }

    /**
     * 查询小额拨付省总列表 (v1: selectGhHkxxxejfszList, 表 xebfsz)
     */
    List<GhhkxxxejfszResVO> selectGhHkxxxejfszList(@Param("jfqj") String jfqj);
}

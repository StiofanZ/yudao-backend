package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZgjrghMapper extends BaseMapperX<ZgjrghDO> {

    /**
     * 金融工会信息核对分页 -- 1:1 还原 V1 selectZgjrghList 全部 WHERE + ORDER BY
     */
    default PageResult<ZgjrghDO> selectPage(ZgjrghPageReqVO reqVO) {
        QueryWrapper<ZgjrghDO> wrapper = new QueryWrapper<>();

        // --- V1 active search fields (frontend 21 条) ---

        // shxydm = (V1: eq)
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // nsrmc LIKE (V1: like concat)
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        // dept_id = (V1: eq)
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        // clghbj = (V1: eq)
        if (reqVO.getClghbj() != null && !reqVO.getClghbj().isEmpty()) {
            wrapper.eq("clghbj", reqVO.getClghbj());
        }
        // hyghbz = (V1: eq)
        if (reqVO.getHyghbz() != null && !reqVO.getHyghbz().isEmpty()) {
            wrapper.eq("hyghbz", reqVO.getHyghbz());
        }
        // ghlb_dm = (V1: eq)
        if (reqVO.getGhlbDm() != null && !reqVO.getGhlbDm().isEmpty()) {
            wrapper.eq("ghlb_dm", reqVO.getGhlbDm());
        }
        // xtlb_dm = (V1: eq)
        if (reqVO.getXtlbDm() != null && !reqVO.getXtlbDm().isEmpty()) {
            wrapper.eq("xtlb_dm", reqVO.getXtlbDm());
        }
        // zspm_dm = (V1: eq)
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        // gzze = (V1: eq)
        if (reqVO.getGzze() != null) {
            wrapper.eq("gzze", reqVO.getGzze());
        }
        // sl = (V1: eq)
        if (reqVO.getSl() != null) {
            wrapper.eq("sl", reqVO.getSl());
        }
        // ybtse = (V1: eq)
        if (reqVO.getYbtse() != null) {
            wrapper.eq("ybtse", reqVO.getYbtse());
        }
        // skssqq >= (V1: >=)
        if (reqVO.getSkssqq() != null) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        // skssqz <= (V1: <=)
        if (reqVO.getSkssqz() != null) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        // rkrq between (V1: between params.beginRkrq and params.endRkrq)
        if (reqVO.getBeginRkrq() != null && reqVO.getEndRkrq() != null) {
            wrapper.between("rkrq", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        // jsbj = (V1: eq)
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }
        // jsrq between (V1: between params.beginJsrq and params.endJsrq)
        if (reqVO.getBeginJsrq() != null && reqVO.getEndJsrq() != null) {
            wrapper.between("jsrq", reqVO.getBeginJsrq(), reqVO.getEndJsrq());
        }
        // jcghzh = (V1: eq)
        if (reqVO.getJcghzh() != null && !reqVO.getJcghzh().isEmpty()) {
            wrapper.eq("jcghzh", reqVO.getJcghzh());
        }
        // jcghhm LIKE (V1: like concat)
        if (reqVO.getJcghhm() != null && !reqVO.getJcghhm().isEmpty()) {
            wrapper.like("jcghhm", reqVO.getJcghhm());
        }
        // jcghbl = (V1: eq)
        if (reqVO.getJcghbl() != null) {
            wrapper.eq("jcghbl", reqVO.getJcghbl());
        }
        // jcghje = (V1: eq)
        if (reqVO.getJcghje() != null) {
            wrapper.eq("jcghje", reqVO.getJcghje());
        }
        // cbjthbj = (V1: eq)
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("cbjthbj", reqVO.getCbjthbj());
        }
        // hkpch = (V1: eq)
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("hkpch", reqVO.getHkpch());
        }

        // V1: ORDER BY RKRQ DESC
        wrapper.orderByDesc("rkrq");

        return selectPage(reqVO, wrapper);
    }
}

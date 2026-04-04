package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.cbjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjtz.CbjtzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjtzMapper extends BaseMapperX<CbjtzDO> {

    /**
     * 筹备金台账分页（v1 selectCbjtzList 完整条件）
     */
    default PageResult<CbjtzDO> selectPage(CbjtzPageReqVO reqVO) {
        QueryWrapper<CbjtzDO> wrapper = new QueryWrapper<>();
        // v1 硬编码条件
        wrapper.eq("JSBJ", "Y");
        // v1 全部动态条件
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("ZGSWJ_DM", reqVO.getZgswjDm());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("ZSPM_DM", reqVO.getZspmDm());
        }
        if (reqVO.getSkssqq() != null) {
            wrapper.ge("SKSSQQ", reqVO.getSkssqq());
        }
        if (reqVO.getSkssqz() != null) {
            wrapper.le("SKSSQZ", reqVO.getSkssqz());
        }
        if (reqVO.getYbtse() != null && !reqVO.getYbtse().isEmpty()) {
            wrapper.eq("YBTSE", reqVO.getYbtse());
        }
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("CBJTHBJ", reqVO.getCbjthbj());
        }
        if (reqVO.getHkpzh() != null && !reqVO.getHkpzh().isEmpty()) {
            wrapper.eq("HKPZH", reqVO.getHkpzh());
        }
        if (reqVO.getFbbj() != null && !reqVO.getFbbj().isEmpty()) {
            wrapper.eq("FBBJ", reqVO.getFbbj());
        }
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.like("BZ", reqVO.getBz());
        }
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
        wrapper.orderByDesc("RKRQ");
        return selectPage(reqVO, wrapper);
    }

    /**
     * 批量确认返拨
     */
    void batchUpdateCbjqrfbpl(@Param("list") List<CbjtzBatchReqVO> list);

    List<DgjftzResVO> selectDgjftzList(@Param("req") CbjtzPageReqVO req);

    /**
     * 代管经费台账分页（v1 selectDgjftzList 明细查询）
     */
    default PageResult<CbjtzDO> selectDgjftzPage(CbjtzPageReqVO reqVO) {
        QueryWrapper<CbjtzDO> wrapper = new QueryWrapper<>();
        // v1 硬编码条件
        wrapper.eq("JSBJ", "Y");
        wrapper.eq("ZSPM_DM", "399001010");
        wrapper.ne("JCGHJE", "0");
        wrapper.in("CBJTHBJ", "D", "Z");
        // 搜索条件
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getSkssqq() != null) {
            wrapper.ge("SKSSQQ", reqVO.getSkssqq());
        }
        if (reqVO.getSkssqz() != null) {
            wrapper.le("SKSSQZ", reqVO.getSkssqz());
        }
        if (reqVO.getYbtse() != null) {
            wrapper.eq("YBTSE", reqVO.getYbtse());
        }
        if (reqVO.getCbjthbj() != null && !reqVO.getCbjthbj().isEmpty()) {
            wrapper.eq("CBJTHBJ", reqVO.getCbjthbj());
        }
        if (reqVO.getHkpzh() != null && !reqVO.getHkpzh().isEmpty()) {
            wrapper.eq("HKPZH", reqVO.getHkpzh());
        }
        if (reqVO.getFbbj() != null && !reqVO.getFbbj().isEmpty()) {
            wrapper.eq("FBBJ", reqVO.getFbbj());
        }
        if (reqVO.getBz() != null && !reqVO.getBz().isEmpty()) {
            wrapper.like("BZ", reqVO.getBz());
        }
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
        wrapper.orderByDesc("RKRQ");
        return selectPage(reqVO, wrapper);
    }
}

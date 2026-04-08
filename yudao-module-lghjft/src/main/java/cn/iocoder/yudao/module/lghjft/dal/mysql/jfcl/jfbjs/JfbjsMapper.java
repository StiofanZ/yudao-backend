package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface JfbjsMapper extends BaseMapperX<JfclJfbjsDO> {

    /**
     * v1: selectJfclJfbjs — 分页查询 gh_jf WHERE 65+ optional conditions AND jsbj='N'
     */
    default PageResult<JfclJfbjsDO> selectPage(JfbjsPageReqVO reqVO) {
        QueryWrapper<JfclJfbjsDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: rkrq date range (mandatory in v1 XML)
        if (reqVO.getRkrqStart() != null && !reqVO.getRkrqStart().isEmpty()
                && reqVO.getRkrqEnd() != null && !reqVO.getRkrqEnd().isEmpty()) {
            wrapper.apply("rkrq >= date_format({0}, '%Y-%m-%d') and rkrq <= date_format({1}, '%Y-%m-%d')",
                    reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        // v1 hardcoded: and t.jsbj='N'
        wrapper.eq("jsbj", "N");
        // v1 optional conditions
        if (reqVO.getSpuuid() != null && !reqVO.getSpuuid().isEmpty()) {
            wrapper.eq("spuuid", reqVO.getSpuuid());
        }
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc uses LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getSkssqq() != null && !reqVO.getSkssqq().isEmpty()) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        if (reqVO.getSkssqz() != null && !reqVO.getSkssqz().isEmpty()) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        return selectPage(reqVO, wrapper);
    }

    /**
     * v1: selectJfclJfbjs — 查询 gh_jf 中待补结算数据 (for settlement operation)
     * Returns spuuid, jmse, rkrq
     */
    List<Map<String, Object>> selectJfclJfbjs(@Param("reqVO") JfbjsPageReqVO reqVO);

    /**
     * v1: updateJfclJfbjs — 批量更新 gh_jf 的 jsbj/jsrq/update_by/update_time (CASE on spuuid)
     */
    void updateJfclJfbjs(@Param("list") List<Map<String, Object>> list);

    /**
     * v1: selectQsjshkrjList — 根据入库日期查询结算、划拨日志
     */
    List<Map<String, Object>> selectQsjshkrjList(@Param("list") List<Date> rurqList);

    /**
     * v1: updateBatchQsjshkrj — 更新结算/划拨日志
     */
    void updateBatchQsjshkrj(@Param("list") List<Map<String, Object>> list);
}

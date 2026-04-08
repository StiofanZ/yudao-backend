package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JfjsMapper extends BaseMapperX<JfclJfjsDO> {

    /**
     * v1: selecList — 分页查询 gh_qsjshkrj
     */
    default PageResult<JfclJfjsDO> selectPage(JfjsPageReqVO reqVO) {
        QueryWrapper<JfclJfjsDO> wrapper = new QueryWrapper<>();
        if (reqVO.getRkrqStart() != null && !reqVO.getRkrqStart().isEmpty()
                && reqVO.getRkrqEnd() != null && !reqVO.getRkrqEnd().isEmpty()) {
            wrapper.apply("rkrq >= date_format({0}, '%Y-%m-%d') and rkrq <= date_format({1}, '%Y-%m-%d')",
                    reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        if (reqVO.getJsrq() != null && !reqVO.getJsrq().isEmpty()) {
            wrapper.apply("jsrq = date_format({0}, '%Y-%m-%d')", reqVO.getJsrq());
        }
        wrapper.orderByDesc("rkrq");
        return selectPage(reqVO, wrapper);
    }

    /**
     * v1: selectJfclJfjs — 查询 gh_jf 中待结算数据
     */
    List<Map<String, Object>> selectJfclJfjs(@Param("rkrqStart") String rkrqStart,
                                              @Param("rkrqEnd") String rkrqEnd,
                                              @Param("spuuid") String spuuid);

    /**
     * v1: updateJfclJfjs — 批量更新 gh_jf 的 jsbj/jsrq/update_by/update_time
     */
    void updateJfclJfjs(@Param("list") List<Map<String, Object>> list);
}

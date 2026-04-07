package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.szjffydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk.SzjffydsqkDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SzjffydsqkMapper extends BaseMapperX<SzjffydsqkDO> {

    /**
     * 分月代收情况(入库)分页 — 还原 v1 selectSzjffydsqkList 完整 WHERE 条件
     */
    default PageResult<SzjffydsqkDO> selectPage(SzjffydsqkPageReqVO reqVO) {
        QueryWrapper<SzjffydsqkDO> wrapper = new QueryWrapper<>();

        if (reqVO.getDwdm() != null && !reqVO.getDwdm().isEmpty()) {
            wrapper.eq("DWDM", reqVO.getDwdm());
        }
        if (reqVO.getDwmc() != null && !reqVO.getDwmc().isEmpty()) {
            wrapper.eq("DWMC", reqVO.getDwmc());
        }
        // v1: DSYF between #{params.beginDsyf} and #{params.endDsyf}
        if (reqVO.getBeginDsyf() != null && !reqVO.getBeginDsyf().isEmpty()
                && reqVO.getEndDsyf() != null && !reqVO.getEndDsyf().isEmpty()) {
            wrapper.between("DSYF", reqVO.getBeginDsyf(), reqVO.getEndDsyf());
        }
        if (reqVO.getRkjf() != null) {
            wrapper.eq("RKJF", reqVO.getRkjf());
        }
        if (reqVO.getGhjf() != null) {
            wrapper.eq("GHJF", reqVO.getGhjf());
        }
        if (reqVO.getCbj() != null) {
            wrapper.eq("CBJ", reqVO.getCbj());
        }
        if (reqVO.getZnj() != null) {
            wrapper.eq("ZNJ", reqVO.getZnj());
        }

        return selectPage(reqVO, wrapper);
    }
}

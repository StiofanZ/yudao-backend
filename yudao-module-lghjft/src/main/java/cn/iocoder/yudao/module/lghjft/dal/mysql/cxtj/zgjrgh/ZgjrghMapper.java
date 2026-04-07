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
     * 金融工会信息核对分页 -- 还原 V1 selectZgjrghList 完整 WHERE + ORDER BY
     */
    default PageResult<ZgjrghDO> selectPage(ZgjrghPageReqVO reqVO) {
        QueryWrapper<ZgjrghDO> wrapper = new QueryWrapper<>();

        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // V1: nsrmc 用 LIKE
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        // V1: nsrjc 用 LIKE
        if (reqVO.getNsrjc() != null && !reqVO.getNsrjc().isEmpty()) {
            wrapper.like("nsrjc", reqVO.getNsrjc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        // V1: skssqq >= #{skssqq}
        if (reqVO.getSkssqq() != null) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        // V1: skssqz <= #{skssqz}
        if (reqVO.getSkssqz() != null) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        // V1: RKRQ between #{params.beginRkrq} and #{params.endRkrq}
        if (reqVO.getBeginRkrq() != null && reqVO.getEndRkrq() != null) {
            wrapper.between("rkrq", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        // V1: JSRQ between #{params.beginJsrq} and #{params.endJsrq}
        if (reqVO.getBeginJsrq() != null && reqVO.getEndJsrq() != null) {
            wrapper.between("jsrq", reqVO.getBeginJsrq(), reqVO.getEndJsrq());
        }
        // V1: jsbj
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("jsbj", reqVO.getJsbj());
        }

        // V1: ORDER BY RKRQ DESC
        wrapper.orderByDesc("rkrq");

        return selectPage(reqVO, wrapper);
    }
}

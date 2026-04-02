package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.sgbflr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr.SgbflrDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SgbflrMapper extends BaseMapperX<SgbflrDO> {

    default PageResult<SgbflrDO> selectPage(SgbflrPageReqVO reqVO) {
        QueryWrapper<SgbflrDO> wrapper = new QueryWrapper<>();
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        wrapper.orderByDesc("HKXX_ID");
        return selectPage(reqVO, wrapper);
    }
}

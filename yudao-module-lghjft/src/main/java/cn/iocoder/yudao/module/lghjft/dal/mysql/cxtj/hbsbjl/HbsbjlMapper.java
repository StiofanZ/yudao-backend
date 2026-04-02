package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HbsbjlMapper extends BaseMapperX<HbsbjlDO> {

    default PageResult<HbsbjlDO> selectPage(HbsbjlPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HbsbjlDO>()
                .eqIfPresent(HbsbjlDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(HbsbjlDO::getHkxxId));
    }
}

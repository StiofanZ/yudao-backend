package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.czrj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.czrj.QxCzrjDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QxCzrjMapper extends BaseMapperX<QxCzrjDO> {

    default PageResult<QxCzrjDO> selectPage(QxCzrjPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QxCzrjDO>()

                .orderByDesc(QxCzrjDO::getCzrjid));
    }
}

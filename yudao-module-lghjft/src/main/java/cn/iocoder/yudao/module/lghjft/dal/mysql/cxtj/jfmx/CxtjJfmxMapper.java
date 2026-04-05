package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jfmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CxtjJfmxMapper extends BaseMapperX<CxtjJfmxDO> {

    List<JfmxResVO> selectJfmxList(@Param("req") JfmxPageReqVO reqVO);
}

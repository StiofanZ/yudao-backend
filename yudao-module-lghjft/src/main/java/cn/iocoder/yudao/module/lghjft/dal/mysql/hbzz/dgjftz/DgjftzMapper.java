package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.dgjftz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.dgjftz.DgjftzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgjftzMapper extends BaseMapperX<DgjftzDO> {

    List<DgjftzResVO> selectDgjftzList(@Param("req") DgjftzPageReqVO reqVO);
}

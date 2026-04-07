package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.szdzhd;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.SzdzhdDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SzdzhdMapper extends BaseMapperX<SzdzhdDO> {

    List<SzdzhdResVO> selectSzdzhdList(@Param("req") SzdzhdPageReqVO reqVO);
}

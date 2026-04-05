package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffsjzqmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jffsjzqmx.JffsjzqmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JffsjzqmxMapper extends BaseMapperX<JffsjzqmxDO> {

    /**
     * v1: selectJffsjzqmxList from JfmxMapper.xml
     */
    List<JffsjzqmxResVO> selectJffsjzqmxList(@Param("req") JffsjzqmxPageReqVO req);
}

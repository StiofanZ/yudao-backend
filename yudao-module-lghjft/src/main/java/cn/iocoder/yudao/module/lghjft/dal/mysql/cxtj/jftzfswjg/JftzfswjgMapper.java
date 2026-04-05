package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jftzfswjg;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jftzfswjg.JftzfswjgDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JftzfswjgMapper extends BaseMapperX<JftzfswjgDO> {

    /**
     * v1: selectJftzfswjgList from JfmxMapper.xml
     */
    List<JftzfswjgResVO> selectJftzfswjgList(@Param("req") JftzfswjgPageReqVO req);
}

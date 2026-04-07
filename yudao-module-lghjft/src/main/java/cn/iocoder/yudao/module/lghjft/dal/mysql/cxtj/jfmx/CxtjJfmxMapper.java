package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jfmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CxtjJfmxMapper extends BaseMapperX<CxtjJfmxDO> {

    IPage<JfmxResVO> selectJfmxList(Page<JfmxResVO> page, @Param("req") JfmxPageReqVO reqVO);

    IPage<JftzmxResVO> selectJftzmxList(Page<JftzmxResVO> page, @Param("req") JftzmxPageReqVO reqVO);

    IPage<SzdzhdResVO> selectSzdzhdList(Page<SzdzhdResVO> page, @Param("req") SzdzhdPageReqVO reqVO);
}

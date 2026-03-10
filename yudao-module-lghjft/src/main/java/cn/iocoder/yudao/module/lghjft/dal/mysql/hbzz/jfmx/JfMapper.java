package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfmx.JfDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 *  经费明细对象 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface JfMapper extends BaseMapperX<JfDO> {

    IPage<JfRespVO> selectJftzmxList(Page<JfRespVO> page, @Param("query") JfPageReqVO query);
}

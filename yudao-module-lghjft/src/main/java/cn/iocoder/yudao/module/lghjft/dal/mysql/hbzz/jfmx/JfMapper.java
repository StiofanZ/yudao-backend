package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfSummaryResVO;
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

    IPage<JfResVO> selectJfmxList(Page<JfResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfResVO> selectJftzmxList(Page<JfResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectJffymxList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectJffnmxList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectJffsjzqmxList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectJftzfnList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectJftzfswjgList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);

    IPage<JfSummaryResVO> selectSzdzhdList(Page<JfSummaryResVO> page, @Param("query") JfPageReqVO query);
}

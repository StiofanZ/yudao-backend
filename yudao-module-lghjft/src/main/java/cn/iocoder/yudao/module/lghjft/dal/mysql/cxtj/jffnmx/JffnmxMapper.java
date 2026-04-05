package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffnmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxSummaryResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JffnmxMapper {

    IPage<JffnmxSummaryResVO> selectJffnmxList(Page<JffnmxSummaryResVO> page, @Param("query") JffnmxPageReqVO query);
}

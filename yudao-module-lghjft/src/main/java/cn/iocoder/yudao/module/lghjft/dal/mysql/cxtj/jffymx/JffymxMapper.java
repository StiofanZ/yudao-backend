package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffymx;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxSummaryResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JffymxMapper {

    IPage<JffymxSummaryResVO> selectJffymxList(Page<JffymxSummaryResVO> page, @Param("query") JffymxPageReqVO query);
}

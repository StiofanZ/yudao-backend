package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jftzfn;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnSummaryResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JftzfnMapper {

    IPage<JftzfnSummaryResVO> selectJftzfnList(Page<JftzfnSummaryResVO> page, @Param("query") JftzfnPageReqVO query);
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TsjfcxMapper extends BaseMapperX<TsjfcxDO> {

    /**
     * v1: LEFT JOIN gh_jf_tsjf + WHERE b.tsjfbj is not null
     */
    IPage<TsjfcxDO> selectTsjfcxPage(Page<TsjfcxDO> page, @Param("query") TsjfcxPageReqVO query);
}

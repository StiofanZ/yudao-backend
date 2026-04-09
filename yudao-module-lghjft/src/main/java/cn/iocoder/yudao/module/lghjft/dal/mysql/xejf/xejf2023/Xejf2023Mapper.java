package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejf2023;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023ResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.XejftjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf2023.Xejf2023DO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Xejf2023Mapper extends BaseMapperX<Xejf2023DO> {

    /**
     * 小额缴费明细分页查询（LEFT JOIN gh_hj 获取 xelx23/24/25/xwlx）
     */
    IPage<Xejf2023ResVO> selectPageWithJoin(Page<Xejf2023ResVO> page, @Param("req") Xejf2023PageReqVO req);

    List<Xejf2023DO> selectPageTz(@Param("req") Xejf2023PageReqVO req);

    List<XejftjResVO> selectXetjList(@Param("req") Xejf2023PageReqVO req);
}

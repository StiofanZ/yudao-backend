package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xedgjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfQrszItemVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XedgjfMapper {

    default PageResult<XedgjfResVO> selectPage(XedgjfPageReqVO reqVO) {
        Page<XedgjfResVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<XedgjfResVO> iPage = selectPageList(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    XedgjfResVO selectByHkxxId(@Param("hkxxId") Long hkxxId);

    List<XedgjfQrszItemVO> selectQrszListByHkxxId(@Param("hkxxId") Long hkxxId);

    IPage<XedgjfResVO> selectPageList(Page<XedgjfResVO> page, @Param("req") XedgjfPageReqVO reqVO);

    int touchMainRow(@Param("hkxxId") Long hkxxId, @Param("operUser") String operUser);

    int deleteQrszByHkxxId(@Param("hkxxId") Long hkxxId);

    int batchInsertQrsz(@Param("list") List<XedgjfQrszItemVO> list);
}

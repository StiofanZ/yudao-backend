package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjQrszItemVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhHkxxxejfcbjMapper extends BaseMapperX<GhHkxxxejfcbjDO> {

    default PageResult<GhHkxxxejfcbjResVO> selectPage(GhHkxxxejfcbjPageReqVO reqVO) {
        Page<GhHkxxxejfcbjResVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<GhHkxxxejfcbjResVO> iPage = selectPageList(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    GhHkxxxejfcbjResVO selectByHkxxId(@Param("hkxxId") Long hkxxId);

    List<GhHkxxxejfcbjQrszItemVO> selectQrszListByHkxxId(@Param("hkxxId") Long hkxxId);

    IPage<GhHkxxxejfcbjResVO> selectPageList(Page<GhHkxxxejfcbjResVO> page,
                                             @Param("req") GhHkxxxejfcbjPageReqVO reqVO);

    int touchMainRow(@Param("hkxxId") Long hkxxId, @Param("operUser") String operUser);

    int deleteQrszByHkxxId(@Param("hkxxId") Long hkxxId);

    int batchInsertQrsz(@Param("list") List<GhHkxxxejfcbjQrszItemVO> list);
}

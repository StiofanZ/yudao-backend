package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XebfMapper extends BaseMapperX<XebfDO> {

    /**
     * 小额缴费拨付台账分页（v1 selectXebfList 完整条件）
     */
    default PageResult<XebfResVO> selectPage(XebfPageReqVO reqVO) {
        Page<XebfResVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<XebfResVO> ipage = selectPageWithConfirm(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    XebfResVO selectJoinedByGhjfId(@Param("ghjfId") Long ghjfId);

    IPage<XebfResVO> selectPageWithConfirm(Page<XebfResVO> page, @Param("req") XebfPageReqVO reqVO);

    int touchMainRow(@Param("ghjfId") Long ghjfId);

    int upsertConfirmByGhjfId(@Param("req") XebfSaveReqVO reqVO, @Param("operUser") String operUser);

    int deleteConfirmByGhjfId(@Param("ghjfId") Long ghjfId);

    int deleteConfirmByGhjfIds(@Param("ids") List<Long> ids);

    /**
     * 查询小额拨付省总列表 (v1: selectGhHkxxxejfszList, 表 xebfsz)
     */
    List<GhhkxxxejfszResVO> selectGhHkxxxejfszList(@Param("jfqj") String jfqj);
}

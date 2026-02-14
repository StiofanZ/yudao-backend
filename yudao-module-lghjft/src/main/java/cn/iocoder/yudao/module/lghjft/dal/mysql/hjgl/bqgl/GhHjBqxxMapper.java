package cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.bqgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.BqglHjxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.BqglHjxxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhHjBqxxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GhHjBqxxMapper extends BaseMapperX<GhHjBqxxDO> {

    /**
     * 以基础信息表为主表分页查询，确保无标签信息时也能完整带出 jcxx。
     */
    IPage<BqglHjxxRespVO> selectHjxxPage(IPage<BqglHjxxRespVO> page, @Param("reqVO") BqglHjxxPageReqVO reqVO);

    default PageResult<BqglHjxxRespVO> getHjxxPage(BqglHjxxPageReqVO reqVO, Long deptId) {
        IPage<BqglHjxxRespVO> page = MyBatisUtils.buildPage(reqVO);
        selectHjxxPage(page, reqVO);
        return new PageResult<>(page.getRecords(), page.getTotal());
    }
}

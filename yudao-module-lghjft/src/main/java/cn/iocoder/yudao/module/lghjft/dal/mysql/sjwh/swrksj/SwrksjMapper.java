package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.swrksj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swrksj.SwrksjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SwrksjMapper extends BaseMapperX<SwrksjDO> {

    /**
     * v1 的查询使用子查询包裹: select * from (SELECT ..., (子查询) dept_id1 FROM gh_jf j WHERE 固定条件) a WHERE 动态条件
     * 由于存在计算列 dept_id1 和复杂硬编码条件, 使用 XML mapper
     */
    IPage<SwrksjDO> selectSwrksjPage(Page<SwrksjDO> page, @Param("query") SwrksjPageReqVO query);

    List<SwrksjDO> selectSwrksjList(@Param("query") SwrksjPageReqVO query);

    default PageResult<SwrksjDO> selectPage(SwrksjPageReqVO reqVO) {
        Page<SwrksjDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<SwrksjDO> iPage = selectSwrksjPage(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }
}

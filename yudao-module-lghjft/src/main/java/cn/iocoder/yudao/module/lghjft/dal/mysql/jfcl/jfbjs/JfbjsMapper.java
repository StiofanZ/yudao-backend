package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JfbjsMapper extends BaseMapperX<JfclJfbjsDO> {

    default PageResult<JfclJfbjsDO> selectPage(JfbjsPageReqVO reqVO) {
        QueryWrapper<JfclJfbjsDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: t.jsbj='N'
        wrapper.eq("JSBJ", "N");
        wrapper.orderByDesc("GHJF_ID");
        return selectPage(reqVO, wrapper);
    }
}

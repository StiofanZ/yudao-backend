package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JfjsMapper extends BaseMapperX<JfclJfjsDO> {

    default PageResult<JfclJfjsDO> selectPage(JfjsPageReqVO reqVO) {
        QueryWrapper<JfclJfjsDO> wrapper = new QueryWrapper<>();
        // v1: gh_qsjshkrj 表，按日期范围过滤
        if (reqVO.getRkrqStart() != null && reqVO.getRkrqEnd() != null) {
            wrapper.between("RKRQ", reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        wrapper.orderByDesc("RKRQ");
        return selectPage(reqVO, wrapper);
    }
}

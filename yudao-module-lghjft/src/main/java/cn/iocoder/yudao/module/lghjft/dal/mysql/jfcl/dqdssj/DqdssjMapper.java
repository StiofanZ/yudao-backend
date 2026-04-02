package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DqdssjMapper extends BaseMapperX<JfclDqdssjDO> {

    default PageResult<JfclDqdssjDO> selectPage(DqdssjPageReqVO reqVO) {
        QueryWrapper<JfclDqdssjDO> wrapper = new QueryWrapper<>();
        // v1: RKRQ BETWEEN date range
        if (reqVO.getRkrqStart() != null && reqVO.getRkrqEnd() != null) {
            wrapper.between("RKRQ", reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        wrapper.orderByDesc("RKRQ");
        return selectPage(reqVO, wrapper);
    }
}

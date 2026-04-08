package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DqdssjMapper extends BaseMapperX<JfclDqdssjDO> {

    /**
     * v1: selecList - paginated query gh_qsjshkrj LEFT JOIN sys_user
     */
    default PageResult<JfclDqdssjDO> selectPage(DqdssjPageReqVO reqVO) {
        QueryWrapper<JfclDqdssjDO> wrapper = new QueryWrapper<>();
        if (reqVO.getRkrqStart() != null && reqVO.getRkrqEnd() != null) {
            wrapper.between("rkrq", reqVO.getRkrqStart(), reqVO.getRkrqEnd());
        }
        wrapper.orderByDesc("rkrq");
        return selectPage(reqVO, wrapper);
    }

    /**
     * v1: selecListzl - paginated query gh_jf WHERE jsbj='E' + 60+ optional filters
     */
    List<JfzcDqdssjVo> selecListzl(DqzldssjPageReqVO reqVO);

    /**
     * v1: selectJfclDqdssj — FROM gh_jf with date range + jsbj='N'
     */
    int updateJfclDqdssj(@Param("list") List<JfzcDqdssjVo> jsList);
}

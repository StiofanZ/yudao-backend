package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsjfclMapper extends BaseMapperX<TsjfclDO> {

    default PageResult<TsjfclDO> selectPage(TsjfclPageReqVO reqVO) {
        QueryWrapper<TsjfclDO> wrapper = new QueryWrapper<>();
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        wrapper.orderByDesc("GHJF_ID");
        return selectPage(reqVO, wrapper);
    }
}

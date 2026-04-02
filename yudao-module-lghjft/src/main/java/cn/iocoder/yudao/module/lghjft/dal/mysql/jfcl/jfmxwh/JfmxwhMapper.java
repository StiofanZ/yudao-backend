package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfmxwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfmxwh.JfmxwhDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JfmxwhMapper extends BaseMapperX<JfmxwhDO> {

    default PageResult<JfmxwhDO> selectPage(JfmxwhPageReqVO reqVO) {
        QueryWrapper<JfmxwhDO> wrapper = new QueryWrapper<>();
        // v1: (dept_id = #{deptId} OR SDGHJG_DM = #{deptId})
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w.eq("dept_id", reqVO.getDeptId()).or().eq("SDGHJG_DM", reqVO.getDeptId()));
        }
        wrapper.orderByDesc("GHJF_ID");
        return selectPage(reqVO, wrapper);
    }
}

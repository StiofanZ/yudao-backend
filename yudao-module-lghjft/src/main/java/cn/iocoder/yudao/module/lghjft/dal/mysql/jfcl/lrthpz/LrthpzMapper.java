package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.lrthpz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.lrthpz.LrthpzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LrthpzMapper extends BaseMapperX<LrthpzDO> {

    default PageResult<LrthpzDO> selectPage(LrthpzPageReqVO reqVO) {
        QueryWrapper<LrthpzDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: yxbj='Y'
        wrapper.eq("YXBJ", "Y");
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        // v1 hardcoded: order by hkpch desc
        wrapper.orderByDesc("HKPCH");
        return selectPage(reqVO, wrapper);
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.thpzcf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThpzcfMapper extends BaseMapperX<ThpzcfDO> {

    default PageResult<ThpzcfDO> selectPage(ThpzcfPageReqVO reqVO) {
        QueryWrapper<ThpzcfDO> wrapper = new QueryWrapper<>();
        // v1 hardcoded: thbj='Y' and yxbj='Y'
        wrapper.eq("THBJ", "Y");
        wrapper.eq("YXBJ", "Y");
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        wrapper.orderByDesc("HKXX_ID");
        return selectPage(reqVO, wrapper);
    }
}

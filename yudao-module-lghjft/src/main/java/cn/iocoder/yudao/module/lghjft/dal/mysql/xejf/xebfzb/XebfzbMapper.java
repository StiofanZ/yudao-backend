package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebfzb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb.XebfzbDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XebfzbMapper extends BaseMapperX<XebfzbDO> {

    /**
     * v1 selectXebfzbList 完整条件迁移
     * 关键: deptId 使用 find_in_set 子查询匹配部门层级
     * (DEPT_ID = deptId or DEPT_ID IN (SELECT t.dept_id FROM sys_dept t WHERE find_in_set(deptId, ancestors)))
     */
    default PageResult<XebfzbDO> selectPage(XebfzbPageReqVO reqVO) {
        QueryWrapper<XebfzbDO> wrapper = new QueryWrapper<>();
        if (reqVO.getXend() != null && !reqVO.getXend().isEmpty()) {
            wrapper.eq("xend", reqVO.getXend());
        }
        // v1: dept hierarchy filter with find_in_set
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.and(w -> w.eq("dept_id", reqVO.getDeptId())
                    .or()
                    .apply("dept_id in (select t.dept_id from sys_dept t where find_in_set({0}, ancestors))",
                            reqVO.getDeptId()));
        }
        wrapper.orderByDesc("xend");
        return selectPage(reqVO, wrapper);
    }
}

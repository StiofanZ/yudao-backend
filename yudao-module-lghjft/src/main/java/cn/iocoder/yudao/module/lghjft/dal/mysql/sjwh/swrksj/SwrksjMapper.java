package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.swrksj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swrksj.SwrksjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SwrksjMapper extends BaseMapperX<SwrksjDO> {

    default PageResult<SwrksjDO> selectPage(SwrksjPageReqVO reqVO) {
        // 使用普通 QueryWrapper 避免 LambdaQueryWrapperX 类型链式调用中断
        QueryWrapper<SwrksjDO> wrapper = new QueryWrapper<>();

        // 搜索条件
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("ZGSWJ_DM", reqVO.getZgswjDm());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("ZSPM_DM", reqVO.getZspmDm());
        }
        if (reqVO.getJsbj() != null && !reqVO.getJsbj().isEmpty()) {
            wrapper.eq("JSBJ", reqVO.getJsbj());
        }

        // v1 硬编码过滤条件（核心业务逻辑）
        wrapper.ne("ZSPM_DM", "399001910");
        wrapper.notIn("JSBJ", "T", "S", "Y", "J");
        wrapper.notInSql("DJXH", "SELECT djxh FROM gh_hj");

        // v1 逻辑：按 dm_swjg 关联的工会机构代码过滤
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.inSql("ZGSWJ_DM", "SELECT SWJG_DM FROM dm_swjg WHERE GHJG_DM = '" + reqVO.getDeptId() + "'");
        }

        wrapper.orderByDesc("ghjf_id");

        return selectPage(reqVO, wrapper);
    }
}

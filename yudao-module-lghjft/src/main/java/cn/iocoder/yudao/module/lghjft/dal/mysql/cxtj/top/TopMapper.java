package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopMapper extends BaseMapperX<TopDO> {

    /**
     * 缴费排行分页 — 还原 v1 selectTopList1 完整 WHERE 条件
     * <p>
     * v1 selectTopList1 使用 root "100000"（全国），order by jfjedn desc
     * v1 selectTopList  使用 root "620000"（甘肃省），order by jfjecy desc，limit 100
     * V2 分页查询对应 v1 selectTopList1（默认 root = "100000"）
     */
    default PageResult<TopDO> selectPage(TopPageReqVO reqVO) {
        QueryWrapper<TopDO> wrapper = new QueryWrapper<>();

        // --- V1 完整条件 ---
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("SHXYDM", reqVO.getShxydm());
        }
        // v1: nsrmc 用 LIKE（遵循 CLAUDE.md 迁移规范）
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("NSRMC", reqVO.getNsrmc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("DEPT_ID", reqVO.getDeptId());
        }
        if (reqVO.getDwmc() != null && !reqVO.getDwmc().isEmpty()) {
            wrapper.like("DWMC", reqVO.getDwmc());
        }

        // v1 selectTopList1: order by jfjedn desc
        wrapper.orderByDesc("JFJEDN");

        return selectPage(reqVO, wrapper);
    }
}

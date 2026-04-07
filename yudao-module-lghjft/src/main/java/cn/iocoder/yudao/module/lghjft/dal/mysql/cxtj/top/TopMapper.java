package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopMapper extends BaseMapperX<TopDO> {

    /**
     * 构建 V1 完整 16 字段 WHERE 条件
     */
    private static void applyAllConditions(QueryWrapper<TopDO> wrapper, TopPageReqVO reqVO) {
        if (reqVO.getDjxh() != null && !reqVO.getDjxh().isEmpty()) {
            wrapper.eq("djxh", reqVO.getDjxh());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        // v1: nsrmc 用 eq（与 v1 xml 一致）
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.eq("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getDwmc() != null && !reqVO.getDwmc().isEmpty()) {
            wrapper.eq("dwmc", reqVO.getDwmc());
        }
        if (reqVO.getBsdn() != null) {
            wrapper.eq("bsdn", reqVO.getBsdn());
        }
        if (reqVO.getBswn() != null) {
            wrapper.eq("bswn", reqVO.getBswn());
        }
        if (reqVO.getBscy() != null) {
            wrapper.eq("bscy", reqVO.getBscy());
        }
        if (reqVO.getJfjedn() != null) {
            wrapper.eq("jfjedn", reqVO.getJfjedn());
        }
        if (reqVO.getJfjewn() != null) {
            wrapper.eq("jfjewn", reqVO.getJfjewn());
        }
        if (reqVO.getJfjecy() != null) {
            wrapper.eq("jfjecy", reqVO.getJfjecy());
        }
        if (reqVO.getJfbl() != null && !reqVO.getJfbl().isEmpty()) {
            wrapper.eq("jfbl", reqVO.getJfbl());
        }
        if (reqVO.getSjjedn() != null) {
            wrapper.eq("sjjedn", reqVO.getSjjedn());
        }
        if (reqVO.getSjjewn() != null) {
            wrapper.eq("sjjewn", reqVO.getSjjewn());
        }
        if (reqVO.getSjjecy() != null) {
            wrapper.eq("sjjecy", reqVO.getSjjecy());
        }
        if (reqVO.getSjbl() != null && !reqVO.getSjbl().isEmpty()) {
            wrapper.eq("sjbl", reqVO.getSjbl());
        }
    }

    /**
     * 缴费排行列表（非分页） — 还原 v1 selectTopList
     * <p>
     * v1 selectTopList: root "620000"（甘肃省），order by jfjecy desc，limit 100
     */
    default List<TopDO> selectList(TopPageReqVO reqVO) {
        QueryWrapper<TopDO> wrapper = new QueryWrapper<>();
        applyAllConditions(wrapper, reqVO);
        wrapper.orderByDesc("jfjecy");
        wrapper.last("limit 100");
        return selectList(wrapper);
    }

    /**
     * 缴费排行分页 — 还原 v1 selectTopList1
     * <p>
     * v1 selectTopList1: root "100000"（全国），order by jfjedn desc
     */
    default PageResult<TopDO> selectPage(TopPageReqVO reqVO) {
        QueryWrapper<TopDO> wrapper = new QueryWrapper<>();
        applyAllConditions(wrapper, reqVO);
        wrapper.orderByDesc("jfjedn");
        return selectPage(reqVO, wrapper);
    }
}

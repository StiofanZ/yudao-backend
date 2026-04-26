package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf.GhHkxxxejfDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GhHkxxxejfMapper extends BaseMapperX<GhHkxxxejfDO> {

    /**
     * v1 selectGhHkxxxejfList 完整条件迁移
     * 关键: 硬编码 lx in ('9','A','5','6','7','D')
     * zh in (zh, zh1, zh2, zh3) 多账号匹配
     * MID(HKPCH,1,8) between 日期范围
     * hm like 模糊匹配
     */
    default PageResult<GhHkxxxejfDO> selectPage(GhHkxxxejfPageReqVO reqVO) {
        QueryWrapper<GhHkxxxejfDO> wrapper = new QueryWrapper<>();
        // v1: lx in ('9','A','5','6','7','D')
        wrapper.in("lx", "9", "A", "5", "6", "7", "D");
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("hkpch", reqVO.getHkpch());
        }
        // v1: MID(HKPCH,1,8) between beginHkpch and endHkpch
        if (reqVO.getBeginHkpch() != null && !reqVO.getBeginHkpch().isEmpty()
                && reqVO.getEndHkpch() != null && !reqVO.getEndHkpch().isEmpty()) {
            wrapper.apply("mid(hkpch,1,8) between {0} and {1}",
                    reqVO.getBeginHkpch(), reqVO.getEndHkpch());
        }
        if (reqVO.getJfqj() != null) {
            wrapper.eq("jfqj", reqVO.getJfqj());
        }
        if (reqVO.getLx() != null && !reqVO.getLx().isEmpty()) {
            wrapper.eq("lx", reqVO.getLx());
        }
        // v1: zh in (zh, zh1, zh2, zh3) — multi-account search
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            List<String> zhList = new ArrayList<>();
            zhList.add(reqVO.getZh());
            if (reqVO.getZh1() != null && !reqVO.getZh1().isEmpty()) zhList.add(reqVO.getZh1());
            if (reqVO.getZh2() != null && !reqVO.getZh2().isEmpty()) zhList.add(reqVO.getZh2());
            if (reqVO.getZh3() != null && !reqVO.getZh3().isEmpty()) zhList.add(reqVO.getZh3());
            wrapper.in("zh", zhList);
        }
        // v1: hm like
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("hm", reqVO.getHm());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getWqyfje() != null) {
            wrapper.eq("wqyfje", reqVO.getWqyfje());
        }
        if (reqVO.getJe() != null) {
            wrapper.eq("je", reqVO.getJe());
        }
        if (reqVO.getThbj() != null && !reqVO.getThbj().isEmpty()) {
            wrapper.eq("thbj", reqVO.getThbj());
        }
        wrapper.orderByDesc("hkxx_id");
        return selectPage(reqVO, wrapper);
    }
}

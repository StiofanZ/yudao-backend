package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NdrwwcMapper extends BaseMapperX<NdrwwcDO> {

    /**
     * 分上缴周期统计分页 -- 还原 v1 selectNdrwwcList 完整 17 个 WHERE 条件
     * <p>
     * v1 dept filter:
     * (DWDM = #{dwdm} or DWDM IN (SELECT t.dept_id FROM sys_dept t WHERE find_in_set(#{dwdm}, ancestors)))
     */
    default PageResult<NdrwwcDO> selectPage(NdrwwcPageReqVO reqVO) {
        QueryWrapper<NdrwwcDO> wrapper = new QueryWrapper<>();

        // --- v1 完整 17 个 WHERE 条件 ---

        // 1. nd
        if (reqVO.getNd() != null && !reqVO.getNd().isEmpty()) {
            wrapper.eq("nd", reqVO.getNd());
        }
        // 2. dwdm — v1: dept hierarchy filter using find_in_set on sys_dept.ancestors
        if (reqVO.getDwdm() != null && !reqVO.getDwdm().isEmpty()) {
            wrapper.and(w -> w
                    .eq("dwdm", reqVO.getDwdm())
                    .or()
                    .inSql("dwdm",
                            "select t.dept_id from sys_dept t where find_in_set('"
                                    + reqVO.getDwdm() + "', ancestors)"));
        }
        // 3. dwmc
        if (reqVO.getDwmc() != null && !reqVO.getDwmc().isEmpty()) {
            wrapper.eq("dwmc", reqVO.getDwmc());
        }
        // 4. bs
        if (reqVO.getBs() != null) {
            wrapper.eq("bs", reqVO.getBs());
        }
        // 5. hs
        if (reqVO.getHs() != null) {
            wrapper.eq("hs", reqVO.getHs());
        }
        // 6. jfje
        if (reqVO.getJfje() != null) {
            wrapper.eq("jfje", reqVO.getJfje());
        }
        // 7. cbjje
        if (reqVO.getCbjje() != null) {
            wrapper.eq("cbjje", reqVO.getCbjje());
        }
        // 8. jcghje
        if (reqVO.getJcghje() != null) {
            wrapper.eq("jcghje", reqVO.getJcghje());
        }
        // 9. hyghje
        if (reqVO.getHyghje() != null) {
            wrapper.eq("hyghje", reqVO.getHyghje());
        }
        // 10. sdghje
        if (reqVO.getSdghje() != null) {
            wrapper.eq("sdghje", reqVO.getSdghje());
        }
        // 11. xjghje
        if (reqVO.getXjghje() != null) {
            wrapper.eq("xjghje", reqVO.getXjghje());
        }
        // 12. sjghje
        if (reqVO.getSjghje() != null) {
            wrapper.eq("sjghje", reqVO.getSjghje());
        }
        // 13. szghje
        if (reqVO.getSzghje() != null) {
            wrapper.eq("szghje", reqVO.getSzghje());
        }
        // 14. qgghje
        if (reqVO.getQgghje() != null) {
            wrapper.eq("qgghje", reqVO.getQgghje());
        }
        // 15. szqzhj
        if (reqVO.getSzqzhj() != null) {
            wrapper.eq("szqzhj", reqVO.getSzqzhj());
        }
        // 16. sdsje
        if (reqVO.getSdsje() != null) {
            wrapper.eq("sdsje", reqVO.getSdsje());
        }
        // 17. swjgje
        if (reqVO.getSwjgje() != null) {
            wrapper.eq("swjgje", reqVO.getSwjgje());
        }

        wrapper.orderByDesc("nd");

        return selectPage(reqVO, wrapper);
    }
}

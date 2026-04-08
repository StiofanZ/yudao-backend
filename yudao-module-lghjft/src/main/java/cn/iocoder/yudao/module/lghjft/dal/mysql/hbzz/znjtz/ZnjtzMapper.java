package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjtz.ZnjtzDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZnjtzMapper extends BaseMapperX<ZnjtzDO> {

    default PageResult<ZnjtzDO> selectPage(ZnjtzPageReqVO reqVO) {
        QueryWrapper<ZnjtzDO> wrapper = new QueryWrapper<>();

        // v1 硬编码条件: jsbj != 'Y' AND zspm_dm='399001910' AND rkrq BETWEEN '2020-02-01' AND '2020-12-31'
        wrapper.ne("jsbj", "Y");
        wrapper.eq("zspm_dm", "399001910");
        wrapper.between("rkrq", "2020-02-01", "2020-12-31");

        // v1 搜索条件
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("dept_id", reqVO.getDeptId());
        }
        if (reqVO.getShxydm() != null && !reqVO.getShxydm().isEmpty()) {
            wrapper.eq("shxydm", reqVO.getShxydm());
        }
        if (reqVO.getNsrmc() != null && !reqVO.getNsrmc().isEmpty()) {
            wrapper.like("nsrmc", reqVO.getNsrmc());
        }
        if (reqVO.getZgswjDm() != null && !reqVO.getZgswjDm().isEmpty()) {
            wrapper.eq("zgswj_dm", reqVO.getZgswjDm());
        }
        if (reqVO.getZspmDm() != null && !reqVO.getZspmDm().isEmpty()) {
            wrapper.eq("zspm_dm", reqVO.getZspmDm());
        }
        if (reqVO.getSkssqq() != null && !reqVO.getSkssqq().isEmpty()) {
            wrapper.ge("skssqq", reqVO.getSkssqq());
        }
        if (reqVO.getSkssqz() != null && !reqVO.getSkssqz().isEmpty()) {
            wrapper.le("skssqz", reqVO.getSkssqz());
        }
        if (reqVO.getYbtse() != null && !reqVO.getYbtse().isEmpty()) {
            wrapper.eq("ybtse", reqVO.getYbtse());
        }
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            wrapper.between("rkrq", reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }

        // v1 order by rkrq desc
        wrapper.orderByDesc("rkrq");

        return selectPage(reqVO, wrapper);
    }

    /**
     * v1 cascade delete from cxtj_cbjqrfb
     */
    void deleteCbjqrfbByGhjfIds(@Param("ghjfIds") List<Long> ghjfIds);

    void deleteCbjqrfbByGhjfId(@Param("ghjfId") Long ghjfId);
}

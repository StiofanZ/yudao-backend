package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsFpblMapper extends BaseMapperX<CsFpblDO> {

    default PageResult<CsFpblDO> selectPage(CsFpblPageReqVO reqVO) {
        QueryWrapper<CsFpblDO> wrapper = new QueryWrapper<>();
        if (reqVO.getLx() != null && !reqVO.getLx().isEmpty()) {
            wrapper.eq("lx", reqVO.getLx());
        }
        if (reqVO.getMs() != null && !reqVO.getMs().isEmpty()) {
            wrapper.like("ms", reqVO.getMs());
        }
        // yxqq/yxqz: frontend sends date string "YYYY-MM-DD", use >= / <= for range filter
        if (reqVO.getYxqq() != null && !reqVO.getYxqq().isEmpty()) {
            wrapper.ge("yxqq", reqVO.getYxqq());
        }
        if (reqVO.getYxqz() != null && !reqVO.getYxqz().isEmpty()) {
            wrapper.le("yxqz", reqVO.getYxqz());
        }
        if (reqVO.getXybz() != null && !reqVO.getXybz().isEmpty()) {
            wrapper.eq("xybz", reqVO.getXybz());
        }
        if (reqVO.getJcghbl() != null) {
            wrapper.eq("jcghbl", reqVO.getJcghbl());
        }
        if (reqVO.getHyghbl() != null) {
            wrapper.eq("hyghbl", reqVO.getHyghbl());
        }
        if (reqVO.getSdghbl() != null) {
            wrapper.eq("sdghbl", reqVO.getSdghbl());
        }
        if (reqVO.getXjghbl() != null) {
            wrapper.eq("xjghbl", reqVO.getXjghbl());
        }
        if (reqVO.getSjghbl() != null) {
            wrapper.eq("sjghbl", reqVO.getSjghbl());
        }
        if (reqVO.getSzghbl() != null) {
            wrapper.eq("szghbl", reqVO.getSzghbl());
        }
        if (reqVO.getQgzghbl() != null) {
            wrapper.eq("qgzghbl", reqVO.getQgzghbl());
        }
        if (reqVO.getSjcjbl() != null) {
            wrapper.eq("sjcjbl", reqVO.getSjcjbl());
        }
        if (reqVO.getSdsjbl() != null) {
            wrapper.eq("sdsjbl", reqVO.getSdsjbl());
        }
        if (reqVO.getSwjgbl() != null) {
            wrapper.eq("swjgbl", reqVO.getSwjgbl());
        }
        if (reqVO.getTj() != null && !reqVO.getTj().isEmpty()) {
            wrapper.like("tj", reqVO.getTj());
        }
        if (reqVO.getYxj() != null) {
            wrapper.eq("yxj", reqVO.getYxj());
        }
        if (reqVO.getMrbz() != null && !reqVO.getMrbz().isEmpty()) {
            wrapper.eq("mrbz", reqVO.getMrbz());
        }
        wrapper.orderByAsc("yxj");
        return selectPage(reqVO, wrapper);
    }
}

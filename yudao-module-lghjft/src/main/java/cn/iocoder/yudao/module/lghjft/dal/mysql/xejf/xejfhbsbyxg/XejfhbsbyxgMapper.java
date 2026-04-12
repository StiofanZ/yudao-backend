package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfhbsbyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsbyxg.XejfhbsbyxgDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XejfhbsbyxgMapper extends BaseMapperX<XejfhbsbyxgDO> {

    default PageResult<XejfhbsbyxgDO> selectPage(XejfhbsbyxgPageReqVO reqVO) {
        QueryWrapper<XejfhbsbyxgDO> wrapper = new QueryWrapper<>();
        wrapper.in("LX", "3", "4");
        wrapper.eq("THBJ", "Y");
        wrapper.eq("XGBJ", "Y");
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getJfqj() != null) {
            wrapper.eq("JFQJ", reqVO.getJfqj());
        }
        if (reqVO.getZh() != null && !reqVO.getZh().isEmpty()) {
            wrapper.eq("ZH", reqVO.getZh());
        }
        if (reqVO.getHm() != null && !reqVO.getHm().isEmpty()) {
            wrapper.like("HM", reqVO.getHm());
        }
        if (reqVO.getXzh() != null && !reqVO.getXzh().isEmpty()) {
            wrapper.eq("XZH", reqVO.getXzh());
        }
        if (reqVO.getXhm() != null && !reqVO.getXhm().isEmpty()) {
            wrapper.like("XHM", reqVO.getXhm());
        }
        if (reqVO.getJe() != null) {
            wrapper.eq("JE", reqVO.getJe());
        }
        if (reqVO.getThrq() != null && !reqVO.getThrq().isEmpty()) {
            wrapper.eq("THRQ", reqVO.getThrq());
        }
        if (reqVO.getUpdateTime() != null && !reqVO.getUpdateTime().isEmpty()) {
            wrapper.likeRight("XGSJ", reqVO.getUpdateTime());
        }
        if (reqVO.getDeptId() != null && !reqVO.getDeptId().isEmpty()) {
            wrapper.eq("DEPT_ID", reqVO.getDeptId());
        }
        wrapper.orderByDesc("HKXX_ID");
        return selectPage(reqVO, wrapper);
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjftj;

import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyjftjMapper {

    List<XwqyjftjAggVO> selectXwqyjftjList(@Param("req") XwqyjftjPageReqVO req);

    List<XwqyjftjfhAggVO> selectXwqyjftjfhList(@Param("req") XwqyjftjPageReqVO req);
}

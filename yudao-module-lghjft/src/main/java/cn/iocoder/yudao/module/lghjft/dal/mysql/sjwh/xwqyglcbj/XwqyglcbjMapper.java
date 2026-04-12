package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.xwqyglcbj;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjSaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyglcbjMapper {

    List<XwqyglcbjResVO> selectXwqyglcbjList(XwqyglcbjPageReqVO pageReqVO);

    long selectXwqyglcbjCount(XwqyglcbjPageReqVO pageReqVO);

    XwqyglcbjResVO selectXwqyglcbjByDjxh(@Param("djxh") String djxh);

    int updateXwqyglcbj(XwqyglcbjSaveReqVO reqVO);
}

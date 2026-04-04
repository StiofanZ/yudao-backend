package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqygl;


import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyglMapper {
    List<XwqyglResVO> selectXwqyglList(XwqyglQuery query);
    long selectXwqyglCount(XwqyglQuery query);

    XwqyglResVO selectXwqyglByDjxh(@Param("djxh") String djxh);

    int insertXwqygl(XwqyglSaveReqVO reqVO);

    int updateXwqygl(XwqyglSaveReqVO reqVO);

    int deleteXwqyglByDjxhs(@Param("djxhs") String[] djxhs);
}
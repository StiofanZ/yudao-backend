package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqygl;


import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyglMapper {
    List<XwqyglResVO> selectXwqyglList(XwqyglPageReqVO pageReqVO);
    long selectXwqyglCount(XwqyglPageReqVO pageReqVO);

    XwqyglResVO selectXwqyglByDjxh(@Param("djxh") String djxh);

    int insertXwqygl(XwqyglSaveReqVO reqVO);

    int updateXwqygl(XwqyglSaveReqVO reqVO);

    int deleteXwqyglByDjxhs(@Param("djxhs") String[] djxhs);
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqygl;


import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XwqyglMapper {
    List<XwqyglRespVO> selectXwqyglList(XwqyglQuery query);
    long selectXwqyglCount(XwqyglQuery query);
}
package cn.iocoder.yudao.module.lghjft.dal.mysql.jf.jfmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.Jfmx;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JfmxMapper {
    /**
     * 获取经费明细列表
     */
    List<Jfmx> selectJfmxList(JfmxQuery query);

    /**
     * 获取经费明细总数
     */
    int selectJfmxCount(JfmxQuery query);
}

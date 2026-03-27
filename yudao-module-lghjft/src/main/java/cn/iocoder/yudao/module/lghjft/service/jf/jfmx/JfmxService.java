package cn.iocoder.yudao.module.lghjft.service.jf.jfmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.Jfmx;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxExportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxQuery;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

public interface JfmxService {

    /**
     * 获取经费明细列表
     */
    List<Jfmx> selectJfmxList(JfmxQuery query);

    /**
     * 获取经费明细分页列表
     */
    PageResult<Jfmx> selectJfmxPage(JfmxQuery query);

    /**
     * 获取经费明细总数
     */
    int selectJfmxCount(JfmxQuery query);

    /**
     * 获取经费明细导出列表
     */
    List<JfmxExportVO> selectJfmxExportList(JfmxQuery query);

}

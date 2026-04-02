package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfSummaryResVO;

/**
 *  经费明细对象 Service 接口
 *
 * @author 李文军
 */
public interface JfService {

    PageResult<JfResVO> selectJfmxList(JfPageReqVO jfmx);

    PageResult<JfResVO> selectJftzmxList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectJffymxList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectJffnmxList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectJffsjzqmxList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectJftzfnList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectJftzfswjgList(JfPageReqVO jfmx);

    PageResult<JfSummaryResVO> selectSzdzhdList(JfPageReqVO jfmx);

}

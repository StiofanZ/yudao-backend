package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfRespVO;

/**
 *  经费明细对象 Service 接口
 *
 * @author 李文军
 */
public interface JfService {

    PageResult<JfRespVO> selectJftzmxList(JfPageReqVO jfmx);

}

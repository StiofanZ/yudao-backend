package cn.iocoder.yudao.module.lghjft.service.sjwh.xzqh;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.xzqh.XzqhDO;

import java.util.List;

/**
 * 行政区划 Service 接口
 *
 * @author 李文军
 */
public interface XzqhService {


    List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO);

}
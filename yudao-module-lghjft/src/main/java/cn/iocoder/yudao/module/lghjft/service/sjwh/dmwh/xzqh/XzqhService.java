package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;

import java.util.List;

/**
 * 行政区划 Service 接口
 *
 * @author 李文军
 */
public interface XzqhService {


    List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO);

}
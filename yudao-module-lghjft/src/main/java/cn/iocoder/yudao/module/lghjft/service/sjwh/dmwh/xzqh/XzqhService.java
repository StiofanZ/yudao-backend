package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 行政区划 Service 接口
 *
 * @author 李文军
 */
public interface XzqhService {

    /**
     * 创建行政区划
     */
    String createXzqh(@Valid XzqhSaveReqVO createReqVO);

    /**
     * 更新行政区划
     */
    void updateXzqh(@Valid XzqhSaveReqVO updateReqVO);

    /**
     * 批量删除行政区划
     */
    void deleteXzqhByIds(List<String> xzqhDms);

    /**
     * 获得行政区划
     */
    XzqhDO getXzqh(String xzqhDm);

    /**
     * 获得行政区划列表
     */
    List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO);

}

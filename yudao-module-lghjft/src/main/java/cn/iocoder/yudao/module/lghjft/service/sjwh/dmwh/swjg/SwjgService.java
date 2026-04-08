package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.swjg;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.swjg.SwjgDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 税务机关 Service 接口
 *
 * @author 李文军
 */
public interface SwjgService {

    /**
     * 创建税务机关
     */
    String createSwjg(@Valid SwjgSaveReqVO createReqVO);

    /**
     * 更新税务机关
     */
    void updateSwjg(@Valid SwjgSaveReqVO updateReqVO);

    /**
     * 批量删除税务机关
     */
    void deleteSwjgByIds(List<String> swjgDms);

    /**
     * 获得税务机关
     */
    SwjgDO getSwjg(String swjgDm);

    /**
     * 获得税务机关列表
     */
    List<SwjgDO> getSwjgList(SwjgListReqVO listReqVO);

}

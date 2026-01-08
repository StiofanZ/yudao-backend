package cn.iocoder.yudao.module.lghjft.service.sjwh.swjg;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swjg.SwjgDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 税务机关 Service 接口
 *
 * @author 李文军
 */
public interface SwjgService {

   /**
     * 更新税务机关
     *
     * @param updateReqVO 更新信息
     */
    void updateSwjg(@Valid SwjgSaveReqVO updateReqVO);



    /**
     * 获得税务机关
     *
     * @param swjgDm 税务机关代码
     * @return 税务机关
     */
    SwjgDO getSwjg(String swjgDm);

    /**
     * 获得税务机关列表
     *
     * @param listReqVO 查询条件
     * @return 税务机关列表
     */
    List<SwjgDO> getSwjgList(SwjgListReqVO listReqVO);

}
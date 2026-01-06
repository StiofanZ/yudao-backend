package cn.iocoder.yudao.module.dm.service.swjg;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.dm.controller.admin.swjg.vo.*;
import cn.iocoder.yudao.module.dm.dal.dataobject.swjg.SwjgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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
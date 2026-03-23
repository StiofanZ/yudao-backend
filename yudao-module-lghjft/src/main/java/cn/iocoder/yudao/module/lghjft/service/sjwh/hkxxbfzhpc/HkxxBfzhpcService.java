package cn.iocoder.yudao.module.lghjft.service.sjwh.hkxxbfzhpc;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 拨付信息 Service 接口
 *
 * @author 李文军
 */
public interface HkxxBfzhpcService {


    /**
     * 更新拨付信息
     *
     * @param updateReqVO 更新信息
     */
    void updateHkxxBfzhpc(@Valid HkxxBfzhpcSaveReqVO updateReqVO);



    /**
    * 批量删除拨付信息
    *
    * @param ids 编号
    */
    void deleteHkxxBfzhpcListByIds(List<Integer> ids);

    /**
     * 获得拨付信息
     *
     * @param id 编号
     * @return 拨付信息
     */
    HkxxBfzhpcRespVO getHkxxBfzhpc(String spuuid);

    /**
     * 获得拨付信息分页
     *
     * @param pageReqVO 分页查询
     * @return 拨付信息分页
     */
    /**
     * 获得经费排除分页
     */
    PageResult<HkxxBfzhpcRespVO> getBfjfpcPage(HkxxBfzhpcPageReqVO pageReqVO);
}
package cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.HkxxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 基层经费到账对象 Service 接口
 *
 * @author 李文军
 */
public interface HkxxService {

    /**
     * 创建基层经费到账对象
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
//    Integer createHkxx(@Valid HkxxSaveReqVO createReqVO);
//
    /**
     * 更新基层经费到账对象
     *
     * @param updateReqVO 更新信息
     */
    void updateHkxx(@Valid HkxxSaveReqVO updateReqVO);

//    /**
//     * 删除基层经费到账对象
//     *
//     * @param id 编号
//     */
//    void deleteHkxx(Integer id);
//
//    /**
//    * 批量删除基层经费到账对象
//    *
//    * @param ids 编号
//    */
//    void deleteHkxxListByIds(List<Integer> ids);
//
    /**
     * 获得基层经费到账对象
     *
     * @param id 编号
     * @return 基层经费到账对象
     */
    HkxxDO getHkxx(Integer id);

    /**
     * 获得基层经费到账对象分页
     *
     * @param pageReqVO 分页查询
     * @return 基层经费到账对象分页
     */
    PageResult<HkxxRespVO> getHkxxPage(HkxxPageReqVO pageReqVO);

}
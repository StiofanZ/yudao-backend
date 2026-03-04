package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfmx.JfDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 *  经费明细对象 Service 接口
 *
 * @author 李文军
 */
public interface JfService {

    public PageResult<JfRespVO> selectJftzmxList(JfPageReqVO jfmx);
//    /**
//     * 创建 经费明细对象
//     *
//     * @param createReqVO 创建信息
//     * @return 编号
//     */
//    Integer createJf(@Valid JfSaveReqVO createReqVO);
//
//    /**
//     * 更新 经费明细对象
//     *
//     * @param updateReqVO 更新信息
//     */
//    void updateJf(@Valid JfSaveReqVO updateReqVO);
//
//    /**
//     * 删除 经费明细对象
//     *
//     * @param id 编号
//     */
//    void deleteJf(Integer id);
//
//    /**
//    * 批量删除 经费明细对象
//    *
//    * @param ids 编号
//    */
//    void deleteJfListByIds(List<Integer> ids);
//
//    /**
//     * 获得 经费明细对象
//     *
//     * @param id 编号
//     * @return  经费明细对象
//     */
//    JfDO getJf(Integer id);
//
//    /**
//     * 获得 经费明细对象分页
//     *
//     * @param pageReqVO 分页查询
//     * @return  经费明细对象分页
//     */
//    PageResult<JfDO> getJfPage(JfPageReqVO pageReqVO);

}
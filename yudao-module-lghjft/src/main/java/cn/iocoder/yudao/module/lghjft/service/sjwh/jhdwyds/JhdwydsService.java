package cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 应代收单位 Service 接口
 *
 * @author 李文军
 */
public interface JhdwydsService {

    /**
     * 创建应代收单位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createJhdwyds(@Valid JhdwydsSaveReqVO createReqVO);

    /**
     * 更新应代收单位
     *
     * @param updateReqVO 更新信息
     */
    void updateJhdwyds(@Valid JhdwydsSaveReqVO updateReqVO);

    /**
     * 删除应代收单位
     *
     * @param id 编号
     */
    void deleteJhdwyds(Integer id);

    /**
    * 批量删除应代收单位
    *
    * @param ids 编号
    */
    void deleteJhdwydsListByIds(List<Integer> ids);

    /**
     * 获得应代收单位
     *
     * @param id 编号
     * @return 应代收单位
     */
    JhdwydsDO getJhdwyds(Integer id);

    /**
     * 获得应代收单位分页
     *
     * @param pageReqVO 分页查询
     * @return 应代收单位分页
     */
    PageResult<JhdwydsDO> getJhdwydsPage(JhdwydsPageReqVO pageReqVO);

    List<JhdwydsDO> getJhdwydsList(JhdwydsReqVO pageReqVO);

}
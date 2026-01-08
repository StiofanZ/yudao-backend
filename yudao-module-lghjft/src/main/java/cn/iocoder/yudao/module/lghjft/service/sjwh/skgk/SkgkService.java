package cn.iocoder.yudao.module.lghjft.service.sjwh.skgk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.skgk.SkgkDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 收款国库 Service 接口
 *
 * @author 李文军
 */
public interface SkgkService {

    /**
     * 创建收款国库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSkgk(@Valid SkgkSaveReqVO createReqVO);

    /**
     * 更新收款国库
     *
     * @param updateReqVO 更新信息
     */
    void updateSkgk(@Valid SkgkSaveReqVO updateReqVO);

    /**
     * 删除收款国库
     *
     * @param id 编号
     */
    void deleteSkgk(Integer id);

    /**
    * 批量删除收款国库
    *
    * @param ids 编号
    */
    void deleteSkgkListByIds(List<Integer> ids);

    /**
     * 获得收款国库
     *
     * @param id 编号
     * @return 收款国库
     */
    SkgkDO getSkgk(Integer id);

    /**
     * 获得收款国库分页
     *
     * @param pageReqVO 分页查询
     * @return 收款国库分页
     */
    PageResult<SkgkDO> getSkgkPage(SkgkPageReqVO pageReqVO);

}
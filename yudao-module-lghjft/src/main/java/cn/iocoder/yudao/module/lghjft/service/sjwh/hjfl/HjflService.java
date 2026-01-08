package cn.iocoder.yudao.module.lghjft.service.sjwh.hjfl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hjfl.HjflDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 户籍分类 Service 接口
 *
 * @author 李文军
 */
public interface HjflService {

    /**
     * 创建户籍分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createHjfl(@Valid HjflSaveReqVO createReqVO);

    /**
     * 更新户籍分类
     *
     * @param updateReqVO 更新信息
     */
    void updateHjfl(@Valid HjflSaveReqVO updateReqVO);

    /**
     * 删除户籍分类
     *
     * @param id 编号
     */
    void deleteHjfl(Integer id);

    /**
    * 批量删除户籍分类
    *
    * @param ids 编号
    */
    void deleteHjflListByIds(List<Integer> ids);

    /**
     * 获得户籍分类
     *
     * @param id 编号
     * @return 户籍分类
     */
    HjflDO getHjfl(Integer id);

    /**
     * 获得户籍分类分页
     *
     * @param pageReqVO 分页查询
     * @return 户籍分类分页
     */
    PageResult<HjflDO> getHjflPage(HjflPageReqVO pageReqVO);

}
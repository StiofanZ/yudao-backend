package cn.iocoder.yudao.module.lghjft.service.sjwh.fpblcopy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo.FpblCopyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo.FpblCopySaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.fpblcopy.FpblCopyDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 分配比例 Service 接口
 *
 * @author 李文军
 */
public interface FpblCopyService {

    /**
     * 创建分配比例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createFpblCopy(@Valid FpblCopySaveReqVO createReqVO);

    /**
     * 更新分配比例
     *
     * @param updateReqVO 更新信息
     */
    void updateFpblCopy(@Valid FpblCopySaveReqVO updateReqVO);

    /**
     * 删除分配比例
     *
     * @param id 编号
     */
    void deleteFpblCopy(Integer id);

    /**
    * 批量删除分配比例
    *
    * @param ids 编号
    */
    void deleteFpblCopyListByIds(List<Integer> ids);

    /**
     * 获得分配比例
     *
     * @param id 编号
     * @return 分配比例
     */
    FpblCopyDO getFpblCopy(Integer id);

    /**
     * 获得分配比例分页
     *
     * @param pageReqVO 分页查询
     * @return 分配比例分页
     */
    PageResult<FpblCopyDO> getFpblCopyPage(FpblCopyPageReqVO pageReqVO);

}
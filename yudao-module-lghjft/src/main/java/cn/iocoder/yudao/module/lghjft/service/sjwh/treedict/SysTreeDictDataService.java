package cn.iocoder.yudao.module.lghjft.service.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDataDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 树形字典数据 Service 接口
 *
 * @author zhaofan
 */
public interface SysTreeDictDataService {

    /**
     * 创建树形字典数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createSysTreeDictData(@Valid SysTreeDictDataSaveReqVO createReqVO);

    /**
     * 更新树形字典数据
     *
     * @param updateReqVO 更新信息
     */
    void updateSysTreeDictData(@Valid SysTreeDictDataSaveReqVO updateReqVO);

    /**
     * 删除树形字典数据
     *
     * @param id 编号
     */
    void deleteSysTreeDictData(String id);

    /**
     * 批量删除树形字典数据
     *
     * @param ids 编号列表
     */
    void deleteSysTreeDictDataListByIds(List<String> ids);

    /**
     * 获得树形字典数据
     *
     * @param id 编号
     * @return 树形字典数据
     */
    SysTreeDictDataDO getSysTreeDictData(String id);

    /**
     * 获得树形字典数据分页
     *
     * @param pageReqVO 分页查询
     * @return 树形字典数据分页
     */
    PageResult<SysTreeDictDataDO> getSysTreeDictDataPage(SysTreeDictDataPageReqVO pageReqVO);

    /**
     * 获得树形字典数据列表
     *
     * @return 树形字典数据列表
     */
    List<SysTreeDictDataDO> getSysTreeDictDataList();

}

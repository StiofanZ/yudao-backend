package cn.iocoder.yudao.module.lghjft.service.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 树形字典 Service 接口
 *
 * @author zhaofan
 */
public interface SysTreeDictService {

    /**
     * 创建树形字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createSysTreeDict(@Valid SysTreeDictSaveReqVO createReqVO);

    /**
     * 更新树形字典
     *
     * @param updateReqVO 更新信息
     */
    void updateSysTreeDict(@Valid SysTreeDictSaveReqVO updateReqVO);

    /**
     * 删除树形字典
     *
     * @param id 编号
     */
    void deleteSysTreeDict(String id);

    /**
     * 批量删除树形字典
     *
     * @param ids 编号列表
     */
    void deleteSysTreeDictListByIds(List<String> ids);

    /**
     * 获得树形字典
     *
     * @param id 编号
     * @return 树形字典
     */
    SysTreeDictDO getSysTreeDict(String id);

    /**
     * 获得树形字典分页
     *
     * @param pageReqVO 分页查询
     * @return 树形字典分页
     */
    PageResult<SysTreeDictDO> getSysTreeDictPage(SysTreeDictPageReqVO pageReqVO);

    /**
     * 获得树形字典列表
     *
     * @return 树形字典列表
     */
    List<SysTreeDictDO> getSysTreeDictList();

}

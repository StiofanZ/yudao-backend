package cn.iocoder.yudao.module.lghjft.service.hjgl.bqgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhDmHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhHjBqxxDO;

import java.util.List;

/**
 * 标签管理 Service 接口
 *
 * @author 芋道源码
 */
public interface BqglService {

    /**
     * 获得标签列表
     *
     * @param rootId 根节点ID
     * @return 标签列表
     */
    List<BqglRespVO> getBqxx(Long rootId);

    /**
     * 创建标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createBqdm(BqglCreateReqVO createReqVO);

    /**
     * 创建标签（别名方法）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createBqxx(BqglCreateReqVO createReqVO);

    /**
     * 更新标签
     *
     * @param updateReqVO 更新信息
     */
    void updateBqdm(BqglUpdateReqVO updateReqVO);

    /**
     * 删除标签
     *
     * @param id 编号
     */
    void deleteBqdm(String id);

    /**
     * 删除标签（别名方法）
     *
     * @param id 编号
     */
    void deleteBqxx(String id);

    /**
     * 获得标签
     *
     * @param id 编号
     * @return 标签
     */
    GhDmHjBqDO getBqdm(String id);

    /**
     * 获得标签列表
     *
     * @param pageReqVO 分页查询
     * @return 标签分页
     */
    PageResult<BqglRespVO> listBqdm(BqglPageReqVO pageReqVO);

    /**
     * 获得户籍信息分页
     *
     * @param pageReqVO 分页查询
     * @param deptId    部门ID
     * @return 户籍信息分页
     */
    PageResult<BqglHjxxRespVO> listHjxx(BqglHjxxPageReqVO pageReqVO, Long deptId);

    /**
     * 保存户籍标签
     *
     * @param saveReqVO 保存信息
     */
    void saveHjxx(BqglHjxxSaveReqVO saveReqVO);

    /**
     * 获得户籍标签列表
     *
     * @param djxh 登记序号
     * @return 标签列表
     */
    List<GhHjBqxxDO> getHjxx(String djxh);
}

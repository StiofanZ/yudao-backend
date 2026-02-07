package cn.iocoder.yudao.module.lghjft.service.hj.ghhj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhj.GhHjDO;
import jakarta.validation.Valid;

/**
 * 基层账户空需维护对象 Service 接口
 *
 * @author 芋道源码
 */
public interface GhHjService {

    /**
     * 创建基层账户空需维护对象
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createGhHj(@Valid GhHjCreateReqVO createReqVO);

    /**
     * 更新基层账户空需维护对象
     *
     * @param updateReqVO 更新信息
     */
    void updateGhHj(@Valid GhHjUpdateReqVO updateReqVO);

    /**
     * 删除基层账户空需维护对象
     *
     * @param id 编号
     */
    void deleteGhHj(String id);

    /**
     * 获得基层账户空需维护对象
     *
     * @param id 编号
     * @return 基层账户空需维护对象
     */
    GhHjDO getGhHj(String id);

    /**
     * 获得基层账户空需维护对象分页
     *
     * @param pageReqVO 分页查询
     * @return 基层账户空需维护对象分页
     */
    PageResult<GhHjDO> getGhHjPage(GhHjPageReqVO pageReqVO);

}

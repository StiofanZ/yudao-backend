package cn.iocoder.yudao.module.lghjft.service.hj.bqgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglRespVO;
import java.util.List;

/**
 * 标签管理 Service 接口
 *
 * @author 芋道源码
 */
public interface BqglService {

    /**
     * 查询标签信息列表
     *
     * @param rootId 根部门ID
     * @return 标签信息列表
     */
    List<BqglRespVO> getBqxx(Long rootId);

    /**
     * 创建标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createBqxx(BqglCreateReqVO createReqVO);

    /**
     * 删除标签
     *
     * @param id 编号
     */
    void deleteBqxx(String id);

    /**
     * 获得户籍信息分页
     *
     * @param pageReqVO 分页查询参数
     * @param deptId 部门ID
     * @return 户籍信息分页
     */
    PageResult<BqglHjxxRespVO> getHjxxPage(BqglHjxxPageReqVO pageReqVO, Long deptId);

    /**
     * 保存户籍标签信息
     *
     * @param saveReqVO 保存信息
     */
    void saveHjxx(BqglHjxxSaveReqVO saveReqVO);
}

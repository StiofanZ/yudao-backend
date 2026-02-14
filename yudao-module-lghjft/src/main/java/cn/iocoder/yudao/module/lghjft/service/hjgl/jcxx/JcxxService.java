package cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import jakarta.validation.Valid;

/**
 * 户籍管理/基础信息 Service 接口
 *
 * @author 芋道源码
 */
public interface JcxxService {

    /**
     * 创建户籍管理/基础信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createJcxx(@Valid JcxxCreateReqVO createReqVO);

    /**
     * 更新户籍管理/基础信息
     *
     * @param updateReqVO 更新信息
     */
    void updateJcxx(@Valid JcxxUpdateReqVO updateReqVO);

    /**
     * 删除户籍管理/基础信息
     *
     * @param id 编号
     */
    void deleteJcxx(String id);

    /**
     * 获得户籍管理/基础信息
     *
     * @param id 编号
     * @return 户籍管理/基础信息
     */
    GhHjJcxxDO getJcxx(String id);

    /**
     * 获得户籍管理/基础信息分页
     *
     * @param pageReqVO 分页查询
     * @return 户籍管理/基础信息分页
     */
    PageResult<GhHjJcxxDO> getJcxxPage(JcxxPageReqVO pageReqVO);

}

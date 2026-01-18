package cn.iocoder.yudao.module.lghjft.service.nrgl.nrxx;

import java.util.List;
import jakarta.validation.Valid;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.nrxx.NrxxDO;

/**
 * 内容管理 Service 接口
 *
 * @author 芋道源码
 */
public interface NrxxService {

    /**
     * 创建内容管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNrxx(@Valid NrxxCreateReqVO createReqVO);

    /**
     * 更新内容管理
     *
     * @param updateReqVO 更新信息
     */
    void updateNrxx(@Valid NrxxUpdateReqVO updateReqVO);

    /**
     * 删除内容管理
     *
     * @param id 编号
     */
    void deleteNrxx(Long id);

    /**
     * 获得内容管理
     *
     * @param id 编号
     * @return 内容管理
     */
    NrxxDO getNrxx(Long id);

    /**
     * 获得内容管理列表
     *
     * @param listReqVO 列表查询
     * @return 内容管理列表
     */
    List<NrxxDO> getNrxxList(NrxxListReqVO listReqVO);

}

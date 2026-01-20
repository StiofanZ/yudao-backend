package cn.iocoder.yudao.module.lghjft.service.nrgl.cjwt;

import java.util.List;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import jakarta.validation.Valid;

/**
 * 常见问题 Service 接口
 *
 * @author 芋道源码
 */
public interface CjwtService {

    /**
     * 创建常见问题
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCjwt(@Valid CjwtCreateReqVO createReqVO);

    /**
     * 更新常见问题
     *
     * @param updateReqVO 更新信息
     */
    void updateCjwt(@Valid CjwtUpdateReqVO updateReqVO);

    /**
     * 删除常见问题
     *
     * @param id 编号
     */
    void deleteCjwt(Long id);

    /**
     * 获得常见问题
     *
     * @param id 编号
     * @return 常见问题
     */
    CjwtDO getCjwt(Long id);

    /**
     * 获得常见问题列表
     *
     * @param listReqVO 查询条件
     * @return 常见问题列表
     */
    List<CjwtDO> getCjwtList(CjwtListReqVO listReqVO);

    /**
     * 发布常见问题
     *
     * @param id 编号
     */
    void publishCjwt(Long id);

    /**
     * 获得公开常见问题列表
     *
     * @param deptId 部门编号
     * @return 常见问题列表
     */
    List<CjwtDO> getPublicCjwtList(Long deptId);

}

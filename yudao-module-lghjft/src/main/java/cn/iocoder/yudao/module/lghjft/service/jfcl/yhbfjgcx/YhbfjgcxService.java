package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 银行拨付结果查询 Service 接口
 *
 * @author 李文军
 */
public interface YhbfjgcxService {

    /**
     * 创建银行拨付结果查询
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createyhbfjgcx(@Valid YhbfjgcxSaveReqVO createReqVO);

    /**
     * 更新银行拨付结果查询
     *
     * @param updateReqVO 更新信息
     */
    void updateyhbfjgcx(@Valid YhbfjgcxSaveReqVO updateReqVO);

    /**
     * 删除银行拨付结果查询
     *
     * @param id 编号
     */
    void deleteyhbfjgcx(String id);

    /**
    * 批量删除银行拨付结果查询
    *
    * @param ids 编号
    */
    void deleteyhbfjgcxListByIds(List<String> ids);

    /**
     * 获得银行拨付结果查询
     *
     * @param id 编号
     * @return 银行拨付结果查询
     */
    YhbfjgcxDO getyhbfjgcx(String id);

    /**
     * 获得银行拨付结果查询分页
     *
     * @param pageReqVO 分页查询
     * @return 银行拨付结果查询分页
     */
    PageResult<YhbfjgcxDO> getyhbfjgcxPage(YhbfjgcxPageReqVO pageReqVO);

}

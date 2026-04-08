package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;

/**
 * 银行拨付结果查询 Service 接口
 *
 * @author 李文军
 */
public interface YhbfjgcxService {

    /**
     * 查询银行拨付结果查询
     */
    YhbfjgcxDO selectYhbfjgcxByBfpch(String bfpch);

    /**
     * 查询银行拨付结果查询列表（分页）
     */
    PageResult<YhbfjgcxDO> selectYhbfjgcxList(YhbfjgcxPageReqVO reqVO);

    /**
     * 新增银行拨付结果查询
     */
    int insertYhbfjgcx(YhbfjgcxDO yhbfjgcx);

    /**
     * 修改银行拨付结果查询
     */
    int updateYhbfjgcx(YhbfjgcxDO yhbfjgcx);

    /**
     * 批量删除银行拨付结果查询
     */
    int deleteYhbfjgcxByBfpchs(String[] bfpchs);

    /**
     * 删除银行拨付结果查询信息
     */
    int deleteYhbfjgcxByBfpch(String bfpch);

}

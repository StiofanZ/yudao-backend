package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfmx;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 银行拨付明细 Service 接口
 *
 * @author 李文军
 */
public interface YhbfmxService {

    /**
     * 创建银行拨付明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createYhbfmx(@Valid YhbfmxSaveReqVO createReqVO);

    /**
     * 更新银行拨付明细
     *
     * @param updateReqVO 更新信息
     */
    void updateYhbfmx(@Valid YhbfmxSaveReqVO updateReqVO);

    /**
     * 删除银行拨付明细
     *
     * @param id 编号
     */
    void deleteYhbfmx(Integer id);

    /**
    * 批量删除银行拨付明细
    *
    * @param ids 编号
    */
    void deleteYhbfmxListByIds(List<Integer> ids);

    /**
     * 获得银行拨付明细
     *
     * @param 编号
     * @return 银行拨付明细
     */
    YhbfmxDO getYhbfmx(Integer bfid);


    /**
     * 获得银行拨付明细分页
     *
     * @param pageReqVO 分页查询
     * @return 银行拨付明细分页
     */
    PageResult<YhbfmxDO> getYhbfmxPage(YhbfmxPageReqVO pageReqVO);
    /**
     * 结算文件生成银行拨付数据
     * @param ghHkxxYhbfmx
     * @return
     */
    public void updateGhyhbfmxJs(YhbfmxSaveReqVO ghHkxxYhbfmx);

    /**
     * 补结算文件生成银行拨付数据
     * @param ghHkxxYhbfmx
     * @return
     */
    public void updateGhyhbfmxBjs(YhbfmxSaveReqVO ghHkxxYhbfmx);

    /**
     * 失败退回重拨数据生成银行拨付数据
     * @param ghHkxxYhbfmx
     * @return
     */
    public void updateGhyhbfmxSbthcb(YhbfmxSaveReqVO ghHkxxYhbfmx);

    /**
     * 生成银行拨付汇总
     * @param ghHkxxYhbfmx
     */
    public void updateGhHkxxYhbfhz(YhbfmxSaveReqVO ghHkxxYhbfmx);
}
package cn.iocoder.yudao.module.lghjft.service.jfcl.hbfhz;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 银行拨付汇总 Service 接口
 *
 * @author 李文军
 */
public interface YhbfhzService {

    /**
     * 创建银行拨付汇总
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createYhbfhz(@Valid YhbfhzSaveReqVO createReqVO);

    /**
     * 更新银行拨付汇总
     *
     * @param updateReqVO 更新信息
     */
    void updateYhbfhz(@Valid YhbfhzSaveReqVO updateReqVO);

    /**
     * 删除银行拨付汇总
     *
     * @param id 编号
     */
    void deleteYhbfhz(Integer id);

    /**
    * 批量删除银行拨付汇总
    *
    * @param ids 编号
    */
    void deleteYhbfhzListByIds(List<Integer> ids);

    /**
     * 获得银行拨付汇总
     *
     * @param id 编号
     * @return 银行拨付汇总
     */
    YhbfhzDO getYhbfhz(Integer id);

    /**
     * 获得银行拨付汇总分页
     *
     * @param pageReqVO 分页查询
     * @return 银行拨付汇总分页
     */
    PageResult<YhbfhzDO> getYhbfhzPage(YhbfhzPageReqVO pageReqVO);

}
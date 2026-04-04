package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhz;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhz.YhbfhzDO;

/**
 * 银行拨付汇总 Service 接口
 *
 * @author 李文军
 */
public interface YhbfhzService {
    /**
     * 查询银行拨付汇总
     *
     * @param bfhzid 银行拨付汇总主键
     * @return 银行拨付汇总
     */
    YhbfhzDO selectYhbfhzByBfhzid(String bfhzid);

    /**
     * 查询银行拨付汇总列表
     *
     * @return 银行拨付汇总集合
     */
//    List<YhbfhzDO> selectYhbfhzList(YhbfhzDO yhbfhz);
    PageResult<YhbfhzDO> selectYhbfhzList(YhbfhzPageReqVO reqVO);

    /**
     * 新增银行拨付汇总
     *
     * @param yhbfhz 银行拨付汇总
     * @return 结果
     */
    int insertYhbfhz(YhbfhzDO yhbfhz);

    /**
     * 修改银行拨付汇总
     *
     * @param yhbfhz 银行拨付汇总
     * @return 结果
     */
    int updateYhbfhz(YhbfhzDO yhbfhz);

    /**
     * 批量删除银行拨付汇总
     *
     * @param bfhzids 需要删除的银行拨付汇总主键集合
     * @return 结果
     */
    int deleteYhbfhzByBfhzids(String[] bfhzids);

    /**
     * 删除银行拨付汇总信息
     *
     * @param bfhzid 银行拨付汇总主键
     * @return 结果
     */
    int deleteYhbfhzByBfhzid(String bfhzid);

    /**
     * 提交银行审核
     *
     * @param yhbfhz 银行拨付汇总
     */
    void updateYhbfhztj(YhbfhzDO yhbfhz);

    /**
     * 支付明细查询
     *
     * @param yhbfhz 银行拨付汇总
     */
    void updateZfmxcx(YhbfhzDO yhbfhz);

}
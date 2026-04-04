package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfhz;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhz.vo.YhbfhzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhz.YhbfhzDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 银行拨付汇总 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhbfhzMapper extends BaseMapperX<YhbfhzDO> {


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
//    IPage<YhbfhzDO> selectYhbfhzList(Page<YhbfhzDO> page, YhbfhzPageReqVO reqVO);
    IPage<YhbfhzDO> selectYhbfhzList(Page<YhbfhzDO> page, @Param("req") YhbfhzPageReqVO reqVO);

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
     * 删除银行拨付汇总
     *
     * @param bfhzid 银行拨付汇总主键
     * @return 结果
     */
    int deleteYhbfhzByBfhzid(String bfhzid);

    /**
     * 批量删除银行拨付汇总
     *
     * @param bfhzids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteYhbfhzByBfhzids(String[] bfhzids);

    /**
     * 写入银行拨付汇总
     *
     * @param yhbfhzs
     * @return
     */
    int insertBatchYhbfhzs(List<YhbfhzDO> yhbfhzs);

    /**
     * 拨付汇总批次号
     *
     * @param dqrq
     * @return
     */
    String selectBfhzpch(String dqrq);


}
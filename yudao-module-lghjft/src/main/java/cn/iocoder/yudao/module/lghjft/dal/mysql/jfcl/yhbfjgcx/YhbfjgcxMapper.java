package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 银行拨付结果查询 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhbfjgcxMapper extends BaseMapperX<YhbfjgcxDO> {

    /**
     * 查询银行拨付结果查询
     */
    YhbfjgcxDO selectYhbfjgcxByBfpch(String bfpch);

    /**
     * 查询银行拨付结果查询列表（分页，XML）
     */
    IPage<YhbfjgcxDO> selectYhbfjgcxList(Page<YhbfjgcxDO> page, @Param("req") YhbfjgcxPageReqVO reqVO);

    /**
     * 新增银行拨付结果查询
     */
    int insertYhbfjgcx(YhbfjgcxDO yhbfjgcx);

    /**
     * 修改银行拨付结果查询
     */
    int updateYhbfjgcx(YhbfjgcxDO yhbfjgcx);

    /**
     * 删除银行拨付结果查询
     */
    int deleteYhbfjgcxByBfpch(String bfpch);

    /**
     * 批量删除银行拨付结果查询
     */
    int deleteYhbfjgcxByBfpchs(String[] bfpchs);

}

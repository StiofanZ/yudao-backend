package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.hkxxbfzhpc;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hkxxbfzhpc.HkxxBfzhpcDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 拨付信息 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface HkxxBfzhpcMapper extends BaseMapperX<HkxxBfzhpcDO> {

    /**
     * 分页查询经费排除列表
     */
    IPage<HkxxBfzhpcRespVO> selectBfjfpcPage(Page<HkxxBfzhpcRespVO> page, @Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 查询账户排除解除
     *
     * @param spuuid 账户排除解除主键
     * @return 账户排除解除
     */
    HkxxBfzhpcRespVO selectByspuuid(String spuuid);

    /**
     * 修改账户排除解除
     *
     * @param bfzhpc 账户排除解除
     * @return 结果
     */
//     int updateGhHkxxBfzhpc(HkxxBfzhpcDO bfzhpc);

    /**
     * 批量插入或更新子表（ON DUPLICATE KEY UPDATE）
     */
    int batchGhHkxxBfzhpc(@Param("list") List<HkxxBfzhpcDO> list);

    /**
     * 更新主表 gh_jf（只更新时间）
     */
    int updateBfjfpc(@Param("spuuid") String spuuid);
}
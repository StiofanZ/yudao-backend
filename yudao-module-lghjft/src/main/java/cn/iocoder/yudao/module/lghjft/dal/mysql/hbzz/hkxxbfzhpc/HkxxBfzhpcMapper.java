package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.hkxxbfzhpc;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo.HkxxBfzhpcResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxxbfzhpc.HkxxBfzhpcDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拨付信息 Mapper
 */
@Mapper
public interface HkxxBfzhpcMapper extends BaseMapperX<HkxxBfzhpcDO> {

    /**
     * 账户排除分页查询（from bfzhpc，与 v1 selectBfzhpcList 一致）
     */
    IPage<HkxxBfzhpcResVO> selectBfzhpcPage(Page<HkxxBfzhpcResVO> page, @Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 单位排除分页查询（from bfzhpc，与 v1 selectBfdwpcList 一致）
     */
    IPage<HkxxBfzhpcResVO> selectBfdwPage(Page<HkxxBfzhpcResVO> page, @Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 经费排除分页查询（与 v1 selectBfjfpcList 一致）
     */
    IPage<HkxxBfzhpcResVO> selectBfjfPage(Page<HkxxBfzhpcResVO> page, @Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 按 spuuid 查经费排除详情（级联子表）
     */
    HkxxBfzhpcResVO selectByspuuid(String spuuid);

    /**
     * 按基层工会账号查账户排除详情（级联子表，from bfzhpc）
     */
    HkxxBfzhpcResVO selectByJcghzh(@Param("jcghzh") String jcghzh);

    /**
     * 按登记序号查单位排除详情（级联子表，from bfzhpc）
     */
    HkxxBfzhpcResVO selectBfdwByDjxh(@Param("djxh") String djxh);

    /**
     * 批量插入或更新子表（on duplicate key update）
     */
    int batchGhHkxxBfzhpc(@Param("list") List<HkxxBfzhpcDO> list);

    /**
     * 更新经费排除（gh_jf 表）
     */
    int updateBfjfpc(@Param("spuuid") String spuuid);

    /**
     * 更新账户排除（gh_hj 表，与 v1 updateBfzhpc 一致）
     */
    int updateBfzhpcByJcghzh(@Param("jcghzh") String jcghzh);

    /**
     * 更新单位排除（gh_hj 表，与 v1 updateBfdwpc 一致）
     */
    int updateBfdwpcByDjxh(@Param("djxh") String djxh);

    /**
     * 账户排除导出列表（不分页）
     */
    List<HkxxBfzhpcResVO> selectBfzhpcList(@Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 单位排除导出列表（不分页）
     */
    List<HkxxBfzhpcResVO> selectBfdwList(@Param("query") HkxxBfzhpcPageReqVO query);

    /**
     * 经费排除导出列表（不分页）
     */
    List<HkxxBfzhpcResVO> selectBfjfList(@Param("query") HkxxBfzhpcPageReqVO query);
}

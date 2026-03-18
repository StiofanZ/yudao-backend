package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YhbfmxMapper {

    // ==================== 基础 CURD ====================
    YhbfmxDO selectById(Integer id);

    int insert(YhbfmxDO entity);

    int updateById(YhbfmxDO entity);

    int deleteById(Integer id);

    int deleteByIds(@Param("ids") List<Integer> ids);

    // ==================== 分页 & 列表 ====================
    IPage<YhbfmxDO> selectPage(Page<YhbfmxDO> page, @Param("req") YhbfmxPageReqVO reqVO);

    // ==================== 业务方法 ====================
    List<YhbfmxDO> getGhyhbfmxJs(@Param("hkpch") String hkpch);

    List<YhbfmxDO> getGhyhbfmxBjs(@Param("hkpch") String hkpch);

    List<YhbfmxDO> getGhyhbfmxSbthcb(YhbfmxPageReqVO reqVO);

    List<YhbfmxDO> getGhHkxxYhbfmx(YhbfmxPageReqVO reqVO);

    int updateYxbjByHkpch(@Param("hkpch") String hkpch, @Param("yxbj") String yxbj);

    int insertBatch(@Param("list") List<YhbfmxDO> list);

    int updateBatchGhHkxxYhbfmx(List<YhbfmxDO> yhbfmxs);

    List<YhbfmxDO> selectListByBfidStr(String[] bfids);

    // ==================== 从老系统补齐的 4 个方法 ====================
//    YhbfmxDO selectGhHkxxYhbfmxByBfid(String bfid);
//
//    int insertBatchGhyhbfmxJs(@Param("list") List<YhbfmxDO> list);
//
//    int updateYxbjByGhHkxxYhbfmx(YhbfmxDO ghHkxxYhbfmx);
//
//    List<YhbfmxDO> selectGhHkxxYhbfmxList(YhbfmxDO ghHkxxYhbfmx);

}
package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.schbwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface SchbwjMapper extends BaseMapperX<JfclSchbwjDO> {

    default PageResult<JfclSchbwjDO> selectPage(SchbwjPageReqVO reqVO) {
        QueryWrapper<JfclSchbwjDO> wrapper = new QueryWrapper<>();
        // v1: yxbj='Y'
        wrapper.eq("YXBJ", "Y");
        // v1 dynamic conditions
        if (reqVO.getHkpch() != null && !reqVO.getHkpch().isEmpty()) {
            wrapper.eq("HKPCH", reqVO.getHkpch());
        }
        if (reqVO.getBeginHkpch() != null && !reqVO.getBeginHkpch().isEmpty()
                && reqVO.getEndHkpch() != null && !reqVO.getEndHkpch().isEmpty()) {
            wrapper.apply("MID(HKPCH,1,8) between {0} and {1}", reqVO.getBeginHkpch(), reqVO.getEndHkpch());
        }
        wrapper.orderByDesc("HKXX_ID");
        return selectPage(reqVO, wrapper);
    }

    // 生成划拨数据相关 XML 映射方法
    List<Map<String, Object>> selectGhjfhb(@Param("jsrqStart") String jsrqStart, @Param("jsrqEnd") String jsrqEnd);

    List<Map<String, Object>> getSchbwjList(@Param("jsrqStart") String jsrqStart, @Param("jsrqEnd") String jsrqEnd);

    String selectHkpch(@Param("dqrq") String dqrq);

    void insertBatchGhHkxx(@Param("list") List<Map<String, Object>> list);

    void updateGhjfhb(@Param("list") List<Map<String, Object>> list);

    void updateBatchQsjshkrj(@Param("list") List<Map<String, Object>> list);

    void deleteGhHkxxByHkpch(@Param("hkpch") String hkpch);

    List<Map<String, Object>> selectQsjshkrjList(@Param("list") List<Date> list);
}

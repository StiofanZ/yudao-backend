package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfhzjkjl;

import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhzjkjl.YhbfhzjkjlDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YhbfhzjkjlMapper {

    YhbfhzjkjlDO selectYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

    IPage<YhbfhzjkjlDO> selectYhbfhzJkjlList(Page<YhbfhzjkjlDO> page, @Param("req") YhbfhzjkjlPageReqVO reqVO);

//    List<YhbfhzjkjlDO> selectYhbfhzJkjlList(YhbfhzjkjlDO yhbfhzjkjlDO);

    int insertYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO);

    int updateYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO);

    int deleteYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

    int deleteYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds);

}

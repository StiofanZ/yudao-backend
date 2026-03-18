package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.GhHkxxYhbfhzJkjl;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhHkxxYhbfhzJkjlMapper {

    GhHkxxYhbfhzJkjl selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);
    IPage<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(Page<GhHkxxYhbfhzJkjl> page, @Param("req") GhHkxxYhbfhzJkjlPageReqVO reqVO);

//    List<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

    int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds);

}
package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.wjgl;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.wjgl.WjglDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WjglMapper extends BaseMapperX<WjglDO> {
    IPage<WjglResVO> selectFilePage(Page<WjglResVO> page, @Param("query") WjglPageReqVO query);

    WjglResVO selectFileByFileid(@Param("fileid") Long fileid);

    List<WjglResVO> selectFileList(@Param("query") WjglPageReqVO query);
}

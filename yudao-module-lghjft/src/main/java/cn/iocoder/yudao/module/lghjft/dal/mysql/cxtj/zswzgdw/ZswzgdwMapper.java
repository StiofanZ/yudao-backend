package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwQrDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZswzgdwMapper extends BaseMapperX<ZswzgdwDO> {

    /**
     * v1: selectZswzgdwList — paginated list with LEFT JOIN, NO cascade
     */
    List<ZswzgdwDO> selectZswzgdwList(ZswzgdwPageReqVO reqVO);

    /**
     * v1: selectZswzgdwByDjxh — single WITH cascade (zswzgdwQrList)
     */
    ZswzgdwDO selectZswzgdwByDjxh(@Param("djxh") String djxh);

    /**
     * v1: insertZswzgdw
     */
    int insertZswzgdw(ZswzgdwDO zswzgdw);

    /**
     * v1: deleteZswzgdwByDjxh
     */
    int deleteZswzgdwByDjxh(@Param("djxh") String djxh);

    /**
     * v1: deleteZswzgdwByDjxhs — batch delete main
     */
    int deleteZswzgdwByDjxhs(@Param("array") String[] djxhs);

    /**
     * v1: deleteZswzgdwQrByDJXHs — batch delete qr
     */
    int deleteZswzgdwQrByDjxhs(@Param("array") String[] djxhs);

    /**
     * v1: deleteZswzgdwQrByDJXH — delete qr by single djxh
     */
    int deleteZswzgdwQrByDjxh(@Param("djxh") String djxh);

    /**
     * v1: batchZswzgdwQr — ON DUPLICATE KEY UPDATE
     */
    int batchZswzgdwQr(@Param("list") List<ZswzgdwQrDO> zswzgdwQrList);
}

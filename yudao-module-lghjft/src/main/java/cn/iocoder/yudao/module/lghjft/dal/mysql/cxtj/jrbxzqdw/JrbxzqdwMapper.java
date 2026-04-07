package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JrbxzqdwMapper extends BaseMapperX<JrbxzqdwDO> {

    /**
     * v1: selectJrbxzqdwList — 3-table JOIN (jrbxzqdw + gh_hj + jrbxzqdwjfqk1 + jrbxzqdwjfqk)
     * 完整 WHERE + ORDER BY b.gzze desc，由 XML Mapper 实现。
     */
    List<JrbxzqdwDO> selectJrbxzqdwList(JrbxzqdwPageReqVO reqVO);

    /**
     * v1: selectJrbxzqdwById — 3-table JOIN 获取单条记录
     */
    JrbxzqdwDO selectJrbxzqdwById(String id);

    /**
     * v1: updateJrbxzqdw — RESTRICTED update（仅 bz, hsjg, createBy, createTime, updateBy, updateTime）
     */
    int updateJrbxzqdw(JrbxzqdwDO jrbxzqdw);

    /**
     * v1: insertJrbxzqdw
     */
    int insertJrbxzqdw(JrbxzqdwDO jrbxzqdw);

    /**
     * v1: deleteJrbxzqdwById
     */
    int deleteJrbxzqdwById(String id);

    /**
     * v1: deleteJrbxzqdwByIds — batch delete
     */
    int deleteJrbxzqdwByIds(String[] ids);
}

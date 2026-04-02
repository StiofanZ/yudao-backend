package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 树形字典 Mapper
 *
 * @author zhaofan
 */
@Mapper
public interface SysTreeDictMapper extends BaseMapperX<SysTreeDictDO> {

    default PageResult<SysTreeDictDO> selectPage(SysTreeDictPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysTreeDictDO>()
                .eq(SysTreeDictDO::getDelFlag, "0")
                .likeIfPresent(SysTreeDictDO::getName, reqVO.getName())
                .eqIfPresent(SysTreeDictDO::getStruType, reqVO.getStruType())
                .orderByDesc(SysTreeDictDO::getCreateTime));
    }

    default List<SysTreeDictDO> selectList() {
        return selectList(new LambdaQueryWrapperX<SysTreeDictDO>()
                .eq(SysTreeDictDO::getDelFlag, "0")
                .orderByDesc(SysTreeDictDO::getCreateTime));
    }

}

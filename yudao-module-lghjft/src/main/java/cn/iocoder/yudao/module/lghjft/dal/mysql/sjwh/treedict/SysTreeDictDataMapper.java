package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDataDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 树形字典数据 Mapper
 *
 * @author zhaofan
 */
@Mapper
public interface SysTreeDictDataMapper extends BaseMapperX<SysTreeDictDataDO> {

    default PageResult<SysTreeDictDataDO> selectPage(SysTreeDictDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysTreeDictDataDO>()
                .eq(SysTreeDictDataDO::getDelFlag, "0")
                .eqIfPresent(SysTreeDictDataDO::getTreeDict, reqVO.getTreeDict())
                .likeIfPresent(SysTreeDictDataDO::getLabel, reqVO.getLabel())
                .eqIfPresent(SysTreeDictDataDO::getIsLeaf, reqVO.getIsLeaf())
                .orderByAsc(SysTreeDictDataDO::getOrderNum));
    }

    default List<SysTreeDictDataDO> selectListAll() {
        return selectList(new LambdaQueryWrapperX<SysTreeDictDataDO>()
                .eq(SysTreeDictDataDO::getDelFlag, "0")
                .orderByAsc(SysTreeDictDataDO::getOrderNum));
    }

}

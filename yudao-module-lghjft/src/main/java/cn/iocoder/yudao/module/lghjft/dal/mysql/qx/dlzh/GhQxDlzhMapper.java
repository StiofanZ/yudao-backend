package cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhQxDlzhMapper extends BaseMapperX<GhQxDlzhDO> {

    default PageResult<GhQxDlzhDO> selectPage(DlzhReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhQxDlzhDO>()
                .likeIfPresent(GhQxDlzhDO::getYhzh, reqVO.getYhzh())
                .likeIfPresent(GhQxDlzhDO::getYhxm, reqVO.getYhxm())
                .likeIfPresent(GhQxDlzhDO::getLxdh, reqVO.getLxdh())
                .likeIfPresent(GhQxDlzhDO::getYhyx, reqVO.getYhyx())
                .likeIfPresent(GhQxDlzhDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(GhQxDlzhDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GhQxDlzhDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GhQxDlzhDO::getId));
    }

    default GhQxDlzhDO selectByYhzh(String yhzh) {
        return selectOne(GhQxDlzhDO::getYhzh, yhzh);
    }

    default GhQxDlzhDO selectByLxdh(String lxdh) {
        return selectOne(GhQxDlzhDO::getLxdh, lxdh);
    }

    default GhQxDlzhDO selectByYhyx(String yhyx) {
        return selectOne(GhQxDlzhDO::getYhyx, yhyx);
    }

    default GhQxDlzhDO selectByShxydm(String shxydm) {
        return selectOne(GhQxDlzhDO::getShxydm, shxydm);
    }

}

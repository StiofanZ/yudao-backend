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
                .eqIfPresent(GhQxDlzhDO::getYhzh, reqVO.getYhzh())
                .eqIfPresent(GhQxDlzhDO::getYhxm, reqVO.getYhxm())
                .eqIfPresent(GhQxDlzhDO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(GhQxDlzhDO::getYhyx, reqVO.getYhyx())
                .eqIfPresent(GhQxDlzhDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(GhQxDlzhDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GhQxDlzhDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GhQxDlzhDO::getId));
    }

    default GhQxDlzhDO selectOne(DlzhReqVO reqVO) {
        return selectOne(new LambdaQueryWrapperX<GhQxDlzhDO>()
                .eq(GhQxDlzhDO::getYhzh, reqVO.getYhzh())
                .or()
                .eq(GhQxDlzhDO::getYhyx, reqVO.getYhyx())
                .or()
                .eq(GhQxDlzhDO::getShxydm, reqVO.getShxydm())
                .or()
                .eq(GhQxDlzhDO::getLxdh, reqVO.getLxdh()));
    }

    default GhQxDlzhDO selectOne(String lxdh, String yhzh, String shxydm, String yhyx) {
        return selectOne(new LambdaQueryWrapperX<GhQxDlzhDO>()
                .eq(GhQxDlzhDO::getDeleted, 0)
                .and(w -> w.eq(GhQxDlzhDO::getLxdh, lxdh)
                        .or()
                        .eq(GhQxDlzhDO::getYhzh, yhzh)
                        .or()
                        .eq(GhQxDlzhDO::getShxydm, shxydm)
                        .or()
                        .eq(GhQxDlzhDO::getYhyx, yhyx))
        );
    }
}

package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bszn;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo.BsznReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bszn.BsznDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 办事指南 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BsznMapper extends BaseMapperX<BsznDO> {

    /**
     * 查询办事指南分页列表（带排名）
     *
     * @param page        分页参数
     * @param reqVO       查询条件
     * @param loginDeptId 登录用户部门ID (可为 null)
     * @param ancestorIds 上级部门ID列表
     * @return 分页结果
     */
    IPage<BsznDO> selectPageWithRank(IPage<BsznDO> page,
                                     @Param("reqVO") BsznReqVO reqVO,
                                     @Param("loginDeptId") Long loginDeptId,
                                     @Param("ancestorIds") List<Long> ancestorIds);

}

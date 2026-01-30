package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.cjwt;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.cjwt.CjwtDO;
import org.apache.ibatis.annotations.Mapper;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.cjwt.vo.CjwtReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 常见问题 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CjwtMapper extends BaseMapperX<CjwtDO> {

    /**
     * 查询常见问题分页列表（带排名）
     *
     * @param page 分页参数
     * @param reqVO 查询条件
     * @param loginDeptId 登录用户部门ID (可为 null)
     * @param ancestorIds 上级部门ID列表
     * @return 分页结果
     */
    IPage<CjwtDO> selectPageWithRank(IPage<CjwtDO> page, 
                                     @Param("reqVO") CjwtReqVO reqVO, 
                                     @Param("loginDeptId") Long loginDeptId, 
                                     @Param("ancestorIds") List<Long> ancestorIds);

}

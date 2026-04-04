package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.hkxx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxx.vo.HkxxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxx.HkxxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 基层经费到账对象 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface HkxxMapper extends BaseMapperX<HkxxDO> {
    Integer updateJcjfzz(HkxxDO hkxxDO);

    //    添加经费到账
    Integer updateJcjfdz(HkxxSaveReqVO updateReqVO);

    //添加经费到账信息
    Integer insertJcjfdz(HkxxSaveReqVO updateReqVO);

    //    确认经费到账信息
    HkxxDO selectJcjfzzByHkxxId(Integer hkxxId);

    //    查询经费到账列表
    IPage<HkxxResVO> selectJcjfzzList(Page<HkxxResVO> page, @Param("query") HkxxPageReqVO query);

    HkxxSummaryResVO selectJcjfzzSummary(@Param("query") HkxxPageReqVO query);

}

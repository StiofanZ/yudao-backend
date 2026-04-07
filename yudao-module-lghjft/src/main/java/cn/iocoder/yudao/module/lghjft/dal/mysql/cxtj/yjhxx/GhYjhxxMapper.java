package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.yjhxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx.GhYjhxxDO;
import org.apache.ibatis.annotations.Mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Mapper
public interface GhYjhxxMapper extends BaseMapperX<GhYjhxxDO> {

    default PageResult<GhYjhxxDO> selectPage(GhYjhxxPageReqVO reqVO) {
        QueryWrapper<GhYjhxxDO> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(reqVO.getNsrmc()), "nsrmc", reqVO.getNsrmc())
               .eq(StrUtil.isNotBlank(reqVO.getShxydm()), "shxydm", reqVO.getShxydm())
               .eq(StrUtil.isNotBlank(reqVO.getNsrsbh()), "nsrsbh", reqVO.getNsrsbh())
               .eq(StrUtil.isNotBlank(reqVO.getYxbj()), "yxbj", reqVO.getYxbj())
               .eq(StrUtil.isNotBlank(reqVO.getCreateBy()), "create_by", reqVO.getCreateBy())
               .apply(StrUtil.isNotBlank(reqVO.getCreateTime()),
                      "date_format(create_time, '%Y-%m-%d') = {0}", reqVO.getCreateTime())
               .eq(StrUtil.isNotBlank(reqVO.getUpdateBy()), "update_by", reqVO.getUpdateBy())
               .apply(StrUtil.isNotBlank(reqVO.getUpdateTime()),
                      "date_format(update_time, '%Y-%m-%d') = {0}", reqVO.getUpdateTime())
               .orderByDesc("jhxx_id");
        return selectPage(reqVO, wrapper);
    }
}

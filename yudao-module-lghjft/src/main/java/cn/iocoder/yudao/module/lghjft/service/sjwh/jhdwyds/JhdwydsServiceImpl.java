package cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds.JhdwydsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JHDWYDS_NOT_EXISTS;

/**
 * 应代收单位 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class JhdwydsServiceImpl implements JhdwydsService {

    @Resource
    private JhdwydsMapper jhdwydsMapper;

    @Override
    public Integer createJhdwyds(JhdwydsSaveReqVO createReqVO) {
        // 插入
        JhdwydsDO jhdwyds = BeanUtils.toBean(createReqVO, JhdwydsDO.class);
        jhdwydsMapper.insert(jhdwyds);

        // 返回
        return jhdwyds.getJhdwId();
    }

    @Override
    public void updateJhdwyds(JhdwydsSaveReqVO updateReqVO) {
        // 校验存在
        validateJhdwydsExists(updateReqVO.getJhdwId());
        // 更新
        JhdwydsDO updateObj = BeanUtils.toBean(updateReqVO, JhdwydsDO.class);
        jhdwydsMapper.updateById(updateObj);
    }

    @Override
    public void deleteJhdwyds(Integer id) {
        // 校验存在
        validateJhdwydsExists(id);
        // 删除
        jhdwydsMapper.deleteById(id);
    }

    @Override
        public void deleteJhdwydsListByIds(List<Integer> ids) {
        // 删除
        jhdwydsMapper.deleteByIds(ids);
        }


    private void validateJhdwydsExists(Integer id) {
        if (jhdwydsMapper.selectById(id) == null) {
            throw exception(JHDWYDS_NOT_EXISTS);
        }
    }

    @Override
    public JhdwydsDO getJhdwyds(Integer id) {
        return jhdwydsMapper.selectById(id);
    }

    @Override
    public PageResult<JhdwydsDO> getJhdwydsPage(JhdwydsPageReqVO pageReqVO) {
        return jhdwydsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<JhdwydsDO> getJhdwydsList(JhdwydsReqVO reqVO) {
        return jhdwydsMapper.selectList(reqVO);
    }

}
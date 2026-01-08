package cn.iocoder.yudao.module.lghjft.service.sjwh.swjg;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swjg.SwjgDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.swjg.SwjgMapper;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

/**
 * 税务机关 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class SwjgServiceImpl implements SwjgService {

    @Resource
    private SwjgMapper swjgMapper;


    @Override
    public void updateSwjg(SwjgSaveReqVO updateReqVO) {
        String swjgDm = updateReqVO.getSwjgDm();
        String sjswjgDm = updateReqVO.getSjswjgDm();

        // 1. 校验存在
        if (swjgDm == null || swjgMapper.selectById(swjgDm) == null) {
            throw new ServiceException("税务机关不存在");
        }

        // 2. 校验上级税务机关代码的有效性
        if (sjswjgDm != null && !sjswjgDm.isEmpty() && !"0".equals(sjswjgDm)) {
            // 2.1 不能设置自己为父税务机关
            if (Objects.equals(swjgDm, sjswjgDm)) {
                throw new ServiceException("不能设置自己为上级税务机关");
            }
            // 2.2 父税务机关不存在
            if (swjgMapper.selectById(sjswjgDm) == null) {
                throw new ServiceException("上级税务机关不存在");
            }
            // 2.3 校验环路（简化版）
            SwjgDO parent = swjgMapper.selectById(sjswjgDm);
            while (parent != null && parent.getSjswjgDm() != null
                    && !parent.getSjswjgDm().isEmpty()
                    && !"0".equals(parent.getSjswjgDm())) {
                if (Objects.equals(swjgDm, parent.getSjswjgDm())) {
                    throw new ServiceException("上级税务机关不能是自己的下级");
                }
                parent = swjgMapper.selectById(parent.getSjswjgDm());
            }
        }

        // 3. 校验税务机关名称的唯一性
        if (sjswjgDm != null && updateReqVO.getSwjgmc() != null) {
            LambdaQueryWrapper<SwjgDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SwjgDO::getSjswjgDm, sjswjgDm)
                    .eq(SwjgDO::getSwjgmc, updateReqVO.getSwjgmc());
            SwjgDO exist = swjgMapper.selectOne(wrapper);
            if (exist != null && !Objects.equals(exist.getSwjgDm(), swjgDm)) {
                throw new ServiceException("同一上级下税务机关名称不能重复");
            }
        }

        // 4. 更新
        SwjgDO updateObj = BeanUtils.toBean(updateReqVO, SwjgDO.class);
        swjgMapper.updateById(updateObj);
    }

    @Override
    public List<SwjgDO> getSwjgList(SwjgListReqVO listReqVO) {
        return swjgMapper.selectList(listReqVO);
    }
    @Override
    public SwjgDO getSwjg(String id) {
        return swjgMapper.selectById(id);
    }

}
package cn.iocoder.yudao.module.lghjft.service.nrgl.bbfb;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bbfb.BbfbMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 版本发布 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BbfbServiceImpl implements BbfbService {

    @Resource
    private BbfbMapper bbfbMapper;

    @Override
    public Long createBbfb(BbfbCreateReqVO createReqVO) {
        BbfbDO bbfb = BeanUtils.toBean(createReqVO, BbfbDO.class);
        bbfb.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bbfb.setStatus(0); // 默认为草稿
        bbfbMapper.insert(bbfb);
        return bbfb.getId();
    }

    @Override
    public void updateBbfb(BbfbUpdateReqVO updateReqVO) {
        validateBbfbExists(updateReqVO.getId());
        BbfbDO updateObj = BeanUtils.toBean(updateReqVO, BbfbDO.class);
        bbfbMapper.updateById(updateObj);
    }

    @Override
    public void deleteBbfb(Long id) {
        validateBbfbExists(id);
        bbfbMapper.deleteById(id);
    }

    private void validateBbfbExists(Long id) {
        if (bbfbMapper.selectById(id) == null) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(404, "版本发布不存在"));
        }
    }

    @Override
    public BbfbDO getBbfb(Long id) {
        return bbfbMapper.selectById(id);
    }

    @Override
    public List<BbfbDO> getBbfbList(BbfbListReqVO listReqVO) {
        return bbfbMapper.selectList(listReqVO);
    }

    @Override
    public void publish(Long id) {
        validateBbfbExists(id);
        BbfbDO updateObj = new BbfbDO();
        updateObj.setId(id);
        updateObj.setStatus(1); // 已发布
        updateObj.setFbsj(LocalDate.now());
        bbfbMapper.updateById(updateObj);
    }

    @Override
    public List<BbfbDO> getPublicBbfbList() {
        // 查询已发布(1)的内容
        return bbfbMapper.selectList(new LambdaQueryWrapper<BbfbDO>()
                .eq(BbfbDO::getStatus, 1)
                .orderByDesc(BbfbDO::getFbsj)
                .orderByDesc(BbfbDO::getId));
    }

}

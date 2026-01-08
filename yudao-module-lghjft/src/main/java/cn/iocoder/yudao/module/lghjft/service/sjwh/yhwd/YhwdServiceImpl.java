package cn.iocoder.yudao.module.lghjft.service.sjwh.yhwd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd.YhwdDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.yhwd.YhwdMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.YHWD_NOT_EXISTS;

/**
 * 银行网点 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class YhwdServiceImpl implements YhwdService {

    @Resource
    private YhwdMapper yhwdMapper;

    @Override
    public Long createYhwd(YhwdSaveReqVO createReqVO) {
        // 插入
        YhwdDO yhwd = BeanUtils.toBean(createReqVO, YhwdDO.class);
        yhwdMapper.insert(yhwd);

        // 返回
        return yhwd.getYhhbId();
    }

    @Override
    public void updateYhwd(YhwdSaveReqVO updateReqVO) {
        // 校验存在
        validateYhwdExists(updateReqVO.getYhhbId());
        // 更新
        YhwdDO updateObj = BeanUtils.toBean(updateReqVO, YhwdDO.class);
        yhwdMapper.updateById(updateObj);
    }

    @Override
    public void deleteYhwd(Long id) {
        // 校验存在
        validateYhwdExists(id);
        // 删除
        yhwdMapper.deleteById(id);
    }

    @Override
        public void deleteYhwdListByIds(List<Long> ids) {
        // 删除
        yhwdMapper.deleteByIds(ids);
        }


    private void validateYhwdExists(Long id) {
        if (yhwdMapper.selectById(id) == null) {
            throw exception(YHWD_NOT_EXISTS);
        }
    }

    @Override
    public YhwdDO getYhwd(Long id) {
        return yhwdMapper.selectById(id);
    }

    @Override
    public PageResult<YhwdDO> getYhwdPage(YhwdPageReqVO pageReqVO) {
        return yhwdMapper.selectPage(pageReqVO);
    }

}
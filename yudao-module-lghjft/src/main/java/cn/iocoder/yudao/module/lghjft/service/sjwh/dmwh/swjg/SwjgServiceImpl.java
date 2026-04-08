package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.swjg;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.swjg.SwjgDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.swjg.SwjgMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SWJG_NOT_EXISTS;

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
    @Transactional(rollbackFor = Exception.class)
    public String createSwjg(SwjgSaveReqVO createReqVO) {
        SwjgDO swjg = BeanUtils.toBean(createReqVO, SwjgDO.class);
        swjgMapper.insert(swjg);
        return swjg.getSwjgDm();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSwjg(SwjgSaveReqVO updateReqVO) {
        validateSwjgExists(updateReqVO.getSwjgDm());
        SwjgDO updateObj = BeanUtils.toBean(updateReqVO, SwjgDO.class);
        swjgMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSwjgByIds(List<String> swjgDms) {
        swjgMapper.deleteByIds(swjgDms);
    }

    @Override
    public SwjgDO getSwjg(String swjgDm) {
        return swjgMapper.selectById(swjgDm);
    }

    @Override
    public List<SwjgDO> getSwjgList(SwjgListReqVO listReqVO) {
        return swjgMapper.selectList(listReqVO);
    }

    private void validateSwjgExists(String swjgDm) {
        if (swjgDm == null || swjgMapper.selectById(swjgDm) == null) {
            throw exception(SWJG_NOT_EXISTS);
        }
    }

}

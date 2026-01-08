package cn.iocoder.yudao.module.lghjft.service.sjwh.skgk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.skgk.SkgkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.skgk.SkgkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SKGK_NOT_EXISTS;

/**
 * 收款国库 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class SkgkServiceImpl implements SkgkService {

    @Resource
    private SkgkMapper skgkMapper;

    @Override
    public Integer createSkgk(SkgkSaveReqVO createReqVO) {
        // 插入
        SkgkDO skgk = BeanUtils.toBean(createReqVO, SkgkDO.class);
        skgkMapper.insert(skgk);

        // 返回
        return skgk.getGkId();
    }

    @Override
    public void updateSkgk(SkgkSaveReqVO updateReqVO) {
        // 校验存在
        validateSkgkExists(updateReqVO.getGkId());
        // 更新
        SkgkDO updateObj = BeanUtils.toBean(updateReqVO, SkgkDO.class);
        skgkMapper.updateById(updateObj);
    }

    @Override
    public void deleteSkgk(Integer id) {
        // 校验存在
        validateSkgkExists(id);
        // 删除
        skgkMapper.deleteById(id);
    }

    @Override
        public void deleteSkgkListByIds(List<Integer> ids) {
        // 删除
        skgkMapper.deleteByIds(ids);
        }


    private void validateSkgkExists(Integer id) {
        if (skgkMapper.selectById(id) == null) {
            throw exception(SKGK_NOT_EXISTS);
        }
    }

    @Override
    public SkgkDO getSkgk(Integer id) {
        return skgkMapper.selectById(id);
    }

    @Override
    public PageResult<SkgkDO> getSkgkPage(SkgkPageReqVO pageReqVO) {
        return skgkMapper.selectPage(pageReqVO);
    }

}
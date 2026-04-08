package cn.iocoder.yudao.module.lghjft.service.sjwh.rws;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.rws.RwsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.rws.RwsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.RWS_NOT_EXISTS;

/**
 * 年度任务 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class RwsServiceImpl implements RwsService {

    @Resource
    private RwsMapper rwsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRws(RwsSaveReqVO createReqVO) {
        // 插入
        RwsDO rws = BeanUtils.toBean(createReqVO, RwsDO.class);
        rwsMapper.insert(rws);

        // 返回
        return rws.getRwid();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRws(RwsSaveReqVO updateReqVO) {
        // 校验存在
        validateRwsExists(updateReqVO.getRwid());
        // 更新
        RwsDO updateObj = BeanUtils.toBean(updateReqVO, RwsDO.class);
        rwsMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRws(Long id) {
        // 校验存在
        validateRwsExists(id);
        // 删除
        rwsMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRwsListByIds(List<Long> ids) {
        // 删除
        rwsMapper.deleteByIds(ids);
    }

    private void validateRwsExists(Long id) {
        if (rwsMapper.selectById(id) == null) {
            throw exception(RWS_NOT_EXISTS);
        }
    }

    @Override
    public RwsDO getRws(Long id) {
        return rwsMapper.selectById(id);
    }

    @Override
    public PageResult<RwsDO> getRwsPage(RwsPageReqVO pageReqVO) {
        return rwsMapper.selectPage(pageReqVO);
    }

}

package cn.iocoder.yudao.module.lghjft.service.rws;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.rws.RwsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.rws.RwsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

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
    public Integer createRws(RwsSaveReqVO createReqVO) {
        // 插入
        RwsDO rws = BeanUtils.toBean(createReqVO, RwsDO.class);
        rwsMapper.insert(rws);

        // 返回
        return rws.getRwid();
    }

    @Override
    public void updateRws(RwsSaveReqVO updateReqVO) {
        // 校验存在
        validateRwsExists(updateReqVO.getRwid());
        // 更新
        RwsDO updateObj = BeanUtils.toBean(updateReqVO, RwsDO.class);
        rwsMapper.updateById(updateObj);
    }

    @Override
    public void deleteRws(Integer id) {
        // 校验存在
        validateRwsExists(id);
        // 删除
        rwsMapper.deleteById(id);
    }

    @Override
        public void deleteRwsListByIds(List<Integer> ids) {
        // 删除
        rwsMapper.deleteByIds(ids);
        }


    private void validateRwsExists(Integer id) {
        if (rwsMapper.selectById(id) == null) {
            throw exception(RWS_NOT_EXISTS);
        }
    }

    @Override
    public RwsDO getRws(Integer id) {
        return rwsMapper.selectById(id);
    }

    @Override
    public PageResult<RwsDO> getRwsPage(RwsPageReqVO pageReqVO) {
        return rwsMapper.selectPage(pageReqVO);
    }

}
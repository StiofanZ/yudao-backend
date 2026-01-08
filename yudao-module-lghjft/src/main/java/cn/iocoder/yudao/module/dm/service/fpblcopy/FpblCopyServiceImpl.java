package cn.iocoder.yudao.module.dm.service.fpblcopy;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.dm.controller.admin.fpblcopy.vo.*;
import cn.iocoder.yudao.module.dm.dal.dataobject.fpblcopy.FpblCopyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.dm.dal.mysql.fpblcopy.FpblCopyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.dm.enums.ErrorCodeConstants.*;

/**
 * 分配比例 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class FpblCopyServiceImpl implements FpblCopyService {

    @Resource
    private FpblCopyMapper fpblCopyMapper;

    @Override
    public Integer createFpblCopy(FpblCopySaveReqVO createReqVO) {
        // 插入
        FpblCopyDO fpblCopy = BeanUtils.toBean(createReqVO, FpblCopyDO.class);
        fpblCopyMapper.insert(fpblCopy);

        // 返回
        return fpblCopy.getBlId();
    }

    @Override
    public void updateFpblCopy(FpblCopySaveReqVO updateReqVO) {
        // 校验存在
        validateFpblCopyExists(updateReqVO.getBlId());
        // 更新
        FpblCopyDO updateObj = BeanUtils.toBean(updateReqVO, FpblCopyDO.class);
        fpblCopyMapper.updateById(updateObj);
    }

    @Override
    public void deleteFpblCopy(Integer id) {
        // 校验存在
        validateFpblCopyExists(id);
        // 删除
        fpblCopyMapper.deleteById(id);
    }

    @Override
        public void deleteFpblCopyListByIds(List<Integer> ids) {
        // 删除
        fpblCopyMapper.deleteByIds(ids);
        }


    private void validateFpblCopyExists(Integer id) {
        if (fpblCopyMapper.selectById(id) == null) {
            throw exception(FPBL_COPY_NOT_EXISTS);
        }
    }

    @Override
    public FpblCopyDO getFpblCopy(Integer id) {
        return fpblCopyMapper.selectById(id);
    }

    @Override
    public PageResult<FpblCopyDO> getFpblCopyPage(FpblCopyPageReqVO pageReqVO) {
        return fpblCopyMapper.selectPage(pageReqVO);
    }

}
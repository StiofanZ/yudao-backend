package cn.iocoder.yudao.module.lghjft.service.jfcl.hbfhz;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.hbfhz.YhbfhzMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 * 银行拨付汇总 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class YhbfhzServiceImpl implements YhbfhzService {

    @Resource
    private YhbfhzMapper yhbfhzMapper;

    @Override
    public Integer createYhbfhz(YhbfhzSaveReqVO createReqVO) {
        // 插入
        YhbfhzDO yhbfhz = BeanUtils.toBean(createReqVO, YhbfhzDO.class);
        yhbfhzMapper.insert(yhbfhz);

        // 返回
        return yhbfhz.getBfhzid();
    }

    @Override
    public void updateYhbfhz(YhbfhzSaveReqVO updateReqVO) {
        // 校验存在
        validateYhbfhzExists(updateReqVO.getBfhzid());
        // 更新
        YhbfhzDO updateObj = BeanUtils.toBean(updateReqVO, YhbfhzDO.class);
        yhbfhzMapper.updateById(updateObj);
    }

    @Override
    public void deleteYhbfhz(Integer id) {
        // 校验存在
        validateYhbfhzExists(id);
        // 删除
        yhbfhzMapper.deleteById(id);
    }

    @Override
        public void deleteYhbfhzListByIds(List<Integer> ids) {
        // 删除
        yhbfhzMapper.deleteByIds(ids);
        }


    private void validateYhbfhzExists(Integer id) {
        if (yhbfhzMapper.selectById(id) == null) {
            throw exception(YHBFHZ_NOT_EXISTS);
        }
    }

    @Override
    public YhbfhzDO getYhbfhz(Integer id) {
        return yhbfhzMapper.selectById(id);
    }

    @Override
    public PageResult<YhbfhzDO> getYhbfhzPage(YhbfhzPageReqVO pageReqVO) {
        return yhbfhzMapper.selectPage(pageReqVO);
    }

}
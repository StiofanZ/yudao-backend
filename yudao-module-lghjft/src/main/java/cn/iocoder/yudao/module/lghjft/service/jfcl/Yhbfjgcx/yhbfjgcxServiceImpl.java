package cn.iocoder.yudao.module.lghjft.service.jfcl.Yhbfjgcx;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.Yhbfjgcx.yhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.Yhbfjgcx.yhbfjgcxMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.YHBFJGCX_NOT_EXISTS;


/**
 * 银行拨付结果查询 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class yhbfjgcxServiceImpl implements yhbfjgcxService {

    @Resource
    private yhbfjgcxMapper yhbfjgcxMapper;

    @Override
    public String createyhbfjgcx(yhbfjgcxSaveReqVO createReqVO) {
        // 插入
        yhbfjgcxDO yhbfjgcx = BeanUtils.toBean(createReqVO, yhbfjgcxDO.class);
        yhbfjgcxMapper.insert(yhbfjgcx);

        // 返回
        return yhbfjgcx.getBfhzid();
    }

    @Override
    public void updateyhbfjgcx(yhbfjgcxSaveReqVO updateReqVO) {
        // 校验存在
        validateyhbfjgcxExists(updateReqVO.getBfhzid());
        // 更新
        yhbfjgcxDO updateObj = BeanUtils.toBean(updateReqVO, yhbfjgcxDO.class);
        yhbfjgcxMapper.updateById(updateObj);
    }

    @Override
    public void deleteyhbfjgcx(String id) {
        // 校验存在
        validateyhbfjgcxExists(id);
        // 删除
        yhbfjgcxMapper.deleteById(id);
    }

    @Override
        public void deleteyhbfjgcxListByIds(List<String> ids) {
        // 删除
        yhbfjgcxMapper.deleteByIds(ids);
        }


    private void validateyhbfjgcxExists(String id) {
        if (yhbfjgcxMapper.selectById(id) == null) {
            throw exception(YHBFJGCX_NOT_EXISTS);
        }
    }

    @Override
    public yhbfjgcxDO getyhbfjgcx(String id) {
        return yhbfjgcxMapper.selectById(id);
    }

    @Override
    public PageResult<yhbfjgcxDO> getyhbfjgcxPage(yhbfjgcxPageReqVO pageReqVO) {
        return yhbfjgcxMapper.selectPage(pageReqVO);
    }

}
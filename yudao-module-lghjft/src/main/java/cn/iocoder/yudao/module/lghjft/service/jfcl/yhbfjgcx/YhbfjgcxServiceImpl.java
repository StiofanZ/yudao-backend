package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfjgcx.YhbfjgcxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.YHBFJGCX_NOT_EXISTS;


/**
 * 银行拨付结果查询 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class YhbfjgcxServiceImpl implements YhbfjgcxService {

    @Resource
    private YhbfjgcxMapper yhbfjgcxMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createyhbfjgcx(YhbfjgcxSaveReqVO createReqVO) {
        // 插入
        YhbfjgcxDO yhbfjgcx = BeanUtils.toBean(createReqVO, YhbfjgcxDO.class);
        yhbfjgcxMapper.insert(yhbfjgcx);

        // 返回
        return yhbfjgcx.getBfhzid();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateyhbfjgcx(YhbfjgcxSaveReqVO updateReqVO) {
        // 校验存在
        validateyhbfjgcxExists(updateReqVO.getBfhzid());
        // 更新
        YhbfjgcxDO updateObj = BeanUtils.toBean(updateReqVO, YhbfjgcxDO.class);
        yhbfjgcxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteyhbfjgcx(String id) {
        // 校验存在
        validateyhbfjgcxExists(id);
        // 删除
        yhbfjgcxMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
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
    public YhbfjgcxDO getyhbfjgcx(String id) {
        return yhbfjgcxMapper.selectById(id);
    }

    @Override
    public PageResult<YhbfjgcxDO> getyhbfjgcxPage(YhbfjgcxPageReqVO pageReqVO) {
        return yhbfjgcxMapper.selectPage(pageReqVO);
    }

}

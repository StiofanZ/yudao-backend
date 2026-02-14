package cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.JcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.jcxx.JcxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_DJXH_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_NOT_EXISTS;

/**
 * 户籍管理/基础信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class JcxxServiceImpl implements JcxxService {

    @Resource
    private JcxxMapper jcxxMapper;

    @Override
    public String createJcxx(JcxxCreateReqVO createReqVO) {
        // 校验登记序号是否唯一
        validateJcxxForCreateOrUpdate(createReqVO.getDjxh(), null);

        // 插入
        JcxxDO jcxx = BeanUtils.toBean(createReqVO, JcxxDO.class);
        jcxxMapper.insert(jcxx);
        // 返回
        return jcxx.getDjxh();
    }

    @Override
    public void updateJcxx(JcxxUpdateReqVO updateReqVO) {
        // 校验存在
        validateJcxxExists(updateReqVO.getDjxh());

        // 更新
        JcxxDO updateObj = BeanUtils.toBean(updateReqVO, JcxxDO.class);
        jcxxMapper.updateById(updateObj);
    }

    @Override
    public void deleteJcxx(String id) {
        // 校验存在
        validateJcxxExists(id);
        // 删除
        jcxxMapper.deleteById(id);
    }

    private void validateJcxxForCreateOrUpdate(String djxh, String id) {
        // 如果 ID 不一致，说明是修改了登记序号（虽然 PK 通常不修改），或者新增
        if (id == null) {
            JcxxDO exists = jcxxMapper.selectById(djxh);
            if (exists != null) {
                throw exception(GH_HJ_DJXH_EXISTS);
            }
        }
    }

    private void validateJcxxExists(String id) {
        if (jcxxMapper.selectById(id) == null) {
            throw exception(GH_HJ_NOT_EXISTS);
        }
    }

    @Override
    public JcxxDO getJcxx(String id) {
        return jcxxMapper.selectById(id);
    }

    @Override
    public PageResult<JcxxDO> getJcxxPage(JcxxPageReqVO pageReqVO) {
        return jcxxMapper.selectPage(pageReqVO);
    }

}

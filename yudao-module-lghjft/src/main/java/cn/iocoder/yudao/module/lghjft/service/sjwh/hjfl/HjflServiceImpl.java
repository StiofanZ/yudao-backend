package cn.iocoder.yudao.module.lghjft.service.sjwh.hjfl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.websocket.core.security.LoginUserHandshakeInterceptor;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hjfl.HjflDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.hjfl.HjflMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HJFL_NOT_EXISTS;

/**
 * 户籍分类 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class HjflServiceImpl implements HjflService {

    @Resource
    private HjflMapper hjflMapper;

    @Override
    public Integer createHjfl(HjflSaveReqVO createReqVO) {
        // 插入
        HjflDO hjfl = BeanUtils.toBean(createReqVO, HjflDO.class);
        hjflMapper.insert(hjfl);

        // 返回
        return hjfl.getHjflid();
    }

    @Override
    public void updateHjfl(HjflSaveReqVO updateReqVO) {
        // 校验存在
        validateHjflExists(updateReqVO.getHjflid());
        // 更新
        HjflDO updateObj = BeanUtils.toBean(updateReqVO, HjflDO.class);
        hjflMapper.updateById(updateObj);
    }

    @Override
    public void deleteHjfl(Integer id) {
        // 校验存在
        validateHjflExists(id);
        // 删除
        hjflMapper.deleteById(id);
    }

    @Override
        public void deleteHjflListByIds(List<Integer> ids) {
        // 删除
        hjflMapper.deleteByIds(ids);
        }


    private void validateHjflExists(Integer id) {
        if (hjflMapper.selectById(id) == null) {
            throw exception(HJFL_NOT_EXISTS);
        }
    }

    @Override
    public HjflDO getHjfl(Integer id) {
        return hjflMapper.selectById(id);
    }

    @Override
    public PageResult<HjflDO> getHjflPage(HjflPageReqVO pageReqVO) {
//        LoginUser loginUser = LoginUserHandshakeInterceptor.getLoginUser();
//        AdminUserDO user = loginUser.getAdminUser();
//        pageReqVO.setDeptId(user.getDeptId().toString()); // 覆盖前端传递的 deptId（可选）
        return hjflMapper.selectPage(pageReqVO);
    }

}
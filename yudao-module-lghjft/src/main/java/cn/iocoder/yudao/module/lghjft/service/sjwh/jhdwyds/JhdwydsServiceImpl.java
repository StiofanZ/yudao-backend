package cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds.JhdwydsMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JHDWYDS_NOT_EXISTS;

/**
 * 应代收单位 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class JhdwydsServiceImpl implements JhdwydsService {

    @Resource
    private JhdwydsMapper jhdwydsMapper;
    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createjhdwyds(JhdwydsSaveReqVO createReqVO) {
        // 插入
        JhdwydsDO jhdwyds = BeanUtils.toBean(createReqVO, JhdwydsDO.class);
        jhdwyds.setCreateTime(DateUtils.getNowDate());
        AdminUserDO user = userService.getUser(getLoginUserId());
        jhdwyds.setCreateBy(user.getNickname());
        jhdwydsMapper.insert(jhdwyds);

        // 返回
        return jhdwyds.getJhdwId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatejhdwyds(JhdwydsSaveReqVO updateReqVO) {
        // 校验存在
        validatejhdwydsExists(updateReqVO.getJhdwId());
        // 更新
        JhdwydsDO updateObj = BeanUtils.toBean(updateReqVO, JhdwydsDO.class);
        AdminUserDO user = userService.getUser(getLoginUserId());
        updateObj.setUpdateBy(user.getNickname());
        jhdwydsMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletejhdwyds(Long id) {
        // 校验存在
        validatejhdwydsExists(id);
        // 删除
        jhdwydsMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletejhdwydsListByIds(List<Long> ids) {
        // 删除
        jhdwydsMapper.deleteByIds(ids);
    }


    private void validatejhdwydsExists(Long id) {
        if (jhdwydsMapper.selectById(id) == null) {
            throw exception(JHDWYDS_NOT_EXISTS);
        }
    }

    @Override
    public JhdwydsDO getjhdwyds(Long id) {
        return jhdwydsMapper.selectById(id);
    }

    @Override
    public PageResult<JhdwydsDO> getjhdwydsPage(JhdwydsPageReqVO pageReqVO) {
        // v1 dept filtering: default to login user's deptId, 100000 means all
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            pageReqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return jhdwydsMapper.selectPage(pageReqVO);
    }


    @Override
    public List<JhdwydsDO> getJhdwydsList(JhdwydsSaveReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            reqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(reqVO.getDeptId())) {
            reqVO.setDeptId(null);
        }

        return jhdwydsMapper.selectList(reqVO);
    }

}

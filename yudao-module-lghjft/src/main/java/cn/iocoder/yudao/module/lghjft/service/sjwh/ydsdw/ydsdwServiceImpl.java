package cn.iocoder.yudao.module.lghjft.service.sjwh.ydsdw;

import cn.hutool.core.collection.CollUtil;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw.ydsdwDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.ydsdw.ydsdwMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 * 应代收单位 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class ydsdwServiceImpl implements ydsdwService {

    @Resource
    private ydsdwMapper ydsdwMapper;
    @Resource
    private AdminUserService userService;
    @Override
    public Integer createydsdw(ydsdwSaveReqVO createReqVO) {
        // 插入
        ydsdwDO ydsdw = BeanUtils.toBean(createReqVO, ydsdwDO.class);
        ydsdw.setCreateTime(DateUtils.getNowDate());
        AdminUserDO user = userService.getUser(getLoginUserId());
        ydsdw.setCreateBy(user.getNickname());
        ydsdwMapper.insert(ydsdw);

        // 返回
        return ydsdw.getJhdwId();
    }

    @Override
    public void updateydsdw(ydsdwSaveReqVO updateReqVO) {
        // 校验存在
        validateydsdwExists(updateReqVO.getJhdwId());
        // 更新
        ydsdwDO updateObj = BeanUtils.toBean(updateReqVO, ydsdwDO.class);
      AdminUserDO user = userService.getUser(getLoginUserId());
        updateObj.setUpdateBy(user.getNickname());


        ydsdwMapper.updateById(updateObj);
    }

    @Override
    public void deleteydsdw(Integer id) {
        // 校验存在
        validateydsdwExists(id);
        // 删除
        ydsdwMapper.deleteById(id);
    }

    @Override
        public void deleteydsdwListByIds(List<Integer> ids) {
        // 删除
        ydsdwMapper.deleteByIds(ids);
        }


    private void validateydsdwExists(Integer id) {
        if (ydsdwMapper.selectById(id) == null) {
            throw exception(JHDWYDS_NOT_EXISTS);
        }
    }

    @Override
    public ydsdwDO getydsdw(Integer id) {
        return ydsdwMapper.selectById(id);
    }

    @Override
    public PageResult<ydsdwDO> getydsdwPage(ydsdwPageReqVO pageReqVO) {
        return ydsdwMapper.selectPage(pageReqVO);
    }


    @Override
    public List<ydsdwDO> getJhdwydsList(ydsdwSaveReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            reqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(reqVO.getDeptId())) {
            reqVO.setDeptId(null);
        }

        return ydsdwMapper.selectList(reqVO);
    }

}
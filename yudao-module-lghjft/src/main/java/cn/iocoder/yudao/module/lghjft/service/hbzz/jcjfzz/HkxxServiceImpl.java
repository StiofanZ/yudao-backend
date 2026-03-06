package cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfmx.JfDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.HkxxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz.HkxxMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;


/**
 * 基层经费到账对象 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class HkxxServiceImpl implements HkxxService {

    @Resource
    private HkxxMapper hkxxMapper;
    @Resource
    private AdminUserService userService;
    @Override
    public void updateHkxx(HkxxSaveReqVO updateReqVO) {
        AdminUserDO user = userService.getUser(getLoginUserId());
        String nickname = user.getNickname();

        // 1. 更新主表（不动）
        validateHkxxExists(updateReqVO.getHkxxId());
        HkxxDO mainDO = BeanUtils.toBean(updateReqVO, HkxxDO.class);
        mainDO.setUpdateTime(DateUtils.getNowDate());
        hkxxMapper.updateJcjfzz(mainDO);

        // ==============================================
        // 子表：一对一 · 有则更新、无则插入（不使用list）
        // ==============================================
        updateReqVO.setUpdateBy(nickname);
        updateReqVO.setUpdateTime(new Date());

        // 尝试更新 → 返回影响行数
        int rows = hkxxMapper.updateJcjfdz(updateReqVO);

        // 更新 0 行 → 说明没有数据 → 直接插入单条
        if (rows == 0) {
            updateReqVO.setUpdateBy(nickname);
            updateReqVO.setUpdateTime(new Date());
            updateReqVO.setDzbj("Y");
            hkxxMapper.insertJcjfdz(updateReqVO); // 单条插入！无list！
        }

    }
//
//    @Override
//    public void deleteHkxx(Integer id) {
//        // 校验存在
//        validateHkxxExists(id);
//        // 删除
//        hkxxMapper.deleteById(id);
//    }
//
//    @Override
//        public void deleteHkxxListByIds(List<Integer> ids) {
//        // 删除
//        hkxxMapper.deleteByIds(ids);
//        }
//
//
    private void validateHkxxExists(Integer id) {
        if (hkxxMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public HkxxDO getHkxx(Integer id) {
        return hkxxMapper.selectJcjfzzByHkxxId(id);
    }
@Override
public PageResult<HkxxRespVO> getHkxxPage(HkxxPageReqVO pageReqVO) {
    // 1. 没传部门 → 自动用当前用户部门
    if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
        AdminUserDO user = userService.getUser(getLoginUserId());
        pageReqVO.setDeptId(user.getDeptId().toString());
    }

    // 2. 传 100000 → 查全部
    if ("100000".equals(pageReqVO.getDeptId())) {
        pageReqVO.setDeptId(null);
    }
    // 1. 构建分页（固定写法）
    Page<HkxxRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

    // 2. 调用你的XML联表查询
    IPage<HkxxRespVO> ipage = hkxxMapper.selectJcjfzzList(page, pageReqVO);

    // 3. 转成框架需要的格式
    return new PageResult<>(ipage.getRecords(), ipage.getTotal());
}

}
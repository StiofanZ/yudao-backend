package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import cn.hutool.core.collection.CollUtil;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.HkxxDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfmx.JfDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfmx.JfMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 *  经费明细对象 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class JfServiceImpl implements JfService {
    @Resource
    private JfMapper jfMapper;
    @Resource
    private AdminUserService userService;
//    查询明细列表
@Override
public PageResult<JfRespVO> selectJftzmxList(JfPageReqVO jfmx) {

    // ===================== 【加入的权限控制逻辑】 =====================
    // 1. 如果前端没传 部门ID(deptId)，自动设置为当前登录用户的部门ID
    if (StringUtils.isEmpty(jfmx.getDeptId())) {
        // 你项目里真实的获取用户方式
        AdminUserDO user = userService.getUser(getLoginUserId());
        // 设置当前用户的部门ID
        jfmx.setDeptId(user.getDeptId().toString());
    }
    // 2. 如果部门ID是 100000，代表查询全部，置为null
    if ("100000".equals(jfmx.getDeptId())) {
        jfmx.setDeptId(null);
    }
    // ==================================================================

    // 3. 构建 MyBatis-Plus 分页对象
    Page<JfRespVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
    IPage<JfRespVO> ipage =  jfMapper.selectJftzmxList(page,jfmx);

    // 4. 转换成芋道源码需要的 PageResult 返回
    return new PageResult<>(ipage.getRecords(), ipage.getTotal());
}


}
package cn.iocoder.yudao.module.lghjft.service.xwqygl;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglRespVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqygl.XwqyglMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XwqyglServiceImpl implements XwqyglService {

    @Resource
    private XwqyglMapper xwqyglMapper;

    @Override
    public PageResult<XwqyglRespVO> getXwqyglPage(XwqyglQuery query) {

        // 如果没有传 deptId，就用当前登录用户部门
        if (query.getDeptId() == null || query.getDeptId().isEmpty()) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            query.setDeptId(loginDeptId != null ? loginDeptId.toString() : null);
        }

        // 超级管理员不限制 deptId
        if ("100000".equals(query.getDeptId())) {
            query.setDeptId(null);
        }

        // 计算分页参数
        int offset = (query.getPageNo() - 1) * query.getPageSize();
        query.setOffset(offset);
        query.setLimit(query.getPageSize());

        // 查询列表
        List<XwqyglRespVO> list = xwqyglMapper.selectXwqyglList(query);
        // 查询总数
        long total = xwqyglMapper.selectXwqyglCount(query);

        // 返回 PageResult
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyglRespVO> getXwqyglList(XwqyglQuery query) {
        if (StrUtil.isEmpty(query.getDeptId())) {
            Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (deptId != null) {
                query.setDeptId(deptId.toString());
            }
        }

        if ("100000".equals(query.getDeptId())) {
            query.setDeptId(null);
        }

        return xwqyglMapper.selectXwqyglList(query);
    }
}
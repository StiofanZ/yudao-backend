package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqygl;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqygl.XwqyglMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class XwqyglServiceImpl implements XwqyglService {

    @Resource
    private XwqyglMapper xwqyglMapper;

    @Override
    public PageResult<XwqyglResVO> getXwqyglPage(XwqyglPageReqVO pageReqVO) {

        // 如果没有传 deptId，就用当前登录用户部门
        if (pageReqVO.getDeptId() == null || pageReqVO.getDeptId().isEmpty()) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            pageReqVO.setDeptId(loginDeptId != null ? loginDeptId.toString() : null);
        }

        // 超级管理员不限制 deptId
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }

        // 计算分页参数
        int offset = (pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize();
        pageReqVO.setOffset(offset);
        pageReqVO.setLimit(pageReqVO.getPageSize());

        // 查询列表
        List<XwqyglResVO> list = xwqyglMapper.selectXwqyglList(pageReqVO);
        // 查询总数
        long total = xwqyglMapper.selectXwqyglCount(pageReqVO);

        // 返回 PageResult
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyglResVO> getXwqyglList(XwqyglPageReqVO pageReqVO) {
        if (StrUtil.isEmpty(pageReqVO.getDeptId())) {
            Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (deptId != null) {
                pageReqVO.setDeptId(deptId.toString());
            }
        }

        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }

        return xwqyglMapper.selectXwqyglList(pageReqVO);
    }

    @Override
    public XwqyglResVO getXwqyglByDjxh(String djxh) {
        return xwqyglMapper.selectXwqyglByDjxh(djxh);
    }

    @Override
    public void createXwqygl(XwqyglSaveReqVO reqVO) {
        xwqyglMapper.insertXwqygl(reqVO);
    }

    @Override
    public void updateXwqygl(XwqyglSaveReqVO reqVO) {
        // v1: updateBy = 当前用户昵称, updateTime = sysdate() (在 SQL 中)
        xwqyglMapper.updateXwqygl(reqVO);
    }

    @Override
    public void deleteXwqyglByDjxhs(String[] djxhs) {
        xwqyglMapper.deleteXwqyglByDjxhs(djxhs);
    }
}

package cn.iocoder.yudao.module.lghjft.service.jf.jfmx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.Jfmx;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxExportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo.JfmxQuery;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jf.jfmx.JfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JfmxServiceImpl implements JfmxService {

    @Resource
    private JfmxMapper jfmxMapper;

    @Override
    public List<Jfmx> selectJfmxList(JfmxQuery query) {
        // 如果传了 djxh（单户台账明细查询），不需要自动添加 deptId 过滤
        if (StrUtil.isEmpty(query.getDjxh())) {
            // 如果没有传 deptId，就用当前登录用户部门
            if (StrUtil.isEmpty(query.getDeptId())) {
                Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
                if (loginDeptId != null) {
                    query.setDeptId(loginDeptId.toString());
                }
            }
        }

        // 超级管理员不限制 deptId
        if ("100000".equals(query.getDeptId())) {
            query.setDeptId(null);
        }

        return jfmxMapper.selectJfmxList(query);
    }

    @Override
    public PageResult<Jfmx> selectJfmxPage(JfmxQuery query) {
        // 如果传了 djxh（单户台账明细查询），不需要自动添加 deptId 过滤
        if (StrUtil.isEmpty(query.getDjxh())) {
            // 如果没有传 deptId，就用当前登录用户部门
            if (StrUtil.isEmpty(query.getDeptId())) {
                Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
                if (loginDeptId != null) {
                    query.setDeptId(loginDeptId.toString());
                }
            }
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
        List<Jfmx> list = jfmxMapper.selectJfmxList(query);
        // 查询总数
        int total = jfmxMapper.selectJfmxCount(query);

        // 返回 PageResult
        return new PageResult<>(list, (long) total);
    }

    @Override
    public int selectJfmxCount(JfmxQuery query) {
        return jfmxMapper.selectJfmxCount(query);
    }

    @Override
    public List<JfmxExportVO> selectJfmxExportList(JfmxQuery query) {
        // 如果没有传 deptId，就用当前登录用户部门
        if (StrUtil.isEmpty(query.getDeptId())) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (loginDeptId != null) {
                query.setDeptId(loginDeptId.toString());
            }
        }

        // 超级管理员不限制 deptId
        if ("100000".equals(query.getDeptId())) {
            query.setDeptId(null);
        }

        // 查询列表
        List<Jfmx> list = jfmxMapper.selectJfmxList(query);
        // 转换为导出VO
        return list.stream()
                .map(jfmx -> {
                    JfmxExportVO exportVO = new JfmxExportVO();
                    BeanUtils.copyProperties(jfmx, exportVO);
                    return exportVO;
                })
                .collect(Collectors.toList());
    }
}

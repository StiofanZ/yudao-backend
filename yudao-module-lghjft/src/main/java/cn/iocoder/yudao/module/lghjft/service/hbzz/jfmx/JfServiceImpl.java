package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfmx.JfMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

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

    @Override
    public PageResult<JfResVO> selectJfmxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfResVO> ipage = jfMapper.selectJfmxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfResVO> selectJftzmxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfResVO> ipage = jfMapper.selectJftzmxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectJffymxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectJffymxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectJffnmxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectJffnmxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectJffsjzqmxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectJffsjzqmxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectJftzfnList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectJftzfnList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectJftzfswjgList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectJftzfswjgList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JfSummaryResVO> selectSzdzhdList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfSummaryResVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfSummaryResVO> ipage = jfMapper.selectSzdzhdList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    private void clBmId(JfPageReqVO jfmx) {
        // 只有前端没传deptId时，才尝试自动获取登录用户部门
        if (StringUtils.isEmpty(jfmx.getDeptId())) {
            try {
                // 获取当前登录用户
                AdminUserDO user = userService.getUser(getLoginUserId());
                // 关键：用户存在 并且 部门ID不为空 才设置
                if (user != null && user.getDeptId() != null) {
                    jfmx.setDeptId(user.getDeptId().toString());
                } else {
                    // 没有部门
                    jfmx.setDeptId(null);
                }
            } catch (Exception e) {
                // 获取用户/部门失败 → 不设置，避免报错
                jfmx.setDeptId(null);
            }
        }
        if ("100000".equals(jfmx.getDeptId())) {
            jfmx.setDeptId(null);
        }
    }


}

package cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo.JftzfnSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jftzfn.JftzfnMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 经费台账分年 Service 实现
 */
@Service
@Validated
public class JftzfnServiceImpl implements JftzfnService {

    @Resource
    private JftzfnMapper jftzfnMapper;
    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public PageResult<JftzfnSummaryResVO> selectJftzfnList(JftzfnPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        Page<JftzfnSummaryResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JftzfnSummaryResVO> ipage = jftzfnMapper.selectJftzfnList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }
}

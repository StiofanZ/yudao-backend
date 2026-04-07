package cn.iocoder.yudao.module.lghjft.service.cxtj.jffnmx;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffnmx.JffnmxMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 经费分年明细 Service 实现
 */
@Service
@Validated
public class JffnmxServiceImpl implements JffnmxService {

    @Resource
    private JffnmxMapper jffnmxMapper;
    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public PageResult<JffnmxSummaryResVO> selectJffnmxList(JffnmxPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        // V1 startPage() 被注释，不分页
        if (PageParam.PAGE_SIZE_NONE.equals(pageReqVO.getPageSize())) {
            Page<JffnmxSummaryResVO> page = new Page<>(1, -1);
            page.setSearchCount(false);
            IPage<JffnmxSummaryResVO> ipage = jffnmxMapper.selectJffnmxList(page, pageReqVO);
            List<JffnmxSummaryResVO> list = ipage.getRecords();
            return new PageResult<>(list, (long) list.size());
        }

        Page<JffnmxSummaryResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JffnmxSummaryResVO> ipage = jffnmxMapper.selectJffnmxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }
}

package cn.iocoder.yudao.module.lghjft.service.cxtj.jffymx;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffymx.JffymxMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 经费分月明细 Service 实现
 */
@Service
@Validated
public class JffymxServiceImpl implements JffymxService {

    @Resource
    private JffymxMapper jffymxMapper;
    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public PageResult<JffymxSummaryResVO> selectJffymxList(JffymxPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        // V1 startPage() 被注释，不分页
        if (PageParam.PAGE_SIZE_NONE.equals(pageReqVO.getPageSize())) {
            Page<JffymxSummaryResVO> page = new Page<>(1, -1);
            page.setSearchCount(false);
            IPage<JffymxSummaryResVO> ipage = jffymxMapper.selectJffymxList(page, pageReqVO);
            List<JffymxSummaryResVO> list = ipage.getRecords();
            return new PageResult<>(list, (long) list.size());
        }

        Page<JffymxSummaryResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JffymxSummaryResVO> ipage = jffymxMapper.selectJffymxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }
}

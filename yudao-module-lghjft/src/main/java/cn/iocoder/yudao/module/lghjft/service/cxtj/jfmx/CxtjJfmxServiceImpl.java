package cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jfmx.CxtjJfmxMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CxtjJfmxServiceImpl implements CxtjJfmxService {

    @Resource
    private CxtjJfmxMapper cxtjJfmxMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public CxtjJfmxDO getJfmx(String spuuid) {
        return cxtjJfmxMapper.selectOne(
                new QueryWrapper<CxtjJfmxDO>().eq("spuuid", spuuid));
    }

    @Override
    public PageResult<JfmxResVO> getJfmxPage(JfmxPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        Page<JfmxResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JfmxResVO> ipage = cxtjJfmxMapper.selectJfmxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<JftzmxResVO> getJftzmxPage(JftzmxPageReqVO pageReqVO) {
        // V1 selectJftzmxList 不做 deptId 过滤（deptId 条件直接在 SQL 中处理）
        Page<JftzmxResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JftzmxResVO> ipage = cxtjJfmxMapper.selectJftzmxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public PageResult<SzdzhdResVO> getSzdzhdPage(SzdzhdPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        Page<SzdzhdResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<SzdzhdResVO> ipage = cxtjJfmxMapper.selectSzdzhdList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }
}

package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top.TopMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class TopServiceImpl implements TopService {

    @Resource
    private TopMapper topMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public TopDO getTop(String djxh) {
        return topMapper.selectById(djxh);
    }

    @Override
    public List<TopDO> getTopList(TopPageReqVO reqVO) {
        // 还原 V1 行为：selectTopList root = "620000"（甘肃省）
        reqVO.setDeptId(deptFilterHelper.filterDeptId(reqVO.getDeptId(), DeptFilterHelper.ROOT_DEPT_SECONDARY));
        return topMapper.selectList(reqVO);
    }

    @Override
    public PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO) {
        // 还原 V1 行为：selectTopList1 root = "100000"（全国）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return topMapper.selectPage(pageReqVO);
    }
}

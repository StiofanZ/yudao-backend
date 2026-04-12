package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ghjfjfdw.GhjfjfdwMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class GhjfjfdwServiceImpl implements GhjfjfdwService {

    @Resource
    private GhjfjfdwMapper ghjfjfdwMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public GhjfjfdwDO getGhjfjfdw(String djxh) {
        return ghjfjfdwMapper.selectById(djxh);
    }

    @Override
    public PageResult<GhjfjfdwDO> getGhjfjfdwPage(GhjfjfdwPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return ghjfjfdwMapper.selectPage(pageReqVO);
    }
}

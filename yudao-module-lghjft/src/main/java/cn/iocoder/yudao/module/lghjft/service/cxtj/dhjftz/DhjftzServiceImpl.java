package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz.DhjftzMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DhjftzServiceImpl implements DhjftzService {

    @Resource
    private DhjftzMapper dhjftzMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    /**
     * V1: selectDhjftzByDeptId — 按 dept_id 查询单条
     */
    @Override
    public DhjftzDO getDhjftzByDeptId(String deptId) {
        return dhjftzMapper.selectOne(new QueryWrapper<DhjftzDO>()
                .eq("dept_id", deptId)
                .last("limit 1"));
    }

    @Override
    public PageResult<DhjftzDO> getDhjftzPage(DhjftzPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return dhjftzMapper.selectPage(pageReqVO);
    }
}

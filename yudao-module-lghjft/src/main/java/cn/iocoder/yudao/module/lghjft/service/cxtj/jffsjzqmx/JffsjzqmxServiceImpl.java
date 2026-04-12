package cn.iocoder.yudao.module.lghjft.service.cxtj.jffsjzqmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffsjzqmx.JffsjzqmxMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class JffsjzqmxServiceImpl implements JffsjzqmxService {

    @Resource
    private JffsjzqmxMapper jffsjzqmxMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public PageResult<JffsjzqmxResVO> getJffsjzqmxPage(JffsjzqmxPageReqVO pageReqVO) {
        // 还原 V1 部门过滤逻辑：自动填充当前用户部门，根部门（100000）则不过滤
        String filteredDeptId = deptFilterHelper.filterDeptId(pageReqVO.getDeptId());
        pageReqVO.setDeptId(filteredDeptId);
        List<JffsjzqmxResVO> list = jffsjzqmxMapper.selectJffsjzqmxList(pageReqVO);
        return new PageResult<>(list, (long) list.size());
    }
}

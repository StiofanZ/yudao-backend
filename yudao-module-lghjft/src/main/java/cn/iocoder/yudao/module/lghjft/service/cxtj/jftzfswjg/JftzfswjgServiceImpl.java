package cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfswjg;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jftzfswjg.JftzfswjgMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class JftzfswjgServiceImpl implements JftzfswjgService {

    @Resource
    private JftzfswjgMapper jftzfswjgMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public List<JftzfswjgResVO> getJftzfswjgList(JftzfswjgPageReqVO pageReqVO) {
        // 还原 V1 部门过滤逻辑：自动填充当前用户部门，根部门则不过滤
        String filteredDeptId = deptFilterHelper.filterDeptId(pageReqVO.getDeptId());
        pageReqVO.setDeptId(filteredDeptId);
        return jftzfswjgMapper.selectJftzfswjgList(pageReqVO);
    }
}

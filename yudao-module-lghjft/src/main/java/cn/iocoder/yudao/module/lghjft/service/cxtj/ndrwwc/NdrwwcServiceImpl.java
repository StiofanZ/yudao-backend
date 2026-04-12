package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc.NdrwwcMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class NdrwwcServiceImpl implements NdrwwcService {

    @Resource
    private NdrwwcMapper ndrwwcMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public NdrwwcDO getNdrwwc(String nd) {
        return ndrwwcMapper.selectById(nd);
    }

    @Override
    public PageResult<NdrwwcDO> getNdrwwcPage(NdrwwcPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDwdm(deptFilterHelper.filterDeptId(pageReqVO.getDwdm()));
        return ndrwwcMapper.selectPage(pageReqVO);
    }
}

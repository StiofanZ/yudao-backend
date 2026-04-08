package cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxtjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.cbjmx.CbjmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 筹备金统计 Service 实现
 */
@Service
@Validated
public class CbjmxServiceImpl implements CbjmxService {

    @Resource
    private CbjmxMapper cbjmxMapper;

    @Override
    public PageResult<CbjmxDO> getCbjmxPage(CbjmxPageReqVO pageReqVO) {
        return cbjmxMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CbjmxtjDO> getCbjmxtjList(CbjmxPageReqVO reqVO) {
        return cbjmxMapper.selectCbjmxtjList(
                reqVO.getZspmDm(), reqVO.getNd(), reqVO.getDeptId(),
                reqVO.getShxydm(), reqVO.getNsrmc());
    }

    @Override
    public List<CbjmxhzDO> getCbjmxhzList(CbjmxPageReqVO reqVO) {
        return cbjmxMapper.selectCbjmxhzList(
                reqVO.getZspmDm(), reqVO.getNd(), reqVO.getDeptId(),
                reqVO.getShxydm(), reqVO.getNsrmc());
    }
}

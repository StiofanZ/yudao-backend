package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc.NdrwwcMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.NDRWWC_NOT_EXISTS;

@Service
@Validated
public class NdrwwcServiceImpl implements NdrwwcService {

    @Resource
    private NdrwwcMapper ndrwwcMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createNdrwwc(NdrwwcSaveReqVO createReqVO) {
        NdrwwcDO obj = BeanUtils.toBean(createReqVO, NdrwwcDO.class);
        ndrwwcMapper.insert(obj);
        return obj.getNd();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNdrwwc(NdrwwcSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getNd());
        NdrwwcDO updateObj = BeanUtils.toBean(updateReqVO, NdrwwcDO.class);
        ndrwwcMapper.updateById(updateObj);
    }

    /**
     * 批量删除 — V1: deleteNdrwwcByNds(String[] nds)
     * delete from ndrwwc where ND in (...)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNdrwwcByNds(String[] nds) {
        if (nds == null || nds.length == 0) {
            return;
        }
        ndrwwcMapper.delete(new QueryWrapper<NdrwwcDO>()
                .in("nd", Arrays.asList(nds)));
    }

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

    private void validateExists(String nd) {
        if (ndrwwcMapper.selectById(nd) == null) {
            throw exception(NDRWWC_NOT_EXISTS);
        }
    }
}

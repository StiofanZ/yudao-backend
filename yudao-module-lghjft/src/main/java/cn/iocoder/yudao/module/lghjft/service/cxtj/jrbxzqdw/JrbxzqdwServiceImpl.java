package cn.iocoder.yudao.module.lghjft.service.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jrbxzqdw.JrbxzqdwMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class JrbxzqdwServiceImpl implements JrbxzqdwService {

    @Resource
    private JrbxzqdwMapper jrbxzqdwMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createJrbxzqdw(JrbxzqdwSaveReqVO createReqVO) {
        JrbxzqdwDO obj = BeanUtils.toBean(createReqVO, JrbxzqdwDO.class);
        jrbxzqdwMapper.insert(obj);
        return obj.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJrbxzqdw(JrbxzqdwSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        JrbxzqdwDO updateObj = BeanUtils.toBean(updateReqVO, JrbxzqdwDO.class);
        jrbxzqdwMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJrbxzqdw(String id) {
        validateExists(id);
        jrbxzqdwMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJrbxzqdwListByIds(List<String> ids) {
        jrbxzqdwMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (jrbxzqdwMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public JrbxzqdwDO getJrbxzqdw(String id) {
        return jrbxzqdwMapper.selectById(id);
    }

    @Override
    public PageResult<JrbxzqdwDO> getJrbxzqdwPage(JrbxzqdwPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return jrbxzqdwMapper.selectPage(pageReqVO);
    }
}

package cn.iocoder.yudao.module.lghjft.service.cxtj.fydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fydsqk.FydsqkMapper;
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
public class FydsqkServiceImpl implements FydsqkService {

    @Resource
    private FydsqkMapper fydsqkMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createFydsqk(FydsqkSaveReqVO createReqVO) {
        FydsqkDO obj = BeanUtils.toBean(createReqVO, FydsqkDO.class);
        fydsqkMapper.insert(obj);
        return obj.getDeptId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFydsqk(FydsqkSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDeptId());
        FydsqkDO updateObj = BeanUtils.toBean(updateReqVO, FydsqkDO.class);
        fydsqkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFydsqk(String id) {
        validateExists(id);
        fydsqkMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFydsqkListByIds(List<String> ids) {
        fydsqkMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (fydsqkMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public FydsqkDO getFydsqk(String id) {
        return fydsqkMapper.selectById(id);
    }

    @Override
    public PageResult<FydsqkDO> getFydsqkPage(FydsqkPageReqVO pageReqVO) {
        // 聚合查询结果包装为 PageResult（不做真正分页，返回全部聚合行）
        List<FydsqkDO> list = getFydsqkAggregateList(pageReqVO);
        PageResult<FydsqkDO> result = new PageResult<>();
        result.setList(list);
        result.setTotal((long) list.size());
        return result;
    }

    @Override
    public List<FydsqkDO> getFydsqkAggregateList(FydsqkPageReqVO reqVO) {
        // DeptFilterHelper: 还原 V1 部门自动填充与根节点放行
        String deptId = deptFilterHelper.filterDeptId(reqVO.getDeptId(), DeptFilterHelper.ROOT_DEPT_SECONDARY);
        reqVO.setDeptId(deptId);

        // V1 聚合查询：GROUP BY DSYF WITH ROLLUP
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            return fydsqkMapper.selectAggregateListByDateRange(reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        return fydsqkMapper.selectAggregateList();
    }
}

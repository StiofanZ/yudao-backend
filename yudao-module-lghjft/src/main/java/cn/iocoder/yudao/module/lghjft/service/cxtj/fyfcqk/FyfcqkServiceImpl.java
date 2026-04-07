package cn.iocoder.yudao.module.lghjft.service.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fyfcqk.FyfcqkMapper;
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
public class FyfcqkServiceImpl implements FyfcqkService {

    @Resource
    private FyfcqkMapper fyfcqkMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createFyfcqk(FyfcqkSaveReqVO createReqVO) {
        FyfcqkDO obj = BeanUtils.toBean(createReqVO, FyfcqkDO.class);
        fyfcqkMapper.insert(obj);
        return obj.getDeptId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFyfcqk(FyfcqkSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDeptId());
        FyfcqkDO updateObj = BeanUtils.toBean(updateReqVO, FyfcqkDO.class);
        fyfcqkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFyfcqk(String id) {
        validateExists(id);
        fyfcqkMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFyfcqkListByIds(List<String> ids) {
        fyfcqkMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (fyfcqkMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public FyfcqkDO getFyfcqk(String id) {
        return fyfcqkMapper.selectById(id);
    }

    @Override
    public PageResult<FyfcqkDO> getFyfcqkPage(FyfcqkPageReqVO pageReqVO) {
        // 聚合查询结果包装为 PageResult（不做真正分页，返回全部聚合行）
        List<FyfcqkDO> list = getFyfcqkAggregateList(pageReqVO);
        PageResult<FyfcqkDO> result = new PageResult<>();
        result.setList(list);
        result.setTotal((long) list.size());
        return result;
    }

    @Override
    public List<FyfcqkDO> getFyfcqkAggregateList(FyfcqkPageReqVO reqVO) {
        // DeptFilterHelper: 还原 V1 部门自动填充与根节点放行
        String deptId = deptFilterHelper.filterDeptId(reqVO.getDeptId(), DeptFilterHelper.ROOT_DEPT_SECONDARY);
        reqVO.setDeptId(deptId);

        // V1 聚合查询：GROUP BY DSYF WITH ROLLUP
        if (reqVO.getBeginRkrq() != null && !reqVO.getBeginRkrq().isEmpty()
                && reqVO.getEndRkrq() != null && !reqVO.getEndRkrq().isEmpty()) {
            return fyfcqkMapper.selectAggregateListByDateRange(reqVO.getBeginRkrq(), reqVO.getEndRkrq());
        }
        return fyfcqkMapper.selectAggregateList();
    }
}

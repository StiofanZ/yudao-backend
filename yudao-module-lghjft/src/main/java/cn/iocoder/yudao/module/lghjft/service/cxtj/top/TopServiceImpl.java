package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top.TopMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.TOP_NOT_EXISTS;

@Service
@Validated
public class TopServiceImpl implements TopService {

    @Resource
    private TopMapper topMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTop(TopSaveReqVO createReqVO) {
        TopDO obj = BeanUtils.toBean(createReqVO, TopDO.class);
        topMapper.insert(obj);
        return obj.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTop(TopSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());
        TopDO updateObj = BeanUtils.toBean(updateReqVO, TopDO.class);
        topMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTop(String djxh) {
        validateExists(djxh);
        topMapper.deleteById(djxh);
    }

    @Override
    public TopDO getTop(String djxh) {
        return topMapper.selectById(djxh);
    }

    @Override
    public PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：selectTopList1 root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return topMapper.selectPage(pageReqVO);
    }

    private void validateExists(String djxh) {
        if (topMapper.selectById(djxh) == null) {
            throw exception(TOP_NOT_EXISTS);
        }
    }
}

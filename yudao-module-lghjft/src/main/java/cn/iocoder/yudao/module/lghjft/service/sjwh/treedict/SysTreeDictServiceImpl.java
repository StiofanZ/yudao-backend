package cn.iocoder.yudao.module.lghjft.service.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.treedict.SysTreeDictMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SYS_TREE_DICT_NOT_EXISTS;

/**
 * 树形字典 Service 实现类
 *
 * @author zhaofan
 */
@Service
@Validated
public class SysTreeDictServiceImpl implements SysTreeDictService {

    @Resource
    private SysTreeDictMapper sysTreeDictMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createSysTreeDict(SysTreeDictSaveReqVO createReqVO) {
        // 插入
        SysTreeDictDO sysTreeDict = BeanUtils.toBean(createReqVO, SysTreeDictDO.class);
        sysTreeDictMapper.insert(sysTreeDict);
        // 返回
        return sysTreeDict.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysTreeDict(SysTreeDictSaveReqVO updateReqVO) {
        // 校验存在
        validateSysTreeDictExists(updateReqVO.getId());
        // 更新
        SysTreeDictDO updateObj = BeanUtils.toBean(updateReqVO, SysTreeDictDO.class);
        sysTreeDictMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSysTreeDict(String id) {
        // 校验存在
        validateSysTreeDictExists(id);
        // 逻辑删除：设置 del_flag = '1'
        sysTreeDictMapper.update(null, new LambdaUpdateWrapper<SysTreeDictDO>()
                .eq(SysTreeDictDO::getId, id)
                .set(SysTreeDictDO::getDelFlag, "1"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSysTreeDictListByIds(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        sysTreeDictMapper.update(null, new LambdaUpdateWrapper<SysTreeDictDO>()
                .in(SysTreeDictDO::getId, ids)
                .set(SysTreeDictDO::getDelFlag, "1"));
    }

    private void validateSysTreeDictExists(String id) {
        if (sysTreeDictMapper.selectById(id) == null) {
            throw exception(SYS_TREE_DICT_NOT_EXISTS);
        }
    }

    @Override
    public SysTreeDictDO getSysTreeDict(String id) {
        return sysTreeDictMapper.selectById(id);
    }

    @Override
    public PageResult<SysTreeDictDO> getSysTreeDictPage(SysTreeDictPageReqVO pageReqVO) {
        return sysTreeDictMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SysTreeDictDO> getSysTreeDictList() {
        return sysTreeDictMapper.selectList();
    }

}

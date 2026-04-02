package cn.iocoder.yudao.module.lghjft.service.sjwh.treedict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo.SysTreeDictDataSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.treedict.SysTreeDictDataDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.treedict.SysTreeDictDataMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SYS_TREE_DICT_DATA_NOT_EXISTS;

/**
 * 树形字典数据 Service 实现类
 *
 * @author zhaofan
 */
@Service
@Validated
public class SysTreeDictDataServiceImpl implements SysTreeDictDataService {

    @Resource
    private SysTreeDictDataMapper sysTreeDictDataMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createSysTreeDictData(SysTreeDictDataSaveReqVO createReqVO) {
        // 插入
        SysTreeDictDataDO sysTreeDictData = BeanUtils.toBean(createReqVO, SysTreeDictDataDO.class);
        sysTreeDictDataMapper.insert(sysTreeDictData);
        // 返回
        return sysTreeDictData.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysTreeDictData(SysTreeDictDataSaveReqVO updateReqVO) {
        // 校验存在
        validateSysTreeDictDataExists(updateReqVO.getId());
        // 更新
        SysTreeDictDataDO updateObj = BeanUtils.toBean(updateReqVO, SysTreeDictDataDO.class);
        sysTreeDictDataMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSysTreeDictData(String id) {
        // 校验存在
        validateSysTreeDictDataExists(id);
        // 逻辑删除：设置 del_flag = '1'
        sysTreeDictDataMapper.update(null, new LambdaUpdateWrapper<SysTreeDictDataDO>()
                .eq(SysTreeDictDataDO::getId, id)
                .set(SysTreeDictDataDO::getDelFlag, "1"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSysTreeDictDataListByIds(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        sysTreeDictDataMapper.update(null, new LambdaUpdateWrapper<SysTreeDictDataDO>()
                .in(SysTreeDictDataDO::getId, ids)
                .set(SysTreeDictDataDO::getDelFlag, "1"));
    }

    private void validateSysTreeDictDataExists(String id) {
        if (sysTreeDictDataMapper.selectById(id) == null) {
            throw exception(SYS_TREE_DICT_DATA_NOT_EXISTS);
        }
    }

    @Override
    public SysTreeDictDataDO getSysTreeDictData(String id) {
        return sysTreeDictDataMapper.selectById(id);
    }

    @Override
    public PageResult<SysTreeDictDataDO> getSysTreeDictDataPage(SysTreeDictDataPageReqVO pageReqVO) {
        return sysTreeDictDataMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SysTreeDictDataDO> getSysTreeDictDataList() {
        return sysTreeDictDataMapper.selectListAll();
    }

}

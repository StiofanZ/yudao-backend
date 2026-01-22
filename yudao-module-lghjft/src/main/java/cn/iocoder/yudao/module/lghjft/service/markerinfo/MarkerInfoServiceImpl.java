package cn.iocoder.yudao.module.lghjft.service.markerinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lghjft.dal.mysql.markerinfo.MarkerInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 * 高德地图标注点信息 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class MarkerInfoServiceImpl implements MarkerInfoService {

    @Resource
    private MarkerInfoMapper markerInfoMapper;

    @Override
    public Long createMarkerInfo(MarkerInfoSaveReqVO createReqVO) {
        // 插入
        MarkerInfoDO markerInfo = BeanUtils.toBean(createReqVO, MarkerInfoDO.class);
        markerInfoMapper.insert(markerInfo);

        // 返回
        return markerInfo.getId();
    }

    @Override
    public void updateMarkerInfo(MarkerInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateMarkerInfoExists(updateReqVO.getId());
        // 更新
        MarkerInfoDO updateObj = BeanUtils.toBean(updateReqVO, MarkerInfoDO.class);
        markerInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteMarkerInfo(Long id) {
        // 校验存在
        validateMarkerInfoExists(id);
        // 删除
        markerInfoMapper.deleteById(id);
    }

    @Override
        public void deleteMarkerInfoListByIds(List<Long> ids) {
        // 删除
        markerInfoMapper.deleteByIds(ids);
        }


    private void validateMarkerInfoExists(Long id) {
        if (markerInfoMapper.selectById(id) == null) {
            throw exception(MARKER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MarkerInfoDO getMarkerInfo(Long id) {
        return markerInfoMapper.selectById(id);
    }

    @Override
    public PageResult<MarkerInfoDO> getMarkerInfoPage(MarkerInfoPageReqVO pageReqVO) {
        return markerInfoMapper.selectPage(pageReqVO);
    }

}
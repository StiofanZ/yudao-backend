package cn.iocoder.yudao.module.lghjft.service.markerinfo;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
        // 如果传了 id → 走 grade 联动查询
        if (pageReqVO.getId() != null) {
            return getMarkerInfoByGradeRule(pageReqVO);
        }
        return markerInfoMapper.selectPage(pageReqVO);
    }


//查询标记点周边数据
    private PageResult<MarkerInfoDO> getMarkerInfoByGradeRule(MarkerInfoPageReqVO reqVO) {
        MarkerInfoDO current = markerInfoMapper.selectById(reqVO.getId());
        if (current == null || current.getGrade() == null) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }

        String grade = current.getGrade();
        LambdaQueryWrapperX<MarkerInfoDO> wrapper = new LambdaQueryWrapperX<>();

        if ("1".equals(grade)) {
            wrapper.eq(MarkerInfoDO::getSjxzqhDm, reqVO.getId());
        } else if ("0".equals(grade)) {
            if (current.getSjxzqhDm() != null) {
                wrapper.eq(MarkerInfoDO::getSjxzqhDm, current.getSjxzqhDm());
            } else {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
        } else if ("3".equals(grade)) {
            wrapper.eq(MarkerInfoDO::getSjxzqhDm, "620100");
        } else {
            return new PageResult<>(Collections.emptyList(), 0L);
        }

        return markerInfoMapper.selectPage(reqVO, wrapper);
    }


}
package cn.iocoder.yudao.module.lghjft.service.markerinfo;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
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
// 兰州市市级行政区划代码（Integer类型，匹配id字段类型）
    private static final Integer LANZHOU_CITY_CODE = 620100;
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
    @Override
    public List<MarkerInfoDO> getCountyData(Integer xzqhDm) {
        if (xzqhDm == null || xzqhDm <= 0) {
            return Collections.emptyList();
        }

        // 按 xzqhDm 查询
        LambdaQueryWrapperX<MarkerInfoDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.eq(MarkerInfoDO::getXzqhDm, xzqhDm);
        MarkerInfoDO current = markerInfoMapper.selectOne(wrapper);

        if (current == null) {
            return Collections.emptyList();
        }

        String grade = current.getGrade();
        Integer cityXzqhDm = null;

        if ("3".equals(grade)) {
            cityXzqhDm = 620100; // 省级 → 兰州市
        } else if ("1".equals(grade)) {
            cityXzqhDm = xzqhDm; // 市级
        } else if ("0".equals(grade)) {
            cityXzqhDm = (xzqhDm / 100) * 100; // 县级 → 推导市
        } else {
            return Collections.emptyList();
        }

        if (cityXzqhDm == null || cityXzqhDm <= 0) {
            return Collections.emptyList();
        }

        // 查询市级（按 xzqhDm）
        LambdaQueryWrapperX<MarkerInfoDO> cityWrapper = new LambdaQueryWrapperX<>();
        cityWrapper.eq(MarkerInfoDO::getXzqhDm, cityXzqhDm);
        MarkerInfoDO city = markerInfoMapper.selectOne(cityWrapper);

        List<MarkerInfoDO> result = new ArrayList<>();
        if (city != null) {
            result.add(city);
        }

        // 查询该市下所有县级：xzqhDm 范围 [cityXzqhDm + 1, cityXzqhDm + 99]
        List<MarkerInfoDO> counties = markerInfoMapper.selectList(
                new LambdaQueryWrapperX<MarkerInfoDO>()
                        .ge(MarkerInfoDO::getXzqhDm, cityXzqhDm + 1)
                        .le(MarkerInfoDO::getXzqhDm, cityXzqhDm + 99)
                        .eq(MarkerInfoDO::getGrade, "0")
        );
        result.addAll(counties);

        return result;
    }
}
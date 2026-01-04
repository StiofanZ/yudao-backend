package cn.iocoder.yudao.module.dm.service.xzqh;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.dm.controller.admin.xzqh.vo.*;
import cn.iocoder.yudao.module.dm.dal.dataobject.xzqh.XzqhDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.dm.dal.mysql.xzqh.XzqhMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.dm.enums.ErrorCodeConstants.*;

/**
 * 行政区划 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class XzqhServiceImpl implements XzqhService {

    @Resource
    private XzqhMapper xzqhMapper;

//    @Override
//    public Long createXzqh(XzqhSaveReqVO createReqVO) {
//        // 校验上级行政区划代码的有效性
//        validateParentXzqh(null, createReqVO.getSjxzqhDm());
//        // 校验行政区划名称的唯一性
//        validateXzqhXzqhmcUnique(null, createReqVO.getSjxzqhDm(), createReqVO.getXzqhmc());
//
//        // 插入
//        XzqhDO xzqh = BeanUtils.toBean(createReqVO, XzqhDO.class);
//        xzqhMapper.insert(xzqh);
//
//        // 返回
//        return xzqh.getId();
//    }
//
//    @Override
//    public void updateXzqh(XzqhSaveReqVO updateReqVO) {
//        // 校验存在
//        validateXzqhExists(updateReqVO.getId());
//        // 校验上级行政区划代码的有效性
//        validateParentXzqh(updateReqVO.getId(), updateReqVO.getSjxzqhDm());
//        // 校验行政区划名称的唯一性
//        validateXzqhXzqhmcUnique(updateReqVO.getId(), updateReqVO.getSjxzqhDm(), updateReqVO.getXzqhmc());
//
//        // 更新
//        XzqhDO updateObj = BeanUtils.toBean(updateReqVO, XzqhDO.class);
//        xzqhMapper.updateById(updateObj);
//    }
//
//    @Override
//    public void deleteXzqh(Long id) {
//        // 校验存在
//        validateXzqhExists(id);
//        // 校验是否有子行政区划
//        if (xzqhMapper.selectCountBySjxzqhDm(id) > 0) {
//            throw exception(XZQH_EXITS_CHILDREN);
//        }
//        // 删除
//        xzqhMapper.deleteById(id);
//    }
//
//
//    private void validateXzqhExists(Long id) {
//        if (xzqhMapper.selectById(id) == null) {
//            throw exception(XZQH_NOT_EXISTS);
//        }
//    }
//
//    private void validateParentXzqh(Long id, Long sjxzqhDm) {
//        if (sjxzqhDm == null || XzqhDO.SJXZQH_DM_ROOT.equals(sjxzqhDm)) {
//            return;
//        }
//        // 1. 不能设置自己为父行政区划
//        if (Objects.equals(id, sjxzqhDm)) {
//            throw exception(XZQH_PARENT_ERROR);
//        }
//        // 2. 父行政区划不存在
//        XzqhDO parentXzqh = xzqhMapper.selectById(sjxzqhDm);
//        if (parentXzqh == null) {
//            throw exception(XZQH_PARENT_NOT_EXITS);
//        }
//        // 3. 递归校验父行政区划，如果父行政区划是自己的子行政区划，则报错，避免形成环路
//        if (id == null) { // id 为空，说明新增，不需要考虑环路
//            return;
//        }
//        for (int i = 0; i < Short.MAX_VALUE; i++) {
//            // 3.1 校验环路
//            sjxzqhDm = parentXzqh.getSjxzqhDm();
//            if (Objects.equals(id, sjxzqhDm)) {
//                throw exception(XZQH_PARENT_IS_CHILD);
//            }
//            // 3.2 继续递归下一级父行政区划
//            if (sjxzqhDm == null || XzqhDO.SJXZQH_DM_ROOT.equals(sjxzqhDm)) {
//                break;
//            }
//            parentXzqh = xzqhMapper.selectById(sjxzqhDm);
//            if (parentXzqh == null) {
//                break;
//            }
//        }
//    }
//
//    private void validateXzqhXzqhmcUnique(Long id, Long sjxzqhDm, String xzqhmc) {
//        XzqhDO xzqh = xzqhMapper.selectBySjxzqhDmAndXzqhmc(sjxzqhDm, xzqhmc);
//        if (xzqh == null) {
//            return;
//        }
//        // 如果 id 为空，说明不用比较是否为相同 id 的行政区划
//        if (id == null) {
//            throw exception(XZQH_XZQHMC_DUPLICATE);
//        }
//        if (!Objects.equals(xzqh.getId(), id)) {
//            throw exception(XZQH_XZQHMC_DUPLICATE);
//        }
//    }
//
//    @Override
//    public XzqhDO getXzqh(Long id) {
//        return xzqhMapper.selectById(id);
//    }

    @Override
    public List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO) {
        return xzqhMapper.selectList(listReqVO);
    }

}
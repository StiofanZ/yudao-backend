package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfmx;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo.YhbfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfhz.YhbfhzMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfmx.YhbfmxMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
@Validated
@Slf4j
public class YhbfmxServiceImpl implements YhbfmxService {

    private static final String SYSTEM_USER = "SYSTEM";

    @Resource
    private YhbfmxMapper yhbfmxMapper;

    @Resource
    private YhbfhzMapper yhbfhzMapper;

    @Resource
    private AdminUserService userService;

    // ==================== 基础方法 ====================
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer createYhbfmx(YhbfmxSaveReqVO createReqVO) {
        YhbfmxDO yhbfmx = BeanUtil.toBean(createReqVO, YhbfmxDO.class);
        yhbfmxMapper.insert(yhbfmx);
        return yhbfmx.getBfid();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmx(YhbfmxSaveReqVO updateReqVO) {
        validateYhbfmxExists(updateReqVO.getBfid());
        YhbfmxDO updateObj = BeanUtil.toBean(updateReqVO, YhbfmxDO.class);
        yhbfmxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteYhbfmx(Integer id) {
        validateYhbfmxExists(id);
        yhbfmxMapper.deleteById(id);
    }

    @Override
    public YhbfmxDO getYhbfmx(Integer bfid) {
        return yhbfmxMapper.selectById(bfid);
    }

    @Override
    public PageResult<YhbfmxDO> getYhbfmxPage(YhbfmxPageReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            reqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(reqVO.getDeptId())) {
            reqVO.setDeptId(null);
        }
        Page<YhbfmxDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<YhbfmxDO> ipage = yhbfmxMapper.selectPage(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteYhbfmxListByIds(List<Integer> ids) {
        yhbfmxMapper.deleteByIds(ids);
    }

    // ==================== 业务方法 1:1 还原 V1 ====================

    /**
     * 结算文件生成银行拨付数据
     * 1:1 对应 V1 GhHkxxYhbfmxServiceImpl.updateGhyhbfmxJs
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxJs(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        if (StringUtils.isEmpty(reqVO.getHkpch())) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxJs(reqVO.getHkpch());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException(YHBFMX_NO_DATA);
        }
        List<List<YhbfmxDO>> partitions = splitList(list, 1000);
        try {
            for (List<YhbfmxDO> partition : partitions) {
                updateYxbjByPartition(partition);
                yhbfmxMapper.insertBatch(partition);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成银行拨付明细异常", e);
            throw new ServiceException(YHBFMX_NO_DATA);
        }
    }

    /**
     * 补结算文件生成银行拨付数据
     * 1:1 对应 V1 GhHkxxYhbfmxServiceImpl.updateGhyhbfmxBjs
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxBjs(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        if (StringUtils.isEmpty(reqVO.getHkpch())) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxBjs(reqVO.getHkpch());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException(YHBFMX_NO_DATA);
        }
        List<List<YhbfmxDO>> partitions = splitList(list, 1000);
        try {
            for (List<YhbfmxDO> partition : partitions) {
                updateYxbjByPartition(partition);
                yhbfmxMapper.insertBatch(partition);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成银行拨付明细异常", e);
            throw new ServiceException(YHBFMX_NO_DATA);
        }
    }

    /**
     * 失败退回重拨数据生成银行拨付数据
     * 1:1 对应 V1 GhHkxxYhbfmxServiceImpl.updateGhyhbfmxSbthcb
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxSbthcb(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        if (reqVO.getBeginUpdateTime() == null || reqVO.getEndUpdateTime() == null) {
            throw new ServiceException(YHBFMX_BEGIN_ERROR);
        }
        YhbfmxPageReqVO query = new YhbfmxPageReqVO();
        query.setBeginUpdateTime(reqVO.getBeginUpdateTime());
        query.setEndUpdateTime(reqVO.getEndUpdateTime());
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxSbthcb(query);
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException(YHBFMX_THCB_DATA);
        }
        List<List<YhbfmxDO>> partitions = splitList(list, 1000);
        try {
            for (List<YhbfmxDO> partition : partitions) {
                updateYxbjByPartition(partition);
                yhbfmxMapper.insertBatch(partition);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成银行拨付明细异常", e);
            throw new ServiceException(YHBFMX_NO_DATA);
        }
    }

    /**
     * 生成银行拨付汇总
     * 1:1 对应 V1 GhHkxxYhbfmxServiceImpl.updateGhHkxxYhbfhz
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfhz(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null || StringUtils.isEmpty(reqVO.getBfpch())) {
            throw exception(YHBFMX_NOT_EXISTS);
        }

        YhbfmxPageReqVO query = new YhbfmxPageReqVO();
        query.setBfpch(reqVO.getBfpch());
        query.setBflx(reqVO.getBflx());
        query.setFclx(reqVO.getFclx());
        // 部门逻辑
        String deptId = reqVO.getDeptId();
        if (StringUtils.isEmpty(deptId)) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            deptId = user.getDeptId().toString();
        }
        if ("100000".equals(deptId)) {
            deptId = null;
        }
        query.setDeptId(deptId);

        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmx(query);
        if (CollUtil.isEmpty(list)) {
            throw exception(YHBFMX_NOT_EXISTS);
        }

        // 校验是否已生成银行拨付汇总信息 (V1: bfhzpch 非空 = 已生成)
        List<YhbfmxDO> alreadyGenerated = list.stream()
                .filter(mx -> mx.getBfhzpch() != null && !mx.getBfhzpch().isEmpty())
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(alreadyGenerated)) {
            throw new ServiceException(YHBFMX_HAS_GENERATED);
        }

        List<List<YhbfmxDO>> partitions = splitList(list, 1000);
        try {
            List<YhbfhzDO> hzList = new ArrayList<>();
            String bfhzpch = null;
            for (int i = 0; i < partitions.size(); i++) {
                List<YhbfmxDO> partition = partitions.get(i);
                if (CollUtil.isEmpty(partition)) {
                    continue;
                }
                log.debug("第 i={} 批次 ，bfhzpch={}", i + 1, bfhzpch);
                bfhzpch = getBfhzpch(i + 1, bfhzpch);
                String finalBfhzpch = bfhzpch;
                partition.forEach(item -> {
                    item.setBfhzpch(finalBfhzpch);
                    item.setBfzt("N");
                    item.setUpdateBy(SYSTEM_USER);
                });

                // 构建汇总信息 (1:1 V1 getGhHkxxYhbfhz)
                YhbfhzDO hz = new YhbfhzDO();
                BeanUtil.copyProperties(partition.get(0), hz);
                hz.setBfhzpch(finalBfhzpch);
                hz.setBfidq(partition.stream().min(Comparator.comparing(YhbfmxDO::getBfid)).get().getBfid().longValue());
                hz.setBfidz(partition.stream().max(Comparator.comparing(YhbfmxDO::getBfid)).get().getBfid().longValue());
                hz.setBfidStr(partition.stream().map(YhbfmxDO::getBfid).distinct()
                        .map(String::valueOf).collect(Collectors.joining(",")));
                hz.setBs((long) partition.size());
                BigDecimal hzje = BigDecimal.ZERO;
                for (YhbfmxDO mx : partition) {
                    hzje = hzje.add(mx.getJe());
                }
                hz.setHzje(hzje);
                hzList.add(hz);
            }
            // 写入汇总信息
            if (CollUtil.isNotEmpty(hzList)) {
                yhbfhzMapper.insertBatchYhbfhzs(hzList);
            }
            // 更新拨付明细的汇总批次号
            for (List<YhbfmxDO> partition : partitions) {
                yhbfmxMapper.updateBatchYhbfmx(partition);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成银行拨付汇总异常", e);
            throw new ServiceException(YHBFMX_NO_DATA);
        }
    }

    // ==================== 工具方法 ====================

    /**
     * 更新有效标记为N (1:1 V1 updateYxbjByGhHkxxYhbfmx)
     * 从 partition 第一条记录取 hkpch 和 updateBy
     */
    private void updateYxbjByPartition(List<YhbfmxDO> partition) {
        YhbfmxDO first = partition.get(0);
        yhbfmxMapper.updateYxbjByHkpch(first.getHkpch(), "N", first.getUpdateBy());
    }

    private <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            int end = Math.min(i + size, list.size());
            result.add(list.subList(i, end));
        }
        return result;
    }

    /**
     * 拨付汇总批次号 (1:1 V1 getBfhzpch)
     * 第一批通过 DB 查询最大批次号并自增
     * 后续批次在前一批基础上递增
     */
    private synchronized String getBfhzpch(int i, String scbfhzpch) {
        String bfhzpch;
        if (i == 1) {
            String dqrq = DateUtil.format(new Date(), "yyyyMMdd");
            bfhzpch = yhbfhzMapper.selectBfhzpch(dqrq);
            if (bfhzpch == null) {
                bfhzpch = dqrq + "00001";
            } else {
                Long bfhzpchl = Long.parseLong(bfhzpch);
                bfhzpchl++;
                bfhzpch = String.valueOf(bfhzpchl);
            }
        } else {
            bfhzpch = scbfhzpch.substring(0, scbfhzpch.length() - (String.valueOf(i).length())) + i;
        }
        log.debug("拨付汇总批次号 scbfhzpch={},bfhzpch={}", scbfhzpch, bfhzpch);
        return bfhzpch;
    }

    private void validateYhbfmxExists(Integer id) {
        if (yhbfmxMapper.selectById(id) == null) {
            throw exception(YHBFMX_NOT_EXISTS);
        }
    }
}

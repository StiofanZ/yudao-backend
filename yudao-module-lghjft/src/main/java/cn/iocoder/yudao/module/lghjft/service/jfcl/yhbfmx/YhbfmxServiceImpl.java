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
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
@Validated
@Slf4j
public class YhbfmxServiceImpl implements YhbfmxService {

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
//获取详情
    @Override
    public YhbfmxDO getYhbfmx(Integer bfid) {
        return yhbfmxMapper.selectById(bfid);
    }
//分页数据
@Override
public PageResult<YhbfmxDO> getYhbfmxPage(YhbfmxPageReqVO reqVO) {
    // 你的原有部门逻辑（保留）
    if (StringUtils.isEmpty(reqVO.getDeptId())) {
        AdminUserDO user = userService.getUser(getLoginUserId());
        reqVO.setDeptId(user.getDeptId().toString());
    }
    if ("100000".equals(reqVO.getDeptId())) {
        reqVO.setDeptId(null);
    }
    Page<YhbfmxDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

    IPage<YhbfmxDO> ipage = yhbfmxMapper.selectPage(page, reqVO); // 关键！
    return new PageResult<>(ipage.getRecords(), ipage.getTotal());
}
//批量删除
@Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteYhbfmxListByIds(List<Integer> ids) {
        yhbfmxMapper.deleteByIds(ids);
    }

    // ==================== 业务方法 1:1 还原 ====================

    /**
     * 结算文件生成银行拨付数据
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxJs(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null || reqVO.getHkpch() == null || reqVO.getHkpch().isEmpty()) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxJs(reqVO.getHkpch());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException();
        }
        yhbfmxMapper.updateYxbjByHkpch(reqVO.getHkpch(), "N");
        yhbfmxMapper.insertBatch(list);
    }

    /**
     * 补结算文件生成银行拨付数据
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxBjs(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null || reqVO.getHkpch() == null) {
            throw new ServiceException(YHBFMX_PARAM_ERROR);
        }
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxBjs(reqVO.getHkpch());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException(YHBFMX_NO_DATA);
        }
        yhbfmxMapper.updateYxbjByHkpch(reqVO.getHkpch(), "N");
        yhbfmxMapper.insertBatch(list);
    }

    /**
     * 失败退回重拨数据生成银行拨付数据
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfmxSbthcb(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null || reqVO.getBeginUpdateTime() == null || reqVO.getEndUpdateTime() == null) {
            throw new ServiceException(YHBFMX_BEGIN_ERROR);
        }
        YhbfmxPageReqVO query = new YhbfmxPageReqVO();
        query.setBeginUpdateTime(reqVO.getBeginUpdateTime());
        query.setEndUpdateTime(reqVO.getEndUpdateTime());
        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmxSbthcb(query);
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException(YHBFMX_THCB_DATA);
        }
        yhbfmxMapper.updateYxbjByHkpch(reqVO.getHkpch(), "N");
        yhbfmxMapper.insertBatch(list);
    }


    /**
     * 生成银行拨付汇总
     * @param
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateYhbfhz(YhbfmxSaveReqVO reqVO) {
        if (reqVO == null || reqVO.getBfpch() == null) {
            throw exception(YHBFMX_NOT_EXISTS);
        }

        YhbfmxPageReqVO query = new YhbfmxPageReqVO();
        query.setBfpch(reqVO.getBfpch());
        query.setDeptId(reqVO.getDeptId());

        List<YhbfmxDO> list = yhbfmxMapper.getYhbfmx(query);
        if (CollUtil.isEmpty(list)) {
            throw exception(YHBFMX_NOT_EXISTS);
        }

        // 检查是否已汇总
        boolean hasBfhz = list.stream().anyMatch(o -> o.getBfhzpch() != null);
        if (hasBfhz) {
            throw new ServiceException(YHBFMX_HAS_GENERATED);
        }

        List<List<YhbfmxDO>> partitions = splitList(list, 1000);
        List<YhbfhzDO> hzList = new ArrayList<>();
        String bfhzpch = null;

        for (int i = 0; i < partitions.size(); i++) {
            List<YhbfmxDO> part = partitions.get(i);
            bfhzpch = getBfhzpch(i + 1, bfhzpch);
            String finalBfhzpch = bfhzpch;

            part.forEach(o -> {
                o.setBfhzpch(finalBfhzpch);
                o.setBfzt("N");
            });

            YhbfhzDO hz = new YhbfhzDO();
            BeanUtil.copyProperties(part.get(0), hz);
            hz.setBfhzpch(bfhzpch);
            hz.setHzje(part.stream().map(YhbfmxDO::getJe).reduce(BigDecimal.ZERO, BigDecimal::add));
            hz.setBs((long) part.size());
            hzList.add(hz);
        }

        yhbfhzMapper.insertBatch(hzList);
        yhbfmxMapper.updateBatchYhbfmx(list);
    }

    // ==================== 工具方法 ====================
    private <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            int end = Math.min(i + size, list.size());
            result.add(list.subList(i, end));
        }
        return result;
    }

    /**
     * 拨付汇总批次号
     * @param i
     * @return
     */
    private String getBfhzpch(int i, String old) {
        if (i == 1) {
            String date = DateUtil.format(new Date(), "yyyyMMdd");
            return date + "00001";
        } else {
            return old.substring(0, old.length() - 1) + i;
        }
    }

    private void validateYhbfmxExists(Integer id) {
        if (yhbfmxMapper.selectById(id) == null) {
            throw exception(YHBFMX_NOT_EXISTS);
        }
    }
}
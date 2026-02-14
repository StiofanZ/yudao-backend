package cn.iocoder.yudao.module.lghjft.service.hjgl.bqgl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhDmHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhHjBqxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.bqgl.GhDmHjBqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.bqgl.GhHjBqxxMapper;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

/**
 * 标签管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BqglServiceImpl implements BqglService {

    @Resource
    private GhDmHjBqMapper ghDmHjBqMapper;

    @Resource
    private GhHjBqxxMapper ghHjBqxxMapper;

    @Resource
    private DeptApi deptApi;

    @Override
    public List<BqglRespVO> getBqxx(Long rootId) {
        return ghDmHjBqMapper.selectBqxxList(rootId);
    }

    @Override
    public String createBqdm(BqglCreateReqVO createReqVO) {
        return createBqxx(createReqVO);
    }

    @Override
    public String createBqxx(BqglCreateReqVO createReqVO) {
        Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
        // 校验唯一性
        validateBqglUnique(null, createReqVO.getBqMc(), deptId);

        // 生成ID: deptId(6位补0) + yyyyMMddHHmmss(14位)
        String id = String.format("%06d%s", deptId, DateUtil.format(new Date(), "yyyyMMddHHmmss"));

        GhDmHjBqDO bqDO = GhDmHjBqDO.builder()
                .id(id)
                .bqMc(createReqVO.getBqMc())
                .deptId(deptId)
                .build();

        ghDmHjBqMapper.insert(bqDO);
        return id;
    }

    @Override
    public void updateBqdm(BqglUpdateReqVO updateReqVO) {
        // 校验存在
        GhDmHjBqDO bqDO = validateBqglExists(updateReqVO.getId());

        // 校验权限：只能修改当前部门
        Long currentDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (bqDO.getDeptId() != null && !bqDO.getDeptId().equals(currentDeptId)) {
            throw exception(BQGL_BQ_NOT_EXISTS); // 权限不足视为不存在
        }

        // 校验唯一性
        validateBqglUnique(updateReqVO.getId(), updateReqVO.getBqMc(), bqDO.getDeptId());

        GhDmHjBqDO updateObj = BeanUtils.toBean(updateReqVO, GhDmHjBqDO.class);
        ghDmHjBqMapper.updateById(updateObj);
    }

    @Override
    public void deleteBqdm(String id) {
        deleteBqxx(id);
    }

    @Override
    public void deleteBqxx(String id) {
        // 校验存在
        GhDmHjBqDO bqDO = validateBqglExists(id);

        // 校验权限：只能删除当前部门
        Long currentDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (bqDO.getDeptId() != null && !bqDO.getDeptId().equals(currentDeptId)) {
            throw exception(BQGL_BQ_NOT_EXISTS);
        }

        // 校验是否存在有效数据
        Long count = ghHjBqxxMapper.selectCount(new LambdaQueryWrapperX<GhHjBqxxDO>()
                .eq(GhHjBqxxDO::getBqId, id)
                .ge(GhHjBqxxDO::getYxqz, LocalDate.now()));
        if (count > 0) {
            throw exception(BQ_HAS_VALID_DATA);
        }
        ghDmHjBqMapper.deleteById(id);
    }

    @Override
    public GhDmHjBqDO getBqdm(String id) {
        return ghDmHjBqMapper.selectById(id);
    }

    @Override
    public PageResult<BqglRespVO> listBqdm(BqglPageReqVO pageReqVO) {
        Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
        Set<Long> deptIds = new HashSet<>();
        deptIds.add(deptId);

        // 获取上级部门
        try {
            DeptRespDTO dept = deptApi.getDept(deptId);
            while (dept != null && dept.getParentId() != null && dept.getParentId() != 0) {
                Long parentId = dept.getParentId();
                deptIds.add(parentId);
                dept = deptApi.getDept(parentId);
            }
        } catch (Exception e) {
            // 忽略 API 调用异常，降级为仅查询当前部门
        }

        PageResult<GhDmHjBqDO> pageResult = ghDmHjBqMapper.selectPage(pageReqVO, deptIds);
        return BeanUtils.toBean(pageResult, BqglRespVO.class);
    }

    @Override
    public PageResult<BqglHjxxRespVO> listHjxx(BqglHjxxPageReqVO pageReqVO, Long deptId) {
        return ghHjBqxxMapper.getHjxxPage(pageReqVO, deptId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHjxx(BqglHjxxSaveReqVO saveReqVO) {
        if (Boolean.TRUE.equals(saveReqVO.getDeleted())) {
            // 取消标记：更新有效期止为当前前一天
            ghHjBqxxMapper.update(null, new LambdaUpdateWrapper<GhHjBqxxDO>()
                    .eq(GhHjBqxxDO::getBqId, saveReqVO.getBqDm())
                    .in(GhHjBqxxDO::getDjxh, saveReqVO.getDjxhList())
                    .ge(GhHjBqxxDO::getYxqz, LocalDate.now())
                    .set(GhHjBqxxDO::getYxqz, LocalDate.now().minusDays(1))
                    .set(GhHjBqxxDO::getDeleted, true));
            return;
        }

        // 1. 将最近失效（yxqz=昨天）的记录恢复
        ghHjBqxxMapper.update(null, new LambdaUpdateWrapper<GhHjBqxxDO>()
                .eq(GhHjBqxxDO::getBqId, saveReqVO.getBqDm())
                .in(GhHjBqxxDO::getDjxh, saveReqVO.getDjxhList())
                .eq(GhHjBqxxDO::getYxqz, LocalDate.now().minusDays(1))
                .set(GhHjBqxxDO::getDeleted, false)
                .set(GhHjBqxxDO::getYxqz, LocalDate.of(9999, 12, 31)));

        // 2. 找出当前有效的记录（yxqz >= 今天）
        List<GhHjBqxxDO> validList = ghHjBqxxMapper.selectList(new LambdaQueryWrapperX<GhHjBqxxDO>()
                .eq(GhHjBqxxDO::getBqId, saveReqVO.getBqDm())
                .in(GhHjBqxxDO::getDjxh, saveReqVO.getDjxhList())
                .ge(GhHjBqxxDO::getYxqz, LocalDate.now()));

        List<String> validDjxhs = validList.stream()
                .map(GhHjBqxxDO::getDjxh)
                .collect(Collectors.toList());

        List<String> needInsertDjxhs = saveReqVO.getDjxhList().stream()
                .filter(djxh -> !validDjxhs.contains(djxh))
                .collect(Collectors.toList());

        if (CollUtil.isNotEmpty(needInsertDjxhs)) {
            // 3. 批量新增
            LocalDate now = LocalDate.now();
            LocalDate maxDate = LocalDate.of(9999, 12, 31);

            List<GhHjBqxxDO> insertList = needInsertDjxhs.stream().map(djxh -> GhHjBqxxDO.builder()
                    .bqId(saveReqVO.getBqDm())
                    .djxh(djxh)
                    .yxqq(now)
                    .yxqz(maxDate)
                    .build()).collect(Collectors.toList());

            ghHjBqxxMapper.insertBatch(insertList);
        }
    }

    @Override
    public List<GhHjBqxxDO> getHjxx(String djxh) {
        return ghHjBqxxMapper.selectList(GhHjBqxxDO::getDjxh, djxh);
    }

    private GhDmHjBqDO validateBqglExists(String id) {
        GhDmHjBqDO bqgl = ghDmHjBqMapper.selectById(id);
        if (bqgl == null) {
            throw exception(BQGL_BQ_NOT_EXISTS);
        }
        return bqgl;
    }

    private void validateBqglUnique(String id, String bqMc, Long deptId) {
        GhDmHjBqDO exists = ghDmHjBqMapper.selectOne(new LambdaQueryWrapperX<GhDmHjBqDO>()
                .eq(GhDmHjBqDO::getBqMc, bqMc)
                .eq(GhDmHjBqDO::getDeptId, deptId)
                .neIfPresent(GhDmHjBqDO::getId, id));
        if (exists != null) {
            throw exception(BQGL_BQ_EXISTS);
        }
    }
}

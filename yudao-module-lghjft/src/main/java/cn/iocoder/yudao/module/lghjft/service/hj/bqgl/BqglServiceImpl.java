package cn.iocoder.yudao.module.lghjft.service.hj.bqgl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl.GhDmHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl.GhHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.GhDmHjBqMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.GhHjBqMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

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
    private GhHjBqMapper ghHjBqMapper;

    @Override
    public List<BqglRespVO> getBqxx(Long rootId) {
        return ghDmHjBqMapper.selectBqxxList(rootId);
    }

    @Override
    public String createBqxx(BqglCreateReqVO createReqVO) {
        Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
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
    public void deleteBqxx(String id) {
        ghDmHjBqMapper.deleteById(id);
    }

    @Override
    public PageResult<BqglHjxxRespVO> getHjxxPage(BqglHjxxPageReqVO pageReqVO, Long deptId) {
        return ghHjBqMapper.getHjxxPage(pageReqVO, deptId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHjxx(BqglHjxxSaveReqVO saveReqVO) {
        if (Boolean.TRUE.equals(saveReqVO.getDeleted())) {
            // 取消标记：更新有效期止为当前前一天
            // 注意：应只取消当前有效的（yxqz >= 今天）的记录，避免误修改历史记录
            ghHjBqMapper.update(null, new LambdaUpdateWrapper<GhHjBqDO>()
                    .eq(GhHjBqDO::getBqId, saveReqVO.getBqDm())
                    .in(GhHjBqDO::getDjxh, saveReqVO.getDjxhList())
                    .ge(GhHjBqDO::getYxqz, LocalDate.now()) // 增加条件：只修改当前有效的
                    .set(GhHjBqDO::getYxqz, LocalDate.now().minusDays(1))
                    .set(GhHjBqDO::getDeleted, true)); // 同步修改 deleted 标志
            return;
        }

        // 1. 查询已存在的标签关联（包括逻辑删除的）
        // 注意：MyBatis Plus 的 selectList 默认会加上 deleted=0，所以这里需要手动处理或直接更新
        // 为了简化，我们先查询所有关联记录（无论是否删除）
        // 由于 MP 的逻辑删除机制，我们这里直接尝试恢复已删除的记录，或插入新记录
        
        // 方案：先查询库中已存在的记录（不区分是否删除，需要自定义SQL或暂时先查出来处理）
        // 但由于 MP 默认屏蔽已删除数据，我们最好是用 update 语句将 deleted=0 更新回去
        
        // 步骤：
        // 1. 将最近失效（yxqz=昨天）的记录恢复
        ghHjBqMapper.update(null, new LambdaUpdateWrapper<GhHjBqDO>()
                .eq(GhHjBqDO::getBqId, saveReqVO.getBqDm())
                .in(GhHjBqDO::getDjxh, saveReqVO.getDjxhList())
                .eq(GhHjBqDO::getYxqz, LocalDate.now().minusDays(1))
                .set(GhHjBqDO::getDeleted, false)
                .set(GhHjBqDO::getYxqz, LocalDate.of(9999, 12, 31))); // 恢复标记时，重置有效期止

        // 2. 找出当前有效的记录（yxqz >= 今天）
        // 如果有更早失效的记录（yxqz < 昨天），它们不会被上面的 update 恢复，也不会被下面的 select 查出来（如果用了 deleted=0 且 yxqz >= today）
        // 从而会进入新增逻辑
        List<GhHjBqDO> validList = ghHjBqMapper.selectList(new LambdaQueryWrapper<GhHjBqDO>()
                .eq(GhHjBqDO::getBqId, saveReqVO.getBqDm())
                .in(GhHjBqDO::getDjxh, saveReqVO.getDjxhList())
                .ge(GhHjBqDO::getYxqz, LocalDate.now()));
        
        List<String> validDjxhs = validList.stream()
                .map(GhHjBqDO::getDjxh)
                .collect(Collectors.toList());
        
        List<String> needInsertDjxhs = saveReqVO.getDjxhList().stream()
                .filter(djxh -> !validDjxhs.contains(djxh))
                .collect(Collectors.toList());

        if (CollUtil.isNotEmpty(needInsertDjxhs)) {
            // 3. 批量新增
            LocalDate now = LocalDate.now();
            LocalDate maxDate = LocalDate.of(9999, 12, 31);
            
            List<GhHjBqDO> insertList = needInsertDjxhs.stream().map(djxh -> GhHjBqDO.builder()
                    .bqId(saveReqVO.getBqDm())
                    .djxh(djxh)
                    .yxqq(now)
                    .yxqz(maxDate)
                    .build()).collect(Collectors.toList());
            
            ghHjBqMapper.insertBatch(insertList);
        }
    }
}

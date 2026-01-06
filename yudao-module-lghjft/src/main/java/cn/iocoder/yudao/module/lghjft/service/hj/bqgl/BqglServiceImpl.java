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
            // 取消标记：逻辑删除
            ghHjBqMapper.delete(new LambdaQueryWrapper<GhHjBqDO>()
                    .eq(GhHjBqDO::getBqId, saveReqVO.getBqDm())
                    .in(GhHjBqDO::getDjxh, saveReqVO.getDjxhList()));
            return;
        }

        // 1. 查询已存在的标签关联
        List<GhHjBqDO> existsList = ghHjBqMapper.selectList(new LambdaQueryWrapper<GhHjBqDO>()
                .eq(GhHjBqDO::getBqId, saveReqVO.getBqDm())
                .in(GhHjBqDO::getDjxh, saveReqVO.getDjxhList()));
        
        // 2. 过滤出需要新增的 djxh
        List<String> existDjxhs = existsList.stream()
                .map(GhHjBqDO::getDjxh)
                .collect(Collectors.toList());
        
        List<String> needInsertDjxhs = saveReqVO.getDjxhList().stream()
                .filter(djxh -> !existDjxhs.contains(djxh))
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(needInsertDjxhs)) {
            return;
        }

        // 3. 批量新增
        List<GhHjBqDO> insertList = needInsertDjxhs.stream().map(djxh -> GhHjBqDO.builder()
                .bqId(saveReqVO.getBqDm())
                .djxh(djxh)
                .build()).collect(Collectors.toList());
        
        ghHjBqMapper.insertBatch(insertList);
    }
}

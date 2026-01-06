package cn.iocoder.yudao.module.lghjft.service.hj.bqgl;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl.GhDmHjBqDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.GhDmHjBqMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;

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
}

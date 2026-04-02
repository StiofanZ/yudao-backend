package cn.iocoder.yudao.module.lghjft.service.jfcl.GhHkxxYhbfhzJkjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;

import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.GhHkxxYhbfhzJkjl.GhHkxxYhbfhzJkjlMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GhHkxxYhbfhzJkjlServiceImpl implements IGhHkxxYhbfhzJkjlService {

    @Resource
    private GhHkxxYhbfhzJkjlMapper ghHkxxYhbfhzJkjlMapper;

    // ===================== 完全保留你原来的方法 =====================
    @Override
    public GhHkxxYhbfhzJkjl selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId) {
        return ghHkxxYhbfhzJkjlMapper.selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
    }
//分页查询
    @Override
    public PageResult<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlPage(GhHkxxYhbfhzJkjlPageReqVO reqVO) {
        Page<GhHkxxYhbfhzJkjl> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<GhHkxxYhbfhzJkjl> ipage = ghHkxxYhbfhzJkjlMapper.selectGhHkxxYhbfhzJkjlList(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl) {
        return ghHkxxYhbfhzJkjlMapper.insertGhHkxxYhbfhzJkjl(ghHkxxYhbfhzJkjl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl) {
        return ghHkxxYhbfhzJkjlMapper.updateGhHkxxYhbfhzJkjl(ghHkxxYhbfhzJkjl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds) {
        return ghHkxxYhbfhzJkjlMapper.deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(yhbfhzJkjlIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId) {
        return ghHkxxYhbfhzJkjlMapper.deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
    }

    // ===================== 只增加适配VO的方法（不影响旧功能） =====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjlSaveReqVO createReqVO) {
        GhHkxxYhbfhzJkjl entity = BeanUtils.toBean(createReqVO, GhHkxxYhbfhzJkjl.class);
        insertGhHkxxYhbfhzJkjl(entity);
        return entity.getYhbfhzJkjlId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjlSaveReqVO updateReqVO) {
        GhHkxxYhbfhzJkjl entity = BeanUtils.toBean(updateReqVO, GhHkxxYhbfhzJkjl.class);
        updateGhHkxxYhbfhzJkjl(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(List<Long> ids) {
        deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(ids.toArray(new Long[0]));
    }


    // ===================== 适配导出 =====================
    @Override
    public List<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(GhHkxxYhbfhzJkjlPageReqVO reqVO) {
        return selectGhHkxxYhbfhzJkjlPage(reqVO).getList();
    }

}
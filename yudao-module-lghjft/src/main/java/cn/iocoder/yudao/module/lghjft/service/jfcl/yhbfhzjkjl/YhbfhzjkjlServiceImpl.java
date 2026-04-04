package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhzjkjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhzjkjl.YhbfhzjkjlDO;

import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfhzjkjl.YhbfhzjkjlMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YhbfhzjkjlServiceImpl implements YhbfhzjkjlService {

    @Resource
    private YhbfhzjkjlMapper yhbfhzjkjlMapper;

    // ===================== 完全保留你原来的方法 =====================
    @Override
    public YhbfhzjkjlDO selectYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId) {
        return yhbfhzjkjlMapper.selectYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
    }

    //分页查询
    @Override
    public PageResult<YhbfhzjkjlDO> selectYhbfhzJkjlPage(YhbfhzjkjlPageReqVO reqVO) {
        Page<YhbfhzjkjlDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<YhbfhzjkjlDO> ipage = yhbfhzjkjlMapper.selectYhbfhzJkjlList(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO) {
        return yhbfhzjkjlMapper.insertYhbfhzJkjl(yhbfhzjkjlDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO) {
        return yhbfhzjkjlMapper.updateYhbfhzJkjl(yhbfhzjkjlDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds) {
        return yhbfhzjkjlMapper.deleteYhbfhzJkjlByYhbfhzJkjlIds(yhbfhzJkjlIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId) {
        return yhbfhzjkjlMapper.deleteYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
    }

    // ===================== 只增加适配VO的方法（不影响旧功能） =====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertYhbfhzJkjl(YhbfhzjkjlSaveReqVO createReqVO) {
        YhbfhzjkjlDO entity = BeanUtils.toBean(createReqVO, YhbfhzjkjlDO.class);
        insertYhbfhzJkjl(entity);
        return entity.getYhbfhzJkjlId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateYhbfhzJkjl(YhbfhzjkjlSaveReqVO updateReqVO) {
        YhbfhzjkjlDO entity = BeanUtils.toBean(updateReqVO, YhbfhzjkjlDO.class);
        updateYhbfhzJkjl(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteYhbfhzJkjlByYhbfhzJkjlIds(List<Long> ids) {
        deleteYhbfhzJkjlByYhbfhzJkjlIds(ids.toArray(new Long[0]));
    }


    // ===================== 适配导出 =====================
    @Override
    public List<YhbfhzjkjlDO> selectYhbfhzJkjlList(YhbfhzjkjlPageReqVO reqVO) {
        return selectYhbfhzJkjlPage(reqVO).getList();
    }

}

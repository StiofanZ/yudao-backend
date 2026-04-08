package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfjgcx.YhbfjgcxMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 银行拨付结果查询 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class YhbfjgcxServiceImpl implements YhbfjgcxService {

    @Resource
    private YhbfjgcxMapper yhbfjgcxMapper;

    @Override
    public YhbfjgcxDO selectYhbfjgcxByBfpch(String bfpch) {
        return yhbfjgcxMapper.selectYhbfjgcxByBfpch(bfpch);
    }

    @Override
    public PageResult<YhbfjgcxDO> selectYhbfjgcxList(YhbfjgcxPageReqVO reqVO) {
        Page<YhbfjgcxDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<YhbfjgcxDO> ipage = yhbfjgcxMapper.selectYhbfjgcxList(page, reqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertYhbfjgcx(YhbfjgcxDO yhbfjgcx) {
        return yhbfjgcxMapper.insertYhbfjgcx(yhbfjgcx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateYhbfjgcx(YhbfjgcxDO yhbfjgcx) {
        return yhbfjgcxMapper.updateYhbfjgcx(yhbfjgcx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfjgcxByBfpchs(String[] bfpchs) {
        return yhbfjgcxMapper.deleteYhbfjgcxByBfpchs(bfpchs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteYhbfjgcxByBfpch(String bfpch) {
        return yhbfjgcxMapper.deleteYhbfjgcxByBfpch(bfpch);
    }

}

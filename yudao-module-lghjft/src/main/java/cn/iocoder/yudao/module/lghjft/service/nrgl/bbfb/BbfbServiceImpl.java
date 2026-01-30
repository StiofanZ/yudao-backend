package cn.iocoder.yudao.module.lghjft.service.nrgl.bbfb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bbfb.BbfbMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 版本发布 Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class BbfbServiceImpl implements BbfbService {

    @Resource
    private BbfbMapper bbfbMapper;

    @Override
    public Long createBbfb(BbfbCreateReqVO createReqVO) {
        BbfbDO bbfb = BeanUtils.toBean(createReqVO, BbfbDO.class);
        // 默认状态为草稿 (0)
        bbfb.setStatus(0);
        // 默认阅读量 0
        bbfb.setReadCount(0);
        bbfbMapper.insert(bbfb);
        return bbfb.getId();
    }

    @Override
    public void updateBbfb(BbfbUpdateReqVO updateReqVO) {
        // 校验存在
        validateBbfbExists(updateReqVO.getId());
        // 更新
        BbfbDO updateObj = BeanUtils.toBean(updateReqVO, BbfbDO.class);
        bbfbMapper.updateById(updateObj);
    }

    @Override
    public void deleteBbfb(Long id) {
        // 校验存在
        validateBbfbExists(id);
        // 删除
        bbfbMapper.deleteById(id);
    }

    private void validateBbfbExists(Long id) {
        if (bbfbMapper.selectById(id) == null) {
            throw exception(new cn.iocoder.yudao.framework.common.exception.ErrorCode(404, "版本发布不存在"));
        }
    }

    @Override
    public BbfbDO getBbfb(Long id) {
        BbfbDO bbfb = bbfbMapper.selectById(id);
        if (bbfb != null) {
            // 增加阅读量
            BbfbDO updateObj = new BbfbDO();
            updateObj.setId(id);
            updateObj.setReadCount(bbfb.getReadCount() == null ? 1 : bbfb.getReadCount() + 1);
            bbfbMapper.updateById(updateObj);
            bbfb.setReadCount(updateObj.getReadCount());
        }
        return bbfb;
    }

    @Override
    public PageResult<BbfbDO> getBbfbPage(BbfbReqVO listReqVO) {
        // 使用自定义的分页查询（带排名）
        IPage<BbfbDO> page = MyBatisUtils.buildPage(listReqVO);
        bbfbMapper.selectPageWithRank(page, listReqVO);
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void publish(Long id) {
        validateBbfbExists(id);
        BbfbDO updateObj = new BbfbDO();
        updateObj.setId(id);
        updateObj.setStatus(1); // 已发布
        updateObj.setFbsj(LocalDate.now());
        bbfbMapper.updateById(updateObj);
    }

    @Override
    public List<BbfbDO> getPublicBbfbList() {
        // 查询已发布(1)的内容
        return bbfbMapper.selectList(new LambdaQueryWrapper<BbfbDO>()
                .eq(BbfbDO::getStatus, 1)
                .orderByDesc(BbfbDO::getFbsj)
                .orderByDesc(BbfbDO::getId));
    }

}

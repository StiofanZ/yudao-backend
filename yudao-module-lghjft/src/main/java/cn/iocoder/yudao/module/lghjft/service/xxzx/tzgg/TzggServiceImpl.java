package cn.iocoder.yudao.module.lghjft.service.xxzx.tzgg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.tzgg.vo.TzggSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.tzgg.TzggDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.tzgg.TzggMapper;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;

/**
 * 通知公告 Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class TzggServiceImpl implements TzggService {

    @Resource
    private TzggMapper tzggMapper;

    @Override
    public Long createTzgg(TzggSaveReqVO createReqVO) {
        TzggDO notice = BeanUtils.toBean(createReqVO, TzggDO.class);
        tzggMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateTzgg(TzggSaveReqVO updateReqVO) {
        // 校验是否存在
        validateTzggExists(updateReqVO.getId());
        // 更新通知公告
        TzggDO updateObj = BeanUtils.toBean(updateReqVO, TzggDO.class);
        tzggMapper.updateById(updateObj);
    }

    @Override
    public void deleteTzgg(Long id) {
        // 校验是否存在
        validateTzggExists(id);
        // 删除通知公告
        tzggMapper.deleteById(id);
    }

    @Override
    public void deleteTzggList(List<Long> ids) {
        tzggMapper.deleteByIds(ids);
    }

    @Override
    public PageResult<TzggDO> getTzggPage(TzggPageReqVO reqVO) {
        return tzggMapper.selectPage(reqVO);
    }

    @Override
    public TzggDO getTzgg(Long id) {
        return tzggMapper.selectById(id);
    }

    @VisibleForTesting
    public void validateTzggExists(Long id) {
        if (id == null) {
            return;
        }
        TzggDO notice = tzggMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }

}

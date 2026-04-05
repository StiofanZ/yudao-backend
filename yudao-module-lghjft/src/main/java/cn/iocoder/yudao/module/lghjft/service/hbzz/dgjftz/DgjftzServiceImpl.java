package cn.iocoder.yudao.module.lghjft.service.hbzz.dgjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.dgjftz.DgjftzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class DgjftzServiceImpl implements DgjftzService {

    @Resource
    private DgjftzMapper dgjftzMapper;

    @Override
    public PageResult<DgjftzResVO> getDgjftzPage(DgjftzPageReqVO pageReqVO) {
        List<DgjftzResVO> records = dgjftzMapper.selectDgjftzList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }
}

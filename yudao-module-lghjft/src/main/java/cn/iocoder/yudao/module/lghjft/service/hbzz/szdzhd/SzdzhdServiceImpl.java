package cn.iocoder.yudao.module.lghjft.service.hbzz.szdzhd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.szdzhd.SzdzhdMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class SzdzhdServiceImpl implements SzdzhdService {

    @Resource
    private SzdzhdMapper szdzhdMapper;

    @Override
    public PageResult<SzdzhdResVO> getSzdzhdPage(SzdzhdPageReqVO pageReqVO) {
        List<SzdzhdResVO> records = szdzhdMapper.selectSzdzhdList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }
}

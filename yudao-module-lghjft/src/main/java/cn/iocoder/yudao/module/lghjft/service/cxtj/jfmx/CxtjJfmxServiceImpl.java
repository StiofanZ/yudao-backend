package cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jfmx.CxtjJfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class CxtjJfmxServiceImpl implements CxtjJfmxService {

    @Resource
    private CxtjJfmxMapper cxtjJfmxMapper;

    @Override
    public PageResult<JfmxResVO> getJfmxPage(JfmxPageReqVO pageReqVO) {
        List<JfmxResVO> records = cxtjJfmxMapper.selectJfmxList(pageReqVO);
        int fromIndex = Math.max((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize(), 0);
        if (fromIndex >= records.size()) {
            return new PageResult<>(new ArrayList<>(), (long) records.size());
        }
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), records.size());
        return new PageResult<>(records.subList(fromIndex, toIndex), (long) records.size());
    }
}

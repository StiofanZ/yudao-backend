package cn.iocoder.yudao.module.lghjft.service.cxtj.jffsjzqmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffsjzqmx.JffsjzqmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class JffsjzqmxServiceImpl implements JffsjzqmxService {

    @Resource
    private JffsjzqmxMapper jffsjzqmxMapper;

    @Override
    public List<JffsjzqmxResVO> getJffsjzqmxList(JffsjzqmxPageReqVO pageReqVO) {
        return jffsjzqmxMapper.selectJffsjzqmxList(pageReqVO);
    }
}

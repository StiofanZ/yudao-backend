package cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfswjg;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jftzfswjg.JftzfswjgMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class JftzfswjgServiceImpl implements JftzfswjgService {

    @Resource
    private JftzfswjgMapper jftzfswjgMapper;

    @Override
    public List<JftzfswjgResVO> getJftzfswjgList(JftzfswjgPageReqVO pageReqVO) {
        return jftzfswjgMapper.selectJftzfswjgList(pageReqVO);
    }
}

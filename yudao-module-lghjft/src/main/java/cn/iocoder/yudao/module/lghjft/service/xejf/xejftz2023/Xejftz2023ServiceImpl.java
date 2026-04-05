package cn.iocoder.yudao.module.lghjft.service.xejf.xejftz2023;

import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023ResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejftz2023.Xejftz2023Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class Xejftz2023ServiceImpl implements Xejftz2023Service {

    @Resource
    private Xejftz2023Mapper xejftz2023Mapper;

    @Override
    public List<Xejftz2023ResVO> getXejftz2023List(Xejftz2023PageReqVO pageReqVO) {
        return xejftz2023Mapper.selectXetjList(pageReqVO);
    }
}

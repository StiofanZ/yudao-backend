package cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl;

import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhhjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GhHjServiceImpl implements IGhHjService{

    @Resource
    private GhhjMapper ghhjMapper;

    @Override
    public GhHjVO selectGhHjBydjxh(String djxh) {
        return ghhjMapper.selectGhHjBydjxh(djxh);
    }
}

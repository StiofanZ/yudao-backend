package cn.iocoder.yudao.module.lghjft.service.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf.GhjfcbjqfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 筹备金全返 Service 实现
 */
@Service
@Validated
public class GhjfcbjqfServiceImpl implements GhjfcbjqfService {

    @Resource
    private GhjfcbjqfMapper ghjfcbjqfMapper;

    @Override
    public PageResult<GhjfcbjqfDO> getGhjfcbjqfPage(GhjfcbjqfPageReqVO pageReqVO) {
        return ghjfcbjqfMapper.selectPage(pageReqVO);
    }
}

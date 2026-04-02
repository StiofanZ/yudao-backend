package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.xzqh.XzqhMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 行政区划 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class XzqhServiceImpl implements XzqhService {

    @Resource
    private XzqhMapper xzqhMapper;

    @Override
    public List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO) {
        return xzqhMapper.selectList(listReqVO);
    }

}
package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.xzqh;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.xzqh.vo.XzqhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.xzqh.XzqhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.xzqh.XzqhMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XZQH_NOT_EXISTS;

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
    @Transactional(rollbackFor = Exception.class)
    public String createXzqh(XzqhSaveReqVO createReqVO) {
        XzqhDO xzqh = BeanUtils.toBean(createReqVO, XzqhDO.class);
        xzqhMapper.insert(xzqh);
        return xzqh.getXzqhDm();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXzqh(XzqhSaveReqVO updateReqVO) {
        validateXzqhExists(updateReqVO.getXzqhDm());
        XzqhDO updateObj = BeanUtils.toBean(updateReqVO, XzqhDO.class);
        xzqhMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXzqhByIds(List<String> xzqhDms) {
        xzqhMapper.deleteByIds(xzqhDms);
    }

    @Override
    public XzqhDO getXzqh(String xzqhDm) {
        return xzqhMapper.selectById(xzqhDm);
    }

    @Override
    public List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO) {
        return xzqhMapper.selectList(listReqVO);
    }

    private void validateXzqhExists(String xzqhDm) {
        if (xzqhMapper.selectById(xzqhDm) == null) {
            throw exception(XZQH_NOT_EXISTS);
        }
    }

}

package cn.iocoder.yudao.module.lghjft.service.nrgl.nrxx;

import java.util.List;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxListReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.nrxx.NrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.nrxx.NrxxMapper;
import cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 内容管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class NrxxServiceImpl implements NrxxService {

    @Resource
    private NrxxMapper nrMapper;

    @Override
    public Long createNrxx(NrxxCreateReqVO createReqVO) {
        NrxxDO nr = BeanUtils.toBean(createReqVO, NrxxDO.class);
        nrMapper.insert(nr);
        return nr.getId();
    }

    @Override
    public void updateNrxx(NrxxUpdateReqVO updateReqVO) {
        // 校验存在
        NrxxDO oldNrxx = validateNrExists(updateReqVO.getId());
        // 更新
        NrxxDO updateObj = BeanUtils.toBean(updateReqVO, NrxxDO.class);
        nrMapper.updateById(updateObj);

        // 如果类型发生变化，递归更新子节点
        if (!oldNrxx.getType().equals(updateReqVO.getType())) {
            updateChildrenType(updateReqVO.getId(), updateReqVO.getType());
        }
    }

    private void updateChildrenType(Long parentId, String newType) {
        List<NrxxDO> children = nrMapper.selectList(NrxxDO::getParentId, parentId);
        if (children.isEmpty()) {
            return;
        }
        for (NrxxDO child : children) {
            child.setType(newType);
            nrMapper.updateById(child);
            updateChildrenType(child.getId(), newType);
        }
    }

    @Override
    public void deleteNrxx(Long id) {
        // 校验存在
        validateNrExists(id);
        // 删除
        nrMapper.deleteById(id);
    }

    private NrxxDO validateNrExists(Long id) {
        NrxxDO nrxx = nrMapper.selectById(id);
        if (nrxx == null) {
            throw exception(ErrorCodeConstants.CONTENT_NOT_EXISTS);
        }
        return nrxx;
    }

    @Override
    public NrxxDO getNrxx(Long id) {
        return nrMapper.selectById(id);
    }

    @Override
    public List<NrxxDO> getNrxxList(NrxxListReqVO listReqVO) {
        return nrMapper.selectList(listReqVO);
    }

}

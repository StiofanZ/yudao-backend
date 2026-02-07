package cn.iocoder.yudao.module.lghjft.service.hj.ghhj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhj.GhHjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhHjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_DJXH_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_HJ_NOT_EXISTS;

/**
 * 基层账户空需维护对象 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GhHjServiceImpl implements GhHjService {

    @Resource
    private GhHjMapper ghHjMapper;

    @Override
    public String createGhHj(GhHjCreateReqVO createReqVO) {
        // 校验登记序号是否唯一
        validateGhHjForCreateOrUpdate(createReqVO.getDjxh(), null);

        // 插入
        GhHjDO ghHj = BeanUtils.toBean(createReqVO, GhHjDO.class);
        ghHjMapper.insert(ghHj);
        // 返回
        return ghHj.getDjxh();
    }

    @Override
    public void updateGhHj(GhHjUpdateReqVO updateReqVO) {
        // 校验存在
        validateGhHjExists(updateReqVO.getDjxh());
        // 校验登记序号唯一（虽然更新时主键通常不变，但为了严谨）
        // validateGhHjForCreateOrUpdate(updateReqVO.getDjxh(), updateReqVO.getDjxh());

        // 更新
        GhHjDO updateObj = BeanUtils.toBean(updateReqVO, GhHjDO.class);
        ghHjMapper.updateById(updateObj);
    }

    @Override
    public void deleteGhHj(String id) {
        // 校验存在
        validateGhHjExists(id);
        // 删除
        ghHjMapper.deleteById(id);
    }

    private void validateGhHjForCreateOrUpdate(String djxh, String id) {
        // 如果 ID 不一致，说明是修改了登记序号（虽然 PK 通常不修改），或者新增
        // 实际上 PK updateById 不会修改 PK。
        // 这里主要检查新增时是否重复
        if (id == null) {
            GhHjDO exists = ghHjMapper.selectById(djxh);
            if (exists != null) {
                throw exception(GH_HJ_DJXH_EXISTS);
            }
        }
    }

    private void validateGhHjExists(String id) {
        if (ghHjMapper.selectById(id) == null) {
            throw exception(GH_HJ_NOT_EXISTS);
        }
    }

    @Override
    public GhHjDO getGhHj(String id) {
        return ghHjMapper.selectById(id);
    }

    @Override
    public PageResult<GhHjDO> getGhHjPage(GhHjPageReqVO pageReqVO) {
        return ghHjMapper.selectPage(pageReqVO);
    }

}

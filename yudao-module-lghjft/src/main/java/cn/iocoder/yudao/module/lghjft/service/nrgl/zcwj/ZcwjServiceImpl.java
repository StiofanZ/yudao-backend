package cn.iocoder.yudao.module.lghjft.service.nrgl.zcwj;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcwj.ZcwjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
public class ZcwjServiceImpl implements ZcwjService {

    @Resource
    private ZcwjMapper zcwjMapper;

    @Override
    public Long createZcwj(ZcwjCreateReqVO createReqVO) {
        ZcwjDO zcwj = BeanUtils.toBean(createReqVO, ZcwjDO.class);
        zcwj.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        zcwj.setStatus(0);
        zcwj.setReadCount(0);
        zcwjMapper.insert(zcwj);
        return zcwj.getId();
    }

    @Override
    public void updateZcwj(ZcwjUpdateReqVO updateReqVO) {
        ZcwjDO oldZcwj = validateZcwjExists(updateReqVO.getId());
        validateDeptPermission(oldZcwj);
        if (Objects.equals(oldZcwj.getStatus(), 2)) {
            throw exception(new ErrorCode(400, "已发布政策文件不允许直接修改"));
        }
        ZcwjDO updateObj = BeanUtils.toBean(updateReqVO, ZcwjDO.class);
        updateObj.setDeptId(null);
        zcwjMapper.updateById(updateObj);
    }

    @Override
    public void deleteZcwj(Long id) {
        ZcwjDO oldZcwj = validateZcwjExists(id);
        validateDeptPermission(oldZcwj);
        zcwjMapper.deleteById(id);
    }

    @Override
    public ZcwjDO getZcwj(Long id) {
        ZcwjDO zcwj = zcwjMapper.selectById(id);
        if (zcwj != null && Objects.equals(zcwj.getStatus(), 2)) {
            ZcwjDO updateObj = new ZcwjDO();
            updateObj.setId(id);
            updateObj.setReadCount(zcwj.getReadCount() == null ? 1 : zcwj.getReadCount() + 1);
            zcwjMapper.updateById(updateObj);
            zcwj.setReadCount(updateObj.getReadCount());
        }
        return zcwj;
    }

    @Override
    public PageResult<ZcwjDO> getZcwjPage(ZcwjReqVO reqVO) {
        return zcwjMapper.selectPage(reqVO);
    }

    @Override
    public List<ZcwjDO> getPublishedList(ZcwjReqVO reqVO) {
        return zcwjMapper.selectPublishedList(reqVO);
    }

    @Override
    public List<ZcwjDO> getRecommendList(Long id, Integer limit) {
        ZcwjDO current = validateZcwjExists(id);
        int size = limit == null || limit <= 0 ? 5 : Math.min(limit, 10);
        ZcwjReqVO reqVO = new ZcwjReqVO();
        reqVO.setFbbm(current.getFbbm());
        List<ZcwjDO> candidates = zcwjMapper.selectPublishedList(reqVO).stream()
                .filter(item -> !Objects.equals(item.getId(), id))
                .collect(Collectors.toList());
        if (StringUtils.hasText(current.getTags())) {
            List<String> tags = Arrays.stream(current.getTags().split(","))
                    .map(String::trim)
                    .filter(StringUtils::hasText)
                    .toList();
            List<ZcwjDO> sameTagList = candidates.stream()
                    .filter(item -> StringUtils.hasText(item.getTags()) && tags.stream().anyMatch(item.getTags()::contains))
                    .limit(size)
                    .toList();
            if (!sameTagList.isEmpty()) {
                return sameTagList;
            }
        }
        return candidates.stream().limit(size).toList();
    }

    @Override
    public void publishZcwj(Long id) {
        ZcwjDO oldZcwj = validateZcwjExists(id);
        validateDeptPermission(oldZcwj);
        ZcwjDO updateObj = new ZcwjDO();
        updateObj.setId(id);
        updateObj.setStatus(2);
        updateObj.setOffShelfReason(null);
        zcwjMapper.updateById(updateObj);
    }

    @Override
    public void offShelfZcwj(Long id, String reason) {
        ZcwjDO oldZcwj = validateZcwjExists(id);
        validateDeptPermission(oldZcwj);
        ZcwjDO updateObj = new ZcwjDO();
        updateObj.setId(id);
        updateObj.setStatus(4);
        updateObj.setOffShelfReason(reason);
        zcwjMapper.updateById(updateObj);
    }

    private ZcwjDO validateZcwjExists(Long id) {
        ZcwjDO zcwj = zcwjMapper.selectById(id);
        if (zcwj == null) {
            throw exception(new ErrorCode(404, "政策文件不存在"));
        }
        return zcwj;
    }

    private void validateDeptPermission(ZcwjDO zcwj) {
        Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
        if (!Objects.equals(zcwj.getDeptId(), loginDeptId)) {
            throw exception(FORBIDDEN);
        }
    }
}

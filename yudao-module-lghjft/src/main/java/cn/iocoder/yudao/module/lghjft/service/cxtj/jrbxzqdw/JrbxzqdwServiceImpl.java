package cn.iocoder.yudao.module.lghjft.service.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jrbxzqdw.JrbxzqdwMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class JrbxzqdwServiceImpl implements JrbxzqdwService {

    @Resource
    private JrbxzqdwMapper jrbxzqdwMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createJrbxzqdw(JrbxzqdwSaveReqVO createReqVO) {
        JrbxzqdwDO obj = BeanUtils.toBean(createReqVO, JrbxzqdwDO.class);
        // V1: jrbxzqdw.setCreateTime(DateUtils.getNowDate())
        obj.setCreateTime(LocalDateTime.now());
        jrbxzqdwMapper.insertJrbxzqdw(obj);
        return obj.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJrbxzqdw(JrbxzqdwSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        // V1: RESTRICTED update — only bz, hsjg, createBy, createTime, updateBy, updateTime
        JrbxzqdwDO updateObj = new JrbxzqdwDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setBz(updateReqVO.getBz());
        updateObj.setHsjg(updateReqVO.getHsjg());
        // V1: sets createBy/updateBy from login user
        String currentUser = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setCreateBy(currentUser);
        updateObj.setUpdateBy(currentUser);
        updateObj.setUpdateTime(LocalDateTime.now());
        jrbxzqdwMapper.updateJrbxzqdw(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJrbxzqdw(String id) {
        validateExists(id);
        jrbxzqdwMapper.deleteJrbxzqdwById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJrbxzqdwListByIds(List<String> ids) {
        jrbxzqdwMapper.deleteJrbxzqdwByIds(ids.toArray(new String[0]));
    }

    private void validateExists(String id) {
        if (jrbxzqdwMapper.selectJrbxzqdwById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public JrbxzqdwDO getJrbxzqdw(String id) {
        // 使用 XML 的 3-table JOIN 查询
        return jrbxzqdwMapper.selectJrbxzqdwById(id);
    }

    @Override
    public PageResult<JrbxzqdwDO> getJrbxzqdwPage(JrbxzqdwPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        // V1 使用 startPage() 进行物理分页，V2 使用 MyBatis-Plus Page
        // XML selectJrbxzqdwList 返回 List，我们用 PageHelper 式手动分页
        Page<JrbxzqdwDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 由于 XML Mapper 返回 List，我们需要手动计算分页
        List<JrbxzqdwDO> allList = jrbxzqdwMapper.selectJrbxzqdwList(pageReqVO);
        long total = allList.size();

        // 手动分页
        int fromIndex = (pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize();
        int toIndex = Math.min(fromIndex + pageReqVO.getPageSize(), allList.size());
        List<JrbxzqdwDO> pageList;
        if (fromIndex >= allList.size()) {
            pageList = List.of();
        } else {
            pageList = allList.subList(fromIndex, toIndex);
        }

        return new PageResult<>(pageList, total);
    }
}

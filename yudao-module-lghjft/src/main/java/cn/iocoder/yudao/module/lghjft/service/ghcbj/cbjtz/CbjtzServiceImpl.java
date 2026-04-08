package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjtz.CbjtzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjtz.CbjtzMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class CbjtzServiceImpl implements CbjtzService {

    @Resource
    private CbjtzMapper cbjtzMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCbjtz(CbjtzSaveReqVO createReqVO) {
        CbjtzDO cbjtz = BeanUtils.toBean(createReqVO, CbjtzDO.class);
        cbjtzMapper.insert(cbjtz);
        return cbjtz.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCbjtz(CbjtzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        // v1: update gh_jf set CBJTHBJ='Z', CBJTHCZY, CBJTHRQ, UPDATE_BY, UPDATE_TIME
        String username = getLoginUsername();
        cbjtzMapper.updateCbjtzById(updateReqVO.getGhjfId(), username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCbjtz(Long id) {
        validateExists(id);
        // v1: cascade delete cxtj_cbjqrfb first, then delete gh_jf
        cbjtzMapper.deleteCbjqrfbByGhjfId(id);
        cbjtzMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCbjtzListByIds(List<Long> ids) {
        // v1: cascade delete cxtj_cbjqrfb first, then delete gh_jf
        cbjtzMapper.deleteCbjqrfbByGhjfIds(ids);
        cbjtzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (cbjtzMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public CbjtzDO getCbjtz(Long id) {
        return cbjtzMapper.selectById(id);
    }

    @Override
    public PageResult<CbjtzResVO> getCbjtzPage(CbjtzPageReqVO pageReqVO) {
        // v1 dept filtering: root = 100000
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        // manual pagination via XML mapper (needs JOIN with cxtj_cbjqrfb)
        long total = cbjtzMapper.selectCbjtzPageCount(pageReqVO);
        if (total == 0) {
            return new PageResult<>(Collections.emptyList(), total);
        }
        // apply pagination offset/limit; PAGE_SIZE_NONE (-1) = no pagination (export)
        applyOffset(pageReqVO);
        List<CbjtzResVO> list = cbjtzMapper.selectCbjtzPageList(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public PageResult<CbjtzResVO> getDgjftzPage(CbjtzPageReqVO pageReqVO) {
        // v1 dept filtering: root = 100000
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));

        long total = cbjtzMapper.selectDgjftzPageCount(pageReqVO);
        if (total == 0) {
            return new PageResult<>(Collections.emptyList(), total);
        }
        applyOffset(pageReqVO);
        List<CbjtzResVO> list = cbjtzMapper.selectDgjftzPageList(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCbjqrfbpl(List<CbjtzBatchReqVO> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        String username = getLoginUsername();
        // set createBy/updateBy for each record
        for (CbjtzBatchReqVO record : records) {
            if (record.getCreateBy() == null) {
                record.setCreateBy(username);
            }
            if (record.getUpdateBy() == null) {
                record.setUpdateBy(username);
            }
        }
        // v1: insert/update cxtj_cbjqrfb
        cbjtzMapper.batchInsertOrUpdateCbjqrfb(records);
        // v1: update gh_jf main table (CBJTHBJ='Z', CBJTHCZY, CBJTHRQ, UPDATE_BY, UPDATE_TIME)
        List<Long> ghjfIds = records.stream().map(CbjtzBatchReqVO::getGhjfId).collect(Collectors.toList());
        cbjtzMapper.batchUpdateMainTable(ghjfIds, username);
    }

    private void applyOffset(CbjtzPageReqVO pageReqVO) {
        int pageSize = pageReqVO.getPageSize() != null ? pageReqVO.getPageSize() : 10;
        if (pageSize <= 0) {
            // PAGE_SIZE_NONE (-1): no pagination (e.g., export)
            pageReqVO.setPageSize(null);
            pageReqVO.setOffset(null);
        } else {
            int pageNo = pageReqVO.getPageNo() != null ? pageReqVO.getPageNo() : 1;
            pageReqVO.setOffset((pageNo - 1) * pageSize);
        }
    }

    private String getLoginUsername() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return userId != null ? userId.toString() : null;
    }
}

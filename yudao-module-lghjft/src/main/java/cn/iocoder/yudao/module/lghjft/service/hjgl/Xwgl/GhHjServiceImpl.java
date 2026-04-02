package cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxKzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhhjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxKzMapper;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
public class GhHjServiceImpl implements IGhHjService {

    @Resource
    private GhhjMapper ghhjMapper;

    @Resource
    private NsrxxService nsrxxService;

    @Resource
    private NsrxxKzMapper nsrxxKzMapper;

    @Override
    public GhHjVO selectGhHjBydjxh(String djxh) {
        return ghhjMapper.selectGhHjBydjxh(djxh);
    }

    @Override
    public PageResult<GhHjVO> selectGhHjPage(GhHjPageReqVO pageReqVO) {
        if (StringUtils.isBlank(pageReqVO.getDeptId())) {
            Long currentDeptId = getLoginUserDeptId();
            if (currentDeptId != null) {
                pageReqVO.setDeptId(currentDeptId.toString());
            }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        Page<GhHjVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<GhHjVO> ipage = ghhjMapper.selectGhHjPage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public List<GhHjVO> selectGhHjList(GhHjPageReqVO pageReqVO) {
        if (StringUtils.isBlank(pageReqVO.getDeptId())) {
            Long currentDeptId = getLoginUserDeptId();
            if (currentDeptId != null) {
                pageReqVO.setDeptId(currentDeptId.toString());
            }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return ghhjMapper.selectGhHjList(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGhHj(GhHjSaveReqVO saveReqVO) {
        return ghhjMapper.insertGhHj(saveReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteGhHjBydjxhs(String[] djxhs) {
        if (djxhs == null || djxhs.length == 0) {
            throw exception(HJGL_DJXHS_EMPTY);
        }
        return ghhjMapper.deleteGhHjBydjxhs(djxhs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int fushenPlBydjxhs(List<String> djxhs) {
        if (CollectionUtils.isEmpty(djxhs)) {
            throw exception(HJGL_DJXHS_EMPTY);
        }
        return ghhjMapper.fushenPlBydjxhs(djxhs);
    }

    @Override
    public Map<String, Object> getDjNsrxxInfo(String searchNsrKey) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (StringUtils.isBlank(searchNsrKey)) {
            result.put("msg", "参数为空，无法查询");
            return result;
        }

        // 1. Check if already maintained by another dept in gh_hj + jhdwyds
        List<GhHjNsrxxResVO> hjList = ghhjMapper.getListByDjNsrxxDto(searchNsrKey, null, null);
        if (!CollectionUtils.isEmpty(hjList)) {
            GhHjNsrxxResVO first = hjList.get(0);
            result.put("msg", searchNsrKey + "已被" + first.getDeptName() + "维护，如有问题请联系该机构核实调拨！");
            return result;
        }

        // 2. Query dj_nsrxx table via NsrxxService
        List<NsrxxDO> nsrxxList = nsrxxService.getNsrxxList(searchNsrKey);
        if (CollectionUtils.isEmpty(nsrxxList)) {
            result.put("msg", "未查询到信息");
            return result;
        }

        // 3. Convert to GhHjVO list
        List<GhHjVO> voList = new ArrayList<>(nsrxxList.size());
        for (NsrxxDO nsrxx : nsrxxList) {
            GhHjVO vo = buildGhHjVoFromNsrxx(nsrxx);
            voList.add(vo);
        }

        result.put("data", voList);
        return result;
    }

    @Override
    public Map<String, Object> getDjNsrxxInfoForUpdateHj(String djxh, String shxydm) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (StringUtils.isBlank(djxh) && StringUtils.isBlank(shxydm)) {
            result.put("msg", "参数为空，无法查询");
            return result;
        }

        // Get current user's dept
        Long currentDeptId = getLoginUserDeptId();

        // Check if already maintained by another dept
        List<GhHjNsrxxResVO> hjList = ghhjMapper.getListByDjNsrxxDto(null, djxh, shxydm);
        if (!CollectionUtils.isEmpty(hjList)) {
            GhHjNsrxxResVO first = hjList.get(0);
            if (currentDeptId != null && !first.getDeptId().equals(currentDeptId.toString())) {
                String key = StringUtils.isNotBlank(shxydm) ? shxydm : djxh;
                result.put("msg", key + "已被" + first.getDeptName() + "维护，如有问题请联系该机构核实调拨！");
                return result;
            }
        }

        // Query dj_nsrxx
        String keyword = StringUtils.isNotBlank(djxh) ? djxh : shxydm;
        List<NsrxxDO> nsrxxList = nsrxxService.getNsrxxList(keyword);
        if (CollectionUtils.isEmpty(nsrxxList)) {
            result.put("msg", "未查询到信息");
            return result;
        }

        GhHjVO vo = buildGhHjVoFromNsrxx(nsrxxList.get(0));
        if (currentDeptId != null) {
            vo.setDeptId(currentDeptId.toString());
        }

        result.put("data", vo);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocationGhHj(GhHjAllocationReqVO reqVO) {
        if (StringUtils.isBlank(reqVO.getDeptId()) || CollectionUtils.isEmpty(reqVO.getDjxhs())) {
            throw exception(HJGL_ALLOCATION_PARAM_EMPTY);
        }
        int updated = ghhjMapper.updateAllocationGhHj(reqVO.getDeptId(), reqVO.getHyghbz(), reqVO.getDjxhs());
        if (updated == 0) {
            throw exception(HJGL_ALLOCATION_FAILED);
        }
    }

    /**
     * 从 NsrxxDO 构建 GhHjVO（对应 v1 的 getGhHjVoByDjNsrxxVo）
     */
    private GhHjVO buildGhHjVoFromNsrxx(NsrxxDO nsrxx) {
        GhHjVO vo = new GhHjVO();
        Long currentDeptId = getLoginUserDeptId();
        if (currentDeptId != null) {
            vo.setDeptId(currentDeptId.toString());
        }
        vo.setDjxh(nsrxx.getDjxh());
        vo.setShxydm(nsrxx.getShxydm());
        vo.setNsrsbh(nsrxx.getNsrsbh());
        vo.setNsrmc(nsrxx.getNsrmc());
        vo.setNsrjc(nsrxx.getNsrmc());
        vo.setJdxzDm(nsrxx.getJdxzDm());
        vo.setZgswjDm(nsrxx.getZgswjDm());
        vo.setZgswskfjDm(nsrxx.getZgswskfjDm());
        vo.setSsglyDm(nsrxx.getSsglyDm());
        vo.setZzjglxDm(nsrxx.getZzjgDm());
        vo.setHyDm(nsrxx.getHyDm());
        vo.setDjzclxDm(nsrxx.getDjzclxDm());
        vo.setDwlsgxDm(nsrxx.getDwlsgxDm());
        vo.setNsrztDm(nsrxx.getNsrztDm());
        vo.setFzcrq(nsrxx.getDjrq());
        vo.setZcdz(nsrxx.getZcdz());

        // Populate extended info from NsrxxKzDO
        NsrxxKzDO kz = nsrxxKzMapper.selectById(nsrxx.getDjxh());
        if (kz != null) {
            vo.setLxr(kz.getCwfzrxm());
            vo.setLxdh(kz.getCwfzryddh());
            vo.setYzbm(kz.getZcdyzbm());
            vo.setZgrs(kz.getCyrs());
        }

        return vo;
    }
}

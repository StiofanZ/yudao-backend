package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjNsrxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.SystemUserSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhhjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.SystemUserSfxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds.JhdwydsMapper;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.SystemUserSfxxService;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class SfxxServiceImpl implements SfxxService {
    @Resource
    private SystemUserSfxxService systemUserSfxxService;
    @Resource
    private SystemUserSfxxMapper systemUserSfxxMapper;
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private GhhjMapper ghhjMapper;
    @Resource
    private JhdwydsMapper jhdwydsMapper;

    @Override
    public List<KbdsfxxResVO> getKbdsfxx(SfxxReqVO pageReqVO) {
        AdminUserDO userDO = adminUserMapper.selectById(pageReqVO.getDlzhId());

        // 无用户时按部门查询已有身份
        if (userDO == null) {
            if (Objects.nonNull(pageReqVO.getDeptId())) {
                pageReqVO.setDlzhId(null);
                return BeanUtils.toBean(systemUserSfxxService.getSfxxList(pageReqVO), KbdsfxxResVO.class);
            }
            return Collections.emptyList();
        }

        // --- 1. 收集用户所有非空标识 ---
        Set<String> identifiers = new LinkedHashSet<>();
        if (StrUtil.isNotBlank(userDO.getUsername())) {
            identifiers.add(userDO.getUsername());
        }
        if (StrUtil.isNotBlank(userDO.getEmail())) {
            identifiers.add(userDO.getEmail());
        }
        if (StrUtil.isNotBlank(userDO.getMobile())) {
            identifiers.add(userDO.getMobile());
        }
        if (StrUtil.isNotBlank(userDO.getShxydm())) {
            identifiers.add(userDO.getShxydm());
        }
        if (identifiers.isEmpty()) {
            return Collections.emptyList();
        }

        // --- 2. 查询 gh_hj 表（1 次查询） ---
        List<GhHjNsrxxResVO> ghhjList = ghhjMapper.selectBindableByIdentifiers(identifiers);

        // --- 3. 查询 jhdwyds 表（1 次查询） ---
        LambdaQueryWrapper<JhdwydsDO> jhdwydsWrapper = new LambdaQueryWrapper<>();
        jhdwydsWrapper.and(w -> w
                .in(JhdwydsDO::getGhshxydm, identifiers)
                .or().in(JhdwydsDO::getGhjflxdh, identifiers));
        List<JhdwydsDO> jhdwydsList = jhdwydsMapper.selectList(jhdwydsWrapper);

        // --- 4. 收集所有 djxh，批量查询已有身份信息（1 次查询） ---
        Set<String> allDjxhs = new HashSet<>();
        for (GhHjNsrxxResVO vo : ghhjList) {
            if (vo.getDjxh() != null) {
                allDjxhs.add(vo.getDjxh());
            }
        }
        for (JhdwydsDO jhdwyds : jhdwydsList) {
            String jhdwDjxh = StrUtil.isNotBlank(jhdwyds.getGhshxydm()) ? jhdwyds.getGhshxydm() : jhdwyds.getShxydm();
            if (jhdwDjxh != null) {
                allDjxhs.add(jhdwDjxh);
            }
        }

        Map<String, SystemUserSfxxDO> sfxxMap = Collections.emptyMap();
        if (!allDjxhs.isEmpty()) {
            List<SystemUserSfxxDO> sfxxList = systemUserSfxxMapper.selectList(
                    new LambdaQueryWrapper<SystemUserSfxxDO>()
                            .eq(SystemUserSfxxDO::getDlzhId, userDO.getId())
                            .in(SystemUserSfxxDO::getDjxh, allDjxhs));
            sfxxMap = sfxxList.stream()
                    .collect(Collectors.toMap(SystemUserSfxxDO::getDjxh, s -> s, (a, b) -> a));
        }

        // --- 5. 组装结果（纯内存，0 次查询） ---
        List<KbdsfxxResVO> result = new ArrayList<>(ghhjList.size() + jhdwydsList.size());

        // gh_hj → 缴费单位（ghlx=02）
        for (GhHjNsrxxResVO ghhj : ghhjList) {
            KbdsfxxResVO vo = new KbdsfxxResVO();
            vo.setDjxh(ghhj.getDjxh());
            vo.setShxydm(ghhj.getShxydm());
            vo.setDwmc(ghhj.getNsrmc());
            vo.setLxdh(ghhj.getLxdh());
            vo.setGhlx("02");
            if (StrUtil.isNotBlank(ghhj.getDeptId())) {
                try {
                    vo.setDeptId(Long.valueOf(ghhj.getDeptId()));
                } catch (NumberFormatException ignored) {
                }
            }
            // 合并已有绑定信息
            SystemUserSfxxDO existing = sfxxMap.get(ghhj.getDjxh());
            if (existing != null) {
                BeanUtils.copyProperties(existing, vo);
            }
            result.add(vo);
        }

        // jhdwyds → 基层工会（ghlx=01）
        for (JhdwydsDO jhdwyds : jhdwydsList) {
            String jhdwDjxh = StrUtil.isNotBlank(jhdwyds.getGhshxydm()) ? jhdwyds.getGhshxydm() : jhdwyds.getShxydm();
            KbdsfxxResVO vo = new KbdsfxxResVO();
            vo.setDjxh(jhdwDjxh);
            vo.setShxydm(jhdwyds.getShxydm());
            vo.setDwmc(jhdwyds.getGhmc());
            vo.setLxdh(jhdwyds.getDwcwlxdh());
            vo.setSflx("03");
            vo.setGhlx("01");
            if (StrUtil.isNotBlank(jhdwyds.getDeptId())) {
                try {
                    vo.setDeptId(Long.valueOf(jhdwyds.getDeptId()));
                } catch (NumberFormatException ignored) {
                }
            }
            // 合并已有绑定信息
            SystemUserSfxxDO existing = sfxxMap.get(jhdwDjxh);
            if (existing != null) {
                BeanUtils.copyProperties(existing, vo);
            }
            result.add(vo);
        }

        // --- 6. 填充人员信息（复用已查询的 userDO，0 次查询） ---
        for (KbdsfxxResVO vo : result) {
            if (vo.getDlzhId() != null && Objects.equals(vo.getDlzhId(), userDO.getId())) {
                vo.setRyxm(userDO.getNickname());
                vo.setLxdh(userDO.getMobile());
            }
        }

        return result;
    }
}

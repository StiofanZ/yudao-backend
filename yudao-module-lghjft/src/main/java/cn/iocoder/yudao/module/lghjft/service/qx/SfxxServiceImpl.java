package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;

import cn.iocoder.yudao.module.lghjft.service.sjwh.jhdwyds.JhdwydsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class SfxxServiceImpl implements SfxxService {
    @Resource
    private GhQxSfxxService ghQxSfxxService;
    @Resource
    private GhQxDlzhService ghQxDlzhService;
    @Resource
    private JcxxService jcxxService;
    @Resource
    private JhdwydsService JhdwydsService;

    @Override
    public List<KbdsfxxResVO> getKbdsfxx(SfxxReqVO pageReqVO) {
        GhQxDlzhDO ghQxDlzhDO = ghQxDlzhService.getDlzh(pageReqVO.getDlzhId());
        List<KbdsfxxResVO> kbdsfxxResVOList = new ArrayList<>();
        if (ghQxDlzhDO == null) {
            if (Objects.nonNull(pageReqVO.getDeptId())) {
                pageReqVO.setDlzhId(null);
                kbdsfxxResVOList = BeanUtils.toBean(ghQxSfxxService.getSfxxList(pageReqVO), KbdsfxxResVO.class);
            }
        } else {
            if (Objects.nonNull(ghQxDlzhDO.getLxdh())) {
                JcxxPageReqVO jcxxPageReqVO = new JcxxPageReqVO();
                jcxxPageReqVO.setLxdh(ghQxDlzhDO.getLxdh());
                for (GhHjJcxxDO jcxxDO : jcxxService.getJcxxList(ghQxDlzhDO.getLxdh())) {
                    KbdsfxxResVO respVO = new KbdsfxxResVO();
                    respVO.setShxydm(jcxxDO.getShxydm() != null ? jcxxDO.getShxydm() : jcxxDO.getNsrsbh());
                    respVO.setLxdh(jcxxDO.getLxdh());
                    respVO.setDwmc(jcxxDO.getNsrmc());
                    respVO.setDjxh(jcxxDO.getDjxh());
                    respVO.setGhlx("02");
                    kbdsfxxResVOList.add(respVO);
                }
                kbdsfxxResVOList.forEach(respVO -> {
                    GhQxSfxxDO ghQxSfxxDO = ghQxSfxxService.getSfxx(ghQxDlzhDO.getId(), respVO.getDjxh());
                    BeanUtils.copyProperties(ghQxSfxxDO, respVO);
                });
                JhdwydsSaveReqVO jhdwydsReqVO = new JhdwydsSaveReqVO();
                jhdwydsReqVO.setDwcwlxdh(ghQxDlzhDO.getLxdh());
                for (JhdwydsDO jhdwydsDO : JhdwydsService.getJhdwydsList(jhdwydsReqVO)) {
                    KbdsfxxResVO respVO = new KbdsfxxResVO();
                    respVO.setShxydm(jhdwydsDO.getGhshxydm());
                    respVO.setLxdh(jhdwydsDO.getDwcwlxdh());
                    respVO.setDwmc(jhdwydsDO.getGhmc());
                    respVO.setDjxh(jhdwydsDO.getGhshxydm());
                    respVO.setGhlx("01");
                    kbdsfxxResVOList.add(respVO);
                }
            }
        }
        kbdsfxxResVOList.forEach(kbdsfxxResVO -> {
            GhQxDlzhDO dlzhDO = ghQxDlzhService.getDlzh(kbdsfxxResVO.getDlzhId());
            if (Objects.nonNull(dlzhDO)) {
                kbdsfxxResVO.setRyxm(dlzhDO.getYhxm());
                kbdsfxxResVO.setLxdh(dlzhDO.getLxdh());
            }
        });
        return kbdsfxxResVOList;
    }
}

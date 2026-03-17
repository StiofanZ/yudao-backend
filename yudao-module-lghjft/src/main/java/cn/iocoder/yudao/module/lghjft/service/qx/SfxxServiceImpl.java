package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.ydsdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw.ydsdwDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;

import cn.iocoder.yudao.module.lghjft.service.sjwh.ydsdw.ydsdwService;
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
    private ydsdwService ydsdwService;

    @Override
    public List<KbdsfxxRespVO> getKbdsfxx(SfxxReqVO pageReqVO) {
        GhQxDlzhDO ghQxDlzhDO = ghQxDlzhService.getDlzh(pageReqVO.getDlzhId());
        List<KbdsfxxRespVO> kbdsfxxRespVOList = new ArrayList<>();
        if (ghQxDlzhDO == null) {
            if (Objects.nonNull(pageReqVO.getDeptId())) {
                pageReqVO.setDlzhId(null);
                kbdsfxxRespVOList = BeanUtils.toBean(ghQxSfxxService.getSfxxList(pageReqVO), KbdsfxxRespVO.class);
            }
        } else {
            if (Objects.nonNull(ghQxDlzhDO.getLxdh())) {
                JcxxPageReqVO jcxxPageReqVO = new JcxxPageReqVO();
                jcxxPageReqVO.setLxdh(ghQxDlzhDO.getLxdh());
                for (GhHjJcxxDO jcxxDO : jcxxService.getJcxxList(ghQxDlzhDO.getLxdh())) {
                    KbdsfxxRespVO respVO = new KbdsfxxRespVO();
                    respVO.setShxydm(jcxxDO.getShxydm() != null ? jcxxDO.getShxydm() : jcxxDO.getNsrsbh());
                    respVO.setLxdh(jcxxDO.getLxdh());
                    respVO.setDwmc(jcxxDO.getNsrmc());
                    respVO.setDjxh(jcxxDO.getDjxh());
                    respVO.setGhlx("02");
                    kbdsfxxRespVOList.add(respVO);
                }
                kbdsfxxRespVOList.forEach(respVO -> {
                    GhQxSfxxDO ghQxSfxxDO = ghQxSfxxService.getSfxx(ghQxDlzhDO.getId(), respVO.getDjxh());
                    BeanUtils.copyProperties(ghQxSfxxDO, respVO);
                });
                ydsdwSaveReqVO jhdwydsReqVO = new ydsdwSaveReqVO();
                jhdwydsReqVO.setDwcwlxdh(ghQxDlzhDO.getLxdh());
                for (ydsdwDO jhdwydsDO : ydsdwService.getJhdwydsList(jhdwydsReqVO)) {
                    KbdsfxxRespVO respVO = new KbdsfxxRespVO();
                    respVO.setShxydm(jhdwydsDO.getGhshxydm());
                    respVO.setLxdh(jhdwydsDO.getDwcwlxdh());
                    respVO.setDwmc(jhdwydsDO.getGhmc());
                    respVO.setDjxh(jhdwydsDO.getGhshxydm());
                    respVO.setGhlx("01");
                    kbdsfxxRespVOList.add(respVO);
                }
            }
        }
        kbdsfxxRespVOList.forEach(kbdsfxxRespVO -> {
            GhQxDlzhDO dlzhDO = ghQxDlzhService.getDlzh(kbdsfxxRespVO.getDlzhId());
            if (Objects.nonNull(dlzhDO)) {
                kbdsfxxRespVO.setRyxm(dlzhDO.getYhxm());
                kbdsfxxRespVO.setLxdh(dlzhDO.getLxdh());
            }
        });
        return kbdsfxxRespVOList;
    }
}

package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.JcxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.service.hjgl.jcxx.JcxxService;
import cn.iocoder.yudao.module.lghjft.service.qx.dlzh.GhQxDlzhService;
import cn.iocoder.yudao.module.lghjft.service.qx.sfxx.GhQxSfxxService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
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

    @Override
    public PageResult<KbdsfxxRespVO> getKbdsfxx(SfxxPageReqVO pageReqVO) {
        GhQxDlzhDO ghQxDlzhDO = ghQxDlzhService.getDlzh(pageReqVO.getDlzhId());
        PageResult<KbdsfxxRespVO> kbdsfxxRespVOPageResult = new PageResult<>();
        if (ghQxDlzhDO == null) {
            if (Objects.isNull(pageReqVO.getDeptId())) return PageResult.empty();
            pageReqVO.setDlzhId(null);
            kbdsfxxRespVOPageResult = BeanUtils.toBean(ghQxSfxxService.getSfxxPage(pageReqVO), KbdsfxxRespVO.class);
        } else {
            if (StrUtil.isBlank(ghQxDlzhDO.getLxdh())) return PageResult.empty();
            JcxxPageReqVO jcxxPageReqVO = new JcxxPageReqVO();
            jcxxPageReqVO.setPageNo(pageReqVO.getPageNo());
            jcxxPageReqVO.setPageSize(pageReqVO.getPageSize());
            jcxxPageReqVO.setLxdh(ghQxDlzhDO.getLxdh());
            PageResult<GhHjJcxxDO> ghHjJcxxDOPageResult = jcxxService.getJcxxPage(jcxxPageReqVO);
            kbdsfxxRespVOPageResult.setTotal(ghHjJcxxDOPageResult.getTotal());
            kbdsfxxRespVOPageResult.setList(new ArrayList<>(ghHjJcxxDOPageResult.getList().stream().map(jcxxDO -> {
                KbdsfxxRespVO respVO = new KbdsfxxRespVO();
                respVO.setShxydm(jcxxDO.getShxydm() != null ? jcxxDO.getShxydm() : jcxxDO.getNsrsbh());
                respVO.setLxdh(jcxxDO.getLxdh());
                respVO.setDwmc(jcxxDO.getNsrmc());
                respVO.setDjxh(jcxxDO.getDjxh());
                return respVO;
            }).toList()));
            kbdsfxxRespVOPageResult.getList().forEach(respVO -> {
                GhQxSfxxDO ghQxSfxxDO = ghQxSfxxService.getSfxx(ghQxDlzhDO.getId(), respVO.getDjxh());
                BeanUtils.copyProperties(ghQxSfxxDO, respVO);
            });
        }
        kbdsfxxRespVOPageResult.getList().forEach(kbdsfxxRespVO -> {
            GhQxDlzhDO dlzhDO = ghQxDlzhService.getDlzh(kbdsfxxRespVO.getDlzhId());
            if (Objects.nonNull(dlzhDO)) {
                kbdsfxxRespVO.setRyxm(dlzhDO.getYhxm());
                kbdsfxxRespVO.setLxdh(dlzhDO.getLxdh());
            }
        });
        return kbdsfxxRespVOPageResult;
    }
}

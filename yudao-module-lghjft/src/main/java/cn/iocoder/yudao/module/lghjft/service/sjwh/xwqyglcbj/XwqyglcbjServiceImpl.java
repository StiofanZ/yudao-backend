package cn.iocoder.yudao.module.lghjft.service.sjwh.xwqyglcbj;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.xwqyglcbj.XwqyglcbjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XwqyglcbjServiceImpl implements XwqyglcbjService {

    @Resource
    private XwqyglcbjMapper xwqyglcbjMapper;

    @Override
    public PageResult<XwqyglcbjResVO> getXwqyglcbjPage(XwqyglcbjPageReqVO pageReqVO) {
        if (StrUtil.isBlank(pageReqVO.getDeptId())) {
            Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (deptId != null) {
                pageReqVO.setDeptId(deptId.toString());
            }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        pageReqVO.setLimit(pageReqVO.getPageSize());
        return new PageResult<>(
                xwqyglcbjMapper.selectXwqyglcbjList(pageReqVO),
                xwqyglcbjMapper.selectXwqyglcbjCount(pageReqVO)
        );
    }

    @Override
    public List<XwqyglcbjResVO> getXwqyglcbjList(XwqyglcbjPageReqVO pageReqVO) {
        if (StrUtil.isBlank(pageReqVO.getDeptId())) {
            Long deptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (deptId != null) {
                pageReqVO.setDeptId(deptId.toString());
            }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return xwqyglcbjMapper.selectXwqyglcbjList(pageReqVO);
    }

    @Override
    public XwqyglcbjResVO getXwqyglcbjByDjxh(String djxh) {
        return xwqyglcbjMapper.selectXwqyglcbjByDjxh(djxh);
    }

    @Override
    public void updateXwqyglcbj(XwqyglcbjSaveReqVO reqVO) {
        xwqyglcbjMapper.updateXwqyglcbj(reqVO);
    }
}

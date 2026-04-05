package cn.iocoder.yudao.module.lghjft.service.cxtj.jffnmx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffnmx.vo.JffnmxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffnmx.JffnmxMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

/**
 * 经费分年明细 Service 实现
 */
@Service
@Validated
public class JffnmxServiceImpl implements JffnmxService {

    @Resource
    private JffnmxMapper jffnmxMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public PageResult<JffnmxSummaryResVO> selectJffnmxList(JffnmxPageReqVO pageReqVO) {
        clBmId(pageReqVO);
        Page<JffnmxSummaryResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JffnmxSummaryResVO> ipage = jffnmxMapper.selectJffnmxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    private void clBmId(JffnmxPageReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    reqVO.setDeptId(user.getDeptId().toString());
                } else {
                    reqVO.setDeptId(null);
                }
            } catch (Exception e) {
                reqVO.setDeptId(null);
            }
        }
        if ("100000".equals(reqVO.getDeptId())) {
            reqVO.setDeptId(null);
        }
    }
}

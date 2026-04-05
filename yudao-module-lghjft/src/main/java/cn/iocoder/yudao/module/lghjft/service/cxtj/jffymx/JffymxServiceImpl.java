package cn.iocoder.yudao.module.lghjft.service.cxtj.jffymx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffymx.vo.JffymxSummaryResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jffymx.JffymxMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

/**
 * 经费分月明细 Service 实现
 */
@Service
@Validated
public class JffymxServiceImpl implements JffymxService {

    @Resource
    private JffymxMapper jffymxMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public PageResult<JffymxSummaryResVO> selectJffymxList(JffymxPageReqVO pageReqVO) {
        clBmId(pageReqVO);
        Page<JffymxSummaryResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<JffymxSummaryResVO> ipage = jffymxMapper.selectJffymxList(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    private void clBmId(JffymxPageReqVO reqVO) {
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

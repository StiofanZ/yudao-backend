package cn.iocoder.yudao.module.lghjft.service.hbzz.jfmx;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo.JfRespVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jfmx.JfMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

/**
 *  经费明细对象 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class JfServiceImpl implements JfService {
    @Resource
    private JfMapper jfMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public PageResult<JfRespVO> selectJftzmxList(JfPageReqVO jfmx) {
        clBmId(jfmx);
        Page<JfRespVO> page = new Page<>(jfmx.getPageNo(), jfmx.getPageSize());
        IPage<JfRespVO> ipage = jfMapper.selectJftzmxList(page, jfmx);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    private void clBmId(JfPageReqVO jfmx) {
        if (StringUtils.isEmpty(jfmx.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            jfmx.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(jfmx.getDeptId())) {
            jfmx.setDeptId(null);
        }
    }


}

package cn.iocoder.yudao.module.lghjft.service.sjwh.wjgl;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.wjgl.WjglDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.wjgl.WjglMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

@Service
@Validated
public class WjglServiceImpl implements WjglService {

    @Resource
    private WjglMapper wjglMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public PageResult<WjglResVO> getFilePage(WjglPageReqVO pageReqVO) {
        // v1 logic: default deptId to "620000" (NOT from login user)
        if (StrUtil.isEmpty(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId("620000");
        }
        // v1 logic: "100000" means show all
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        Page<WjglResVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        page.setOptimizeCountSql(false);
        IPage<WjglResVO> ipage = wjglMapper.selectFilePage(page, pageReqVO);
        return new PageResult<>(ipage.getRecords(), ipage.getTotal());
    }

    @Override
    public WjglResVO getFile(Long fileid) {
        return wjglMapper.selectFileByFileid(fileid);
    }

    @Override
    public void createFile(WjglSaveReqVO createReqVO) {
        WjglDO fileDO = new WjglDO();
        fileDO.setFilename(createReqVO.getFilename());
        fileDO.setFilestatus(createReqVO.getFilestatus());
        fileDO.setFileurl(createReqVO.getFileurl());
        fileDO.setBz(createReqVO.getBz());
        // v1 logic: auto-fill deptId and createBy from login user
        if (StrUtil.isEmpty(createReqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            fileDO.setDeptId(user.getDeptId().toString());
            fileDO.setCreateBy(user.getUsername());
        } else {
            fileDO.setDeptId(createReqVO.getDeptId());
            AdminUserDO user = userService.getUser(getLoginUserId());
            fileDO.setCreateBy(user.getUsername());
        }
        // v1 logic: always set createTime to now
        fileDO.setCreateTime(new Date());
        wjglMapper.insert(fileDO);
    }

    @Override
    public void updateFile(WjglSaveReqVO updateReqVO) {
        WjglDO fileDO = new WjglDO();
        fileDO.setFileid(updateReqVO.getFileid());
        fileDO.setDeptId(updateReqVO.getDeptId());
        fileDO.setFilename(updateReqVO.getFilename());
        fileDO.setFilestatus(updateReqVO.getFilestatus());
        fileDO.setFileurl(updateReqVO.getFileurl());
        fileDO.setBz(updateReqVO.getBz());
        // v1 logic: always set updateBy and updateTime
        AdminUserDO user = userService.getUser(getLoginUserId());
        fileDO.setUpdateBy(user.getUsername());
        fileDO.setUpdateTime(new Date());
        wjglMapper.updateById(fileDO);
    }

    @Override
    public void deleteFiles(Long[] fileids) {
        for (Long fileid : fileids) {
            wjglMapper.deleteById(fileid);
        }
    }

    @Override
    public List<WjglResVO> getFileList(WjglPageReqVO pageReqVO) {
        // Same deptId logic as getFilePage
        if (StrUtil.isEmpty(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId("620000");
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return wjglMapper.selectFileList(pageReqVO);
    }
}

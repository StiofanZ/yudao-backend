package cn.iocoder.yudao.module.lghjft.service.sjwh.wjgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo.WjglSaveReqVO;

import java.util.List;

public interface WjglService {
    PageResult<WjglResVO> getFilePage(WjglPageReqVO pageReqVO);

    WjglResVO getFile(Long fileid);

    void createFile(WjglSaveReqVO createReqVO);

    void updateFile(WjglSaveReqVO updateReqVO);

    void deleteFiles(Long[] fileids);

    List<WjglResVO> getFileList(WjglPageReqVO pageReqVO);
}

package cn.iocoder.yudao.module.lghjft.service.cxtj.jftzfswjg;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo.JftzfswjgResVO;

import java.util.List;

public interface JftzfswjgService {

    List<JftzfswjgResVO> getJftzfswjgList(JftzfswjgPageReqVO pageReqVO);
}

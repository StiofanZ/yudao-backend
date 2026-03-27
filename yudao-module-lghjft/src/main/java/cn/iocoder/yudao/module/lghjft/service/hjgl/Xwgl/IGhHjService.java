package cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl;

import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;

public interface IGhHjService {
    /**
     * 根据登记序号获取户籍管理详细信息
     */
    GhHjVO selectGhHjBydjxh(String djxh);
}

package cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;

/**
 * 经费处理-生成划拨文件 Service接口
 * v1: IJfclSchbwjService — selecList + updateGhjfhb
 */
public interface SchbwjService {

    /**
     * v1 selecList: paginated query gh_hkxx LEFT JOIN sys_user
     */
    PageResult<JfclSchbwjDO> getSchbwjPage(SchbwjPageReqVO pageReqVO);

    /**
     * v1 updateGhjfhb: async allocation generation
     */
    String updateGhjfhb(SchbwjSaveReqVO reqVO);
}

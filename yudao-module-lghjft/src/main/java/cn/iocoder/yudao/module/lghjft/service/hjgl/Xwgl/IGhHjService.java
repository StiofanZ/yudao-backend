package cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjAllocationReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;

import java.util.List;
import java.util.Map;

public interface IGhHjService {
    /**
     * 根据登记序号获取户籍管理详细信息
     */
    GhHjVO selectGhHjBydjxh(String djxh);

    /**
     * 户籍管理分页查询
     */
    PageResult<GhHjVO> selectGhHjPage(GhHjPageReqVO pageReqVO);

    /**
     * 户籍管理列表查询（导出用）
     */
    List<GhHjVO> selectGhHjList(GhHjPageReqVO pageReqVO);

    /**
     * 新增户籍
     */
    int insertGhHj(GhHjSaveReqVO saveReqVO);

    /**
     * 批量删除户籍
     */
    int deleteGhHjBydjxhs(String[] djxhs);

    /**
     * 批量复审
     */
    int fushenPlBydjxhs(List<String> djxhs);

    /**
     * 查询纳税人信息（新增户籍用）
     *
     * @param searchNsrKey 纳税人关键字
     * @return 包含 msg / data 的结果
     */
    Map<String, Object> getDjNsrxxInfo(String searchNsrKey);

    /**
     * 查询纳税人信息（更新户籍用，校验当前部门归属）
     *
     * @param djxh   登记序号
     * @param shxydm 社会信用代码
     * @return 包含 msg / data 的结果
     */
    Map<String, Object> getDjNsrxxInfoForUpdateHj(String djxh, String shxydm);

    /**
     * 户籍调拨
     */
    void allocationGhHj(GhHjAllocationReqVO reqVO);
}

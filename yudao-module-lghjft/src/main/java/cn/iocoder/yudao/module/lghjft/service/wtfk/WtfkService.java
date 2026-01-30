package cn.iocoder.yudao.module.lghjft.service.wtfk;

import java.util.*;

import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk.WtfkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 工会经费通-问题反馈 Service 接口
 *
 * @author 李文军
 */
public interface WtfkService {


    /**
     * 创建工会经费通-问题反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWtfk(@Valid WtfkSaveReqVO createReqVO);

    /**
     * 更新工会经费通-问题反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateWtfk(@Valid WtfkSaveReqVO updateReqVO);

    /**
     * 删除工会经费通-问题反馈
     *
     * @param id 编号
     */
    void deleteWtfk(Long id, Boolean isAdminView);

    /**
    * 批量删除工会经费通-问题反馈
    *
    * @param ids 编号
    */
    void deleteWtfkListByIds(List<Long> ids, Boolean isAdminView);

    /**
     * 获得工会经费通-问题反馈
     *
     * @param id 编号
     * @return 工会经费通-问题反馈
     */
    WtfkDO getWtfk(Long id);

    /**
     * 获得工会经费通-问题反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 工会经费通-问题反馈分页
     */
    PageResult<WtfkDO> getWtfkPage(WtfkPageReqVO pageReqVO);

    /**
     * 处理工会经费通-问题反馈
     *
     * @param reqVO 处理信息（包含处理备注、状态等）
     */
    void handleProcess(@Valid WtfkProcessReqVO reqVO);

    /**
     * 获得问题反馈处理日志列表
     * @param feedbackId 反馈编号
     * @return 处理日志列表 (包含操作人姓名 operatorName)
     */
    List<Map<String, Object>> getWtfkLogList(Long feedbackId); // 返回值改为 List<Map>

    /**
     * 获得工会经费通-问题反馈详情（包含处理人姓名）
     *
     * @param id 编号
     * @return 问题反馈详情
     */
    WtfkRespVO getWtfkDetail(Long id);
}
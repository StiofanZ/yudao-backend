package cn.iocoder.yudao.module.lghjft.service.sjwh.hkxxbfzhpc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.HkxxBfzhpcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.HkxxBfzhpcResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo.HkxxBfzhpcSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 拨付信息 Service 接口
 *
 * @author 李文军
 */
public interface HkxxBfzhpcService {

    /**
     * 新增拨付排除（经费级别）
     */
    void createHkxxBfzhpc(@Valid HkxxBfzhpcSaveReqVO createReqVO);

    /**
     * 更新经费排除（更新 gh_hkxx_bfzhpc + gh_jf）
     */
    void updateHkxxBfzhpc(@Valid HkxxBfzhpcSaveReqVO updateReqVO);

    /**
     * 更新账户排除（更新 gh_hkxx_bfzhpc + gh_hj）
     */
    void updateBfzhpc(@Valid HkxxBfzhpcSaveReqVO updateReqVO);

    /**
     * 更新单位排除（更新 gh_hkxx_bfzhpc + gh_hj）
     */
    void updateBfdwpc(@Valid HkxxBfzhpcSaveReqVO updateReqVO);

    /**
     * 批量删除拨付信息
     */
    void deleteHkxxBfzhpcListByIds(List<Integer> ids);

    /**
     * 获得经费排除详情（按spuuid）
     */
    HkxxBfzhpcResVO getHkxxBfzhpc(String spuuid);

    /**
     * 按基层工会账号查账户排除详情
     */
    HkxxBfzhpcResVO getHkxxBfzhpcByJcghzh(String jcghzh);

    /**
     * 按登记序号查单位排除详情
     */
    HkxxBfzhpcResVO getBfdwpcByDjxh(String djxh);

    /**
     * 账户排除分页（FROM bfzhpc）
     */
    PageResult<HkxxBfzhpcResVO> getBfzhpcPage(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 经费排除分页（FROM gh_jf，/page endpoint）
     */
    PageResult<HkxxBfzhpcResVO> getBfjfpcPage(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 单位排除分页（FROM bfzhpc）
     */
    PageResult<HkxxBfzhpcResVO> getBfdwPage(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 经费排除分页（FROM gh_jf，/page-bfjf endpoint）
     */
    PageResult<HkxxBfzhpcResVO> getBfjfPage(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 账户排除导出列表
     */
    List<HkxxBfzhpcResVO> getBfzhpcList(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 单位排除导出列表
     */
    List<HkxxBfzhpcResVO> getBfdwList(HkxxBfzhpcPageReqVO pageReqVO);

    /**
     * 经费排除导出列表
     */
    List<HkxxBfzhpcResVO> getBfjfList(HkxxBfzhpcPageReqVO pageReqVO);
}

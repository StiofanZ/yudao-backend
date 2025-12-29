package cn.iocoder.yudao.module.lghjft.service.wfsqtfsq;

import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqSaveReqVO;

import java.time.LocalDate;
import java.util.List;

/**
 * 申请-退费申请 Service 接口
 *
 * @author 李文军
 */
public interface WfSqTfsqService {

    /**
     * 获得申请退费信息
     *
     * @param id 编号
     * @return 申请退费信息
     */
    WfSqTfsqRespVO getWfSqTfsq(Long id);

    /**
     * 申请退费
     *
     * @param list 退费信息列表
     * @return 退费申请ID
     */
    Long save(List<WfSqTfsqSaveReqVO> list);

    /**
     * 获得可退费信息列表
     *
     * @param djxh   登记序号
     * @param skssqq 税款所属期起
     * @param skssqz 税款所属期止
     * @return 可退费信息列表
     */
    List<WfSqTfsqKtfxxRespVO> getKtfxxList(String djxh, LocalDate skssqq, LocalDate skssqz);

}
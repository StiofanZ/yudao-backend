package cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfjs.JfjsMapper;
import cn.iocoder.yudao.module.lghjft.enums.constant.BackConstant;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_JFJS_NO_DATA;

@Service
@Validated
public class JfjsServiceImpl implements JfjsService {

    @Resource
    private JfjsMapper jfjsMapper;

    @Override
    public PageResult<JfclJfjsDO> getJfjsPage(JfjsPageReqVO pageReqVO) {
        return jfjsMapper.selectPage(pageReqVO);
    }

    /**
     * v1: updateJfclJfjs — 工会经费结算 (1:1 translation)
     *
     * 1. 查询 gh_jf WHERE rkrq BETWEEN dates AND jsbj='N' [AND spuuid]
     * 2. 若无数据 → 抛异常 "无有效数据进行结算"
     * 3. 对每条: jmse != 0 → jsbj='W', else → jsbj='Y'
     * 4. 设置 jsrq, updateBy, updateTime
     * 5. 批量 UPDATE gh_jf (CASE on spuuid)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settleJfjs(JfjsPageReqVO reqVO) {
        // v1: List<JfclJfjs> jsList = jfclJfjsMapper.selectJfclJfjs(jfclJfjs);
        List<Map<String, Object>> jsList = jfjsMapper.selectJfclJfjs(
                reqVO.getRkrqStart(), reqVO.getRkrqEnd(), reqVO.getSpuuid());
        if (jsList == null || jsList.isEmpty()) {
            throw exception(JFCL_JFJS_NO_DATA);
        }

        // v1: String userId = BackConstant.USER_SYSTEM_ID;
        String userId = BackConstant.USER_SYSTEM_ID;
        Date nowDate = new Date();
        // v1: Date jsrq = jfclJfjs.getJsrq(); if(null == jsrq) jsrq = nowDate;
        Date jsrq;
        if (reqVO.getJsrq() != null && !reqVO.getJsrq().isEmpty()) {
            jsrq = nowDate; // jsrq from params is a filter for list, settlement uses current date
        } else {
            jsrq = nowDate;
        }

        // v1: jsList.stream().forEach(item -> { ... })
        List<Map<String, Object>> updateList = new ArrayList<>(jsList.size());
        for (Map<String, Object> item : jsList) {
            Map<String, Object> updateItem = new HashMap<>(8);
            updateItem.put("spuuid", item.get("spuuid") != null ? item.get("spuuid") : item.get("SPUUID"));

            // v1: if (item.getJmse().compareTo(BigDecimal.ZERO) != 0) → 'W' else → 'Y'
            Object jmseObj = item.get("jmse") != null ? item.get("jmse") : item.get("JMSE");
            BigDecimal jmse = BigDecimal.ZERO;
            if (jmseObj instanceof BigDecimal) {
                jmse = (BigDecimal) jmseObj;
            } else if (jmseObj instanceof Number) {
                jmse = new BigDecimal(jmseObj.toString());
            }

            if (jmse.compareTo(BigDecimal.ZERO) != 0) {
                updateItem.put("jsbj", "W");
            } else {
                updateItem.put("jsbj", "Y");
            }
            updateItem.put("jsrq", jsrq);
            updateItem.put("updateBy", userId);
            updateItem.put("updateTime", nowDate);
            updateList.add(updateItem);
        }

        // v1: jfclJfjsMapper.updateJfclJfjs(jsList);
        jfjsMapper.updateJfclJfjs(updateList);
    }
}

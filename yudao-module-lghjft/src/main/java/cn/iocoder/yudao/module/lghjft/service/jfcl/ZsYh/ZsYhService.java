package cn.iocoder.yudao.module.lghjft.service.jfcl.ZsYh;



import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 招商银行接口
 * @author shipj
 */
public interface ZsYhService {

    /**
     * 4. 企银支付批量经办BB1PAYBH
     *
     * @param bodyParam
     * @param fkId
     * @return
     */
    public Map<String, JSONObject> qyzfpljbFc(Map<String, Object> bodyParam, String fkId);

    /**
     * 5. 企银批量支付批次查询BB1QRYBT
     * @param bodyParam
     * @param fkId
     * @return
     */
    public Map<String, JSONObject> qyplzfpccxFc(Map<String, Object> bodyParam, String fkId);

    /**
     * 6.企银批量支付明细查询BB1QRYBD
     * @param bodyParam
     * @param fkId
     * @return
     */
    public Map<String, JSONObject> qyplzfmxcxFc(Map<String, Object> bodyParam, String fkId);

    /**
     * 7. 支付退票明细查询BB1PAYQB
     * @param bodyParam
     * @param fkId
     * @return
     */
    public Map<String, JSONObject> zpthmxcxFc(Map<String, Object> bodyParam, String fkId);

    /**
     * 8.根据退票日期查询退票记录（BB1QRYOB）
     * @param bodyParam
     * @param fkId
     * @return
     */
    public Map<String, JSONObject> tprqcxtpjlFc(Map<String, Object> bodyParam, String fkId);


}

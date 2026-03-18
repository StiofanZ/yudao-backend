package cn.iocoder.yudao.module.lghjft.service.jfcl.ZsYh;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx.YhbfmxDO;
import cn.iocoder.yudao.module.lghjft.service.jfcl.cmb.CmbUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shipj
 */
@Service
@Slf4j
public class ZsYhServiceImpl extends ZsYhServiceBase implements ZsYhService {

    private static Logger logger = LoggerFactory.getLogger(ZsYhServiceImpl.class);

    /**
     * 企银支付批量经办BB1PAYBH
     * @param bodyParam
     * @param fkId
     * @return
     */
    @Override
    public Map<String, JSONObject> qyzfpljbFc(Map<String, Object> bodyParam, String fkId) {
        return getFcMap(yqzfpljb, geTqyzfpljbBody(bodyParam), fkId);
    }

    /**
     * 企银批量支付批次查询BB1QRYBT
     * @param bodyParam
     * @param fkId
     * @return
     */
    @Override
    public Map<String, JSONObject> qyplzfpccxFc(Map<String, Object> bodyParam, String fkId) {
        return getFcMap(qyplzfpccx, getQyplzfpccxBody(bodyParam), fkId);
    }

    /**
     * 企银批量支付明细查询BB1QRYBD
     * @param bodyParam
     * @param fkId
     * @return
     */
    @Override
    public Map<String, JSONObject> qyplzfmxcxFc(Map<String, Object> bodyParam, String fkId) {
        return getFcMap(qyzfmxcx, geQyplzfmxcxBody(bodyParam), fkId);
    }

    /**
     * 支付退票明细查询BB1PAYQB
     * @param bodyParam
     * @param fkId
     * @return
     */
    @Override
    public Map<String, JSONObject> zpthmxcxFc(Map<String, Object> bodyParam, String fkId) {
        return getFcMap(zpthmxcx, getZpthmxcxBody(bodyParam), fkId);
    }

    /**
     * 根据退票日期查询退票记录
     * @param bodyParam
     * @param fkId
     * @return
     */
    @Override
    public Map<String, JSONObject> tprqcxtpjlFc(Map<String, Object> bodyParam, String fkId) {
        return getFcMap(tprqcxtpjl, getTprqcxtpjlBody(bodyParam), fkId);
    }

    private JSONObject geTqyzfpljbBody(Map<String,Object> bodyParam) {
        YhbfhzDO ghHkxxYhbfhz = (YhbfhzDO) bodyParam.get("ghHkxxYhbfhz");
        List<YhbfmxDO> ghHkxxYhbfmxs = (List<YhbfmxDO>) bodyParam.get("ghHkxxYhbfmxs");
        //汇总信息
        JSONArray bb1bmdbhx1Array = new JSONArray();
        JSONObject bb1bmdbhx1Obj = new JSONObject();
        bb1bmdbhx1Obj.put("busMod", busMod); // 业务模式（模式编号） busMod
        bb1bmdbhx1Obj.put("busCod", busCod); // 业务类型（业务代码） busCod
        bb1bmdbhx1Obj.put("bthNbr", ghHkxxYhbfhz.getBfhzpch()); // 批次编号 bthNbr
        bb1bmdbhx1Obj.put("dtlNbr", String.valueOf(ghHkxxYhbfmxs.size())); // 总明细笔数 dtlNbr
        bb1bmdbhx1Obj.put("ctnFlg", "N"); // 续传标志 ctnFlg
        bb1bmdbhx1Obj.put("ctnSts", StringUtils.EMPTY); //续传状态 ctnSts
        bb1bmdbhx1Array.add(bb1bmdbhx1Obj);
        //支付信息
        JSONArray bb1paybhx1Array = new JSONArray();
        for (YhbfmxDO ghHkxxYhbfmx : ghHkxxYhbfmxs) {
            JSONObject bb1paybhx1Obj = new JSONObject();
            bb1paybhx1Obj.put("dbtAcc", dbtAcc); // 转出帐号 dbtAcc
            bb1paybhx1Obj.put("dmaNbr", ghHkxxYhbfmx.getFfjzzdy()); // 记账子单元编号 dmaNbr
            bb1paybhx1Obj.put("crtAcc", ghHkxxYhbfmx.getZh()); // 收方帐号 crtAcc
            bb1paybhx1Obj.put("crtNam", ghHkxxYhbfmx.getHm()); // 收方户名 crtNam
            bb1paybhx1Obj.put("crtBnk", ghHkxxYhbfmx.getYhmc()); // 收方开户行名称 crtBnk
            bb1paybhx1Obj.put("crtAdr", ghHkxxYhbfmx.getSkrkhd()); //收方开户行地址 crtAdr
            bb1paybhx1Obj.put("brdNbr", ghHkxxYhbfmx.getSflhh()); //收方行联行号 brdNbr
            bb1paybhx1Obj.put("ccyNbr", ccyNbr); //币种 ccyNbr
            bb1paybhx1Obj.put("trsAmt", String.valueOf(ghHkxxYhbfmx.getJe().setScale(2, RoundingMode.HALF_UP))); //交易金额 trsAmt
            bb1paybhx1Obj.put("eptDat", DateUtil.date().toString("yyyy-MM-dd")); //期望日 eptDat
            bb1paybhx1Obj.put("eptTim", DateUtil.date().toString("HH:mm:ss")); //期望时间 eptTim
            bb1paybhx1Obj.put("bnkFlg", "N"); //系统内标志 bnkFlg
            bb1paybhx1Obj.put("stlChn", StringUtils.EMPTY); //结算通道 stlChn
            bb1paybhx1Obj.put("nusAge", CmbUtil.subStr(ghHkxxYhbfmx.getYt(),100)); //用途 nusAge
            bb1paybhx1Obj.put("crtSqn", String.valueOf(ghHkxxYhbfmx.getHkxxId())); //收方编号 crtSqn
            bb1paybhx1Obj.put("yurRef", String.valueOf(ghHkxxYhbfmx.getBfid())); //业务参考号 yurRef
            bb1paybhx1Obj.put("busNar", CmbUtil.subStr(ghHkxxYhbfmx.getFy(),100)); //业务摘要 busNar
            bb1paybhx1Obj.put("ntfCh1", CmbUtil.getNewStr(Arrays.toString(ghHkxxYhbfmx.getSfyjdz()))); //通知方式一（邮箱） ntfCh1
            bb1paybhx1Obj.put("ntfCh2", CmbUtil.getNewStr(Arrays.toString(ghHkxxYhbfmx.getSfyddh()))); //通知方式二（手机号） ntfCh2
            bb1paybhx1Obj.put("trsTyp", ghHkxxYhbfmx.getYwzl()); //业务种类 trsTyp
            bb1paybhx1Obj.put("rcvChk", StringUtils.EMPTY); //行内收方账号户名校验 rcvChk
            bb1paybhx1Obj.put("drpFlg", StringUtils.EMPTY); //直汇普通标志 drpFlg
            bb1paybhx1Obj.put("trxAmt", StringUtils.EMPTY); //相应金额 trxAmt
            bb1paybhx1Obj.put("ctrNbr", "N/A"); //合同号 ctrNbr
            bb1paybhx1Obj.put("invNbr", "N/A"); //发票号 invNbr
            bb1paybhx1Obj.put("rsvAmt", StringUtils.EMPTY); //预留金额 rsvAmt
            bb1paybhx1Obj.put("rsvNa1", StringUtils.EMPTY); //预留摘要１ rsvNa1
            bb1paybhx1Obj.put("rsvNa2", StringUtils.EMPTY); //预留摘要２ rsvNa2
            bb1paybhx1Obj.put("rsvNb1", StringUtils.EMPTY); //预留编号１ rsvNb1
            bb1paybhx1Obj.put("rsvNb2", StringUtils.EMPTY); //预留编号２ rsvNb2
            bb1paybhx1Obj.put("remNbr", StringUtils.EMPTY); //非居民附言编号 remNbr
            bb1paybhx1Obj.put("splC80", StringUtils.EMPTY); //特殊码 splC80
            bb1paybhx1Array.add(bb1paybhx1Obj);
        }
        JSONObject reqObj = new JSONObject();
        reqObj.put("bb1bmdbhx1",bb1bmdbhx1Array);
        reqObj.put("bb1paybhx1",bb1paybhx1Array);
        return reqObj;
    }

    private JSONObject geQyplzfmxcxBody(Map<String, Object> bodyParam) {
        YhbfhzDO ghHkxxYhbfhz = (YhbfhzDO) bodyParam.get("ghHkxxYhbfhz");
        //查询条件
        JSONArray bb1qrybdy1Array = new JSONArray();
        JSONObject eObj = new JSONObject();
        eObj.put("bthNbr", ghHkxxYhbfhz.getBfhzpch()); // 批次编号 bthNbr
        eObj.put("autStr", StringUtils.EMPTY); // 请求状态 autStr
        eObj.put("rtnStr", StringUtils.EMPTY); // 处理结果 rtnStr
        eObj.put("ctnKey", StringUtils.EMPTY); // 续传键值 ctnKey
        bb1qrybdy1Array.add(eObj);
        JSONObject reqObj = new JSONObject();
        reqObj.put("bb1qrybdy1",bb1qrybdy1Array);
        return reqObj;
    }

    private JSONObject getQyplzfpccxBody(Map<String, Object> bodyParam) {
        JSONArray bb1qrybtx1Array = new JSONArray();
        JSONObject eObj = new JSONObject();
        eObj.put("begDat", bodyParam.get("begDat")); // 当前日期-1个月
        eObj.put("endDat", bodyParam.get("endDat")); // 默认当前日期
        eObj.put("autStr", StringUtils.EMPTY); // 请求状态 autStr
        eObj.put("rtnStr", StringUtils.EMPTY); // 处理结果 rtnStr
        bb1qrybtx1Array.add(eObj);
        JSONObject reqObj = new JSONObject();
        reqObj.put("bb1qrybtx1",bb1qrybtx1Array);
        return reqObj;
    }

    private JSONObject getZpthmxcxBody(Map<String, Object> bodyParam) {
        JSONArray bb1payqby1Array = new JSONArray();
        JSONObject eObj = new JSONObject();
        eObj.put("bbkNbr", StringUtils.EMPTY); //分行号 bbkNbr
        eObj.put("accNbr", bodyParam.get("accNbr")); // 账号 accNbr
        eObj.put("bgnDat", bodyParam.get("bgnDat")); // 开始日期 bgnDat
        eObj.put("endDat", bodyParam.get("endDat")); // 结束日期 endDat
        eObj.put("reqNbr", StringUtils.EMPTY); // 流程实例号 reqNbr
        eObj.put("ctnKey", StringUtils.EMPTY); //续传键值 ctnKey
        eObj.put("rsv50z", StringUtils.EMPTY); //保留字段 rsv50z
        bb1payqby1Array.add(eObj);
        JSONObject reqObj = new JSONObject();
        reqObj.put("bb1payqby1",bb1payqby1Array);
        return reqObj;
    }

    private JSONObject getTprqcxtpjlBody(Map<String, Object> bodyParam) {
        JSONArray bb1qryoby1Array = new JSONArray();
        JSONObject eObj = new JSONObject();
        eObj.put("startDate", bodyParam.get("startDate")); //开始日期 startDate
        eObj.put("endDate", bodyParam.get("endDate")); // 结束日期 endDate
        eObj.put("continueKey", StringUtils.EMPTY); // 续传值 continueKey
        bb1qryoby1Array.add(eObj);
        JSONObject reqObj = new JSONObject();
        reqObj.put("bb1qryoby1",bb1qryoby1Array);
        return reqObj;
    }

}
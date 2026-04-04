package cn.iocoder.yudao.module.lghjft.service.jfcl.ZsYh;

import cn.hutool.core.map.MapUtil;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhzjkjl.YhbfhzjkjlDO;
import cn.iocoder.yudao.module.lghjft.enums.constant.BackConstant;
import cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhzjkjl.YhbfhzjkjlService;
import cn.iocoder.yudao.module.lghjft.service.jfcl.cmb.CmbUtil;
import cn.iocoder.yudao.module.lghjft.service.jfcl.cmb.DcHelper;
import cn.iocoder.yudao.module.lghjft.service.jfcl.cmb.DeviceInfo;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 招商银行基类
 * @author shipj
 */
@Component
@Slf4j
public abstract class ZsYhServiceBase {

    private static Logger logger = LoggerFactory.getLogger(ZsYhServiceBase.class);

    @Autowired
    protected YhbfhzjkjlService yhbfhzjkjlService;

    protected final String QQZT_S = "S";

    protected final String QQZT_E = "E";

    protected final String BODY_OBJ = "bodyObj";

    protected final String HEAD_OBJ = "headObj";

    // 测试地址
    @Value("${zsyh.api.url}")
    protected String url;

    // 银行公钥
    @Value("${zsyh.api.publicKey}")
    protected String publicKey;

    // 客户私钥
    @Value("${zsyh.api.privateKey}")
    protected String privateKey;

    // 对称密钥
    @Value("${zsyh.api.symKey}")
    protected String symKey;

    // 企业网银用户号
    @Value("${zsyh.api.uid}")
    protected String uid;

    // 出口IP(可为空) 36.142.141.155
    @Value("${zsyh.di.ipAddr}")
    protected String ipAddr;

    // MAC地址 48E7DAABAA85
    @Value("${zsyh.di.macAddr}")
    protected String macAddr;

    //CPU-ID 178BFBFF00A50F00
    @Value("${zsyh.di.cpuId}")
    protected String cpuId;

    //计算机名 LAPTOP-C4T1ODFM
    @Value("${zsyh.di.computerName}")
    protected String computerName;

    //主板ID MBN0CX24A28846A
    @Value("${zsyh.di.mainBoardId}")
    protected String mainBoardId;

    //主板厂商 ASUSTeK COMPUTER INC.
    @Value("${zsyh.di.mainBoardName}")
    protected String mainBoardName;

    //业务模式（模式编号）
    @Value("${zsyh.fc.yqzfpljb.bb1bmdbhx1.busMod}")
    protected String busMod;

    //业务类型（业务代码）
    @Value("${zsyh.fc.yqzfpljb.bb1bmdbhx1.busCod}")
    protected String busCod;

    //转出帐号
    @Value("${zsyh.fc.yqzfpljb.bb1paybhx1.dbtAcc}")
    protected String dbtAcc;

    //币种 ccyNb
    @Value("${zsyh.fc.yqzfpljb.bb1paybhx1.ccyNbr}")
    protected String ccyNbr;

    //行内收方账号户名校验 rcvChk
    @Value("${zsyh.fc.yqzfpljb.bb1paybhx1.rcvChk}")
    protected String rcvChk;

    //企银支付批量经办
    @Value("${zsyh.fc.yqzfpljb.mc}")
    protected String yqzfpljb;

    //企银批量支付明细查询
    @Value("${zsyh.fc.qyzfmxcx.mc}")
    protected String qyzfmxcx;

    //企银批量支付批次查询
    @Value("${zsyh.fc.qyplzfpccx.mc}")
    protected String qyplzfpccx;

    //支付退票明细查询
    @Value("${zsyh.fc.zpthmxcx.mc}")
    protected String zpthmxcx;

    //根据退票日期查询退票记录
    @Value("${zsyh.fc.tprqcxtpjl.mc}")
    protected String tprqcxtpjl;

    //获取 DcHelper
    protected DcHelper getDcHelper() {
        return new DcHelper(url, uid, privateKey, publicKey, symKey);
    }

    //获取DeviceInfo
    protected DeviceInfo getDeviceInfo() {
        return new DeviceInfo(ipAddr, macAddr, cpuId, computerName, mainBoardId, mainBoardName);
    }

    protected String getReqStr(JSONObject body, JSONObject head) {
        JSONObject requestObj = new JSONObject();
        requestObj.put("body", body);
        requestObj.put("head", head);
        JSONObject obj = new JSONObject();
        obj.put("request", requestObj);

        JSONObject signatureObj = new JSONObject();
        signatureObj.put("sigdat", "__signature_sigdat__");
        signatureObj.put("sigtim", CmbUtil.getCurrentDatetime());

        obj.put("signature", signatureObj);
        return obj.toJSONString();
    }

    protected JSONObject getHead(String funcode, String userid, String reqid) {
        logger.debug("funcode={}，reqid={}，uid={}", funcode, reqid, uid);
        JSONObject headObj = new JSONObject();
        headObj.put("funcode", funcode);
        headObj.put("userid", userid);
        headObj.put("reqid", reqid);
        return headObj;
    }

    protected JSONObject getBody(Map<String, Object> param) {
        return null;
    }

    protected Map<String, JSONObject> handleRes(String resStr) {
        JSONObject resObj = JSONObject.parseObject(resStr);
        JSONObject responseObj = resObj.getJSONObject("response");
        JSONObject bodyObj = responseObj.getJSONObject("body");
        JSONObject headObj = responseObj.getJSONObject("head");
        Map<String, JSONObject> resMap = MapUtil.newHashMap(2);
        resMap.put(BODY_OBJ,bodyObj);
        resMap.put(HEAD_OBJ,headObj);
        return resMap;
    }

    protected void updateYhbfhzjkjl(YhbfhzjkjlDO yhbfhzjkjlDO, String fkId) {
        if (Objects.isNull(yhbfhzjkjlDO)) {
            logger.warn("日志对象 yhbfhzjkjlDO 为空，本次不会写入请求接口信息！");
        }
        String userId = BackConstant.USER_SYSTEM_ID;
        if (!Objects.isNull(fkId)) {
            yhbfhzjkjlDO.setFkId(Long.parseLong(fkId));
        }
        yhbfhzjkjlDO.setCreateBy(userId);
        yhbfhzjkjlDO.setCreateTime(DateUtils.getNowDate());
        yhbfhzjkjlDO.setUpdateBy(userId);
        yhbfhzjkjlDO.setUpdateTime(DateUtils.getNowDate());
        yhbfhzjkjlService.insertYhbfhzJkjl(yhbfhzjkjlDO);
    }

    protected Map<String, JSONObject> getFcMap(String fc, JSONObject bodyStr, String fkId) {
        Map<String, JSONObject> resMap = Maps.newHashMap();
        YhbfhzjkjlDO yhbfhzjkjlDO = new YhbfhzjkjlDO();
        try {
            yhbfhzjkjlDO.setJkmc(fc);
            String reqid = CmbUtil.getReqid();
            JSONObject headStr = getHead(fc, uid, reqid);
            logger.debug("headStr:" + headStr);
            logger.debug("bodyStr:" + bodyStr);
            String reqStr = getReqStr(bodyStr, headStr);
            yhbfhzjkjlDO.setQqbw(reqStr);
            logger.info("reqStr:" + reqStr);
            DcHelper dchelper = getDcHelper();
            DeviceInfo deviceInfo = getDeviceInfo();
            String resStr = dchelper.sendRequest(reqStr, fc, deviceInfo);
            yhbfhzjkjlDO.setXybw(resStr);
            handleRes(resStr);
            logger.info("resStr:" + resStr);
            yhbfhzjkjlDO.setQqzt(QQZT_S);
            resMap = handleRes(resStr);
            JSONObject headObj = resMap.get(HEAD_OBJ);
            yhbfhzjkjlDO.setQqjgxx(headObj.getString("resultmsg"));
        } catch (Exception e) {
            yhbfhzjkjlDO.setQqzt(QQZT_E);
            yhbfhzjkjlDO.setQqjgxx(e.toString());
            logger.error(fc + "调用异常：", e);
            throw new ServiceException(fc + "调用异常" + e.getMessage());
        }finally {
            updateYhbfhzjkjl(yhbfhzjkjlDO, fkId);
        }
        return resMap;
    }

}

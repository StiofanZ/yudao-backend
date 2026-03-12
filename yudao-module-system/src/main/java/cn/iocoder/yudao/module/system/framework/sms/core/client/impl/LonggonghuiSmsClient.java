package cn.iocoder.yudao.module.system.framework.sms.core.client.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.core.KeyValue;
import cn.iocoder.yudao.framework.common.util.collection.MapUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.system.framework.sms.core.client.dto.SmsReceiveRespDTO;
import cn.iocoder.yudao.module.system.framework.sms.core.client.dto.SmsSendRespDTO;
import cn.iocoder.yudao.module.system.framework.sms.core.client.dto.SmsTemplateRespDTO;
import cn.iocoder.yudao.module.system.framework.sms.core.enums.SmsTemplateAuditStatusEnum;
import cn.iocoder.yudao.module.system.framework.sms.core.property.SmsChannelProperties;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 陇工惠短信客户端。
 * <p>
 * 验证码能力按已提供文档实现，通知短信能力按渠道扩展参数驱动，
 * 这样业务层只依赖统一短信服务，不感知第三方协议。
 */
@Slf4j
public class LonggonghuiSmsClient extends AbstractSmsClient {

    private static final String MRL_YZM_JKDZ = "/gbs/admin/sys/sms/sendMobileCode";

    public LonggonghuiSmsClient(SmsChannelProperties properties) {
        super(properties);
    }

    @Override
    public SmsSendRespDTO sendSms(Long sendLogId, String mobile, String apiTemplateId, String templateContent,
                                  List<KeyValue<String, Object>> templateParams) {
        Map<String, Object> csMap = MapUtils.convertMap(templateParams);
        if (csMap.containsKey("code")) {
            return fsYzm(sendLogId, mobile, apiTemplateId, csMap);
        }
        return fsTzdx(sendLogId, mobile, apiTemplateId, templateContent, csMap);
    }

    @Override
    public List<SmsReceiveRespDTO> parseSmsReceiveStatus(String text) {
        throw new UnsupportedOperationException("陇工惠短信客户端暂未实现回执解析");
    }

    @Override
    public SmsTemplateRespDTO getSmsTemplate(String apiTemplateId) {
        return new SmsTemplateRespDTO().setId(StrUtil.blankToDefault(apiTemplateId, "LONGGONGHUI"))
                .setContent("").setAuditStatus(SmsTemplateAuditStatusEnum.SUCCESS.getStatus()).setAuditReason("");
    }

    private SmsSendRespDTO fsYzm(Long rzId, String sjhm, String apiTemplateId, Map<String, Object> csMap) {
        Map<String, Object> yzm = hqKzcs("yzm");
        String wgdz = hqZsDz(yzm, "wgdz", MRL_YZM_JKDZ);
        Map<String, Object> qqcs = new LinkedHashMap<>();
        qqcs.put("mobile", sjhm);
        qqcs.put("code", MapUtil.getStr(csMap, "code"));
        return zxQingqiu(rzId, wgdz, yzm, qqcs, apiTemplateId);
    }

    private SmsSendRespDTO fsTzdx(Long rzId, String sjhm, String apiTemplateId, String dxnr, Map<String, Object> csMap) {
        Map<String, Object> tzdx = hqKzcs("tzdx");
        String jkdz = hqStr(tzdx, "jkdz");
        boolean fyYzmJk = StrUtil.isBlank(jkdz) || StrUtil.endWithIgnoreCase(jkdz, "sendMobileCode");
        String wgdz = hqZsDz(tzdx, "wgdz", fyYzmJk ? MRL_YZM_JKDZ : jkdz);
        Map<String, Object> qqcs = new LinkedHashMap<>();
        qqcs.put(hqStr(tzdx, "sjhmzd", "mobile"), sjhm);
        qqcs.put(hqStr(tzdx, "nrzd", fyYzmJk ? "code" : "content"), dxnr);
        String mbdmzd = hqStr(tzdx, "mbdmzd");
        if (StrUtil.isNotBlank(mbdmzd)) {
            qqcs.put(mbdmzd, StrUtil.blankToDefault(apiTemplateId, hqStr(tzdx, "mbdm")));
        } else if (!fyYzmJk && StrUtil.isNotBlank(apiTemplateId)) {
            qqcs.put("templateCode", apiTemplateId);
        }
        if (!csMap.isEmpty()) {
            qqcs.put(hqStr(tzdx, "fjcszd", "templateParams"), csMap);
        }
        return zxQingqiu(rzId, wgdz, tzdx, qqcs, apiTemplateId);
    }

    private SmsSendRespDTO zxQingqiu(Long rzId, String dz, Map<String, Object> pz, Map<String, Object> qqcs,
                                     String apiTemplateId) {
        yzPz(pz, dz);
        String yyid = hqStr(pz, "yyid");
        String pt = hqStr(pz, "pt", yyid);
        String jmmy = hqStr(pz, "jmmy");
        String qmmy = hqStr(pz, "qmmy");
        String requestId = IdUtil.fastSimpleUUID();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = MD5.create().digestHex("appId=" + yyid + "&requestId=" + requestId
                + "&timestamp=" + timestamp + "&secret=" + qmmy);

        Map<String, Object> tokenData = new LinkedHashMap<>();
        tokenData.put("appId", yyid);
        tokenData.put("platform", pt);
        tokenData.put("requestId", requestId);
        tokenData.put("sign", sign);
        tokenData.put("timestamp", timestamp);
        tokenData.put("token", StrUtil.blankToDefault(hqStr(pz, "token"), ""));

        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, jmmy.getBytes(StandardCharsets.UTF_8));
        String token = Base64.encodeUrlSafe(aes.encrypt(JsonUtils.toJsonString(tokenData).getBytes(StandardCharsets.UTF_8)));
        String params = Base64.encodeUrlSafe(aes.encrypt(JsonUtils.toJsonString(qqcs).getBytes(StandardCharsets.UTF_8)));
        String qqfs = hqStr(pz, "qqfs", "GET").toUpperCase();

        HttpResponse resp;
        if ("POST".equals(qqfs)) {
            resp = HttpRequest.post(dz)
                    .header("appId", yyid)
                    .header("token", token)
                    .form("params", params)
                    .execute();
        } else {
            resp = HttpRequest.get(dz)
                    .header("appId", yyid)
                    .header("token", token)
                    .form("params", params)
                    .execute();
        }
        return jxResp(resp, rzId, apiTemplateId);
    }

    private SmsSendRespDTO jxResp(HttpResponse resp, Long rzId, String apiTemplateId) {
        String body = resp.body();
        if (!resp.isOk()) {
            return new SmsSendRespDTO().setSuccess(false).setApiCode(String.valueOf(resp.getStatus()))
                    .setApiMsg(StrUtil.blankToDefault(body, "HTTP 请求失败"));
        }
        JSONObject json;
        try {
            json = JSONUtil.parseObj(body);
        } catch (Exception ex) {
            log.warn("[jxResp][陇工惠短信响应不是标准 JSON，按成功处理。rzId={}, apiTemplateId={}, body={}]", rzId, apiTemplateId, body);
            return new SmsSendRespDTO().setSuccess(true).setApiCode("0").setApiMsg("ok")
                    .setApiRequestId(String.valueOf(rzId)).setSerialNo(String.valueOf(rzId));
        }
        Integer code = json.getInt("code");
        boolean cg = code != null && code == 0;
        return new SmsSendRespDTO().setSuccess(cg)
                .setApiCode(String.valueOf(code))
                .setApiMsg(json.getStr("msg"))
                .setApiRequestId(json.getByPath("data.requestId", String.class))
                .setSerialNo(StrUtil.blankToDefault(json.getByPath("data.serialNo", String.class), String.valueOf(rzId)));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> hqKzcs(String key) {
        Map<String, Object> kzcs = properties.getKzcs();
        Object value = kzcs != null ? kzcs.get(key) : null;
        if (value instanceof Map<?, ?> valueMap) {
            return (Map<String, Object>) valueMap;
        }
        throw new IllegalArgumentException("陇工惠短信渠道缺少 " + key + " 配置");
    }

    private String hqZsDz(Map<String, Object> pz, String wgdzKey, String mrlJkdz) {
        String wgdz = hqStr(pz, wgdzKey);
        String jkdz = hqStr(pz, "jkdz", mrlJkdz);
        if (StrUtil.isBlank(wgdz)) {
            return jkdz;
        }
        if (StrUtil.startWith(wgdz, "http://") || StrUtil.startWith(wgdz, "https://")) {
            return StrUtil.removeSuffix(wgdz, "/") + (StrUtil.startWith(jkdz, "/") ? jkdz : "/" + jkdz);
        }
        return wgdz;
    }

    private void yzPz(Map<String, Object> pz, String dz) {
        if (StrUtil.hasBlank(dz, hqStr(pz, "yyid"), hqStr(pz, "jmmy"), hqStr(pz, "qmmy"))) {
            throw new IllegalArgumentException("陇工惠短信渠道配置不完整，请补齐 yyid/jmmy/qmmy/wgdz");
        }
    }

    private String hqStr(Map<String, Object> map, String key) {
        return hqStr(map, key, null);
    }

    private String hqStr(Map<String, Object> map, String key, String mrz) {
        String value = map == null ? null : MapUtil.getStr(map, key);
        return StrUtil.blankToDefault(value, mrz);
    }
}

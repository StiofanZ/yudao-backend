package cn.iocoder.yudao.module.lghjft.service.jfcl.cmb;

import com.google.gson.JsonObject;

/**
 * 设备信息
 */
public class DeviceInfo {

    private String ipVal;
    private String macVal;
    private String cpuInfo;
    private String computerName;
    private String mainBoardId;
    private String mainBoardName;

    //添加设备信息，请根据文档指引填写真实的信息，参数按顺序分别为：出口IP(无需)、MAC地址、CPU-ID、计算机名、主板ID、主板厂商
    public DeviceInfo(String ipVal, String macVal, String cpuInfo, String computerName, String mainBoardId, String mainBoardName) {
        this.ipVal = ipVal;
        this.macVal = macVal;
        this.cpuInfo = cpuInfo;
        this.computerName = computerName;
        this.mainBoardId = mainBoardId;
        this.mainBoardName = mainBoardName;
    }

    public JsonObject toJson() {
        JsonObject deviceInfo = new JsonObject();
        if (ipVal != null) {
            deviceInfo.addProperty("ipType", ipVal.indexOf(':') != -1 ? "6" : "4");
            deviceInfo.addProperty("ipVal", ipVal);
        }
        deviceInfo.addProperty("macVal", macVal);
        deviceInfo.addProperty("cpuInfo", cpuInfo);
        deviceInfo.addProperty("cmpName", computerName);
        deviceInfo.addProperty("mbId", mainBoardId);
        deviceInfo.addProperty("mbMf", mainBoardName);
        return deviceInfo;
    }
}

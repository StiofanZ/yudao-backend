package cn.iocoder.yudao.module.lghjft.service.jfcl.cmb;

import java.io.Serializable;

/**
 * @author shipj
 */
public class CmbQueryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    //请求状态
    private String autStr;

    //处理结果
    private String rtnStr;

    //起始日期
    private String startDate;

    //结束日期
    private String endDate;

    //结束日期
    private String continueKey;

    //账号
    private String accNbr;

    public String getAutStr() {
        return autStr;
    }

    public void setAutStr(String autStr) {
        this.autStr = autStr;
    }

    public String getRtnStr() {
        return rtnStr;
    }

    public void setRtnStr(String rtnStr) {
        this.rtnStr = rtnStr;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContinueKey() {
        return continueKey;
    }

    public void setContinueKey(String continueKey) {
        this.continueKey = continueKey;
    }

    public String getAccNbr() {
        return accNbr;
    }

    public void setAccNbr(String accNbr) {
        this.accNbr = accNbr;
    }
}

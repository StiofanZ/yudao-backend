package cn.iocoder.yudao.module.lghjft.service.jfcl.cmb;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author shipj
 */
public class CmbUtil {

    protected static final int BOUND_START = 1000000;

    protected static final int BOUND_END = 9000000;

    private static Random random = new Random();

    public static String getCurrentDatetime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String getReqid() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + (BOUND_START + random.nextInt(BOUND_END));
    }

    public static String subStr(String str, int maxLength) {
        if(StringUtils.isBlank(str)){
            return StringUtils.EMPTY;
        }
        if (str.length() > maxLength * 2) { // 如果文本超过了最大截取长度，则进行截取操作
            return str.substring(0, maxLength);
        } else {
            return str;
        }
    }

    public static String getNewStr(String str) {
        return StringUtils.isNotBlank(str) ? str : StringUtils.EMPTY;
    }
}

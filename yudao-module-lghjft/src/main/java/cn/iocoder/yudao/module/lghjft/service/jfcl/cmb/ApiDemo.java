package cn.iocoder.yudao.module.lghjft.service.jfcl.cmb;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 招商银行银企直联国密免前置/SaaS对接示例，本示例仅供参考，不保证各种异常场景运行，请勿直接使用，如有错漏请联系对接人员。运行时，请使用所获取的测试资源替换 用户编号、公私钥、对称密钥、服务商编号等信息。
 *
 * @author cmb.firmbank
 * @date 2023/7/20
 */
public class ApiDemo {

    private static final int BOUND_START = 1000000;
    private static final int BOUND_END = 9000000;
    // 测试地址，生产需要替换
    private static String url = "http://cdctest.cmburl.cn/cdcserver/api/v2";
    // 生产地址
    //private static String url = "https://cdc.cmbchina.com/cdcserver/api/v2";
    // 银行公钥
    private static String publicKey = "BNsIe9U0x8IeSe4h/dxUzVEz9pie0hDSfMRINRXc7s1UIXfkExnYECF4QqJ2SnHxLv3z/99gsfDQrQ6dzN5lZj0=";
    // 客户私钥，生产需要替换
    private static String privateKey = "NBtl7WnuUtA2v5FaebEkU0/Jj1IodLGT6lQqwkzmd2E=";
    // 对称密钥，生产需要替换
    private static String symKey = "VuAzSWQhsoNqzn0K";

    // 企业网银用户号，生产需要替换
    private static String uid = "U005589286";

    private static Random random = new Random();

    private ApiDemo() {
    }

    public static void main(String[] args) throws Exception {
        // 装载BC库,必须在应用的启动类中调用此函数
        Security.addProvider(new BouncyCastleProvider());

        // 业务接口名，这里是查询业务模式接口，生产请替换为对应接口名
        String funcode = "DCLISMOD";
        // 准备接口数据，生产请替换为具体接口请求报文,包含所需的请求字段
        String currentDatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reqid = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + (BOUND_START + random.nextInt(BOUND_END));
        String data = "{\"request\":{\"body\":{\"buscod\":\"N02030\"},\"head\":{\"funcode\":\"" + funcode + "\",\"userid\":\"" + uid + "\",\"reqid"
                + "\":\"" + reqid + "\"}},\"signature\":{\"sigdat\":\"__signature_sigdat__\",\"sigtim\":\"" + currentDatetime + "\"}}";
        System.out.println("data:\r\n" + data);
        DcHelper dchelper = new DcHelper(url, uid, privateKey, publicKey, symKey);
        // 添加设备信息，请根据文档指引填写真实的信息，参数按顺序分别为：出口IP(无需)、MAC地址、CPU-ID、计算机名、主板ID、主板厂商
        DeviceInfo deviceInfo = new DeviceInfo(null, "48E7DAABAA85", "178BFBFF00A50F00", "LAPTOP-C4T1ODFM", "MBN0CX24A28846A", "ASUSTeK COMPUTER INC.");
        String response = dchelper.sendRequest(data, funcode, deviceInfo);
        process("Api请求1成功,响应报文:\r\n" + response);
        System.out.println("Api请求1成功,响应报文:\r\n" + response);
        String response2 = dchelper.sendRequest(data, funcode, deviceInfo);
        process("Api请求2成功,响应报文:\r\n" + response2);
        System.out.println("Api请求2成功,响应报文:\r\n" + response);
    }

    private static void process(String response) {

    }
}

package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.jeecgframework.poi.excel.annotation.Excel;

@TableName("gh_hkxx_yhbfhz_jkjl")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhHkxxYhbfhzJkjlDo {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long yhbfhzJkjlId;

    /**
     * 银行接口名称
     */
    @Excel(name = "银行接口名称")
    private String jkmc;

    /**
     * 汇总信息id
     */
    @Excel(name = "汇总信息id")
    private Long fkId;

    /**
     * 请求报文
     */
    @Excel(name = "请求报文")
    private String qqbw;

    /**
     * 响应报文
     */
    @Excel(name = "响应报文")
    private String xybw;

    /**
     * 请求结果（成功-S，异常-E）
     */
    private String qqzt;

    /**
     * 请求信息，对应请求结果字段
     */
    @Excel(name = "请求信息，对应请求结果字段")
    private String qqjgxx;


    private String createTime;
    private String updateTime;
    private String updateBy;
    private String createBy;

}

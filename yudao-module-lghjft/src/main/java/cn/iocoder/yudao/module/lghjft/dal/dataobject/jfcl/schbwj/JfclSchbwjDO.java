package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 经费处理-生成划拨文件 DO (gh_hkxx 表)
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JfclSchbwjDO {

    @TableId(value = "HKXX_ID")
    private Long hkxxId;
    private String hkpch;
    private String zh;
    private String hm;
    private String hh;
    private BigDecimal je;
    private String dz;
    private String fy;
    private String yxbj;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}

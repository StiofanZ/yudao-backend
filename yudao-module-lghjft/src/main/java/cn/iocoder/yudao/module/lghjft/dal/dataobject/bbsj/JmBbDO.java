package cn.iocoder.yudao.module.lghjft.dal.dataobject.bbsj;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("jimu_report")
@Data
public class JmBbDO {

    @TableId
    private String id;

    private String code;

    private String name;

    private String status;

    @TableField("del_flag")
    private Integer delFlag;
}

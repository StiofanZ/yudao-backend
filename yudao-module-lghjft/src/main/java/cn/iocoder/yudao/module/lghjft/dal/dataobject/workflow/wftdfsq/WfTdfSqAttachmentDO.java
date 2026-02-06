package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wftdfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
////存储文件的实体类
@TableName("lghjft_wf_tdfsq_attachment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfTdfSqAttachmentDO extends BaseDO { // 移除BaseDO继承

    @TableId
    private Long id;
    private Long applyId;
    private String fileType;
    private String fileUrl;
    private String creator;

//    @TableField(exist = false)
//    private String updater;

    @TableField(exist = false)
    private Boolean deleted;
}
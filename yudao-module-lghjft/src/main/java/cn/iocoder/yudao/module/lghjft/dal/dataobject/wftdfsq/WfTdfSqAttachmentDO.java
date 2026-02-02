package cn.iocoder.yudao.module.lghjft.dal.dataobject.wftdfsq;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
////存储文件的实体类
@TableName("lghjft_wf_tdfsq_attachment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfTdfSqAttachmentDO { // 移除BaseDO继承

    @TableId
    private Long id;
    private Long applyId;
    private String fileType;
    private String fileUrl;
    private String creator;

}
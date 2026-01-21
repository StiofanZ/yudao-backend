package cn.iocoder.yudao.module.file.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件信息对象 file_info
 * 
 * @author shipj
 * @date 2025-05-09
 */
@TableName("file_info")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long fileId;

    /** 文件名 */
    private String fileName;

    /** 文件原名 */
    private String fileOriginName;

    /** 文件后缀 */
    private String fileType;

    /** 文件路径 */
    private String filePath;

    /** 访问地址 */
    private String fileUrl;

    /** 业务id */
    private Long bizId;

    /** 文件大小（kb） */
    private Long fileSize;

    /** 创建者 */
    private Long createBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新者 */
    private Long updateBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 备注 */
    private String remark;

    /** 删除标志 */
    private String delFlag;

    public FileInfoDO(Long fileId, String fileName, String originalFilename, String fileType, String bucket, String fileUrl, Long bizId, Long fileSize, String delFlag) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileOriginName = originalFilename;
        this.fileType = fileType;
        this.filePath = bucket + fileUrl;
        this.fileUrl = fileUrl;
        this.bizId = bizId;
        this.fileSize = fileSize;
        this.delFlag = delFlag;
    }
}

package cn.iocoder.yudao.module.file.dal.dataobject.dto;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息对象 file_info
 * 
 * @author shipj
 * @date 2025-05-09
 */
@Data
public class FileInfoDTO extends PageParam implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
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

    /** 关联业务表名 **/
    private String tableName;

    /** 文件大小（kb） */
    private Long fileSize;

    /** 删除标志 */
    private String delFlag;

}

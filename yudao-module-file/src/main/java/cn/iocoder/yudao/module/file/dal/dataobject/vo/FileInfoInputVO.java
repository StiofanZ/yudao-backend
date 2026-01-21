package cn.iocoder.yudao.module.file.dal.dataobject.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 文件上传信息对象 file_info
 * 
 * @author shipj
 * @date 2025-05-09
 */
@Data
public class FileInfoInputVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 文件名 */
    private MultipartFile file;

    /** 桶 */
    private String bucket;


    public FileInfoInputVO(MultipartFile file, String bucket){
        this.file = file;
        this.bucket = bucket;
    }
}

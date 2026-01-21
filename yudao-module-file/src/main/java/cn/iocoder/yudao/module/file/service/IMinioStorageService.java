package cn.iocoder.yudao.module.file.service;

import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoInputVO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IMinioStorageService {
    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     */
    public String getObjectURL(String bucketName, String objectName);

    /**
     * 文件上传加水印
     *
     * @param file
     * @return
     */
    public FileInfoVO uploadWatermark(MultipartFile file);

    /**
     * 文件上传
     *
     * @param fileInfoInputVO
     * @return
     */
    FileInfoVO uploadFile(FileInfoInputVO fileInfoInputVO);



    /**
     * 文件下载
     * @param fileId
     * @return
     */
    public InputStream downloadFile(Long fileId);

    /**
     * 文件删除
     * @param fileId
     */
    public void removeFile(Long fileId);

    /**
     * 桶创建
     * @param bucketName
     */
    public void createBucket(String bucketName) ;
}

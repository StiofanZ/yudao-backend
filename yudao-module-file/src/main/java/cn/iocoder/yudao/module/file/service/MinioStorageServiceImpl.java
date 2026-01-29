package cn.iocoder.yudao.module.file.service;

import cn.iocoder.yudao.module.file.dal.dataobject.dos.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.dataobject.dto.FileInfoDTO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoInputVO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import cn.iocoder.yudao.module.file.minio.MinioTemplate;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import cn.iocoder.yudao.module.file.utils.DelFlag;
import cn.iocoder.yudao.module.file.utils.FileTypeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author shipj
 */
@Slf4j
@Service
public class MinioStorageServiceImpl implements IMinioStorageService {

    @Resource
    private MinioTemplate minioTemplate;

    @Resource
    private IFileInfoService fileInfoService;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.watermark}")
    private Boolean watermark;

    @Value("${project.profile}")
    private String profile;

    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     */
    @Override
    public String getObjectURL(String bucketName, String objectName) {
        try {
            return minioTemplate.getObjectURL(bucketName, objectName);
        } catch (Exception e) {
            log.error("文件url获取异常：{}", e.getMessage());
            throw new RuntimeException("文件url获取失败");
        }
    }

    @Override
    public FileInfoVO uploadFile(FileInfoInputVO fileInfoInputVO) {
        MultipartFile file = fileInfoInputVO.getFile();
        String bucket = fileInfoInputVO.getBucket();
        // 判断上传文件是否为空
        if (null == file) {
            throw new RuntimeException("上传文件不能为空");
        }
        if(StringUtils.isEmpty(bucket)){
            bucket = bucketName;
        }
        Long fileSize = file.getSize();
        InputStream inputStream = null;
        FileInfoVO fileInfoVO = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("文件上传异常：{}",e);
            throw new RuntimeException(e.getMessage());
        }
        try {
            // 判断存储桶是否存在
            createBucket(bucket);
            // 文件名上传
            String originalFilename = file.getOriginalFilename();
            String fileType = FileTypeUtils.getFileType(originalFilename);
            String fileName = DateUtils.dateTimeNow() + "." + fileType;

            minioTemplate.putObject(bucket, fileName, inputStream);

            //文件日志
            String fileUrl = getObjectURL(bucket, fileName);
            fileInfoService.insertFileInfo(new FileInfoDO(null, fileName, originalFilename,
                    fileType, bucket, fileUrl, null,fileInfoInputVO.getTableName(), fileSize, DelFlag.NO_DEL.getKey()));
            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setFileName(fileName);
            List<FileInfoVO> fileInfoVOS = fileInfoService.selectFileInfoList(fileInfoDTO);
            if(CollectionUtils.isEmpty(fileInfoVOS)){
                throw new RuntimeException("文件上传失败");
            }else{
                fileInfoVO = fileInfoVOS.get(0);
            }
        } catch (Exception e) {
            log.error("文件上传异常：{}", e.getMessage());
            throw new RuntimeException("文件上传失败");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("文件上传异常：{}",e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return fileInfoVO;
    }

    @Override
    public FileInfoVO uploadWatermark(MultipartFile file) {
        // 判断上传文件是否为空
        if (null == file) {
            throw new RuntimeException("上传文件不能为空");
        }
        Long fileSize = file.getSize();
        InputStream inputStream = null;
        FileInfoVO fileInfoVO = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("文件上传异常：{}",e);
            throw new RuntimeException(e.getMessage());
        }
        try {
            // 判断存储桶是否存在
            createBucket(bucketName);
            // 文件名上传
            String originalFilename = file.getOriginalFilename();
            String fileType = FileTypeUtils.getFileType(originalFilename);
            String fileName = DateUtils.dateTimeNow() + "." + fileType;

            minioTemplate.putObject(bucketName, fileName, inputStream);

            //文件日志
            String fileUrl = getObjectURL(bucketName, fileName);
            Long fileId = fileInfoService.insertFileInfo(new FileInfoDO(null, fileName, originalFilename,
                    fileType, bucketName, fileUrl, null, null,fileSize, DelFlag.NO_DEL.getKey()));
            fileInfoVO = fileInfoService.selectFileInfoVOByFileId(fileId);
        } catch (Exception e) {
            log.error("文件上传异常：{}", e.getMessage());
            throw new RuntimeException("文件上传失败");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("文件上传异常：{}",e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return fileInfoVO;
    }

    @Override
    public InputStream downloadFile(Long fileId) {
        InputStream inputStream = null;
        try {
            FileInfoVO fileInfoVO = fileInfoService.selectFileInfoVOByFileId(fileId);
            inputStream = minioTemplate.getObject(bucketName, fileInfoVO.getFileName());
        } catch (Exception e) {
            log.error("文件下载异常：{}", e.getMessage());
            throw new RuntimeException("文件下载失败");
        }
        return inputStream;
    }

    @Override
    public void removeFile(Long fileId) {
        try {
            FileInfoVO fileInfoVO = fileInfoService.selectFileInfoVOByFileId(fileId);
            String fileName = fileInfoVO.getFileName();
            fileInfoService.deleteFileInfoByFileId(fileId);
            minioTemplate.removeObject(bucketName, fileName);
        } catch (Exception e) {
            log.error("文件删除异常：{}", e.getMessage());
            throw new RuntimeException("文件删除异常");
        }
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            minioTemplate.createBucket(bucketName);
        } catch (Exception e) {
            log.error("文件桶创建异常：{}", e.getMessage());
            throw new RuntimeException("文件桶创建失败");
        }
    }
}
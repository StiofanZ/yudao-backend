package cn.iocoder.yudao.module.file.minio;

import cn.iocoder.yudao.module.file.config.MinioConfig;
import io.minio.*;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@Configuration
public class MinioTemplate {

    public MinioTemplate() {}


    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioClient minioClient;

    public void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        MinioClient minioClient = this.minioClient;
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        }
    }

    /**
     * 获取文件外链
     * @param bucketName bucket 名称
     * @param objectName 文件名称
     * @return
     */
    public String getObjectURL(String bucketName,String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        return this.minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
    }

    /**
     * 获取文件
     * @param bucketName
     * @param objectName
     * @return
     */
    public InputStream getObject(String bucketName,String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        return this.minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 上传文件
     * @param bucketName
     * @param objectName
     * @param stream
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        this.minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(stream, stream.available(), -1)
                        .build());
    }

    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     */
    public void removeObject(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        this.minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 下载文件
     * @param bucketName
     * @param objectName
     */
    public InputStream downloadFile(String bucketName,String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.ErrorResponseException {
        return this.minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

}
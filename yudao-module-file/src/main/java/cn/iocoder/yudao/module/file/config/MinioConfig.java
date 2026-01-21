package cn.iocoder.yudao.module.file.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Minio配置类
 * </p>
 *
 * @author shipj
 * @custom.date 2025/1/22 14:19
 */
@Data
@Configuration
public class MinioConfig {

    /**
     * 访问地址
     */
    @Value("${minio.url}")
    private String endpoint;

    /**
     * accessKey类似于用户ID，用于唯一标识你的账户
     */
    @Value("${minio.access-key}")
    private String accessKey;

    /**
     * secretKey是你账户的密码
     */
    @Value("${minio.secret-key}")
    private String secretKey;

    /**
     * 默认存储桶
     */
    @Value("${minio.bucket-name}")
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}
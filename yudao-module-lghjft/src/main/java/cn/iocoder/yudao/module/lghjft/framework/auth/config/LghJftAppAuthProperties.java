package cn.iocoder.yudao.module.lghjft.framework.auth.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "yudao.lghjft.app")
@Validated
@Data
public class LghJftAppAuthProperties {
    /**
     * 应用 ID
     */
    @NotEmpty(message = "App ID 不能为空")
    private String appId;

    /**
     * 应用密钥
     */
    @NotEmpty(message = "App Secret 不能为空")
    private String appSecret;

    /**
     * 平台标识
     */
    @NotEmpty(message = "Platform 不能为空")
    private String platform;

    /**
     * 网关地址
     */
    @NotEmpty(message = "Gateway URL 不能为空")
    private String gatewayUrl;

    /**
     * 解密密钥
     */
    @NotEmpty(message = "Decrypt Key 不能为空")
    private String decryptKey;

}

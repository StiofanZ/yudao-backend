package cn.iocoder.yudao.module.lghjft.framework.auth.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@EnableConfigurationProperties({LghJftAuthProperties.class,
                                LghJftAppAuthProperties.class})
public class LghJftAuthConfiguration {


    /**
     * 验证配置是否加载成功
     */
    @Bean
    public String lghJftConfigValidator(LghJftAuthProperties authProps,
                                        LghJftAppAuthProperties appProps) {
        System.out.println("LghJft 配置加载成功:");
        System.out.println("  - Auth AppId: " + authProps.getAppId());
        System.out.println("  - App AppId: " + appProps.getAppId());
        return "LghJft Config Ready";
    }}

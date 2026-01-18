package cn.iocoder.yudao.module.lghjft.framework.auth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LghJftAuthProperties.class,LghJftAppAuthProperties.class})
public class LghJftAuthConfiguration {
}

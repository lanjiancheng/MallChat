package com.abin.mallchat.oss;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnExpression("${oss.enabled}")
@ConditionalOnProperty(value = "oss.type", havingValue = "minio") // ConditionalOnProperty注解表达，某个属性等于某个值的时候，才触发某些装在
public class MinIOConfiguration {


    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(MinioClient.class) // 用于标识当前类路径中不存在某个类的时候，才触发
    public MinioClient minioClient(OssProperties ossProperties) {
        return MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }

    @Bean
    @ConditionalOnBean({MinioClient.class})
    @ConditionalOnMissingBean(MinIOTemplate.class) // 用于标识当前类路径中不存在某个类的时候，才触发
    public MinIOTemplate minioTemplate(MinioClient minioClient, OssProperties ossProperties) {
        return new MinIOTemplate(minioClient, ossProperties);
    }
}
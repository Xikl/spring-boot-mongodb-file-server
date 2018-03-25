package com.ximo.fileserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description 服务器统一配置
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private String address;

    private Integer port;

}

package org.example.nitroaicoder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * 跨域是指一个 Web 应用程序运行在服务器上的一个域名，而用户访问该 Web 应用程序时，使用的是另一个域名。（前后端端口不一致）
 * 浏览器为了安全，默认禁止跨域请求访问
 *
 * 通过全局跨域配置，让整个项目所有的接口支持跨域，解决跨域报错
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求
        registry.addMapping("/**")
                // 允许发送 cookies
                .allowCredentials( true)
                // 允许访问所有域
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}

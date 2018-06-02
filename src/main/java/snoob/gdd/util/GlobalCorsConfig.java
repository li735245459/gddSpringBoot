package snoob.gdd.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 重写父类提供的跨域请求处理的接口
             * @param registry
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 匹配所有URL
                        .allowedOrigins("http://127.0.0.1:4200") // 允许http://127.0.0.1:4200发起跨域请求
                        .allowCredentials(true) //允许发送Cookie信息
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许跨域请求的方法
                        .allowedHeaders("*"); // 允许跨域请求包含content-type
            }
        };
    }
}

package snoob.gdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import snoob.gdd.filter.JwtFilter;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 创建并初始化Spring应用上下文,启动Spring应用
 *
 * @SpringBootApplication被一下三个注解所修饰
 * @Configuration: 类级注释, 通过@bean注解的公共方法声明bean(与@Bean注解配合使用)
 * @EnableAutoConfiguration: 启用应用程序上下文的自动配置
 * @ComponentScan: 自动扫描指定包下的标有@Component(以及子注解@Service、@Repository、@Controller)注解的类并注册成bean
 */
@SpringBootApplication
@MapperScan(basePackages = "snoob.gdd.mapper")
public class GddApplication {

    /**
     * 注册全局跨域
     */
    @Bean
    public WebMvcConfigurer originConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 重写父类提供的跨域请求处理的接口
             * @param registry
             * .allowCredentials(true) //允许发送Cookie信息
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 匹配所有URL
                        .allowedOrigins("http://127.0.0.1:4200") // 允许http://127.0.0.1:4200发起跨域请求
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许跨域请求的方法
                        .allowedHeaders("*"); // 允许跨域请求包含content-type
            }
        };
    }

    /**
     * 注册自定义JWT拦截器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
//        List<String> urlPatterns = new ArrayList(); //添加需要拦截的url
//        urlPatterns.add("/**");
//        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(GddApplication.class, args);
    }
}

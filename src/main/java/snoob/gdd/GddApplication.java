package snoob.gdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "snoob.gdd.mapper")
public class GddApplication {

    public static void main(String[] args) {
        SpringApplication.run(GddApplication.class, args);
    }
}

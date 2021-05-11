package club.javafan.blog;


import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan( basePackages = {"club.javafan.blog.repository"})
@ForestScan(basePackages = "club.javafan.blog.client")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BlogApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BlogApplication.class, args);
    }
}

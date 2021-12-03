package club.javafan.blog;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages={"club.javafan","com.github.xiaoymin"})
@MapperScan( basePackages = {"club.javafan.blog.repository"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableMethodCache(basePackages="club.javafan")
@EnableCreateCacheAnnotation
public class BlogApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BlogApplication.class, args);
    }
}

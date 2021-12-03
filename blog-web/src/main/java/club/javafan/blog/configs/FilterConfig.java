package club.javafan.blog.configs;

import club.javafan.blog.web.filter.AdminLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class FilterConfig implements WebMvcConfigurer {
    @Value("${file.file-path}")
    private String FILE_PATH;
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 路径映射
        String path = System.getProperty("user.dir").replaceAll("\\\\", "/") + FILE_PATH;
        log.info("static served on: {}",path);
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + path)
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .setCachePeriod(3600);
    }

}
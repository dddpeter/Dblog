package club.javafan.blog.configs;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableKnife4j
@ConditionalOnProperty(prefix = "spring.profiles",name = "active",havingValue = "dev")
public class Knife4jConfiguration {
    @Value("${spring.application.name}")
    String name;

    @Bean(value = "api")
    public Docket api() {
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("Dblog")
                        .termsOfServiceUrl("https://blog.dddpeter.com/")
                        .contact(new Contact("dddpeter","https://blog.dddpeter.com/","dddpeter@126.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName(name)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("club.javafan.blog.web.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

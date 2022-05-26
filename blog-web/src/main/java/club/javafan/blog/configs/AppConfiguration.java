package club.javafan.blog.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hqins
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.profiles",name = "active",havingValue = "dev")
public class AppConfiguration {
    @Value("${spring.application.name}")
    String name;
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dblog")
                        .description("Spring shop sample application")
                        .version("1.0"));
    }
}

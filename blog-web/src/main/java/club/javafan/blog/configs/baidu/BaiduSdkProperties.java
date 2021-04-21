package club.javafan.blog.configs.baidu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "baidu")
@Data
public class BaiduSdkProperties {
    private String appId;
    private String appKey;
    private String appSecret;
}

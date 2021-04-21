package club.javafan.blog.configs.baidu;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class BaiduSdkConfig {
    @Resource
    BaiduSdkProperties baiduSdkProperties;

    @Bean
    public AipContentCensor baiduAipContentCensor(){
        AipContentCensor client = new AipContentCensor(baiduSdkProperties.getAppId(),
                baiduSdkProperties.getAppKey(), baiduSdkProperties.getAppSecret());
        return client;
    }

}

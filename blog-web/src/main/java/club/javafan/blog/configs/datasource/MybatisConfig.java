package club.javafan.blog.configs.datasource;



import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static club.javafan.blog.domain.enums.DataSourceEnum.MASTER;
import static club.javafan.blog.domain.enums.DataSourceEnum.SLAVE;


/**
 * @author dddpeter(博客)
 * @date 2019/12/8 9:46
 * @desc 设置数据源
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class MybatisConfig {
    @Resource
    private DataSource masterDataSource;
    @Resource
    private DataSource slaveDataSource;

    @Value("${mybatis-plus.mapper-locations}")
    private String MAPPER_PATH;

    @Bean
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        // 配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap(2);
        dataSourceMap.put(MASTER, masterDataSource);
        dataSourceMap.put(SLAVE, slaveDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler(){
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("createdTime", LocalDateTime.now(), metaObject);
                this.setFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("modifiedTime", LocalDate.now(), metaObject);

            }
        };
    }
    @Bean
    public GlobalConfig globalConfig(MetaObjectHandler metaObjectHandler){
        GlobalConfig globalConfig  = new GlobalConfig();
        globalConfig.setMetaObjectHandler(metaObjectHandler);
        return globalConfig;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource,GlobalConfig globalConfig) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:huifei.lhf@alibaba-inc.com">huifei.lhf</a>
 * @since 2019-12-27 10:25
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.dao",
        sqlSessionFactoryRef = "mysqlSqlSessionFactory",
        sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
class MysqlDataSourceConfig {
//
//    private static final String APP_NAME = "CAINIAO_UOP_DVC_APP";
//    private static final String APP_KEY = "CAINIAO_UOP_DVC_GROUP";
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    private static final String MAPPER_SCAN_PACKAGE = "com.example.demo.dao";

    @Bean(name = "singleDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource rdsDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);

        dataSource.setTimeBetweenEvictionRunsMillis(2000);
        dataSource.setMinEvictableIdleTimeMillis(600000);
        dataSource.setMaxEvictableIdleTimeMillis(900000);

        dataSource.setValidationQuery("select 1");

        //在空闲时检查有效性, 默认false
        dataSource.setTestWhileIdle(true);
        //在获取连接的时候检查有效性, 默认false
        dataSource.setTestOnBorrow(false);
        //在连接对象返回时，是否测试对象的有效性,默认false
        dataSource.setTestOnReturn(false);
//        dataSource.setKeepAlive(true);
//        dataSource.setPhyMaxUseCount(1000);
  //      dataSource.setFilters("stat");

        return dataSource;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("singleDataSource") DataSource dataSource)
        throws Exception {
        System.out.println("MysqlDataSourceConfig, mysqlSqlSessionFactory#bean start");

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(new org.apache.ibatis.session.Configuration());
        bean.setMapperLocations(resolveMapperLocations(Arrays
                .asList("classpath:/mybatis/**/*.xml")));
    //    bean.setTypeAliasesPackage(MAPPER_SCAN_PACKAGE);
        return bean.getObject();
    }
    public Resource[] resolveMapperLocations(List<String> mapperLocationPaths) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList();
        if (!CollectionUtils.isEmpty(mapperLocationPaths)) {
            for (String mapperLocation : mapperLocationPaths) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (Throwable e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }
    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate(
        @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory mysqlSqlSessionFactory) {
        System.out.println("MysqlDataSourceConfig, mysqlSqlSessionTemplate#bean start");

        return new SqlSessionTemplate(mysqlSqlSessionFactory);
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("singleDataSource") DataSource singleDataSource) {
        System.out.println("MysqlDataSourceConfig, mysqlTransactionManager#bean start");
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(singleDataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate(@Qualifier("mysqlTransactionManager") DataSourceTransactionManager mysqlTransactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(mysqlTransactionManager);

        return transactionTemplate;
    }
}

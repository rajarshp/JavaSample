package com.mycode.foodorderapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.mycode.foodorderapp.repository")
public class MysqlConfig {

    @Value("${mysql.url}")
    private String mysqlUrl;

    @Value("${mysql.driver}")
    private String mysqlDriver;

    @Value("${mysql.username}")
    private String mysqlUserName;

    @Value("${mysql.password}")
    private String mysqlPassword;


    
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(mysqlUrl);
        config.setUsername(mysqlUserName);
        config.setPassword(mysqlPassword);
        config.setDriverClassName(mysqlDriver);
        config.setConnectionTimeout(60000);
        config.setMaximumPoolSize(20);
        config.setAutoCommit(true);
        return new HikariDataSource(config);

    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    
    // Define your MySQL repositories
}

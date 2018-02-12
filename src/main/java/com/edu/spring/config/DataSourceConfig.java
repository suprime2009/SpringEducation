package com.edu.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.edu.spring.persistence.repository")
class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.demo")
    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
        return new HikariDataSource();
    }
}

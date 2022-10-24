package com.gsww.qyws.gzbd.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig   {
	@Bean(name = "yypjDataSource")
    @Qualifier("yypjDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.gzbd")
    public DataSource qyrkDataSource() {
        return DataSourceBuilder.create().build();
    }

   @Bean(name = "yypjJdbcTemplate")
    public JdbcTemplate qyrkJdbcTemplate(
            @Qualifier("yypjDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }




}

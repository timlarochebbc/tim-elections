//package com.tim.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    @ConfigurationProperties("app.datasource")
//    public JdbcTemplate dataSource(){
//        return new JdbcTemplate();
//    }
//}

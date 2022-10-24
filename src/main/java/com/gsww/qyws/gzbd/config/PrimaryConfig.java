//package com.gsww.qyws.gzbd.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author guoyu.huang
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager", basePackages = {
//        "com.gsww.qyws.gzbd"}) // 设置Repository所在位置
//public class PrimaryConfig {
//
//    @Autowired
//    @Qualifier("yypjDataSource")
//    private DataSource firstDataSource;
//
//    @Primary
//    @Bean(name = "primaryEntityManager")
//    public EntityManager firstEntityManager(EntityManagerFactoryBuilder builder) {
//        return firstEntityManagerFactory(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "primaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        Map<String, String> map = new HashMap<>();
//        map.put("hibernate.hbm2ddl.auto", "update");
//        map.put("hibernate.show_sql", "true");
//        return builder.dataSource(firstDataSource).packages("com.gsww.qyws.gzbd.entity").properties(map) // 设置实体类所在位置
//                .persistenceUnit("firstPersistenceUnit").build();
//    }
//
//    @Primary
//    @Bean(name = "primaryTransactionManager")
//    public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(firstEntityManagerFactory(builder).getObject());
//    }
//
//}

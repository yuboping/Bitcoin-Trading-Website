package com.gsww.qyws.gzbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableTransactionManagement
@Configuration
@EnableWebMvc
public class GzbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzbdApplication.class, args);
    }

}

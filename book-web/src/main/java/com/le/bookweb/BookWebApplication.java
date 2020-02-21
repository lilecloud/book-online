package com.le.bookweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@MapperScan(basePackages = {"com.le.bookdao.mapper"})
@ComponentScan(basePackages = {"com.le"})
@EnableTransactionManagement
public class BookWebApplication {

    public static void main(String [] args) {
        SpringApplication.run(BookWebApplication.class, args);
    }

}

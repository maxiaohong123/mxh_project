package com.mxh.springbootstart.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

//@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class}
//)
public class SpringBootStartDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartDemoApp.class,args);
    }
}

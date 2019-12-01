package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
        System.out.println("qwe");
        System.out.println("qwe");
        System.out.println("qwe");
        System.out.println("qwe");
        System.out.println("qwe");
        System.out.println("jjj");
        System.out.println("jjj");
    }
}

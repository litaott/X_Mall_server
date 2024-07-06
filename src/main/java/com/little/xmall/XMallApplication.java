package com.little.xmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.little.xmall.mapper")
public class XMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(XMallApplication.class, args);
    }

}

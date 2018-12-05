package com.pmcc.revicesell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching//启动缓存
public class RevicesellApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevicesellApplication.class, args);
    }
}

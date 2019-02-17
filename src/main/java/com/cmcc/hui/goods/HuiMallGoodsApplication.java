package com.cmcc.hui.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class HuiMallGoodsApplication {

    public static void main(String[] args) {

        log.info("开始启动");
        SpringApplication.run(HuiMallGoodsApplication.class, args);
        log.info("启动完成");
    }


}


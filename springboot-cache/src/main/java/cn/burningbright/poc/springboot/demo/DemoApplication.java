package cn.burningbright.poc.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-23 14:26
 */
@ComponentScan(value = {"cn.burningbright.poc.springboot.**", "cn.hutool.extra.spring"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

}

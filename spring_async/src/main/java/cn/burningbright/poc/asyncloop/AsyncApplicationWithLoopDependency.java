package cn.burningbright.poc.asyncloop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AsyncApplicationWithLoopDependency {

    public static void main(String[] args){
        SpringApplication.run(AsyncApplicationWithLoopDependency.class, args);
    }

}
package cn.burningbright.poc.asyncstep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsyncApplicationWithWrongDependency {

    public static void main(String[] args){
        SpringApplication.run(AsyncApplicationWithWrongDependency.class, args);
    }

}
package cn.burningbright.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationWithThreadSafe {

    public static void main(String[] args){
        SpringApplication.run(ApplicationWithThreadSafe.class, args);
    }

}
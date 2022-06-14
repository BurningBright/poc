package cn.burningbright.poc.asynstart.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:spring_async.xml")
public class AsyncApplicationWithXML {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplicationWithXML.class, args);
    }

}

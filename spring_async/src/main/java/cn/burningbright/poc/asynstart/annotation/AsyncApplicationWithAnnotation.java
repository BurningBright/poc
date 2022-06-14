package cn.burningbright.poc.asynstart.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class AsyncApplicationWithAnnotation {

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("AnExecutor-");
        executor.setMaxPoolSize(3);
        return executor;
    }

    public static void main(String[] args){
        SpringApplication.run(AsyncApplicationWithAnnotation.class, args);
    }

}
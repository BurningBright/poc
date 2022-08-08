package cn.burningbright.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
@ComponentScan(value = "cn.burningbright.poc", useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class, RestControllerAdvice.class})}
)
@Configuration
public class AppConfig {

    @Bean
    public Child child() {
        return new Child();
    }

    public static class Child{}

}
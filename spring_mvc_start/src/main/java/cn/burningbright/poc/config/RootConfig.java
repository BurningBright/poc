package cn.burningbright.poc.config;

import javafx.scene.Parent;
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
@ComponentScan(value = "cn.burningbright.poc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class, RestControllerAdvice.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AppConfig.class})
})
@Configuration
public class RootConfig {

    @Bean
    public Parent parent() {
        return new Parent();
    }

    public class Parent {}

}
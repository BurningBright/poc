package cn.burningbright.value;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cn.burningbright.value")
public class HelloApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(HelloApp.class);

        BeanA bean = ac.getBean(BeanA.class);

        bean.test();
    }
}
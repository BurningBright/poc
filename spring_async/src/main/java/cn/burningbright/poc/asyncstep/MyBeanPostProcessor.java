package cn.burningbright.poc.asyncstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class MyBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AsyncDemo helloService;

    @PostConstruct
    public void init() {
        System.out.println(helloService.getClass());
    }


    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}

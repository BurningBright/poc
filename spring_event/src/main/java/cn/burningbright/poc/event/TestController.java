package cn.burningbright.poc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: chenguang.lin
 * @Date: 2024-02-21 16:11
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/publishEvent")
    public void publishEvent() {
        applicationContext.publishEvent(
                new BaseEvent<>(new Archer("张三"), "add"));
//        applicationContext.publishEvent(
//                new BaseEvent<>(new Knight("李四"), "add"));
//        applicationContext.publishEvent(
//                new BaseEvent<>(new Infantry("王五"), "add"));
    }

}
package cn.burningbright.poc.controller;

import cn.burningbright.poc.config.AppConfig;
import cn.burningbright.poc.config.RootConfig;
import cn.burningbright.poc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
@Slf4j
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    private RootConfig.Parent p;
    @Autowired
    private AppConfig.Child c;

    @RequestMapping("/hello/jsp")
    public String helloJsp() {
        return "test";
    }

    @ResponseBody
    @RequestMapping("/hello/ayo")
    public String helloAyo() {
        log.info(helloService.toString());
        log.info(p.toString());
        log.info(c.toString());
        return helloService.sayHi();
    }

}
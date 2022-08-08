package cn.burningbright.poc;

import cn.burningbright.poc.config.AppConfig;
import cn.burningbright.poc.config.RootConfig;
import cn.burningbright.poc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    private RootConfig.Parent p;
    @Autowired
    private AppConfig.Child c;

    @ResponseBody
    @RequestMapping("/hello/mvc")
    public String hello() {
        System.out.println(helloService);
        return "test";
    }

}
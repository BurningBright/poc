package cn.burningbright.poc;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "hello", tags = "hello")
public class HelloWorld {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

}
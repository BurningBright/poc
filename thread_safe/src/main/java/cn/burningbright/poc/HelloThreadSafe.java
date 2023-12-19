package cn.burningbright.poc;

import cn.burningbright.poc.ts.Class01A;
import cn.burningbright.poc.ts.Class05A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloThreadSafe {

    @Autowired
    private Class05A classA;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return classA.addNum().toString();
    }

}
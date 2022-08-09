package cn.burningbright.poc.service;

import cn.burningbright.poc.config.AppConfig;
import cn.burningbright.poc.config.RootConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private RootConfig.Parent p;

    @Override
    public String sayHi() {
        log.info(p.toString());
        return "hi";
    }

}

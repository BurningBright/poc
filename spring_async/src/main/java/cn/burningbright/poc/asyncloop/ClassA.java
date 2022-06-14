package cn.burningbright.poc.asyncloop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClassA implements InterfaceA {

    @Autowired
    InterfaceB interfaceB;

    @Async
    @Override
    public void funA() {
        log.info("I'm function A");
    }

}

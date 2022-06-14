package cn.burningbright.poc.asyncmix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class Case5Implement implements Case5Interface {

    @Transactional
    @Async
    @Override
    public void funA() {
        Case5Interface xx = Case5Interface.class.cast(AopContext.currentProxy());
        log.info("this is funA");
        xx.funB();
    }

    @Override
    public void funB() {
        log.info("this is funB");
        int b = 1/0;
    }

}
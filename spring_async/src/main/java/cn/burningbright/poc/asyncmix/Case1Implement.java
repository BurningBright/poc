package cn.burningbright.poc.asyncmix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class Case1Implement implements Case1Interface {

    @Transactional
    @Override
    public void funA() {
        Case1Interface xx = Case1Interface.class.cast(AopContext.currentProxy());
        log.info("this is funA");
        xx.funB();
    }

    @Override
    public void funB() {
        log.info("this is funB");
        int b = 1/0;
    }

    @Override
    public void funA1() {
        Case1Interface xx = Case1Interface.class.cast(AopContext.currentProxy());
        log.info("this is funA1");
        xx.funB1();
    }

    @Async
    @Override
    public void funB1() {
        log.info("this is funB1");
        int b = 1/0;
    }

}
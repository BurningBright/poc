package cn.burningbright.poc.asyncmix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class Case2Implement implements Case2Interface {

    @Override
    public void funA() {
        Case2Interface xx = Case2Interface.class.cast(AopContext.currentProxy());
        log.info("this is funA");
        xx.funB();
    }

    @Async
    @Override
    public void funB() {
        log.info("this is funB");
        int b = 1/0;
    }

}
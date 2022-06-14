package cn.burningbright.poc.asyncloop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClassB implements InterfaceB {

    @Autowired
    InterfaceA interfaceA;

    @Lazy
    @Autowired
    InterfaceA interfaceAa;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void funB() {
        interfaceA.funA();
    }

    @Override
    public void funBb() {
        interfaceAa.funA();
    }

    @Override
    public void funBbb() {
        applicationContext.getBean(InterfaceA.class).funA();
    }

}

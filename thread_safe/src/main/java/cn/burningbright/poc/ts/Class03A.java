package cn.burningbright.poc.ts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:23
 */
@Slf4j
@Service
public class Class03A {

    @Resource
    private ApplicationContext applicationContext;

    public Integer addNum(){
        Class03B classB = applicationContext.getBean(Class03B.class);
        classB.setNum(classB.getNum()+1);
        log.info(classB.getNum().toString());
        return classB.getNum();
    }

}
package cn.burningbright.poc.ts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:31
 */
@Slf4j
@Service
public class Class05A {

    @Autowired
    private ObjectProvider<Class03B> class03BObjectProvider;

    public Integer addNum(){
        Class03B classB = class03BObjectProvider.getIfAvailable();
        classB.setNum(classB.getNum()+1);
        log.info(classB.getNum().toString());
        return classB.getNum();
    }

}
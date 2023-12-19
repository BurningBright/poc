package cn.burningbright.poc.ts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:29
 */
@Slf4j
@Service
public class Class04A {

    @Lookup
    public Class03B getClass03B() {
        return null;
    }

    public Integer addNum(){
        Class03B classB = getClass03B();
        classB.setNum(classB.getNum()+1);
        log.info(classB.getNum().toString());
        return classB.getNum();
    }

}
package cn.burningbright.poc.ts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:13
 */
@Slf4j
@Service
public class Class02A {

    @Autowired
    private Class02B classB;

    public Integer addNum(){
        classB.setNum(classB.getNum()+1);
        log.info(classB.getNum().toString());
        return classB.getNum();
    }

}
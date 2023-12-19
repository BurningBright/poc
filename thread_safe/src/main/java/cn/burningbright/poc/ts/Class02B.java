package cn.burningbright.poc.ts;

import org.springframework.stereotype.Component;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:11
 */
@Component
public class Class02B {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNum() {
        return integerThreadLocal.get();
    }

    public void setNum(Integer num) {
        integerThreadLocal.set(num);
    }

}
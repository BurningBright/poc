package cn.burningbright.poc.ts;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: chenguang.lin
 * @Date: 2023-12-18 13:23
 */
@Component
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class Class03B {

    private Integer num = 0;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
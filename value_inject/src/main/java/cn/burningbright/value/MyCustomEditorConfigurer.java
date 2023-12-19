package cn.burningbright.value;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyCustomEditorConfigurer implements BeanFactoryPostProcessor, Ordered {

    private int order = Ordered.LOWEST_PRECEDENCE;  // default: same as non-Ordered

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ///  把我们自定义的 转换器器注册进去
        beanFactory.registerCustomEditor(Car.class, CarPropertyEditor.class);
    }

    @Override
    public int getOrder() {
        return this.order;
    }

}
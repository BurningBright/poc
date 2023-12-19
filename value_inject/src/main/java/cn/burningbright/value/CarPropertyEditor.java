package cn.burningbright.value;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class CarPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        ///  这实现我们的 字符串 转 自定义对象的 逻辑
        if (StringUtils.hasText(text)) {
            String[] split = text.split("\\|");

            Car car = new Car(split[0], split[1]);
            setValue(car);
        }
        else {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        Car value = (Car) getValue();
        return (value != null ? value.toString() : "");
    }

}
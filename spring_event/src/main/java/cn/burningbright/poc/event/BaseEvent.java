package cn.burningbright.poc.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * @Author: chenguang.lin
 * @Date: 2024-02-21 16:10
 */
@Data
@AllArgsConstructor
public class BaseEvent<T> implements ResolvableTypeProvider {

    private T data;

    private String addOrUpdate;

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(), ResolvableType.forInstance(getData()));
    }

}
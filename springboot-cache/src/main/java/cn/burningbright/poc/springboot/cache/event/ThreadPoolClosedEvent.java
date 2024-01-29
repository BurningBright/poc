package cn.burningbright.poc.springboot.cache.event;


import cn.burningbright.poc.springboot.cache.utils.CacheHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ThreadPoolClosedEvent implements ApplicationListener<ContextClosedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        while (true) {
            if (CacheHelper.getMethodExecutor().getActiveCount() <= 0) {
                log.info("thread pool is closed");
                break;
            }
            TimeUnit.MICROSECONDS.sleep(10);
        }
    }

}

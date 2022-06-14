package cn.burningbright.poc.asyncstep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@Component
public class AsyncDemo {

    @Async
    public void asyncInvokeSimplest() {

        log.info("asyncSimplest");

    }


    /**
     * 带参数的异步调⽤异步⽅法可以传⼊参数
     *
     * @param s
     */

    @Async
    public void asyncInvokeWithParameter(String s) {

        log.info("asyncInvokeWithParameter, parameter={}", s);

    }

    /**
     * 异常调⽤返回Future
     *
     * @param i
     * @return
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {

        log.info("asyncInvokeReturnFuture, parameter={}", i);

        Future<String> future;

        try {

            Thread.sleep(1000 * 1);

            future = new AsyncResult<String>("success:" + i);

        } catch (InterruptedException e) {

            future = new AsyncResult<String>("error");

        }

        return future;

    }

    @Async
    public Future<String> asyncInvokeReturnFuture() {

        log.info("asyncInvokeReturnFuture");
        return new AsyncResult<String>(Thread.currentThread().getName());

    }

}

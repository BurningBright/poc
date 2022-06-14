import cn.burningbright.poc.asynstart.annotation.AsyncApplicationWithAnnotation;
import cn.burningbright.poc.asynstart.annotation.AsyncDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithAnnotation.class)
public class AsyncApplicationWithAnnotationTests {

    @Autowired
    private AsyncDemo asyncDemo;

    /**
     * 注解启动异步线程池
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {

        asyncDemo.asyncInvokeSimplest();
        asyncDemo.asyncInvokeWithParameter("test");

        Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
        Assert.assertTrue(future.get().contains("AnExecutor"));

    }

}
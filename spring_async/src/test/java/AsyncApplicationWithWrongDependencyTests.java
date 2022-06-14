import cn.burningbright.poc.asyncstep.AsyncApplicationWithWrongDependency;
import cn.burningbright.poc.asyncstep.AsyncDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithWrongDependency.class)
public class AsyncApplicationWithWrongDependencyTests {

    @Autowired
    private AsyncDemo asyncDemo;

    /**
     * 错误依赖导致加载顺序错乱
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {

        asyncDemo.asyncInvokeSimplest();

        asyncDemo.asyncInvokeWithParameter("test");

        Future<String> future = asyncDemo.asyncInvokeReturnFuture();

        Assert.assertTrue(future.get().contains("main"));

    }

}
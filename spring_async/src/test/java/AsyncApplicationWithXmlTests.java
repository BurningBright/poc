import cn.burningbright.poc.asynstart.annotation.AsyncDemo;
import cn.burningbright.poc.asynstart.xml.AsyncApplicationWithXML;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithXML.class)
public class AsyncApplicationWithXmlTests {

    @Autowired
    private AsyncDemo asyncDemo;

    /**
     * xml配置启动异步线程池
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void contextLoads() throws InterruptedException, ExecutionException {
        Future<String> future = asyncDemo.asyncInvokeReturnFuture(1);
        Assert.assertTrue(future.get().contains("xmlExecutor"));
    }

}
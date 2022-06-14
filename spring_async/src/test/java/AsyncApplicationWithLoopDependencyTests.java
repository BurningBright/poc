import cn.burningbright.poc.asyncloop.AsyncApplicationWithLoopDependency;
import cn.burningbright.poc.asyncloop.InterfaceB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithLoopDependency.class)
public class AsyncApplicationWithLoopDependencyTests {

    @Autowired
    private InterfaceB interfaceB;

    /**
     * 正常启动，未异步执行
     * 在ClassA 加入 @DependsOn("ClassB")注解后启动报错
     */
    @Test
    public void bTest() {
        interfaceB.funB();
    }

    /**
     * 懒加载形式，异步执行
     */
    @Test
    public void bbTest() {
        interfaceB.funBb();
    }

    /**
     * 从应用上下文中取到代理对象，异步执行
     */
    @Test
    public void bbbTest() {
        interfaceB.funBbb();
    }

}
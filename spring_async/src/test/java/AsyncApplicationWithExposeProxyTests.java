import cn.burningbright.poc.asyncmix.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= AsyncApplicationWithExposeProxy.class)
public class AsyncApplicationWithExposeProxyTests {

    @Autowired
    Case1Interface cc1;

    @Autowired
    Case2Interface cc2;

    @Autowired
    Case3Interface cc3;

    @Autowired
    Case4Interface cc4;

    @Autowired
    Case5Interface cc5;

    /**
     * 事务执行
     */
    @Test
    public void case1() {
        cc1.funA();
    }

    /**
     * 异步执行，与事务注解位置无关，仅与有没有事务有关
     */
    @Test
    public void case11() {
        cc1.funA1();
    }

    /**
     * 非入口注解，异步执行报错
     */
    @Test
    public void case2() {
        cc2.funA();
    }

    /**
     * 事务、异步正常执行
     */
    @Test
    public void case3() {
        cc3.funA();
    }

    /**
     * 事务、异步正常执行
     */
    @Test
    public void case4() {
        cc4.funA();
    }

    /**
     * 入口注解，异步执行正常，代理对象未绑定至异步线程
     * @throws InterruptedException
     */
    @Test
    public void case5() throws InterruptedException {
        cc5.funA();
        Thread.sleep(3000);
    }

}
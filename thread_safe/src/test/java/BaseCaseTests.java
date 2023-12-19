import cn.burningbright.poc.ApplicationWithThreadSafe;
import cn.burningbright.poc.ts.Class01A;
import cn.burningbright.poc.ts.Class02A;
import cn.burningbright.poc.ts.Class03A;
import cn.burningbright.poc.ts.Class04A;
import cn.burningbright.poc.ts.Class05A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationWithThreadSafe.class)
public class BaseCaseTests {

    @Autowired
    private Class01A class01A;

    @Autowired
    private Class02A class02A;

    @Autowired
    private Class03A class03A;

    @Autowired
    private Class04A class04A;

    @Autowired
    private Class05A class05A;

    public BaseCaseTests() {
    }

    @Test
    public void test01() {
        for (int i=0; i<10; i++) {
            Integer result = class01A.addNum();
            Assert.assertEquals(i+1, result.intValue());
        }
    }

    @Test
    public void test02() {
        for (int i=0; i<10; i++) {
            Thread tt = new Thread(new Runnable() {
                public void run() {
                    Integer result = class02A.addNum();
                    Assert.assertEquals(1, result.intValue());
                }
            });
            tt.start();
        }
    }

    @Test
    public void test03() {
        for (int i=0; i<10; i++) {
            Integer result = class03A.addNum();
            Assert.assertEquals(1, result.intValue());
        }
    }

    @Test
    public void test04() {
        for (int i=0; i<10; i++) {
            Integer result = class04A.addNum();
            Assert.assertEquals(1, result.intValue());
        }
    }

    @Test
    public void test05() {
        for (int i=0; i<10; i++) {
            Integer result = class05A.addNum();
            Assert.assertEquals(1, result.intValue());
        }
    }

}
